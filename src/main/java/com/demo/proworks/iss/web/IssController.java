package com.demo.proworks.iss.web;

import java.io.File;
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
import com.demo.proworks.iss.service.IssService;
import com.demo.proworks.iss.vo.IssVo;
import com.demo.proworks.iss.vo.IssListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;

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
public class IssController {

	/** IssService */
	@Resource(name = "issServiceImpl")
	private IssService issService;

	/** AttService */
	@Resource(name = "attServiceImpl")
	private AttService attService;

	/**
	 * 이슈리스크관리 목록을 조회합니다.
	 */
	@ElService(key = "ISS001List")
	@RequestMapping(value = "ISS001List")
	@ElDescription(sub = "이슈리스크관리 목록조회", desc = "페이징을 처리하여 이슈리스크관리 목록 조회를 한다.")
	public IssListVo selectListIss(IssVo issVo) throws Exception {

		List<IssVo> issList = issService.selectListIss(issVo);
		long totCnt = issService.selectListCountIss(issVo);

		IssListVo retIssList = new IssListVo();
		retIssList.setIssVoList(issList);
		retIssList.setTotalCount(totCnt);
		retIssList.setPageSize(issVo.getPageSize());
		retIssList.setPageIndex(issVo.getPageIndex());

		AppLog.info("retIssList" + retIssList.toString());

		return retIssList;
	}

	/**
	 * 이슈리스크관리을 단건 조회 처리 한다.
	 */
	@ElService(key = "ISS001UpdView")
	@RequestMapping(value = "ISS001UpdView")
	@ElDescription(sub = "이슈리스크관리 갱신 폼을 위한 조회", desc = "이슈리스크관리 갱신 폼을 위한 조회를 한다.")
	public IssVo selectIss(IssVo issVo) throws Exception {
		IssVo selectIssVo = issService.selectIss(issVo);
		return selectIssVo;
	}

	/**
	 * 이슈리스크관리를 등록 처리 한다. (단순 등록)
	 */
	@ElService(key = "ISS001Ins")
	@RequestMapping(value = "ISS001Ins")
	@ElDescription(sub = "이슈리스크관리 등록처리", desc = "이슈리스크관리를 등록 처리 한다.")
	public void insertIss(IssVo issVo) throws Exception {
		issService.insertIss(issVo);
	}

	/**
	 * 이슈리스크관리를 파일과 함께 등록 처리 한다. (트랜잭션)
	 */
	@ElService(key = "ISS001InsWithFiles")
	@RequestMapping(value = "ISS001InsWithFiles")
	@ElDescription(sub = "이슈리스크관리 파일 등록처리", desc = "이슈리스크관리를 파일과 함께 등록 처리 한다.")
	public void insertIssWithFiles(HttpServletRequest request) throws Exception {

	
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	
	    IssVo issVo = new IssVo();
	    issVo.setPjtId(request.getParameter("pjtId"));
	    issVo.setName(request.getParameter("name"));
	    issVo.setUserId(request.getParameter("userId"));
	    issVo.setDueDate(request.getParameter("dueDate"));
	    issVo.setStatus(request.getParameter("status"));
	    issVo.setPriority(request.getParameter("priority"));
	    issVo.setDescription(request.getParameter("description"));
	    issVo.setResponsePlan(request.getParameter("responsePlan"));
	    issVo.setResolvedDate(request.getParameter("resolvedDate"));
	
	
	    List<MultipartFile> fileList = multipartRequest.getFiles("files");
	
	    List<MultipartFile> validFiles = new ArrayList<>();
	    if (fileList != null) {
	        for (MultipartFile file : fileList) {
	            if (file != null && !file.isEmpty()) {
	                validFiles.add(file);
	            }
	        }
	    }
	
	    MultipartFile[] files = validFiles.toArray(new MultipartFile[0]);
	
	    issService.insertIssWithFiles(issVo, files);
	    
	}

	/**
	 * 이슈리스크관리를 갱신 처리 한다. (단순 수정)
	 */
	@ElService(key = "ISS001Upd")
	@RequestMapping(value = "ISS001Upd")
	@ElValidator(errUrl = "/iss/issRegister", errContinue = true)
	@ElDescription(sub = "이슈리스크관리 갱신처리", desc = "이슈리스크관리를 갱신 처리 한다.")
	public void updateIss(IssVo issVo) throws Exception {
		issService.updateIss(issVo);
	}

