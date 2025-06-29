package com.demo.proworks.pjt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.pjt.service.PjtService;
import com.demo.proworks.pjt.vo.PjtVo;
import com.demo.proworks.pjt.dao.PjtDAO;

/**  
 * @subject     : 프로젝트 기본 정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 프로젝트 기본 정보 관련 처리를 담당하는 ServiceImpl
 * @author      : 김성민
 * @since       : 2025/06/26
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/26			 김성민	 		최초 생성
 * 
 */
@Service("pjtServiceImpl")
public class PjtServiceImpl implements PjtService {

    @Resource(name="pjtDAO")
    private PjtDAO pjtDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 프로젝트 기본 정보 목록을 조회합니다.
     *
     * @process
     * 1. 프로젝트 기본 정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<PjtVo>을(를) 리턴한다.
     * 
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 프로젝트 기본 정보 목록 List<PjtVo>
     * @throws Exception
     */
	public List<PjtVo> selectListPjt(PjtVo pjtVo) throws Exception {
		List<PjtVo> list = pjtDAO.selectListPjt(pjtVo);	
	
		return list;
	}

    /**
     * 조회한 프로젝트 기본 정보 전체 카운트
     *
     * @process
     * 1. 프로젝트 기본 정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 프로젝트 기본 정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountPjt(PjtVo pjtVo) throws Exception {
		return pjtDAO.selectListCountPjt(pjtVo);
	}

    /**
     * 프로젝트 기본 정보를 상세 조회한다.
     *
     * @process
     * 1. 프로젝트 기본 정보를 상세 조회한다.
     * 2. 결과 PjtVo을(를) 리턴한다.
     * 
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public PjtVo selectPjt(PjtVo pjtVo) throws Exception {
		PjtVo resultVO = pjtDAO.selectPjt(pjtVo);			
        
        return resultVO;
	}

    /**
     * 프로젝트 기본 정보를 등록 처리 한다.
     *
     * @process
     * 1. 프로젝트 기본 정보를 등록 처리 한다.
     * 
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 번호
     * @throws Exception
     */
	public int insertPjt(PjtVo pjtVo) throws Exception {
		return pjtDAO.insertPjt(pjtVo);	
	}
	
    /**
     * 프로젝트 기본 정보를 갱신 처리 한다.
     *
     * @process
     * 1. 프로젝트 기본 정보를 갱신 처리 한다.
     * 
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 번호
     * @throws Exception
     */
	public int updatePjt(PjtVo pjtVo) throws Exception {				
		return pjtDAO.updatePjt(pjtVo);	   		
	}

    /**
     * 프로젝트 기본 정보를 삭제 처리 한다.
     *
     * @process
     * 1. 프로젝트 기본 정보를 삭제 처리 한다.
     * 
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 번호
     * @throws Exception
     */
	public int deletePjt(PjtVo pjtVo) throws Exception {
		return pjtDAO.deletePjt(pjtVo);
	}
	
}
