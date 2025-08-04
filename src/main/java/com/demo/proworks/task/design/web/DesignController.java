package com.demo.proworks.task.design.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.task.design.service.DesignService;
import com.demo.proworks.task.design.vo.DesignVo;
import com.demo.proworks.task.design.vo.DesignListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.log.AppLog;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @subject : 설계 업무 정보 관련 처리를 담당하는 컨트롤러
 * @description : 설계 업무 정보 관련 처리를 담당하는 컨트롤러
 * @author : 백승호
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 백승호 최초 생성
 * 
 */
@Controller
public class DesignController {

	/** DesignService */
	@Resource(name = "designServiceImpl")
	private DesignService designService;

	/**
	 * 모든 업무 정보 목록을 조회합니다.
	 *
	 * @param designVo 설계 업무 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "TASK001All")
	@RequestMapping(value = "TASK001All")
	@ElDescription(sub = "모든 업무 정보 목록조회", desc = "모든 업무 정보 목록조회한다.")
	public DesignListVo selectTasks(DesignVo designVo) throws Exception {

		List<DesignVo> designList = designService.selectTasks(designVo);
		long totCnt = designService.selectListCountDesign(designVo);

		DesignListVo retDesignList = new DesignListVo();

		AppLog.debug("designList : " + designList);
		retDesignList.setDesignVoList(designList);
		retDesignList.setTotalCount(totCnt);
		retDesignList.setPageSize(designVo.getPageSize());
		retDesignList.setPageIndex(designVo.getPageIndex());

		return retDesignList;
	}

	/**
	 * 설계 업무 정보 목록을 조회합니다.
	 *
	 * @param designVo 설계 업무 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "DESIGN001List")
	@RequestMapping(value = "DESIGN001List")
	@ElDescription(sub = "설계 업무 정보 목록조회", desc = "페이징을 처리하여 설계 업무 정보 목록 조회를 한다.")
	public DesignListVo selectListDesign(DesignVo designVo) throws Exception {

		List<DesignVo> designList = designService.selectListDesign(designVo);
		long totCnt = designService.selectListCountDesign(designVo);

		DesignListVo retDesignList = new DesignListVo();

		AppLog.debug("designList : " + designList);

		retDesignList.setDesignVoList(designList);
		retDesignList.setTotalCount(totCnt);
		retDesignList.setPageSize(designVo.getPageSize());
		retDesignList.setPageIndex(designVo.getPageIndex());

		return retDesignList;
	}

	/**
	 * 개발 업무 정보 목록을 조회합니다.
	 *
	 * @param designVo 설계 업무 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "DEVELOP001List")
	@RequestMapping(value = "DEVELOP001List")
	@ElDescription(sub = "개발 업무 정보 목록조회", desc = "페이징을 처리하여 개발 업무 정보 목록 조회를 한다.")
	public DesignListVo selectListDevelop(DesignVo designVo) throws Exception {

		List<DesignVo> designList = designService.selectListDevelop(designVo);
		long totCnt = designService.selectListCountDevelop(designVo);

		DesignListVo retDesignList = new DesignListVo();

		AppLog.debug("designList : " + designList);

		retDesignList.setDesignVoList(designList);
		retDesignList.setTotalCount(totCnt);
		retDesignList.setPageSize(designVo.getPageSize());
		retDesignList.setPageIndex(designVo.getPageIndex());

		return retDesignList;
	}

	/**
	 * 업무 정보을 단건 조회 처리 한다.
	 *
	 * @param designVo 설계 업무 정보
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "DESIGN001UpdView")
	@RequestMapping(value = "DESIGN001UpdView")
	@ElDescription(sub = "설계 업무 정보 갱신 폼을 위한 조회", desc = "설계 업무 정보 갱신 폼을 위한 조회를 한다.")
	public DesignVo selectDesign(DesignVo designVo) throws Exception {
		DesignVo selectDesignVo = designService.selectDesign(designVo);
		
		AppLog.debug("selectDesignVo : " + selectDesignVo);

		return selectDesignVo;
	}

	/**
	 * 업무 정보를 등록 처리 한다.
	 *
	 * @param designVo 설계 업무 정보
	 * @throws Exception
	 */
	@ElService(key = "DESIGN001Ins")
	@RequestMapping(value = "DESIGN001Ins")
	@ElDescription(sub = "설계 업무 정보 등록처리", desc = "설계 업무 정보를 등록 처리 한다.")
	public void insertDesign(DesignVo designVo) throws Exception {
		designService.insertDesign(designVo);
	}

	/**
	 * 업무 정보를 갱신 처리 한다.
	 *
	 * @param designVo 설계 업무 정보
	 * @throws Exception
	 */
	@ElService(key = "DESIGN001Upd")
	@RequestMapping(value = "DESIGN001Upd")
	@ElValidator(errUrl = "/design/designRegister", errContinue = true)
	@ElDescription(sub = "설계 업무 정보 갱신처리", desc = "설계 업무 정보를 갱신 처리 한다.")
	public void updateDesign(DesignVo designVo) throws Exception {

		designService.updateDesign(designVo);
	}

	/**
	 * 업무 정보를 삭제 처리한다.
	 *
	 * @param designVo 설계 업무 정보
	 * @throws Exception
	 */
	@ElService(key = "DESIGN001Del")
	@RequestMapping(value = "DESIGN001Del")
	@ElDescription(sub = "설계 업무 정보 삭제처리", desc = "설계 업무 정보를 삭제 처리한다.")
	public void deleteDesign(DesignVo designVo) throws Exception {
		designService.deleteDesign(designVo);
	}

