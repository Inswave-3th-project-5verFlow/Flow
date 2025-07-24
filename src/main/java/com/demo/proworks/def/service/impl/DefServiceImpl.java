package com.demo.proworks.def.service.impl;

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
import com.demo.proworks.att.dao.AttDAO;
import com.demo.proworks.att.service.AttService;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.cmmn.ProworksCommVO;
import com.demo.proworks.common.s3.S3Uploader;
import com.demo.proworks.def.dao.DefDAO;
import com.demo.proworks.def.service.DefService;
import com.demo.proworks.def.vo.DefVo;
import com.demo.proworks.unit.dao.UnitTestDao;
import com.demo.proworks.unit.vo.UnitTestVo;

/**  
 * @subject     : 테스트결함관리 관련 처리를 담당하는 ServiceImpl (Enhanced)
 * @description	: 테스트결함관리 관련 처리를 담당하는 ServiceImpl (자동 생성 기능 추가)
 * @author      : 우민지
 * @since       : 2025/07/23
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/23			 우민지	 		최초 생성
 * 2025/07/23			 시스템			자동 생성 기능 추가
 * 
 */
@Service("defServiceImpl")
public class DefServiceImpl implements DefService {

    private static final Logger logger = LoggerFactory.getLogger(DefServiceImpl.class);

    @Resource(name="defDAO")
    private DefDAO defDAO;
    
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
    private static final String REF_TYPE_DEFECT = "DEFECT";

    /**
     * 테스트결함관리 목록을 조회합니다.
     */
    @Override
    public List<DefVo> selectListDef(DefVo defVo) throws Exception {
        List<DefVo> list = defDAO.selectListDef(defVo);	
        return list;
    }

    /**
     * 조회한 테스트결함관리 전체 카운트
     */
    @Override
    public long selectListCountDef(DefVo defVo) throws Exception {
        return defDAO.selectListCountDef(defVo);
    }

    /**
     * 테스트결함관리를 상세 조회한다.
     */
    @Override
    public DefVo selectDef(DefVo defVo) throws Exception {
        DefVo resultVO = defDAO.selectDef(defVo);			
        return resultVO;
    }

    /**
     * 테스트결함관리를 등록 처리 한다.
     */
    @Override
    public int insertDef(DefVo defVo) throws Exception {
        return defDAO.insertDef(defVo);	
    }
    
    /**
     * 테스트결함관리를 갱신 처리 한다.
     */
    @Override
    public int updateDef(DefVo defVo) throws Exception {				
        return defDAO.updateDef(defVo);	   		
    }

    /**
     * 테스트결함관리를 삭제 처리 한다.
     */
    @Override
    public int deleteDef(DefVo defVo) throws Exception {
        return defDAO.deleteDef(defVo);
    }
    
    // ========== 자동 생성 기능 구현 ==========
    
