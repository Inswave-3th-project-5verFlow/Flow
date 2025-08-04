package com.demo.proworks.def.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.demo.proworks.att.service.AttService;
import com.demo.proworks.att.vo.AttListVo;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.cmmn.ProworksCommVO;
import com.demo.proworks.def.service.DefService;
import com.demo.proworks.def.vo.DefListVo;
import com.demo.proworks.def.vo.DefVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;

/**
 * @subject : 테스트결함관리 관련 처리를 담당하는 컨트롤러
 * @description : 테스트결함관리 관련 처리를 담당하는 컨트롤러
 * @author : 우민지
 * @since : 2025/07/23
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/23 우민지 최초 생성
 * 
 */
@Controller
public class DefController {

	@Resource(name = "defServiceImpl")
	private DefService defService;

	@Resource(name = "attServiceImpl")
	private AttService attService;


	/**
	 * 테스트결함관리 목록을 조회합니다.
	 */
	@ElService(key = "DEF001List")
	@RequestMapping(value = "DEF001List")
	@ElDescription(sub = "테스트결함관리 목록조회", desc = "페이징을 처리하여 테스트결함관리 목록 조회를 한다.")
	public DefListVo selectListDef(DefVo defVo) throws Exception {

		AppLog.debug("결함 목록 조회 요청: {}", defVo);

		List<DefVo> defList = defService.selectListDef(defVo);
		long totCnt = defService.selectListCountDef(defVo);

		DefListVo retDefList = new DefListVo();
		retDefList.setDefVoList(defList);
		retDefList.setTotalCount(totCnt);
		retDefList.setPageSize(defVo.getPageSize());
		retDefList.setPageIndex(defVo.getPageIndex());

		AppLog.debug("결함 목록 조회 완료: {} 건", defList.size());
		return retDefList;
	}

	/**
	 * 테스트결함관리을 단건 조회 처리 한다.
	 */
	@ElService(key = "DEF001UpdView")
	@RequestMapping(value = "DEF001UpdView")
	@ElDescription(sub = "테스트결함관리 갱신 폼을 위한 조회", desc = "테스트결함관리 갱신 폼을 위한 조회를 한다.")
	public DefVo selectDef(DefVo defVo) throws Exception {

		AppLog.debug("결함 상세 조회 요청: {}", defVo);

		DefVo selectDefVo = defService.selectDef(defVo);

		AppLog.debug("결함 상세 조회 완료: {}", (selectDefVo != null ? selectDefVo.getId() : "없음"));
		return selectDefVo;
	}
	
	/**
	 * 테스트결함관리 상세 조회 (UnitTest 패턴)
	 */
	@ElService(key = "DEF001Detail")
	@RequestMapping(value = "DEF001Detail")
	@ElDescription(sub = "테스트결함관리 상세 조회", desc = "결함 상세 정보를 조회한다.")
	public DefVo selectDefDetail(DefVo defVo) throws Exception {
		AppLog.debug("===== 컨트롤러 결함 상세 조회 시작 =====");
		AppLog.debug("요청 파라미터: {}", defVo);
		AppLog.debug("요청 결함 ID: {}", defVo.getId());

		DefVo selectDefVo = defService.selectDefDetail(defVo);

		
		AppLog.debug("===== 컨트롤러 결함 상세 조회 완료 =====");
		return selectDefVo;
	}

	/**
	 * 테스트결함관리를 등록 처리 한다. 
	 */
	@ElService(key = "DEF001Ins")
	@RequestMapping(value = "DEF001Ins")
	@ElDescription(sub = "테스트결함관리 등록처리", desc = "테스트결함관리를 등록 처리 한다.")
	public void insertDef(DefVo defVo) throws Exception {

		AppLog.debug("결함 등록 요청: {}", defVo);

		String defectId = defService.insertDef(defVo);

		AppLog.debug("결함 등록 완료: {}", defectId);
	}

	/**
	 * 테스트결함관리를 갱신 처리 한다. (단순 수정)
	 */
	@ElService(key = "DEF001Upd")
	@RequestMapping(value = "DEF001Upd")
	@ElDescription(sub = "테스트결함관리 갱신처리", desc = "테스트결함관리를 갱신 처리 한다.")
	public void updateDef(DefVo defVo) throws Exception {

		AppLog.debug("결함 수정 요청: {}", defVo);

		defService.updateDef(defVo);

		AppLog.debug("결함 수정 완료: {}", defVo.getId());
	}

