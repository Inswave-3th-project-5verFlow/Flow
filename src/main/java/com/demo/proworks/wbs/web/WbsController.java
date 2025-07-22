package com.demo.proworks.wbs.web;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.wbs.service.WbsService;
import com.demo.proworks.wbs.vo.WbsVo;
import com.demo.proworks.wbs.vo.WbsListVo;
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
 *               2025/07/19 김성민 STG 테이블 관련 코드 제거
 * 
 */
@Controller
public class WbsController {

	/** WbsService */
	@Resource(name = "wbsServiceImpl")
	private WbsService wbsService;

	/**
	 * WBS 목록을 조회 한다.
	 *
	 * @param wbsVo WBS
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "WbsList")
	@RequestMapping(value = "WbsList")
	@ElDescription(sub = "WBS 목록조회", desc = "페이징을 처리하여 WBS 목록 조회를 한다.")
	public WbsListVo selectListWbs(WbsVo wbsVo) throws Exception {
		List<WbsVo> wbsList;
		long totCnt;

		// 검색 조건 확인
		if (hasSearchCondition(wbsVo)) {
			// 검색 조건이 있는 경우: 검색 결과 + 관련 계층
			wbsList = wbsService.selectListWbsSearch(wbsVo);
			totCnt = wbsService.selectListCountWbsSearch(wbsVo);
		} else {
			// 검색 조건이 없는 경우: 전체 계층 구조
			wbsList = wbsService.selectListWbsAll(wbsVo);
			totCnt = wbsService.selectListCountWbsAll(wbsVo);
		}

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
	 * WBS 목록을 다건 처리한다.
	 *
	 * @param wbsListVo WBS 목록 정보
	 * @throws Exception
	 */
	@ElService(key = "WbsSave")
	@RequestMapping(value = "WbsSave")
	@ElDescription(sub = "WBS 목록을 다건 처리한다.", desc = "WBS 목록을 다건 처리한다.")
	public void saveWbs(WbsListVo wbsListVo) throws Exception {
		int cnt = wbsListVo.getWbsVoList().size();

		for (int i = 0; i < cnt; i++) {
			WbsVo wbsVo = wbsListVo.getWbsVoList().get(i);
			String rowStatus = wbsVo.getRowStatus();

			switch (rowStatus) {
			case "C":
				wbsService.insertWbs(wbsVo);
				break;
			case "U":
				wbsService.updateWbs(wbsVo);
				break;
			case "D":
				wbsService.deleteWbs(wbsVo);
				break;
			default:
				// 예외처리 또는 무시
				break;
			}
		}
	}

	/**
	 * 검색 조건이 있는지 확인
	 */
	private boolean hasSearchCondition(WbsVo wbsVo) {
		return (wbsVo.getScTaskId() != null && !wbsVo.getScTaskId().trim().isEmpty())
				|| (wbsVo.getScTaskName() != null && !wbsVo.getScTaskName().trim().isEmpty())
				|| (wbsVo.getScTaskStatus() != null && !wbsVo.getScTaskStatus().trim().isEmpty())
				|| (wbsVo.getScTaskAsi() != null && !wbsVo.getScTaskAsi().trim().isEmpty())
				|| (wbsVo.getScTaskRate() != null && !wbsVo.getScTaskRate().trim().isEmpty());
	}

}