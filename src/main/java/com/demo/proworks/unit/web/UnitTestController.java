package com.demo.proworks.unit.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.unit.service.UnitTestService;
import com.demo.proworks.unit.vo.UnitTestListVo;
import com.demo.proworks.unit.vo.UnitTestVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;

/**
 * @subject : 이슈리스크관리 관련 처리를 담당하는 컨트롤러
 * @description : 이슈리스크관리 관련 처리를 담당하는 컨트롤러
 * @author : 우민지
 * @since : 2025/07/07
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/07 우민지 최초 생성
 *               2025/07/15 우민지 파일 관련 메서드 AttController로 이동
 * 
 */
@Controller
public class UnitTestController {
    
    private static final Logger logger = LoggerFactory.getLogger(UnitTestController.class);
    
    @Resource(name = "unitTestServiceImpl")
    private UnitTestService unitTestService;
    
    /**
     * 단위테스트 케이스 목록 조회
     * @throws Exception 
     */
    @ElService(key = "UNIT001List")
    @RequestMapping(value = "UNIT001List")
    @ElDescription(sub = "단위테스트 케이스 목록 조회", desc = "단위테스트 케이스 목록을 조회한다.")
    public UnitTestListVo selectUnitTestList(UnitTestVo unitTestVo) throws Exception {
        
//        System.out.println("========================단위테스트 케이스 보내기 : {}" + unitTestVo);
        
        
        List<UnitTestVo> unitTestList = unitTestService.selectUnitTestList(unitTestVo);
		long totCnt = unitTestService.selectListCountUnitTest(unitTestVo);
//		System.out.println("========================단위테스트 케이스 보내기 : {}" + totCnt);
		
		UnitTestListVo retList = new UnitTestListVo();
		retList.setUnitTestList(unitTestList);
		retList.setTotalCount(totCnt);
		retList.setPageSize(unitTestVo.getPageSize());
		retList.setPageIndex(unitTestVo.getPageIndex());
		
//		System.out.println("=========================단위테스트 케이스 상세 조회 요청: {}" + retList);

		return retList;
    }
    
    
    
    /**
     * 단위테스트 케이스 상세 조회
     */
    @ElService(key = "UNIT001Detail")
    @RequestMapping(value = "UNIT001Detail")
    @ElDescription(sub = "단위테스트 케이스 상세 조회", desc = "단위테스트 케이스 상세 정보를 조회한다.")
    @ResponseBody
    public Map<String, Object> selectUnitTestDetail(@RequestBody Map<String, Object> requestMap) {
        try {
            logger.debug("단위테스트 케이스 상세 조회 요청: {}", requestMap);
            
            UnitTestVo testVo = unitTestService.selectUnitTestDetail(requestMap);
            return createResponse(true, "0000", "조회되었습니다.", testVo);
            
        } catch (Exception e) {
            logger.error("단위테스트 케이스 상세 조회 실패", e);
            return createResponse(false, "UNIT001D", e.getMessage(), null);
        }
    }
    
    /**
     * 단위테스트 케이스 등록
     */
    @ElService(key = "UNIT001Ins")
    @RequestMapping(value = "UNIT001Ins")
    @ElDescription(sub = "단위테스트 케이스 등록", desc = "단위테스트 케이스를 등록한다.")
    @ResponseBody
    public Map<String, Object> insertUnitTest(@RequestBody Map<String, Object> requestMap, HttpServletRequest request) {
        try {
            logger.debug("단위테스트 케이스 등록 요청: {}", requestMap);
            
            // 사용자 정보 설정
            setUserInfo(requestMap, request);
            
            String testCaseId = unitTestService.insertUnitTest(requestMap);
            
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("testCaseId", testCaseId);
            
            return createResponse(true, "0000", "등록되었습니다.", resultData);
            
        } catch (Exception e) {
            logger.error("단위테스트 케이스 등록 실패", e);
            return createResponse(false, "UNIT002", e.getMessage(), null);
        }
    }
    
    /**
     * 단위테스트 케이스 파일 포함 등록
     */
    @ElService(key = "UNIT001InsWithFiles")
    @RequestMapping(value = "UNIT001InsWithFiles")
    @ElDescription(sub = "단위테스트 케이스 파일 포함 등록", desc = "단위테스트 케이스와 파일을 함께 등록한다.")
    @ResponseBody
    public Map<String, Object> insertUnitTestWithFiles(MultipartHttpServletRequest request) {
        try {
            logger.debug("단위테스트 케이스 파일 포함 등록 요청");
            
            // 테스트 케이스 정보 추출
            Map<String, Object> testCaseMap = extractTestCaseData(request);
            
            // 사용자 정보 설정
            setUserInfo(testCaseMap, request);
            
            // 파일 정보 추출
            List<MultipartFile> files = request.getFiles("files");
            
            String testCaseId = unitTestService.insertUnitTestWithFiles(testCaseMap, files);
            
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("testCaseId", testCaseId);
            
            return createResponse(true, "0000", "등록되었습니다.", resultData);
            
        } catch (Exception e) {
            logger.error("단위테스트 케이스 파일 포함 등록 실패", e);
            return createResponse(false, "UNIT003", e.getMessage(), null);
        }
    }
    
