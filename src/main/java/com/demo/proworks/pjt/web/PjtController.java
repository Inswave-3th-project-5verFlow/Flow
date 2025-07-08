package com.demo.proworks.pjt.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.pjt.service.PjtService;
import com.demo.proworks.pjt.vo.PjtListVo;
import com.demo.proworks.pjt.vo.PjtVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**
 * @subject : 프로젝트 정보 관련 처리를 담당하는 컨트롤러
 * @description : 프로젝트 정보 관련 처리를 담당하는 컨트롤러
 * @author : 김성민
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 김성민 최초 생성
 * 
 */
@Controller
public class PjtController {

	/** PjtService */
	@Resource(name = "pjtServiceImpl")
	private PjtService pjtService;

	/**
	 * 프로젝트 정보 목록을 조회합니다.
	 *
	 * @param pjtVo 프로젝트 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "PjtList")
	@RequestMapping(value = "PjtList")
	@ElDescription(sub = "프로젝트 정보 목록조회", desc = "페이징을 처리하여 프로젝트 정보 목록 조회를 한다.")
	public PjtListVo selectListPjt(PjtVo pjtVo) throws Exception {

		List<PjtVo> pjtList = pjtService.selectListPjt(pjtVo);
		long totCnt = pjtService.selectListCountPjt(pjtVo);

		PjtListVo retPjtList = new PjtListVo();
		retPjtList.setPjtVoList(pjtList);
		retPjtList.setTotalCount(totCnt);
		retPjtList.setPageSize(pjtVo.getPageSize());
		retPjtList.setPageIndex(pjtVo.getPageIndex());

		return retPjtList;
	}

	/**
	 * 프로젝트 정보을 단건 조회 처리 한다.
	 *
	 * @param pjtVo 프로젝트 정보
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "PjtUpdView")
	@RequestMapping(value = "PjtUpdView")
	@ElDescription(sub = "프로젝트 정보 갱신 폼을 위한 조회", desc = "프로젝트 정보 갱신 폼을 위한 조회를 한다.")
	public PjtVo selectPjt(PjtVo pjtVo) throws Exception {
		PjtVo selectPjtVo = pjtService.selectPjt(pjtVo);

		return selectPjtVo;
	}

	/**
	 * 프로젝트 정보를 등록 처리 한다.
	 *
	 * @param pjtVo 프로젝트 정보
	 * @throws Exception
	 */
	@ElService(key = "PjtIns")
	@RequestMapping(value = "PjtIns")
	@ElDescription(sub = "프로젝트 정보 등록처리", desc = "프로젝트 정보를 등록 처리 한다.")
	public void insertPjt(PjtVo pjtVo) throws Exception {

		pjtService.insertPjt(pjtVo);
	}

	/**
	 * 프로젝트 정보를 갱신 처리 한다.
	 *
	 * @param pjtVo 프로젝트 정보
	 * @throws Exception
	 */
	@ElService(key = "PjtUpd")
	@RequestMapping(value = "PjtUpd")
	@ElValidator(errUrl = "/pjt/pjtRegister", errContinue = true)
	@ElDescription(sub = "프로젝트 정보 갱신처리", desc = "프로젝트 정보를 갱신 처리 한다.")
	public void updatePjt(PjtVo pjtVo) throws Exception {

		pjtService.updatePjt(pjtVo);
	}

	/**
	 * 프로젝트 정보를 삭제 처리한다.
	 *
	 * @param pjtVo 프로젝트 정보
	 * @throws Exception
	 */
	@ElService(key = "PjtDel")
	@RequestMapping(value = "PjtDel")
	@ElDescription(sub = "프로젝트 정보 삭제처리", desc = "프로젝트 정보를 삭제 처리한다.")
	public void deletePjt(PjtVo pjtVo) throws Exception {
		pjtService.deletePjt(pjtVo);
	}

}
