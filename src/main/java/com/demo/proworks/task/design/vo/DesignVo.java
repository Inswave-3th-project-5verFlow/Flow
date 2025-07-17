package com.demo.proworks.task.design.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "설계 업무 정보")
public class DesignVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public DesignVo(){
    }

    @ElDtoField(logicalName = "업무ID", physicalName = "taskId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskId;

    @ElDtoField(logicalName = "업무명", physicalName = "taskName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskName;

    @ElDtoField(logicalName = "업무상세", physicalName = "taskDes", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskDes;

    @ElDtoField(logicalName = "업무상태", physicalName = "taskStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskStatus;

    @ElDtoField(logicalName = "테스트여부", physicalName = "isTest", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isTest;

    @ElDtoField(logicalName = "뎁스", physicalName = "taskDepth", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskDepth;

    @ElDtoField(logicalName = "단계ID", physicalName = "stgId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String stgId;

    @ElDtoField(logicalName = "상위업무ID", physicalName = "ptTaskId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String ptTaskId;

    @ElDtoField(logicalName = "순서", physicalName = "taskSeq", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskSeq;

    @ElDtoField(logicalName = "시작예정일", physicalName = "taskSt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskSt;

    @ElDtoField(logicalName = "완료예정일", physicalName = "taskEt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskEt;

    @ElDtoField(logicalName = "실제시작일", physicalName = "taskRst", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskRst;

    @ElDtoField(logicalName = "실제완료일", physicalName = "taskRet", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskRet;

    @ElDtoField(logicalName = "업무담당자", physicalName = "taskAsi", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskAsi;

    @ElDtoField(logicalName = "진척률", physicalName = "taskRate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskRate;

    @ElDtoField(logicalName = "프로젝트ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtId;

    @ElDtoField(logicalName = "search_업무명", physicalName = "scTaskName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scTaskName;

    @ElDtoField(logicalName = "search_업무상태", physicalName = "scTaskStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scTaskStatus;

    @ElDtoField(logicalName = "search_테스트여부", physicalName = "scIsTest", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scIsTest;

    @ElDtoField(logicalName = "search_단계ID", physicalName = "scStgId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scStgId;

    @ElDtoField(logicalName = "진행상태", physicalName = "rowStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String rowStatus;

    @ElDtoField(logicalName = "상위업무명", physicalName = "ptTaskName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String ptTaskName;

    @ElDtoField(logicalName = "단계명", physicalName = "stgName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String stgName;

    @ElDtoField(logicalName = "사용자ID", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

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

    @ElVoField(physicalName = "taskDes")
    public String getTaskDes(){
        String ret = this.taskDes;
        return ret;
    }

    @ElVoField(physicalName = "taskDes")
    public void setTaskDes(String taskDes){
        this.taskDes = taskDes;
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

    @ElVoField(physicalName = "isTest")
    public String getIsTest(){
        String ret = this.isTest;
        return ret;
    }

    @ElVoField(physicalName = "isTest")
    public void setIsTest(String isTest){
        this.isTest = isTest;
    }

    @ElVoField(physicalName = "taskDepth")
    public String getTaskDepth(){
        String ret = this.taskDepth;
        return ret;
    }

    @ElVoField(physicalName = "taskDepth")
    public void setTaskDepth(String taskDepth){
        this.taskDepth = taskDepth;
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

    @ElVoField(physicalName = "ptTaskId")
    public String getPtTaskId(){
        String ret = this.ptTaskId;
        return ret;
    }

    @ElVoField(physicalName = "ptTaskId")
    public void setPtTaskId(String ptTaskId){
        this.ptTaskId = ptTaskId;
    }

    @ElVoField(physicalName = "taskSeq")
    public String getTaskSeq(){
        String ret = this.taskSeq;
        return ret;
    }

    @ElVoField(physicalName = "taskSeq")
    public void setTaskSeq(String taskSeq){
        this.taskSeq = taskSeq;
    }

    @ElVoField(physicalName = "taskSt")
    public String getTaskSt(){
        String ret = this.taskSt;
        return ret;
    }

    @ElVoField(physicalName = "taskSt")
    public void setTaskSt(String taskSt){
        this.taskSt = taskSt;
    }

    @ElVoField(physicalName = "taskEt")
    public String getTaskEt(){
        String ret = this.taskEt;
        return ret;
    }

    @ElVoField(physicalName = "taskEt")
    public void setTaskEt(String taskEt){
        this.taskEt = taskEt;
    }

    @ElVoField(physicalName = "taskRst")
    public String getTaskRst(){
        String ret = this.taskRst;
        return ret;
    }

    @ElVoField(physicalName = "taskRst")
    public void setTaskRst(String taskRst){
        this.taskRst = taskRst;
    }

    @ElVoField(physicalName = "taskRet")
    public String getTaskRet(){
        String ret = this.taskRet;
        return ret;
    }

    @ElVoField(physicalName = "taskRet")
    public void setTaskRet(String taskRet){
        this.taskRet = taskRet;
    }

    @ElVoField(physicalName = "taskAsi")
    public String getTaskAsi(){
        String ret = this.taskAsi;
        return ret;
    }

    @ElVoField(physicalName = "taskAsi")
    public void setTaskAsi(String taskAsi){
        this.taskAsi = taskAsi;
    }

    @ElVoField(physicalName = "taskRate")
    public String getTaskRate(){
        String ret = this.taskRate;
        return ret;
    }

    @ElVoField(physicalName = "taskRate")
    public void setTaskRate(String taskRate){
        this.taskRate = taskRate;
    }

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        String ret = this.pjtId;
        return ret;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
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

    @ElVoField(physicalName = "scTaskStatus")
    public String getScTaskStatus(){
        String ret = this.scTaskStatus;
        return ret;
    }

    @ElVoField(physicalName = "scTaskStatus")
    public void setScTaskStatus(String scTaskStatus){
        this.scTaskStatus = scTaskStatus;
    }

    @ElVoField(physicalName = "scIsTest")
    public String getScIsTest(){
        String ret = this.scIsTest;
        return ret;
    }

    @ElVoField(physicalName = "scIsTest")
    public void setScIsTest(String scIsTest){
        this.scIsTest = scIsTest;
    }

    @ElVoField(physicalName = "scStgId")
    public String getScStgId(){
        String ret = this.scStgId;
        return ret;
    }

    @ElVoField(physicalName = "scStgId")
    public void setScStgId(String scStgId){
        this.scStgId = scStgId;
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

    @ElVoField(physicalName = "ptTaskName")
    public String getPtTaskName(){
        String ret = this.ptTaskName;
        return ret;
    }

    @ElVoField(physicalName = "ptTaskName")
    public void setPtTaskName(String ptTaskName){
        this.ptTaskName = ptTaskName;
    }

    @ElVoField(physicalName = "stgName")
    public String getStgName(){
        String ret = this.stgName;
        return ret;
    }

    @ElVoField(physicalName = "stgName")
    public void setStgName(String stgName){
        this.stgName = stgName;
    }

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        String ret = this.userId;
        return ret;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DesignVo [");
        sb.append("taskId").append("=").append(taskId).append(",");
        sb.append("taskName").append("=").append(taskName).append(",");
        sb.append("taskDes").append("=").append(taskDes).append(",");
        sb.append("taskStatus").append("=").append(taskStatus).append(",");
        sb.append("isTest").append("=").append(isTest).append(",");
        sb.append("taskDepth").append("=").append(taskDepth).append(",");
        sb.append("stgId").append("=").append(stgId).append(",");
        sb.append("ptTaskId").append("=").append(ptTaskId).append(",");
        sb.append("taskSeq").append("=").append(taskSeq).append(",");
        sb.append("taskSt").append("=").append(taskSt).append(",");
        sb.append("taskEt").append("=").append(taskEt).append(",");
        sb.append("taskRst").append("=").append(taskRst).append(",");
        sb.append("taskRet").append("=").append(taskRet).append(",");
        sb.append("taskAsi").append("=").append(taskAsi).append(",");
        sb.append("taskRate").append("=").append(taskRate).append(",");
        sb.append("pjtId").append("=").append(pjtId).append(",");
        sb.append("scTaskName").append("=").append(scTaskName).append(",");
        sb.append("scTaskStatus").append("=").append(scTaskStatus).append(",");
        sb.append("scIsTest").append("=").append(scIsTest).append(",");
        sb.append("scStgId").append("=").append(scStgId).append(",");
        sb.append("rowStatus").append("=").append(rowStatus).append(",");
        sb.append("ptTaskName").append("=").append(ptTaskName).append(",");
        sb.append("stgName").append("=").append(stgName).append(",");
        sb.append("userId").append("=").append(userId);
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
