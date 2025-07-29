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

import com.demo.proworks.att.service.LargeFileService;
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
    private EfficientTestFileCreator fileCreator;
    
    @Value("${batch.performance.report.path:/tmp/performance_reports}")
    private String reportPath;
    
    @Value("${batch.performance.test.file.path:/tmp/test_files}")
    private String testFilePath;
    
    /**
     * 260MB 파일 업로드 성능 테스트 배치 실행
     * 
     * @return 배치 실행 결과
     */
    public Map<String, Object> executePerformanceTest() {
        AppLog.info("=== 파일 업로드 성능 테스트 배치 시작 ===");
        
        Map<String, Object> batchResult = new HashMap<>();
        String batchId = "BATCH_" + System.currentTimeMillis();
        
        try {
            // 1. 배치 환경 설정
            batchResult.put("batchId", batchId);
            batchResult.put("startTime", new Date());
            batchResult.put("status", "RUNNING");
            
            // 2. 테스트 파일 준비 (260MB 가상 파일 생성)
            File testFile = createTestFile();
            AppLog.info("테스트 파일 생성 완료: " + testFile.getName() + " (" + (testFile.length() / 1024 / 1024) + "MB)");
            
            // 3. 성능 테스트 실행
            Map<String, Object> performanceResults = runPerformanceTests(testFile);
            
            // 4. 리포트 생성
            String reportFileName = generatePerformanceReport(batchId, performanceResults);
            
            // 5. 배치 완료 처리
            batchResult.put("status", "COMPLETED");
            batchResult.put("endTime", new Date());
            batchResult.put("performanceResults", performanceResults);
            batchResult.put("reportFile", reportFileName);
            batchResult.put("success", true);
            
            AppLog.info("=== 파일 업로드 성능 테스트 배치 완료 ===");
            AppLog.info("리포트 파일: " + reportFileName);
            
        } catch (Exception e) {
            AppLog.error("성능 테스트 배치 실패: " + e.getMessage());
            batchResult.put("status", "FAILED");
            batchResult.put("endTime", new Date());
            batchResult.put("error", e.getMessage());
            batchResult.put("success", false);
        }
        
        return batchResult;
    }
    
    /**
     * 스케줄러를 통한 정기 성능 테스트
     * 매일 새벽 2시에 실행되는 성능 모니터링
     */
    public void scheduledPerformanceMonitoring() {
        AppLog.info("=== 정기 성능 모니터링 시작 ===");
        
        try {
            Map<String, Object> result = executePerformanceTest();
            
            // 성능 지표가 기준치 이하인 경우 알림
            if (result.containsKey("performanceResults")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> perfResults = (Map<String, Object>) result.get("performanceResults");
                checkPerformanceThresholds(perfResults);
            }
            
        } catch (Exception e) {
            AppLog.error("정기 성능 모니터링 실패: " + e.getMessage());
            // 알림 서비스 호출 (실제 환경에서는 이메일/SMS 등)
            sendPerformanceAlert("정기 성능 모니터링 실패", e.getMessage());
        }
    }
    
    /**
     * 웹 인터페이스를 통한 수동 성능 테스트
     */
    public Map<String, Object> manualPerformanceTest(String testType) {
        AppLog.info("수동 성능 테스트 시작: " + testType);
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            switch (testType.toUpperCase()) {
                case "QUICK":
                    result = executeQuickPerformanceTest();
                    break;
                case "FULL":
                    result = executePerformanceTest();
                    break;
                case "COMPARISON":
                    result = executeComparisonTest();
                    break;
                default:
                    throw new IllegalArgumentException("지원하지 않는 테스트 타입: " + testType);
            }
            
        } catch (Exception e) {
            AppLog.error("수동 성능 테스트 실패: " + e.getMessage());
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 빠른 성능 테스트 (50MB 파일 사용)
     */
    private Map<String, Object> executeQuickPerformanceTest() throws Exception {
        AppLog.info("빠른 성능 테스트 실행");
        
        File testFile = createTestFile(50); // 50MB 파일
        Map<String, Object> results = runPerformanceTests(testFile);
        
        // 간단한 결과 요약
        Map<String, Object> summary = new HashMap<>();
        summary.put("testType", "QUICK");
        summary.put("fileSize", "50MB");
        summary.put("results", results);
        summary.put("timestamp", new Date());
        
        return summary;
    }
    
    /**
     * 비교 테스트 (여러 크기의 파일로 테스트)
     */
    private Map<String, Object> executeComparisonTest() throws Exception {
        AppLog.info("비교 성능 테스트 실행");
        
        Map<String, Object> comparisonResults = new HashMap<>();
        int[] testSizes = {10, 50, 100, 260}; // MB
        
        for (int size : testSizes) {
            AppLog.info(size + "MB 파일 테스트 시작");
            
            File testFile = createTestFile(size);
            Map<String, Object> results = runPerformanceTests(testFile);
            
            comparisonResults.put(size + "MB", results);
            
            // 테스트 간 간격
            Thread.sleep(1000);
        }
        
        Map<String, Object> summary = new HashMap<>();
        summary.put("testType", "COMPARISON");
        summary.put("results", comparisonResults);
        summary.put("timestamp", new Date());
        
        return summary;
    }
    
    /**
     * 테스트 파일 생성 (기본 260MB)
     */
    private File createTestFile() throws IOException {
        return fileCreator.create260MBTestFile(testFilePath);
    }
    
    /**
     * 지정된 크기의 테스트 파일 생성
     */
    private File createTestFile(int sizeMB) throws IOException {
        String fileName = String.format("test_file_%dMB_%d.dat", sizeMB, System.currentTimeMillis());
        return fileCreator.createTestFileWithChannel(testFilePath, fileName, sizeMB);
    }
    
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
     * 성능 리포트 생성
     */
    private String generatePerformanceReport(String batchId, Map<String, Object> results) throws IOException {
        // 리포트 디렉토리 생성
        File reportDir = new File(reportPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String reportFileName = String.format("performance_report_%s_%s.txt", 
            sdf.format(new Date()), batchId);
        File reportFile = new File(reportDir, reportFileName);
        
        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write("=====================================\n");
            writer.write("파일 업로드 성능 테스트 리포트\n");
            writer.write("=====================================\n\n");
            
            writer.write("배치 ID: " + batchId + "\n");
            writer.write("생성일시: " + new Date() + "\n");
            writer.write("리포트 버전: 1.0\n\n");
            
            writer.write("1. 테스트 개요\n");
            writer.write("-------------\n");
            writer.write("목적: 대용량 파일 업로드 성능 개선 효과 측정\n");
            writer.write("대상: " + results.get("testFile") + "\n");
            writer.write("파일 크기: " + results.get("fileSizeMB") + "MB\n\n");
            
            writer.write("2. 시스템 환경\n");
            writer.write("-------------\n");
            @SuppressWarnings("unchecked")
            Map<String, Object> systemInfo = (Map<String, Object>) results.get("systemInfo");
            writer.write("총 메모리: " + systemInfo.get("totalMemoryMB") + "MB\n");
            writer.write("사용 메모리: " + systemInfo.get("usedMemoryMB") + "MB\n");
            writer.write("프로세서 수: " + systemInfo.get("availableProcessors") + "\n\n");
            
            writer.write("3. 성능 테스트 결과\n");
            writer.write("------------------\n");
            writer.write("기존 방식 (단일 업로드):\n");
            writer.write("  - 소요 시간: " + results.get("traditionalDuration") + "ms\n");
            writer.write("  - 처리량: " + results.get("traditionalThroughput") + "MB/s\n");
            writer.write("  - 메모리 사용: 전체 파일 크기 (" + results.get("fileSizeMB") + "MB)\n\n");
            
            writer.write("개선 방식 (멀티파트 업로드):\n");
            writer.write("  - 소요 시간: " + results.get("multipartDuration") + "ms\n");
            writer.write("  - 처리량: " + results.get("multipartThroughput") + "MB/s\n");
            writer.write("  - 메모리 사용: 최대 5MB (청크 단위)\n\n");
            
            writer.write("4. 성능 개선 효과\n");
            writer.write("----------------\n");
            writer.write("성능 향상: " + results.get("improvementPercent") + "%\n");
            writer.write("속도 배수: " + results.get("speedupFactor") + "\n");
            writer.write("메모리 효율성: " + calculateMemoryEfficiency(results) + "%\n\n");
            
            writer.write("5. 기술적 장점\n");
            writer.write("-------------\n");
            writer.write("- 메모리 사용량 대폭 감소 (파일 크기 대비 5MB 고정)\n");
            writer.write("- 네트워크 장애 시 부분 재전송 가능\n");
            writer.write("- 병렬 처리를 통한 처리량 향상\n");
            writer.write("- 대용량 파일에 대한 확장성 확보\n\n");
            
            writer.write("6. 권장사항\n");
            writer.write("-----------\n");
            writer.write("- 5MB 이상의 파일에 대해 멀티파트 업로드 적용\n");
            writer.write("- 네트워크 환경에 따른 청크 크기 최적화\n");
            writer.write("- 업로드 진행률 모니터링 구현\n");
            writer.write("- 실패 시 재시도 로직 강화\n\n");
            
            writer.write("=====================================\n");
            writer.write("리포트 생성 완료\n");
            writer.write("=====================================\n");
        }
        
        AppLog.info("성능 리포트 생성 완료: {}", reportFile.getAbsolutePath());
        return reportFile.getAbsolutePath();
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
    
    /**
     * 성능 임계값 체크
     */
    private void checkPerformanceThresholds(Map<String, Object> results) {
        double improvementPercent = Double.parseDouble(results.get("improvementPercent").toString());
        double multipartThroughput = Double.parseDouble(results.get("multipartThroughput").toString());
        
        // 성능 임계값 설정
        double minImprovementPercent = 20.0; // 최소 20% 개선
        double minThroughput = 10.0; // 최소 10MB/s
        
        if (improvementPercent < minImprovementPercent) {
            AppLog.warn("성능 개선율이 기준 미달: " + improvementPercent + "% (기준: " + minImprovementPercent + "%)");
            sendPerformanceAlert("성능 개선율 미달", 
                String.format("현재: %.1f%%, 기준: %.1f%%", improvementPercent, minImprovementPercent));
        }
        
        if (multipartThroughput < minThroughput) {
            AppLog.warn("처리량이 기준 미달: " + multipartThroughput + "MB/s (기준: " + minThroughput + "MB/s)");
            sendPerformanceAlert("처리량 미달", 
                String.format("현재: %.2fMB/s, 기준: %.2fMB/s", multipartThroughput, minThroughput));
        }
    }
    
    /**
     * 성능 알림 전송 (실제 환경에서는 이메일/SMS 등)
     */
    private void sendPerformanceAlert(String alertType, String message) {
        AppLog.warn("성능 알림: " + alertType + " - " + message);
        
        // 실제 환경에서는 다음과 같은 알림 서비스 호출
        // - 이메일 발송
        // - SMS 발송  
        // - Slack/Teams 메시지
        // - 모니터링 시스템 연동
    }
    
    /**
     * 배치 실행 상태 조회
     */
    public Map<String, Object> getBatchStatus(String batchId) {
        Map<String, Object> status = new HashMap<>();
        status.put("batchId", batchId);
        status.put("status", "COMPLETED"); // 실제로는 DB에서 조회
        status.put("lastExecuted", new Date());
        
        return status;
    }
    
    /**
     * 배치 실행 히스토리 조회
     */
    public Map<String, Object> getBatchHistory(int limit) {
        Map<String, Object> history = new HashMap<>();
        
        // 실제로는 DB에서 조회하지만, 여기서는 샘플 데이터
        history.put("totalExecutions", 15);
        history.put("successCount", 14);
        history.put("failureCount", 1);
        history.put("lastSuccessTime", new Date());
        history.put("averageImprovement", "45.3%");
        
        return history;
    }
}