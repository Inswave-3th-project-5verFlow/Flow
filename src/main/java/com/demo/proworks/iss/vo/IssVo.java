package com.demo.proworks.iss.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이슈리스크관리")
public class IssVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public IssVo(){
    }

    @ElDtoField(logicalName = "프로젝트 ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtId;

    @ElDtoField(logicalName = "이슈/리스크 고유 ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String id;

    @ElDtoField(logicalName = "이슈/리스크명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "작성자 사용자 ID", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "완료 예정일", physicalName = "dueDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String dueDate;

    @ElDtoField(logicalName = "상태", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "우선순위", physicalName = "priority", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String priority;

    @ElDtoField(logicalName = "상세 내용", physicalName = "description", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String description;

    @ElDtoField(logicalName = "등록일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일시", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedAt;

    @ElDtoField(logicalName = "해결일", physicalName = "resolvedDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String resolvedDate;

    @ElDtoField(logicalName = "대응방안", physicalName = "responsePlan", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String responsePlan;

    @ElDtoField(logicalName = "삭제여부", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isDeleted;

    @ElDtoField(logicalName = "프로젝트명", physicalName = "pjtName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtName;

    @ElDtoField(logicalName = "작성자명", physicalName = "userName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userName;

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        String ret = this.pjtId;
        return ret;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
    }

    @ElVoField(physicalName = "id")
    public String getId(){
        String ret = this.id;
        return ret;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
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

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        String ret = this.userId;
        return ret;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "dueDate")
    public String getDueDate(){
        String ret = this.dueDate;
        return ret;
    }

    @ElVoField(physicalName = "dueDate")
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
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

    @ElVoField(physicalName = "priority")
    public String getPriority(){
        String ret = this.priority;
        return ret;
    }

    @ElVoField(physicalName = "priority")
    public void setPriority(String priority){
        this.priority = priority;
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

    @ElVoField(physicalName = "createdAt")
    public String getCreatedAt(){
        String ret = this.createdAt;
        return ret;
    }

    @ElVoField(physicalName = "createdAt")
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
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

    @ElVoField(physicalName = "resolvedDate")
    public String getResolvedDate(){
        String ret = this.resolvedDate;
        return ret;
    }

    @ElVoField(physicalName = "resolvedDate")
    public void setResolvedDate(String resolvedDate){
        this.resolvedDate = resolvedDate;
    }

    @ElVoField(physicalName = "responsePlan")
    public String getResponsePlan(){
        String ret = this.responsePlan;
        return ret;
    }

    @ElVoField(physicalName = "responsePlan")
    public void setResponsePlan(String responsePlan){
        this.responsePlan = responsePlan;
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

    @ElVoField(physicalName = "pjtName")
    public String getPjtName(){
        String ret = this.pjtName;
        return ret;
    }

    @ElVoField(physicalName = "pjtName")
    public void setPjtName(String pjtName){
        this.pjtName = pjtName;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IssVo [");
        sb.append("pjtId").append("=").append(pjtId).append(",");
        sb.append("id").append("=").append(id).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("dueDate").append("=").append(dueDate).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("priority").append("=").append(priority).append(",");
        sb.append("description").append("=").append(description).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("updatedAt").append("=").append(updatedAt).append(",");
        sb.append("resolvedDate").append("=").append(resolvedDate).append(",");
        sb.append("responsePlan").append("=").append(responsePlan).append(",");
        sb.append("isDeleted").append("=").append(isDeleted).append(",");
        sb.append("pjtName").append("=").append(pjtName).append(",");
        sb.append("userName").append("=").append(userName);
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
