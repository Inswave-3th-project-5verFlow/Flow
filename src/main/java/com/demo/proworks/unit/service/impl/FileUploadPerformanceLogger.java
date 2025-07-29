package com.demo.proworks.unit.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.inswave.elfw.log.AppLog;

/**
 * 강화된 파일 업로드 성능 로거
 * - 상세한 성능 메트릭 수집
 * - 비교 분석을 위한 데이터 저장
 * - CSV 형태의 로그 파일 생성
 * - 실시간 성능 모니터링 지원
 */
@Component
public class FileUploadPerformanceLogger {
    
    @Value("${performance.log.path:/tmp/performance_logs}")
    private String logPath;
    
    @Value("${performance.log.enabled:true}")
    private boolean logEnabled;
    
    // 세션별 성능 데이터 저장소
    private final Map<String, PerformanceSession> activeSessions = new ConcurrentHashMap<>();
    
    // 성능 통계 저장소
    private final Map<String, PerformanceStats> methodStats = new ConcurrentHashMap<>();
    
    /**
     * 성능 세션 클래스
     */
    public static class PerformanceSession {
        private String sessionId;
        private String fileName;
        private long fileSize;
        private String method;
        private long startTime;
        private long endTime;
        private boolean completed;
        private String errorMessage;
        private Map<String, Object> additionalMetrics;
        
        public PerformanceSession(String sessionId, String fileName, long fileSize, String method) {
            this.sessionId = sessionId;
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.method = method;
            this.startTime = System.currentTimeMillis();
            this.completed = false;
            this.additionalMetrics = new HashMap<>();
        }
        
        // Getters and setters
        public String getSessionId() { return sessionId; }
        public String getFileName() { return fileName; }
        public long getFileSize() { return fileSize; }
        public String getMethod() { return method; }
        public long getStartTime() { return startTime; }
        public long getEndTime() { return endTime; }
        public void setEndTime(long endTime) { this.endTime = endTime; }
        public boolean isCompleted() { return completed; }
        public void setCompleted(boolean completed) { this.completed = completed; }
        public String getErrorMessage() { return errorMessage; }
        public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
        public Map<String, Object> getAdditionalMetrics() { return additionalMetrics; }
        
        public long getDuration() {
            return endTime - startTime;
        }
        
        public double getThroughputMBps() {
            if (getDuration() <= 0) return 0.0;
            return (fileSize / 1024.0 / 1024.0) / (getDuration() / 1000.0);
        }
    }
    
    /**
     * 방법별 성능 통계 클래스
     */
    public static class PerformanceStats {
        private String method;
        private int totalUploads;
        private long totalBytes;
        private long totalDuration;
        private int successCount;
        private int failureCount;
        private double bestThroughput;
        private double worstThroughput;
        
        public PerformanceStats(String method) {
            this.method = method;
            this.bestThroughput = 0.0;
            this.worstThroughput = Double.MAX_VALUE;
        }
        
        public void addUpload(PerformanceSession session) {
            totalUploads++;
            totalBytes += session.getFileSize();
            totalDuration += session.getDuration();
            
            if (session.isCompleted() && session.getErrorMessage() == null) {
                successCount++;
                double throughput = session.getThroughputMBps();
                bestThroughput = Math.max(bestThroughput, throughput);
                worstThroughput = Math.min(worstThroughput, throughput);
            } else {
                failureCount++;
            }
        }
        
        public double getAverageThroughput() {
            if (totalDuration <= 0) return 0.0;
            return (totalBytes / 1024.0 / 1024.0) / (totalDuration / 1000.0);
        }
        
        public double getSuccessRate() {
            if (totalUploads == 0) return 0.0;
            return (double) successCount / totalUploads * 100.0;
        }
        
        // Getters
        public String getMethod() { return method; }
        public int getTotalUploads() { return totalUploads; }
        public long getTotalBytes() { return totalBytes; }
        public long getTotalDuration() { return totalDuration; }
        public int getSuccessCount() { return successCount; }
        public int getFailureCount() { return failureCount; }
        public double getBestThroughput() { return bestThroughput == 0.0 ? 0.0 : bestThroughput; }
        public double getWorstThroughput() { return worstThroughput == Double.MAX_VALUE ? 0.0 : worstThroughput; }
    }
    
