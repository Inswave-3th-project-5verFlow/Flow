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
    public List<UnitTestVo> selectUnitTestList(UnitTestVo unitTestVo) throws ElException {
        return (List<UnitTestVo>) list("com.demo.proworks.unit.selectUnitTestList", unitTestVo);
    }
    
    /**
     * 실패한 단위테스트 케이스 목록 조회 (결함 등록용)
     */
    public List<UnitTestVo> selectFailedUnitTestList(UnitTestVo unitTestVo) throws ElException {
        return (List<UnitTestVo>) list("com.demo.proworks.unit.selectFailedUnitTestList", unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 총 개수 조회
     */
    public long selectUnitTestListCount(UnitTestVo unitTestVo) throws ElException {
        return (Long)selectByPk("com.demo.proworks.unit.selectUnitTestListCount", unitTestVo);
    }
    
    /**
     * 실패한 단위테스트 케이스 총 개수 조회 (결함 등록용)
     */
    public long selectFailedUnitTestListCount(UnitTestVo unitTestVo) throws ElException {
        return (Long)selectByPk("com.demo.proworks.unit.selectFailedUnitTestListCount", unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 상세 조회
     */
    public UnitTestVo selectUnitTestDetail(UnitTestVo unitTestVo) throws ElException {
        return (UnitTestVo) selectByPk("com.demo.proworks.unit.selectUnitTestDetail", unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 등록
     */
    public int insertUnitTest(UnitTestVo unitTestVo) throws ElException {
        return insert("com.demo.proworks.unit.insertUnitTest", unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 수정
     */
    public int updateUnitTest(UnitTestVo unitTestVo) throws ElException {
        return update("com.demo.proworks.unit.updateUnitTest", unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 삭제 (논리삭제)
     */
    public int deleteUnitTest(UnitTestVo unitTestVo) throws ElException {
        return delete("com.demo.proworks.unit.deleteUnitTest", unitTestVo);
    }
    
    /**
     * 테스트 상태별 통계 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectUnitTestStatistics(UnitTestVo unitTestVo) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectUnitTestStatistics", unitTestVo);
    }
    
    /**
     * 테스트 실행 상태 업데이트
     */
    public int updateTestStatus(UnitTestVo unitTestVo) throws ElException {
        return update("com.demo.proworks.unit.updateTestStatus", unitTestVo);
    }
    
    /**
     * 테스트 결과 업데이트
     */
    public int updateTestResult(UnitTestVo unitTestVo) throws ElException {
        return update("com.demo.proworks.unit.updateTestResult", unitTestVo);
    }
    
    /**
     * 담당자별 테스트 케이스 개수 조회
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectTestCountByAssignee(UnitTestVo unitTestVo) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCountByAssignee", unitTestVo);
    }
    
    /**
     * 우선순위별 테스트 케이스 개수 조회
     */
    public List<Map<String, Object>> selectTestCountByPriority(UnitTestVo unitTestVo) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCountByPriority", unitTestVo);
    }
    
    /**
     * 테스트 타입별 테스트 케이스 개수 조회
     */
    public List<Map<String, Object>> selectTestCountByType(UnitTestVo unitTestVo) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCountByType", unitTestVo);
    }
    
    /**
     * 최근 테스트 실행 이력 조회
     */
    public List<Map<String, Object>> selectRecentTestExecution(UnitTestVo unitTestVo) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectRecentTestExecution", unitTestVo);
    }
    
    /**
     * 특정 기간 테스트 실행 통계 조회
     */
    public List<Map<String, Object>> selectTestExecutionStatsByDate(UnitTestVo unitTestVo) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestExecutionStatsByDate", unitTestVo);
    }
    
    /**
     * 테스트 커버리지 통계 조회
     */
    public List<Map<String, Object>> selectTestCoverageStats(UnitTestVo unitTestVo) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCoverageStats", unitTestVo);
    }
    
    /**
     * 테스트 케이스 중복 체크
     */
    public int checkDuplicateTestCase(UnitTestVo unitTestVo) throws ElException {
        Object result = selectByPk("com.demo.proworks.unit.checkDuplicateTestCase", unitTestVo);
        return result != null ? (Integer) result : 0;
    }
    
    /**
     * 업무별 테스트 케이스 개수 조회
     */
    public List<Map<String, Object>> selectTestCountByTask(UnitTestVo unitTestVo) throws ElException {
        return (List<Map<String, Object>>) list("com.demo.proworks.unit.selectTestCountByTask", unitTestVo);
    }
    
    /**
     * 다음 테스트 케이스 시퀀스 번호 조회
     * TC_001, TC_002, ... TC_010, ... TC_100 형태의 ID를 위한 시퀀스 번호 생성
     */
    public int getNextTestCaseSequence() throws ElException {
        Object result = selectByPk("com.demo.proworks.unit.getNextTestCaseSequence", null);
        return result != null ? (Integer) result : 1;
    }
    
    public int updateProjectTaskIsTest(UnitTestVo unitTestVo) throws ElException {
	    return update("com.demo.proworks.unit.updateProjectTaskIsTest", unitTestVo);
	}
}