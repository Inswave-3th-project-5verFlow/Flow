package com.demo.proworks.dashboard.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.proworks.cmmn.ProworksUserHeader;
import com.demo.proworks.dashboard.service.DashboardService;
import com.demo.proworks.dashboard.vo.ChartVo;
import com.demo.proworks.dashboard.vo.DashboardChartListVo;
import com.demo.proworks.dashboard.vo.DashboardDataVo;
import com.demo.proworks.dashboard.vo.DashboardVo;
import com.demo.proworks.pjt.vo.PjtVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.session.HttpSessionAdapter;
import com.inswave.elfw.util.ControllerContextUtil;
import org.springframework.web.bind.annotation.RequestMethod;
import com.inswave.elfw.annotation.ElValidator;

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
     * 대시보드 전체 데이터를 조회한다.
     *
     * @param pjtVo 프로젝트 정보 PjtVo
     * @return 대시보드 전체 데이터
     * @throws Exception
     */
    @ElService(key = "DashboardData")
    @RequestMapping(value = "DashboardData")
    @ElDescription(sub = "대시보드 전체 데이터 조회", desc = "대시보드에 필요한 모든 데이터를 한번에 조회한다.")
    public DashboardDataVo getDashboardData(PjtVo pjtVo) throws Exception {
        return dashboardService.getDashboardData(pjtVo);
    }

}
