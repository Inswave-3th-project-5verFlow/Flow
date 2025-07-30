package com.demo.proworks.cmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.cmt.dao.CmtDAO;
import com.demo.proworks.cmt.vo.CmtVo;

/**  
 * @subject     : 코멘트 관리 관련 처리를 담당하는 DAO
 * @description : 코멘트 관리 관련 처리를 담당하는 DAO
 * @author      : 우민지
 * @since       : 2025/07/27
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/27			 우민지	 		최초 생성
 * 
 */
@Repository("cmtDAO")
public class CmtDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 코멘트 관리 상세 조회한다.
     *  
     * @param  CmtVo 코멘트 관리
     * @return CmtVo 코멘트 관리
     * @throws ElException
     */
    public CmtVo selectCmt(CmtVo vo) throws ElException {
        return (CmtVo) selectByPk("com.demo.proworks.cmt.selectCmt", vo);
    }

    /**
     * 페이징을 처리하여 코멘트 관리 목록조회를 한다.
     *  
     * @param  CmtVo 코멘트 관리
     * @return List<CmtVo> 코멘트 관리
     * @throws ElException
     */
    public List<CmtVo> selectListCmt(CmtVo vo) throws ElException {      	
        return (List<CmtVo>)list("com.demo.proworks.cmt.selectListCmt", vo);
    }

    /**
     * 코멘트 관리 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  CmtVo 코멘트 관리
     * @return 코멘트 관리 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountCmt(CmtVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.cmt.selectListCountCmt", vo);
    }
        
    /**
     * 코멘트 관리를 등록한다.
     *  
     * @param  CmtVo 코멘트 관리
     * @return 번호
     * @throws ElException
     */
    public int insertCmt(CmtVo vo) throws ElException {    	
        return insert("com.demo.proworks.cmt.insertCmt", vo);
    }

    /**
     * 코멘트 관리를 갱신한다.
     *  
     * @param  CmtVo 코멘트 관리
     * @return 번호
     * @throws ElException
     */
    public int updateCmt(CmtVo vo) throws ElException {
        return update("com.demo.proworks.cmt.updateCmt", vo);
    }

    /**
     * 코멘트 관리를 삭제한다.
     *  
     * @param  CmtVo 코멘트 관리
     * @return 번호
     * @throws ElException
     */
    public int deleteCmt(CmtVo vo) throws ElException {
        return delete("com.demo.proworks.cmt.deleteCmt", vo);
    }

}
