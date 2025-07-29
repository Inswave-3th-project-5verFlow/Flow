package com.demo.proworks.batch.performance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.inswave.elfw.log.AppLog;

/**
 * 효율적인 테스트 파일 생성기
 * Java 8 호환 및 메모리 효율적인 대용량 파일 생성
 */
@Component
public class EfficientTestFileCreator {
    
    /**
     * 지정된 크기의 테스트 파일 생성 (방법 1: FileChannel 사용)
     * 가장 효율적이고 빠른 방법
     */
    public File createTestFileWithChannel(String filePath, String fileName, int sizeMB) throws IOException {
        File testDir = new File(filePath);
        if (!testDir.exists()) {
            testDir.mkdirs();
        }
        
        File testFile = new File(testDir, fileName);
        AppLog.info(sizeMB + "MB 테스트 파일 생성 시작 (FileChannel): " + fileName);
        
        long startTime = System.currentTimeMillis();
        
        try (RandomAccessFile raf = new RandomAccessFile(testFile, "rw");
             FileChannel channel = raf.getChannel()) {
            
            // 1MB 버퍼 생성
            int bufferSize = 1024 * 1024; // 1MB
            byte[] buffer = new byte[bufferSize];
            Arrays.fill(buffer, (byte) 'A'); // 'A'로 채우기
            
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            
            // 파일 크기만큼 반복해서 쓰기
            for (int i = 0; i < sizeMB; i++) {
                byteBuffer.rewind(); // 버퍼 포지션 리셋
                channel.write(byteBuffer);
                
                // 진행상황 로깅 (50MB마다)
                if ((i + 1) % 50 == 0) {
                    AppLog.debug("테스트 파일 생성 진행: " + (i + 1) + "MB / " + sizeMB + "MB");
                }
            }
            
            channel.force(false); // 강제로 디스크에 쓰기
        }
        
        long duration = System.currentTimeMillis() - startTime;
        AppLog.info("테스트 파일 생성 완료: " + fileName + " (" + (testFile.length() / 1024 / 1024) + "MB" + " , " + duration + "ms)");
        
        return testFile;
    }
    
    /**
     * 지정된 크기의 테스트 파일 생성 (방법 2: FileOutputStream 사용)
     * 호환성이 좋은 방법
     */
    public File createTestFileWithStream(String filePath, String fileName, int sizeMB) throws IOException {
        File testDir = new File(filePath);
        if (!testDir.exists()) {
            testDir.mkdirs();
        }
        
        File testFile = new File(testDir, fileName);
        AppLog.info(sizeMB + "MB 테스트 파일 생성 시작 (FileOutputStream): " + fileName);
        
        long startTime = System.currentTimeMillis();
        
        try (FileOutputStream fos = new FileOutputStream(testFile)) {
            // 64KB 버퍼 사용 (메모리 효율적)
            int bufferSize = 64 * 1024; // 64KB
            byte[] buffer = new byte[bufferSize];
            Arrays.fill(buffer, (byte) 'A');
            
            long totalBytes = (long) sizeMB * 1024 * 1024;
            long writtenBytes = 0;
            
            while (writtenBytes < totalBytes) {
                long remainingBytes = totalBytes - writtenBytes;
                int writeSize = (int) Math.min(bufferSize, remainingBytes);
                
                fos.write(buffer, 0, writeSize);
                writtenBytes += writeSize;
                
                // 진행상황 로깅 (50MB마다)
                long writtenMB = writtenBytes / 1024 / 1024;
                if (writtenMB > 0 && writtenMB % 50 == 0) {
                    AppLog.debug("테스트 파일 생성 진행: " + writtenMB + "MB / " + sizeMB + "MB");
                }
            }
            
            fos.flush();
        }
        
        long duration = System.currentTimeMillis() - startTime;
        AppLog.info("테스트 파일 생성 완료: " + fileName + " (" + (testFile.length() / 1024 / 1024) + "MB" + " , " + duration + "ms)");
        
        return testFile;
    }
    
