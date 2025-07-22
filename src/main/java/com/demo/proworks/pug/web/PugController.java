package com.demo.proworks.pug.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.cmmn.ProworksUserHeader;
import com.demo.proworks.pug.service.PugService;
import com.demo.proworks.pug.vo.PugVo;
import com.demo.proworks.pug.vo.PugListVo;
import com.demo.proworks.pug.vo.PugUserListVo;
import com.demo.proworks.pug.vo.PugUserVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @subject : 프로젝트 유저 그룹 매핑 정보 관련 처리를 담당하는 컨트롤러
 * @description : 프로젝트 유저 그룹 매핑 정보 관련 처리를 담당하는 컨트롤러
 * @author : 김성민
 * @since : 2025/07/07
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/07 김성민 최초 생성
 * 
 */
@Controller
public class PugController {

	/** PugService */
	@Resource(name = "pugServiceImpl")
	private PugService pugService;

	/**
	 * 프로젝트 유저 그룹 매핑 정보 목록을 조회 한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "PugList")
	@RequestMapping(value = "PugList")
	@ElDescription(sub = "프로젝트 유저 그룹 매핑 정보 목록조회", desc = "페이징을 처리하여 프로젝트 유저 그룹 매핑 정보 목록 조회를 한다.")
	public PugListVo selectListPug(PugVo pugVo) throws Exception {

		List<PugVo> pugList = pugService.selectListPug(pugVo);
		long totCnt = pugService.selectListCountPug(pugVo);

		PugListVo retPugList = new PugListVo();
		retPugList.setPugVoList(pugList);
		retPugList.setTotalCount(totCnt);
		retPugList.setPageSize(pugVo.getPageSize());
		retPugList.setPageIndex(pugVo.getPageIndex());

		return retPugList;
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보을 단건 조회 처리 한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "PugUpdView")
	@RequestMapping(value = "PugUpdView")
	@ElDescription(sub = "프로젝트 유저 그룹 매핑 정보 갱신 폼을 위한 조회", desc = "프로젝트 유저 그룹 매핑 정보 갱신 폼을 위한 조회를 한다.")
	public PugVo selectPug(PugVo pugVo) throws Exception {
		PugVo selectPugVo = pugService.selectPug(pugVo);

		return selectPugVo;
	}

	/**
	 * 메뉴 그룹 목록을 조회한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보
	 * @throws Exception
	 */
	@ElService(key = "PugGrpList")
	@RequestMapping(value = "PugGrpList")
	@ElDescription(sub = "메뉴 그룹 목록 조회", desc = "메뉴 그룹 목록을 조회한다.")
	public PugListVo selectListGrp(PugVo pugVo) throws Exception {
		List<PugVo> pugList = pugService.selectListGrp(pugVo);
		PugListVo retPugList = new PugListVo();
		retPugList.setPugVoList(pugList);
		return retPugList;
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 다건 처리한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보
	 * @throws Exception
	 */
	@ElService(key = "PugSave")
	@RequestMapping(value = "PugSave")
	@ElDescription(sub = "유저 그룹 매핑 정보를 다건 처리", desc = "프로젝트 유저 그룹 매핑 정보를 다건 처리한다.")
	public void savePug(PugListVo pugListVo) throws Exception {
		int cnt = pugListVo.getPugVoList().size();

		for (int i = 0; i < cnt; i++) {
			PugVo pugVo = pugListVo.getPugVoList().get(i);
			String rowStatus = pugVo.getRowStatus();

			switch (rowStatus) {
			case "C":
				pugService.insertPug(pugVo);
				break;
			case "U":
				pugService.updatePug(pugVo);
				break;
			case "D":
				pugService.deletePug(pugVo);
				break;
			default:
				// 예외처리 또는 무시
				break;
			}
		}
	}

	/**
	 * 사용자 정보를 조건에 따라 페이징 처리하여 목록을 조회한다.
	 *
	 * @param pugUserVo 사용자 정보 검색 조건 및 페이징 정보
	 * @return 사용자 정보 목록 및 페이징 정보
	 * @throws Exception
	 */
	@ElService(key = "PugUserList")
	@RequestMapping(value = "PugUserList")
	@ElDescription(sub = "사용자 정보 목록 조회", desc = "검색 조건 및 페이징 정보를 기반으로 사용자 목록을 조회한다.")
	public PugUserListVo selectListPugUser(PugUserVo pugUserVo) throws Exception {

		// 사용자 목록 조회
		List<PugUserVo> userList = pugService.selectListPugUser(pugUserVo);
		System.out.println(userList.size());
		// 사용자 목록 전체 건수 조회
		long totCnt = pugService.selectListCountPugUser(pugUserVo);
		System.out.println("================");
		System.out.println(totCnt);
		// 결과 객체 구성
		PugUserListVo retUserList = new PugUserListVo();
		retUserList.setPugUserVoList(userList);
		retUserList.setTotalCount(totCnt);
		retUserList.setPageSize(pugUserVo.getPageSize());
		retUserList.setPageIndex(pugUserVo.getPageIndex());

		return retUserList;
	}

}
