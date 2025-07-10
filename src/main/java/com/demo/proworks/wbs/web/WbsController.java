package com.demo.proworks.wbs.web;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.wbs.service.WbsService;
import com.demo.proworks.wbs.vo.WbsVo;
import com.demo.proworks.wbs.vo.WbsListVo;
import com.demo.proworks.wbs.vo.WbsStgListVo;
import com.demo.proworks.wbs.vo.WbsStgVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @subject : WBS 관련 처리를 담당하는 컨트롤러
 * @description : WBS 관련 처리를 담당하는 컨트롤러
 * @author : 김성민
 * @since : 2025/07/09
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/09 김성민 최초 생성
 * 
 */
@Controller
public class WbsController {

	/** WbsService */
	@Resource(name = "wbsServiceImpl")
	private WbsService wbsService;

	/**
	 * WBS 목록을 조회합니다.
	 *
	 * @param wbsVo WBS
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "WbsList")
	@RequestMapping(value = "WbsList")
	@ElDescription(sub = "WBS 목록조회", desc = "페이징을 처리하여 WBS 목록 조회를 한다.")
	public WbsListVo selectListWbs(WbsVo wbsVo) throws Exception {

		List<WbsVo> wbsList = wbsService.selectListWbs(wbsVo);
		long totCnt = wbsService.selectListCountWbs(wbsVo);

		WbsListVo retWbsList = new WbsListVo();
		retWbsList.setWbsVoList(wbsList);
		retWbsList.setTotalCount(totCnt);
		retWbsList.setPageSize(wbsVo.getPageSize());
		retWbsList.setPageIndex(wbsVo.getPageIndex());

		return retWbsList;
	}

	/**
	 * WBS을 단건 조회 처리 한다.
	 *
	 * @param wbsVo WBS
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "WbsUpdView")
	@RequestMapping(value = "WbsUpdView")
	@ElDescription(sub = "WBS 갱신 폼을 위한 조회", desc = "WBS 갱신 폼을 위한 조회를 한다.")
	public WbsVo selectWbs(WbsVo wbsVo) throws Exception {
		WbsVo selectWbsVo = wbsService.selectWbs(wbsVo);

		return selectWbsVo;
	}

	/**
	 * WBS를 등록 처리 한다.
	 *
	 * @param wbsVo WBS
	 * @throws Exception
	 */
	@ElService(key = "WbsIns")
	@RequestMapping(value = "WbsIns")
	@ElDescription(sub = "WBS 등록처리", desc = "WBS를 등록 처리 한다.")
	public void insertWbs(WbsVo wbsVo) throws Exception {
		wbsService.insertWbs(wbsVo);
	}

	/**
	 * WBS를 갱신 처리 한다.
	 *
	 * @param wbsVo WBS
	 * @throws Exception
	 */
	@ElService(key = "WbsUpd")
	@RequestMapping(value = "WbsUpd")
	@ElValidator(errUrl = "/wbs/wbsRegister", errContinue = true)
	@ElDescription(sub = "WBS 갱신처리", desc = "WBS를 갱신 처리 한다.")
	public void updateWbs(WbsVo wbsVo) throws Exception {

		wbsService.updateWbs(wbsVo);
	}

	/**
	 * WBS를 삭제 처리한다.
	 *
	 * @param wbsVo WBS
	 * @throws Exception
	 */
	@ElService(key = "WbsDel")
	@RequestMapping(value = "WbsDel")
	@ElDescription(sub = "WBS 삭제처리", desc = "WBS를 삭제 처리한다.")
	public void deleteWbs(WbsVo wbsVo) throws Exception {
		wbsService.deleteWbs(wbsVo);
	}	

	/**
     * 단계 목록을조회 한다.
     *
     * @param  WbsStgVo    
     * @throws Exception
     */
    @ElService(key = "WbsStgList")    
    @RequestMapping(value = "WbsStgList")
    @ElDescription(sub = "단계 목록 조회", desc = "단계 목록을 조회한다.")    
    public WbsStgListVo selectListStg(WbsStgVo wbsStgVo) throws Exception {
    	List<WbsStgVo> stgList = wbsService.selectListStg(wbsStgVo);
    	WbsStgListVo list = new WbsStgListVo();
    	list.setWbsStgVoList(stgList);
    	return list;
         
    }

}
