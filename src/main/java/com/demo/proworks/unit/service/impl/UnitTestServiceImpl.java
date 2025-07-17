package com.demo.proworks.unit.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.demo.proworks.att.dao.AttDAO;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.cmmn.ProworksCommVO;
import com.demo.proworks.unit.dao.UnitTestDao;
import com.demo.proworks.unit.service.UnitTestService;
import com.demo.proworks.unit.vo.UnitTestListVo;
import com.demo.proworks.unit.vo.UnitTestVo;

/**
 * 단위테스트 케이스 관리 서비스 구현체
 */
@Service("unitTestServiceImpl")
public class UnitTestServiceImpl implements UnitTestService {
    
    private static final Logger logger = LoggerFactory.getLogger(UnitTestServiceImpl.class);
    
    @Resource(name = "unitTestDao")
    private UnitTestDao unitTestDao;
    
    @Resource(name = "attDAO")
    private AttDAO attDAO;
    
    @Resource
    private AmazonS3 amazonS3;
    
    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    
    @Value("${file.upload.path}")
    private String uploadPath;
    
    // 참조 타입 상수
    private static final String REF_TYPE_UNIT_TEST = "UNIT_TEST";
    
    /**
     * 단위테스트 케이스 목록 조회
     */
    @Override
    public List<UnitTestVo> selectUnitTestList(UnitTestVo unitTestVo) throws Exception{
        logger.debug("단위테스트 케이스 목록 조회 시작: {}", unitTestVo);
        
        return unitTestDao.selectUnitTestList(unitTestVo);
    }
    
