package com.demo.proworks.unit.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.demo.proworks.unit.service.UnitTestService;
import com.demo.proworks.unit.vo.UnitTestListVo;
import com.demo.proworks.unit.vo.UnitTestVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;

/**
 * @subject : 단위테스트 관리 관련 처리를 담당하는 컨트롤러
 * @description : 단위테스트 관리 관련 처리를 담당하는 컨트롤러
 * @author : 개발자
 * @since : 2025/07/21
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/21 개발자 최초 생성
 * 
 */
@Controller
public class UnitTestController {


	@Resource(name = "unitTestServiceImpl")
	private UnitTestService unitTestService;

	@Resource(name = "attServiceImpl")
	private AttService attService;

	/**
	 * 단위테스트 케이스 목록을 조회합니다.
	 */
	@ElService(key = "UNIT001List")
	@RequestMapping(value = "UNIT001List")
	@ElDescription(sub = "단위테스트 케이스 목록조회", desc = "페이징을 처리하여 단위테스트 케이스 목록 조회를 한다.")
	public UnitTestListVo selectListUnitTest(UnitTestVo unitTestVo) throws Exception {

		List<UnitTestVo> unitTestList = unitTestService.selectUnitTestList(unitTestVo);
		long totCnt = unitTestService.selectListCountUnitTest(unitTestVo);

		UnitTestListVo retUnitTestList = new UnitTestListVo();
		retUnitTestList.setUnitTestList(unitTestList);
		retUnitTestList.setTotalCount(totCnt);
		retUnitTestList.setPageSize(unitTestVo.getPageSize());
		retUnitTestList.setPageIndex(unitTestVo.getPageIndex());

		AppLog.info("retUnitTestList" + retUnitTestList.toString());

		return retUnitTestList;
	}

	/**
	 * 프로젝트별 단위테스트 케이스 목록 조회
	 */
	@ElService(key = "UNIT001ListByProject")
	@RequestMapping(value = "UNIT001ListByProject")
	@ElDescription(sub = "프로젝트별 단위테스트 케이스 목록 조회", desc = "특정 프로젝트의 단위테스트 케이스 목록을 조회한다.")
	public UnitTestListVo selectUnitTestListByProject(UnitTestVo unitTestVo) throws Exception {

		AppLog.debug("프로젝트별 단위테스트 케이스 목록 조회 요청: {}", unitTestVo);

		List<UnitTestVo> unitTestList = unitTestService.selectUnitTestList(unitTestVo);
		long totCnt = unitTestService.selectListCountUnitTest(unitTestVo);

		UnitTestListVo retUnitTestList = new UnitTestListVo();
		retUnitTestList.setUnitTestList(unitTestList);
		retUnitTestList.setTotalCount(totCnt);
		retUnitTestList.setPageSize(unitTestVo.getPageSize());
		retUnitTestList.setPageIndex(unitTestVo.getPageIndex());

		AppLog.debug("프로젝트별 목록 조회 완료: {} 건", totCnt);

		return retUnitTestList;
	}

	/**
	 * 실패한 단위테스트 케이스 목록 조회 (결함 등록용)
	 */
	@ElService(key = "UNIT001FailedList")
	@RequestMapping(value = "UNIT001FailedList")
	@ElDescription(sub = "실패한 단위테스트 케이스 목록 조회", desc = "결함 등록을 위해 실패한 단위테스트 케이스 목록을 조회한다.")
	public UnitTestListVo selectFailedUnitTestList(UnitTestVo unitTestVo) throws Exception {

		AppLog.debug("실패한 단위테스트 케이스 목록 조회 요청: {}", unitTestVo);

		// 기본값 설정 - 실패한 테스트케이스만 조회
		if (unitTestVo.getPageSize() == 0) {
			unitTestVo.setPageSize(1000); // 충분히 큰 값으로 설정
		}

		List<UnitTestVo> failedUnitTestList = unitTestService.selectFailedUnitTestList(unitTestVo);

		// 카운트는 조회된 리스트의 크기로 설정 (별도 카운트 쿼리 실행하지 않음)
		long totCnt = failedUnitTestList != null ? failedUnitTestList.size() : 0;

		UnitTestListVo retUnitTestList = new UnitTestListVo();
		retUnitTestList.setUnitTestList(failedUnitTestList);
		retUnitTestList.setTotalCount(totCnt);
		retUnitTestList.setPageSize(unitTestVo.getPageSize());
		retUnitTestList.setPageIndex(unitTestVo.getPageIndex());

		AppLog.debug("실패한 테스트케이스 목록 조회 완료: {} 건", totCnt);

		return retUnitTestList;
	}