    /**
     * 업로드 시작 로깅 (세션 기반)
     */
    public String logUploadStart(String fileName, long fileSize, String method) {
        if (!logEnabled) return null;
        
        String sessionId = generateSessionId();
        PerformanceSession session = new PerformanceSession(sessionId, fileName, fileSize, method);
        activeSessions.put(sessionId, session);
        
        long fileSizeMB = fileSize / 1024 / 1024;
        long fileSizeKB = fileSize / 1024;
        
        if (fileSizeMB > 0) {
            AppLog.info("UPLOAD_START: sessionId=" + sessionId + " , " + "fileName=" + fileName + " , " + "fileSize=" + fileSizeMB + "MB" + " , " + "method=" + method);
        } else {
            AppLog.info("UPLOAD_START: sessionId=" + sessionId + " , " + "fileName=" + fileName + " , " + "fileSize=" + fileSizeKB + "KB" + " , " + "method=" + method);
        }
        
        AppLog.debug("파일 업로드 시작: " + fileName + " (" + fileSize + "bytes)" + " , " + "세션ID: " + sessionId);
        
        return sessionId;
    }
    
    /**
     * 업로드 완료 로깅
     */
    public void logUploadComplete(String sessionId, String fileName, long fileSize, long durationMs, String method) {
        if (!logEnabled || sessionId == null) return;
        
        PerformanceSession session = activeSessions.get(sessionId);
        if (session != null) {
            session.setEndTime(System.currentTimeMillis());
            session.setCompleted(true);
            
            // 통계 업데이트
            updateMethodStats(session);
            
            // 로그 기록
            logUploadComplete(fileName, fileSize, durationMs, method);
            
            // CSV 로그 기록
            writePerformanceLogToCsv(session);
            
            // 세션 정리
            activeSessions.remove(sessionId);
        } else {
            // 세션이 없는 경우 기본 로깅
            logUploadComplete(fileName, fileSize, durationMs, method);
        }
    }
    
    /**
     * 기존 업로드 완료 로깅 (호환성)
     */
    public void logUploadComplete(String fileName, long fileSize, long durationMs, String method) {
        if (!logEnabled) return;
        
        long fileSizeMB = fileSize / 1024 / 1024;
        double throughputMBps = 0;
        if (durationMs > 0) {
            throughputMBps = (fileSize / 1024.0 / 1024.0) / (durationMs / 1000.0);
        }
        
        AppLog.info("UPLOAD_COMPLETE: fileName=" + fileName + " , " + "fileSize=" + fileSizeMB + "MB" + " , " + "duration=" + durationMs + "ms" + " , " + "throughput=" + String.format("%.2f", throughputMBps) + "MB/s" + " , " + "method=" + method);
        AppLog.info("파일 업로드 완료: " + fileName + " - " + fileSizeMB + "MB" + " , " + String.format("%.1f", durationMs / 1000.0) + "초" + " , " + String.format("%.2f", throughputMBps) + "MB/s");
    }
    
    /**
     * 업로드 실패 로깅
     */
    public void logUploadError(String sessionId, String fileName, long fileSize, long durationMs, String method, String errorMessage) {
        if (!logEnabled) return;
        
        PerformanceSession session = activeSessions.get(sessionId);
        if (session != null) {
            session.setEndTime(System.currentTimeMillis());
            session.setCompleted(false);
            session.setErrorMessage(errorMessage);
            
            // 통계 업데이트
            updateMethodStats(session);
            
            // CSV 로그 기록
            writePerformanceLogToCsv(session);
            
            // 세션 정리
            activeSessions.remove(sessionId);
        }
        
        // 기존 로깅
        logUploadError(fileName, fileSize, durationMs, method, errorMessage);
    }
    
    /**
     * 기존 업로드 실패 로깅 (호환성)
     */
    public void logUploadError(String fileName, long fileSize, long durationMs, String method, String errorMessage) {
        if (!logEnabled) return;
        
        long fileSizeMB = fileSize / 1024 / 1024;
        AppLog.error("UPLOAD_ERROR: fileName=" + fileName + " , " + "fileSize=" + fileSizeMB + "MB" + " , " + "duration=" + durationMs + "ms" + " , " + "method=" + method + " , " + "error=" + errorMessage);
        AppLog.error("파일 업로드 실패: " + fileName + " - " + errorMessage);
    }
    
