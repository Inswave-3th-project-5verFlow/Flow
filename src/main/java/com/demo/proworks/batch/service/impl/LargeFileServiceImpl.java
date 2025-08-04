package com.demo.proworks.batch.service.impl;

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
 * 대용량 파일 처리 서비스
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
    
    private static final long MULTIPART_THRESHOLD = 5 * 1024 * 1024; 
    private static final long PART_SIZE = 5 * 1024 * 1024; 
    private static final int MAX_THREADS = 4; 
    
    private final Map<String, Map<String, Object>> uploadStats = new HashMap<>();
    
    /**
     * 대용량 파일 업로드
     */
    @Override
    public AttVo uploadLargeFile(MultipartFile file, String refType, String refId) throws Exception {
        String sessionId = performanceLogger.logUploadStart(file.getOriginalFilename(), file.getSize(), "MULTIPART_UPLOAD");
        performanceLogger.logSystemResources("UPLOAD_START");
        
        try {
            if (file.getSize() < MULTIPART_THRESHOLD) {
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
     * 기존 방식 파일 업로드
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
     * 멀티파트 업로드 실행
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
                    throw new IOException("파일 읽기 오류");
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
                
            }
            
            for (CompletableFuture<PartETag> future : futures) {
                partETags.add(future.get());
            }
            
            
            CompleteMultipartUploadRequest completeRequest = new CompleteMultipartUploadRequest(
                bucketName, s3Key, uploadId, partETags);
            amazonS3.completeMultipartUpload(completeRequest);
            
            AttVo resultVo = saveFileToDatabase(file, refType, refId, s3Key, storedFileName);
            
            performanceLogger.logUploadComplete(sessionId, fileName, fileSize, 
                System.currentTimeMillis(), "MULTIPART_UPLOAD");
            performanceLogger.logSystemResources("MULTIPART_UPLOAD_COMPLETE");
            
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
     * 단일 파트 업로드
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
     * 기존 방식 업로드
     */
    private AttVo uploadTraditionalMethod(MultipartFile file, String refType, String refId, String sessionId) throws Exception {
        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String s3Key = uploadPath + "/" + refType + "/" + refId + "/" + storedFileName;
        
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        
        byte[] fileBytes = file.getBytes();
        java.io.ByteArrayInputStream inputStream = new java.io.ByteArrayInputStream(fileBytes);
        
        amazonS3.putObject(bucketName, s3Key, inputStream, metadata);
        
        AttVo resultVo = saveFileToDatabase(file, refType, refId, s3Key, storedFileName);
        
        performanceLogger.logUploadComplete(sessionId, fileName, file.getSize(), 
            System.currentTimeMillis(), "TRADITIONAL_UPLOAD");
        performanceLogger.logSystemResources("TRADITIONAL_UPLOAD_COMPLETE");
        
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
            long traditionalStartTime = System.currentTimeMillis();
            AttVo traditionalResult = uploadLargeFileTraditional(file, refType, refId + "_traditional");
            long traditionalDuration = System.currentTimeMillis() - traditionalStartTime;
            
            System.gc();
            Thread.sleep(1000);
            
            long multipartStartTime = System.currentTimeMillis();
            AttVo multipartResult = uploadLargeFile(file, refType, refId + "_multipart");
            long multipartDuration = System.currentTimeMillis() - multipartStartTime;
            
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
    
    
    /**
     * DB에 파일 정보 저장
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
            
            String fileId = fileVo.getFileId();
            AppLog.debug("file 테이블 저장 완료, 자동 생성된 fileId: " + fileId);

            AttVo attachmentVo = new AttVo();
            attachmentVo.setFileId(fileId);
            attachmentVo.setRefType(refType);
            attachmentVo.setRefId(refId);
            attachmentVo.setIsDeleted("N");
            
            attDAO.insertFileAttachment(attachmentVo);
            
            String attachmentId = attachmentVo.getId();
            AppLog.debug("file_attachments 테이블 저장 완료, 자동 생성된 attachmentId: " + attachmentId);

            AttVo resultVo = new AttVo();
            resultVo.setId(attachmentId); 
            resultVo.setFileId(fileId);   
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