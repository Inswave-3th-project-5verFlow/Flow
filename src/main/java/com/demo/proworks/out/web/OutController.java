package com.demo.proworks.out.web;

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
import com.demo.proworks.out.service.OutService;
import com.demo.proworks.out.vo.OutVo;
import com.demo.proworks.out.vo.OutListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;

/**
 * @subject : 산출물관리 관련 처리를 담당하는 컨트롤러
 * @description : 산출물관리 관련 처리를 담당하는 컨트롤러
 * @author : 우민지
 * @since : 2025/07/07
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/07 우민지 최초 생성
 * 
 */
@Controller
public class OutController {

	/** OutService */
	@Resource(name = "outServiceImpl")
	private OutService outService;

	/** AttService */
	@Resource(name = "attServiceImpl")
	private AttService attService;

	/**
	 * 산출물관리 목록을 조회합니다.
	 */
	@ElService(key = "OUT001List")
	@RequestMapping(value = "OUT001List")
	@ElDescription(sub = "산출물관리 목록조회", desc = "페이징을 처리하여 산출물관리 목록 조회를 한다.")
	public OutListVo selectListOut(OutVo outVo) throws Exception {

		List<OutVo> outList = outService.selectListOut(outVo);
		long totCnt = outService.selectListCountOut(outVo);

		OutListVo retOutList = new OutListVo();
		retOutList.setOutVoList(outList);
		retOutList.setTotalCount(totCnt);
		retOutList.setPageSize(outVo.getPageSize());
		retOutList.setPageIndex(outVo.getPageIndex());

		AppLog.info("retOutList" + retOutList.toString());

		return retOutList;
	}

	/**
	 * 산출물관리을 단건 조회 처리 한다.
	 */
	@ElService(key = "OUT001UpdView")
	@RequestMapping(value = "OUT001UpdView")
	@ElDescription(sub = "산출물관리 갱신 폼을 위한 조회", desc = "산출물관리 갱신 폼을 위한 조회를 한다.")
	public OutVo selectOut(OutVo outVo) throws Exception {
		OutVo selectOutVo = outService.selectOut(outVo);
		return selectOutVo;
	}

	/**
	 * 산출물관리를 등록 처리 한다. (단순 등록)
	 */
	@ElService(key = "OUT001Ins")
	@RequestMapping(value = "OUT001Ins")
	@ElDescription(sub = "산출물관리 등록처리", desc = "산출물관리를 등록 처리 한다.")
	public void insertOut(OutVo outVo) throws Exception {
		outService.insertOut(outVo);
	}

	/**
	 * 산출물관리를 파일과 함께 등록 처리 한다. (트랜잭션)
	 */
	@ElService(key = "OUT001InsWithFiles")
	@RequestMapping(value = "OUT001InsWithFiles")
	@ElDescription(sub = "산출물관리 파일 등록처리", desc = "산출물관리를 파일과 함께 등록 처리 한다.")
	public void insertOutWithFiles(HttpServletRequest request) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		OutVo outVo = new OutVo();
		outVo.setPjtId(request.getParameter("pjtId"));
		outVo.setName(request.getParameter("name"));
		outVo.setApprovalStatus(request.getParameter("approvalStatus"));
		outVo.setApprovalDate(request.getParameter("approvalDate"));
		outVo.setApprovalComment(request.getParameter("approvalComment"));
		outVo.setOutputType(request.getParameter("outputType"));

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

		outService.insertOutWithFiles(outVo, files);

	}

	/**
	 * 산출물관리를 갱신 처리 한다. (단순 수정)
	 */
	@ElService(key = "OUT001Upd")
	@RequestMapping(value = "OUT001Upd")
	@ElValidator(errUrl = "/out/outRegister", errContinue = true)
	@ElDescription(sub = "산출물관리 갱신처리", desc = "산출물관리를 갱신 처리 한다.")
	public void updateOut(OutVo outVo) throws Exception {
		outService.updateOut(outVo);
	}

	/**
	 * 산출물관리를 파일과 함께 갱신 처리 한다. (트랜잭션)
	 */
	@ElService(key = "OUT001UpdWithFiles")
	@RequestMapping(value = "OUT001UpdWithFiles")
	@ElDescription(sub = "산출물관리 파일 갱신처리", desc = "산출물관리를 파일과 함께 갱신 처리 한다.")
	public void updateOutWithFiles(HttpServletRequest request) throws Exception {


		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		OutVo outVo = new OutVo();
		outVo.setId(request.getParameter("id")); 
		outVo.setPjtId(request.getParameter("pjtId"));
		outVo.setName(request.getParameter("name"));
		outVo.setApprovalStatus(request.getParameter("approvalStatus"));
		outVo.setApprovalDate(request.getParameter("approvalDate"));
		outVo.setApprovalComment(request.getParameter("approvalComment"));
		outVo.setOutputType(request.getParameter("outputType"));


		if (outVo.getId() == null || outVo.getId().trim().isEmpty()) {
			throw new RuntimeException("수정할 산출물 ID가 필요합니다.");
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

		outService.updateOutWithFiles(outVo, files);

	}

	/**
	 * 산출물관리를 삭제 처리한다.
	 */
	@ElService(key = "OUT001Del")
	@RequestMapping(value = "OUT001Del")
	@ElDescription(sub = "산출물관리 삭제처리", desc = "산출물관리를 삭제 처리한다.")
	public void deleteOut(OutVo outVo) throws Exception {
		outService.deleteOut(outVo);
	}


	/**
	 * 산출물 파일 업로드 (별도)
	 */
	@ElService(key = "OUT001FileUpload")
	@RequestMapping(value = "OUT001FileUpload")
	@ElDescription(sub = "산출물 파일 업로드", desc = "산출물에 파일을 업로드한다.")
	@ResponseBody
	public List<AttVo> uploadOutFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("outputId") String outputId) throws Exception {


		try {
			return attService.uploadFiles(files, "OUTPUT", outputId);
		} catch (Exception e) {
			throw new ElException("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 산출물 파일 목록 조회
	 */
	@ElService(key = "OUT001FileList")
	@RequestMapping(value = "OUT001FileList")
	@ElDescription(sub = "산출물 파일 목록 조회", desc = "산출물의 파일 목록을 조회한다.")
	public AttListVo getOutFileList(OutVo outVo) throws Exception {


		ProworksCommVO fileParam = new ProworksCommVO();
		fileParam.setRefType("OUTPUT");
		fileParam.setRefId(outVo.getId());


		List<AttVo> attList = attService.getFileList(fileParam);

		AttListVo retAttList = new AttListVo();
		retAttList.setAttVoList(attList);

		return retAttList;
	}

	/**
	 * 산출물 파일 삭제
	 */
	@ElService(key = "OUT001FileDelete")
	@RequestMapping(value = "OUT001FileDelete")
	@ElDescription(sub = "산출물 파일 삭제", desc = "산출물의 파일을 삭제한다.")
	@ResponseBody
	public void deleteOutFile(@RequestParam("fileId") String fileId) throws Exception {
		System.out.println("=== 산출물 파일 삭제 ===");
		System.out.println("파일 ID: " + fileId);

		try {
			attService.deleteFile(fileId);
		} catch (Exception e) {
			throw new ElException("파일 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	
}