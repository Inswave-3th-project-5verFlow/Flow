package com.demo.proworks.dashboard.service;

import java.util.List;

import com.demo.proworks.dashboard.vo.ChartVo;
import com.demo.proworks.dashboard.vo.DashboardDataVo;
import com.demo.proworks.dashboard.vo.DashboardVo;
import com.demo.proworks.pjt.vo.PjtVo;

/**  
 * @subject     : 대시보드 관련 처리를 담당하는 인터페이스
 * @description : 대시보드 관련 처리를 담당하는 인터페이스
 * @author      : 김성민
 * @since       : 2025/07/14
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/14			 김성민	 		최초 생성
 * 
 */
public interface DashboardService {

    /**
     * 대시보드 전체 데이터를 조회한다. (통합)
     *
     * @param pjtVo 프로젝트 정보 PjtVo (userId를 포함)
     * @return DashboardDataVO 대시보드 전체 데이터
     * @throws Exception
     */
    public DashboardDataVo getDashboardData(PjtVo pjtVo) throws Exception;
}
