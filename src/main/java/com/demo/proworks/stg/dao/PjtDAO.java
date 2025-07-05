package com.demo.proworks.stg.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.stg.vo.PjtVo;
import com.demo.proworks.stg.dao.PjtDAO;

/**  
 * @subject     : 프로젝트 정보 관련 처리를 담당하는 DAO
 * @description : 프로젝트 정보 관련 처리를 담당하는 DAO
 * @author      : 김성민
 * @since       : 2025/07/05
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/05			 김성민	 		최초 생성
 * 
 */
@Repository("pjtDAO")
public class PjtDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 프로젝트 정보 상세 조회한다.
     *  
     * @param  PjtVo 프로젝트 정보
     * @return PjtVo 프로젝트 정보
     * @throws ElException
     */
    public PjtVo selectPjt(PjtVo vo) throws ElException {
        return (PjtVo) selectByPk("com.demo.proworks.stg.selectPjt", vo);
    }

    /**
     * 페이징을 처리하여 프로젝트 정보 목록조회를 한다.
     *  
     * @param  PjtVo 프로젝트 정보
     * @return List<PjtVo> 프로젝트 정보
     * @throws ElException
     */
    public List<PjtVo> selectListPjt(PjtVo vo) throws ElException {      	
        return (List<PjtVo>)list("com.demo.proworks.stg.selectListPjt", vo);
    }

    /**
     * 프로젝트 정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  PjtVo 프로젝트 정보
     * @return 프로젝트 정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountPjt(PjtVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.stg.selectListCountPjt", vo);
    }
        
    /**
     * 프로젝트 정보를 등록한다.
     *  
     * @param  PjtVo 프로젝트 정보
     * @return 번호
     * @throws ElException
     */
    public int insertPjt(PjtVo vo) throws ElException {    	
        return insert("com.demo.proworks.stg.insertPjt", vo);
    }

    /**
     * 프로젝트 정보를 갱신한다.
     *  
     * @param  PjtVo 프로젝트 정보
     * @return 번호
     * @throws ElException
     */
    public int updatePjt(PjtVo vo) throws ElException {
        return update("com.demo.proworks.stg.updatePjt", vo);
    }

    /**
     * 프로젝트 정보를 삭제한다.
     *  
     * @param  PjtVo 프로젝트 정보
     * @return 번호
     * @throws ElException
     */
    public int deletePjt(PjtVo vo) throws ElException {
        return delete("com.demo.proworks.stg.deletePjt", vo);
    }

}
