package com.demo.proworks.def.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.demo.proworks.def.service.DefService;
import com.demo.proworks.def.vo.DefListVo;
import com.demo.proworks.def.vo.DefVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.exception.ElException;

/**  
 * @subject     : 테스트결함관리 관련 처리를 담당하는 컨트롤러
 * @description : 테스트결함관리 관련 처리를 담당하는 컨트롤러
 * @author      : 우민지
 * @since       : 2025/07/23
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/23			 우민지	 		최초 생성
 * 
 */
@Controller
public class DefController {
	
    private static final Logger logger = LoggerFactory.getLogger(DefController.class);

    /** DefService */
    @Resource(name = "defServiceImpl")
    private DefService defService;
    
    @Resource(name = "attServiceImpl")
    private AttService attService;
    
    /**
     * 테스트결함관리 목록을 조회합니다.
     */
    @ElService(key="DEFList")
    @RequestMapping(value="DEFList")    
    @ElDescription(sub="테스트결함관리 목록조회",desc="페이징을 처리하여 테스트결함관리 목록 조회를 한다.")               
    public DefListVo selectListDef(DefVo defVo) throws Exception {    	   	

        logger.debug("결함 목록 조회 요청: {}", defVo);

        List<DefVo> defList = defService.selectListDef(defVo);                  
        long totCnt = defService.selectListCountDef(defVo);
	
		DefListVo retDefList = new DefListVo();
		retDefList.setDefVoList(defList); 
		retDefList.setTotalCount(totCnt);
		retDefList.setPageSize(defVo.getPageSize());
		retDefList.setPageIndex(defVo.getPageIndex());

        logger.debug("결함 목록 조회 완료: {} 건", defList.size());
        return retDefList;            
    }  
        
    /**
     * 테스트결함관리을 단건 조회 처리 한다.
     */
    @ElService(key = "DEFUpdView")    
    @RequestMapping(value="DEFUpdView") 
    @ElDescription(sub = "테스트결함관리 갱신 폼을 위한 조회", desc = "테스트결함관리 갱신 폼을 위한 조회를 한다.")    
    public DefVo selectDef(DefVo defVo) throws Exception {
        
        logger.debug("결함 상세 조회 요청: {}", defVo);
        
        DefVo selectDefVo = defService.selectDef(defVo);    	    
		
        logger.debug("결함 상세 조회 완료: {}", selectDefVo != null ? selectDefVo.getId() : "없음");
        return selectDefVo;
    } 
 
    /**
     * 테스트결함관리를 등록 처리 한다.
     */
    @ElService(key="DEFIns")    
    @RequestMapping(value="DEFIns")
    @ElDescription(sub="테스트결함관리 등록처리",desc="테스트결함관리를 등록 처리 한다.")
    public void insertDef(DefVo defVo) throws Exception {    	 
        
        logger.debug("결함 등록 요청: {}", defVo);
        
        defService.insertDef(defVo);   
        
        logger.debug("결함 등록 완료: {}", defVo.getId());
    }
       
    /**
     * 테스트결함관리를 갱신 처리 한다.
     */
    @ElService(key="DEFUpd")    
    @RequestMapping(value="DEFUpd")    
    @ElValidator(errUrl="/def/defRegister", errContinue=true)
    @ElDescription(sub="테스트결함관리 갱신처리",desc="테스트결함관리를 갱신 처리 한다.")    
    public void updateDef(DefVo defVo) throws Exception {  
        
        logger.debug("결함 수정 요청: {}", defVo);
 
    	defService.updateDef(defVo);                                            
        
        logger.debug("결함 수정 완료: {}", defVo.getId());
    }

    /**
     * 테스트결함관리를 삭제 처리한다.
     */
    @ElService(key = "DEFDel")    
    @RequestMapping(value="DEFDel")
    @ElDescription(sub = "테스트결함관리 삭제처리", desc = "테스트결함관리를 삭제 처리한다.")    
    public void deleteDef(DefVo defVo) throws Exception {
        
        logger.debug("결함 삭제 요청: {}", defVo);
        
        defService.deleteDef(defVo);
        
        logger.debug("결함 삭제 완료: {}", defVo.getId());
    }
    
    // ========== 자동 생성 기능 관련 컨트롤러 메서드들 ==========
    