    /**
     * 단위테스트 실패 시 자동으로 결함을 생성한다.
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createDefectFromFailedTest(UnitTestVo unitTestVo) throws Exception {
        logger.info("단위테스트 실패로 인한 결함 자동 생성 시작: {}", unitTestVo.getTestCaseId());
        
        try {
            // 1. 이미 해당 테스트케이스에 대한 결함이 존재하는지 확인
            DefVo existingDefect = findExistingDefect(unitTestVo.getTestCaseId());
            
            if (existingDefect != null) {
                logger.info("기존 결함이 존재합니다: {}", existingDefect.getId());
                
                // 기존 결함이 완료 상태라면 새로 생성, 아니면 기존 결함 업데이트
                if ("완료".equals(existingDefect.getStatus())) {
                    return createNewDefect(unitTestVo);
                } else {
                    updateExistingDefect(existingDefect, unitTestVo);
                    return existingDefect.getId();
                }
            } else {
                // 2. 새로운 결함 생성
                return createNewDefect(unitTestVo);
            }
            
        } catch (Exception e) {
            logger.error("결함 자동 생성 실패: {}", e.getMessage(), e);
            throw new Exception("결함 자동 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 결함 상태가 '완료'로 변경될 때 관련 단위테스트를 '성공'으로 업데이트
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTestStatusOnDefectComplete(String defectId) throws Exception {
        logger.info("결함 완료로 인한 단위테스트 상태 업데이트 시작: {}", defectId);
        
        try {
            // 1. 결함 정보 조회
            DefVo defVo = new DefVo();
            defVo.setId(defectId);
            DefVo defect = defDAO.selectDef(defVo);
            
            if (defect == null) {
                logger.warn("결함을 찾을 수 없습니다: {}", defectId);
                return;
            }
            
            // 2. 관련 단위테스트 조회
            UnitTestVo unitTestVo = new UnitTestVo();
            unitTestVo.setTestCaseId(defect.getTestId());
            UnitTestVo unitTest = unitTestDao.selectUnitTestDetail(unitTestVo);
            
            if (unitTest == null) {
                logger.warn("연관된 단위테스트를 찾을 수 없습니다: {}", defect.getTestId());
                return;
            }
            
            // 3. 단위테스트 상태를 '성공'으로 변경
            unitTest.setTestStatus("SUC");
            unitTest.setUpdatedAt(getCurrentTimestamp());
            
            int result = unitTestDao.updateTestStatus(unitTest);
            
            if (result > 0) {
                logger.info("단위테스트 상태 업데이트 완료: {} -> 성공", unitTest.getTestCaseId());
            } else {
                logger.warn("단위테스트 상태 업데이트 실패: {}", unitTest.getTestCaseId());
            }
            
        } catch (Exception e) {
            logger.error("단위테스트 상태 업데이트 실패: {}", e.getMessage(), e);
            throw new Exception("단위테스트 상태 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 결함 상태를 변경하고 이력을 기록
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDefectStatus(DefVo defVo) throws Exception {
        logger.info("결함 상태 변경: {} -> {}", defVo.getId(), defVo.getStatus());
        
        // 1. 결함 상태 업데이트
        defVo.setUpdatedAt(getCurrentTimestamp());
        
        // 완료 상태로 변경되는 경우
        if ("완료".equals(defVo.getStatus())) {
            defVo.setFixedDate(getCurrentTimestamp());
            defDAO.updateDefectFixedDate(defVo);
            
            // 관련 단위테스트 상태도 성공으로 변경
            updateTestStatusOnDefectComplete(defVo.getId());
        }
        
        int result = defDAO.updateDef(defVo);
        
        if (result > 0) {
            // 2. 상태 변경 이력 기록
            defDAO.insertDefectStatusHistory(defVo);
            logger.info("결함 상태 변경 완료: {}", defVo.getId());
        } else {
            throw new Exception("결함 상태 변경에 실패했습니다.");
        }
    }
    
    /**
     * 결함 통계 정보 조회
     */
    @Override
    public Map<String, Object> selectDefectStatistics(DefVo defVo) throws Exception {
        logger.debug("결함 통계 조회: {}", defVo);
        
        Map<String, Object> result = new HashMap<>();
        
        // 상태별 통계
        List<Map<String, Object>> statusStats = defDAO.selectDefectStatsByStatus(defVo);
        // 우선순위별 통계
        List<Map<String, Object>> priorityStats = defDAO.selectDefectStatsByPriority(defVo);
        
        int totalCount = 0;
        int waitingCount = 0;
        int inProgressCount = 0;
        int completedCount = 0;
        
        for (Map<String, Object> stat : statusStats) {
            String status = (String) stat.get("status");
            int count = parseInt(stat.get("count"), 0);
            
            totalCount += count;
            
            switch (status) {
                case "대기":
                    waitingCount = count;
                    break;
                case "진행중":
                    inProgressCount = count;
                    break;
                case "완료":
                    completedCount = count;
                    break;
            }
        }
        
        result.put("totalCount", totalCount);
        result.put("waitingCount", waitingCount);
        result.put("inProgressCount", inProgressCount);
        result.put("completedCount", completedCount);
        result.put("statusStats", statusStats);
        result.put("priorityStats", priorityStats);
        
        // 완료율 계산
        if (totalCount > 0) {
            double completionRate = (double) completedCount / totalCount * 100;
            result.put("completionRate", Math.round(completionRate * 100) / 100.0);
        } else {
            result.put("completionRate", 0.0);
        }
        
        logger.debug("결함 통계 조회 완료: {}", result);
        return result;
    }
    
