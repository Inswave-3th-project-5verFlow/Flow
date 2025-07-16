package com.demo.proworks.unit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO;
import com.demo.proworks.unit.vo.UnitTestVo;

/**
 * 단위테스트 케이스 관리 DAO
 */
@Repository("unitTestDao")
public class UnitTestDao extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {
    
    /**
     * 단위테스트 케이스 목록 조회
     */
    @SuppressWarnings("unchecked")
    public List<UnitTestVo> selectUnitTestList(UnitTestVo unitTestVo) throws ElException {
        return (List<UnitTestVo>) list("com.demo.proworks.unit.selectUnitTestList", unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 총 개수 조회
     */
    public long selectUnitTestListCount(UnitTestVo unitTestVo) throws ElException {
        
        return (Long)selectByPk("com.demo.proworks.unit.selectUnitTestListCount", unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 상세 조회
     */
    public UnitTestVo selectUnitTestDetail(Map<String, Object> paramMap) throws ElException {
        return (UnitTestVo) selectByPk("com.demo.proworks.unit.selectUnitTestDetail", paramMap);
    }
    
    /**
     * 단위테스트 케이스 등록
     */
    public int insertUnitTest(Map<String, Object> paramMap) throws ElException {
        return insert("com.demo.proworks.unit.insertUnitTest", paramMap);
    }
    
    /**
     * 단위테스트 케이스 수정
     */
    public int updateUnitTest(Map<String, Object> paramMap) throws ElException {
        return update("com.demo.proworks.unit.updateUnitTest", paramMap);
    }
    
    /**
     * 단위테스트 케이스 삭제 (논리삭제)
     */
    public int deleteUnitTest(Map<String, Object> paramMap) throws ElException {
        return update("com.demo.proworks.unit.deleteUnitTest", paramMap);
    }
    
    /**
     * 테스트 상태별 통계 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectUnitTestStatistics(Map<String, Object> paramMap) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectUnitTestStatistics", paramMap);
    }
    
    /**
     * 테스트 실행 상태 업데이트
     */
    public int updateTestStatus(Map<String, Object> paramMap) throws ElException {
        return update("com.demo.proworks.unit.updateTestStatus", paramMap);
    }
    
    /**
     * 테스트 결과 업데이트
     */
    public int updateTestResult(Map<String, Object> paramMap) throws ElException {
        return update("com.demo.proworks.unit.updateTestResult", paramMap);
    }
    
    /**
     * 담당자별 테스트 케이스 개수 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectTestCountByAssignee(Map<String, Object> paramMap) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCountByAssignee", paramMap);
    }
    
    /**
     * 우선순위별 테스트 케이스 개수 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectTestCountByPriority(Map<String, Object> paramMap) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCountByPriority", paramMap);
    }
    
    /**
     * 테스트 타입별 테스트 케이스 개수 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectTestCountByType(Map<String, Object> paramMap) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCountByType", paramMap);
    }
    
    /**
     * 최근 테스트 실행 이력 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectRecentTestExecution(Map<String, Object> paramMap) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectRecentTestExecution", paramMap);
    }
    
    /**
     * 특정 기간 테스트 실행 통계 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectTestExecutionStatsByDate(Map<String, Object> paramMap) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestExecutionStatsByDate", paramMap);
    }
    
    /**
     * 테스트 커버리지 통계 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectTestCoverageStats(Map<String, Object> paramMap) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCoverageStats", paramMap);
    }
    
    /**
     * 테스트 케이스 중복 체크
     */
    public int checkDuplicateTestCase(Map<String, Object> paramMap) throws ElException {
        Object result = selectByPk("com.demo.proworks.unit.checkDuplicateTestCase", paramMap);
        return result != null ? (Integer) result : 0;
    }
    
    /**
     * 업무별 테스트 케이스 개수 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectTestCountByTask(Map<String, Object> paramMap) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCountByTask", paramMap);
    }
}