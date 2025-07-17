package com.demo.proworks.unit.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.unit.vo.UnitTestListVo;
import com.demo.proworks.unit.vo.UnitTestVo;

/**
 * 단위테스트 케이스 관리 서비스 인터페이스
 */
public interface UnitTestService {
    
    /**
     * 단위테스트 케이스 목록 조회
     *
     * @param paramMap 검색 조건
     * @return 단위테스트 케이스 목록
     * @throws Exception
     */
    List<UnitTestVo> selectUnitTestList(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스 상세 조회
     *
     * @param paramMap 조회 조건 (testCaseId)
     * @return 단위테스트 케이스 상세 정보
     * @throws Exception
     */
    UnitTestVo selectUnitTestDetail(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 이슈리스크관리 목록 조회의 전체 카운트를 조회한다.
     *
     * @param  vo 이슈리스크관리
     * @return 전체 카운트
     * @throws Exception
     */
    public long selectListCountUnitTest(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스 등록
     *
     * @param paramMap 등록할 테스트 케이스 정보
     * @return 등록 결과 (등록된 ID)
     * @throws Exception
     */
    String insertUnitTest(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 단위테스트 케이스와 파일 함께 등록
     *
     * @param testCaseMap 테스트 케이스 정보
     * @param files 첨부 파일 목록
     * @return 등록된 테스트 케이스 ID
     * @throws Exception
     */
    String insertUnitTestWithFiles(Map<String, Object> testCaseMap, List<MultipartFile> files) throws Exception;
    
    /**
     * 단위테스트 케이스 수정
     *
     * @param paramMap 수정할 테스트 케이스 정보
     * @return 수정 결과
     * @throws Exception
     */
    int updateUnitTest(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 단위테스트 케이스 삭제 (논리삭제)
     *
     * @param paramMap 삭제 조건 (testCaseId)
     * @return 삭제 결과
     * @throws Exception
     */
    int deleteUnitTest(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 단위테스트 케이스 첨부파일 목록 조회
     *
     * @param paramMap 조회 조건 (testCaseId)
     * @return 첨부파일 목록
     * @throws Exception
     */
    List<AttVo> selectUnitTestFileList(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 단위테스트 케이스 첨부파일 삭제
     *
     * @param paramMap 삭제 조건 (fileId)
     * @return 삭제 결과
     * @throws Exception
     */
    int deleteUnitTestFile(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 테스트 상태별 통계 조회
     *
     * @param paramMap 검색 조건
     * @return 통계 정보
     * @throws Exception
     */
    Map<String, Object> selectUnitTestStatistics(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 테스트 실행 상태 업데이트
     *
     * @param paramMap 업데이트 조건 (testCaseId, testStatus)
     * @return 업데이트 결과
     * @throws Exception
     */
    int updateTestStatus(Map<String, Object> paramMap) throws Exception;
    
    /**
     * 테스트 결과 업데이트
     *
     * @param paramMap 업데이트 조건 (testCaseId, actualResult, testStatus, duration, notes)
     * @return 업데이트 결과
     * @throws Exception
     */
    int updateTestResult(Map<String, Object> paramMap) throws Exception;
}