    /**
     * 수정 기한이 임박한 결함 목록 조회
     */
    @Override
    public List<DefVo> selectUpcomingDefects(DefVo defVo) throws Exception {
        return defDAO.selectUpcomingDefects(defVo);
    }
    
    /**
     * 결함과 파일을 함께 등록
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public DefVo insertDefWithFiles(DefVo defVo, MultipartFile[] files) throws Exception {
        List<String> uploadedS3Keys = new ArrayList<>();

        try {
            logger.debug("=== 결함 with 파일 등록 시작 ===");
            
            // 1. 결함 등록
            String defectId = generateDefectId();
            defVo.setId(defectId);
            defVo.setCreatedAt(getCurrentTimestamp());
            defVo.setUpdatedAt(getCurrentTimestamp());
            defVo.setIsDeleted("N");
            
            int result = defDAO.insertDef(defVo);
            
            if (result <= 0) {
                throw new Exception("결함 등록에 실패했습니다.");
            }

            // 2. 파일 업로드
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo attVo = uploadAndSaveFile(file, REF_TYPE_DEFECT, defectId);
                        uploadedS3Keys.add(attVo.getS3Key());
                        logger.debug("파일 업로드 완료: {}", file.getOriginalFilename());
                    }
                }
            }

            logger.debug("=== 결함 with 파일 등록 완료 ===");
            return defVo;

        } catch (Exception e) {
            logger.error("결함 등록 실패: {}", e.getMessage());

            // 업로드된 파일들 S3에서 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    logger.debug("S3 파일 롤백: {}", s3Key);
                } catch (Exception ignored) {
                }
            }

            throw new RuntimeException("결함 등록 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 결함과 파일을 함께 수정
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public DefVo updateDefWithFiles(DefVo defVo, MultipartFile[] files) throws Exception {
        List<String> uploadedS3Keys = new ArrayList<>();

        try {
            logger.debug("=== 결함 수정 with 파일 시작 ===");
            
            // 1. 결함 수정
            defVo.setUpdatedAt(getCurrentTimestamp());
            int result = defDAO.updateDef(defVo);
            
            if (result <= 0) {
                throw new Exception("결함 수정에 실패했습니다.");
            }

            // 2. 새로운 파일 업로드 (기존 파일은 유지)
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        AttVo attVo = uploadAndSaveFile(file, REF_TYPE_DEFECT, defVo.getId());
                        uploadedS3Keys.add(attVo.getS3Key());
                        logger.debug("새 파일 업로드: {}", file.getOriginalFilename());
                    }
                }
            }

            logger.debug("=== 결함 수정 with 파일 완료 ===");
            return defVo;

        } catch (Exception e) {
            logger.error("결함 수정 실패: {}", e.getMessage());

            // 새로 업로드된 파일들 S3에서 삭제 (롤백)
            for (String s3Key : uploadedS3Keys) {
                try {
                    amazonS3.deleteObject(bucketName, s3Key);
                    logger.debug("S3 파일 롤백: {}", s3Key);
                } catch (Exception ignored) {
                }
            }

            throw new RuntimeException("결함 수정 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 결함 첨부파일 목록 조회
     */
    @Override
    public List<AttVo> selectDefFileList(DefVo defVo) throws Exception {
        logger.debug("결함 첨부파일 목록 조회: {}", defVo);
        
        ProworksCommVO commVO = new ProworksCommVO();
        commVO.setRefType(REF_TYPE_DEFECT);
        commVO.setRefId(defVo.getId());
        
        List<AttVo> fileList = attService.getFileList(commVO);
        
        logger.debug("결함 첨부파일 목록 조회 완료: {} 건", fileList.size());
        return fileList;
    }
    