    /**
     * 결함 상태 업데이트 (완료 시 관련 테스트 상태도 업데이트)
     */
    @ElService(key="DEFUpdateStatus")
    @RequestMapping(value="DEFUpdateStatus")
    @ElDescription(sub="결함 상태 업데이트", desc="결함 상태를 업데이트하고 완료 시 관련 테스트 상태도 업데이트한다.")
    public void updateDefectStatus(DefVo defVo) throws Exception {
        
        logger.debug("결함 상태 업데이트 요청: {}", defVo);

        defService.updateDefectStatus(defVo);
        
        logger.debug("결함 상태 업데이트 완료: {}", defVo.getId());
    }
    
    /**
     * 결함 통계 조회
     */
    @ElService(key="DEFStatistics")
    @RequestMapping(value="DEFStatistics")
    @ElDescription(sub="결함 통계 조회", desc="결함 상태별 통계를 조회한다.")
    public Map<String, Object> selectDefectStatistics(DefVo defVo) throws Exception {

        logger.debug("결함 통계 조회 요청: {}", defVo);
        
        Map<String, Object> statistics = defService.selectDefectStatistics(defVo);
        
        logger.debug("결함 통계 조회 완료: {}", statistics);

        return statistics;
    }
    
    /**
     * 수정 기한 임박 결함 목록 조회
     */
    @ElService(key="DEFUpcoming")
    @RequestMapping(value="DEFUpcoming")
    @ElDescription(sub="임박 결함 목록 조회", desc="수정 기한이 임박한 결함 목록을 조회한다.")
    public DefListVo selectUpcomingDefects(DefVo defVo) throws Exception {

        logger.debug("임박 결함 목록 조회 요청: {}", defVo);
        
        List<DefVo> upcomingList = defService.selectUpcomingDefects(defVo);
        
        DefListVo retDefList = new DefListVo();
        retDefList.setDefVoList(upcomingList);
        retDefList.setTotalCount(upcomingList.size());
        
        logger.debug("임박 결함 목록 조회 완료: {} 건", upcomingList.size());

        return retDefList;
    }

    // ===== 결함 관련 파일 처리 =====

    /**
     * 결함 파일 목록 조회
     */
    @ElService(key = "DEFFileList")
    @RequestMapping(value = "DEFFileList")
    @ElDescription(sub = "결함 파일 목록 조회", desc = "결함의 파일 목록을 조회한다.")
    public AttListVo getDefectFileList(DefVo defVo) throws Exception {
	    
	    logger.debug("=== 결함 파일 목록 조회 ===");
	    logger.debug("요청 데이터: {}", defVo != null ? defVo.toString() : "null");
	    
	    // null 체크
	    if (defVo == null) {
	        logger.warn("defVo가 null입니다.");
	        AttListVo emptyResult = new AttListVo();
	        emptyResult.setAttVoList(new ArrayList<>());
	        return emptyResult;
	    }
	    
	    // 결함 ID 체크
	    if (defVo.getId() == null || defVo.getId().trim().isEmpty()) {
	        logger.warn("결함 ID가 없습니다.");
	        AttListVo emptyResult = new AttListVo();
	        emptyResult.setAttVoList(new ArrayList<>());
	        return emptyResult;
	    }
	    
	    // DefService를 통해 파일 목록 조회
	    List<AttVo> attList = defService.selectDefFileList(defVo);
	    logger.debug("조회된 파일 개수: {}", (attList != null ? attList.size() : 0));

	    AttListVo retAttList = new AttListVo();
	    retAttList.setAttVoList(attList != null ? attList : new ArrayList<>());
	    
	    logger.debug("=== 결함 파일 목록 조회 완료 ===");
	    return retAttList;
	}

    /**
     * 결함 파일 삭제
     */
    @ElService(key = "DEFFileDelete")
    @RequestMapping(value = "DEFFileDelete")
    @ElDescription(sub = "결함 파일 삭제", desc = "결함의 파일을 삭제한다.")
    @ResponseBody
    public void deleteDefectFile(@RequestParam("fileId") String fileId) throws Exception {
        logger.debug("=== 결함 파일 삭제 ===");
        logger.debug("파일 ID: {}", fileId);

        if (fileId == null || fileId.trim().isEmpty()) {
            throw new ElException("파일 ID가 필요합니다.");
        }

        try {
            // DefService를 통해 파일 삭제
            int result = defService.deleteDefFile(fileId);
            
            if (result <= 0) {
                throw new ElException("파일 삭제에 실패했습니다.");
            }
            
            logger.debug("결함 파일 삭제 성공");
        } catch (Exception e) {
            logger.error("결함 파일 삭제 실패: {}", e.getMessage());
            throw new ElException("파일 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}