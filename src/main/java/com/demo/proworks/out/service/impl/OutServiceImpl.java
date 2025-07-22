package com.demo.proworks.out.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.demo.proworks.att.dao.AttDAO;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.out.dao.OutDAO;
import com.demo.proworks.out.service.OutService;
import com.demo.proworks.out.vo.OutVo;

/**  
 * @subject     : 산출물관리 관련 처리를 담당하는 ServiceImpl
 * @description : 산출물관리 관련 처리를 담당하는 ServiceImpl
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			우민지			최초 생성
 * 2025/07/22			우민지			파일 관련 메서드 추가
 * 
 */
@Service("outServiceImpl")
public class OutServiceImpl implements OutService {

    @Resource(name = "outDAO")
    private OutDAO outDAO;

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

    @Override
    public OutVo selectOut(OutVo vo) throws Exception {
        return outDAO.selectOut(vo);
    }

    @Override
    public List<OutVo> selectListOut(OutVo vo) throws Exception {
        return outDAO.selectListOut(vo);
    }

    @Override
    public long selectListCountOut(OutVo vo) throws Exception {
        return outDAO.selectListCountOut(vo);
    }

    @Override
    public void insertOut(OutVo vo) throws Exception {
        outDAO.insertOut(vo);
    }

    @Override
    public void updateOut(OutVo vo) throws Exception {
        outDAO.updateOut(vo);
    }

    @Override
    public void deleteOut(OutVo vo) throws Exception {
        outDAO.deleteOut(vo);
    }

    /**
     * 산출물과 파일을 함께 등록한다. (트랜잭션)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OutVo insertOutWithFiles(OutVo outVo, MultipartFile[] files) throws Exception {
        List<String> uploadedS3Keys = new ArrayList<>();

        try {
            System.out.println("=== 산출물 등록 with 파일 시작 ===");
            System.out.println("산출물 정보: " + outVo.toString());

            // 1. 산출물 등록
            outDAO.insertOut(outVo);
            String outputId = outVo.getId();
            if (outputId == null) {
                throw new RuntimeException("산출물 ID 생성 실패");
            }
            System.out.println("산출물 등록 완료, ID: " + outputId);

            // 2. 파일 업로드 및 연결
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo attVo = uploadAndSaveFile(file, "OUTPUT", outputId);
                        uploadedS3Keys.add(attVo.getS3Key());
                        System.out.println("파일 업로드 완료: " + file.getOriginalFilename());
                    }
                }
            }

            System.out.println("=== 산출물 등록 with 파일 완료 ===");
            return outVo;

        } catch (Exception e) {
            System.err.println("산출물 등록 실패: " + e.getMessage());

            // S3에서 업로드된 파일들 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    System.out.println("S3 파일 롤백 완료: " + s3Key);
                } catch (Exception s3Exception) {
                    System.err.println("S3 파일 롤백 실패: " + s3Key + " - " + s3Exception.getMessage());
                }
            }

            throw new RuntimeException("산출물 등록 실패: " + e.getMessage(), e);
        }
    }

    /**
     * 산출물과 파일을 함께 수정한다. (트랜잭션)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OutVo updateOutWithFiles(OutVo outVo, MultipartFile[] files) throws Exception {
        List<String> uploadedS3Keys = new ArrayList<>();

        try {
            System.out.println("=== 산출물 수정 with 파일 시작 ===");
            System.out.println("수정할 산출물 ID: " + outVo.getId());

            // 1. 산출물 ID 유효성 검사
            if (outVo.getId() == null || outVo.getId().trim().isEmpty()) {
                throw new RuntimeException("수정할 산출물 ID가 필요합니다.");
            }

            // 2. 기존 산출물 존재 여부 확인
            OutVo existingOutput = outDAO.selectOut(outVo);
            if (existingOutput == null) {
                throw new RuntimeException("수정할 산출물을 찾을 수 없습니다. ID: " + outVo.getId());
            }

            // 3. 산출물 정보 수정
            int updateResult = outDAO.updateOut(outVo);
            if (updateResult <= 0) {
                throw new RuntimeException("산출물 정보 수정 실패");
            }
            System.out.println("산출물 정보 수정 완료");

            // 4. 새로운 파일 업로드 (기존 파일은 유지)
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo attVo = uploadAndSaveFile(file, "OUTPUT", outVo.getId());
                        uploadedS3Keys.add(attVo.getS3Key());
                        System.out.println("새 파일 업로드: " + file.getOriginalFilename());
                    }
                }
            }

            System.out.println("=== 산출물 수정 with 파일 완료 ===");
            return outVo;

        } catch (Exception e) {
            System.err.println("산출물 수정 실패: " + e.getMessage());

            // 새로 업로드된 파일들 S3에서 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    System.out.println("S3 파일 롤백: " + s3Key);
                } catch (Exception ignored) {
                }
            }

            throw new RuntimeException("산출물 수정 실패: " + e.getMessage(), e);
        }
    }

    /**
     * 단일 파일 업로드 및 DB 저장 (내부용)
     */
    private AttVo uploadAndSaveFile(MultipartFile file, String refType, String refId) throws Exception {
        // 1. UUID로 파일명 생성
        String originalFileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String s3Key = uploadPath + "/" + refType + "/" + refId + "/" + storedFileName;

        // 2. S3에 파일 업로드
        com.amazonaws.services.s3.model.ObjectMetadata metadata = new com.amazonaws.services.s3.model.ObjectMetadata();
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
}