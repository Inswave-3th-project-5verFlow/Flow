package com.demo.proworks.iss.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "이슈리스크관리")
public class IssVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "프로젝트 ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtId;

    @ElDtoField(logicalName = "이슈/리스크 고유 ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String id;

    @ElDtoField(logicalName = "이슈/리스크명", physicalName = "name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String name;

    @ElDtoField(logicalName = "작성자 사용자 ID", physicalName = "userId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String userId;

    @ElDtoField(logicalName = "완료 예정일", physicalName = "dueDate", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String dueDate;

    @ElDtoField(logicalName = "상태 (해결 전, 해결 중, 해결 후)", physicalName = "status", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String status;

    @ElDtoField(logicalName = "우선순위 (높음, 중간, 낮음)", physicalName = "priority", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String priority;

    @ElDtoField(logicalName = "타입 (이슈, 리스크, 테스트)", physicalName = "type", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String type;

    @ElDtoField(logicalName = "상세 내용", physicalName = "description", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String description;

    @ElDtoField(logicalName = "등록일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일시", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String updatedAt;

    @ElDtoField(logicalName = "resolved_date", physicalName = "resolvedDate", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String resolvedDate;

    @ElDtoField(logicalName = "response_plan", physicalName = "responsePlan", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String responsePlan;

    @ElDtoField(logicalName = "is_deleted", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String isDeleted;

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        return pjtId;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
    }

    @ElVoField(physicalName = "id")
    public String getId(){
        return id;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        return name;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
    }

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        return userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "dueDate")
    public String getDueDate(){
        return dueDate;
    }

    @ElVoField(physicalName = "dueDate")
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        return status;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "priority")
    public String getPriority(){
        return priority;
    }

    @ElVoField(physicalName = "priority")
    public void setPriority(String priority){
        this.priority = priority;
    }

    @ElVoField(physicalName = "type")
    public String getType(){
        return type;
    }

    @ElVoField(physicalName = "type")
    public void setType(String type){
        this.type = type;
    }

    @ElVoField(physicalName = "description")
    public String getDescription(){
        return description;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
    }

    @ElVoField(physicalName = "createdAt")
    public String getCreatedAt(){
        return createdAt;
    }

    @ElVoField(physicalName = "createdAt")
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    @ElVoField(physicalName = "updatedAt")
    public String getUpdatedAt(){
        return updatedAt;
    }

    @ElVoField(physicalName = "updatedAt")
    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    @ElVoField(physicalName = "resolvedDate")
    public String getResolvedDate(){
        return resolvedDate;
    }

    @ElVoField(physicalName = "resolvedDate")
    public void setResolvedDate(String resolvedDate){
        this.resolvedDate = resolvedDate;
    }

    @ElVoField(physicalName = "responsePlan")
    public String getResponsePlan(){
        return responsePlan;
    }

    @ElVoField(physicalName = "responsePlan")
    public void setResponsePlan(String responsePlan){
        this.responsePlan = responsePlan;
    }

    @ElVoField(physicalName = "isDeleted")
    public String getIsDeleted(){
        return isDeleted;
    }

    @ElVoField(physicalName = "isDeleted")
    public void setIsDeleted(String isDeleted){
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "IssVo [pjtId=" + pjtId + ",id=" + id + ",name=" + name + ",userId=" + userId + ",dueDate=" + dueDate + ",status=" + status + ",priority=" + priority + ",type=" + type + ",description=" + description + ",createdAt=" + createdAt + ",updatedAt=" + updatedAt + ",resolvedDate=" + resolvedDate + ",responsePlan=" + responsePlan + ",isDeleted=" + isDeleted + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
