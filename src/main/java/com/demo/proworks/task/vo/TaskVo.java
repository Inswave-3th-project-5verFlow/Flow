package com.demo.proworks.task.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "업무정보")
public class TaskVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "task_id", physicalName = "taskId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskId;

    @ElDtoField(logicalName = "task_name", physicalName = "taskName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskName;

    @ElDtoField(logicalName = "task_order", physicalName = "taskOrder", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskOrder;

    @ElDtoField(logicalName = "task_description", physicalName = "taskDescription", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskDescription;

    @ElDtoField(logicalName = "start_date", physicalName = "startDate", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String startDate;

    @ElDtoField(logicalName = "end_date", physicalName = "endDate", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String endDate;

    @ElDtoField(logicalName = "task_status", physicalName = "taskStatus", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskStatus;

    @ElDtoField(logicalName = "pit_stg_id", physicalName = "pitStgId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pitStgId;

    @ElDtoField(logicalName = "search_task_name", physicalName = "scTaskName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scTaskName;

    @ElVoField(physicalName = "taskId")
    public String getTaskId(){
        return taskId;
    }

    @ElVoField(physicalName = "taskId")
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    @ElVoField(physicalName = "taskName")
    public String getTaskName(){
        return taskName;
    }

    @ElVoField(physicalName = "taskName")
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    @ElVoField(physicalName = "taskOrder")
    public String getTaskOrder(){
        return taskOrder;
    }

    @ElVoField(physicalName = "taskOrder")
    public void setTaskOrder(String taskOrder){
        this.taskOrder = taskOrder;
    }

    @ElVoField(physicalName = "taskDescription")
    public String getTaskDescription(){
        return taskDescription;
    }

    @ElVoField(physicalName = "taskDescription")
    public void setTaskDescription(String taskDescription){
        this.taskDescription = taskDescription;
    }

    @ElVoField(physicalName = "startDate")
    public String getStartDate(){
        return startDate;
    }

    @ElVoField(physicalName = "startDate")
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    @ElVoField(physicalName = "endDate")
    public String getEndDate(){
        return endDate;
    }

    @ElVoField(physicalName = "endDate")
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    @ElVoField(physicalName = "taskStatus")
    public String getTaskStatus(){
        return taskStatus;
    }

    @ElVoField(physicalName = "taskStatus")
    public void setTaskStatus(String taskStatus){
        this.taskStatus = taskStatus;
    }

    @ElVoField(physicalName = "pitStgId")
    public String getPitStgId(){
        return pitStgId;
    }

    @ElVoField(physicalName = "pitStgId")
    public void setPitStgId(String pitStgId){
        this.pitStgId = pitStgId;
    }

    @ElVoField(physicalName = "scTaskName")
    public String getScTaskName(){
        return scTaskName;
    }

    @ElVoField(physicalName = "scTaskName")
    public void setScTaskName(String scTaskName) {
        this.scTaskName = scTaskName;
    }

    @Override
    public String toString() {
        return "TaskVo [taskId=" + taskId + ",taskName=" + taskName + ",taskOrder=" + taskOrder + ",taskDescription=" + taskDescription + ",startDate=" + startDate + ",endDate=" + endDate + ",taskStatus=" + taskStatus + ",pitStgId=" + pitStgId + ",scTaskName=" + scTaskName + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