    /**
     * 단위테스트 케이스 수정
     */
    @ElService(key = "UNIT001Upd")
    @RequestMapping(value = "UNIT001Upd")
    @ElDescription(sub = "단위테스트 케이스 수정", desc = "단위테스트 케이스를 수정한다.")
    @ResponseBody
    public Map<String, Object> updateUnitTest(@RequestBody Map<String, Object> requestMap, HttpServletRequest request) {
        try {
            logger.debug("단위테스트 케이스 수정 요청: {}", requestMap);
            
            // 사용자 정보 설정
            setUserInfo(requestMap, request);
            
            int result = unitTestService.updateUnitTest(requestMap);
            
            if (result > 0) {
                return createResponse(true, "0000", "수정되었습니다.", null);
            } else {
                return createResponse(false, "UNIT004", "수정에 실패했습니다.", null);
            }
            
        } catch (Exception e) {
            logger.error("단위테스트 케이스 수정 실패", e);
            return createResponse(false, "UNIT004", e.getMessage(), null);
        }
    }
    
    /**
     * 단위테스트 케이스 삭제
     */
    @ElService(key = "UNIT001Del")
    @RequestMapping(value = "UNIT001Del")
    @ElDescription(sub = "단위테스트 케이스 삭제", desc = "단위테스트 케이스를 삭제한다.")
    @ResponseBody
    public Map<String, Object> deleteUnitTest(@RequestBody Map<String, Object> requestMap, HttpServletRequest request) {
        try {
            logger.debug("단위테스트 케이스 삭제 요청: {}", requestMap);
            
            // 사용자 정보 설정
            setUserInfo(requestMap, request);
            
            int result = unitTestService.deleteUnitTest(requestMap);
            
            if (result > 0) {
                return createResponse(true, "0000", "삭제되었습니다.", null);
            } else {
                return createResponse(false, "UNIT005", "삭제에 실패했습니다.", null);
            }
            
        } catch (Exception e) {
            logger.error("단위테스트 케이스 삭제 실패", e);
            return createResponse(false, "UNIT005", e.getMessage(), null);
        }
    }
    
    /**
     * 단위테스트 케이스 첨부파일 목록 조회
     */
    @ElService(key = "UNIT001FileList")
    @RequestMapping(value = "UNIT001FileList")
    @ElDescription(sub = "단위테스트 케이스 첨부파일 목록 조회", desc = "단위테스트 케이스의 첨부파일 목록을 조회한다.")
    @ResponseBody
    public Map<String, Object> selectUnitTestFileList(@RequestBody Map<String, Object> requestMap) {
        try {
            logger.debug("첨부파일 목록 조회 요청: {}", requestMap);
            
            List<AttVo> fileList = unitTestService.selectUnitTestFileList(requestMap);
            
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("attVoList", fileList);
            
            return createResponse(true, "0000", "조회되었습니다.", resultData);
            
        } catch (Exception e) {
            logger.error("첨부파일 목록 조회 실패", e);
            return createResponse(false, "UNIT006", e.getMessage(), null);
        }
    }
    
    /**
     * 단위테스트 케이스 첨부파일 삭제
     */
    @ElService(key = "UNIT001FileDelete")
    @RequestMapping(value = "UNIT001FileDelete")
    @ElDescription(sub = "단위테스트 케이스 첨부파일 삭제", desc = "단위테스트 케이스의 첨부파일을 삭제한다.")
    @ResponseBody
    public Map<String, Object> deleteUnitTestFile(@RequestParam("fileId") String fileId, HttpServletRequest request) {
        try {
            logger.debug("첨부파일 삭제 요청: {}", fileId);
            
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("fileId", fileId);
            
            // 사용자 정보 설정
            setUserInfo(paramMap, request);
            
            int result = unitTestService.deleteUnitTestFile(paramMap);
            
            if (result > 0) {
                return createResponse(true, "0000", "파일이 삭제되었습니다.", null);
            } else {
                return createResponse(false, "UNIT007", "파일 삭제에 실패했습니다.", null);
            }
            
        } catch (Exception e) {
            logger.error("첨부파일 삭제 실패", e);
            return createResponse(false, "UNIT007", e.getMessage(), null);
        }
    }
    
    /**
     * 테스트 상태별 통계 조회
     */
    @ElService(key = "UNIT001Statistics")
    @RequestMapping(value = "UNIT001Statistics")
    @ElDescription(sub = "테스트 상태별 통계 조회", desc = "테스트 상태별 통계를 조회한다.")
    public Map<String, Object> selectUnitTestStatistics(Map<String, Object> searchCondition) throws Exception {
    
	    logger.debug("통계 조회 요청: {}", searchCondition);
	    
	    Map<String, Object> statistics = unitTestService.selectUnitTestStatistics(searchCondition);
	    
	    // elData 래퍼로 감싸서 응답
	    Map<String, Object> response = new HashMap<>();
	    response.put("elData", statistics);
	    
	    logger.debug("통계 조회 완료: {}", response);
	    
	    return response;
	}
    