    public long selectListCountUnitTest(UnitTestVo unitTestVo) throws Exception {
        return unitTestDao.selectUnitTestListCount(unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 상세 조회
     */
    @Override
    public UnitTestVo selectUnitTestDetail(Map<String, Object> paramMap) throws Exception {
        logger.debug("단위테스트 케이스 상세 조회: {}", paramMap);
        
        UnitTestVo result = unitTestDao.selectUnitTestDetail(paramMap);
        
        if (result == null) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없습니다.");
        }
        
        return result;
    }
    
    /**
     * 단위테스트 케이스 등록
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertUnitTest(Map<String, Object> paramMap) throws Exception {
        logger.debug("단위테스트 케이스 등록 시작: {}", paramMap);
        
        // 필수값 검증
        validateRequiredFields(paramMap);
        
        // 테스트 케이스 ID 생성
        String testCaseId = generateTestCaseId();
        paramMap.put("testCaseId", testCaseId);
        
        // 기본값 설정
        setDefaultValues(paramMap);
        
        // 등록
        int result = unitTestDao.insertUnitTest(paramMap);
        
        if (result > 0) {
            logger.debug("단위테스트 케이스 등록 완료: {}", testCaseId);
            return testCaseId;
        } else {
            throw new Exception("단위테스트 케이스 등록에 실패했습니다.");
        }
    }
    
    /**
     * 단위테스트 케이스와 파일 함께 등록
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertUnitTestWithFiles(Map<String, Object> testCaseMap, List<MultipartFile> files) throws Exception {
        logger.debug("단위테스트 케이스와 파일 함께 등록 시작");
        
        List<String> uploadedS3Keys = new ArrayList<>(); // 롤백용
        
        try {
            // 1. 단위테스트 케이스 등록
            String testCaseId = insertUnitTest(testCaseMap);
            
            // 2. 파일이 있는 경우 파일 업로드 및 DB 저장
            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        String s3Key = uploadUnitTestFile(testCaseId, file, (String) testCaseMap.get("createdBy"));
                        uploadedS3Keys.add(s3Key);
                        logger.debug("파일 업로드 완료: {}", file.getOriginalFilename());
                    }
                }
            }
            
            logger.debug("단위테스트 케이스와 파일 등록 완료: {}", testCaseId);
            return testCaseId;
            
        } catch (Exception e) {
            logger.error("단위테스트 케이스와 파일 등록 실패", e);
            
            // S3에서 업로드된 파일들 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    logger.debug("S3 파일 롤백 완료: {}", s3Key);
                } catch (Exception s3Exception) {
                    logger.error("S3 파일 롤백 실패: " + s3Key, s3Exception);
                }
            }
            
            throw new Exception("단위테스트 케이스와 파일 등록에 실패했습니다: " + e.getMessage(), e);
        }
    }
    
    /**
     * 단위테스트 케이스 수정
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateUnitTest(Map<String, Object> paramMap) throws Exception {
        logger.debug("단위테스트 케이스 수정: {}", paramMap);
        
        // 필수값 검증
        if (isEmpty(paramMap.get("id"))) {
            throw new Exception("수정할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateUnitTest(paramMap);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 수정할 수 없습니다.");
        }
        
        logger.debug("단위테스트 케이스 수정 완료: {}", paramMap.get("id"));
        return result;
    }
    
    /**
     * 단위테스트 케이스 삭제 (논리삭제)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteUnitTest(Map<String, Object> paramMap) throws Exception {
        logger.debug("단위테스트 케이스 삭제: {}", paramMap);
        
        String testCaseId = (String) paramMap.get("id");
        if (isEmpty(testCaseId)) {
            throw new Exception("삭제할 테스트 케이스 ID가 필요합니다.");
        }
        
        // 첨부파일도 함께 논리삭제
        Map<String, Object> fileParam = new HashMap<>();
        fileParam.put("testCaseId", testCaseId);
        
        // 파일 목록 조회 후 논리 삭제
        List<AttVo> fileList = selectUnitTestFileList(fileParam);
        for (AttVo fileVo : fileList) {
            Map<String, Object> deleteFileParam = new HashMap<>();
            deleteFileParam.put("fileId", fileVo.getFileId());
            deleteUnitTestFile(deleteFileParam);
        }
        
        // 테스트 케이스 논리삭제
        int result = unitTestDao.deleteUnitTest(paramMap);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 삭제할 수 없습니다.");
        }
        
        logger.debug("단위테스트 케이스 삭제 완료: {}", testCaseId);
        return result;
    }
    
    /**
     * 단위테스트 케이스 첨부파일 목록 조회
     */
    @Override
    public List<AttVo> selectUnitTestFileList(Map<String, Object> paramMap) throws Exception {
        logger.debug("첨부파일 목록 조회: {}", paramMap);
        
        // AttDAO 조회용 파라미터 설정
        ProworksCommVO commVO = new ProworksCommVO();
        commVO.setRefType(REF_TYPE_UNIT_TEST);
        commVO.setRefId((String) paramMap.get("testCaseId"));
        
        List<AttVo> fileList = attDAO.selectFileListByRef(commVO);
        
        logger.debug("첨부파일 목록 조회 완료: {} 건", fileList.size());
        return fileList;
    }
    
    /**
     * 단위테스트 케이스 첨부파일 삭제
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteUnitTestFile(Map<String, Object> paramMap) throws Exception {
        logger.debug("첨부파일 삭제: {}", paramMap);
        
        String fileId = (String) paramMap.get("fileId");
        if (isEmpty(fileId)) {
            throw new Exception("삭제할 파일 ID가 필요합니다.");
        }
        
        // 파일 정보 조회
        AttVo fileInfo = attDAO.selectFileInfo(fileId);
        
        if (fileInfo != null) {
            // S3에서 물리 삭제
            try {
                if (!isEmpty(fileInfo.getS3Key())) {
                    amazonS3.deleteObject(bucketName, fileInfo.getS3Key());
                    logger.debug("S3 파일 삭제 완료: {}", fileInfo.getS3Key());
                }
            } catch (Exception e) {
                logger.error("S3 파일 삭제 실패: " + fileInfo.getS3Key(), e);
                // S3 삭제 실패해도 DB 삭제는 진행
            }
            
            // DB에서 논리 삭제
            int result = attDAO.deleteFileAttachment(fileId);
            
            logger.debug("첨부파일 삭제 완료: {}", fileId);
            return result;
        }
        
        return 0;
    }
    
    /**
     * 테스트 상태별 통계 조회
     */
    @Override
    public Map<String, Object> selectUnitTestStatistics(Map<String, Object> paramMap) throws Exception {
        logger.debug("통계 조회: {}", paramMap);
        
        List<Map<String, Object>> statistics = unitTestDao.selectUnitTestStatistics(paramMap);
        
        Map<String, Object> result = new HashMap<>();
        int totalCount = 0;
        int pendingCount = 0;
        int runningCount = 0;
        int successCount = 0;
        int failCount = 0;
        
        for (Map<String, Object> stat : statistics) {
            String status = (String) stat.get("testStatus");
            int count = parseInt(stat.get("cnt"), 0);
            
            totalCount += count;
            
            switch (status) {
                case "PEN":
                    pendingCount = count;
                    break;
                case "RUN":
                    runningCount = count;
                    break;
                case "SUC":
                    successCount = count;
                    break;
                case "FAI":
                    failCount = count;
                    break;
            }
        }
        
        result.put("totalCount", totalCount);
        result.put("pendingCount", pendingCount);
        result.put("runningCount", runningCount);
        result.put("successCount", successCount);
        result.put("failCount", failCount);
        result.put("completedCount", successCount + failCount);
        
        // 성공률 계산
        if (totalCount > 0) {
            double successRate = (double) successCount / totalCount * 100;
            result.put("successRate", Math.round(successRate * 100) / 100.0);
        } else {
            result.put("successRate", 0.0);
        }
        
        logger.debug("통계 조회 완료: {}", result);
        return result;
    }
    
    /**
     * 테스트 실행 상태 업데이트
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTestStatus(Map<String, Object> paramMap) throws Exception {
        logger.debug("테스트 상태 업데이트: {}", paramMap);
        
        if (isEmpty(paramMap.get("id"))) {
            throw new Exception("업데이트할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateTestStatus(paramMap);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 상태를 업데이트할 수 없습니다.");
        }
        
        logger.debug("테스트 상태 업데이트 완료: {}", paramMap.get("id"));
        return result;
    }
    
    /**
     * 테스트 결과 업데이트
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTestResult(Map<String, Object> paramMap) throws Exception {
        logger.debug("테스트 결과 업데이트: {}", paramMap);
        
        if (isEmpty(paramMap.get("id"))) {
            throw new Exception("업데이트할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateTestResult(paramMap);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 결과를 업데이트할 수 없습니다.");
        }
        
        logger.debug("테스트 결과 업데이트 완료: {}", paramMap.get("id"));
        return result;
    }
    
    // ===== Private 메서드 =====
    
    /**
     * 파일 업로드 및 DB 등록
     */
    private String uploadUnitTestFile(String testCaseId, MultipartFile file, String uploadedBy) throws Exception {
        logger.debug("파일 업로드 시작: {}", file.getOriginalFilename());
        
        // 파일 정보 추출
        String originalFileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFileName);
        String storedFileName = UUID.randomUUID().toString() + "." + fileExtension;
        String s3Key = uploadPath + "/" + REF_TYPE_UNIT_TEST + "/" + testCaseId + "/" + storedFileName;
        
        // S3에 파일 업로드
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        
        amazonS3.putObject(bucketName, s3Key, file.getInputStream(), metadata);
        logger.debug("S3 업로드 완료: {}", s3Key);
        
        // file 테이블에 저장
        AttVo fileVo = new AttVo();
        String fileId = "FILE_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        
        fileVo.setFileId(fileId);
        fileVo.setOriginalFileName(originalFileName);
        fileVo.setStoredFileName(storedFileName);
        fileVo.setFileSize(String.valueOf(file.getSize()));
        fileVo.setFileExtension(fileExtension);
        fileVo.setS3Bucket(bucketName);
        fileVo.setS3Key(s3Key);
        
        int fileResult = attDAO.insertFile(fileVo);
        if (fileResult <= 0) {
            throw new Exception("file 테이블 저장 실패");
        }
        
        // file_attachments 테이블에 연결 정보 저장
        AttVo attachmentVo = new AttVo();
        attachmentVo.setId("ATT_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20));
        attachmentVo.setFileId(fileId);
        attachmentVo.setRefType(REF_TYPE_UNIT_TEST);
        attachmentVo.setRefId(testCaseId);
        attachmentVo.setIsDeleted("0");
        
        int attachResult = attDAO.insertFileAttachment(attachmentVo);
        if (attachResult <= 0) {
            throw new Exception("file_attachments 테이블 저장 실패");
        }
        
        logger.debug("파일 업로드 완료: {}", originalFileName);
        return s3Key;
    }
    
    /**
     * 테스트 케이스 ID 생성
     */
    private String generateTestCaseId() {
        return "TC_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
    }
    
    /**
     * 필수값 검증
     */
    private void validateRequiredFields(Map<String, Object> paramMap) throws Exception {
        if (isEmpty(paramMap.get("testCaseName"))) {
            throw new Exception("테스트케이스명은 필수입니다.");
        }
        if (isEmpty(paramMap.get("testTarget"))) {
            throw new Exception("테스트 대상은 필수입니다.");
        }
    }
    
    /**
     * 기본값 설정
     */
    private void setDefaultValues(Map<String, Object> paramMap) {
        if (isEmpty(paramMap.get("testType"))) {
            paramMap.put("testType", "NORMAL");
        }
        if (isEmpty(paramMap.get("priority"))) {
            paramMap.put("priority", "MEDIUM");
        }
        if (isEmpty(paramMap.get("testStatus"))) {
            paramMap.put("testStatus", "PEN");
        }
    }
    
    /**
     * 파일 확장자 추출
     */
    private String getFileExtension(String fileName) {
        if (!StringUtils.hasText(fileName)) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
    
    /**
     * 문자열 비어있음 체크
     */
    private boolean isEmpty(Object value) {
        return value == null || value.toString().trim().isEmpty();
    }
    
    /**
     * int 파싱 (기본값 포함)
     */
    private int parseInt(Object value, int defaultValue) {
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}