package com.demo.proworks.att.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.demo.proworks.att.dao.AttDAO;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.batch.service.LargeFileService;
import com.demo.proworks.unit.service.impl.FileUploadPerformanceLogger;
import com.inswave.elfw.log.AppLog;

/**
 * 수정된 대용량 파일 처리 서비스 구현체
 * - UnitTest 패턴을 참고하여 DB 저장 로직 개선
 * - 트랜잭션 및 예외 처리 개선
 * - fileId 생성 및 관리 방식 개선
 */
@Service("largeFileServiceImpl")
public class LargeFileServiceImpl implements LargeFileService {
    
    @Resource(name = "attDAO")
    private AttDAO attDAO;
    
    @Resource
    private AmazonS3 amazonS3;
    
    @Resource
    private FileUploadPerformanceLogger performanceLogger;
    
    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    
    @Value("${file.upload.path}")
    private String uploadPath;
    
    // 멀티파트 업로드 설정
    private static final long MULTIPART_THRESHOLD = 5 * 1024 * 1024; // 5MB
    private static final long PART_SIZE = 5 * 1024 * 1024; // 5MB per part
    private static final int MAX_THREADS = 4; // 병렬 업로드 스레드 수
    
    // 성능 비교용 통계 저장소 (실제 환경에서는 Redis/DB 사용)
    private final Map<String, Map<String, Object>> uploadStats = new HashMap<>();
    
