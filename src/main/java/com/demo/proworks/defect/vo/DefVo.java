package com.demo.proworks.defect.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "테스트결함관리")
public class DefVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public DefVo(){
    }

    @ElDtoField(logicalName = "결함 고유 ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String id;

    @ElDtoField(logicalName = "연결된 테스트 ID", physicalName = "testId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testId;

    @ElDtoField(logicalName = "결함명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "결함 상세 설명", physicalName = "description", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String description;

    @ElDtoField(logicalName = "우선순위", physicalName = "priority", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String priority;

    @ElDtoField(logicalName = "상태", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "등록일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정 담당자", physicalName = "assignee", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String assignee;

    @ElDtoField(logicalName = "수정 기한", physicalName = "fixDueDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fixDueDate;

    @ElDtoField(logicalName = "수정 완료일", physicalName = "fixedDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fixedDate;

    @ElDtoField(logicalName = "비고사항", physicalName = "remarks", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String remarks;

    @ElDtoField(logicalName = "삭제 여부", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isDeleted;

    @ElDtoField(logicalName = "테스트명", physicalName = "testName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testName;

    @ElDtoField(logicalName = "사용자명", physicalName = "userName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userName;

    @ElDtoField(logicalName = "최종수정일시", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedAt;

    @ElVoField(physicalName = "id")
    public String getId(){
        String ret = this.id;
        return ret;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
    }

    @ElVoField(physicalName = "testId")
    public String getTestId(){
        String ret = this.testId;
        return ret;
    }

    @ElVoField(physicalName = "testId")
    public void setTestId(String testId){
        this.testId = testId;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        String ret = this.name;
        return ret;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
    }

    @ElVoField(physicalName = "description")
    public String getDescription(){
        String ret = this.description;
        return ret;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
    }

    @ElVoField(physicalName = "priority")
    public String getPriority(){
        String ret = this.priority;
        return ret;
    }

    @ElVoField(physicalName = "priority")
    public void setPriority(String priority){
        this.priority = priority;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        String ret = this.status;
        return ret;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "createdAt")
    public String getCreatedAt(){
        String ret = this.createdAt;
        return ret;
    }

    @ElVoField(physicalName = "createdAt")
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    @ElVoField(physicalName = "assignee")
    public String getAssignee(){
        String ret = this.assignee;
        return ret;
    }

    @ElVoField(physicalName = "assignee")
    public void setAssignee(String assignee){
        this.assignee = assignee;
    }

    @ElVoField(physicalName = "fixDueDate")
    public String getFixDueDate(){
        String ret = this.fixDueDate;
        return ret;
    }

    @ElVoField(physicalName = "fixDueDate")
    public void setFixDueDate(String fixDueDate){
        this.fixDueDate = fixDueDate;
    }

    @ElVoField(physicalName = "fixedDate")
    public String getFixedDate(){
        String ret = this.fixedDate;
        return ret;
    }

    @ElVoField(physicalName = "fixedDate")
    public void setFixedDate(String fixedDate){
        this.fixedDate = fixedDate;
    }

    @ElVoField(physicalName = "remarks")
    public String getRemarks(){
        String ret = this.remarks;
        return ret;
    }

    @ElVoField(physicalName = "remarks")
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    @ElVoField(physicalName = "isDeleted")
    public String getIsDeleted(){
        String ret = this.isDeleted;
        return ret;
    }

    @ElVoField(physicalName = "isDeleted")
    public void setIsDeleted(String isDeleted){
        this.isDeleted = isDeleted;
    }

    @ElVoField(physicalName = "testName")
    public String getTestName(){
        String ret = this.testName;
        return ret;
    }

    @ElVoField(physicalName = "testName")
    public void setTestName(String testName){
        this.testName = testName;
    }

    @ElVoField(physicalName = "userName")
    public String getUserName(){
        String ret = this.userName;
        return ret;
    }

    @ElVoField(physicalName = "userName")
    public void setUserName(String userName){
        this.userName = userName;
    }

    @ElVoField(physicalName = "updatedAt")
    public String getUpdatedAt(){
        String ret = this.updatedAt;
        return ret;
    }

    @ElVoField(physicalName = "updatedAt")
    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DefVo [");
        sb.append("id").append("=").append(id).append(",");
        sb.append("testId").append("=").append(testId).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("description").append("=").append(description).append(",");
        sb.append("priority").append("=").append(priority).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("assignee").append("=").append(assignee).append(",");
        sb.append("fixDueDate").append("=").append(fixDueDate).append(",");
        sb.append("fixedDate").append("=").append(fixedDate).append(",");
        sb.append("remarks").append("=").append(remarks).append(",");
        sb.append("isDeleted").append("=").append(isDeleted).append(",");
        sb.append("testName").append("=").append(testName).append(",");
        sb.append("userName").append("=").append(userName).append(",");
        sb.append("updatedAt").append("=").append(updatedAt);
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