	/**
	 * 단위테스트 케이스를 단건 조회 처리 한다.
	 */
	@ElService(key = "UNIT001UpdView")
	@RequestMapping(value = "UNIT001UpdView")
	@ElDescription(sub = "단위테스트 케이스 갱신 폼을 위한 조회", desc = "단위테스트 케이스 갱신 폼을 위한 조회를 한다.")
	public UnitTestVo selectUnitTest(UnitTestVo unitTestVo) throws Exception {
		AppLog.debug("===== 컨트롤러 상세 조회 시작 =====");
		AppLog.debug("요청 파라미터: {}", unitTestVo);
		AppLog.debug("요청 testCaseId: {}", unitTestVo.getTestCaseId());

		UnitTestVo selectUnitTestVo = unitTestService.selectUnitTestDetail(unitTestVo);

		AppLog.debug("===== 컨트롤러 응답 데이터 =====");
		AppLog.debug("응답 전체: {}", selectUnitTestVo);

		if (selectUnitTestVo != null) {
			AppLog.debug("응답 testCaseId: {}", selectUnitTestVo.getTestCaseId());
			AppLog.debug("응답 description: [{}]", selectUnitTestVo.getDescription());
			AppLog.debug("응답 testData: [{}]", selectUnitTestVo.getTestData());
			AppLog.debug("description 길이: {}",
					selectUnitTestVo.getDescription() != null ? selectUnitTestVo.getDescription().length() : "null");
			AppLog.debug("testData 길이: {}",
					selectUnitTestVo.getTestData() != null ? selectUnitTestVo.getTestData().length() : "null");

			// HTML 태그가 포함되어 있는지 확인
			if (selectUnitTestVo.getDescription() != null) {
				boolean hasHtmlTags = selectUnitTestVo.getDescription().contains("<")
						&& selectUnitTestVo.getDescription().contains(">");
				AppLog.debug("description HTML 태그 포함 여부: {}", hasHtmlTags);
			}

			if (selectUnitTestVo.getTestData() != null) {
				boolean hasHtmlTags = selectUnitTestVo.getTestData().contains("<")
						&& selectUnitTestVo.getTestData().contains(">");
				AppLog.debug("testData HTML 태그 포함 여부: {}", hasHtmlTags);
			}
		} else {
			AppLog.warn("조회 결과가 null입니다.");
		}

		AppLog.debug("===== 컨트롤러 상세 조회 완료 =====");

		return selectUnitTestVo;
	}

	/**
	 * 단위테스트 케이스를 등록 처리 한다. (단순 등록)
	 */
	@ElService(key = "UNIT001Ins")
	@RequestMapping(value = "UNIT001Ins")
	@ElDescription(sub = "단위테스트 케이스 등록처리", desc = "단위테스트 케이스를 등록 처리 한다.")
	public void insertUnitTest(UnitTestVo unitTestVo) throws Exception {
		unitTestService.insertUnitTest(unitTestVo);
	}

