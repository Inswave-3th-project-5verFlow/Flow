package com.demo.proworks.out.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.out.vo.OutVo;
import com.demo.proworks.out.dao.OutDAO;

/**  
 * @subject     : 산출물관리 관련 처리를 담당하는 DAO
 * @description : 산출물관리 관련 처리를 담당하는 DAO
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Repository("outDAO")
public class OutDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 산출물관리 상세 조회한다.
     *  
     * @param  OutVo 산출물관리
     * @return OutVo 산출물관리
     * @throws ElException
     */
    public OutVo selectOut(OutVo vo) throws ElException {
        return (OutVo) selectByPk("com.demo.proworks.out.selectOut", vo);
    }

    /**
     * 페이징을 처리하여 산출물관리 목록조회를 한다.
     *  
     * @param  OutVo 산출물관리
     * @return List<OutVo> 산출물관리
     * @throws ElException
     */
    public List<OutVo> selectListOut(OutVo vo) throws ElException {      	
        return (List<OutVo>)list("com.demo.proworks.out.selectListOut", vo);
    }

    /**
     * 산출물관리 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  OutVo 산출물관리
     * @return 산출물관리 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountOut(OutVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.out.selectListCountOut", vo);
    }
        
    /**
     * 산출물관리를 등록한다.
     *  
     * @param  OutVo 산출물관리
     * @return 번호
     * @throws ElException
     */
    public int insertOut(OutVo vo) throws ElException {    	
        return insert("com.demo.proworks.out.insertOut", vo);
    }

    /**
     * 산출물관리를 갱신한다.
     *  
     * @param  OutVo 산출물관리
     * @return 번호
     * @throws ElException
     */
    public int updateOut(OutVo vo) throws ElException {
        return update("com.demo.proworks.out.updateOut", vo);
    }

    /**
     * 산출물관리를 삭제한다.
     *  
     * @param  OutVo 산출물관리
     * @return 번호
     * @throws ElException
     */
    public int deleteOut(OutVo vo) throws ElException {
        return delete("com.demo.proworks.out.deleteOut", vo);
    }

}
