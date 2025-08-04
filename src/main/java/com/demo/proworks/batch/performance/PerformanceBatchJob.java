package com.demo.proworks.batch.performance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.demo.proworks.batch.service.LargeFileService;
import com.demo.proworks.unit.service.impl.FileUploadPerformanceLogger;
import com.inswave.elfw.log.AppLog;

/**
 * 파일 업로드 성능 테스트 배치 작업
 * 프로웍스 배치 가이드를 참고한 성능 테스트 및 리포트 생성
 * 
 * 실행 방법:
 * 1. 스케줄러를 통한 자동 실행
 * 2. 웹 인터페이스를 통한 수동 실행
 * 3. 명령줄을 통한 배치 실행
 */
@Component
public class PerformanceBatchJob {
    
    @Resource(name = "largeFileServiceImpl")
    private LargeFileService largeFileService;
    
    @Resource
    private FileUploadPerformanceLogger performanceLogger;
    
    @Resource
    
    @Value("${batch.performance.report.path:/tmp/performance_reports}")
    private String reportPath;
    
    @Value("${batch.performance.test.file.path:/tmp/test_files}")
    private String testFilePath;
    
    
    
    
    /**
     * 성능 테스트 실행
     */
    private Map<String, Object> runPerformanceTests(File testFile) throws Exception {
        Map<String, Object> results = new HashMap<>();
        
        // 시스템 정보 수집
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        Map<String, Object> systemInfo = new HashMap<>();
		systemInfo.put("totalMemoryMB", totalMemory / 1024 / 1024);
		systemInfo.put("freeMemoryMB", freeMemory / 1024 / 1024);
		systemInfo.put("usedMemoryMB", usedMemory / 1024 / 1024);
		systemInfo.put("availableProcessors", runtime.availableProcessors());
		
		results.put("systemInfo", systemInfo);
        
        // 테스트 실행 (실제로는 MultipartFile로 변환해야 하지만, 시뮬레이션)
        AppLog.info("성능 테스트 시뮬레이션 실행");
        
        // 기존 방식 시뮬레이션
        long traditionalStart = System.currentTimeMillis();
        simulateTraditionalUpload(testFile);
        long traditionalDuration = System.currentTimeMillis() - traditionalStart;
        
        // 개선 방식 시뮬레이션  
        long multipartStart = System.currentTimeMillis();
        simulateMultipartUpload(testFile);
        long multipartDuration = System.currentTimeMillis() - multipartStart;
        
        // 결과 계산
        long fileSize = testFile.length();
        double traditionalThroughput = (fileSize / 1024.0 / 1024.0) / (traditionalDuration / 1000.0);
        double multipartThroughput = (fileSize / 1024.0 / 1024.0) / (multipartDuration / 1000.0);
        double improvementPercent = ((double) traditionalDuration - multipartDuration) / traditionalDuration * 100;
        
        results.put("testFile", testFile.getName());
        results.put("fileSizeMB", fileSize / 1024 / 1024);
        results.put("traditionalDuration", traditionalDuration);
        results.put("multipartDuration", multipartDuration);
        results.put("traditionalThroughput", String.format("%.2f", traditionalThroughput));
        results.put("multipartThroughput", String.format("%.2f", multipartThroughput));
        results.put("improvementPercent", String.format("%.1f", improvementPercent));
        results.put("speedupFactor", String.format("%.1fx", multipartThroughput / traditionalThroughput));
        
        // 성능 로깅
        performanceLogger.logUploadMethodComparison("TRADITIONAL_BATCH", fileSize, traditionalDuration, 
            "배치 테스트 - 기존 방식");
        performanceLogger.logUploadMethodComparison("MULTIPART_BATCH", fileSize, multipartDuration, 
            "배치 테스트 - 개선 방식");
        
        AppLog.info("성능 테스트 결과: 기존 " + traditionalDuration + "ms vs 개선 " + multipartDuration + "ms (" + String.format("%.1f", improvementPercent) + "% 개선)");
        
        return results;
    }
    
    /**
     * 기존 방식 업로드 시뮬레이션
     */
    private void simulateTraditionalUpload(File testFile) throws Exception {
        AppLog.debug("기존 방식 업로드 시뮬레이션");
        
        // 전체 파일을 메모리에 로드하는 시뮬레이션
        long fileSize = testFile.length();
        byte[] fileData = new byte[(int) fileSize];
        
        // 파일 읽기 시뮬레이션
        try (java.io.FileInputStream fis = new java.io.FileInputStream(testFile)) {
            int totalRead = 0;
            int bytesRead;
            while ((bytesRead = fis.read(fileData, totalRead, fileData.length - totalRead)) > 0) {
                totalRead += bytesRead;
            }
        }
        
        // 네트워크 전송 시뮬레이션 (실제로는 S3 업로드)
        Thread.sleep(100); // 네트워크 지연 시뮬레이션
        
        AppLog.debug("기존 방식 시뮬레이션 완료: {}MB 메모리 사용", fileSize / 1024 / 1024);
    }
    
    /**
     * 멀티파트 업로드 시뮬레이션
     */
    private void simulateMultipartUpload(File testFile) throws Exception {
        AppLog.debug("멀티파트 업로드 시뮬레이션");
        
        long fileSize = testFile.length();
        long partSize = 5 * 1024 * 1024; // 5MB 청크
        int partCount = (int) Math.ceil((double) fileSize / partSize);
        
        try (java.io.FileInputStream fis = new java.io.FileInputStream(testFile)) {
            for (int i = 0; i < partCount; i++) {
                byte[] partData = new byte[(int) Math.min(partSize, fileSize - (i * partSize))];
                fis.read(partData);
                
                // 각 파트의 병렬 업로드 시뮬레이션
                Thread.sleep(20); // 각 파트별 네트워크 지연
                
                if ((i + 1) % 10 == 0) {
                    AppLog.debug("멀티파트 진행: {} / {} 파트 완료" + " , " +  i + 1 + " , " + partCount);
                }
            }
        }
        
        AppLog.debug("멀티파트 시뮬레이션 완료: {} 파트, 최대 5MB 메모리 사용", partCount);
    }
    
   
    /**
     * 메모리 효율성 계산
     */
    private String calculateMemoryEfficiency(Map<String, Object> results) {
        long fileSizeMB = (Long) results.get("fileSizeMB");
        long maxChunkSizeMB = 5; // 멀티파트 청크 크기
        
        double efficiency = (1.0 - (double) maxChunkSizeMB / fileSizeMB) * 100;
        return String.format("%.1f", Math.max(0, efficiency));
    }
    
   
}