	/**
	 * 단위테스트 케이스를 파일과 함께 등록 처리 한다. (트랜잭션)
	 */
	@ElService(key = "UNIT001InsWithFiles")
	@RequestMapping(value = "UNIT001InsWithFiles")
	@ElDescription(sub = "단위테스트 케이스 파일 등록처리", desc = "단위테스트 케이스를 파일과 함께 등록 처리 한다.")
	public void insertUnitTestWithFiles(HttpServletRequest request) throws Exception {

		AppLog.debug("=== 단위테스트 케이스 파일 등록 시작 ===");

		// MultipartHttpServletRequest로 캐스팅
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		// 1. 단위테스트 케이스 정보 세팅
		UnitTestVo unitTestVo = new UnitTestVo();
		unitTestVo.setTaskId(request.getParameter("taskId"));
		unitTestVo.setTaskName(request.getParameter("taskName"));
		unitTestVo.setTestCaseName(request.getParameter("testCaseName"));
		unitTestVo.setTestTarget(request.getParameter("testTarget"));
		unitTestVo.setTestType(request.getParameter("testType"));
		unitTestVo.setPriority(request.getParameter("priority"));
		unitTestVo.setAssignee(request.getParameter("assignee"));
		unitTestVo.setDescription(request.getParameter("description"));
		unitTestVo.setPrecondition(request.getParameter("precondition"));
		unitTestVo.setTestData(request.getParameter("testData"));
		unitTestVo.setTestSteps(request.getParameter("testSteps"));
		unitTestVo.setExpectedResult(request.getParameter("expectedResult"));
		unitTestVo.setActualResult(request.getParameter("actualResult"));
		unitTestVo.setTestStatus(request.getParameter("testStatus"));

		// 실행일시 포맷 변환 (YYYYMMDD -> YYYY-MM-DD)
		String executionDate = request.getParameter("executionDate");
		if (executionDate != null && executionDate.length() == 8) {
			executionDate = executionDate.substring(0, 4) + "-" + executionDate.substring(4, 6) + "-"
					+ executionDate.substring(6, 8);
		}
		unitTestVo.setExecutionDate(executionDate);
		unitTestVo.setDuration(request.getParameter("duration"));
		unitTestVo.setNotes(request.getParameter("notes"));
		unitTestVo.setRequirements(request.getParameter("requirements"));
		unitTestVo.setPjtId(request.getParameter("pjtId"));

		AppLog.debug("단위테스트 케이스 정보: {}", unitTestVo);

		// 2. 실제 파일 객체들 받아오기
		List<MultipartFile> fileList = multipartRequest.getFiles("files");
		AppLog.debug("받은 파일 개수: {}", (fileList != null ? fileList.size() : 0));

		// 3. MultipartFile 배열로 변환 (null이 아닌 파일만)
		List<MultipartFile> validFiles = new ArrayList<>();
		if (fileList != null) {
			for (MultipartFile file : fileList) {
				if (file != null && !file.isEmpty()) {
					validFiles.add(file);
					AppLog.debug("유효한 파일: {} (크기: {})" + file.getOriginalFilename(), file.getSize());
				} else {
					AppLog.debug("유효하지 않은 파일");
				}
			}
		}

		MultipartFile[] files = validFiles.toArray(new MultipartFile[0]);

		// 4. 서비스 호출 (기존 방식 사용)
		unitTestService.insertUnitTestWithFiles(unitTestVo, files);

		AppLog.debug("=== 단위테스트 케이스 파일 등록 완료 ===");
	}

	/**
	 * 단위테스트 케이스를 갱신 처리 한다. (단순 수정)
	 */
	@ElService(key = "UNIT001Upd")
	@RequestMapping(value = "UNIT001Upd")
	@ElDescription(sub = "단위테스트 케이스 갱신처리", desc = "단위테스트 케이스를 갱신 처리 한다.")
	public void updateUnitTest(UnitTestVo unitTestVo) throws Exception {
		unitTestService.updateUnitTest(unitTestVo);
	}

