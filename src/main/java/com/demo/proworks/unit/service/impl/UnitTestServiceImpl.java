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
import com.demo.proworks.att.service.AttService;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.cmmn.ProworksCommVO;
import com.demo.proworks.common.s3.S3Uploader;
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
    
    @Resource(name = "attServiceImpl")
    private AttService attService;
    
    @Resource(name = "attDAO")
    private AttDAO attDAO;
    
    @Resource
    private S3Uploader s3Uploader;

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
    
    @Override
    public long selectListCountUnitTest(UnitTestVo unitTestVo) throws Exception {
        return unitTestDao.selectUnitTestListCount(unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 상세 조회
     */
    @Override
    public UnitTestVo selectUnitTestDetail(UnitTestVo unitTestVo) throws Exception {
        logger.debug("단위테스트 케이스 상세 조회: {}", unitTestVo);
        
        UnitTestVo result = unitTestDao.selectUnitTestDetail(unitTestVo);
        
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
    public String insertUnitTest(UnitTestVo unitTestVo) throws Exception {
        logger.debug("단위테스트 케이스 등록 시작: {}", unitTestVo);
        
        // 필수값 검증
        validateRequiredFields(unitTestVo);
        
        // 테스트 케이스 ID 생성 (시퀀스 기반)
        String testCaseId = generateTestCaseId();
        unitTestVo.setTestCaseId(testCaseId);
        
        // 기본값 설정
        setDefaultValues(unitTestVo);
        
        // 등록
        int result = unitTestDao.insertUnitTest(unitTestVo);
        
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
    public UnitTestVo insertUnitTestWithFiles(UnitTestVo unitTestVo, MultipartFile[] files) throws Exception {
        List<String> uploadedS3Keys = new ArrayList<>();

        try {
            logger.debug("=== 단위테스트 케이스 with 파일 등록 시작 ===");
            logger.debug("단위테스트 케이스 정보: {}", unitTestVo);

            // 1. 단위테스트 케이스 등록
            String testCaseId = insertUnitTest(unitTestVo);
            unitTestVo.setTestCaseId(testCaseId);

            // 2. 파일 업로드
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo attVo = uploadAndSaveFile(file, "UNIT_TEST", testCaseId);
                        uploadedS3Keys.add(attVo.getS3Key());
                        logger.debug("파일 업로드 완료: {}", file.getOriginalFilename());
                    }
                }
            }

            logger.debug("=== 단위테스트 케이스 with 파일 등록 완료 ===");
            return unitTestVo;

        } catch (Exception e) {
            logger.error("단위테스트 케이스 등록 실패: {}", e.getMessage());

            // 업로드된 파일들 S3에서 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    logger.debug("S3 파일 롤백: {}", s3Key);
                } catch (Exception ignored) {
                }
            }

            throw new RuntimeException("단위테스트 케이스 등록 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 단위테스트 케이스 수정
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateUnitTest(UnitTestVo unitTestVo) throws Exception {
        logger.debug("단위테스트 케이스 수정: {}", unitTestVo);
        
        // 필수값 검증
        if (isEmpty(unitTestVo.getTestCaseId())) {
            throw new Exception("수정할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateUnitTest(unitTestVo);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 수정할 수 없습니다.");
        }
        
        logger.debug("단위테스트 케이스 수정 완료: {}", unitTestVo.getTestCaseId());
        return result;
    }
    
    /**
     * 단위테스트 케이스와 파일 함께 수정
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UnitTestVo updateUnitTestWithFiles(UnitTestVo unitTestVo, MultipartFile[] files) throws Exception {
        List<String> uploadedS3Keys = new ArrayList<>();

        try {
            logger.debug("=== 단위테스트 케이스 수정 with 파일 시작 ===");
            logger.debug("수정할 테스트 케이스 ID: {}", unitTestVo.getTestCaseId());

            // 1. 테스트 케이스 ID 유효성 검사
            if (unitTestVo.getTestCaseId() == null || unitTestVo.getTestCaseId().trim().isEmpty()) {
                throw new RuntimeException("수정할 테스트 케이스 ID가 필요합니다.");
            }

            // 2. 기존 테스트 케이스 존재 여부 확인
            UnitTestVo existingTestCase = unitTestDao.selectUnitTestDetail(unitTestVo);
            if (existingTestCase == null) {
                throw new RuntimeException("수정할 테스트 케이스를 찾을 수 없습니다. ID: " + unitTestVo.getTestCaseId());
            }

            // 3. 테스트 케이스 정보 수정
            int updateResult = updateUnitTest(unitTestVo);
            if (updateResult <= 0) {
                throw new RuntimeException("테스트 케이스 정보 수정 실패");
            }
            logger.debug("테스트 케이스 정보 수정 완료");

            // 4. 새로운 파일 업로드 (기존 파일은 유지)
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo attVo = uploadAndSaveFile(file, "UNIT_TEST", unitTestVo.getTestCaseId());
                        uploadedS3Keys.add(attVo.getS3Key());
                        logger.debug("새 파일 업로드: {}", file.getOriginalFilename());
                    }
                }
            }

            logger.debug("=== 단위테스트 케이스 수정 with 파일 완료 ===");
            return unitTestVo;

        } catch (Exception e) {
            logger.error("단위테스트 케이스 수정 실패: {}", e.getMessage());

            // 새로 업로드된 파일들 S3에서 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    logger.debug("S3 파일 롤백: {}", s3Key);
                } catch (Exception ignored) {
                }
            }

            throw new RuntimeException("단위테스트 케이스 수정 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 단위테스트 케이스 삭제 (논리삭제)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteUnitTest(UnitTestVo unitTestVo) throws Exception {
        logger.debug("단위테스트 케이스 삭제: {}", unitTestVo);
        
        String testCaseId = unitTestVo.getTestCaseId();
        if (isEmpty(testCaseId)) {
            throw new Exception("삭제할 테스트 케이스 ID가 필요합니다.");
        }
        
        // 첨부파일도 함께 논리삭제
        UnitTestVo fileParam = new UnitTestVo();
        fileParam.setTestCaseId(testCaseId);
        
        // 파일 목록 조회 후 논리 삭제
        List<AttVo> fileList = selectUnitTestFileList(fileParam);
        for (AttVo fileInfo : fileList) {
            deleteUnitTestFile(fileInfo.getFileId());
        }
        
        // 테스트 케이스 논리삭제
        int result = unitTestDao.deleteUnitTest(unitTestVo);
        
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
    public List<AttVo> selectUnitTestFileList(UnitTestVo unitTestVo) throws Exception {
        logger.debug("첨부파일 목록 조회: {}", unitTestVo);
        
        // AttService 조회용 파라미터 설정
        ProworksCommVO commVO = new ProworksCommVO();
        commVO.setRefType(REF_TYPE_UNIT_TEST);
        commVO.setRefId(unitTestVo.getTestCaseId());
        
        List<AttVo> fileList = attService.getFileList(commVO);
        
        logger.debug("첨부파일 목록 조회 완료: {} 건", fileList.size());
        return fileList;
    }
    
    /**
     * 단위테스트 케이스 첨부파일 삭제
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteUnitTestFile(String fileId) throws Exception {
        logger.debug("첨부파일 삭제: {}", fileId);
        
        if (isEmpty(fileId)) {
            throw new Exception("삭제할 파일 ID가 필요합니다.");
        }
        
        // AttService를 통해 파일 삭제
        attService.deleteFile(fileId);
        
        logger.debug("첨부파일 삭제 완료: {}", fileId);
        return 1; // 성공 시 1 반환
    }
    
    /**
     * 테스트 상태별 통계 조회
     */
    @Override
    public Map<String, Object> selectUnitTestStatistics(UnitTestVo unitTestVo) throws Exception {
        logger.debug("통계 조회: {}", unitTestVo);
        
        List<Map<String, Object>> statistics = unitTestDao.selectUnitTestStatistics(unitTestVo);
        
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
    public int updateTestStatus(UnitTestVo unitTestVo) throws Exception {
        logger.debug("테스트 상태 업데이트: {}", unitTestVo);
        
        if (isEmpty(unitTestVo.getTestCaseId())) {
            throw new Exception("업데이트할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateTestStatus(unitTestVo);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 상태를 업데이트할 수 없습니다.");
        }
        
        logger.debug("테스트 상태 업데이트 완료: {}", unitTestVo.getTestCaseId());
        return result;
    }
    
    /**
     * 테스트 결과 업데이트
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTestResult(UnitTestVo unitTestVo) throws Exception {
        logger.debug("테스트 결과 업데이트: {}", unitTestVo);
        
        if (isEmpty(unitTestVo.getTestCaseId())) {
            throw new Exception("업데이트할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateTestResult(unitTestVo);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 결과를 업데이트할 수 없습니다.");
        }
        
        logger.debug("테스트 결과 업데이트 완료: {}", unitTestVo.getTestCaseId());
        return result;
    }
    
    // ===== Private 메서드 =====
    
    /**
     * 파일 업로드 및 저장
     */
    private AttVo uploadAndSaveFile(MultipartFile file, String refType, String refId) throws Exception {
        String s3Key = s3Uploader.upload(file, uploadPath + "/" + refType);

        String originalName = file.getOriginalFilename();
        String extension = getFileExtension(originalName);
        String storedName = s3Key.substring(s3Key.lastIndexOf("/") + 1);

        // file 테이블 insert (AUTO_INCREMENT로 ID 자동 생성)
        AttVo fileVo = new AttVo();
        fileVo.setOriginalFileName(originalName);
        fileVo.setStoredFileName(storedName);
        fileVo.setFileSize(String.valueOf(file.getSize()));
        fileVo.setFileExtension(extension);
        fileVo.setS3Key(s3Key);
        attDAO.insertFile(fileVo);

        // insertFile 후 자동 생성된 fileId를 가져옴 (숫자)
        String fileId = fileVo.getFileId(); // 이게 "4" 같은 숫자 문자열

        // attachment 테이블 insert
        AttVo attachmentVo = new AttVo();
        attachmentVo.setId(UUID.randomUUID().toString()); // attachment 테이블의 PK
        attachmentVo.setFileId(fileId); // 위에서 생성된 숫자 ID 사용
        attachmentVo.setRefType(refType);
        attachmentVo.setRefId(refId);
        attachmentVo.setIsDeleted("N");
        attDAO.insertFileAttachment(attachmentVo);

        return fileVo;
    }
    
    /**
     * 테스트 케이스 ID 생성 (시퀀스 기반)
     * TC_001, TC_002, ... TC_010, ... TC_100 형태로 생성
     */
    private String generateTestCaseId() throws Exception {
        logger.debug("테스트 케이스 ID 생성 시작");
        
        try {
            // 현재 최대 시퀀스 번호 조회
            int nextSequence = unitTestDao.getNextTestCaseSequence();
            
            // TC_001 형태로 포맷팅 (3자리 패딩)
            String testCaseId = String.format("TC_%03d", nextSequence);
            
            logger.debug("생성된 테스트 케이스 ID: {}", testCaseId);
            return testCaseId;
            
        } catch (Exception e) {
            logger.error("테스트 케이스 ID 생성 실패: {}", e.getMessage());
            throw new Exception("테스트 케이스 ID 생성에 실패했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 필수값 검증
     */
    private void validateRequiredFields(UnitTestVo unitTestVo) throws Exception {
        if (isEmpty(unitTestVo.getTestCaseName())) {
            throw new Exception("테스트케이스명은 필수입니다.");
        }
        if (isEmpty(unitTestVo.getTestTarget())) {
            throw new Exception("테스트 대상은 필수입니다.");
        }
    }
    
    /**
     * 기본값 설정
     */
    private void setDefaultValues(UnitTestVo unitTestVo) {
        if (isEmpty(unitTestVo.getTestType())) {
            unitTestVo.setTestType("NORMAL");
        }
        if (isEmpty(unitTestVo.getPriority())) {
            unitTestVo.setPriority("MEDIUM");
        }
        if (isEmpty(unitTestVo.getTestStatus())) {
            unitTestVo.setTestStatus("PEN");
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