package com.demo.proworks.task.design.util;

import com.demo.proworks.task.design.vo.DesignVo;
import com.demo.proworks.wbs.vo.WbsVo;

/**
 * DesignVo와 WbsVo 간 변환을 담당하는 유틸리티 클래스
 * 같은 PROJECT_TASK 테이블을 사용하므로 필드 매핑을 통해 변환한다.
 * 
 * @author 김성민
 * @since 2025/07/24
 */
public final class DesignWbsConverter {
    
    private DesignWbsConverter() {
        // 유틸리티 클래스이므로 인스턴스 생성 방지
    }
    
    /**
     * DesignVo를 WbsVo로 변환한다.
     * WBS 로직에서 사용할 수 있도록 필요한 필드들을 매핑한다.
     * 
     * @param designVo 변환할 DesignVo
     * @return 변환된 WbsVo
     */
    public static WbsVo toWbsVo(DesignVo designVo) {
        if (designVo == null) {
            return null;
        }
        
        WbsVo wbsVo = new WbsVo();
        
        // 핵심 필드 매핑
        wbsVo.setTaskId(designVo.getTaskId());
        wbsVo.setTaskName(designVo.getTaskName());
        wbsVo.setTaskDes(designVo.getTaskDes());
        wbsVo.setTaskStatus(designVo.getTaskStatus());
        wbsVo.setIsTest(designVo.getIsTest());
        wbsVo.setIsDesign(designVo.getIsDesign());
        wbsVo.setTaskDepth(designVo.getTaskDepth());
        wbsVo.setPtTaskId(designVo.getPtTaskId());
        
        // 일정 관련 필드
        wbsVo.setTaskSt(designVo.getTaskSt());
        wbsVo.setTaskEt(designVo.getTaskEt());
        wbsVo.setTaskRst(designVo.getTaskRst());
        wbsVo.setTaskRet(designVo.getTaskRet());
        
        // 담당자 및 진척률
        wbsVo.setTaskAsi(designVo.getTaskAsi());
        wbsVo.setTaskRate(designVo.getTaskRate());
        
        // 프로젝트 및 사용자 정보
        wbsVo.setPjtId(designVo.getPjtId());
        wbsVo.setUserId(designVo.getUserId());
        
        // 행 상태 (CRUD 작업용)
        wbsVo.setRowStatus(designVo.getRowStatus());
        
        // 공통 필드 (상속된 필드들)
        wbsVo.setPageIndex(designVo.getPageIndex());
        wbsVo.setPageSize(designVo.getPageSize());
        
        return wbsVo;
    }
    
    /**
     * WbsVo를 DesignVo로 변환한다.
     * WBS 로직 처리 후 결과를 Design VO로 다시 변환할 때 사용한다.
     * 
     * @param wbsVo 변환할 WbsVo
     * @return 변환된 DesignVo
     */
    public static DesignVo toDesignVo(WbsVo wbsVo) {
        if (wbsVo == null) {
            return null;
        }
        
        DesignVo designVo = new DesignVo();
        
        // 핵심 필드 매핑
        designVo.setTaskId(wbsVo.getTaskId());
        designVo.setTaskName(wbsVo.getTaskName());
        designVo.setTaskDes(wbsVo.getTaskDes());
        designVo.setTaskStatus(wbsVo.getTaskStatus());
        designVo.setIsTest(wbsVo.getIsTest());
        designVo.setIsDesign(wbsVo.getIsDesign());
        designVo.setTaskDepth(wbsVo.getTaskDepth());
        designVo.setPtTaskId(wbsVo.getPtTaskId());
        
        // 일정 관련 필드
        designVo.setTaskSt(wbsVo.getTaskSt());
        designVo.setTaskEt(wbsVo.getTaskEt());
        designVo.setTaskRst(wbsVo.getTaskRst());
        designVo.setTaskRet(wbsVo.getTaskRet());
        
        // 담당자 및 진척률
        designVo.setTaskAsi(wbsVo.getTaskAsi());
        designVo.setTaskRate(wbsVo.getTaskRate());
        
        // 프로젝트 및 사용자 정보
        designVo.setPjtId(wbsVo.getPjtId());
        designVo.setUserId(wbsVo.getUserId());
        
        // 행 상태 (CRUD 작업용)
        designVo.setRowStatus(wbsVo.getRowStatus());
        
        // 공통 필드 (상속된 필드들)
        designVo.setPageIndex(wbsVo.getPageIndex());
        designVo.setPageSize(wbsVo.getPageSize());
        
        // DesignVo 고유 필드는 기본값 유지
        // deTaskId는 변환하지 않음 (WbsVo에 없는 필드)
        
        return designVo;
    }
}