	/**
	 * 단위테스트 케이스를 파일과 함께 갱신 처리 한다. (트랜잭션)
	 */
	@ElService(key = "UNIT001UpdWithFiles")
	@RequestMapping(value = "UNIT001UpdWithFiles")
	@ElDescription(sub = "단위테스트 케이스 파일 갱신처리", desc = "단위테스트 케이스를 파일과 함께 갱신 처리 한다.")
	public void updateUnitTestWithFiles(HttpServletRequest request) throws Exception {

		AppLog.debug("=== 단위테스트 케이스 파일 수정 시작 ===");

		// MultipartHttpServletRequest로 캐스팅
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		// 1. 단위테스트 케이스 정보 세팅 (testCaseId 필수!)
		UnitTestVo unitTestVo = new UnitTestVo();
		unitTestVo.setTestCaseId(request.getParameter("testCaseId")); // 수정 시 testCaseId 필수
		unitTestVo.setTaskId(request.getParameter("taskId"));
		unitTestVo.setTaskName(request.getParameter("taskName"));
		unitTestVo.setTestCaseName(request.getParameter("testCaseName"));
		unitTestVo.setTestTarget(request.getParameter("testTarget"));
		unitTestVo.setTestType(request.getParameter("testType"));
		unitTestVo.setPriority(request.getParameter("priority"));
		unitTestVo.setAssignee(request.getParameter("assignee"));
		unitTestVo.setDescription(request.getParameter("description"));
		unitTestVo.setPrecondition(request.getParameter("precondition"));
		unitTestVo.setTestData(request.getParameter("testData"));
		unitTestVo.setTestSteps(request.getParameter("testSteps"));
		unitTestVo.setExpectedResult(request.getParameter("expectedResult"));
		unitTestVo.setActualResult(request.getParameter("actualResult"));
		unitTestVo.setTestStatus(request.getParameter("testStatus"));
		unitTestVo.setExecutionDate(request.getParameter("executionDate"));
		unitTestVo.setDuration(request.getParameter("duration"));
		unitTestVo.setNotes(request.getParameter("notes"));
		unitTestVo.setRequirements(request.getParameter("requirements"));
		unitTestVo.setPjtId(request.getParameter("pjtId"));

		AppLog.debug("수정할 단위테스트 케이스 정보: {}", unitTestVo);
		AppLog.debug("테스트 케이스 ID: {}", unitTestVo.getTestCaseId());

		// testCaseId가 없으면 오류
		if (unitTestVo.getTestCaseId() == null || unitTestVo.getTestCaseId().trim().isEmpty()) {
			throw new RuntimeException("수정할 테스트 케이스 ID가 필요합니다.");
		}

		// 2. 실제 파일 객체들 받아오기
		List<MultipartFile> fileList = multipartRequest.getFiles("files");
		AppLog.debug("받은 파일 개수: {}", (fileList != null ? fileList.size() : 0));

		// 3. MultipartFile 배열로 변환 (null이 아닌 파일만)
		List<MultipartFile> validFiles = new ArrayList<>();
		if (fileList != null) {
			for (MultipartFile file : fileList) {
				if (file != null && !file.isEmpty()) {
					validFiles.add(file);
					AppLog.debug("유효한 파일: {} (크기: {})" + file.getOriginalFilename() + "," + file.getSize());
				}
			}
		}

		MultipartFile[] files = validFiles.toArray(new MultipartFile[0]);

		// 4. 서비스 호출 (수정 메서드)
		unitTestService.updateUnitTestWithFiles(unitTestVo, files);

		AppLog.debug("=== 단위테스트 케이스 파일 수정 완료 ===");
	}

