package com.demo.proworks.wbs.service;

/**
 * @subject : WBS 진척률 계산을 담당하는 인터페이스
 * @description : WBS 진척률 계산을 담당하는 인터페이스
 * @author : 김성민
 * @since : 2025/07/24
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/24 김성민 최초 생성
 * 
 */
public interface WbsProgressService {
    
    /**
     * 업무의 진척률을 계산하고 업데이트한다.
     * 
     * @param  taskId 진척률을 계산할 업무 ID
     * @param  projectId 프로젝트 ID
     */
    void calculateAndUpdateProgress(String taskId, String projectId);
      
    /**
     * 개발업무의 진척률을 계산한다.
     * 
     * @param  taskId 개발업무 ID
     * @param  projectId 프로젝트 ID
     * @return 계산된 진척률
     */
    int calculateProgress(String taskId, String projectId);
    
    /**
     * 상태에 따른 기본 진척률을 반환한다.
     * 
     * @param  status 업무 상태
     * @return 진척률 (0, 50, 100)
     */
    int getProgressRateByStatus(String status);
    
}