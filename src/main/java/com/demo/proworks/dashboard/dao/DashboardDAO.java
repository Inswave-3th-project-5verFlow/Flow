package com.demo.proworks.dashboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO;
import com.demo.proworks.dashboard.vo.ChartVo;
import com.demo.proworks.dashboard.vo.DashboardVo;
import com.demo.proworks.pjt.vo.PjtVo;
import com.inswave.elfw.exception.ElException;

/**
 * @subject : 대시보드 관련 처리를 담당하는 DAO
 * @description : 대시보드 관련 처리를 담당하는 DAO
 * @author : 김성민
 * @since : 2025/07/14
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/14 김성민 최초 생성
 * 
 */
@Repository("dashboardDAO")
public class DashboardDAO extends ProworksDefaultAbstractDAO {

    /**
     * 대시보드 요약 정보를 조회한다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo
     * @return DashboardVo 대시보드 요약 정보
     * @throws ElException
     */
    public DashboardVo selectDashboardSummary(PjtVo pjtVo) throws ElException {
        return (DashboardVo) selectByPk("com.demo.proworks.dashboard.selectDashboardSummary", pjtVo);
    }

    /**
     * 프로젝트 현황 차트 데이터를 조회한다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo
     * @return List<ChartVo> 프로젝트 현황 차트 데이터
     * @throws ElException
     */
    public List<ChartVo> selectProjectStatusChart(PjtVo pjtVo) throws ElException {
        return (List<ChartVo>) list("com.demo.proworks.dashboard.selectProjectStatusChart", pjtVo);
    }

    /**
     * 업무 단계별 현황 차트 데이터를 조회한다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo
     * @return List<ChartVo> 업무 단계별 현황 차트 데이터
     * @throws ElException
     */
    public List<ChartVo> selectTaskStatusChart(PjtVo pjtVo) throws ElException {
        return (List<ChartVo>) list("com.demo.proworks.dashboard.selectTaskStatusChart", pjtVo);
    }

    /**
     * 이슈/리스크 현황 차트 데이터를 조회한다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo
     * @return List<ChartVo> 이슈/리스크 현황 차트 데이터
     * @throws ElException
     */
    public List<ChartVo> selectIssueStatusChart(PjtVo pjtVo) throws ElException {
        return (List<ChartVo>) list("com.demo.proworks.dashboard.selectIssueStatusChart", pjtVo);
    }
}
