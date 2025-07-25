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
     * @param unitTestVo 검색 조건
     * @return 단위테스트 케이스 목록
     * @throws Exception
     */
    List<UnitTestVo> selectUnitTestList(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 실패한 단위테스트 케이스 목록 조회 (결함 등록용)
     *
     * @param unitTestVo 검색 조건
     * @return 실패한 단위테스트 케이스 목록
     * @throws Exception
     */
    List<UnitTestVo> selectFailedUnitTestList(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스 상세 조회
     *
     * @param unitTestVo 조회 조건 (testCaseId)
     * @return 단위테스트 케이스 상세 정보
     * @throws Exception
     */
    UnitTestVo selectUnitTestDetail(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스 목록 조회의 전체 카운트를 조회한다.
     *
     * @param  unitTestVo 단위테스트 케이스
     * @return 전체 카운트
     * @throws Exception
     */
    long selectListCountUnitTest(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 실패한 단위테스트 케이스 목록 조회의 전체 카운트를 조회한다.
     *
     * @param  unitTestVo 단위테스트 케이스
     * @return 실패한 테스트케이스 전체 카운트
     * @throws Exception
     */
    long selectFailedListCountUnitTest(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스 등록
     *
     * @param unitTestVo 등록할 테스트 케이스 정보
     * @return 등록 결과 (등록된 ID)
     * @throws Exception
     */
    String insertUnitTest(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스와 파일 함께 등록
     *
     * @param unitTestVo 테스트 케이스 정보
     * @param files 첨부 파일 배열
     * @return 등록된 테스트 케이스 정보
     * @throws Exception
     */
    UnitTestVo insertUnitTestWithFiles(UnitTestVo unitTestVo, MultipartFile[] files) throws Exception;
    
    /**
     * 단위테스트 케이스 수정
     *
     * @param unitTestVo 수정할 테스트 케이스 정보
     * @return 수정 결과
     * @throws Exception
     */
    int updateUnitTest(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스와 파일 함께 수정
     *
     * @param unitTestVo 테스트 케이스 정보 (testCaseId 필수)
     * @param files 새로 업로드할 파일 배열 (null 가능)
     * @return 수정된 테스트 케이스 정보
     * @throws Exception
     */
    UnitTestVo updateUnitTestWithFiles(UnitTestVo unitTestVo, MultipartFile[] files) throws Exception;
    
    /**
     * 단위테스트 케이스 삭제 (논리삭제)
     *
     * @param unitTestVo 삭제 조건 (testCaseId)
     * @return 삭제 결과
     * @throws Exception
     */
    int deleteUnitTest(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스 첨부파일 목록 조회
     *
     * @param unitTestVo 조회 조건 (testCaseId)
     * @return 첨부파일 목록
     * @throws Exception
     */
    List<AttVo> selectUnitTestFileList(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 단위테스트 케이스 첨부파일 삭제
     *
     * @param fileId 삭제할 파일 ID
     * @return 삭제 결과
     * @throws Exception
     */
    int deleteUnitTestFile(String fileId) throws Exception;
    
    /**
     * 테스트 상태별 통계 조회
     *
     * @param unitTestVo 검색 조건
     * @return 통계 정보
     * @throws Exception
     */
    Map<String, Object> selectUnitTestStatistics(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 테스트 실행 상태 업데이트
     *
     * @param unitTestVo 업데이트 조건 (testCaseId, testStatus)
     * @return 업데이트 결과
     * @throws Exception
     */
    int updateTestStatus(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 테스트 결과 업데이트
     *
     * @param unitTestVo 업데이트 조건 (testCaseId, actualResult, testStatus, duration, notes)
     * @return 업데이트 결과
     * @throws Exception
     */
    int updateTestResult(UnitTestVo unitTestVo) throws Exception;
    
}