    /**
     * 대용량 파일 업로드 (개선된 멀티파트 방식)
     */
    @Override
    public AttVo uploadLargeFile(MultipartFile file, String refType, String refId) throws Exception {
        String sessionId = performanceLogger.logUploadStart(file.getOriginalFilename(), file.getSize(), "MULTIPART_UPLOAD");
        performanceLogger.logSystemResources("UPLOAD_START");
        
        try {
            // 파일 크기에 따라 업로드 방식 결정
            if (file.getSize() < MULTIPART_THRESHOLD) {
                AppLog.info("파일 크기가 작아 단일 업로드 사용: " + (file.getSize() / 1024 / 1024) + "MB");
                return uploadSinglePart(file, refType, refId, sessionId);
            }
            
            return uploadMultipart(file, refType, refId, sessionId);
            
        } catch (Exception e) {
            performanceLogger.logUploadError(sessionId, file.getOriginalFilename(), file.getSize(), 
                System.currentTimeMillis(), "MULTIPART_UPLOAD", e.getMessage());
            throw new RuntimeException("대용량 파일 업로드 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 기존 방식 파일 업로드 (비교용)
     */
    @Override
    public AttVo uploadLargeFileTraditional(MultipartFile file, String refType, String refId) throws Exception {
        String sessionId = performanceLogger.logUploadStart(file.getOriginalFilename(), file.getSize(), "TRADITIONAL_UPLOAD");
        performanceLogger.logSystemResources("TRADITIONAL_UPLOAD_START");
        
        try {
            return uploadTraditionalMethod(file, refType, refId, sessionId);
            
        } catch (Exception e) {
            performanceLogger.logUploadError(sessionId, file.getOriginalFilename(), file.getSize(), 
                System.currentTimeMillis(), "TRADITIONAL_UPLOAD", e.getMessage());
            throw new RuntimeException("기존 방식 파일 업로드 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 멀티파트 업로드 실행 (개선된 버전)
     */
    private AttVo uploadMultipart(MultipartFile file, String refType, String refId, String sessionId) throws Exception {
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        String fileExtension = getFileExtension(fileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String s3Key = uploadPath + "/" + refType + "/" + refId + "/" + storedFileName;
        
        AppLog.info("멀티파트 업로드 시작: " + fileName + " (" + (fileSize / 1024 / 1024) + "MB)");
        
        // 1. 멀티파트 업로드 초기화
        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, s3Key);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(fileSize);
        initRequest.setObjectMetadata(metadata);
        
        InitiateMultipartUploadResult initResponse = amazonS3.initiateMultipartUpload(initRequest);
        String uploadId = initResponse.getUploadId();
        
        AppLog.info("멀티파트 업로드 초기화 완료. Upload ID: " + uploadId);
        
        List<PartETag> partETags = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        List<CompletableFuture<PartETag>> futures = new ArrayList<>();
        
        try (InputStream inputStream = file.getInputStream()) {
            // 2. 파일을 청크로 분할하여 병렬 업로드
            long remainingBytes = fileSize;
            int partNumber = 1;
            
            while (remainingBytes > 0) {
                long partSize = Math.min(PART_SIZE, remainingBytes);
                
                // 청크 데이터 읽기
                byte[] partData = new byte[(int) partSize];
                int bytesRead = inputStream.read(partData);
                
                if (bytesRead != partSize) {
                    throw new IOException("파일 읽기 오류: 예상 크기와 실제 읽은 크기가 다름");
                }
                
                // 비동기 파트 업로드
                final int currentPartNumber = partNumber;
                final byte[] currentPartData = partData.clone();
                
                CompletableFuture<PartETag> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        return uploadPart(bucketName, s3Key, uploadId, currentPartNumber, currentPartData);
                    } catch (Exception e) {
                        throw new RuntimeException("파트 업로드 실패: " + e.getMessage(), e);
                    }
                }, executor);
                
                futures.add(future);
                
                remainingBytes -= partSize;
                partNumber++;
                
                AppLog.debug("파트 " + currentPartNumber + " 업로드 큐에 추가: " + (partSize / 1024 / 1024) + "MB");
            }
            
            // 3. 모든 파트 업로드 완료 대기
            for (CompletableFuture<PartETag> future : futures) {
                partETags.add(future.get());
            }
            
            AppLog.info("모든 파트 업로드 완료. 총 " + partETags.size() + " 개 파트");
            
            // 4. 멀티파트 업로드 완료
            CompleteMultipartUploadRequest completeRequest = new CompleteMultipartUploadRequest(
                bucketName, s3Key, uploadId, partETags);
            amazonS3.completeMultipartUpload(completeRequest);
            
            // 5. DB에 파일 정보 저장 (UnitTest 패턴 적용)
            AttVo resultVo = saveFileToDatabase(file, refType, refId, s3Key, storedFileName);
            
            // 6. 성능 로깅
            performanceLogger.logUploadComplete(sessionId, fileName, fileSize, 
                System.currentTimeMillis(), "MULTIPART_UPLOAD");
            performanceLogger.logSystemResources("MULTIPART_UPLOAD_COMPLETE");
            
            // 성능 통계 저장
            saveUploadStats(uploadId, "MULTIPART_UPLOAD", fileSize, System.currentTimeMillis(), partETags.size());
            
            AppLog.info("멀티파트 업로드 성공: " + fileName + " -> " + s3Key);
            return resultVo;
            
        } catch (Exception e) {
            // 업로드 실패 시 정리
            try {
                AbortMultipartUploadRequest abortRequest = new AbortMultipartUploadRequest(bucketName, s3Key, uploadId);
                amazonS3.abortMultipartUpload(abortRequest);
                AppLog.info("멀티파트 업로드 중단됨: " + uploadId);
            } catch (Exception abortException) {
                AppLog.error("멀티파트 업로드 중단 실패: " + abortException.getMessage());
            }
            throw e;
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }
    
    /**
     * 개별 파트 업로드
     */
    private PartETag uploadPart(String bucketName, String s3Key, String uploadId, int partNumber, byte[] partData) throws Exception {
        long partStartTime = System.currentTimeMillis();
        
        UploadPartRequest uploadRequest = new UploadPartRequest()
            .withBucketName(bucketName)
            .withKey(s3Key)
            .withUploadId(uploadId)
            .withPartNumber(partNumber)
            .withInputStream(new java.io.ByteArrayInputStream(partData))
            .withPartSize(partData.length);
        
        UploadPartResult uploadResult = amazonS3.uploadPart(uploadRequest);
        
        long partDuration = System.currentTimeMillis() - partStartTime;
        AppLog.debug("파트 " + partNumber + " 업로드 완료: " + (partData.length / 1024 / 1024) + "MB" + " , " + partDuration + "ms");
        
        return uploadResult.getPartETag();
    }
    
    /**
     * 단일 파트 업로드 (5MB 미만 파일용)
     */
    private AttVo uploadSinglePart(MultipartFile file, String refType, String refId, String sessionId) throws Exception {
        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String s3Key = uploadPath + "/" + refType + "/" + refId + "/" + storedFileName;
        
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        
        amazonS3.putObject(bucketName, s3Key, file.getInputStream(), metadata);
        
        AttVo resultVo = saveFileToDatabase(file, refType, refId, s3Key, storedFileName);
        
        performanceLogger.logUploadComplete(sessionId, fileName, file.getSize(), 
            System.currentTimeMillis(), "SINGLE_UPLOAD");
        
        return resultVo;
    }
    
    /**
     * 기존 방식 업로드 (비교용)
     */
    private AttVo uploadTraditionalMethod(MultipartFile file, String refType, String refId, String sessionId) throws Exception {
        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String s3Key = uploadPath + "/" + refType + "/" + refId + "/" + storedFileName;
        
        // 기존 방식: 한 번에 전체 파일 업로드
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        
        // 메모리에 전체 파일 로드 (기존 방식의 단점)
        byte[] fileBytes = file.getBytes();
        java.io.ByteArrayInputStream inputStream = new java.io.ByteArrayInputStream(fileBytes);
        
        amazonS3.putObject(bucketName, s3Key, inputStream, metadata);
        
        AttVo resultVo = saveFileToDatabase(file, refType, refId, s3Key, storedFileName);
        
        performanceLogger.logUploadComplete(sessionId, fileName, file.getSize(), 
            System.currentTimeMillis(), "TRADITIONAL_UPLOAD");
        performanceLogger.logSystemResources("TRADITIONAL_UPLOAD_COMPLETE");
        
        // 성능 통계 저장
        saveUploadStats(UUID.randomUUID().toString(), "TRADITIONAL_UPLOAD", file.getSize(), System.currentTimeMillis(), 1);
        
        return resultVo;
    }
    
    /**
     * 성능 비교 테스트
     */
    @Override
    public Map<String, Object> performanceComparisonTest(MultipartFile file, String refType, String refId) throws Exception {
        Map<String, Object> comparisonResult = new HashMap<>();
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        
        AppLog.info("=== 파일 업로드 성능 비교 테스트 시작 ===");
        AppLog.info("테스트 파일: " + fileName + " (" + (fileSize / 1024 / 1024) + "MB)");
        
        try {
            // 1. 기존 방식 테스트
            long traditionalStartTime = System.currentTimeMillis();
            AttVo traditionalResult = uploadLargeFileTraditional(file, refType, refId + "_traditional");
            long traditionalDuration = System.currentTimeMillis() - traditionalStartTime;
            
            // 메모리 정리
            System.gc();
            Thread.sleep(1000);
            
            // 2. 개선된 방식 테스트  
            long multipartStartTime = System.currentTimeMillis();
            AttVo multipartResult = uploadLargeFile(file, refType, refId + "_multipart");
            long multipartDuration = System.currentTimeMillis() - multipartStartTime;
            
            // 3. 성능 비교 결과 생성
            double traditionalThroughput = (fileSize / 1024.0 / 1024.0) / (traditionalDuration / 1000.0);
            double multipartThroughput = (fileSize / 1024.0 / 1024.0) / (multipartDuration / 1000.0);
            double improvementPercent = ((double) traditionalDuration - multipartDuration) / traditionalDuration * 100;
            
            comparisonResult.put("fileName", fileName);
            comparisonResult.put("fileSize", fileSize);
            comparisonResult.put("fileSizeMB", fileSize / 1024 / 1024);
            
            comparisonResult.put("traditionalDuration", traditionalDuration);
            comparisonResult.put("multipartDuration", multipartDuration);
            comparisonResult.put("traditionalThroughput", String.format("%.2f", traditionalThroughput));
            comparisonResult.put("multipartThroughput", String.format("%.2f", multipartThroughput));
            comparisonResult.put("improvementPercent", String.format("%.1f", improvementPercent));
            
            comparisonResult.put("traditionalResult", traditionalResult);
            comparisonResult.put("multipartResult", multipartResult);
            
            // 성능 비교 로깅
            performanceLogger.logUploadMethodComparison("TRADITIONAL", fileSize, traditionalDuration, 
                "기존 단일 업로드 방식");
            performanceLogger.logUploadMethodComparison("MULTIPART", fileSize, multipartDuration, 
                "개선된 멀티파트 업로드 방식");
            
            AppLog.info("=== 성능 비교 결과 ===");
            AppLog.info("기존 방식: " + traditionalDuration + "ms (" + String.format("%.2f", traditionalThroughput) + "MB/s)");
            AppLog.info("개선 방식: " + multipartDuration + "ms (" + String.format("%.2f", multipartThroughput) + "MB/s)");
            AppLog.info("성능 개선: " + String.format("%.1f", improvementPercent) + "%");
            
            return comparisonResult;
            
        } catch (Exception e) {
            AppLog.error("성능 비교 테스트 실패: " + e.getMessage());
            comparisonResult.put("success", false);
            comparisonResult.put("errorMessage", e.getMessage());
            return comparisonResult;
        }
    }
    
    /**
     * 대용량 파일 다운로드 (스트리밍)
     */
    @Override
    public S3Object downloadLargeFile(String fileId) throws Exception {
        AppLog.info("대용량 파일 다운로드 시작: " + fileId);
        
        AttVo fileVo = attDAO.selectFileInfo(fileId);
        if (fileVo == null) {
            throw new RuntimeException("파일을 찾을 수 없습니다: " + fileId);
        }
        
        long downloadStartTime = System.currentTimeMillis();
        S3Object s3Object = amazonS3.getObject(bucketName, fileVo.getS3Key());
        
        long downloadDuration = System.currentTimeMillis() - downloadStartTime;
        long fileSize = s3Object.getObjectMetadata().getContentLength();
        
        AppLog.info("대용량 파일 다운로드 완료: " + fileVo.getOriginalFileName() + " (" + (fileSize / 1024 / 1024) + "MB" + " , " + downloadDuration + "ms)");
        
        return s3Object;
    }
    
    /**
     * 업로드 진행률 조회 (멀티파트 업로드용)
     */
    @Override
    public Map<String, Object> getUploadProgress(String uploadId) throws Exception {
        Map<String, Object> progress = uploadStats.get(uploadId);
        if (progress == null) {
            progress = new HashMap<>();
            progress.put("status", "NOT_FOUND");
        }
        return progress;
    }
    
    /**
     * 대용량 파일 업로드 통계 조회
     */
    @Override
    public Map<String, Object> getLargeFileUploadStatistics() throws Exception {
        Map<String, Object> statistics = new HashMap<>();
        
        long totalUploads = uploadStats.size();
        long totalBytes = uploadStats.values().stream()
            .mapToLong(stat -> (Long) stat.get("fileSize"))
            .sum();
        
        double averageDuration = uploadStats.values().stream()
            .mapToLong(stat -> (Long) stat.get("duration"))
            .average()
            .orElse(0.0);
        
        statistics.put("totalUploads", totalUploads);
        statistics.put("totalBytes", totalBytes);
        statistics.put("totalMB", totalBytes / 1024 / 1024);
        statistics.put("averageDuration", String.format("%.2f", averageDuration));
        
        return statistics;
    }
    
    // ===== Private Helper Methods =====
    
    /**
     * DB에 파일 정보 저장 (올바른 AUTO_INCREMENT 패턴)
     */
    private AttVo saveFileToDatabase(MultipartFile file, String refType, String refId, String s3Key, String storedFileName) throws Exception {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFileName);
        
        AppLog.debug("=== DB 파일 저장 시작 (AUTO_INCREMENT 패턴) ===");
        AppLog.debug("originalFileName: " + originalFileName);
        AppLog.debug("s3Key: " + s3Key);
        AppLog.debug("fileSize: " + file.getSize());
        
        try {
            // 1. file 테이블 insert (AUTO_INCREMENT로 id 자동 생성)
            AttVo fileVo = new AttVo();
            fileVo.setOriginalFileName(originalFileName);
            fileVo.setStoredFileName(storedFileName);
            fileVo.setFileSize(String.valueOf(file.getSize()));
            fileVo.setFileExtension(fileExtension);
            fileVo.setS3Key(s3Key);
            
            attDAO.insertFile(fileVo);
            
            // insertFile 후 자동 생성된 file.id를 가져옴
            String fileId = fileVo.getFileId(); // "47" 같은 숫자 문자열
            AppLog.debug("file 테이블 저장 완료, 자동 생성된 fileId: " + fileId);

            // 2. file_attachments 테이블 insert (id도 AUTO_INCREMENT, file_id는 위에서 생성된 값)
            AttVo attachmentVo = new AttVo();
            // id는 설정하지 않음 - AUTO_INCREMENT로 자동 생성됨
            attachmentVo.setFileId(fileId); // file 테이블에서 생성된 ID 사용
            attachmentVo.setRefType(refType);
            attachmentVo.setRefId(refId);
            attachmentVo.setIsDeleted("N");
            
            attDAO.insertFileAttachment(attachmentVo);
            
            // insertFileAttachment 후 자동 생성된 file_attachments.id를 가져옴
            String attachmentId = attachmentVo.getId(); // AUTO_INCREMENT로 생성된 ID
            AppLog.debug("file_attachments 테이블 저장 완료, 자동 생성된 attachmentId: " + attachmentId);

            // 3. 반환용 AttVo 설정
            AttVo resultVo = new AttVo();
            resultVo.setId(attachmentId);        // file_attachments.id (AUTO_INCREMENT)
            resultVo.setFileId(fileId);          // file.id (AUTO_INCREMENT)
            resultVo.setRefType(refType);
            resultVo.setRefId(refId);
            resultVo.setOriginalFileName(originalFileName);
            resultVo.setStoredFileName(storedFileName);
            resultVo.setFileSize(String.valueOf(file.getSize()));
            resultVo.setFileExtension(fileExtension);
            resultVo.setS3Bucket(bucketName);
            resultVo.setS3Key(s3Key);
            resultVo.setIsDeleted("N");

            AppLog.debug("=== DB 파일 저장 완료 ===");
            AppLog.debug("file.id: " + fileId + ", file_attachments.id: " + attachmentId);
            return resultVo;
            
        } catch (Exception e) {
            AppLog.error("DB 파일 저장 실패: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("파일 정보 저장 실패: " + e.getMessage(), e);
        }
    }
    
    private void saveUploadStats(String uploadId, String method, long fileSize, long duration, int partCount) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("method", method);
        stats.put("fileSize", fileSize);
        stats.put("duration", duration);
        stats.put("partCount", partCount);
        stats.put("throughput", (fileSize / 1024.0 / 1024.0) / (duration / 1000.0));
        stats.put("timestamp", System.currentTimeMillis());
        
        uploadStats.put(uploadId, stats);
    }
    
    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
}