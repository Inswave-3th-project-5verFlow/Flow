package com.demo.proworks.wbs.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "WBS")
public class WbsVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "업무ID", physicalName = "taskId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskId;

    @ElDtoField(logicalName = "업무명", physicalName = "taskName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskName;

    @ElDtoField(logicalName = "업무상세", physicalName = "taskDes", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskDes;

    @ElDtoField(logicalName = "업무상태", physicalName = "taskStatus", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskStatus;

    @ElDtoField(logicalName = "테스트여부", physicalName = "isTest", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String isTest;

    @ElDtoField(logicalName = "뎁스", physicalName = "taskDepth", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskDepth;

    @ElDtoField(logicalName = "단계ID", physicalName = "stgId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String stgId;

    @ElDtoField(logicalName = "상위업무ID", physicalName = "ptTaskId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String ptTaskId;

    @ElDtoField(logicalName = "순서", physicalName = "taskSeq", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskSeq;

    @ElDtoField(logicalName = "시작예정일", physicalName = "taskSt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskSt;

    @ElDtoField(logicalName = "완료예정일", physicalName = "taskEt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskEt;

    @ElDtoField(logicalName = "실제시작일", physicalName = "taskRst", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskRst;

    @ElDtoField(logicalName = "실제완료일", physicalName = "taskRet", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskRet;

    @ElDtoField(logicalName = "업무담당자", physicalName = "taskAsi", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskAsi;

    @ElDtoField(logicalName = "진척률", physicalName = "taskRate", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String taskRate;

    @ElDtoField(logicalName = "프로젝트ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtId;

    @ElDtoField(logicalName = "search_업무ID", physicalName = "scTaskId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scTaskId;

    @ElDtoField(logicalName = "search_업무명", physicalName = "scTaskName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scTaskName;

    @ElDtoField(logicalName = "search_업무상태", physicalName = "scTaskStatus", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scTaskStatus;

    @ElDtoField(logicalName = "search_단계ID", physicalName = "scStgId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scStgId;

    @ElDtoField(logicalName = "search_업무담당자", physicalName = "scTaskAsi", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scTaskAsi;

    @ElDtoField(logicalName = "search_진척률", physicalName = "scTaskRate", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scTaskRate;

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

    @ElVoField(physicalName = "taskDes")
    public String getTaskDes(){
        return taskDes;
    }

    @ElVoField(physicalName = "taskDes")
    public void setTaskDes(String taskDes){
        this.taskDes = taskDes;
    }

    @ElVoField(physicalName = "taskStatus")
    public String getTaskStatus(){
        return taskStatus;
    }

    @ElVoField(physicalName = "taskStatus")
    public void setTaskStatus(String taskStatus){
        this.taskStatus = taskStatus;
    }

    @ElVoField(physicalName = "isTest")
    public String getIsTest(){
        return isTest;
    }

    @ElVoField(physicalName = "isTest")
    public void setIsTest(String isTest){
        this.isTest = isTest;
    }

    @ElVoField(physicalName = "taskDepth")
    public String getTaskDepth(){
        return taskDepth;
    }

    @ElVoField(physicalName = "taskDepth")
    public void setTaskDepth(String taskDepth){
        this.taskDepth = taskDepth;
    }

    @ElVoField(physicalName = "stgId")
    public String getStgId(){
        return stgId;
    }

    @ElVoField(physicalName = "stgId")
    public void setStgId(String stgId){
        this.stgId = stgId;
    }

    @ElVoField(physicalName = "ptTaskId")
    public String getPtTaskId(){
        return ptTaskId;
    }

    @ElVoField(physicalName = "ptTaskId")
    public void setPtTaskId(String ptTaskId){
        this.ptTaskId = ptTaskId;
    }

    @ElVoField(physicalName = "taskSeq")
    public String getTaskSeq(){
        return taskSeq;
    }

    @ElVoField(physicalName = "taskSeq")
    public void setTaskSeq(String taskSeq){
        this.taskSeq = taskSeq;
    }

    @ElVoField(physicalName = "taskSt")
    public String getTaskSt(){
        return taskSt;
    }

    @ElVoField(physicalName = "taskSt")
    public void setTaskSt(String taskSt){
        this.taskSt = taskSt;
    }

    @ElVoField(physicalName = "taskEt")
    public String getTaskEt(){
        return taskEt;
    }

    @ElVoField(physicalName = "taskEt")
    public void setTaskEt(String taskEt){
        this.taskEt = taskEt;
    }

    @ElVoField(physicalName = "taskRst")
    public String getTaskRst(){
        return taskRst;
    }

    @ElVoField(physicalName = "taskRst")
    public void setTaskRst(String taskRst){
        this.taskRst = taskRst;
    }

    @ElVoField(physicalName = "taskRet")
    public String getTaskRet(){
        return taskRet;
    }

    @ElVoField(physicalName = "taskRet")
    public void setTaskRet(String taskRet){
        this.taskRet = taskRet;
    }

    @ElVoField(physicalName = "taskAsi")
    public String getTaskAsi(){
        return taskAsi;
    }

    @ElVoField(physicalName = "taskAsi")
    public void setTaskAsi(String taskAsi){
        this.taskAsi = taskAsi;
    }

    @ElVoField(physicalName = "taskRate")
    public String getTaskRate(){
        return taskRate;
    }

    @ElVoField(physicalName = "taskRate")
    public void setTaskRate(String taskRate){
        this.taskRate = taskRate;
    }

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        return pjtId;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
    }

    @ElVoField(physicalName = "scTaskId")
    public String getScTaskId(){
        return scTaskId;
    }

    @ElVoField(physicalName = "scTaskId")
    public void setScTaskId(String scTaskId) {
        this.scTaskId = scTaskId;
    }

    @ElVoField(physicalName = "scTaskName")
    public String getScTaskName(){
        return scTaskName;
    }

    @ElVoField(physicalName = "scTaskName")
    public void setScTaskName(String scTaskName) {
        this.scTaskName = scTaskName;
    }

    @ElVoField(physicalName = "scTaskStatus")
    public String getScTaskStatus(){
        return scTaskStatus;
    }

    @ElVoField(physicalName = "scTaskStatus")
    public void setScTaskStatus(String scTaskStatus) {
        this.scTaskStatus = scTaskStatus;
    }

    @ElVoField(physicalName = "scStgId")
    public String getScStgId(){
        return scStgId;
    }

    @ElVoField(physicalName = "scStgId")
    public void setScStgId(String scStgId) {
        this.scStgId = scStgId;
    }

    @ElVoField(physicalName = "scTaskAsi")
    public String getScTaskAsi(){
        return scTaskAsi;
    }

    @ElVoField(physicalName = "scTaskAsi")
    public void setScTaskAsi(String scTaskAsi) {
        this.scTaskAsi = scTaskAsi;
    }

    @ElVoField(physicalName = "scTaskRate")
    public String getScTaskRate(){
        return scTaskRate;
    }

    @ElVoField(physicalName = "scTaskRate")
    public void setScTaskRate(String scTaskRate) {
        this.scTaskRate = scTaskRate;
    }

    @Override
    public String toString() {
        return "WbsVo [taskId=" + taskId + ",taskName=" + taskName + ",taskDes=" + taskDes + ",taskStatus=" + taskStatus + ",isTest=" + isTest + ",taskDepth=" + taskDepth + ",stgId=" + stgId + ",ptTaskId=" + ptTaskId + ",taskSeq=" + taskSeq + ",taskSt=" + taskSt + ",taskEt=" + taskEt + ",taskRst=" + taskRst + ",taskRet=" + taskRet + ",taskAsi=" + taskAsi + ",taskRate=" + taskRate + ",pjtId=" + pjtId + ",scTaskId=" + scTaskId + ",scTaskName=" + scTaskName + ",scTaskStatus=" + scTaskStatus + ",scStgId=" + scStgId + ",scTaskAsi=" + scTaskAsi + ",scTaskRate=" + scTaskRate + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
