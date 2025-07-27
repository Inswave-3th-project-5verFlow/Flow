package com.demo.proworks.unit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

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
import com.demo.proworks.def.service.DefService;
import com.demo.proworks.unit.dao.UnitTestDao;
import com.demo.proworks.unit.service.UnitTestService;
import com.demo.proworks.unit.vo.UnitTestListVo;
import com.demo.proworks.unit.vo.UnitTestVo;
import com.inswave.elfw.log.AppLog;


/**
 * 단위테스트 케이스 관리 서비스 구현체
 */
@Service("unitTestServiceImpl")
public class UnitTestServiceImpl implements UnitTestService {
    
    
    @Resource(name = "unitTestDao")
    private UnitTestDao unitTestDao;
    
    @Resource(name = "attServiceImpl")
    private AttService attService;
    
    @Resource(name = "defServiceImpl")
    private DefService defService;
    
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
	    AppLog.debug("단위테스트 케이스 목록 조회 시작: {}", unitTestVo);
	    
	    // 현재 사용자 권한 정보 설정
	    return unitTestDao.selectUnitTestList(unitTestVo);
	}
    
    /**
     * 실패한 단위테스트 케이스 목록 조회 (결함 등록용)
     */
    @Override
    public List<UnitTestVo> selectFailedUnitTestList(UnitTestVo unitTestVo) throws Exception {
        AppLog.debug("실패한 단위테스트 케이스 목록 조회 시작: {}", unitTestVo);
        
        return unitTestDao.selectFailedUnitTestList(unitTestVo);
    }
    
    @Override
    public long selectListCountUnitTest(UnitTestVo unitTestVo) throws Exception {
	    
	    return unitTestDao.selectUnitTestListCount(unitTestVo);
	}

    /**
     * 실패한 단위테스트 케이스 총 개수 조회
     */
    @Override
    public long selectFailedListCountUnitTest(UnitTestVo unitTestVo) throws Exception {
        return unitTestDao.selectFailedUnitTestListCount(unitTestVo);
    }
    
    /**
     * 단위테스트 케이스 상세 조회
     */
    @Override
	public UnitTestVo selectUnitTestDetail(UnitTestVo unitTestVo) throws Exception {
	    AppLog.debug("===== 단위테스트 케이스 상세 조회 시작 =====");
	    AppLog.debug("요청 파라미터: {}", unitTestVo);
	    AppLog.debug("testCaseId: {}", unitTestVo.getTestCaseId());
	    
	    UnitTestVo result = unitTestDao.selectUnitTestDetail(unitTestVo);
	    
	    AppLog.debug("===== 조회 결과 확인 =====");
	    AppLog.debug("전체 결과: {}", result);
	    if (result != null) {
	        AppLog.debug("description 원본: [{}]", result.getDescription());
	        AppLog.debug("testData 원본: [{}]", result.getTestData());
	        AppLog.debug("description 길이: {}", result.getDescription() != null ? result.getDescription().length() : "null");
	        AppLog.debug("testData 길이: {}", result.getTestData() != null ? result.getTestData().length() : "null");
	    }
	    
	    if (result == null) {
	        throw new Exception("해당 테스트 케이스를 찾을 수 없습니다.");
	    }
	    
	    return result;
	}
    
    /**
     * 단위테스트 케이스 등록
     */
   
    @Override
    public String insertUnitTest(UnitTestVo unitTestVo) throws Exception {
	    AppLog.debug("단위테스트 케이스 등록 시작: {}", unitTestVo);
	    
	    // 필수값 검증
	    validateRequiredFields(unitTestVo);
	    
	    // 테스트 케이스 ID 생성 (시퀀스 기반)
	    String testCaseId = generateTestCaseId();
	    unitTestVo.setTestCaseId(testCaseId);
	    
	    // 기본값 설정
	    setDefaultValues(unitTestVo);
	    
	    // 1. 테스트 케이스 등록
	    int result = unitTestDao.insertUnitTest(unitTestVo);
	    
	    if (result > 0) {
	        // 2. project_task의 isTest 필드 업데이트
	        try {
	            int updateResult = unitTestDao.updateProjectTaskIsTest(unitTestVo);
	            AppLog.debug("project_task.isTest 업데이트 완료 for taskId: {} (업데이트된 행 수: {})"+ 
	                unitTestVo.getTaskId(), updateResult);
	        } catch (Exception e) {
	            AppLog.debug("project_task.isTest 업데이트 실패 (taskId: {}): {}"+ 
	                unitTestVo.getTaskId(), e.getMessage());
	            // 필요에 따라 예외를 던지거나 로그만 남길 수 있습니다
	        }
	        
	        AppLog.debug("단위테스트 케이스 등록 완료: {}", testCaseId);
	        return testCaseId;
	    } else {
	        throw new Exception("단위테스트 케이스 등록에 실패했습니다.");
	    }
	}
    
    /**
     * 단위테스트 케이스와 파일 함께 등록
     */
   
    @Override
    public UnitTestVo insertUnitTestWithFiles(UnitTestVo unitTestVo, MultipartFile[] files) throws Exception {
        List<String> uploadedS3Keys = new ArrayList<>();

        try {
            AppLog.debug("=== 단위테스트 케이스 with 파일 등록 시작 ===");
            AppLog.debug("단위테스트 케이스 정보: {}", unitTestVo);

            // 1. 단위테스트 케이스 등록
            String testCaseId = insertUnitTest(unitTestVo);
            unitTestVo.setTestCaseId(testCaseId);

            // 2. 파일 업로드
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo attVo = uploadAndSaveFile(file, "UNIT_TEST", testCaseId);
                        uploadedS3Keys.add(attVo.getS3Key());
                        AppLog.debug("파일 업로드 완료: {}", file.getOriginalFilename());
                    }
                }
            }

            AppLog.debug("=== 단위테스트 케이스 with 파일 등록 완료 ===");
            return unitTestVo;

        } catch (Exception e) {
            AppLog.error("단위테스트 케이스 등록 실패: {}", e.getMessage());

            // 업로드된 파일들 S3에서 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    AppLog.debug("S3 파일 롤백: {}", s3Key);
                } catch (Exception ignored) {
                }
            }

            throw new RuntimeException("단위테스트 케이스 등록 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 단위테스트 케이스 수정
     */
   
    @Override
    public int updateUnitTest(UnitTestVo unitTestVo) throws Exception {
        AppLog.debug("단위테스트 케이스 수정: {}", unitTestVo);
        
        // 필수값 검증
        if (isEmpty(unitTestVo.getTestCaseId())) {
            throw new Exception("수정할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateUnitTest(unitTestVo);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 수정할 수 없습니다.");
        }
        
        AppLog.debug("단위테스트 케이스 수정 완료: {}", unitTestVo.getTestCaseId());
        return result;
    }
    
    /**
     * 단위테스트 케이스와 파일 함께 수정
     */
   
    @Override
    public UnitTestVo updateUnitTestWithFiles(UnitTestVo unitTestVo, MultipartFile[] files) throws Exception {
        List<String> uploadedS3Keys = new ArrayList<>();

        try {
            AppLog.debug("=== 단위테스트 케이스 수정 with 파일 시작 ===");
            AppLog.debug("수정할 테스트 케이스 ID: {}", unitTestVo.getTestCaseId());

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
            AppLog.debug("테스트 케이스 정보 수정 완료");

            // 4. 새로운 파일 업로드 (기존 파일은 유지)
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo attVo = uploadAndSaveFile(file, "UNIT_TEST", unitTestVo.getTestCaseId());
                        uploadedS3Keys.add(attVo.getS3Key());
                        AppLog.debug("새 파일 업로드: {}", file.getOriginalFilename());
                    }
                }
            }

            AppLog.debug("=== 단위테스트 케이스 수정 with 파일 완료 ===");
            return unitTestVo;

        } catch (Exception e) {
            AppLog.error("단위테스트 케이스 수정 실패: {}", e.getMessage());

            // 새로 업로드된 파일들 S3에서 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    AppLog.debug("S3 파일 롤백: {}", s3Key);
                } catch (Exception ignored) {
                }
            }

            throw new RuntimeException("단위테스트 케이스 수정 실패: " + e.getMessage(), e);
        }
    }
    
    
    
    
    /**
     * 단위테스트 케이스 첨부파일 목록 조회
     */
    @Override
    public List<AttVo> selectUnitTestFileList(UnitTestVo unitTestVo) throws Exception {
        AppLog.debug("첨부파일 목록 조회: {}", unitTestVo);
        
        // AttService 조회용 파라미터 설정
        ProworksCommVO commVO = new ProworksCommVO();
        commVO.setRefType(REF_TYPE_UNIT_TEST);
        commVO.setRefId(unitTestVo.getTestCaseId());
        
        List<AttVo> fileList = attService.getFileList(commVO);
        
        AppLog.debug("첨부파일 목록 조회 완료: {} 건", fileList.size());
        return fileList;
    }
    
    /**
     * 단위테스트 케이스 첨부파일 삭제
     * @throws Exception 
     */
    @Override
    public int deleteUnitTestFile(String fileId) throws Exception{
        AppLog.debug("첨부파일 Hard Delete: {}", fileId);
        
        if (isEmpty(fileId)) {
            throw new Exception("삭제할 파일 ID가 필요합니다.");
        }
        
        try {
            // AttService를 통해 파일 완전 삭제 (S3 + DB)
            attService.deleteFile(fileId);
            
            AppLog.debug("첨부파일 Hard Delete 완료: {}", fileId);
            return 1;
            
        } catch (Exception e) {
            AppLog.error("첨부파일 삭제 실패: {} - {}");
            throw new RuntimeException("파일 삭제 중 오류 발생: " + e.getMessage(), e);
        }
    }
    
    @Override
    public int deleteUnitTest(UnitTestVo unitTestVo) throws Exception {
	    AppLog.debug("단위테스트 케이스 삭제 시작: {}", unitTestVo);
	    
	    String testCaseId = unitTestVo.getTestCaseId();
	    if (isEmpty(testCaseId)) {
	        throw new Exception("삭제할 테스트 케이스 ID가 필요합니다.");
	    }
	    
	    // 1. 테스트 케이스 존재 여부 확인
	    UnitTestVo existingTest = unitTestDao.selectUnitTestDetail(unitTestVo);
	    if (existingTest == null) {
	        throw new Exception("해당 테스트 케이스를 찾을 수 없습니다.");
	    }
	    
	    AppLog.debug("삭제 대상 테스트 케이스: {}", existingTest.getTestCaseName());
	    
	    // 2. 관련 파일 삭제 시도 (실패해도 무시)
	    try {
	        deleteRelatedFilesSafely(testCaseId);
	        AppLog.debug("관련 파일 삭제 완료");
	    } catch (Exception e) {
	        AppLog.warn("관련 파일 삭제 실패 (무시하고 계속): {}", e.getMessage());
	        // 파일 삭제 실패해도 테스트 케이스는 삭제 진행
	    }
	    
	    // 3. 테스트 케이스 삭제
	    int result = unitTestDao.deleteUnitTest(unitTestVo);
	    
	    if (result == 0) {
	        throw new Exception("테스트 케이스 삭제에 실패했습니다.");
	    }
	    
	    AppLog.debug("단위테스트 케이스 삭제 완료: {}", testCaseId);
	    return result;
	}
	
	/**
	 * 안전한 파일 삭제 (트랜잭션 없음)
	 */
	private void deleteRelatedFilesSafely(String testCaseId) {
	    try {
	        AppLog.debug("관련 파일 삭제 시작: {}", testCaseId);
	        
	        // 1. 해당 테스트 케이스의 모든 파일 목록 조회
	        ProworksCommVO searchVo = new ProworksCommVO();
	        searchVo.setRefType("UNIT_TEST");
	        searchVo.setRefId(testCaseId);
	        
	        List<AttVo> fileList = attDAO.selectFileListByRef(searchVo);
	        AppLog.debug("삭제 대상 파일 수: {}", fileList.size());
	
	        // 2. 각 파일을 개별적으로 삭제
	        for (AttVo fileVo : fileList) {
	            try {
	                // S3에서 파일 삭제
	                if (fileVo.getS3Key() != null && !fileVo.getS3Key().trim().isEmpty()) {
	                    amazonS3.deleteObject(bucketName, fileVo.getS3Key());
	                    AppLog.debug("S3 파일 삭제 성공: {}", fileVo.getOriginalFileName());
	                }
	                
	                // DB에서 file_attachments 삭제
	                attDAO.hardDeleteFileAttachment(fileVo.getFileId());
	                AppLog.debug("file_attachments 삭제 성공: {}", fileVo.getFileId());
	                
	                // DB에서 file 삭제
	                attDAO.hardDeleteFile(fileVo.getFileId());
	                AppLog.debug("file 테이블 삭제 성공: {}", fileVo.getFileId());
	                
	            } catch (Exception fileException) {
	                AppLog.error("개별 파일 삭제 실패: {} - {}");
	                // 개별 파일 삭제 실패해도 다른 파일은 계속 처리
	            }
	        }
	        
	        AppLog.debug("관련 파일 삭제 처리 완료");
	        
	    } catch (Exception e) {
	        AppLog.error("파일 삭제 중 오류: {}", e.getMessage());
	        // 예외를 던지지 않음 - 파일 삭제 실패해도 테스트 케이스 삭제는 계속 진행
	    }
	}
    /**
	 * 파일 삭제 (트랜잭션 없음)
	 */
	private void deleteRelatedFilesWithoutTransaction(String testCaseId) {
	    try {
	        AppLog.debug("관련 파일 삭제 시작 (비트랜잭션): {}", testCaseId);
	        
	        // 1. 해당 테스트 케이스의 모든 파일 목록 조회
	        ProworksCommVO searchVo = new ProworksCommVO();
	        searchVo.setRefType("UNIT_TEST");
	        searchVo.setRefId(testCaseId);
	        
	        List<AttVo> fileList = attDAO.selectFileListByRef(searchVo);
	        AppLog.debug("삭제 대상 파일 수: {}", fileList.size());
	
	        // 2. S3에서 각 파일들 물리 삭제
	        for (AttVo fileVo : fileList) {
	            try {
	                if (fileVo.getS3Key() != null && !fileVo.getS3Key().trim().isEmpty()) {
	                    amazonS3.deleteObject(bucketName, fileVo.getS3Key());
	                    AppLog.debug("S3 파일 삭제: {}", fileVo.getOriginalFileName());
	                }
	            } catch (Exception s3Exception) {
	                AppLog.error("S3 파일 삭제 실패: {} - {}", fileVo.getOriginalFileName());
	                // S3 삭제 실패해도 계속 진행
	            }
	        }
	
	        // 3. DB에서 파일 레코드들 개별 삭제
	        for (AttVo fileVo : fileList) {
	            try {
	                // file_attachments 먼저 삭제
	                attDAO.hardDeleteFileAttachment(fileVo.getFileId());
	                AppLog.debug("file_attachments 삭제: {}", fileVo.getFileId());
	                
	                // file 테이블 삭제
	                attDAO.hardDeleteFile(fileVo.getFileId());
	                AppLog.debug("file 테이블 삭제: {}", fileVo.getFileId());
	                
	            } catch (Exception dbException) {
	                AppLog.error("DB 파일 레코드 삭제 실패: {} - {}", fileVo.getFileId());
	                // 개별 삭제 실패해도 계속 진행
	            }
	        }
	        
	        AppLog.debug("관련 파일 삭제 처리 완료");
	        
	    } catch (Exception e) {
	        AppLog.error("관련 파일 삭제 실패: {}", e.getMessage());
	        // 예외를 던지지 않음 (테스트 케이스 삭제는 계속 진행)
	    }
	}
	
	/**
	 * 테스트 케이스만 삭제 (트랜잭션 적용)
	 */
	@Transactional(rollbackFor = Exception.class)
	public int deleteUnitTestCaseOnly(UnitTestVo unitTestVo) throws Exception {
	    AppLog.debug("테스트 케이스 DB 삭제 시작: {}", unitTestVo.getTestCaseId());
	    
	    try {
	        int result = unitTestDao.deleteUnitTest(unitTestVo);
	        
	        if (result == 0) {
	            throw new Exception("테스트 케이스 삭제에 실패했습니다.");
	        }
	        
	        AppLog.debug("테스트 케이스 DB 삭제 성공: {} (영향받은 행: {})", unitTestVo.getTestCaseId());
	        return result;
	        
	    } catch (Exception e) {
	        AppLog.error("테스트 케이스 DB 삭제 실패: {} - {}", unitTestVo.getTestCaseId());
	        throw e; // 트랜잭션 롤백을 위해 예외 재발생
	    }
	}
    
    /**
     * 테스트 상태별 통계 조회
     */
    @Override
    public Map<String, Object> selectUnitTestStatistics(UnitTestVo unitTestVo) throws Exception {
        AppLog.debug("통계 조회: {}", unitTestVo);
    
        
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
        
        AppLog.debug("통계 조회 완료: {}", result);
        return result;
    }
    
    /**
     * 테스트 실행 상태 업데이트
     */
   
    @Override
    public int updateTestStatus(UnitTestVo unitTestVo) throws Exception {
        AppLog.debug("테스트 상태 업데이트: {}", unitTestVo);
        
        if (isEmpty(unitTestVo.getTestCaseId())) {
            throw new Exception("업데이트할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateTestStatus(unitTestVo);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 상태를 업데이트할 수 없습니다.");
        }
        
        AppLog.debug("테스트 상태 업데이트 완료: {}", unitTestVo.getTestCaseId());
        return result;
    }
    
    /**
     * 테스트 결과 업데이트
     */
   
    @Override
    public int updateTestResult(UnitTestVo unitTestVo) throws Exception {
        AppLog.debug("테스트 결과 업데이트: {}", unitTestVo);
        
        if (isEmpty(unitTestVo.getTestCaseId())) {
            throw new Exception("업데이트할 테스트 케이스 ID가 필요합니다.");
        }
        
        int result = unitTestDao.updateTestResult(unitTestVo);
        
        if (result == 0) {
            throw new Exception("해당 테스트 케이스를 찾을 수 없거나 결과를 업데이트할 수 없습니다.");
        }
        
        AppLog.debug("테스트 결과 업데이트 완료: {}", unitTestVo.getTestCaseId());
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
        AppLog.debug("테스트 케이스 ID 생성 시작");
        
        try {
            // 현재 최대 시퀀스 번호 조회
            int nextSequence = unitTestDao.getNextTestCaseSequence();
            
            // TC_001 형태로 포맷팅 (3자리 패딩)
            String testCaseId = String.format("TC_%03d", nextSequence);
            
            AppLog.debug("생성된 테스트 케이스 ID: {}", testCaseId);
            return testCaseId;
            
        } catch (Exception e) {
            AppLog.error("테스트 케이스 ID 생성 실패: {}", e.getMessage());
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