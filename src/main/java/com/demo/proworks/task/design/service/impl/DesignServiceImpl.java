package com.demo.proworks.task.design.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.task.design.service.DesignService;
import com.demo.proworks.task.design.vo.DesignVo;
import com.demo.proworks.task.design.dao.DesignDAO;

/**
 * @subject : 설계 업무 정보 관련 처리를 담당하는 ServiceImpl
 * @description : 설계 업무 정보 관련 처리를 담당하는 ServiceImpl
 * @author : 백승호
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 백승호 최초 생성
 * 
 */
@Service("designServiceImpl")
public class DesignServiceImpl implements DesignService {

	@Resource(name = "designDAO")
	private DesignDAO designDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * 설계 업무 정보 목록을 조회합니다.
	 *
	 * @process 1. 설계 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectListDesign(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectListDesign(designVo);

		return list;
	}
	

	/**
	 * 개발 업무 정보 목록을 조회합니다.
	 *
	 * @process 1. 설계 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectListDevelop(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectListDevelop(designVo);

		return list;
	}

	/**
	 * 조회한 설계 업무 정보 전체 카운트
	 *
	 * @process 1. 설계 업무 정보 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountDesign(DesignVo designVo) throws Exception {
		return designDAO.selectListCountDesign(designVo);
	}
	
	/**
	 * 조회한 설계 업무 정보 전체 카운트
	 *
	 * @process 1. 설계 업무 정보 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountDevelop(DesignVo designVo) throws Exception {
		return designDAO.selectListCountDevelop(designVo);
	}

	/**
	 * 설계 업무 정보를 상세 조회한다.
	 *
	 * @process 1. 설계 업무 정보를 상세 조회한다. 2. 결과 DesignVo을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public DesignVo selectDesign(DesignVo designVo) throws Exception {
		DesignVo resultVO = designDAO.selectDesign(designVo);

		return resultVO;
	}

	/**
	 * 설계 업무 정보를 등록 처리 한다.
	 *
	 * @process 1. 설계 업무 정보를 등록 처리 한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertDesign(DesignVo designVo) throws Exception {
		return designDAO.insertDesign(designVo);
	}

	/**
	 * 설계 업무 정보를 갱신 처리 한다.
	 *
	 * @process 1. 설계 업무 정보를 갱신 처리 한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateDesign(DesignVo designVo) throws Exception {
		return designDAO.updateDesign(designVo);
	}

	/**
	 * 설계 업무 정보를 삭제 처리 한다.
	 *
	 * @process 1. 설계 업무 정보를 삭제 처리 한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteDesign(DesignVo designVo) throws Exception {
		return designDAO.deleteDesign(designVo);
	}

	/**
	 * 트리 구조에 맞게 설계 업무 목록을 조회한다.
	 *
	 * @process 1. 설계 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTreeListDesign(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectTreeListDesign(designVo);

		return list;
	}

	/**
	 * 트리 구조에 맞게 개발 업무 목록을 조회한다.
	 *
	 * @process 1. 개발 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTreeListDevelop(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectTreeListDevelop(designVo);

		return list;
	}

}
