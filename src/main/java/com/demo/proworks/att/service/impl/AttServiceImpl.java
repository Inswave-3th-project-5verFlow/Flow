package com.demo.proworks.att.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.demo.proworks.att.dao.AttDAO;
import com.demo.proworks.att.service.AttService;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.cmmn.ProworksCommVO;
import com.demo.proworks.iss.vo.IssVo;

/**  
 * @subject     : 파일첨부 관련 처리를 담당하는 ServiceImpl
 * @description	: 파일첨부 관련 처리를 담당하는 ServiceImpl
 * @author      : 우민지
 * @since       : 2025/07/11
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/11			우민지			최초 생성
 * 
 */
@Service("attServiceImpl")
public class AttServiceImpl implements AttService {

	@Resource(name = "attDAO")
    private AttDAO attDAO;

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    @Resource
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${file.upload.path}")
    private String uploadPath;

    /**
     * 멀티파일 업로드 처리
     * 트랜잭션으로 처리하여 하나라도 실패하면 전체 롤백
     *
     * @param  files MultipartFile[] 업로드할 파일 배열
     * @param  refType String 참조 타입 (ISSUE_RISK, DEFECT 등)
     * @param  refId String 참조 ID
     * @return 업로드된 파일 목록 List<AttVo>
     * @throws Exception
     */
   
