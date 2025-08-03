package com.demo.proworks.wbs.service;

import com.demo.proworks.wbs.vo.WbsVo;

/**
 * @subject : WBS 상태 전파를 담당하는 인터페이스
 * @description : WBS 상태 전파를 담당하는 인터페이스
 * @author : 김성민
 * @since : 2025/07/24
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/24 김성민 최초 생성
 * 
 */
public interface WbsStatusPropagationService {
    
    /**
     * 하위 업무 추가 시 상위 업무 상태를 자동 조정한다.
     * 
     * @param  childTask 추가된 하위 업무 WbsVo
     */
    void propagateOnChildInsert(WbsVo childTask);
    
    /**
     * 상위 업무 상태 변경 시 하위 업무들의 상태를 자동 조정한다.
     * 
     * @param  parentTask 상태가 변경된 상위 업무 WbsVo
     * @param  oldStatus 변경 전 상태
     * @param  newStatus 변경 후 상태
     */
    void propagateOnParentUpdate(WbsVo parentTask, String oldStatus, String newStatus);
    
    /**
     * 하위 업무 상태 변경이 상위 업무에 미치는 영향을 처리한다.
     * 
     * @param  childTask 상태가 변경된 하위 업무 WbsVo
     */
    void propagateOnChildUpdate(WbsVo childTask);
    
    /**
     * 업무 삭제 시 관련 업무들의 상태를 조정한다.
     * 
     * @param  deletedTask 삭제된 업무 WbsVo
     */
    void propagateOnTaskDelete(WbsVo deletedTask);
}