	/**
	 * 이슈리스크관리를 파일과 함께 갱신 처리 한다. (트랜잭션)
	 */
	@ElService(key = "ISS001UpdWithFiles")
	@RequestMapping(value = "ISS001UpdWithFiles")
	@ElDescription(sub = "이슈리스크관리 파일 갱신처리", desc = "이슈리스크관리를 파일과 함께 갱신 처리 한다.")
	public void updateIssWithFiles(HttpServletRequest request) throws Exception {

	    
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	
	    IssVo issVo = new IssVo();
	    issVo.setId(request.getParameter("id"));  // 수정 시 ID 필수
	    issVo.setPjtId(request.getParameter("pjtId"));
	    issVo.setName(request.getParameter("name"));
	    issVo.setUserId(request.getParameter("userId"));
	    issVo.setDueDate(request.getParameter("dueDate"));
	    issVo.setStatus(request.getParameter("status"));
	    issVo.setPriority(request.getParameter("priority"));
	    issVo.setDescription(request.getParameter("description"));
	    issVo.setResponsePlan(request.getParameter("responsePlan"));
	    issVo.setResolvedDate(request.getParameter("resolvedDate"));
	
	    if (issVo.getId() == null || issVo.getId().trim().isEmpty()) {
	        throw new RuntimeException("수정할 이슈 ID가 필요합니다.");
	    }
	
	    List<MultipartFile> fileList = multipartRequest.getFiles("files");
	
	    List<MultipartFile> validFiles = new ArrayList<>();
	    if (fileList != null) {
	        for (MultipartFile file : fileList) {
	            if (file != null && !file.isEmpty()) {
	                validFiles.add(file);
	            }
	        }
	    }
	
	    MultipartFile[] files = validFiles.toArray(new MultipartFile[0]);
	
	    issService.updateIssWithFiles(issVo, files);
	    
	}

	/**
	 * 이슈리스크관리를 삭제 처리한다.
	 */
	@ElService(key = "ISS001Del")
	@RequestMapping(value = "ISS001Del")
	@ElDescription(sub = "이슈리스크관리 삭제처리", desc = "이슈리스크관리를 삭제 처리한다.")
	public void deleteIss(IssVo issVo) throws Exception {
		issService.deleteIss(issVo);
	}


	/**
	 * 이슈리스크 파일 업로드 (별도)
	 */
	@ElService(key = "ISS001FileUpload")
	@RequestMapping(value = "ISS001FileUpload")
	@ElDescription(sub = "이슈리스크 파일 업로드", desc = "이슈리스크에 파일을 업로드한다.")
	@ResponseBody
	public List<AttVo> uploadIssFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("issueId") String issueId) throws Exception {


		try {
			// AttService에 위임
			return attService.uploadFiles(files, "ISSUE_RISK", issueId);
		} catch (Exception e) {
			System.err.println("이슈 파일 업로드 실패: " + e.getMessage());
			throw new ElException("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 이슈리스크 파일 목록 조회
	 */
	@ElService(key = "ISS001FileList")
	@RequestMapping(value = "ISS001FileList")
	@ElDescription(sub = "이슈리스크 파일 목록 조회", desc = "이슈리스크의 파일 목록을 조회한다.")
	public AttListVo getIssFileList(IssVo issVo) throws Exception {
	
	    
	    ProworksCommVO fileParam = new ProworksCommVO();
	    fileParam.setRefType("ISSUE");
	    fileParam.setRefId(issVo.getId());
	    
	    
	    List<AttVo> attList = attService.getFileList(fileParam);

	    AttListVo retAttList = new AttListVo();
	    retAttList.setAttVoList(attList);
	    
	    return retAttList;
	}

	/**
	 * 이슈리스크 파일 삭제
	 */
	@ElService(key = "ISS001FileDelete")
	@RequestMapping(value = "ISS001FileDelete")
	@ElDescription(sub = "이슈리스크 파일 삭제", desc = "이슈리스크의 파일을 삭제한다.")
	@ResponseBody
	public void deleteIssFile(@RequestParam("fileId") String fileId) throws Exception {

		try {
			attService.deleteFile(fileId);
		} catch (Exception e) {
			throw new ElException("파일 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

}