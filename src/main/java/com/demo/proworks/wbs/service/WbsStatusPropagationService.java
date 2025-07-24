package com.demo.proworks.wbs.service;

import com.demo.proworks.wbs.vo.WbsVo;

/**
 * WBS 상태 전파를 담당하는 서비스
 * 업무 상태 변경 시 연관된 상하위 업무들의 상태를 자동으로 조정한다.
 * 
 * @author 김성민
 * @since 2025/07/24
 */
public interface WbsStatusPropagationService {
    
    /**
     * 하위 업무 추가 시 상위 업무 상태를 자동 조정한다.
     * 규칙: 상위 업무가 대기/완료 상태면 진행중으로 변경
     * 
     * @param childTask 추가된 하위 업무
     */
    void propagateOnChildInsert(WbsVo childTask);
    
    /**
     * 상위 업무 상태 변경 시 하위 업무들의 상태를 자동 조정한다.
     * 규칙: 상위 업무 완료 시 모든 하위 업무도 완료 처리 (설계업무의 연관 개발업무 포함)
     * 
     * @param parentTask 상태가 변경된 상위 업무
     * @param oldStatus 변경 전 상태
     * @param newStatus 변경 후 상태
     */
    void propagateOnParentUpdate(WbsVo parentTask, String oldStatus, String newStatus);
    
    /**
     * 하위 업무 상태 변경이 상위 업무에 미치는 영향을 처리한다.
     * 규칙: 모든 하위 업무가 완료되면 상위 업무도 완료로 변경
     * 
     * @param childTask 상태가 변경된 하위 업무
     */
    void propagateOnChildUpdate(WbsVo childTask);
    
    /**
     * 업무 삭제 시 관련 업무들의 상태를 조정한다.
     * 
     * @param deletedTask 삭제된 업무
     */
    void propagateOnTaskDelete(WbsVo deletedTask);
}