    /**
     * 테스트 실행 상태 업데이트
     */
    @ElService(key = "UNIT001UpdateStatus")
    @RequestMapping(value = "UNIT001UpdateStatus")
    @ElDescription(sub = "테스트 실행 상태 업데이트", desc = "테스트 실행 상태를 업데이트한다.")
    @ResponseBody
    public Map<String, Object> updateTestStatus(@RequestBody Map<String, Object> requestMap, HttpServletRequest request) {
        try {
            logger.debug("테스트 상태 업데이트 요청: {}", requestMap);
            
            // 사용자 정보 설정
            setUserInfo(requestMap, request);
            
            int result = unitTestService.updateTestStatus(requestMap);
            
            if (result > 0) {
                return createResponse(true, "0000", "상태가 업데이트되었습니다.", null);
            } else {
                return createResponse(false, "UNIT009", "상태 업데이트에 실패했습니다.", null);
            }
            
        } catch (Exception e) {
            logger.error("테스트 상태 업데이트 실패", e);
            return createResponse(false, "UNIT009", e.getMessage(), null);
        }
    }
    
    /**
     * 테스트 결과 업데이트
     */
    @ElService(key = "UNIT001UpdateResult")
    @RequestMapping(value = "UNIT001UpdateResult")
    @ElDescription(sub = "테스트 결과 업데이트", desc = "테스트 결과를 업데이트한다.")
    @ResponseBody
    public Map<String, Object> updateTestResult(@RequestBody Map<String, Object> requestMap, HttpServletRequest request) {
        try {
            logger.debug("테스트 결과 업데이트 요청: {}", requestMap);
            
            // 사용자 정보 설정
            setUserInfo(requestMap, request);
            
            int result = unitTestService.updateTestResult(requestMap);
            
            if (result > 0) {
                return createResponse(true, "0000", "결과가 업데이트되었습니다.", null);
            } else {
                return createResponse(false, "UNIT010", "결과 업데이트에 실패했습니다.", null);
            }
            
        } catch (Exception e) {
            logger.error("테스트 결과 업데이트 실패", e);
            return createResponse(false, "UNIT010", e.getMessage(), null);
        }
    }
    
    // ===== 유틸리티 메서드 =====
    
    /**
     * 프로웍스 표준 응답 생성
     */
    private Map<String, Object> createResponse(boolean success, String code, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        
        // 헤더 정보
        Map<String, Object> header = new HashMap<>();
        header.put("resSuc", success);
        header.put("resCode", code);
        header.put("resMsg", message);
        response.put("elHeader", header);
        
        // 데이터 정보
        if (data != null) {
            response.put("elData", data);
        }
        
        return response;
    }
    
    /**
     * 사용자 정보 설정
     */
    private void setUserInfo(Map<String, Object> paramMap, HttpServletRequest request) {
        String userId = getUserId(request);
        paramMap.put("createdBy", userId);
        paramMap.put("updatedBy", userId);
    }
    
    /**
     * 세션에서 사용자 ID 조회
     */
    private String getUserId(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("userId");
        return (userId != null) ? userId : "system";
    }
    
    /**
     * MultipartRequest에서 테스트 케이스 데이터 추출
     */
    private Map<String, Object> extractTestCaseData(MultipartHttpServletRequest request) {
        Map<String, Object> testCaseMap = new HashMap<>();
        
        testCaseMap.put("taskId", request.getParameter("taskId"));
        testCaseMap.put("taskName", request.getParameter("taskName"));
        testCaseMap.put("testCaseName", request.getParameter("testCaseName"));
        testCaseMap.put("testTarget", request.getParameter("testTarget"));
        testCaseMap.put("testType", getParameterWithDefault(request, "testType", "NORMAL"));
        testCaseMap.put("priority", getParameterWithDefault(request, "priority", "MEDIUM"));
        testCaseMap.put("assignee", request.getParameter("assignee"));
        testCaseMap.put("description", request.getParameter("description"));
        testCaseMap.put("precondition", request.getParameter("precondition"));
        testCaseMap.put("testData", request.getParameter("testData"));
        testCaseMap.put("testSteps", request.getParameter("testSteps"));
        testCaseMap.put("expectedResult", request.getParameter("expectedResult"));
        testCaseMap.put("actualResult", request.getParameter("actualResult"));
        testCaseMap.put("testStatus", getParameterWithDefault(request, "testStatus", "PEN"));
        testCaseMap.put("executionDate", request.getParameter("executionDate"));
        testCaseMap.put("duration", request.getParameter("duration"));
        testCaseMap.put("notes", request.getParameter("notes"));
        testCaseMap.put("requirements", request.getParameter("requirements"));
        
        return testCaseMap;
    }
    
    /**
     * 파라미터 조회 (기본값 포함)
     */
    private String getParameterWithDefault(MultipartHttpServletRequest request, String paramName, String defaultValue) {
        String value = request.getParameter(paramName);
        return (value != null && !value.trim().isEmpty()) ? value : defaultValue;
    }
}