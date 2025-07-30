package com.demo.proworks.cmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.cmt.dao.CmtDAO;
import com.demo.proworks.cmt.service.CmtService;
import com.demo.proworks.cmt.vo.CmtVo;

/**  
 * @subject     : 코멘트 관리 관련 처리를 담당하는 ServiceImpl
 * @description	: 코멘트 관리 관련 처리를 담당하는 ServiceImpl
 * @author      : 우민지
 * @since       : 2025/07/27
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/27			 우민지	 		최초 생성
 * 
 */
@Service("cmtServiceImpl")
public class CmtServiceImpl implements CmtService {

    @Resource(name="cmtDAO")
    private CmtDAO cmtDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 코멘트 관리 목록을 조회합니다.
     *
     * @process
     * 1. 코멘트 관리 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<CmtVo>을(를) 리턴한다.
     * 
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 코멘트 관리 목록 List<CmtVo>
     * @throws Exception
     */
	public List<CmtVo> selectListCmt(CmtVo cmtVo) throws Exception {
		List<CmtVo> list = cmtDAO.selectListCmt(cmtVo);	
	
		return list;
	}

    /**
     * 조회한 코멘트 관리 전체 카운트
     *
     * @process
     * 1. 코멘트 관리 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 코멘트 관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountCmt(CmtVo cmtVo) throws Exception {
		return cmtDAO.selectListCountCmt(cmtVo);
	}

    /**
     * 코멘트 관리를 상세 조회한다.
     *
     * @process
     * 1. 코멘트 관리를 상세 조회한다.
     * 2. 결과 CmtVo을(를) 리턴한다.
     * 
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public CmtVo selectCmt(CmtVo cmtVo) throws Exception {
		CmtVo resultVO = cmtDAO.selectCmt(cmtVo);			
        
        return resultVO;
	}

    /**
     * 코멘트 관리를 등록 처리 한다.
     *
     * @process
     * 1. 코멘트 관리를 등록 처리 한다.
     * 
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 번호
     * @throws Exception
     */
	public int insertCmt(CmtVo cmtVo) throws Exception {
		return cmtDAO.insertCmt(cmtVo);	
	}
	
    /**
     * 코멘트 관리를 갱신 처리 한다.
     *
     * @process
     * 1. 코멘트 관리를 갱신 처리 한다.
     * 
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 번호
     * @throws Exception
     */
	public int updateCmt(CmtVo cmtVo) throws Exception {				
		return cmtDAO.updateCmt(cmtVo);	   		
	}

    /**
     * 코멘트 관리를 삭제 처리 한다.
     *
     * @process
     * 1. 코멘트 관리를 삭제 처리 한다.
     * 
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 번호
     * @throws Exception
     */
	public int deleteCmt(CmtVo cmtVo) throws Exception {
		return cmtDAO.deleteCmt(cmtVo);
	}
	
}
