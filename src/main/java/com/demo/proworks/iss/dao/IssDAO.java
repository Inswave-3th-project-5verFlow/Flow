package com.demo.proworks.iss.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.iss.vo.IssVo;
import com.demo.proworks.iss.dao.IssDAO;

/**  
 * @subject     : 이슈리스크관리 관련 처리를 담당하는 DAO
 * @description : 이슈리스크관리 관련 처리를 담당하는 DAO
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Repository("issDAO")
public class IssDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 이슈리스크관리 상세 조회한다.
     *  
     * @param  IssVo 이슈리스크관리
     * @return IssVo 이슈리스크관리
     * @throws ElException
     */
    public IssVo selectIss(IssVo vo) throws ElException {
        return (IssVo) selectByPk("com.demo.proworks.iss.selectIss", vo);
    }

    /**
     * 페이징을 처리하여 이슈리스크관리 목록조회를 한다.
     *  
     * @param  IssVo 이슈리스크관리
     * @return List<IssVo> 이슈리스크관리
     * @throws ElException
     */
    public List<IssVo> selectListIss(IssVo vo) throws ElException {      	
        return (List<IssVo>)list("com.demo.proworks.iss.selectListIss", vo);
    }

    /**
     * 이슈리스크관리 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  IssVo 이슈리스크관리
     * @return 이슈리스크관리 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountIss(IssVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.iss.selectListCountIss", vo);
    }
        
    /**
     * 이슈리스크관리를 등록한다.
     *  
     * @param  IssVo 이슈리스크관리
     * @return 번호
     * @throws ElException
     */
    public int insertIss(IssVo vo) throws ElException {    	
        return insert("com.demo.proworks.iss.insertIss", vo);
    }

    /**
     * 이슈리스크관리를 갱신한다.
     *  
     * @param  IssVo 이슈리스크관리
     * @return 번호
     * @throws ElException
     */
    public int updateIss(IssVo vo) throws ElException {
        return update("com.demo.proworks.iss.updateIss", vo);
    }

    /**
     * 이슈리스크관리를 삭제한다.
     *  
     * @param  IssVo 이슈리스크관리
     * @return 번호
     * @throws ElException
     */
    public int deleteIss(IssVo vo) throws ElException {
        return delete("com.demo.proworks.iss.deleteIss", vo);
    }

}