	@Override
	public List<AttVo> uploadFiles(MultipartFile[] files, String refType, String refId) throws Exception {
        System.out.println("=== AttService: 멀티파일 업로드 시작 ===");
        System.out.println("참조 타입: " + refType + ", 참조 ID: " + refId);
        System.out.println("파일 개수: " + (files != null ? files.length : 0));
        
		List<AttVo> uploadedFiles = new ArrayList<>();
        List<String> uploadedS3Keys = new ArrayList<>(); // 롤백용

        try {
            if (files != null) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo fileVo = uploadSingleFileInternal(file, refType, refId);
                        uploadedFiles.add(fileVo);
                        uploadedS3Keys.add(fileVo.getS3Key());
                        
                        System.out.println("파일 업로드 성공: " + file.getOriginalFilename());
                    }
                }
            }

            System.out.println("=== AttService: 멀티파일 업로드 완료 ===");
            return uploadedFiles;
            
        } catch (Exception e) {
            System.err.println("AttService: 파일 업로드 실패, S3 파일 롤백 시작: " + e.getMessage());
            
            // S3에서 업로드된 파일들 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    System.out.println("S3 파일 롤백 완료: " + s3Key);
                } catch (Exception s3Exception) {
                    System.err.println("S3 파일 롤백 실패: " + s3Key + " - " + s3Exception.getMessage());
                }
            }
            
            // 트랜잭션 롤백을 위해 예외 재발생
            throw new RuntimeException("파일 업로드 실패: " + e.getMessage(), e);
        }
	}
	
	/**
     * 단일 파일 업로드 처리 (public)
     * 외부에서 단일 파일 업로드 시 사용
     */
   
    @Override
    public AttVo uploadSingleFile(MultipartFile file, String refType, String refId) throws Exception {
        System.out.println("=== AttService: 단일 파일 업로드 ===");
        System.out.println("파일명: " + file.getOriginalFilename());
        
        try {
            return uploadSingleFileInternal(file, refType, refId);
        } catch (Exception e) {
            System.err.println("AttService: 단일 파일 업로드 실패: " + e.getMessage());
            throw new RuntimeException("파일 업로드 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 단일 파일 업로드 처리 (내부용)
     * 실제 파일 업로드 로직
     */
    private AttVo uploadSingleFileInternal(MultipartFile file, String refType, String refId) throws Exception {
        // 1. UUID로 파일명 생성
        String originalFileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String s3Key = uploadPath + "/" + refType + "/" + refId + "/" + storedFileName;

        // 2. S3에 파일 업로드
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucketName, s3Key, file.getInputStream(), metadata);
        System.out.println("S3 업로드 완료: " + s3Key);

        // 3. file 테이블에 저장
        AttVo fileVo = new AttVo();
        String fileId = "FILE_" + UUID.randomUUID().toString();
        
        fileVo.setFileId(fileId);
        fileVo.setOriginalFileName(originalFileName);
        fileVo.setStoredFileName(storedFileName);
        fileVo.setFileSize(String.valueOf(file.getSize()));
        fileVo.setFileExtension(fileExtension);
        fileVo.setS3Bucket(bucketName);
        fileVo.setS3Key(s3Key);
        
        int fileResult = attDAO.insertFile(fileVo);
        if (fileResult <= 0) {
            throw new RuntimeException("file 테이블 저장 실패");
        }
        System.out.println("file 테이블 저장 완료: " + fileId);

        // 4. file_attachments 테이블에 연결 정보 저장
        AttVo attachmentVo = new AttVo();
        attachmentVo.setId("ATT_" + UUID.randomUUID().toString());
        attachmentVo.setFileId(fileId);
        attachmentVo.setRefType(refType);
        attachmentVo.setRefId(refId);
        attachmentVo.setIsDeleted("N");

        int attachResult = attDAO.insertFileAttachment(attachmentVo);
        if (attachResult <= 0) {
            throw new RuntimeException("file_attachments 테이블 저장 실패");
        }
        System.out.println("file_attachments 테이블 저장 완료");

        // 5. 반환용 데이터 설정
        AttVo resultVo = new AttVo();
        resultVo.setId(attachmentVo.getId());
        resultVo.setFileId(fileId);
        resultVo.setRefType(refType);
        resultVo.setRefId(refId);
        resultVo.setOriginalFileName(originalFileName);
        resultVo.setStoredFileName(storedFileName);
        resultVo.setFileSize(String.valueOf(file.getSize()));
        resultVo.setFileExtension(fileExtension);
        resultVo.setS3Bucket(bucketName);
        resultVo.setS3Key(s3Key);
        resultVo.setIsDeleted("N");
        resultVo.setAttachedAt(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));

        return resultVo;
    }

    /**
     * 파일 목록 조회
     */
    @Override
    public List<AttVo> getFileList(ProworksCommVO vo) throws Exception {
        
        return attDAO.selectFileListByRef(vo);
    }

    /**
     * 파일 다운로드
     */
    @Override
	public S3Object downloadFile(String fileId) throws Exception {
	    System.out.println("=== AttService: 파일 다운로드 ===");
	    System.out.println("파일 ID: " + fileId);
	    
	    AttVo fileVo = attDAO.selectFileInfo(fileId);
	    if (fileVo == null) {
	        throw new RuntimeException("파일을 찾을 수 없습니다. ID: " + fileId);
	    }
	
	    System.out.println("S3 버킷: " + bucketName);
	    System.out.println("S3 키: " + fileVo.getS3Key());
	    System.out.println("원본 파일명: " + fileVo.getOriginalFileName());
	    System.out.println("저장 파일명: " + fileVo.getStoredFileName());
	    
	    // S3 키가 null이거나 비어있는 경우 확인
	    if (fileVo.getS3Key() == null || fileVo.getS3Key().trim().isEmpty()) {
	        throw new RuntimeException("S3 키 정보가 없습니다. 파일이 손상되었을 수 있습니다.");
	    }
	    
	    try {
	        // S3에서 객체 존재 여부 확인
	        System.out.println("S3 객체 존재 여부 확인 중...");
	        boolean exists = amazonS3.doesObjectExist(bucketName, fileVo.getS3Key());
	        System.out.println("S3 객체 존재 여부: " + exists);
	        
	        if (!exists) {
	            // 다른 가능한 경로들 확인
	            String[] possiblePaths = {
	                fileVo.getS3Key(),
	                "uploads/" + fileVo.getS3Key(),
	                uploadPath + "/" + fileVo.getS3Key(),
	                uploadPath + "/UNIT_TEST/" + fileVo.getStoredFileName(),
	                "files/" + fileVo.getStoredFileName(),
	                fileVo.getStoredFileName()
	            };
	            
	            System.out.println("다른 가능한 경로들 확인:");
	            for (String path : possiblePaths) {
	                System.out.println("경로 확인: " + path);
	                if (amazonS3.doesObjectExist(bucketName, path)) {
	                    System.out.println("발견된 올바른 경로: " + path);
	                    fileVo.setS3Key(path); // 올바른 경로로 업데이트
	                    exists = true;
	                    break;
	                }
	            }
	            
	            if (!exists) {
	                throw new RuntimeException("S3에서 파일을 찾을 수 없습니다. 키: " + fileVo.getS3Key());
	            }
	        }
	        
	        System.out.println("S3에서 파일 다운로드 시작: " + fileVo.getS3Key());
	        S3Object s3Object = amazonS3.getObject(bucketName, fileVo.getS3Key());
	        
	        // 파일 메타데이터 확인
	        System.out.println("파일 크기: " + s3Object.getObjectMetadata().getContentLength());
	        System.out.println("콘텐츠 타입: " + s3Object.getObjectMetadata().getContentType());
	        
	        System.out.println("S3에서 파일 다운로드 성공");
	        return s3Object;
	        
	    } catch (Exception e) {
	        System.err.println("S3 다운로드 실패: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException("S3에서 파일 다운로드 실패: " + e.getMessage(), e);
	    }
	}

    /**
     * 파일 삭제 처리
     * DB에서 논리 삭제 후 S3에서 물리 삭제
     */
   
    @Override
    public void deleteFile(String fileId) throws Exception {
        System.out.println("=== AttService: 파일 삭제 ===");
        System.out.println("파일 ID: " + fileId);
        
        try {
            // 1. 파일 정보 조회
            AttVo fileVo = attDAO.selectFileInfo(fileId);
            if (fileVo == null) {
                throw new RuntimeException("삭제할 파일을 찾을 수 없습니다. ID: " + fileId);
            }

            // 2. DB에서 논리 삭제
            int dbResult = attDAO.deleteFileAttachment(fileId);
            if (dbResult <= 0) {
                throw new RuntimeException("DB 파일 삭제 실패");
            }
            System.out.println("DB 논리 삭제 완료");

            // 3. S3에서 물리 삭제
            amazonS3.deleteObject(bucketName, fileVo.getS3Key());
            System.out.println("S3 물리 삭제 완료: " + fileVo.getS3Key());
            
        } catch (Exception e) {
            System.err.println("파일 삭제 실패: " + e.getMessage());
            throw new RuntimeException("파일 삭제 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 파일 상세 정보 조회
     */
    @Override
    public AttVo getFileInfo(String fileId) throws Exception {
        System.out.println("=== AttService: 파일 상세 조회 ===");
        System.out.println("파일 ID: " + fileId);
        
        AttVo fileVo = attDAO.selectFileInfo(fileId);
        System.out.println("fileVo: " + fileVo);
        if (fileVo == null) {
            System.out.println("파일을 찾을 수 없습니다.");
        } else {
            System.out.println("파일명: " + fileVo.getOriginalFileName());
        }
        
        return fileVo;
    }

    /**
     * 파일 확장자 추출
     */
    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
    
    /**
     * 특정 참조의 모든 파일 완전 삭제 (UnitTest 등에서 사용)
     * 
     * @param refType 참조 타입 (예: UNIT_TEST)
     * @param refId 참조 ID (예: TC_001)
     * @throws Exception
     */
    public void deleteFilesByRef(String refType, String refId){
        System.out.println("=== AttService: 참조별 파일 Hard Delete ===");
        System.out.println("참조 타입: " + refType + ", 참조 ID: " + refId);
        
        try {
            // 1. 해당 참조의 모든 파일 목록 조회
            ProworksCommVO searchVo = new ProworksCommVO();
            searchVo.setRefType(refType);
            searchVo.setRefId(refId);
            
            List<AttVo> fileList = attDAO.selectFileListByRef(searchVo);
            System.out.println("삭제 대상 파일 수: " + fileList.size());

            // 2. S3에서 각 파일들 물리 삭제
            for (AttVo fileVo : fileList) {
                try {
                    if (fileVo.getS3Key() != null && !fileVo.getS3Key().trim().isEmpty()) {
                        amazonS3.deleteObject(bucketName, fileVo.getS3Key());
                        System.out.println("S3 파일 삭제: " + fileVo.getOriginalFileName());
                    }
                } catch (Exception s3Exception) {
                    System.err.println("S3 파일 삭제 실패: " + fileVo.getOriginalFileName() + " - " + s3Exception.getMessage());
                    // S3 삭제 실패해도 계속 진행 (이미 삭제된 파일일 수 있음)
                }
            }

            // 3. DB에서 파일 레코드들 Hard Delete
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("refType", refType);
            paramMap.put("refId", refId);
            
            int deleteCount = attDAO.hardDeleteFilesByRef(paramMap);
            System.out.println("DB 파일 레코드 삭제 완료: " + deleteCount + "건");
            
        } catch (Exception e) {
            System.err.println("참조별 파일 Hard Delete 실패: " + e.getMessage());
            throw new RuntimeException("파일 삭제 중 오류 발생: " + e.getMessage(), e);
        }
    }
}