package com.demo.proworks.iss.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.iss.service.IssService;
import com.demo.proworks.iss.vo.IssVo;
import com.demo.proworks.iss.dao.IssDAO;

/**  
 * @subject     : 이슈리스크관리 관련 처리를 담당하는 ServiceImpl
 * @description	: 이슈리스크관리 관련 처리를 담당하는 ServiceImpl
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Service("issServiceImpl")
public class IssServiceImpl implements IssService {

    @Resource(name="issDAO")
    private IssDAO issDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 이슈리스크관리 목록을 조회합니다.
     *
     * @process
     * 1. 이슈리스크관리 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<IssVo>을(를) 리턴한다.
     * 
     * @param  issVo 이슈리스크관리 IssVo
     * @return 이슈리스크관리 목록 List<IssVo>
     * @throws Exception
     */
	public List<IssVo> selectListIss(IssVo issVo) throws Exception {
		List<IssVo> list = issDAO.selectListIss(issVo);	
	
		return list;
	}

    /**
     * 조회한 이슈리스크관리 전체 카운트
     *
     * @process
     * 1. 이슈리스크관리 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  issVo 이슈리스크관리 IssVo
     * @return 이슈리스크관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountIss(IssVo issVo) throws Exception {
		return issDAO.selectListCountIss(issVo);
	}

    /**
     * 이슈리스크관리를 상세 조회한다.
     *
     * @process
     * 1. 이슈리스크관리를 상세 조회한다.
     * 2. 결과 IssVo을(를) 리턴한다.
     * 
     * @param  issVo 이슈리스크관리 IssVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public IssVo selectIss(IssVo issVo) throws Exception {
		IssVo resultVO = issDAO.selectIss(issVo);			
        
        return resultVO;
	}

    /**
     * 이슈리스크관리를 등록 처리 한다.
     *
     * @process
     * 1. 이슈리스크관리를 등록 처리 한다.
     * 
     * @param  issVo 이슈리스크관리 IssVo
     * @return 번호
     * @throws Exception
     */
	public int insertIss(IssVo issVo) throws Exception {
		return issDAO.insertIss(issVo);	
	}
	
    /**
     * 이슈리스크관리를 갱신 처리 한다.
     *
     * @process
     * 1. 이슈리스크관리를 갱신 처리 한다.
     * 
     * @param  issVo 이슈리스크관리 IssVo
     * @return 번호
     * @throws Exception
     */
	public int updateIss(IssVo issVo) throws Exception {				
		return issDAO.updateIss(issVo);	   		
	}

    /**
     * 이슈리스크관리를 삭제 처리 한다.
     *
     * @process
     * 1. 이슈리스크관리를 삭제 처리 한다.
     * 
     * @param  issVo 이슈리스크관리 IssVo
     * @return 번호
     * @throws Exception
     */
	public int deleteIss(IssVo issVo) throws Exception {
		return issDAO.deleteIss(issVo);
	}
	
}
