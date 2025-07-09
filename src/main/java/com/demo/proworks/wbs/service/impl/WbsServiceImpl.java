package com.demo.proworks.wbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.wbs.service.WbsService;
import com.demo.proworks.wbs.vo.WbsStgVo;
import com.demo.proworks.wbs.vo.WbsVo;
import com.demo.proworks.wbs.dao.WbsDAO;

/**
 * @subject : WBS 관련 처리를 담당하는 ServiceImpl
 * @description : WBS 관련 처리를 담당하는 ServiceImpl
 * @author : 김성민
 * @since : 2025/07/09
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/09 김성민 최초 생성
 * 
 */
@Service("wbsServiceImpl")
public class WbsServiceImpl implements WbsService {

	@Resource(name = "wbsDAO")
	private WbsDAO wbsDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * WBS 목록을 조회합니다.
	 *
	 * @process 1. WBS 페이징 처리하여 목록을 조회한다. 2. 결과 List<WbsVo>을(를) 리턴한다.
	 * 
	 * @param wbsVo WBS WbsVo
	 * @return WBS 목록 List<WbsVo>
	 * @throws Exception
	 */
	public List<WbsVo> selectListWbs(WbsVo wbsVo) throws Exception {
		List<WbsVo> list = wbsDAO.selectListWbs(wbsVo);

		return list;
	}

	/**
	 * 조회한 WBS 전체 카운트
	 *
	 * @process 1. WBS 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param wbsVo WBS WbsVo
	 * @return WBS 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountWbs(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListCountWbs(wbsVo);
	}

	/**
	 * WBS를 상세 조회한다.
	 *
	 * @process 1. WBS를 상세 조회한다. 2. 결과 WbsVo을(를) 리턴한다.
	 * 
	 * @param wbsVo WBS WbsVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public WbsVo selectWbs(WbsVo wbsVo) throws Exception {
		WbsVo resultVO = wbsDAO.selectWbs(wbsVo);

		return resultVO;
	}

	/**
	 * WBS를 등록 처리 한다.
	 *
	 * @process 1. WBS를 등록 처리 한다.
	 * 
	 * @param wbsVo WBS WbsVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertWbs(WbsVo wbsVo) throws Exception {
		return wbsDAO.insertWbs(wbsVo);
	}

	/**
	 * WBS를 갱신 처리 한다.
	 *
	 * @process 1. WBS를 갱신 처리 한다.
	 * 
	 * @param wbsVo WBS WbsVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateWbs(WbsVo wbsVo) throws Exception {
		return wbsDAO.updateWbs(wbsVo);
	}

	/**
	 * WBS를 삭제 처리 한다.
	 *
	 * @process 1. WBS를 삭제 처리 한다.
	 * 
	 * @param wbsVo WBS WbsVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteWbs(WbsVo wbsVo) throws Exception {
		return wbsDAO.deleteWbs(wbsVo);
	}

	/**
	 * 단계 목록을 조회 한다.
	 *
	 * @param WbsStgVo
	 * @return List<WbsStgVo>
	 * @throws Exception
	 */
	@Override
	public List<WbsStgVo> selectListStg(WbsStgVo wbsStgVo) throws Exception {		
		return wbsDAO.selectListStg(wbsStgVo);
	}
}
