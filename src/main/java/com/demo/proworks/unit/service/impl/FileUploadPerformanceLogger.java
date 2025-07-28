package com.demo.proworks.unit.service.impl;

import org.springframework.stereotype.Component;

import com.inswave.elfw.log.AppLog;



@Component
public class FileUploadPerformanceLogger {
	/**
	 * 
	 * 업로드 시작 로깅
	 */
	public void logUploadStart(String fileName, long fileSize, String method) {
		long fileSizeMB = fileSize / 1024 / 1024;
		long fileSizeKB = fileSize / 1024;
		if (fileSizeMB > 0) {
			AppLog.info("UPLOAD_START: fileName={}, fileSize={}MB, method={}" + fileName + "," +  fileSizeMB + "," +method);
		} else {
			AppLog.info("UPLOAD_START: fileName={}, fileSize={}KB, method={}"+ fileName + "," +  fileSizeMB + "," +method);
		}
		AppLog.debug("파일 업로드 시작: {} ({}bytes)" + " , " + fileName  + " , " + fileSize);
	}

	/**
	 * 
	 * 업로드 완료 로깅
	 */
	public void logUploadComplete(String fileName, long fileSize, long durationMs, String method) {
		long fileSizeMB = fileSize / 1024 / 1024;
		double throughputMBps = 0;
		if (durationMs > 0) {
			throughputMBps = (fileSize / 1024.0 / 1024.0) / (durationMs / 1000.0);
		}
		AppLog.info("UPLOAD_COMPLETE: fileName={}, fileSize={}MB, duration={}ms, throughput={}MB/s, method={}" + " , " +
				fileName + " , " + fileSizeMB + " , " + durationMs + " , " + String.format("%.2f", throughputMBps) + " , " + method);
		AppLog.info("파일 업로드 완료: {} - {}MB, {:.1f}초, {}MB/s"+ " , " + fileName+ " , " + fileSizeMB+ " , " + durationMs / 1000.0+ " , " +
				String.format("%.2f", throughputMBps));
	}

	/**
	 * 
	 * 업로드 실패 로깅
	 */
	public void logUploadError(String fileName, long fileSize, long durationMs, String method, String errorMessage) {
		long fileSizeMB = fileSize / 1024 / 1024;
		AppLog.error("UPLOAD_ERROR: fileName={}, fileSize={}MB, duration={}ms, method={}, error={}" + " , " + fileName+ " , " +
				fileSizeMB+ " , " + durationMs+ " , " + method+ " , " + errorMessage);
		AppLog.error("파일 업로드 실패: {} - {}"+ " , " + fileName + " , " +errorMessage);
	}

	/**
	 * 
	 * 메모리 사용량 로깅
	 */
	public void logMemoryUsage(String operation) {
		Runtime runtime = Runtime.getRuntime();
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		long usedMemory = totalMemory - freeMemory;
		long maxMemory = runtime.maxMemory();
		AppLog.info("MEMORY_USAGE: operation={}, used={}MB, total={}MB, max={}MB, free={}MB"+ " , " + operation+ " , " +
				usedMemory / 1024 / 1024 + " , " + totalMemory / 1024 / 1024 + " , " + maxMemory / 1024 / 1024 + " , " + freeMemory / 1024 / 1024);
	}

	/**
	 * 
	 * 다중 파일 업로드 세션 시작 로깅
	 */
	public void logMultiFileUploadStart(int fileCount, long totalSize) {
		long totalSizeMB = totalSize / 1024 / 1024;
		AppLog.info("MULTI_UPLOAD_START: fileCount={}, totalSize={}MB"+ " , " + fileCount + " , " +totalSizeMB);
		AppLog.info("다중 파일 업로드 시작: {} 개 파일, 총 {}MB" + " , " + fileCount + " , " + totalSizeMB);
	}

	/**
	 * 
	 * 다중 파일 업로드 세션 완료 로깅
	 */
	public void logMultiFileUploadComplete(int fileCount, long totalSize, long totalDuration) {
		long totalSizeMB = totalSize / 1024 / 1024;
		double avgThroughputMBps = 0;
		if (totalDuration > 0) {
			avgThroughputMBps = (totalSize / 1024.0 / 1024.0) / (totalDuration / 1000.0);
		}
		AppLog.info("MULTI_UPLOAD_COMPLETE: fileCount={}, totalSize={}MB, totalDuration={}ms, avgThroughput={}MB/s"+ " , " +
				fileCount+ " , " + totalSizeMB+ " , " + totalDuration + " , " + String.format("%.2f", avgThroughputMBps));
		AppLog.info("다중 파일 업로드 완료: {} 개 파일, 총 {}MB, {:.1f}초, 평균 {}MB/s" + " , " +fileCount + " , " +totalSizeMB+ " , " + totalDuration / 1000.0 + " , " +
				String.format("%.2f", avgThroughputMBps));
	}

	/**
	 * 
	 * 업로드 방식 비교 로깅 (발표용)
	 */
	public void logUploadMethodComparison(String method, long fileSize, long duration, String notes) {
		long fileSizeMB = fileSize / 1024 / 1024;
		double throughputMBps = 0;
		if (duration > 0) {
			throughputMBps = (fileSize / 1024.0 / 1024.0) / (duration / 1000.0);
		}
		AppLog.info("UPLOAD_METHOD_COMPARISON: method={}, fileSize={}MB, duration={}ms, throughput={}MB/s, notes={}" + " , " +
				method + " , " +fileSizeMB + " , " + duration + " , " +String.format("%.2f", throughputMBps), notes);
	}

	/**
	 * 
	 * 시스템 리소스 상태 로깅
	 */
	public void logSystemResources(String operation) {
		Runtime runtime = Runtime.getRuntime();
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		long usedMemory = totalMemory - freeMemory;
		long maxMemory = runtime.maxMemory();
// 메모리 사용률 계산
		double memoryUsagePercent = ((double) usedMemory / maxMemory) * 100;
		AppLog.info("SYSTEM_RESOURCES: operation={}, memoryUsage={:.1f}%, used={}MB, max={}MB, availableProcessors={}" + " , " +
				operation + " , " + memoryUsagePercent + " , " +usedMemory / 1024 / 1024 + " , " + maxMemory / 1024 / 1024 + " , " +
				Runtime.getRuntime().availableProcessors());
	}
}