	/**
	 * 업무 정보의 상태(rosStatus)에 따라서 통합 저장한다.
	 *
	 * @param designVoList 설계 업무들 정보
	 * @throws Exception
	 */
	@ElService(key = "DESIGN001Save")
	@RequestMapping(value = "DESIGN001Save")
	@ElDescription(sub = "설계 업무 정보들 통합 저장", desc = "설계 업무 정보들 통합 저장한다.")
	public void saveDesign(DesignListVo designVoList) throws Exception {

		int cnt = designVoList.getDesignVoList().size();

		for (int i = 0; i < cnt; i++) {
			String rowStatus = designVoList.getDesignVoList().get(i).getRowStatus();
			if (rowStatus.equals("C"))
				designService.insertDesign(designVoList.getDesignVoList().get(i));
			else if (rowStatus.equals("D"))
				designService.deleteDesign(designVoList.getDesignVoList().get(i));
			else if (rowStatus.equals("U"))
				designService.updateDesign(designVoList.getDesignVoList().get(i));
		}

	}

	/**
	 * 트리 구조로 보여주기 위해서 필요한 설계 업무 depth별로 정렬된 상태로 조회한다.
	 *
	 * @param designVo 설계 업무 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "DESIGN001Tree")
	@RequestMapping(value = "DESIGN001Tree")
	@ElDescription(sub = "트리 구조로 설계 업무를 조회", desc = "트리 구조로 설계 업무를 조회한다.")
	public DesignListVo selectTreeListDesign(DesignVo designVo) throws Exception {


		// 트리 구조는 전체 데이터가 필요하므로 페이징 제한 해제
		designVo.setPageSize(9999);
		designVo.setPageUnit(9999);
		AppLog.debug("조회 전 Param: " + designVo);
		
		List<DesignVo> designList = designService.selectTreeListDesign(designVo);
		AppLog.debug("조회 결과 개수: " + designList.size());
		AppLog.debug("designList : " + designList);
		
		DesignListVo retDesignList = new DesignListVo();
		retDesignList.setDesignVoList(designList);
		AppLog.debug("retDesignList : " + retDesignList);
		
		return retDesignList;
	}

	/**
	 * 트리 구조로 보여주기 위해서 필요한 개발 업무 depth별로 정렬된 상태로 조회한다.
	 *
	 * @param designVo 설계 업무 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "DEVELOP001Tree")
	@RequestMapping(value = "DEVELOP001Tree")
	@ElDescription(sub = "트리 구조로 개발 업무를 조회", desc = "트리 구조로 개발 업무를 조회한다.")
	public DesignListVo selectTreeListDevelop(DesignVo designVo) throws Exception {

		
		// 트리 구조는 전체 데이터가 필요하므로 페이징 제한 해제
		designVo.setPageSize(9999);
		designVo.setPageUnit(9999);
		AppLog.debug("조회 전 Param: " + designVo);
		
		List<DesignVo> designList = designService.selectTreeListDevelop(designVo);
		AppLog.debug("조회 결과 개수: " + designList.size());
		AppLog.debug("designList : " + designList);
		
		DesignListVo retDesignList = new DesignListVo();
		retDesignList.setDesignVoList(designList);
		AppLog.debug("retDesignList : " + retDesignList);
		
		return retDesignList;
	}

	/**
	 * 트리 구조로 보여주기 위해서 필요한 모든 업무 정보를 depth별로 정렬된 상태로 조회한다.
	 *
	 * @param designVo 업무 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "TASK001Tree")
	@RequestMapping(value = "TASK001Tree")
	@ElDescription(sub = "트리 구조로 모든 업무를 조회", desc = "트리 구조로 모든 업무를 조회한다.")
	public DesignListVo selectTreeList(DesignVo designVo) throws Exception {

		// 트리 구조는 전체 데이터가 필요하므로 페이징 제한 해제
		designVo.setPageSize(9999);
		designVo.setPageUnit(9999);
		AppLog.debug("조회 전 Param: " + designVo);
		
		List<DesignVo> designList = designService.selectTreeList(designVo);
		AppLog.debug("조회 결과 개수: " + designList.size());
		AppLog.debug("designList : " + designList);
		
		DesignListVo retDesignList = new DesignListVo();
		retDesignList.setDesignVoList(designList);
		AppLog.debug("retDesignList : " + retDesignList);
		
		return retDesignList;
	}

	/**
	 * 트리 구조로 보여주기 위해서 필요한 개발 업무 depth별로 정렬된 상태로 조회한다.
	 *
	 * @param designVo 설계 업무 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "TASK001List")
	@RequestMapping(value = "TASK001List")
	@ElDescription(sub = "모든 단계의 업무를 정렬된 형태로 조회.", desc = "모든 단계의 업무를 정렬된 형태로 조회한다.")
	public DesignListVo selectListTask(DesignVo designVo) throws Exception {

		// 트리 구조는 전체 데이터가 필요하므로 페이징 제한 해제
		designVo.setPageSize(9999);
		designVo.setPageUnit(9999);
		AppLog.debug("조회 전 Param: " + designVo);
		
		List<DesignVo> designList = designService.selectListTask(designVo);
		AppLog.debug("조회 결과 개수: " + designList.size());
		AppLog.debug("designList : " + designList);
		
		DesignListVo retDesignList = new DesignListVo();
		retDesignList.setDesignVoList(designList);
		AppLog.debug("retDesignList : " + retDesignList);
		
		return retDesignList;
	}

}
