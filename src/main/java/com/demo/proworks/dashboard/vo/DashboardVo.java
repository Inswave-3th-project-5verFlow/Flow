package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "대시보드 요약 정보")
public class DashboardVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public DashboardVo() {
    }

    @ElDtoField(logicalName = "총 프로젝트 수", physicalName = "totalProjects", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long totalProjects;

    @ElDtoField(logicalName = "총 업무 수", physicalName = "totalTasks", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long totalTasks;

    @ElDtoField(logicalName = "총 이슈/리스크 수", physicalName = "totalIssues", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long totalIssues;

    @ElVoField(physicalName = "totalProjects")
    public long getTotalProjects() {
        return totalProjects;
    }

    @ElVoField(physicalName = "totalProjects")
    public void setTotalProjects(long totalProjects) {
        this.totalProjects = totalProjects;
    }

    @ElVoField(physicalName = "totalTasks")
    public long getTotalTasks() {
        return totalTasks;
    }

    @ElVoField(physicalName = "totalTasks")
    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }

    @ElVoField(physicalName = "totalIssues")
    public long getTotalIssues() {
        return totalIssues;
    }

    @ElVoField(physicalName = "totalIssues")
    public void setTotalIssues(long totalIssues) {
        this.totalIssues = totalIssues;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DashboardVo [");
        sb.append("totalProjects").append("=").append(totalProjects).append(",");
        sb.append("totalTasks").append("=").append(totalTasks).append(",");
        sb.append("totalIssues").append("=").append(totalIssues);
        sb.append("]");
        return sb.toString();
    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
    }

    @Override
    public void _xStreamDec() {
    }
}
