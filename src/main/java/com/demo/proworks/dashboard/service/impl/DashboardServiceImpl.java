package com.demo.proworks.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.proworks.dashboard.dao.DashboardDAO;
import com.demo.proworks.dashboard.service.DashboardService;
import com.demo.proworks.dashboard.vo.ChartVo;
import com.demo.proworks.dashboard.vo.DashboardDataVo;
import com.demo.proworks.dashboard.vo.DashboardVo;
import com.demo.proworks.pjt.vo.PjtVo;

/**
 * @subject : 대시보드 관련 처리를 담당하는 ServiceImpl
 * @description : 대시보드 관련 처리를 담당하는 ServiceImpl
 * @author : 김성민
 * @since : 2025/07/14
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/14 김성민 최초 생성
 * 
 */
@Service("dashboardServiceImpl")
public class DashboardServiceImpl implements DashboardService {

    @Resource(name = "dashboardDAO")
    private DashboardDAO dashboardDAO;

    @Override
    public DashboardVo selectDashboardSummary(PjtVo pjtVo) throws Exception {
        return dashboardDAO.selectDashboardSummary(pjtVo);
    }

    @Override
    public List<ChartVo> selectProjectStatusChart(PjtVo pjtVo) throws Exception {
        return dashboardDAO.selectProjectStatusChart(pjtVo);
    }

    @Override
    public List<ChartVo> selectTaskStatusChart(PjtVo pjtVo) throws Exception {
        return dashboardDAO.selectTaskStatusChart(pjtVo);
    }

    @Override
    public List<ChartVo> selectIssueStatusChart(PjtVo pjtVo) throws Exception {
        return dashboardDAO.selectIssueStatusChart(pjtVo);
    }


    /**
     * 대시보드 전체 데이터를 조회한다. (통합)
     */
    @Override
    public DashboardDataVo getDashboardData(PjtVo pjtVo) throws Exception {
        DashboardDataVo dashboardData = new DashboardDataVo();

        // 1. 기존 DAO 메소드를 재사용하여 각 데이터 조회
        DashboardVo summary = dashboardDAO.selectDashboardSummary(pjtVo);
        List<ChartVo> projectChart = dashboardDAO.selectProjectStatusChart(pjtVo);
        List<ChartVo> taskChart = dashboardDAO.selectTaskStatusChart(pjtVo);
        List<ChartVo> issueChart = dashboardDAO.selectIssueStatusChart(pjtVo);

        // 2. 통합 VO에 데이터 담기
        dashboardData.setPjtChartVo(projectChart);
        dashboardData.setIrrChartVo(issueChart);
        dashboardData.setTaskChartVo(taskChart); 
        dashboardData.setDashboardVo(summary);


        return dashboardData;
    }
}
