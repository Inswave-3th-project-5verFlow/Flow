package com.demo.proworks.pug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.pug.service.PugService;
import com.demo.proworks.pug.vo.PugVo;
import com.demo.proworks.pug.dao.PugDAO;

/**
 * @subject : 프로젝트 유저 그룹 매핑 정보 관련 처리를 담당하는 ServiceImpl
 * @description : 프로젝트 유저 그룹 매핑 정보 관련 처리를 담당하는 ServiceImpl
 * @author : 김성민
 * @since : 2025/07/07
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/07 김성민 최초 생성
 * 
 */
@Service("pugServiceImpl")
public class PugServiceImpl implements PugService {

	@Resource(name = "pugDAO")
	private PugDAO pugDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * 프로젝트 유저 그룹 매핑 정보 목록을 조회합니다.
	 *
	 * @process 1. 프로젝트 유저 그룹 매핑 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<PugVo>을(를) 리턴한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 프로젝트 유저 그룹 매핑 정보 목록 List<PugVo>
	 * @throws Exception
	 */
	public List<PugVo> selectListPug(PugVo pugVo) throws Exception {
		List<PugVo> list = pugDAO.selectListPug(pugVo);

		return list;
	}

	/**
	 * 조회한 프로젝트 유저 그룹 매핑 정보 전체 카운트
	 *
	 * @process 1. 프로젝트 유저 그룹 매핑 정보 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 프로젝트 유저 그룹 매핑 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountPug(PugVo pugVo) throws Exception {
		return pugDAO.selectListCountPug(pugVo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 상세 조회한다.
	 *
	 * @process 1. 프로젝트 유저 그룹 매핑 정보를 상세 조회한다. 2. 결과 PugVo을(를) 리턴한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public PugVo selectPug(PugVo pugVo) throws Exception {
		PugVo resultVO = pugDAO.selectPug(pugVo);

		return resultVO;
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 등록 처리 한다.
	 *
	 * @process 1. 프로젝트 유저 그룹 매핑 정보를 등록 처리 한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertPug(PugVo pugVo) throws Exception {
		return pugDAO.insertPug(pugVo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 갱신 처리 한다.
	 *
	 * @process 1. 프로젝트 유저 그룹 매핑 정보를 갱신 처리 한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updatePug(PugVo pugVo) throws Exception {
		return pugDAO.updatePug(pugVo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 삭제 처리 한다.
	 *
	 * @process 1. 프로젝트 유저 그룹 매핑 정보를 삭제 처리 한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deletePug(PugVo pugVo) throws Exception {
		return pugDAO.deletePug(pugVo);
	}

	/**
	 * 메뉴 그룹 목록을 조회한다.
	 *
	 * @process 1. 프로젝트 유저 그룹 매핑 정보를 삭제 처리 한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws Exception
	 */	
	public List<PugVo> selectListGrp(PugVo pugVo) throws Exception {		
		return pugDAO.selectListGrp(pugVo);
	}

}
