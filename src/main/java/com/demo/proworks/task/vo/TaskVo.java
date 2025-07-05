package com.demo.proworks.task.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "업무정보")
public class TaskVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public TaskVo(){
    }

    @ElDtoField(logicalName = "task_id", physicalName = "taskId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskId;

    @ElDtoField(logicalName = "task_name", physicalName = "taskName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskName;

    @ElDtoField(logicalName = "task_order", physicalName = "taskOrder", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskOrder;

    @ElDtoField(logicalName = "task_description", physicalName = "taskDescription", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskDescription;

    @ElDtoField(logicalName = "start_date", physicalName = "startDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String startDate;

    @ElDtoField(logicalName = "end_date", physicalName = "endDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String endDate;

    @ElDtoField(logicalName = "task_status", physicalName = "taskStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskStatus;

    @ElDtoField(logicalName = "stg_id", physicalName = "stgId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String stgId;

    @ElDtoField(logicalName = "search_task_name", physicalName = "scTaskName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scTaskName;

    @ElDtoField(logicalName = "rowStatus", physicalName = "rowStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String rowStatus;

    @ElVoField(physicalName = "taskId")
    public String getTaskId(){
        String ret = this.taskId;
        return ret;
    }

    @ElVoField(physicalName = "taskId")
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    @ElVoField(physicalName = "taskName")
    public String getTaskName(){
        String ret = this.taskName;
        return ret;
    }

    @ElVoField(physicalName = "taskName")
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    @ElVoField(physicalName = "taskOrder")
    public String getTaskOrder(){
        String ret = this.taskOrder;
        return ret;
    }

    @ElVoField(physicalName = "taskOrder")
    public void setTaskOrder(String taskOrder){
        this.taskOrder = taskOrder;
    }

    @ElVoField(physicalName = "taskDescription")
    public String getTaskDescription(){
        String ret = this.taskDescription;
        return ret;
    }

    @ElVoField(physicalName = "taskDescription")
    public void setTaskDescription(String taskDescription){
        this.taskDescription = taskDescription;
    }

    @ElVoField(physicalName = "startDate")
    public String getStartDate(){
        String ret = this.startDate;
        return ret;
    }

    @ElVoField(physicalName = "startDate")
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    @ElVoField(physicalName = "endDate")
    public String getEndDate(){
        String ret = this.endDate;
        return ret;
    }

    @ElVoField(physicalName = "endDate")
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    @ElVoField(physicalName = "taskStatus")
    public String getTaskStatus(){
        String ret = this.taskStatus;
        return ret;
    }

    @ElVoField(physicalName = "taskStatus")
    public void setTaskStatus(String taskStatus){
        this.taskStatus = taskStatus;
    }

    @ElVoField(physicalName = "stgId")
    public String getStgId(){
        String ret = this.stgId;
        return ret;
    }

    @ElVoField(physicalName = "stgId")
    public void setStgId(String stgId){
        this.stgId = stgId;
    }

    @ElVoField(physicalName = "scTaskName")
    public String getScTaskName(){
        String ret = this.scTaskName;
        return ret;
    }

    @ElVoField(physicalName = "scTaskName")
    public void setScTaskName(String scTaskName){
        this.scTaskName = scTaskName;
    }

    @ElVoField(physicalName = "rowStatus")
    public String getRowStatus(){
        String ret = this.rowStatus;
        return ret;
    }

    @ElVoField(physicalName = "rowStatus")
    public void setRowStatus(String rowStatus){
        this.rowStatus = rowStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TaskVo [");
        sb.append("taskId").append("=").append(taskId).append(",");
        sb.append("taskName").append("=").append(taskName).append(",");
        sb.append("taskOrder").append("=").append(taskOrder).append(",");
        sb.append("taskDescription").append("=").append(taskDescription).append(",");
        sb.append("startDate").append("=").append(startDate).append(",");
        sb.append("endDate").append("=").append(endDate).append(",");
        sb.append("taskStatus").append("=").append(taskStatus).append(",");
        sb.append("stgId").append("=").append(stgId).append(",");
        sb.append("scTaskName").append("=").append(scTaskName).append(",");
        sb.append("rowStatus").append("=").append(rowStatus);
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
