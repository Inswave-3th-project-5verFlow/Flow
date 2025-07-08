package com.demo.proworks.defect.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.defect.vo.DefVo;
import com.demo.proworks.defect.dao.DefDAO;

/**  
 * @subject     : 테스트결함관리 관련 처리를 담당하는 DAO
 * @description : 테스트결함관리 관련 처리를 담당하는 DAO
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Repository("defDAO")
public class DefDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 테스트결함관리 상세 조회한다.
     *  
     * @param  DefVo 테스트결함관리
     * @return DefVo 테스트결함관리
     * @throws ElException
     */
    public DefVo selectDef(DefVo vo) throws ElException {
        return (DefVo) selectByPk("com.demo.proworks.defect.selectDef", vo);
    }

    /**
     * 페이징을 처리하여 테스트결함관리 목록조회를 한다.
     *  
     * @param  DefVo 테스트결함관리
     * @return List<DefVo> 테스트결함관리
     * @throws ElException
     */
    public List<DefVo> selectListDef(DefVo vo) throws ElException {      	
        return (List<DefVo>)list("com.demo.proworks.defect.selectListDef", vo);
    }

    /**
     * 테스트결함관리 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  DefVo 테스트결함관리
     * @return 테스트결함관리 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountDef(DefVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.defect.selectListCountDef", vo);
    }
        
    /**
     * 테스트결함관리를 등록한다.
     *  
     * @param  DefVo 테스트결함관리
     * @return 번호
     * @throws ElException
     */
    public int insertDef(DefVo vo) throws ElException {    	
        return insert("com.demo.proworks.defect.insertDef", vo);
    }

    /**
     * 테스트결함관리를 갱신한다.
     *  
     * @param  DefVo 테스트결함관리
     * @return 번호
     * @throws ElException
     */
    public int updateDef(DefVo vo) throws ElException {
        return update("com.demo.proworks.defect.updateDef", vo);
    }

    /**
     * 테스트결함관리를 삭제한다.
     *  
     * @param  DefVo 테스트결함관리
     * @return 번호
     * @throws ElException
     */
    public int deleteDef(DefVo vo) throws ElException {
        return delete("com.demo.proworks.defect.deleteDef", vo);
    }

}
