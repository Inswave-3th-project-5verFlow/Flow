package com.demo.proworks.batch.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.demo.proworks.att.vo.AttVo;

/**
 * 대용량 파일 처리 서비스 인터페이스
 * - 멀티파트 업로드를 통한 대용량 파일 처리
 * - 성능 로깅 및 모니터링
 * - 파일 청크 처리 및 병렬 업로드
 * - 성능 비교 및 분석 기능
 * 
 * @author 개발자
 * @since 2025/07/29
 */
public interface LargeFileService {
    
    /**
     * 대용량 파일 업로드 (개선된 버전)
     * - 멀티파트 업로드 사용
     * - 청크 단위 병렬 처리
     * - 상세 성능 로깅
     * 
     * @param file 업로드할 파일
     * @param refType 참조 타입 (예: DEMO, UNIT_TEST, PERFORMANCE_TEST 등)
     * @param refId 참조 ID
     * @return 업로드된 파일 정보
     * @throws Exception 업로드 실패 시
     */
    AttVo uploadLargeFile(MultipartFile file, String refType, String refId) throws Exception;
    
    /**
     * 기존 방식 파일 업로드 (비교용)
     * - 단일 업로드 방식
     * - 기본 성능 로깅
     * - 성능 비교 분석용
     * 
     * @param file 업로드할 파일
     * @param refType 참조 타입  
     * @param refId 참조 ID
     * @return 업로드된 파일 정보
     * @throws Exception 업로드 실패 시
     */
    AttVo uploadLargeFileTraditional(MultipartFile file, String refType, String refId) throws Exception;
    
    /**
     * 대용량 파일 다운로드 (스트리밍)
     * - 메모리 효율적인 스트리밍 다운로드
     * - 청크 단위 처리
     * 
     * @param fileId 파일 ID
     * @return S3Object 스트림
     * @throws Exception 다운로드 실패 시
     */
    S3Object downloadLargeFile(String fileId) throws Exception;
    
    /**
     * 파일 업로드 성능 비교 테스트
     * 
     * @param file 테스트할 파일
     * @param refType 참조 타입
     * @param refId 참조 ID  
     * @return 성능 비교 결과 맵
     * @throws Exception 테스트 실패 시
     */
    Map<String, Object> performanceComparisonTest(MultipartFile file, String refType, String refId) throws Exception;
    
    /**
     * 멀티파트 업로드 진행률 조회
     * - 실시간 업로드 진행 상황 추적
     * - 업로드 상태 모니터링
     * 
     * @param uploadId 업로드 ID
     * @return 진행률 정보 맵
     * @throws Exception 조회 실패 시
     */
    Map<String, Object> getUploadProgress(String uploadId) throws Exception;
    
    /**
     * 대용량 파일 업로드 통계 조회
     * - 업로드 이력 및 성능 통계
     * - 시스템 성능 분석 데이터
     * 
     * @return 통계 정보 맵
     * @throws Exception 조회 실패 시
     */
    Map<String, Object> getLargeFileUploadStatistics() throws Exception;
}