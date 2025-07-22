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

    private static final Logger logger = LoggerFactory.getLogger(UnitTestController.class);

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

        logger.debug("프로젝트별 단위테스트 케이스 목록 조회 요청: {}", unitTestVo);

        List<UnitTestVo> unitTestList = unitTestService.selectUnitTestList(unitTestVo);
        long totCnt = unitTestService.selectListCountUnitTest(unitTestVo);

        UnitTestListVo retUnitTestList = new UnitTestListVo();
        retUnitTestList.setUnitTestList(unitTestList);
        retUnitTestList.setTotalCount(totCnt);
        retUnitTestList.setPageSize(unitTestVo.getPageSize());
        retUnitTestList.setPageIndex(unitTestVo.getPageIndex());

        logger.debug("프로젝트별 목록 조회 완료: {} 건", totCnt);

        return retUnitTestList;
    }

    /**
     * 단위테스트 케이스를 단건 조회 처리 한다.
     */
    @ElService(key = "UNIT001UpdView")
    @RequestMapping(value = "UNIT001UpdView")
    @ElDescription(sub = "단위테스트 케이스 갱신 폼을 위한 조회", desc = "단위테스트 케이스 갱신 폼을 위한 조회를 한다.")
    public UnitTestVo selectUnitTest(UnitTestVo unitTestVo) throws Exception {
        UnitTestVo selectUnitTestVo = unitTestService.selectUnitTestDetail(unitTestVo);
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

        logger.debug("=== 단위테스트 케이스 파일 등록 시작 ===");

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
            executionDate = executionDate.substring(0,4) + "-" + 
                           executionDate.substring(4,6) + "-" + 
                           executionDate.substring(6,8);
        }
        unitTestVo.setExecutionDate(executionDate);
        unitTestVo.setDuration(request.getParameter("duration"));
        unitTestVo.setNotes(request.getParameter("notes"));
        unitTestVo.setRequirements(request.getParameter("requirements"));
        unitTestVo.setPjtId(request.getParameter("pjtId"));

        logger.debug("단위테스트 케이스 정보: {}", unitTestVo);

        // 2. 실제 파일 객체들 받아오기
        List<MultipartFile> fileList = multipartRequest.getFiles("files");
        logger.debug("받은 파일 개수: {}", (fileList != null ? fileList.size() : 0));

        // 3. MultipartFile 배열로 변환 (null이 아닌 파일만)
        List<MultipartFile> validFiles = new ArrayList<>();
        if (fileList != null) {
            for (MultipartFile file : fileList) {
                if (file != null && !file.isEmpty()) {
                    validFiles.add(file);
                    logger.debug("유효한 파일: {} (크기: {})", file.getOriginalFilename(), file.getSize());
                }
            }
        }

        MultipartFile[] files = validFiles.toArray(new MultipartFile[0]);

        // 4. 서비스 호출 (기존 방식 사용)
        unitTestService.insertUnitTestWithFiles(unitTestVo, files);
        
        logger.debug("=== 단위테스트 케이스 파일 등록 완료 ===");
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

        logger.debug("=== 단위테스트 케이스 파일 수정 시작 ===");
        
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

        logger.debug("수정할 단위테스트 케이스 정보: {}", unitTestVo);
        logger.debug("테스트 케이스 ID: {}", unitTestVo.getTestCaseId());

        // testCaseId가 없으면 오류
        if (unitTestVo.getTestCaseId() == null || unitTestVo.getTestCaseId().trim().isEmpty()) {
            throw new RuntimeException("수정할 테스트 케이스 ID가 필요합니다.");
        }

        // 2. 실제 파일 객체들 받아오기
        List<MultipartFile> fileList = multipartRequest.getFiles("files");
        logger.debug("받은 파일 개수: {}", (fileList != null ? fileList.size() : 0));

        // 3. MultipartFile 배열로 변환 (null이 아닌 파일만)
        List<MultipartFile> validFiles = new ArrayList<>();
        if (fileList != null) {
            for (MultipartFile file : fileList) {
                if (file != null && !file.isEmpty()) {
                    validFiles.add(file);
                    logger.debug("유효한 파일: {} (크기: {})", file.getOriginalFilename(), file.getSize());
                }
            }
        }

        MultipartFile[] files = validFiles.toArray(new MultipartFile[0]);

        // 4. 서비스 호출 (수정 메서드)
        unitTestService.updateUnitTestWithFiles(unitTestVo, files);
        
        logger.debug("=== 단위테스트 케이스 파일 수정 완료 ===");
    }

    /**
     * 단위테스트 케이스를 삭제 처리한다.
     */
    @ElService(key = "UNIT001Del")
    @RequestMapping(value = "UNIT001Del")
    @ElDescription(sub = "단위테스트 케이스 삭제처리", desc = "단위테스트 케이스를 삭제 처리한다.")
    public void deleteUnitTest(UnitTestVo unitTestVo) throws Exception {
        unitTestService.deleteUnitTest(unitTestVo);
    }

    // ===== 단위테스트 케이스 관련 파일 처리 (AttService 위임) =====

    /**
     * 단위테스트 케이스 파일 업로드 (별도)
     */
    @ElService(key = "UNIT001FileUpload")
    @RequestMapping(value = "UNIT001FileUpload")
    @ElDescription(sub = "단위테스트 케이스 파일 업로드", desc = "단위테스트 케이스에 파일을 업로드한다.")
    @ResponseBody
    public List<AttVo> uploadUnitTestFiles(@RequestParam("files") MultipartFile[] files,
            @RequestParam("testCaseId") String testCaseId) throws Exception {

        logger.debug("=== 단위테스트 케이스 별도 파일 업로드 ===");
        logger.debug("테스트 케이스 ID: {}", testCaseId);
        logger.debug("파일 개수: {}", files.length);

        try {
            // AttService에 위임
            return attService.uploadFiles(files, "UNIT_TEST", testCaseId);
        } catch (Exception e) {
            logger.error("단위테스트 케이스 파일 업로드 실패: {}", e.getMessage());
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
	    
	    logger.debug("=== 단위테스트 케이스 파일 목록 조회 ===");
	    logger.debug("요청 데이터: {}", unitTestVo != null ? unitTestVo.toString() : "null");
	    
	    // null 체크
	    if (unitTestVo == null) {
	        logger.warn("unitTestVo가 null입니다.");
	        AttListVo emptyResult = new AttListVo();
	        emptyResult.setAttVoList(new ArrayList<>());
	        return emptyResult;
	    }
	    
	    // ProworksCommVO 객체 생성해서 AttService에 위임
	    ProworksCommVO fileParam = new ProworksCommVO();
	    fileParam.setRefType("UNIT_TEST");
	    fileParam.setRefId(unitTestVo.getTestCaseId());
	    
	    logger.debug("파일 조회 파라미터: refType={}, refId={}", fileParam.getRefType(), fileParam.getRefId());
	    
	    List<AttVo> attList = attService.getFileList(fileParam);
	    logger.debug("조회된 파일 개수: {}", (attList != null ? attList.size() : 0));
	
	    AttListVo retAttList = new AttListVo();
	    retAttList.setAttVoList(attList != null ? attList : new ArrayList<>());
	    
	    logger.debug("=== 단위테스트 케이스 파일 목록 조회 완료 ===");
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
        logger.debug("=== 단위테스트 케이스 파일 삭제 ===");
        logger.debug("파일 ID: {}", fileId);

        try {
            // AttService에 위임
            attService.deleteFile(fileId);
            logger.debug("단위테스트 케이스 파일 삭제 성공");
        } catch (Exception e) {
            logger.error("단위테스트 케이스 파일 삭제 실패: {}", e.getMessage());
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

        logger.debug("통계 조회 요청: {}", unitTestVo);
        
        Map<String, Object> statistics = unitTestService.selectUnitTestStatistics(unitTestVo);
        
        logger.debug("통계 조회 완료: {}", statistics);

        return statistics;
    }

    /**
     * 프로젝트별 테스트 상태별 통계 조회
     */
    @ElService(key = "UNIT001StatisticsByProject")
    @RequestMapping(value = "UNIT001StatisticsByProject")
    @ElDescription(sub = "프로젝트별 테스트 상태별 통계 조회", desc = "특정 프로젝트의 테스트 상태별 통계를 조회한다.")
    public Map<String, Object> selectUnitTestStatisticsByProject(UnitTestVo unitTestVo) throws Exception {

        logger.debug("프로젝트별 통계 조회 요청: {}", unitTestVo);
        
        Map<String, Object> statistics = unitTestService.selectUnitTestStatistics(unitTestVo);
        
        logger.debug("프로젝트별 통계 조회 완료: {}", statistics);

        return statistics;
    }

    /**
     * 테스트 실행 상태 업데이트
     */
    @ElService(key = "UNIT001UpdateStatus")
    @RequestMapping(value = "UNIT001UpdateStatus")
    @ElDescription(sub = "테스트 실행 상태 업데이트", desc = "테스트 실행 상태를 업데이트한다.")
    public void updateTestStatus(UnitTestVo unitTestVo) throws Exception {
        
        logger.debug("테스트 상태 업데이트 요청: {}", unitTestVo);

        int result = unitTestService.updateTestStatus(unitTestVo);

        if (result <= 0) {
            throw new Exception("상태 업데이트에 실패했습니다.");
        }
        
        logger.debug("테스트 상태 업데이트 완료: {}", unitTestVo.getTestCaseId());
    }

    /**
     * 테스트 결과 업데이트
     */
    @ElService(key = "UNIT001UpdateResult")
    @RequestMapping(value = "UNIT001UpdateResult")
    @ElDescription(sub = "테스트 결과 업데이트", desc = "테스트 결과를 업데이트한다.")
    public void updateTestResult(UnitTestVo unitTestVo) throws Exception {
        
        logger.debug("테스트 결과 업데이트 요청: {}", unitTestVo);

        int result = unitTestService.updateTestResult(unitTestVo);

        if (result <= 0) {
            throw new Exception("결과 업데이트에 실패했습니다.");
        }
        
        logger.debug("테스트 결과 업데이트 완료: {}", unitTestVo.getTestCaseId());
    }
}