    /**
     * 메모리 사용량 로깅
     */
    public void logMemoryUsage(String operation) {
        if (!logEnabled) return;
        
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();
        
        AppLog.info("MEMORY_USAGE: operation=" + operation + " , " + "used=" + (usedMemory / 1024 / 1024) + "MB" + " , " + "total=" + (totalMemory / 1024 / 1024) + "MB" + " , " + "max=" + (maxMemory / 1024 / 1024) + "MB" + " , " + "free=" + (freeMemory / 1024 / 1024) + "MB");
    }
    
    /**
     * 다중 파일 업로드 세션 시작 로깅
     */
    public String logMultiFileUploadStart(int fileCount, long totalSize) {
        if (!logEnabled) return null;
        
        String sessionId = generateSessionId();
        long totalSizeMB = totalSize / 1024 / 1024;
        
        AppLog.info("MULTI_UPLOAD_START: sessionId=" + sessionId + " , " + "fileCount=" + fileCount + " , " + "totalSize=" + totalSizeMB + "MB");
        AppLog.info("다중 파일 업로드 시작: " + fileCount + " 개 파일" + " , " + "총 " + totalSizeMB + "MB" + " , " + "세션ID: " + sessionId);
        
        return sessionId;
    }
    
    /**
     * 다중 파일 업로드 세션 완료 로깅
     */
    public void logMultiFileUploadComplete(String sessionId, int fileCount, long totalSize, long totalDuration) {
        if (!logEnabled) return;
        
        long totalSizeMB = totalSize / 1024 / 1024;
        double avgThroughputMBps = 0;
        if (totalDuration > 0) {
            avgThroughputMBps = (totalSize / 1024.0 / 1024.0) / (totalDuration / 1000.0);
        }
        
        AppLog.info("MULTI_UPLOAD_COMPLETE: sessionId=" + sessionId + " , " + "fileCount=" + fileCount + " , " + "totalSize=" + totalSizeMB + "MB" + " , " + "totalDuration=" + totalDuration + "ms" + " , " + "avgThroughput=" + String.format("%.2f", avgThroughputMBps) + "MB/s");
        AppLog.info("다중 파일 업로드 완료: " + fileCount + " 개 파일" + " , " + "총 " + totalSizeMB + "MB" + " , " + String.format("%.1f", totalDuration / 1000.0) + "초" + " , " + "평균 " + String.format("%.2f", avgThroughputMBps) + "MB/s");
    }
    
    /**
     * 업로드 방식 비교 로깅 (발표용)
     */
    public void logUploadMethodComparison(String method, long fileSize, long duration, String notes) {
        if (!logEnabled) return;
        
        long fileSizeMB = fileSize / 1024 / 1024;
        double throughputMBps = 0;
        if (duration > 0) {
            throughputMBps = (fileSize / 1024.0 / 1024.0) / (duration / 1000.0);
        }
        
        AppLog.info("UPLOAD_METHOD_COMPARISON: method=" + method + " , " + "fileSize=" + fileSizeMB + "MB" + " , " + "duration=" + duration + "ms" + " , " + "throughput=" + String.format("%.2f", throughputMBps) + "MB/s" + " , " + "notes=" + notes);
    }
    
    /**
     * 시스템 리소스 상태 로깅
     */
    public void logSystemResources(String operation) {
        if (!logEnabled) return;
        
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();
        
        // 메모리 사용률 계산
        double memoryUsagePercent = ((double) usedMemory / maxMemory) * 100;
        
        AppLog.info("SYSTEM_RESOURCES: operation=" + operation + " , " + "memoryUsage=" + String.format("%.1f", memoryUsagePercent) + "%" + " , " + "used=" + (usedMemory / 1024 / 1024) + "MB" + " , " + "max=" + (maxMemory / 1024 / 1024) + "MB" + " , " + "availableProcessors=" + Runtime.getRuntime().availableProcessors());
    }
    
