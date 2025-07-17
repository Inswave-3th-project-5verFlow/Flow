package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "대쉬보드 종합 데이터")
public class DashboardDataVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public DashboardDataVo(){
    }

    @ElDtoField(logicalName = "프로젝트 데이터", physicalName = "pjtChartVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.dashboard.vo.ChartVo> pjtChartVo;

    @ElDtoField(logicalName = "업무 데이터", physicalName = "taskChartVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.dashboard.vo.ChartVo> taskChartVo;

    @ElDtoField(logicalName = "이슈 데이터", physicalName = "irrChartVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.dashboard.vo.ChartVo> irrChartVo;

    @ElDtoField(logicalName = "요약 데이터", physicalName = "dashboardVo", type = "", typeKind = "Vo", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private com.demo.proworks.dashboard.vo.DashboardVo dashboardVo;

    @ElVoField(physicalName = "pjtChartVo")
    public java.util.List<com.demo.proworks.dashboard.vo.ChartVo> getPjtChartVo(){
        return pjtChartVo;
    }

    @ElVoField(physicalName = "pjtChartVo")
    public void setPjtChartVo(java.util.List<com.demo.proworks.dashboard.vo.ChartVo> pjtChartVo){
        this.pjtChartVo = pjtChartVo;
    }

    @ElVoField(physicalName = "taskChartVo")
    public java.util.List<com.demo.proworks.dashboard.vo.ChartVo> getTaskChartVo(){
        return taskChartVo;
    }

    @ElVoField(physicalName = "taskChartVo")
    public void setTaskChartVo(java.util.List<com.demo.proworks.dashboard.vo.ChartVo> taskChartVo){
        this.taskChartVo = taskChartVo;
    }

    @ElVoField(physicalName = "irrChartVo")
    public java.util.List<com.demo.proworks.dashboard.vo.ChartVo> getIrrChartVo(){
        return irrChartVo;
    }

    @ElVoField(physicalName = "irrChartVo")
    public void setIrrChartVo(java.util.List<com.demo.proworks.dashboard.vo.ChartVo> irrChartVo){
        this.irrChartVo = irrChartVo;
    }

    @ElVoField(physicalName = "dashboardVo")
    public com.demo.proworks.dashboard.vo.DashboardVo getDashboardVo(){
        return dashboardVo;
    }

    @ElVoField(physicalName = "dashboardVo")
    public void setDashboardVo(com.demo.proworks.dashboard.vo.DashboardVo dashboardVo){
        this.dashboardVo = dashboardVo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DashboardDataVo [");
        sb.append("pjtChartVo").append("=").append(pjtChartVo).append(",");
        sb.append("taskChartVo").append("=").append(taskChartVo).append(",");
        sb.append("irrChartVo").append("=").append(irrChartVo).append(",");
        sb.append("dashboardVo").append("=").append(dashboardVo);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; pjtChartVo != null && i < pjtChartVo.size() ; i++ ) {
            com.demo.proworks.dashboard.vo.ChartVo vo = (com.demo.proworks.dashboard.vo.ChartVo)pjtChartVo.get(i);
            vo._xStreamEnc();	 
        }
        for( int i=0 ; taskChartVo != null && i < taskChartVo.size() ; i++ ) {
            com.demo.proworks.dashboard.vo.ChartVo vo = (com.demo.proworks.dashboard.vo.ChartVo)taskChartVo.get(i);
            vo._xStreamEnc();	 
        }
        for( int i=0 ; irrChartVo != null && i < irrChartVo.size() ; i++ ) {
            com.demo.proworks.dashboard.vo.ChartVo vo = (com.demo.proworks.dashboard.vo.ChartVo)irrChartVo.get(i);
            vo._xStreamEnc();	 
        }
        if( this.dashboardVo != null ) this.dashboardVo._xStreamEnc();
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; pjtChartVo != null && i < pjtChartVo.size() ; i++ ) {
            com.demo.proworks.dashboard.vo.ChartVo vo = (com.demo.proworks.dashboard.vo.ChartVo)pjtChartVo.get(i);
            vo._xStreamDec();	 
        }
        for( int i=0 ; taskChartVo != null && i < taskChartVo.size() ; i++ ) {
            com.demo.proworks.dashboard.vo.ChartVo vo = (com.demo.proworks.dashboard.vo.ChartVo)taskChartVo.get(i);
            vo._xStreamDec();	 
        }
        for( int i=0 ; irrChartVo != null && i < irrChartVo.size() ; i++ ) {
            com.demo.proworks.dashboard.vo.ChartVo vo = (com.demo.proworks.dashboard.vo.ChartVo)irrChartVo.get(i);
            vo._xStreamDec();	 
        }
        if( this.dashboardVo != null ) this.dashboardVo._xStreamDec();
    }


}