    /**
     * 결함 첨부파일 삭제
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteDefFile(String fileId) throws Exception {
        logger.debug("결함 첨부파일 삭제: {}", fileId);
        
        if (isEmpty(fileId)) {
            throw new Exception("삭제할 파일 ID가 필요합니다.");
        }
        
        // AttService를 통해 파일 삭제
        attService.deleteFile(fileId);
        
        logger.debug("결함 첨부파일 삭제 완료: {}", fileId);
        return 1;
    }
    
    // ========== Private Helper Methods ==========
    
    /**
     * 기존 결함 존재 여부 확인
     */
    private DefVo findExistingDefect(String testCaseId) throws Exception {
        DefVo searchVo = new DefVo();
        searchVo.setTestId(testCaseId);
        
        return defDAO.selectLatestDefectByTestId(searchVo);
    }
    
    /**
     * 새로운 결함 생성
     */
    private String createNewDefect(UnitTestVo unitTestVo) throws Exception {
        logger.info("새로운 결함 생성: {}", unitTestVo.getTestCaseId());
        
        String defectId = generateDefectId();
        
        DefVo defVo = new DefVo();
        defVo.setId(defectId);
        defVo.setTestId(unitTestVo.getTestCaseId());
        defVo.setName(generateDefectName(unitTestVo));
        defVo.setDescription(generateDefectDescription(unitTestVo));
        defVo.setPriority(mapPriorityToDefect(unitTestVo.getPriority()));
        defVo.setStatus("대기");
        defVo.setAssignee(unitTestVo.getAssignee());
        defVo.setCreatedAt(getCurrentTimestamp());
        defVo.setUpdatedAt(getCurrentTimestamp());
        defVo.setPjtId(unitTestVo.getPjtId());
        defVo.setIsDeleted("N");
        defVo.setFixDueDate(calculateFixDueDate(unitTestVo.getPriority()));
        
        int result = defDAO.insertDef(defVo);
        
        if (result > 0) {
            logger.info("결함 생성 완료: {}", defectId);
            return defectId;
        } else {
            throw new Exception("결함 생성에 실패했습니다.");
        }
    }
    
    /**
     * 기존 결함 업데이트
     */
    private void updateExistingDefect(DefVo existingDefect, UnitTestVo unitTestVo) throws Exception {
        logger.info("기존 결함 업데이트: {}", existingDefect.getId());
        
        existingDefect.setStatus("대기");
        existingDefect.setDescription(updateDefectDescription(existingDefect.getDescription(), unitTestVo));
        existingDefect.setUpdatedAt(getCurrentTimestamp());
        existingDefect.setPriority(mapPriorityToDefect(unitTestVo.getPriority()));
        existingDefect.setFixDueDate(calculateFixDueDate(unitTestVo.getPriority()));
        
        defDAO.updateDef(existingDefect);
    }
    
    /**
     * 파일 업로드 및 저장
     */
    private AttVo uploadAndSaveFile(MultipartFile file, String refType, String refId) throws Exception {
        String s3Key = s3Uploader.upload(file, uploadPath + "/" + refType);

        String originalName = file.getOriginalFilename();
        String extension = getFileExtension(originalName);
        String storedName = s3Key.substring(s3Key.lastIndexOf("/") + 1);

        AttVo fileVo = new AttVo();
        fileVo.setOriginalFileName(originalName);
        fileVo.setStoredFileName(storedName);
        fileVo.setFileSize(String.valueOf(file.getSize()));
        fileVo.setFileExtension(extension);
        fileVo.setS3Key(s3Key);
        attDAO.insertFile(fileVo);

        String fileId = fileVo.getFileId();

        AttVo attachmentVo = new AttVo();
        attachmentVo.setId(UUID.randomUUID().toString());
        attachmentVo.setFileId(fileId);
        attachmentVo.setRefType(refType);
        attachmentVo.setRefId(refId);
        attachmentVo.setIsDeleted("N");
        attDAO.insertFileAttachment(attachmentVo);

        return fileVo;
    }
    
    // Helper methods (결함 ID 생성, 이름 생성 등은 기존 DefectAutoCreateService와 동일)
    
    private String generateDefectId() throws Exception {
        int nextSequence = defDAO.getNextDefectSequence();
        return String.format("DEF_%03d", nextSequence);
    }
    
    private String generateDefectName(UnitTestVo unitTestVo) {
        return String.format("[%s] %s 테스트 실패", 
            unitTestVo.getTestCaseId(), 
            unitTestVo.getTestCaseName());
    }
    
