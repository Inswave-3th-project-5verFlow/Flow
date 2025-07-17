package com.demo.proworks.att.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.demo.proworks.att.service.AttService;
import com.demo.proworks.att.vo.AttListVo;
import com.demo.proworks.att.vo.AttVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import org.springframework.web.bind.annotation.RequestMethod;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 파일관리 관련 처리를 담당하는 컨트롤러
 * @description : 파일관리 관련 처리를 담당하는 컨트롤러
 * @author      : 우민지
 * @since       : 2025/07/11
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/11			 우민지	 		최초 생성
 * 2025/07/15			 우민지	 		파일 관련 메서드 통합
 * 
 */
@Controller
public class AttController {
	
    @Resource(name = "attServiceImpl")
    private AttService attService;
    
    /**
     * 멀티파일 업로드 처리
     */
    @ElService(key="FileUpload")
    @RequestMapping(value="FileUpload")    
    @ElDescription(sub="파일 업로드",desc="멀티파일 업로드를 처리한다.")
    @ResponseBody
    public List<AttVo> uploadFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("refType") String refType,
            @RequestParam("refId") String refId) throws Exception {
        
        try {
            return attService.uploadFiles(files, refType, refId);
        } catch (Exception e) {
            // 로그 기록
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 파일 목록 조회 (JSON 방식)
     * @throws Exception 
     */
    @ElService(key="FileList")
    @RequestMapping(value="FileList")    
    @ElDescription(sub="파일 목록 조회",desc="파일 목록을 조회한다.")
    public ResponseEntity<List<AttVo>> getFileList(HttpServletRequest request) throws Exception {
        // Step 1: JSON 데이터 읽기
        StringBuilder jsonString = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        }

        // Step 2: JSON 파싱
        JSONObject json = new JSONObject(jsonString.toString());
        String refType = json.optString("refType");
        String refId = json.optString("refId");

        // Debugging: 로그로 파라미터 확인
        System.out.println("Received refType: " + refType);
        System.out.println("Received refId: " + refId);

        // Step 3: 파라미터 유효성 검사
        if (refType == null || refId == null || refType.trim().isEmpty() || refId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        // Step 4: 서비스 호출
        // List<AttVo> files = attService.getFileList(refType, refId);

        // Step 5: 파일 목록 반환
        return null;
    }

    /**
     * 이미지 미리보기 (별도 엔드포인트)
     */
    @ElService(key = "ImagePreview")
	@RequestMapping(value = "ImagePreview")
    @ElDescription(sub = "이미지 미리보기", desc = "이미지 파일을 미리보기한다.")
    public void imagePreview(@RequestParam("fileId") String fileId,
                        HttpServletResponse response) throws Exception {
    
	    S3Object s3Object = null;
	    InputStream inputStream = null;
	    
	    try {
	        System.out.println("=== 이미지 미리보기 시작 ===");
	        System.out.println("파일 ID: " + fileId);
	        
	        // 파일 정보 조회
	        AttVo fileVo = attService.getFileInfo(fileId);
	        if (fileVo == null) {
	            System.err.println("파일 정보를 찾을 수 없습니다: " + fileId);
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
	            return;
	        }
	        
	        System.out.println("파일 정보: " + fileVo.toString());
	        
	        // 파일 확장자 추출 (null 체크)
	        String ext = null;
	        if (fileVo.getFileExtension() != null) {
	            ext = fileVo.getFileExtension().toLowerCase();
	        } else if (fileVo.getOriginalFileName() != null) {
	            // 파일명에서 확장자 추출
	            String fileName = fileVo.getOriginalFileName();
	            int lastDotIndex = fileName.lastIndexOf(".");
	            if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
	                ext = fileName.substring(lastDotIndex + 1).toLowerCase();
	            }
	        }
	        
	        System.out.println("추출된 확장자: " + ext);
	        
	        // 이미지 파일인지 확인
	        if (ext == null || !isImageExtension(ext)) {
	            System.err.println("이미지 파일이 아닙니다: " + ext);
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "이미지 파일이 아닙니다.");
	            return;
	        }
	        
	        // S3 키 확인
	        if (fileVo.getS3Key() == null || fileVo.getS3Key().trim().isEmpty()) {
	            System.err.println("S3 키가 없습니다: " + fileVo.getS3Key());
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일 경로 정보가 없습니다.");
	            return;
	        }
	        
	        // S3에서 파일 가져오기
	        System.out.println("S3에서 파일 다운로드 시작: " + fileVo.getS3Key());
	        s3Object = attService.downloadFile(fileId);
	        
	        // 응답 헤더 설정
	        response.setContentType(getContentType(ext));
	        response.setHeader("Content-Disposition", "inline; filename=\"" + fileVo.getOriginalFileName() + "\"");
	        response.setHeader("Cache-Control", "public, max-age=3600");
	        
	        // 파일 스트림 복사
	        inputStream = s3Object.getObjectContent();
	        FileCopyUtils.copy(inputStream, response.getOutputStream());
	        
	        System.out.println("이미지 미리보기 완료");
	        
	    } catch (Exception e) {
	        System.err.println("이미지 미리보기 실패: " + e.getMessage());
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "이미지 로드 실패: " + e.getMessage());
	    } finally {
	        try {
	            if (inputStream != null) inputStream.close();
	            if (s3Object != null) s3Object.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

    /**
     * 파일 미리보기 (텍스트 파일용)
     */
    @ElService(key = "FilePreview")
	@RequestMapping(value = "FilePreview")
    @ElDescription(sub = "파일 미리보기", desc = "텍스트 파일의 내용을 미리보기한다.")
    @ResponseBody
    public Map<String, Object> previewTextFile(@RequestParam("fileId") String fileId) throws Exception {

	    S3Object s3Object = null;
	    InputStream inputStream = null;
	    Map<String, Object> result = new HashMap<>();
	
	    try {
	        System.out.println("=== 파일 미리보기 시작 ===");
	        
	        // 파일 정보 조회
	        AttVo fileVo = attService.getFileInfo(fileId);
	        if (fileVo == null) {
	            result.put("success", false);
	            result.put("message", "파일을 찾을 수 없습니다.");
	            return result;
	        }
	
	        // S3에서 파일 다운로드
	        s3Object = attService.downloadFile(fileId);
	        inputStream = s3Object.getObjectContent();
	
	        // 텍스트 내용 읽기
	        StringBuilder content = new StringBuilder();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	        String line;
	        int charCount = 0;
	        int maxChars = 5000;
	
	        while ((line = reader.readLine()) != null && charCount < maxChars) {
	            if (charCount + line.length() > maxChars) {
	                content.append(line.substring(0, maxChars - charCount));
	                content.append("\n\n... (파일 내용이 길어서 일부만 표시됩니다)");
	                break;
	            }
	            content.append(line).append("\n");
	            charCount += line.length() + 1;
	        }
	
	        // Base64 인코딩으로 안전하게 전송
	        String contentString = content.toString();
	        String encodedContent = java.util.Base64.getEncoder().encodeToString(
	            contentString.getBytes("UTF-8")
	        );
	
	        result.put("success", true);
	        result.put("content", encodedContent);
	        result.put("fileName", fileVo.getOriginalFileName());
	        result.put("encoding", "base64");
	        
	        return result;
	
	    } catch (Exception e) {
	        System.err.println("파일 미리보기 실패: " + e.getMessage());
	        result.put("success", false);
	        result.put("message", "파일을 읽을 수 없습니다: " + e.getMessage());
	        return result;
	    } finally {
	        try {
	            if (inputStream != null) inputStream.close();
	            if (s3Object != null) s3Object.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


    /**
     * 파일 다운로드
     */
    @RequestMapping(value = "FileDownload")
    @ElDescription(sub = "파일 다운로드", desc = "파일을 다운로드한다.")
    public void downloadFile(@RequestParam("fileId") String fileId,
            @RequestParam(value = "preview", required = false, defaultValue = "false") boolean isPreview,
            @RequestParam(value = "inline", required = false, defaultValue = "false") boolean isInline,
            HttpServletResponse response) throws Exception {

        S3Object s3Object = null;
        InputStream inputStream = null;

        try {
            s3Object = attService.downloadFile(fileId);

            // 파일 정보 조회
            AttVo fileVo = attService.getFileInfo(fileId);
            if (fileVo == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
                return;
            }

            // 응답 헤더 설정
            String contentType = getContentType(fileVo.getFileExtension());
            response.setContentType(contentType);

            // 미리보기 모드인 경우
            if (isPreview || isInline) {
                response.setHeader("Content-Disposition",
                        "inline; filename=\"" + URLEncoder.encode(fileVo.getOriginalFileName(), "UTF-8") + "\"");
            } else {
                // 일반 다운로드
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + URLEncoder.encode(fileVo.getOriginalFileName(), "UTF-8") + "\"");
            }

            // 파일 크기 설정
            try {
                long fileSize = Long.parseLong(fileVo.getFileSize());
                response.setContentLengthLong(fileSize);
            } catch (NumberFormatException e) {
                System.out.println("파일 크기 설정 실패: " + fileVo.getFileSize());
            }

            // 캐시 헤더 설정 (이미지 미리보기용)
            if (isPreview || isInline) {
                response.setHeader("Cache-Control", "public, max-age=3600");
                response.setDateHeader("Expires", System.currentTimeMillis() + 3600000);
            }

            // 파일 데이터 전송
            inputStream = s3Object.getObjectContent();
            FileCopyUtils.copy(inputStream, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "파일 다운로드 중 오류가 발생했습니다: " + e.getMessage());
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (s3Object != null) s3Object.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 파일 삭제
     */
    @ElService(key="FileDelete")
    @RequestMapping(value="FileDelete")    
    @ElDescription(sub="파일 삭제",desc="파일을 삭제한다.")
    @ResponseBody
    public void deleteFile(@RequestParam("fileId") String fileId) throws Exception {
        
        try {
            attService.deleteFile(fileId);
        } catch (Exception e) {
            // 로그 기록
            e.printStackTrace();
            throw new RuntimeException("파일 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 파일 정보 조회 (메타데이터용)
     */
    @ElService(key = "FileInfo")
    @RequestMapping(value = "FileInfo")
    @ElDescription(sub = "파일 정보 조회", desc = "파일의 상세 정보를 조회한다.")
    @ResponseBody
    public AttVo getFileInfo(@RequestParam("fileId") String fileId) throws Exception {

        try {
            AttVo fileVo = attService.getFileInfo(fileId);
            if (fileVo == null) {
                throw new RuntimeException("파일을 찾을 수 없습니다.");
            }

            // 추가 정보 설정 (파일 타입 분류 등)
            String ext = fileVo.getFileExtension().toLowerCase();
            String fileType = "기타";

            if (isImageExtension(ext)) {
                fileType = "이미지";
            } else if (isTextExtension(ext)) {
                fileType = "텍스트";
            } else if (isDocumentExtension(ext)) {
                fileType = "문서";
            } else if (isArchiveExtension(ext)) {
                fileType = "압축파일";
            }

            return fileVo;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일 정보 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // ===== 유틸리티 메서드들 =====

    /**
     * 파일 확장자에 따른 Content-Type 반환
     */
    private String getContentType(String fileExtension) {
        if (fileExtension == null) return "application/octet-stream";

        String ext = fileExtension.toLowerCase();
        switch (ext) {
            // 이미지
            case "jpg":
            case "jpeg": return "image/jpeg";
            case "png": return "image/png";
            case "gif": return "image/gif";
            case "bmp": return "image/bmp";
            case "svg": return "image/svg+xml";
            case "webp": return "image/webp";

            // 텍스트
            case "txt": return "text/plain; charset=UTF-8";
            case "html":
            case "htm": return "text/html; charset=UTF-8";
            case "css": return "text/css; charset=UTF-8";
            case "js": return "application/javascript; charset=UTF-8";
            case "json": return "application/json; charset=UTF-8";
            case "xml": return "application/xml; charset=UTF-8";
            case "csv": return "text/csv; charset=UTF-8";

            // 문서
            case "pdf": return "application/pdf";
            case "doc": return "application/msword";
            case "docx": return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "xls": return "application/vnd.ms-excel";
            case "xlsx": return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "ppt": return "application/vnd.ms-powerpoint";
            case "pptx": return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            case "hwp": return "application/haansofthwp";

            // 압축
            case "zip": return "application/zip";
            case "rar": return "application/vnd.rar";
            case "7z": return "application/x-7z-compressed";

            default: return "application/octet-stream";
        }
    }

    /**
     * 이미지 파일 확장자 체크
     */
    private boolean isImageExtension(String ext) {
        String[] imageExts = { "jpg", "jpeg", "png", "gif", "bmp", "svg", "webp" };
        return Arrays.asList(imageExts).contains(ext);
    }

    /**
     * 텍스트 파일 확장자 체크
     */
    private boolean isTextExtension(String ext) {
        String[] textExts = { "txt", "log", "csv", "json", "xml", "html", "css", "js", "java", "sql", "properties" };
        return Arrays.asList(textExts).contains(ext);
    }

    /**
     * 문서 파일 확장자 체크
     */
    private boolean isDocumentExtension(String ext) {
        String[] docExts = { "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "hwp" };
        return Arrays.asList(docExts).contains(ext);
    }

    /**
     * 압축 파일 확장자 체크
     */
    private boolean isArchiveExtension(String ext) {
        String[] archiveExts = { "zip", "rar", "7z", "tar", "gz" };
        return Arrays.asList(archiveExts).contains(ext);
    }

    // ===== 프로웍스 표준 헤더 생성 =====

    /**
     * 프로웍스 성공 헤더 생성
     */
    private Map<String, Object> createSuccessHeader() {
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("resSuc", true);
        header.put("resCode", "0000");
        header.put("resMsg", "정상 처리되었습니다.");
        return header;
    }

    /**
     * 프로웍스 에러 헤더 생성
     */
    private Map<String, Object> createErrorHeader(String errorMsg) {
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("resSuc", false);
        header.put("resCode", "9999");
        header.put("resMsg", errorMsg);
        return header;
    }
}