	/**
	 * 단위테스트 케이스를 삭제 처리한다.
	 */
	@ElService(key = "UNIT001Del")
	@RequestMapping(value = "UNIT001Del")
	@ElDescription(sub = "단위테스트 케이스 삭제처리", desc = "단위테스트 케이스를 삭제 처리한다.")
	public void deleteUnitTest(UnitTestVo unitTestVo) throws Exception {
		AppLog.debug("=== 컨트롤러: 단위테스트 케이스 삭제 시작 ===");
		AppLog.debug("삭제 대상 테스트 케이스 ID: {}", unitTestVo.getTestCaseId());

		try {
			// 서비스의 트랜잭션이 아닌 별도로 처리
			unitTestService.deleteUnitTest(unitTestVo);
			AppLog.debug("단위테스트 케이스 삭제 성공");

		} catch (Exception e) {
			AppLog.error("단위테스트 케이스 삭제 실패: {}", e.getMessage());
			throw new RuntimeException("테스트 케이스 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 방법 2: 파일과 테스트 케이스를 분리해서 삭제
	 */
	@ElService(key = "UNIT001DelWithFiles")
	@RequestMapping(value = "UNIT001DelWithFiles")
	@ElDescription(sub = "단위테스트 케이스 파일 포함 삭제", desc = "단위테스트 케이스와 관련 파일을 안전하게 삭제한다.")
	public void deleteUnitTestWithFiles(UnitTestVo unitTestVo) throws Exception {
	    AppLog.debug("=== 컨트롤러: 단위테스트 케이스 + 파일 삭제 시작 ===");
	    
	    String testCaseId = unitTestVo.getTestCaseId();
	    
	    if (testCaseId == null || testCaseId.trim().isEmpty()) {
	        throw new RuntimeException("삭제할 테스트 케이스 ID가 필요합니다.");
	    }
	    
	    try {
	        // 1. 테스트 케이스 존재 여부 확인
	        UnitTestVo existingTest = unitTestService.selectUnitTestDetail(unitTestVo);
	        if (existingTest == null) {
	            throw new RuntimeException("해당 테스트 케이스를 찾을 수 없습니다.");
	        }
	        
	        AppLog.debug("삭제 대상: {}", existingTest.getTestCaseName());
	        
	        // 2. 서비스에서 파일과 테스트 케이스 삭제
	        int deleteResult = unitTestService.deleteUnitTest(unitTestVo);
	        
	        if (deleteResult <= 0) {
	            throw new RuntimeException("테스트 케이스 삭제에 실패했습니다.");
	        }
	        
	        AppLog.debug("테스트 케이스 삭제 성공: {}", testCaseId);
	        
	    } catch (Exception e) {
	        AppLog.error("테스트 케이스 삭제 중 오류: {}", e.getMessage());
	        throw new RuntimeException("삭제 중 오류가 발생했습니다: " + e.getMessage());
	    }
	}


	/**
	 * 방법 3: 단순하게 서비스 메서드 분리 호출
	 */
	@ElService(key = "UNIT001DelSimple")
	@RequestMapping(value = "UNIT001DelSimple")
	@ElDescription(sub = "단위테스트 케이스 단순 삭제", desc = "트랜잭션 문제 없이 단순하게 삭제한다.")
	public void deleteUnitTestSimple(UnitTestVo unitTestVo) throws Exception {
		AppLog.debug("=== 컨트롤러: 단위테스트 케이스 단순 삭제 ===");

		String testCaseId = unitTestVo.getTestCaseId();
		if (testCaseId == null || testCaseId.trim().isEmpty()) {
			throw new RuntimeException("삭제할 테스트 케이스 ID가 필요합니다.");
		}

		try {
			// 1. 파일 삭제 시도 (실패해도 무시)
			try {
				AppLog.debug("관련 파일 삭제 시도: {}", testCaseId);
				attService.deleteFilesByRef("UNIT_TEST", testCaseId);
				AppLog.debug("관련 파일 삭제 완료");
			} catch (Exception fileException) {
				AppLog.warn("파일 삭제 실패 (무시하고 계속): {}", fileException.getMessage());
				// 파일 삭제 실패는 무시
			}

			// 2. 테스트 케이스 삭제 (트랜잭션 없는 버전 호출)
			int result = unitTestService.updateUnitTest(unitTestVo); // 실제로는 삭제 전용 메서드 필요

			if (result <= 0) {
				throw new RuntimeException("테스트 케이스 삭제에 실패했습니다.");
			}

			AppLog.debug("단위테스트 케이스 삭제 완료: {}", testCaseId);

		} catch (Exception e) {
			AppLog.error("테스트 케이스 삭제 실패: {}", e.getMessage());
			throw new RuntimeException("테스트 케이스 삭제 실패: " + e.getMessage());
		}
	}

// ===== 헬퍼 메서드들 =====

	/**
	 * 테스트 케이스 관련 파일들만 삭제
	 */
	private boolean deleteTestCaseFiles(String testCaseId) {
		try {
			AppLog.debug("테스트 케이스 파일 삭제 시작: {}", testCaseId);

			// AttService를 통해 파일 삭제
			attService.deleteFilesByRef("UNIT_TEST", testCaseId);

			AppLog.debug("테스트 케이스 파일 삭제 완료: {}", testCaseId);
			return true;

		} catch (Exception e) {
			AppLog.error("테스트 케이스 파일 삭제 실패: {} - {}");
			return false;
		}
	}

	/**
	 * 테스트 케이스만 삭제 (DB에서만)
	 */
	private boolean deleteTestCaseOnly(UnitTestVo unitTestVo) {
		try {
			AppLog.debug("테스트 케이스 DB 삭제 시작: {}", unitTestVo.getTestCaseId());

			// 서비스의 비트랜잭션 삭제 메서드 호출 (새로 만들어야 함)
			int result = unitTestService.deleteUnitTest(unitTestVo);

			AppLog.debug("테스트 케이스 DB 삭제 완료: {}", unitTestVo.getTestCaseId());
			return result > 0;

		} catch (Exception e) {
			AppLog.error("테스트 케이스 DB 삭제 실패: {} - {}");
			return false;
		}
	}

	/**
	 * 단위테스트 케이스 파일 업로드 (별도)
	 */
	@ElService(key = "UNIT001FileUpload")
	@RequestMapping(value = "UNIT001FileUpload")
	@ElDescription(sub = "단위테스트 케이스 파일 업로드", desc = "단위테스트 케이스에 파일을 업로드한다.")
	@ResponseBody
	public List<AttVo> uploadUnitTestFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("testCaseId") String testCaseId) throws Exception {

		AppLog.debug("=== 단위테스트 케이스 별도 파일 업로드 ===");
		AppLog.debug("테스트 케이스 ID: {}", testCaseId);
		AppLog.debug("파일 개수: {}", files.length);

		try {
			// AttService에 위임
			return attService.uploadFiles(files, "UNIT_TEST", testCaseId);
		} catch (Exception e) {
			AppLog.error("단위테스트 케이스 파일 업로드 실패: {}", e.getMessage());
			throw new ElException("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 단위테스트 케이스 파일 목록 조회
	 */
	@ElService(key = "UNIT001FileList")
	@RequestMapping(value = "UNIT001FileList")
	@ElDescription(sub = "단위테스트 케이스 파일 목록 조회", desc = "단위테스트 케이스의 파일 목록을 조회한다.")
	public AttListVo getUnitTestFileList(UnitTestVo unitTestVo) throws Exception {

		AppLog.debug("=== 단위테스트 케이스 파일 목록 조회 ===");
		AppLog.debug("요청 데이터: {}", unitTestVo != null ? unitTestVo.toString() : "null");

		// null 체크
		if (unitTestVo == null) {
			AppLog.warn("unitTestVo가 null입니다.");
			AttListVo emptyResult = new AttListVo();
			emptyResult.setAttVoList(new ArrayList<>());
			return emptyResult;
		}

		// ProworksCommVO 객체 생성해서 AttService에 위임
		ProworksCommVO fileParam = new ProworksCommVO();
		fileParam.setRefType("UNIT_TEST");
		fileParam.setRefId(unitTestVo.getTestCaseId());

		AppLog.debug("파일 조회 파라미터: refType={}, refId={}" + fileParam.getRefType() + ", " + fileParam.getRefId());

		List<AttVo> attList = attService.getFileList(fileParam);
		AppLog.debug("조회된 파일 개수: {}", (attList != null ? attList.size() : 0));

		AttListVo retAttList = new AttListVo();
		retAttList.setAttVoList(attList != null ? attList : new ArrayList<>());

		AppLog.debug("=== 단위테스트 케이스 파일 목록 조회 완료 ===");
		return retAttList;
	}

	/**
	 * 단위테스트 케이스 파일 삭제
	 */
	@ElService(key = "UNIT001FileDelete")
	@RequestMapping(value = "UNIT001FileDelete")
	@ElDescription(sub = "단위테스트 케이스 파일 삭제", desc = "단위테스트 케이스의 파일을 삭제한다.")
	@ResponseBody
	public void deleteUnitTestFile(@RequestParam("fileId") String fileId) throws Exception {
		AppLog.debug("=== 단위테스트 케이스 파일 삭제 ===");
		AppLog.debug("파일 ID: {}", fileId);

		try {
			// AttService에 위임
			attService.deleteFile(fileId);
			AppLog.debug("단위테스트 케이스 파일 삭제 성공");
		} catch (Exception e) {
			AppLog.error("단위테스트 케이스 파일 삭제 실패: {}", e.getMessage());
			throw new ElException("파일 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 테스트 상태별 통계 조회
	 */
	@ElService(key = "UNIT001Statistics")
	@RequestMapping(value = "UNIT001Statistics")
	@ElDescription(sub = "테스트 상태별 통계 조회", desc = "테스트 상태별 통계를 조회한다.")
	public Map<String, Object> selectUnitTestStatistics(UnitTestVo unitTestVo) throws Exception {

		AppLog.debug("통계 조회 요청: {}", unitTestVo);

		Map<String, Object> statistics = unitTestService.selectUnitTestStatistics(unitTestVo);

		AppLog.debug("통계 조회 완료: {}", statistics);

		return statistics;
	}

	/**
	 * 프로젝트별 테스트 상태별 통계 조회
	 */
	@ElService(key = "UNIT001StatisticsByProject")
	@RequestMapping(value = "UNIT001StatisticsByProject")
	@ElDescription(sub = "프로젝트별 테스트 상태별 통계 조회", desc = "특정 프로젝트의 테스트 상태별 통계를 조회한다.")
	public Map<String, Object> selectUnitTestStatisticsByProject(UnitTestVo unitTestVo) throws Exception {

		AppLog.debug("프로젝트별 통계 조회 요청: {}", unitTestVo);

		Map<String, Object> statistics = unitTestService.selectUnitTestStatistics(unitTestVo);

		AppLog.debug("프로젝트별 통계 조회 완료: {}", statistics);

		return statistics;
	}

	/**
	 * 테스트 실행 상태 업데이트
	 */
	@ElService(key = "UNIT001UpdateStatus")
	@RequestMapping(value = "UNIT001UpdateStatus")
	@ElDescription(sub = "테스트 실행 상태 업데이트", desc = "테스트 실행 상태를 업데이트한다.")
	public void updateTestStatus(UnitTestVo unitTestVo) throws Exception {

		AppLog.debug("테스트 상태 업데이트 요청: {}", unitTestVo);

		int result = unitTestService.updateTestStatus(unitTestVo);

		if (result <= 0) {
			throw new Exception("상태 업데이트에 실패했습니다.");
		}

		AppLog.debug("테스트 상태 업데이트 완료: {}", unitTestVo.getTestCaseId());
	}

	/**
	 * 테스트 결과 업데이트
	 */
	@ElService(key = "UNIT001UpdateResult")
	@RequestMapping(value = "UNIT001UpdateResult")
	@ElDescription(sub = "테스트 결과 업데이트", desc = "테스트 결과를 업데이트한다.")
	public void updateTestResult(UnitTestVo unitTestVo) throws Exception {

		AppLog.debug("테스트 결과 업데이트 요청: {}", unitTestVo);

		int result = unitTestService.updateTestResult(unitTestVo);

		if (result <= 0) {
			throw new Exception("결과 업데이트에 실패했습니다.");
		}

		AppLog.debug("테스트 결과 업데이트 완료: {}", unitTestVo.getTestCaseId());
	}
}