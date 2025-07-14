package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "대시보드 차트 목록")
public class DashboardChartListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public DashboardChartListVo(){
    }

    @ElDtoField(logicalName = "차트 데이터", physicalName = "chartVoList", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.dashboard.vo.ChartVo> chartVoList;

    @ElVoField(physicalName = "chartVoList")
    public java.util.List<com.demo.proworks.dashboard.vo.ChartVo> getChartVoList(){
        return chartVoList;
    }

    @ElVoField(physicalName = "chartVoList")
    public void setChartVoList(java.util.List<com.demo.proworks.dashboard.vo.ChartVo> chartVoList){
        this.chartVoList = chartVoList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DashboardChartListVo [");
        sb.append("chartVoList").append("=").append(chartVoList);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; chartVoList != null && i < chartVoList.size() ; i++ ) {
            com.demo.proworks.dashboard.vo.ChartVo vo = (com.demo.proworks.dashboard.vo.ChartVo)chartVoList.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; chartVoList != null && i < chartVoList.size() ; i++ ) {
            com.demo.proworks.dashboard.vo.ChartVo vo = (com.demo.proworks.dashboard.vo.ChartVo)chartVoList.get(i);
            vo._xStreamDec();	 
        }
    }


}
