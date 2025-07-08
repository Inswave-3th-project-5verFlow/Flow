package com.demo.proworks.out.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "산출물관리")
public class OutVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "산출물 고유 ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String id;

    @ElDtoField(logicalName = "프로젝트 ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtId;

    @ElDtoField(logicalName = "산출물명", physicalName = "name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String name;

    @ElDtoField(logicalName = "등록일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일시", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String updatedAt;

    @ElDtoField(logicalName = "승인 상태 (대기/승인/반려)", physicalName = "approvalStatus", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String approvalStatus;

    @ElDtoField(logicalName = "승인일시", physicalName = "approvalDate", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String approvalDate;

    @ElDtoField(logicalName = "승인/반려 의견", physicalName = "approvalComment", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String approvalComment;

    @ElDtoField(logicalName = "산출물 유형", physicalName = "outputType", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String outputType;

    @ElDtoField(logicalName = "삭제 여부", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String isDeleted;

    @ElVoField(physicalName = "id")
    public String getId(){
        return id;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
    }

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        return pjtId;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        return name;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
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

    @ElVoField(physicalName = "approvalStatus")
    public String getApprovalStatus(){
        return approvalStatus;
    }

    @ElVoField(physicalName = "approvalStatus")
    public void setApprovalStatus(String approvalStatus){
        this.approvalStatus = approvalStatus;
    }

    @ElVoField(physicalName = "approvalDate")
    public String getApprovalDate(){
        return approvalDate;
    }

    @ElVoField(physicalName = "approvalDate")
    public void setApprovalDate(String approvalDate){
        this.approvalDate = approvalDate;
    }

    @ElVoField(physicalName = "approvalComment")
    public String getApprovalComment(){
        return approvalComment;
    }

    @ElVoField(physicalName = "approvalComment")
    public void setApprovalComment(String approvalComment){
        this.approvalComment = approvalComment;
    }

    @ElVoField(physicalName = "outputType")
    public String getOutputType(){
        return outputType;
    }

    @ElVoField(physicalName = "outputType")
    public void setOutputType(String outputType){
        this.outputType = outputType;
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
        return "OutVo [id=" + id + ",pjtId=" + pjtId + ",name=" + name + ",createdAt=" + createdAt + ",updatedAt=" + updatedAt + ",approvalStatus=" + approvalStatus + ",approvalDate=" + approvalDate + ",approvalComment=" + approvalComment + ",outputType=" + outputType + ",isDeleted=" + isDeleted + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