    /**
     * 성능 비교 리포트 생성
     */
    public String generatePerformanceReport() {
        if (!logEnabled) return null;
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String reportFileName = String.format("performance_comparison_%s.txt", sdf.format(new Date()));
            
            File logDir = new File(logPath);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            
            File reportFile = new File(logDir, reportFileName);
            
            try (FileWriter writer = new FileWriter(reportFile)) {
                writer.write("=====================================\n");
                writer.write("파일 업로드 성능 비교 리포트\n");
                writer.write("=====================================\n\n");
                writer.write("생성일시: " + new Date() + "\n\n");
                
                for (Map.Entry<String, PerformanceStats> entry : methodStats.entrySet()) {
                    PerformanceStats stats = entry.getValue();
                    
                    writer.write("방법: " + stats.getMethod() + "\n");
                    writer.write("총 업로드 수: " + stats.getTotalUploads() + "\n");
                    writer.write("성공률: " + String.format("%.1f%%", stats.getSuccessRate()) + "\n");
                    writer.write("평균 처리량: " + String.format("%.2f MB/s", stats.getAverageThroughput()) + "\n");
                    writer.write("최고 처리량: " + String.format("%.2f MB/s", stats.getBestThroughput()) + "\n");
                    writer.write("최저 처리량: " + String.format("%.2f MB/s", stats.getWorstThroughput()) + "\n");
                    writer.write("총 데이터량: " + (stats.getTotalBytes() / 1024 / 1024) + " MB\n");
                    writer.write("총 소요시간: " + (stats.getTotalDuration() / 1000) + " 초\n");
                    writer.write("-------------------------------------\n\n");
                }
            }
            
            AppLog.info("성능 비교 리포트 생성: " + reportFile.getAbsolutePath());
            return reportFile.getAbsolutePath();
            
        } catch (IOException e) {
            AppLog.error("성능 리포트 생성 실패: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 방법별 성능 통계 조회
     */
    public Map<String, PerformanceStats> getMethodStats() {
        return new HashMap<>(methodStats);
    }
    
    /**
     * 활성 세션 조회
     */
    public Map<String, PerformanceSession> getActiveSessions() {
        return new HashMap<>(activeSessions);
    }
    
    // ===== Private Helper Methods =====
    
    /**
     * 세션 ID 생성
     */
    private String generateSessionId() {
        return "PERF_" + System.currentTimeMillis() + "_" + Thread.currentThread().getId();
    }
    
    /**
     * 방법별 통계 업데이트
     */
    private void updateMethodStats(PerformanceSession session) {
        String method = session.getMethod();
        PerformanceStats stats = methodStats.computeIfAbsent(method, PerformanceStats::new);
        stats.addUpload(session);
    }
    
    /**
     * CSV 형태로 성능 로그 기록
     */
    private void writePerformanceLogToCsv(PerformanceSession session) {
        try {
            File logDir = new File(logPath);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String csvFileName = String.format("performance_log_%s.csv", sdf.format(new Date()));
            File csvFile = new File(logDir, csvFileName);
            
            boolean isNewFile = !csvFile.exists();
            
            try (FileWriter writer = new FileWriter(csvFile, true)) {
                // 헤더 작성 (새 파일인 경우)
                if (isNewFile) {
                    writer.write("SessionID,Timestamp,FileName,FileSizeMB,Method,DurationMs,ThroughputMBps,Success,ErrorMessage\n");
                }
                
                // 데이터 작성
                writer.write(String.format("%s,%s,%s,%d,%s,%d,%.2f,%s,%s\n",
                    session.getSessionId(),
                    new Date(session.getStartTime()),
                    session.getFileName(),
                    session.getFileSize() / 1024 / 1024,
                    session.getMethod(),
                    session.getDuration(),
                    session.getThroughputMBps(),
                    session.isCompleted() && session.getErrorMessage() == null ? "Y" : "N",
                    session.getErrorMessage() != null ? session.getErrorMessage() : ""
                ));
            }
            
        } catch (IOException e) {
            AppLog.error("CSV 성능 로그 작성 실패: " + e.getMessage());
        }
    }
}