    private String generateDefectDescription(UnitTestVo unitTestVo) {
        StringBuilder sb = new StringBuilder();
        sb.append("단위테스트 실패로 인해 자동 생성된 결함입니다.\n\n");
        sb.append("▣ 테스트 케이스 정보:\n");
        sb.append("- 테스트 ID: ").append(unitTestVo.getTestCaseId()).append("\n");
        sb.append("- 테스트명: ").append(unitTestVo.getTestCaseName()).append("\n");
        sb.append("- 테스트 대상: ").append(unitTestVo.getTestTarget()).append("\n");
        sb.append("- 실행일시: ").append(unitTestVo.getExecutionDate()).append("\n");
        
        if (unitTestVo.getActualResult() != null && !unitTestVo.getActualResult().trim().isEmpty()) {
            sb.append("- 실제 결과: ").append(unitTestVo.getActualResult()).append("\n");
        }
        
        return sb.toString();
    }
    
    private String updateDefectDescription(String existingDescription, UnitTestVo unitTestVo) {
        StringBuilder sb = new StringBuilder();
        sb.append(existingDescription);
        sb.append("\n\n--- 재발생 정보 (").append(getCurrentTimestamp()).append(") ---\n");
        sb.append("테스트가 다시 실패했습니다.\n");
        sb.append("- 실행일시: ").append(unitTestVo.getExecutionDate()).append("\n");
        
        return sb.toString();
    }
    
    private String mapPriorityToDefect(String testPriority) {
        if (testPriority == null) return "보통";
        
        switch (testPriority.toUpperCase()) {
            case "HIGH": return "높음";
            case "MEDIUM": return "보통";
            case "LOW": return "낮음";
            default: return "보통";
        }
    }
    
    private String calculateFixDueDate(String priority) {
        int daysToAdd = 7;
        
        if (priority != null) {
            switch (priority.toUpperCase()) {
                case "HIGH": daysToAdd = 3; break;
                case "MEDIUM": daysToAdd = 7; break;
                case "LOW": daysToAdd = 14; break;
            }
        }
        
        java.time.LocalDate dueDate = java.time.LocalDate.now().plusDays(daysToAdd);
        return dueDate.toString();
    }
    
    private String getCurrentTimestamp() {
        return java.time.LocalDateTime.now().toString();
    }
    
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
    
    private boolean isEmpty(Object value) {
        return value == null || value.toString().trim().isEmpty();
    }
    
    private int parseInt(Object value, int defaultValue) {
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
   
    /**
	 * 테스트 결과 업데이트 (단위테스트 상태 변경용)
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateTestResult(UnitTestVo unitTestVo) throws Exception {
	    logger.debug("단위테스트 결과 업데이트: {}", unitTestVo);
	    
	    if (isEmpty(unitTestVo.getTestCaseId())) {
	        throw new Exception("업데이트할 테스트 케이스 ID가 필요합니다.");
	    }
	    
	    // 단위테스트 상태 업데이트
	    int result = unitTestDao.updateTestResult(unitTestVo);
	    
	    if (result > 0) {
	        logger.debug("단위테스트 결과 업데이트 완료: {}", unitTestVo.getTestCaseId());
	        
	        // 만약 테스트가 실패로 변경되었다면 자동으로 결함 생성
	        if ("FAI".equals(unitTestVo.getTestStatus())) {
	            logger.info("테스트 실패로 인한 결함 자동 생성 시작: {}", unitTestVo.getTestCaseId());
	            try {
	                String defectId = createDefectFromFailedTest(unitTestVo);
	                logger.info("결함 자동 생성 완료: {}", defectId);
	            } catch (Exception e) {
	                logger.error("결함 자동 생성 실패: {}", e.getMessage(), e);
	                // 결함 생성 실패해도 테스트 상태 업데이트는 유지
	            }
	        }
	    } else {
	        throw new Exception("해당 테스트 케이스를 찾을 수 없거나 결과를 업데이트할 수 없습니다.");
	    }
	    
	    return result;
	}
   
	    
}