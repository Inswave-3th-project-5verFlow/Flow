package com.demo.proworks.dashboard.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.proworks.dashboard.service.DashboardService;
import com.demo.proworks.dashboard.vo.ChartVo;
import com.demo.proworks.dashboard.vo.DashboardChartListVo;
import com.demo.proworks.dashboard.vo.DashboardVo;
import com.demo.proworks.pjt.vo.PjtVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;

/**
 * @subject : 대시보드 관련 처리를 담당하는 컨트롤러
 * @description : 대시보드 관련 처리를 담당하는 컨트롤러
 * @author : 김성민
 * @since : 2025/07/14
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/14 김성민 최초 생성
 * 
 */
@Controller
public class DashboardController {

    /** DashboardService */
    @Resource(name = "dashboardServiceImpl")
    private DashboardService dashboardService;

    /**
     * 대시보드 요약 정보를 조회합니다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo (userId를 포함)
     * @return 대시보드 요약 정보
     * @throws Exception
     */
    @ElService(key = "DashboardSummary")
    @RequestMapping(value = "DashboardSummary")
    @ElDescription(sub = "대시보드 요약 정보 조회", desc = "총 프로젝트, 업무, 이슈/리스크 수를 조회한다.")
    public DashboardVo selectDashboardSummary(PjtVo pjtVo) throws Exception {
        return dashboardService.selectDashboardSummary(pjtVo);
    }

    /**
     * 프로젝트 현황 차트 데이터를 조회합니다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo (userId를 포함)
     * @return 프로젝트 현황 차트 데이터 목록
     * @throws Exception
     */
    @ElService(key = "ProjectStatusChart")
    @RequestMapping(value = "ProjectStatusChart")
    @ElDescription(sub = "프로젝트 현황 차트 데이터 조회", desc = "프로젝트 상태별 개수를 조회한다.")
    public DashboardChartListVo selectProjectStatusChart(PjtVo pjtVo) throws Exception {
        List<ChartVo> chartData = dashboardService.selectProjectStatusChart(pjtVo);
        DashboardChartListVo result = new DashboardChartListVo();
        result.setChartVoList(chartData);
        return result;
    }

    /**
     * 업무 단계별 현황 차트 데이터를 조회합니다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo (userId를 포함)
     * @return 업무 단계별 현황 차트 데이터 목록
     * @throws Exception
     */
    @ElService(key = "TaskStatusChart")
    @RequestMapping(value = "TaskStatusChart")
    @ElDescription(sub = "업무 단계별 현황 차트 데이터 조회", desc = "업무 단계별 개수를 조회한다.")
    public DashboardChartListVo selectTaskStatusChart(PjtVo pjtVo) throws Exception {
        List<ChartVo> chartData = dashboardService.selectTaskStatusChart(pjtVo);
        DashboardChartListVo result = new DashboardChartListVo();
        result.setChartVoList(chartData);
        return result;
    }

    /**
     * 이슈/리스크 현황 차트 데이터를 조회합니다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo (userId를 포함)
     * @return 이슈/리스크 현황 차트 데이터 목록
     * @throws Exception
     */
    @ElService(key = "IssueStatusChart")
    @RequestMapping(value = "IssueStatusChart")
    @ElDescription(sub = "이슈/리스크 현황 차트 데이터 조회", desc = "이슈/리스크 상태별 개수를 조회한다.")
    public DashboardChartListVo selectIssueStatusChart(PjtVo pjtVo) throws Exception {
        List<ChartVo> chartData = dashboardService.selectIssueStatusChart(pjtVo);
        DashboardChartListVo result = new DashboardChartListVo();
        result.setChartVoList(chartData);
        return result;
    }
}
