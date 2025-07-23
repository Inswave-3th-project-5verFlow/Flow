package com.demo.proworks.def.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO;
import com.demo.proworks.def.vo.DefVo;
import com.inswave.elfw.exception.ElException;

/**  
 * @subject     : 테스트결함관리 관련 처리를 담당하는 DAO 구현체
 * @description : 테스트결함관리 관련 처리를 담당하는 DAO 구현체
 * @author      : 우민지
 * @since       : 2025/07/23
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/23			 우민지	 		최초 생성
 * 
 */
@Repository("defDAO")
public class DefDAO extends ProworksDefaultAbstractDAO{

    /**
     * 테스트결함관리 페이징 처리하여 목록을 조회한다.
     */
    @SuppressWarnings("unchecked")
    public List<DefVo> selectListDef(DefVo defVo) throws Exception {
        return (List<DefVo>) list("def.selectListDef", defVo);
    }

    /**
     * 조회한 테스트결함관리 전체 카운트
     */
    public long selectListCountDef(DefVo defVo) throws Exception {
        return (Long) selectByPk("def.selectListCountDef", defVo);
    }

    /**
     * 테스트결함관리를 상세 조회한다.
     */

    public DefVo selectDef(DefVo defVo) throws Exception {
        return (DefVo) selectByPk("def.selectDef", defVo);
    }

    /**
     * 테스트결함관리를 등록 처리 한다.
     */

    public int insertDef(DefVo defVo) throws Exception {
        return insert("def.insertDef", defVo);
    }

    /**
     * 테스트결함관리를 갱신 처리 한다.
     */

    public int updateDef(DefVo defVo) throws Exception {
        return update("def.updateDef", defVo);
    }

    /**
     * 테스트결함관리를 삭제 처리 한다.
     */

    public int deleteDef(DefVo defVo) throws Exception {
        return update("def.deleteDef", defVo);
    }

    // ========== 자동 생성 기능 관련 메서드들 ==========

    /**
     * 테스트 ID로 최신 결함 조회
     */

    public DefVo selectLatestDefectByTestId(DefVo defVo) throws Exception {
        return (DefVo) selectByPk("def.selectLatestDefectByTestId", defVo);
    }

    /**
     * 결함 완료일 업데이트
     */

    public int updateDefectFixedDate(DefVo defVo) throws Exception {
        return update("def.updateDefectFixedDate", defVo);
    }

    /**
     * 결함 상태 변경 이력 기록
     */

    public int insertDefectStatusHistory(DefVo defVo) throws Exception {
        return insert("def.insertDefectStatusHistory", defVo);
    }

    /**
     * 상태별 결함 통계 조회
     */

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectDefectStatsByStatus(DefVo defVo) throws Exception {
        return (List<Map<String, Object>>) list("def.selectDefectStatsByStatus", defVo);
    }

    /**
     * 우선순위별 결함 통계 조회
     */

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectDefectStatsByPriority(DefVo defVo) throws Exception {
        return (List<Map<String, Object>>) list("def.selectDefectStatsByPriority", defVo);
    }

    /**
     * 수정 기한이 임박한 결함 목록 조회
     */

    @SuppressWarnings("unchecked")
    public List<DefVo> selectUpcomingDefects(DefVo defVo) throws Exception {
        return (List<DefVo>) list("def.selectUpcomingDefects", defVo);
    }

    /**
     * 결함 시퀀스 번호 생성
     */

    public int getNextDefectSequence() throws Exception {
        Object result = selectByPk("def.getNextDefectSequence", null);
        return result != null ? (Integer) result : 1;
    }
}