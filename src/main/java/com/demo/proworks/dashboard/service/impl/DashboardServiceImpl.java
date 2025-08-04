package com.demo.proworks.dashboard.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.proworks.dashboard.dao.DashboardDAO;
import com.demo.proworks.dashboard.service.DashboardService;
import com.demo.proworks.dashboard.vo.ChartVo;
import com.demo.proworks.dashboard.vo.DashboardDataVo;
import com.demo.proworks.dashboard.vo.DashboardVo;
import com.demo.proworks.pjt.vo.PjtVo;
import com.inswave.elfw.log.AppLog;

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

	/**
	 * 대시보드 전체 데이터를 조회한다.
	 *
	 * @process 1. 프로젝트 요약 정보 및 각종 차트 데이터를 조회한다. 
	 *			2. 업무 현황 차트의 라벨을 변환한다. (1:설계, 이외:개발) 
	 *	 		3. 조회된 모든 데이터를 통합 VO에 담아 반환한다.
	 * 
	 * @param pjtVo 프로젝트 정보 PjtVo
	 * @return 대시보드 전체 데이터 (요약정보, 프로젝트상태차트, 업무현황차트, 이슈리스크차트)
	 * @throws Exception
	 */
	@Override
	public DashboardDataVo getDashboardData(PjtVo pjtVo) throws Exception {
		DashboardDataVo dashboardData = new DashboardDataVo();

		// 프로젝트 요약 정보
		DashboardVo summary = dashboardDAO.selectDashboardSummary(pjtVo);

		// 프로젝트 상태
		List<ChartVo> projectChart = dashboardDAO.selectProjectStatusChart(pjtVo);

		// 업무 현황
		List<ChartVo> taskChart = dashboardDAO.selectTaskStatusChart(pjtVo).stream().map(chart -> {
			String newLabel = "1".equals(chart.getLabel()) ? "설계" : "개발";
			chart.setLabel(newLabel);
			return chart;
		}).collect(Collectors.toList());

		// 이슈 리스크 현황
		List<ChartVo> issueChart = dashboardDAO.selectIssueStatusChart(pjtVo);

		dashboardData.setPjtChartVo(projectChart);
		dashboardData.setIrrChartVo(issueChart);
		dashboardData.setTaskChartVo(taskChart);
		dashboardData.setDashboardVo(summary);

		return dashboardData;
	}
}
