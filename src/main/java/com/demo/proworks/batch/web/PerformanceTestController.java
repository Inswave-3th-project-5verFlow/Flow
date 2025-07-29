//package com.demo.proworks.batch.web;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.demo.proworks.batch.performance.PerformanceBatchJob;
//import com.demo.proworks.att.vo.DemoResultVo;
//import com.demo.proworks.att.vo.SimpleResponseVo;
//import com.inswave.elfw.annotation.ElDescription;
//import com.inswave.elfw.annotation.ElService;
//import com.inswave.elfw.log.AppLog;
//import com.inswave.elfw.resolver.ElCommandMap;
//
///**
// * 성능 테스트 웹 인터페이스 컨트롤러
// * 대용량 파일 업로드 성능 테스트를 웹에서 실행하고 결과를 확인할 수 있는 인터페이스 제공
// */
//@Controller
//public class PerformanceTestController {
//    
//    @Resource
//    private PerformanceBatchJob performanceBatchJob;
//    
//    /**
//     * 260MB 파일 업로드 성능 데모 실행
//     * 면접/발표용 데모 기능
//     */
//    @ElService(key="RunPerformanceDemo")
//    @RequestMapping(value="RunPerformanceDemo")    
//    @ElDescription(sub="성능 개선 데모 실행", desc="260MB 파일을 이용한 성능 개선 데모를 실행한다.")
//    @ResponseBody
//    public ElCommandMap runPerformanceDemo() throws Exception {
//        
//        AppLog.info("=== 성능 개선 데모 실행 요청 ===");
//        
//        ElCommandMap response = new ElCommandMap();
//        
//        try {
//            // 배치 성능 테스트 실행
//            ElCommandMap batchResult = performanceBatchJob.executePerformanceTest();
//            
//            if (batchResult.getBoolean("success")) {
//                ElCommandMap perfResults = batchResult.getMap("performanceResults");
//                
//                response.put("success", true);
//                response.put("demoTitle", "대용량 파일 업로드 성능 개선 데모");
//                response.put("demoDescription", "260MB 파일을 이용한 기존 방식 vs 개선 방식 성능 비교");
//                
//                // 데모 결과 포맷팅
//                response.put("testResults", formatDemoResults(perfResults));
//                response.put("reportFile", batchResult.getString("reportFile"));
//                response.put("batchId", batchResult.getString("batchId"));
//                
//                AppLog.info("성능 개선 데모 실행 완료");
//                
//            } else {
//                response.put("success", false);
//                response.put("message", "데모 실행 실패: " + batchResult.getString("error"));
//            }
//            
//        } catch (Exception e) {
//            AppLog.error("성능 개선 데모 실행 실패: {}", e.getMessage());
//            response.put("success", false);
//            response.put("message", "데모 실행 중 오류 발생: " + e.getMessage());
//        }
//        
//        return response;
//    }
//    
//    /**
//     * 수동 성능 테스트 실행
//     */
//    @ElService(key="RunManualPerformanceTest")
//    @RequestMapping(value="RunManualPerformanceTest")    
//    @ElDescription(sub="수동 성능 테스트", desc="지정된 타입으로 성능 테스트를 수동 실행한다.")
//    @ResponseBody
//    public ElCommandMap runManualPerformanceTest(
//            @RequestParam(value = "testType", defaultValue = "FULL") String testType) throws Exception {
//        
//        AppLog.info("수동 성능 테스트 실행: " + testType);
//        
//        ElCommandMap response = new ElCommandMap();
//        
//        try {
//            ElCommandMap testResult = performanceBatchJob.manualPerformanceTest(testType);
//            
//            response.put("success", testResult.getBoolean("success"));
//            response.put("testType", testType);
//            response.put("results", testResult);
//            
//            if (response.getBoolean("success")) {
//                AppLog.info("수동 성능 테스트 완료: " + testType);
//            } else {
//                AppLog.error("수동 성능 테스트 실패: " + testResult.getString("error"));
//            }
//            
//        } catch (Exception e) {
//            AppLog.error("수동 성능 테스트 실행 실패: " + e.getMessage());
//            response.put("success", false);
//            response.put("message", "테스트 실행 중 오류 발생: " + e.getMessage());
//        }
//        
//        return response;
//    }
//    
//    /**
//     * 배치 실행 상태 조회
//     */
//    @ElService(key="GetBatchStatus")
//    @RequestMapping(value="GetBatchStatus")    
//    @ElDescription(sub="배치 상태 조회", desc="성능 테스트 배치의 실행 상태를 조회한다.")
//    @ResponseBody
//    public ElCommandMap getBatchStatus(
//            @RequestParam("batchId") String batchId) throws Exception {
//        
//        ElCommandMap response = new ElCommandMap();
//        
//        try {
//            ElCommandMap status = performanceBatchJob.getBatchStatus(batchId);
//            
//            response.put("success", true);
//            response.put("batchStatus", status);
//            
//        } catch (Exception e) {
//            AppLog.error("배치 상태 조회 실패: " + e.getMessage());
//            
//            response.put("success", false);
//            response.put("message", "상태 조회 실패: " + e.getMessage());
//        }
//        
//        return response;
//    }
//    
//    /**
//     * 배치 실행 히스토리 조회
//     */
//    @ElService(key="GetBatchHistory")
//    @RequestMapping(value="GetBatchHistory")    
//    @ElDescription(sub="배치 히스토리 조회", desc="성능 테스트 배치의 실행 히스토리를 조회한다.")
//    @ResponseBody
//    public ElCommandMap getBatchHistory(
//            @RequestParam(value = "limit", defaultValue = "10") int limit) throws Exception {
//        
//        ElCommandMap response = new ElCommandMap();
//        
//        try {
//            ElCommandMap history = performanceBatchJob.getBatchHistory(limit);
//            
//            response.put("success", true);
//            response.put("batchHistory", history);
//            
//        } catch (Exception e) {
//            AppLog.error("배치 히스토리 조회 실패: " + e.getMessage());
//            
//            response.put("success", false);
//            response.put("message", "히스토리 조회 실패: " + e.getMessage());
//        }
//        
//        return response;
//    }
//    
//    /**
//     * 실시간 성능 모니터링 데이터 조회
//     */
//    @ElService(key="GetPerformanceMetrics")
//    @RequestMapping(value="GetPerformanceMetrics")    
//    @ElDescription(sub="성능 메트릭 조회", desc="실시간 성능 모니터링 데이터를 조회한다.")
//    @ResponseBody
//    public ElCommandMap getPerformanceMetrics() throws Exception {
//        
//        ElCommandMap response = new ElCommandMap();
//        
//        try {
//            ElCommandMap metrics = collectCurrentMetrics();
//            
//            response.put("success", true);
//            response.put("metrics", metrics);
//            response.put("timestamp", System.currentTimeMillis());
//            
//        } catch (Exception e) {
//            AppLog.error("성능 메트릭 조회 실패: " + e.getMessage());
//            
//            response.put("success", false);
//            response.put("message", "메트릭 조회 실패: " + e.getMessage());
//        }
//        
//        return response;
//    }
//    
//    /**
//     * 성능 개선 요약 리포트 조회
//     */
//    @ElService(key="GetPerformanceSummary")
//    @RequestMapping(value="GetPerformanceSummary")    
//    @ElDescription(sub="성능 개선 요약", desc="전체 성능 개선 효과에 대한 요약 리포트를 조회한다.")
//    @ResponseBody
//    public ElCommandMap getPerformanceSummary() throws Exception {
//        
//        ElCommandMap response = new ElCommandMap();
//        
//        try {
//            ElCommandMap summary = generatePerformanceSummary();
//            
//            response.put("success", true);
//            response.put("summary", summary);
//            
//        } catch (Exception e) {
//            AppLog.error("성능 요약 조회 실패: " + e.getMessage());
//            
//            response.put("success", false);
//            response.put("message", "요약 조회 실패: " + e.getMessage());
//        }
//        
//        return response;
//    }
//    
//    // ===== Private Helper Methods =====
//    
//    /**
//     * 데모 결과 포맷팅
//     */
//    private ElCommandMap formatDemoResults(ElCommandMap perfResults) {
//        ElCommandMap formatted = new ElCommandMap();
//        
//        // 기본 정보
//        formatted.put("fileName", perfResults.getString("testFile"));
//        formatted.put("fileSizeMB", perfResults.getLong("fileSizeMB"));
//        
//        // 기존 방식 결과
//        ElCommandMap traditional = new ElCommandMap();
//        traditional.put("method", "기존 단일 업로드 방식");
//        traditional.put("duration", perfResults.getLong("traditionalDuration"));
//        traditional.put("durationSeconds", 
//            String.format("%.1f초", perfResults.getLong("traditionalDuration") / 1000.0));
//        traditional.put("throughput", perfResults.getString("traditionalThroughput") + "MB/s");
//        traditional.put("memoryUsage", perfResults.getLong("fileSizeMB") + "MB (전체 파일)");
//        traditional.put("description", "전체 파일을 메모리에 로드 후 한 번에 업로드");
//        
//        // 개선 방식 결과
//        ElCommandMap improved = new ElCommandMap();
//        improved.put("method", "개선된 멀티파트 업로드 방식");
//        improved.put("duration", perfResults.getLong("multipartDuration"));
//        improved.put("durationSeconds", 
//            String.format("%.1f초", perfResults.getLong("multipartDuration") / 1000.0));
//        improved.put("throughput", perfResults.getString("multipartThroughput") + "MB/s");
//        improved.put("memoryUsage", "5MB (고정 청크 크기)");
//        improved.put("description", "5MB 청크로 분할하여 병렬 업로드");
//        
//        formatted.put("traditionalMethod", traditional);
//        formatted.put("improvedMethod", improved);
//        
//        // 개선 효과
//        ElCommandMap improvement = new ElCommandMap();
//        improvement.put("performanceGain", perfResults.getString("improvementPercent") + "%");
//        improvement.put("speedupFactor", perfResults.getString("speedupFactor"));
//        improvement.put("memoryEfficiency", calculateMemoryEfficiencyPercent(perfResults) + "%");
//        improvement.put("scalability", "대용량 파일에 대한 선형적 확장성 확보");
//        
//        formatted.put("improvementSummary", improvement);
//        
//        // 기술적 장점
//        formatted.put("technicalAdvantages", java.util.Arrays.asList(
//            "메모리 사용량 95% 이상 감소",
//            "네트워크 장애 시 부분 재전송 가능",
//            "병렬 처리를 통한 처리량 향상",
//            "대용량 파일 처리 안정성 확보",
//            "시스템 리소스 효율적 활용"
//        ));
//        
//        return formatted;
//    }
//    
//    /**
//     * 현재 시스템 메트릭 수집
//     */
//    private ElCommandMap collectCurrentMetrics() {
//        ElCommandMap metrics = new ElCommandMap();
//        
//        Runtime runtime = Runtime.getRuntime();
//        long totalMemory = runtime.totalMemory();
//        long freeMemory = runtime.freeMemory();
//        long usedMemory = totalMemory - freeMemory;
//        long maxMemory = runtime.maxMemory();
//        
//        // 메모리 메트릭
//        ElCommandMap memory = new ElCommandMap();
//        memory.put("totalMB", totalMemory / 1024 / 1024);
//        memory.put("usedMB", usedMemory / 1024 / 1024);
//        memory.put("freeMB", freeMemory / 1024 / 1024);
//        memory.put("maxMB", maxMemory / 1024 / 1024);
//        memory.put("usagePercent", String.format("%.1f", (double) usedMemory / maxMemory * 100));
//        
//        // 시스템 메트릭
//        ElCommandMap system = new ElCommandMap();
//        system.put("availableProcessors", runtime.availableProcessors());
//        system.put("javaVersion", System.getProperty("java.version"));
//        system.put("osName", System.getProperty("os.name"));
//        system.put("osArch", System.getProperty("os.arch"));
//        
//        metrics.put("memory", memory);
//        metrics.put("system", system);
//        metrics.put("timestamp", new java.util.Date());
//        
//        return metrics;
//    }
//    
//    /**
//     * 성능 개선 요약 생성 (실제 측정 결과 기반)
//     */
//    private ElCommandMap generatePerformanceSummary() {
//        ElCommandMap summary = new ElCommandMap();
//        
//        try {
//            // 최근 성능 테스트 결과들 조회 (최근 10건)
//            ElCommandMap recentResults = performanceBatchJob.getRecentPerformanceResults(10);
//            
//            if (recentResults != null && recentResults.getList("results") != null) {
//                java.util.List<ElCommandMap> results = recentResults.getList("results");
//                
//                // 실제 측정 데이터 분석
//                ElCommandMap calculatedMetrics = calculateRealMetrics(results);
//                
//                summary.put("title", "대용량 파일 업로드 성능 개선 효과 (실측 기반)");
//                summary.put("implementationDate", "2025-07-29");
//                summary.put("measurementPeriod", recentResults.getString("measurementPeriod"));
//                summary.put("totalTestCount", results.size());
//                summary.put("lastUpdated", new java.util.Date());
//                
//                // 실제 측정된 성능 개선 지표
//                summary.put("performanceImprovements", calculatedMetrics.getMap("improvements"));
//                summary.put("statisticalData", calculatedMetrics.getMap("statistics"));
//                summary.put("trendAnalysis", calculatedMetrics.getMap("trends"));
//                
//                // 비즈니스 임팩트 (측정 결과 기반)
//                ElCommandMap businessImpact = generateBusinessImpact(calculatedMetrics);
//                summary.put("businessImpact", businessImpact);
//                
//                // 기술 스택 (실제 설정값)
//                ElCommandMap techStack = getCurrentTechStack();
//                summary.put("technicalStack", techStack);
//                
//            } else {
//                // 측정 데이터가 없는 경우 기본값 반환
//                summary = generateDefaultSummary();
//            }
//            
//        } catch (Exception e) {
//            AppLog.error("실제 성능 데이터 조회 실패, 기본 요약 반환: " + e.getMessage());
//            summary = generateDefaultSummary();
//        }
//        
//        return summary;
//    }
//    
//    /**
//     * 실제 측정 결과 기반 메트릭 계산
//     */
//    private ElCommandMap calculateRealMetrics(java.util.List<ElCommandMap> results) {
//        ElCommandMap metrics = new ElCommandMap();
//        
//        // 성능 향상률 계산
//        double totalImprovementPercent = 0.0;
//        double totalSpeedupFactor = 0.0;
//        long totalTraditionalTime = 0;
//        long totalMultipartTime = 0;
//        long totalFileSize = 0;
//        
//        int validResults = 0;
//        
//        for (ElCommandMap result : results) {
//            if (result.getBoolean("success")) {
//                totalImprovementPercent += result.getDouble("improvementPercent");
//                totalSpeedupFactor += result.getDouble("speedupFactor");
//                totalTraditionalTime += result.getLong("traditionalDuration");
//                totalMultipartTime += result.getLong("multipartDuration");
//                totalFileSize += result.getLong("fileSizeMB");
//                validResults++;
//            }
//        }
//        
//        if (validResults > 0) {
//            // 평균값 계산
//            double avgImprovementPercent = totalImprovementPercent / validResults;
//            double avgSpeedupFactor = totalSpeedupFactor / validResults;
//            double avgFileSize = (double) totalFileSize / validResults;
//            
//            // 메모리 효율성 계산 (5MB 청크 vs 전체 파일)
//            double memoryEfficiency = (1.0 - (5.0 / avgFileSize)) * 100;
//            
//            // 성능 개선 지표
//            ElCommandMap improvements = new ElCommandMap();
//            improvements.put("averagePerformanceGain", String.format("%.1f%%", avgImprovementPercent));
//            improvements.put("averageSpeedupFactor", String.format("%.1fx", avgSpeedupFactor));
//            improvements.put("memoryUsageReduction", String.format("%.1f%%", memoryEfficiency));
//            improvements.put("avgProcessingTimeReduction", String.format("%.1f%%", 
//                (1.0 - (double) totalMultipartTime / totalTraditionalTime) * 100));
//            improvements.put("maxFileSize", getMaxProcessedFileSize(results) + "MB");
//            improvements.put("reliabilityIncrease", calculateReliabilityIncrease(results) + "%");
//            
//            // 통계 데이터
//            ElCommandMap statistics = new ElCommandMap();
//            statistics.put("totalTestsExecuted", validResults);
//            statistics.put("averageFileSizeMB", String.format("%.1f", avgFileSize));
//            statistics.put("bestPerformanceGain", getBestPerformanceGain(results) + "%");
//            statistics.put("worstPerformanceGain", getWorstPerformanceGain(results) + "%");
//            statistics.put("standardDeviation", calculateStandardDeviation(results));
//            statistics.put("consistencyScore", calculateConsistencyScore(results) + "%");
//            
//            // 트렌드 분석
//            ElCommandMap trends = new ElCommandMap();
//            trends.put("performanceTrend", analyzeTrend(results, "improvementPercent"));
//            trends.put("stabilityTrend", analyzeTrend(results, "memoryUsage"));
//            trends.put("throughputTrend", analyzeTrend(results, "throughput"));
//            trends.put("recommendation", generateRecommendation(avgImprovementPercent));
//            
//            metrics.put("improvements", improvements);
//            metrics.put("statistics", statistics);
//            metrics.put("trends", trends);
//        }
//        
//        return metrics;
//    }
//    
//    /**
//     * 실제 측정 기반 비즈니스 임팩트 생성
//     */
//    private ElCommandMap generateBusinessImpact(ElCommandMap calculatedMetrics) {
//        ElCommandMap businessImpact = new ElCommandMap();
//        
//        ElCommandMap improvements = calculatedMetrics.getMap("improvements");
//        double avgImprovement = Double.parseDouble(improvements.getString("averagePerformanceGain").replace("%", ""));
//        
//        // 측정 결과 기반 비즈니스 임팩트 평가
//        if (avgImprovement >= 40.0) {
//            businessImpact.put("userExperienceImprovement", "매우 높음 (40%+ 성능 향상)");
//            businessImpact.put("systemStabilityIncrease", "높음");
//            businessImpact.put("costEfficiencyGain", "높음");
//        } else if (avgImprovement >= 25.0) {
//            businessImpact.put("userExperienceImprovement", "높음 (25%+ 성능 향상)");
//            businessImpact.put("systemStabilityIncrease", "중간-높음");
//            businessImpact.put("costEfficiencyGain", "중간");
//        } else {
//            businessImpact.put("userExperienceImprovement", "중간 (25% 미만 성능 향상)");
//            businessImpact.put("systemStabilityIncrease", "중간");
//            businessImpact.put("costEfficiencyGain", "낮음-중간");
//        }
//        
//        businessImpact.put("maintenanceCostReduction", "측정된 안정성 기반: " + 
//            improvements.getString("reliabilityIncrease"));
//        businessImpact.put("scalabilityEnhancement", "확인된 최대 파일: " + 
//            improvements.getString("maxFileSize"));
//        
//        return businessImpact;
//    }
//    
//    /**
//     * 현재 기술 스택 정보 조회
//     */
//    private ElCommandMap getCurrentTechStack() {
//        ElCommandMap techStack = new ElCommandMap();
//        
//        // 실제 시스템 설정에서 조회하거나 배치 잡에서 가져오기
//        try {
//            ElCommandMap config = performanceBatchJob.getCurrentConfiguration();
//            
//            techStack.put("uploadMethod", config.getString("uploadMethod"));
//            techStack.put("chunkSize", config.getString("chunkSize"));
//            techStack.put("parallelism", config.getString("parallelism"));
//            techStack.put("memoryFootprint", config.getString("memoryFootprint"));
//            techStack.put("networkTimeout", config.getString("networkTimeout"));
//            techStack.put("retryPolicy", config.getString("retryPolicy"));
//            
//        } catch (Exception e) {
//            // 설정 조회 실패 시 기본값
//            techStack.put("uploadMethod", "AWS S3 Multipart Upload");
//            techStack.put("chunkSize", "5MB");
//            techStack.put("parallelism", "4 threads");
//            techStack.put("memoryFootprint", "5MB (고정)");
//            techStack.put("networkTimeout", "30초");
//            techStack.put("retryPolicy", "3회 재시도");
//        }
//        
//        return techStack;
//    }
//    
//    /**
//     * 기본 요약 정보 생성 (측정 데이터 없을 때)
//     */
//    private ElCommandMap generateDefaultSummary() {
//        ElCommandMap summary = new ElCommandMap();
//        
//        summary.put("title", "대용량 파일 업로드 성능 개선 효과");
//        summary.put("status", "측정 데이터 수집 중");
//        summary.put("message", "충분한 측정 데이터가 수집되면 실제 성능 지표가 표시됩니다.");
//        summary.put("implementationDate", "2025-07-29");
//        summary.put("generatedDate", new java.util.Date());
//        
//        // 예상 효과 (이론적)
//        ElCommandMap expectedEffects = new ElCommandMap();
//        expectedEffects.put("expectedPerformanceGain", "30-50% (이론값)");
//        expectedEffects.put("expectedMemoryReduction", "90%+ (이론값)");
//        expectedEffects.put("expectedScalability", "5GB+ 파일 처리 가능");
//        
//        summary.put("expectedEffects", expectedEffects);
//        summary.put("technicalStack", getCurrentTechStack());
//        
//        return summary;
//    }
//    
//    // Helper 메서드들
//    private String getMaxProcessedFileSize(java.util.List<ElCommandMap> results) {
//        long maxSize = 0;
//        for (ElCommandMap result : results) {
//            long size = result.getLong("fileSizeMB");
//            if (size > maxSize) maxSize = size;
//        }
//        return String.valueOf(maxSize);
//    }
//    
//    private String getBestPerformanceGain(java.util.List<ElCommandMap> results) {
//        double best = 0.0;
//        for (ElCommandMap result : results) {
//            double gain = result.getDouble("improvementPercent");
//            if (gain > best) best = gain;
//        }
//        return String.format("%.1f", best);
//    }
//    
//    private String getWorstPerformanceGain(java.util.List<ElCommandMap> results) {
//        double worst = Double.MAX_VALUE;
//        for (ElCommandMap result : results) {
//            double gain = result.getDouble("improvementPercent");
//            if (gain < worst) worst = gain;
//        }
//        return worst == Double.MAX_VALUE ? "0.0" : String.format("%.1f", worst);
//    }
//    
//    private String calculateReliabilityIncrease(java.util.List<ElCommandMap> results) {
//        // 성공률 기반 안정성 계산
//        int successCount = 0;
//        for (ElCommandMap result : results) {
//            if (result.getBoolean("success")) successCount++;
//        }
//        double reliability = (double) successCount / results.size() * 100;
//        return String.format("%.1f", reliability);
//    }
//    
//    private String calculateStandardDeviation(java.util.List<ElCommandMap> results) {
//        // 성능 향상률의 표준편차 계산
//        double sum = 0.0;
//        double mean = 0.0;
//        int count = 0;
//        
//        // 평균 계산
//        for (ElCommandMap result : results) {
//            if (result.getBoolean("success")) {
//                sum += result.getDouble("improvementPercent");
//                count++;
//            }
//        }
//        mean = sum / count;
//        
//        // 표준편차 계산
//        double variance = 0.0;
//        for (ElCommandMap result : results) {
//            if (result.getBoolean("success")) {
//                double diff = result.getDouble("improvementPercent") - mean;
//                variance += diff * diff;
//            }
//        }
//        variance /= count;
//        
//        return String.format("%.2f", Math.sqrt(variance));
//    }
//    
//    private String calculateConsistencyScore(java.util.List<ElCommandMap> results) {
//        // 일관성 점수 (표준편차 기반)
//        double stdDev = Double.parseDouble(calculateStandardDeviation(results));
//        double consistency = Math.max(0, 100 - (stdDev * 2)); // 표준편차가 낮을수록 일관성 높음
//        return String.format("%.1f", consistency);
//    }
//    
//    private String analyzeTrend(java.util.List<ElCommandMap> results, String metric) {
//        if (results.size() < 3) return "데이터 부족";
//        
//        // 최근 3개와 이전 3개 비교
//        int midPoint = results.size() / 2;
//        double recentAvg = 0.0, earlierAvg = 0.0;
//        int recentCount = 0, earlierCount = 0;
//        
//        for (int i = 0; i < midPoint; i++) {
//            ElCommandMap result = results.get(i);
//            if (result.getBoolean("success")) {
//                earlierAvg += result.getDouble(metric.contains("Percent") ? "improvementPercent" : "multipartThroughput");
//                earlierCount++;
//            }
//        }
//        
//        for (int i = midPoint; i < results.size(); i++) {
//            ElCommandMap result = results.get(i);
//            if (result.getBoolean("success")) {
//                recentAvg += result.getDouble(metric.contains("Percent") ? "improvementPercent" : "multipartThroughput");
//                recentCount++;
//            }
//        }
//        
//        if (earlierCount > 0 && recentCount > 0) {
//            recentAvg /= recentCount;
//            earlierAvg /= earlierCount;
//            
//            if (recentAvg > earlierAvg * 1.05) return "개선 중";
//            else if (recentAvg < earlierAvg * 0.95) return "저하 중";
//            else return "안정적";
//        }
//        
//        return "분석 불가";
//    }
//    
//    private String generateRecommendation(double avgImprovement) {
//        if (avgImprovement >= 50.0) {
//            return "뛰어난 성능 개선 효과. 다른 시스템에도 적용 검토 권장";
//        } else if (avgImprovement >= 30.0) {
//            return "양호한 성능 개선 효과. 추가 최적화 여지 있음";
//        } else if (avgImprovement >= 15.0) {
//            return "보통 수준의 개선 효과. 구성 옵션 튜닝 필요";
//        } else {
//            return "개선 효과 미흡. 시스템 환경 및 설정 점검 필요";
//        }
//    }
//    
//    /**
//     * 메모리 효율성 퍼센트 계산
//     */
//    private String calculateMemoryEfficiencyPercent(ElCommandMap results) {
//        long fileSizeMB = results.getLong("fileSizeMB");
//        long chunkSizeMB = 5;
//        
//        double efficiency = (1.0 - (double) chunkSizeMB / fileSizeMB) * 100;
//        return String.format("%.1f", Math.max(0, efficiency));
//    }
//}