    /**
     * 지정된 크기의 테스트 파일 생성 (방법 3: 간단한 반복 방법)
     * 가장 단순하지만 느린 방법
     */
    public File createTestFileSimple(String filePath, String fileName, int sizeMB) throws IOException {
        File testDir = new File(filePath);
        if (!testDir.exists()) {
            testDir.mkdirs();
        }
        
        File testFile = new File(testDir, fileName);
        AppLog.info(sizeMB + "MB 테스트 파일 생성 시작 (Simple): " + fileName);
        
        long startTime = System.currentTimeMillis();
        
        try (FileOutputStream fos = new FileOutputStream(testFile)) {
            // Java 8 호환: 1KB씩 쓰기
            byte[] kilobyteData = new byte[1024]; // 1KB
            Arrays.fill(kilobyteData, (byte) 'A');
            
            int totalKB = sizeMB * 1024; // 총 KB 수
            
            for (int i = 0; i < totalKB; i++) {
                fos.write(kilobyteData);
                
                // 진행상황 로깅 (10MB마다)
                if ((i + 1) % (10 * 1024) == 0) {
                    AppLog.debug("테스트 파일 생성 진행: " + ((i + 1) / 1024) + "MB / " + sizeMB + "MB");
                }
            }
        }
        
        long duration = System.currentTimeMillis() - startTime;
        AppLog.info("테스트 파일 생성 완료: " + fileName + " (" + (testFile.length() / 1024 / 1024) + "MB" + " , " + duration + "ms)");
        
        return testFile;
    }
    
    /**
     * 260MB 테스트 파일 생성 (데모용)
     */
    public File create260MBTestFile(String filePath) throws IOException {
        String fileName = String.format("demo_260MB_%d.dat", System.currentTimeMillis());
        
        // 가장 효율적인 방법 사용
        return createTestFileWithChannel(filePath, fileName, 260);
    }
    
    /**
     * 다양한 크기의 테스트 파일들 생성 (비교 테스트용)
     */
    public File[] createMultiSizeTestFiles(String filePath) throws IOException {
        int[] sizes = {10, 50, 100, 260}; // MB
        File[] testFiles = new File[sizes.length];
        
        for (int i = 0; i < sizes.length; i++) {
            String fileName = String.format("test_%dMB_%d.dat", sizes[i], System.currentTimeMillis());
            testFiles[i] = createTestFileWithChannel(filePath, fileName, sizes[i]);
        }
        
        return testFiles;
    }
    
    /**
     * 테스트 파일 유효성 검증
     */
    public boolean validateTestFile(File testFile, int expectedSizeMB) {
        if (!testFile.exists()) {
            AppLog.error("테스트 파일이 존재하지 않습니다: " + testFile.getAbsolutePath());
            return false;
        }
        
        long actualSize = testFile.length();
        long expectedSize = (long) expectedSizeMB * 1024 * 1024;
        
        if (Math.abs(actualSize - expectedSize) > 1024) { // 1KB 오차 허용
            AppLog.error("테스트 파일 크기가 예상과 다릅니다. 예상: " + expectedSizeMB + "MB" + " , " + "실제: " + (actualSize / 1024 / 1024) + "MB");
            return false;
        }
        
        AppLog.info("테스트 파일 유효성 검증 통과: " + testFile.getName() + " (" + (actualSize / 1024 / 1024) + "MB)");
        return true;
    }
    
    /**
     * 테스트 파일 정리
     */
    public void cleanupTestFiles(String filePath) {
        File testDir = new File(filePath);
        if (!testDir.exists()) {
            return;
        }
        
        File[] files = testDir.listFiles((dir, name) -> 
            name.startsWith("test_") || name.startsWith("demo_"));
        
        if (files != null) {
            int deletedCount = 0;
            for (File file : files) {
                if (file.delete()) {
                    deletedCount++;
                }
            }
            AppLog.info("테스트 파일 정리 완료: " + deletedCount + " 개 파일 삭제");
        }
    }
}