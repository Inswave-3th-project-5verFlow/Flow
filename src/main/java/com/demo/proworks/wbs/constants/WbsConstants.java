package com.demo.proworks.wbs.constants;

/**
 * WBS 관련 상수를 관리하는 클래스
 * 
 * @author 김성민
 * @since 2025/07/24
 */
public final class WbsConstants {
    
    // 업무 타입 정의 (is_design 컬럼)
    public static final class TaskType {
        /** 설계 업무 */
        public static final String DESIGN = "1";
        /** 개발 업무 */
        public static final String DEVELOPMENT = "0";
        
        private TaskType() {}
    }
    
    // 업무 상태 정의 (task_status 컬럼)
    public static final class TaskStatus {
        /** 대기 상태 */
        public static final String WAITING = "대기";
        /** 진행중 상태 */
        public static final String IN_PROGRESS = "진행중";
        /** 완료 상태 */
        public static final String COMPLETED = "완료";
        
        private TaskStatus() {}
    }
    
    // 진척률 정의 (task_rate 컬럼)
    public static final class ProgressRate {
        /** 대기 상태 진척률 */
        public static final int WAITING_RATE = 0;
        /** 진행중 상태 진척률 */
        public static final int IN_PROGRESS_RATE = 50;
        /** 완료 상태 진척률 */
        public static final int COMPLETED_RATE = 100;
        
        private ProgressRate() {}
    }
    
    // 행 상태 정의 (row_status 컬럼)
    public static final class RowStatus {
        /** 신규 생성 */
        public static final String CREATE = "C";
        /** 수정 */
        public static final String UPDATE = "U";
        /** 삭제 */
        public static final String DELETE = "D";
        
        private RowStatus() {}
    }
    
    private WbsConstants() {
        // 유틸리티 클래스이므로 인스턴스 생성 방지
    }
}