	/**
	 * 테스트결함관리를 삭제 처리한다.
	 */
	@ElService(key = "DEF001Del")
	@RequestMapping(value = "DEF001Del")
	@ElDescription(sub = "테스트결함관리 삭제처리", desc = "테스트결함관리를 삭제 처리한다.")
	public void deleteDef(DefVo defVo) throws Exception {

		AppLog.debug("결함 삭제 요청: {}", defVo);

		defService.deleteDef(defVo);

		AppLog.debug("결함 삭제 완료: {}", defVo.getId());
	}


	/**
	 * 결함을 파일과 함께 등록 처리 한다. (트랜잭션)
	 */
	@ElService(key = "DEF001InsWithFiles")
	@RequestMapping(value = "DEF001InsWithFiles")
	@ElDescription(sub = "결함 파일 등록처리", desc = "결함을 파일과 함께 등록 처리 한다.")
	public void insertDefWithFiles(HttpServletRequest request) throws Exception {

		AppLog.debug("=== 결함 파일 등록 시작 ===");

		try {
			// MultipartHttpServletRequest로 캐스팅
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

			// 1. 결함 정보 세팅
			DefVo defVo = new DefVo();
			defVo.setTestId(request.getParameter("testId"));
			defVo.setTestName(request.getParameter("testName"));
			defVo.setName(request.getParameter("name"));
			defVo.setDescription(request.getParameter("description"));
			defVo.setPriority(request.getParameter("priority"));
			defVo.setStatus(request.getParameter("status"));
			defVo.setAssignee(request.getParameter("assignee"));
			defVo.setFixDueDate(request.getParameter("fixDueDate"));
			defVo.setRemarks(request.getParameter("remarks"));
			defVo.setPjtId(request.getParameter("pjtId"));

			AppLog.debug("결함 정보: {}", defVo);

			// 2. 파일 추출
			List<MultipartFile> files = multipartRequest.getFiles("files");
			MultipartFile[] fileArray = files.toArray(new MultipartFile[0]);

			AppLog.debug("업로드된 파일 개수: {}", files.size());

			// 3. 서비스 호출
			DefVo result = defService.insertDefWithFiles(defVo, fileArray);

			AppLog.debug("파일과 함께 결함 등록 완료: {}", result.getId());

		} catch (Exception e) {
			AppLog.error("파일과 함께 결함 등록 실패: {}", e.getMessage());
			throw new ElException("결함 등록 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 결함을 파일과 함께 갱신 처리 한다. (트랜잭션)
	 */
	@ElService(key = "DEF001UpdWithFiles")
	@RequestMapping(value = "DEF001UpdWithFiles")
	@ElDescription(sub = "결함 파일 갱신처리", desc = "결함을 파일과 함께 갱신 처리 한다.")
	public void updateDefWithFiles(HttpServletRequest request) throws Exception {

		AppLog.debug("=== 결함 파일 수정 시작 ===");

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

			DefVo defVo = new DefVo();
			defVo.setId(request.getParameter("id"));
			defVo.setTestId(request.getParameter("testId"));
			defVo.setTestName(request.getParameter("testName"));
			defVo.setName(request.getParameter("name"));
			defVo.setDescription(request.getParameter("description"));
			defVo.setPriority(request.getParameter("priority"));
			defVo.setStatus(request.getParameter("status"));
			defVo.setAssignee(request.getParameter("assignee"));
			defVo.setFixDueDate(request.getParameter("fixDueDate"));
			defVo.setRemarks(request.getParameter("remarks"));
			defVo.setPjtId(request.getParameter("pjtId"));

			AppLog.debug("수정할 결함 정보: {}", defVo);
			AppLog.debug("결함 ID: {}", defVo.getId());

			if (defVo.getId() == null || defVo.getId().trim().isEmpty()) {
				throw new RuntimeException("수정할 결함 ID가 필요합니다.");
			}

			List<MultipartFile> fileList = multipartRequest.getFiles("files");
			AppLog.debug("받은 파일 개수: {}", (fileList != null ? fileList.size() : 0));

			List<MultipartFile> validFiles = new ArrayList<>();
			if (fileList != null) {
				for (MultipartFile file : fileList) {
					if (file != null && !file.isEmpty()) {
						validFiles.add(file);
					}
				}
			}

			MultipartFile[] files = validFiles.toArray(new MultipartFile[0]);

			DefVo result = defService.updateDefWithFiles(defVo, files);

			AppLog.debug("파일과 함께 결함 수정 완료: {}", result.getId());

		} catch (Exception e) {
			AppLog.error("파일과 함께 결함 수정 실패: {}", e.getMessage());
			throw new ElException("결함 수정 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 결함 파일 목록 조회
	 */
	@ElService(key = "DEF001FileList")
	@RequestMapping(value = "DEF001FileList")
	@ElDescription(sub = "결함 파일 목록 조회", desc = "결함의 파일 목록을 조회한다.")
	public AttListVo getDefectFileList(DefVo defVo) throws Exception {

		AppLog.debug("=== 결함 파일 목록 조회 ===");
		AppLog.debug("요청 데이터: {}", defVo != null ? defVo.toString() : "null");

		if (defVo == null) {
			AppLog.warn("defVo가 null입니다.");
			AttListVo emptyResult = new AttListVo();
			emptyResult.setAttVoList(new ArrayList<>());
			return emptyResult;
		}

		// 결함 ID 체크
		if (defVo.getId() == null || defVo.getId().trim().isEmpty()) {
			AppLog.warn("결함 ID가 없습니다.");
			AttListVo emptyResult = new AttListVo();
			emptyResult.setAttVoList(new ArrayList<>());
			return emptyResult;
		}

		ProworksCommVO fileParam = new ProworksCommVO();
		fileParam.setRefType("DEFECT"); 
		fileParam.setRefId(defVo.getId());


		List<AttVo> attList = attService.getFileList(fileParam);
		AppLog.debug("조회된 파일 개수: {}", (attList != null ? attList.size() : 0));

		AttListVo retAttList = new AttListVo();
		retAttList.setAttVoList(attList != null ? attList : new ArrayList<>());

		AppLog.debug("=== 결함 파일 목록 조회 완료 ===");
		return retAttList;
	}

	/**
	 * 결함 파일 삭제
	 */
	@ElService(key = "DEF001FileDelete")
	@RequestMapping(value = "DEF001FileDelete")
	@ElDescription(sub = "결함 파일 삭제", desc = "결함의 파일을 삭제한다.")
	@ResponseBody
	public void deleteDefectFile(@RequestParam("fileId") String fileId) throws Exception {
		AppLog.debug("=== 결함 파일 삭제 ===");
		AppLog.debug("파일 ID: {}", fileId);

		if (fileId == null || fileId.trim().isEmpty()) {
			throw new ElException("파일 ID가 필요합니다.");
		}

		try {
			int result = defService.deleteDefFile(fileId);

			if (result <= 0) {
				throw new ElException("파일 삭제에 실패했습니다.");
			}

			AppLog.debug("결함 파일 삭제 성공");
		} catch (Exception e) {
			AppLog.error("결함 파일 삭제 실패: {}", e.getMessage());
			throw new ElException("파일 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 결함 파일 업로드 (별도)
	 */
	@ElService(key = "DEF001FileUpload")
	@RequestMapping(value = "DEF001FileUpload")
	@ElDescription(sub = "결함 파일 업로드", desc = "결함에 파일을 업로드한다.")
	@ResponseBody
	public List<AttVo> uploadDefectFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("defectId") String defectId) throws Exception {

		AppLog.debug("=== 결함 별도 파일 업로드 ===");
		AppLog.debug("결함 ID: {}", defectId);
		AppLog.debug("파일 개수: {}", files.length);

		try {
			// AttService에 위임
			return attService.uploadFiles(files, "DEFECT", defectId);
		} catch (Exception e) {
			AppLog.error("결함 파일 업로드 실패: {}", e.getMessage());
			throw new ElException("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
		}
	}


	/**
	 * 결함 상태 업데이트 (완료 시 관련 테스트 상태도 업데이트)
	 */
	@ElService(key = "DEF001UpdateStatus")
	@RequestMapping(value = "DEF001UpdateStatus")
	@ElDescription(sub = "결함 상태 업데이트", desc = "결함 상태를 업데이트하고 완료 시 관련 테스트 상태도 업데이트한다.")
	public void updateDefectStatus(DefVo defVo) throws Exception {

		AppLog.debug("결함 상태 업데이트 요청: {}", defVo);

		defService.updateDefectStatus(defVo);

		AppLog.debug("결함 상태 업데이트 완료: {}", defVo.getId());
	}

	/**
	 * 결함 상태 업데이트 (단순)
	 */
	@ElService(key = "DEF001UpdateStatusSimple")
	@RequestMapping(value = "DEF001UpdateStatusSimple")
	@ElDescription(sub = "결함 상태 단순 업데이트", desc = "결함 상태를 단순하게 업데이트한다.")
	public void updateDefStatus(DefVo defVo) throws Exception {

		AppLog.debug("결함 상태 단순 업데이트 요청: {}", defVo);

		int result = defService.updateDefStatus(defVo);

		if (result <= 0) {
			throw new Exception("상태 업데이트에 실패했습니다.");
		}

		AppLog.debug("결함 상태 단순 업데이트 완료: {}", defVo.getId());
	}

	/**
	 * 결함 우선순위 업데이트
	 */
	@ElService(key = "DEF001UpdatePriority")
	@RequestMapping(value = "DEF001UpdatePriority")
	@ElDescription(sub = "결함 우선순위 업데이트", desc = "결함 우선순위를 업데이트한다.")
	public void updateDefPriority(DefVo defVo) throws Exception {

		AppLog.debug("결함 우선순위 업데이트 요청: {}", defVo);

		int result = defService.updateDefPriority(defVo);

		if (result <= 0) {
			throw new Exception("우선순위 업데이트에 실패했습니다.");
		}

		AppLog.debug("결함 우선순위 업데이트 완료: {}", defVo.getId());
	}

	/**
	 * 결함 담당자 변경
	 */
	@ElService(key = "DEF001UpdateAssignee")
	@RequestMapping(value = "DEF001UpdateAssignee")
	@ElDescription(sub = "결함 담당자 변경", desc = "결함 담당자를 변경한다.")
	public void updateDefAssignee(DefVo defVo) throws Exception {

		AppLog.debug("결함 담당자 변경 요청: {}", defVo);

		int result = defService.updateDefAssignee(defVo);

		if (result <= 0) {
			throw new Exception("담당자 변경에 실패했습니다.");
		}

		AppLog.debug("결함 담당자 변경 완료: {}", defVo.getId());
	}


	/**
	 * 결함 통계 조회
	 */
	@ElService(key = "DEF001Statistics")
	@RequestMapping(value = "DEF001Statistics")
	@ElDescription(sub = "결함 통계 조회", desc = "결함 상태별 통계를 조회한다.")
	public Map<String, Object> selectDefectStatistics(DefVo defVo) throws Exception {

		AppLog.debug("결함 통계 조회 요청: {}", defVo);

		Map<String, Object> statistics = defService.selectDefectStatistics(defVo);

		AppLog.debug("결함 통계 조회 완료: {}", statistics);

		return statistics;
	}

	/**
	 * 프로젝트별 결함 통계 조회
	 */
	@ElService(key = "DEF001StatisticsByProject")
	@RequestMapping(value = "DEF001StatisticsByProject")
	@ElDescription(sub = "프로젝트별 결함 통계 조회", desc = "프로젝트별 결함 통계를 조회한다.")
	public Map<String, Object> selectDefectStatisticsByProject(DefVo defVo) throws Exception {
		AppLog.debug("프로젝트별 결함 통계 조회 요청: {}", defVo);

		Map<String, Object> statistics = defService.selectDefectStatisticsByProject(defVo);

		AppLog.debug("프로젝트별 결함 통계 조회 완료: {}", statistics);
		return statistics;
	}

	/**
	 * 수정 기한 임박 결함 목록 조회
	 */
	@ElService(key = "DEF001Upcoming")
	@RequestMapping(value = "DEF001Upcoming")
	@ElDescription(sub = "임박 결함 목록 조회", desc = "수정 기한이 임박한 결함 목록을 조회한다.")
	public DefListVo selectUpcomingDefects(DefVo defVo) throws Exception {

		AppLog.debug("임박 결함 목록 조회 요청: {}", defVo);

		List<DefVo> upcomingList = defService.selectUpcomingDefects(defVo);

		DefListVo retDefList = new DefListVo();
		retDefList.setDefVoList(upcomingList);
		retDefList.setTotalCount(upcomingList.size());

		AppLog.debug("임박 결함 목록 조회 완료: {} 건", upcomingList.size());

		return retDefList;
	}

	/**
	 * 상태별 결함 통계 조회
	 */
	@ElService(key = "DEF001StatsByStatus")
	@RequestMapping(value = "DEF001StatsByStatus")
	@ElDescription(sub = "상태별 결함 통계 조회", desc = "상태별 결함 통계를 조회한다.")
	public List<Map<String, Object>> selectDefectStatsByStatus(DefVo defVo) throws Exception {

		AppLog.debug("상태별 결함 통계 조회 요청: {}", defVo);

		List<Map<String, Object>> statusStats = defService.selectDefectStatsByStatus(defVo);

		AppLog.debug("상태별 결함 통계 조회 완료: {} 건", statusStats.size());

		return statusStats;
	}

	/**
	 * 우선순위별 결함 통계 조회
	 */
	@ElService(key = "DEF001StatsByPriority")
	@RequestMapping(value = "DEF001StatsByPriority")
	@ElDescription(sub = "우선순위별 결함 통계 조회", desc = "우선순위별 결함 통계를 조회한다.")
	public List<Map<String, Object>> selectDefectStatsByPriority(DefVo defVo) throws Exception {

		AppLog.debug("우선순위별 결함 통계 조회 요청: {}", defVo);

		List<Map<String, Object>> priorityStats = defService.selectDefectStatsByPriority(defVo);

		AppLog.debug("우선순위별 결함 통계 조회 완료: {} 건", priorityStats.size());

		return priorityStats;
	}


	/**
	 * 프로젝트별 결함 목록 조회
	 */
	@ElService(key = "DEF001ListByProject")
	@RequestMapping(value = "DEF001ListByProject")
	@ElDescription(sub = "프로젝트별 결함 목록 조회", desc = "프로젝트별 결함 목록을 조회한다.")
	public DefListVo selectDefectListByProject(DefVo defVo) throws Exception {
		AppLog.debug("프로젝트별 결함 목록 조회 요청: {}", defVo);

		List<DefVo> defList = defService.selectListDef(defVo);
		long totCnt = defService.selectListCountDef(defVo);

		DefListVo retDefList = new DefListVo();
		retDefList.setDefVoList(defList);
		retDefList.setTotalCount(totCnt);
		retDefList.setPageSize(defVo.getPageSize());
		retDefList.setPageIndex(defVo.getPageIndex());

		AppLog.debug("프로젝트별 결함 목록 조회 완료: {} 건", defList.size());
		return retDefList;
	}

	/**
	 * 테스트 케이스 연관 결함 목록 조회
	 */
	@ElService(key = "DEF001ListByTestId")
	@RequestMapping(value = "DEF001ListByTestId")
	@ElDescription(sub = "테스트 케이스 연관 결함 목록 조회", desc = "특정 테스트 케이스와 연관된 결함 목록을 조회한다.")
	public DefListVo selectDefectsByTestId(DefVo defVo) throws Exception {

		AppLog.debug("테스트 케이스 연관 결함 조회 요청: {}", defVo);

		List<DefVo> defList = defService.selectDefectsByTestId(defVo);

		DefListVo retDefList = new DefListVo();
		retDefList.setDefVoList(defList);
		retDefList.setTotalCount(defList.size());

		AppLog.debug("테스트 케이스 연관 결함 조회 완료: {} 건", defList.size());

		return retDefList;
	}

	/**
	 * 미완료 결함 목록 조회
	 */
	@ElService(key = "DEF001IncompleteList")
	@RequestMapping(value = "DEF001IncompleteList")
	@ElDescription(sub = "미완료 결함 목록 조회", desc = "미완료 상태(대기+진행중)의 결함 목록을 조회한다.")
	public DefListVo selectIncompleteDefects(DefVo defVo) throws Exception {

		AppLog.debug("미완료 결함 목록 조회 요청: {}", defVo);

		List<DefVo> incompleteList = defService.selectIncompleteDefects(defVo);

		DefListVo retDefList = new DefListVo();
		retDefList.setDefVoList(incompleteList);
		retDefList.setTotalCount(incompleteList.size());

		AppLog.debug("미완료 결함 목록 조회 완료: {} 건", incompleteList.size());

		return retDefList;
	}

	/**
	 * 담당자별 결함 목록 조회
	 */
	@ElService(key = "DEF001ListByAssignee")
	@RequestMapping(value = "DEF001ListByAssignee")
	@ElDescription(sub = "담당자별 결함 목록 조회", desc = "특정 담당자의 결함 목록을 조회한다.")
	public DefListVo selectDefectsByAssignee(DefVo defVo) throws Exception {

		AppLog.debug("담당자별 결함 조회 요청: {}", defVo);

		List<DefVo> defList = defService.selectDefectsByAssignee(defVo);

		DefListVo retDefList = new DefListVo();
		retDefList.setDefVoList(defList);
		retDefList.setTotalCount(defList.size());

		AppLog.debug("담당자별 결함 조회 완료: {} 건", defList.size());

		return retDefList;
	}


	/**
	 * 여러 결함의 상태를 일괄 업데이트
	 */
	@ElService(key = "DEF001UpdateMultipleStatus")
	@RequestMapping(value = "DEF001UpdateMultipleStatus")
	@ElDescription(sub = "일괄 상태 업데이트", desc = "여러 결함의 상태를 일괄 업데이트한다.")
	@ResponseBody
	public Map<String, Object> updateMultipleDefStatus(@RequestParam("defectIds") List<String> defectIds,
			@RequestParam("newStatus") String newStatus) throws Exception {


		Map<String, Object> result = new HashMap<>();

		try {
			int updatedCount = defService.updateMultipleDefStatus(defectIds, newStatus);

			result.put("success", true);
			result.put("message", "일괄 상태 업데이트 완료");
			result.put("totalCount", defectIds.size());
			result.put("updatedCount", updatedCount);


		} catch (Exception e) {
			AppLog.error("일괄 상태 업데이트 실패: {}", e.getMessage());

			result.put("success", false);
			result.put("message", "일괄 상태 업데이트 실패: " + e.getMessage());
			result.put("error", e.getMessage());
		}

		return result;
	}


	/**
	 * 결함 ID 중복 체크
	 */
	@ElService(key = "DEF001CheckDuplicate")
	@RequestMapping(value = "DEF001CheckDuplicate")
	@ElDescription(sub = "결함 ID 중복 체크", desc = "결함 ID의 중복 여부를 확인한다.")
	@ResponseBody
	public Map<String, Object> checkDuplicateDefectId(DefVo defVo) throws Exception {

		AppLog.debug("결함 ID 중복 체크: {}", defVo.getId());

		Map<String, Object> result = new HashMap<>();

		try {
			int duplicateCount = defService.checkDuplicateDefectId(defVo);

			result.put("isDuplicate", duplicateCount > 0);
			result.put("count", duplicateCount);
			result.put("message", duplicateCount > 0 ? "이미 존재하는 결함 ID입니다." : "사용 가능한 결함 ID입니다.");


		} catch (Exception e) {
			AppLog.error("결함 ID 중복 체크 실패: {}", e.getMessage());

			result.put("isDuplicate", false);
			result.put("error", e.getMessage());
		}

		return result;
	}

	/**
	 * 결함 존재 여부 확인
	 */
	@ElService(key = "DEF001CheckExists")
	@RequestMapping(value = "DEF001CheckExists")
	@ElDescription(sub = "결함 존재 여부 확인", desc = "결함의 존재 여부를 확인한다.")
	@ResponseBody
	public Map<String, Object> checkDefectExists(@RequestParam("defectId") String defectId) throws Exception {

		AppLog.debug("결함 존재 여부 확인: {}", defectId);

		Map<String, Object> result = new HashMap<>();

		try {
			boolean exists = defService.isDefectExists(defectId);

			result.put("exists", exists);
			result.put("message", exists ? "결함이 존재합니다." : "결함을 찾을 수 없습니다.");

//			AppLog.debug("결함 존재 여부 확인 완료: {} (존재: {})", defectId, exists);

		} catch (Exception e) {
			AppLog.error("결함 존재 여부 확인 실패: {}", e.getMessage());

			result.put("exists", false);
			result.put("error", e.getMessage());
		}

		return result;
	}

	/**
	 * 테스트 케이스 연관 결함 존재 여부 확인
	 */
	@ElService(key = "DEF001CheckRelatedDefects")
	@RequestMapping(value = "DEF001CheckRelatedDefects")
	@ElDescription(sub = "테스트 케이스 연관 결함 존재 여부 확인", desc = "테스트 케이스에 연관된 결함의 존재 여부를 확인한다.")
	@ResponseBody
	public Map<String, Object> checkRelatedDefects(@RequestParam("testId") String testId) throws Exception {

		AppLog.debug("테스트 케이스 연관 결함 존재 여부 확인: {}", testId);

		Map<String, Object> result = new HashMap<>();

		try {
			boolean hasRelated = defService.hasRelatedDefects(testId);

			result.put("hasRelatedDefects", hasRelated);
			result.put("message", hasRelated ? "연관된 결함이 존재합니다." : "연관된 결함이 없습니다.");

//			AppLog.debug("테스트 케이스 연관 결함 확인 완료: {} (연관 결함 존재: {})", testId, hasRelated);

		} catch (Exception e) {
			AppLog.error("테스트 케이스 연관 결함 확인 실패: {}", e.getMessage());

			result.put("hasRelatedDefects", false);
			result.put("error", e.getMessage());
		}

		return result;
	}


	/**
	 * 결함을 파일과 함께 안전하게 삭제
	 */
	@ElService(key = "DEF001DelWithFiles")
	@RequestMapping(value = "DEF001DelWithFiles")
	@ElDescription(sub = "결함 파일 포함 삭제", desc = "결함과 관련 파일을 안전하게 삭제한다.")
	public void deleteDefWithFiles(DefVo defVo) throws Exception {
		AppLog.debug("=== 컨트롤러: 결함 + 파일 삭제 시작 ===");

		String defectId = defVo.getId();

		if (defectId == null || defectId.trim().isEmpty()) {
			throw new RuntimeException("삭제할 결함 ID가 필요합니다.");
		}

		try {
			DefVo existingDef = defService.selectDef(defVo);
			if (existingDef == null) {
				throw new RuntimeException("해당 결함을 찾을 수 없습니다.");
			}

			AppLog.debug("삭제 대상: {}", existingDef.getName());

			int deleteResult = defService.deleteDef(defVo);

			if (deleteResult <= 0) {
				throw new RuntimeException("결함 삭제에 실패했습니다.");
			}

			AppLog.debug("결함 삭제 성공: {}", defectId);

		} catch (Exception e) {
			AppLog.error("결함 삭제 중 오류: {}", e.getMessage());
			throw new RuntimeException("삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 결함 단순 삭제 (트랜잭션 문제 방지)
	 */
	@ElService(key = "DEF001DelSimple")
	@RequestMapping(value = "DEF001DelSimple")
	@ElDescription(sub = "결함 단순 삭제", desc = "트랜잭션 문제 없이 단순하게 삭제한다.")
	public void deleteDefSimple(DefVo defVo) throws Exception {
		AppLog.debug("=== 컨트롤러: 결함 단순 삭제 ===");

		String defectId = defVo.getId();
		if (defectId == null || defectId.trim().isEmpty()) {
			throw new RuntimeException("삭제할 결함 ID가 필요합니다.");
		}

		try {
			try {
				AppLog.debug("관련 파일 삭제 시도: {}", defectId);
				attService.deleteFilesByRef("DEFECT", defectId);
				AppLog.debug("관련 파일 삭제 완료");
			} catch (Exception fileException) {
				AppLog.warn("파일 삭제 실패 (무시하고 계속): {}", fileException.getMessage());
			}

			int result = defService.deleteDef(defVo);

			if (result <= 0) {
				throw new RuntimeException("결함 삭제에 실패했습니다.");
			}

			AppLog.debug("결함 삭제 완료: {}", defectId);

		} catch (Exception e) {
			AppLog.error("결함 삭제 실패: {}", e.getMessage());
			throw new RuntimeException("결함 삭제 실패: " + e.getMessage());
		}
	}
}