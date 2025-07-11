package com.demo.proworks.out.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "산출물관리")
public class OutVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public OutVo(){
    }

    @ElDtoField(logicalName = "산출물 고유 ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String id;

    @ElDtoField(logicalName = "프로젝트 ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtId;

    @ElDtoField(logicalName = "산출물명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "등록일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일시", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedAt;

    @ElDtoField(logicalName = "승인 상태", physicalName = "approvalStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String approvalStatus;

    @ElDtoField(logicalName = "승인일시", physicalName = "approvalDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String approvalDate;

    @ElDtoField(logicalName = "승인/반려 의견", physicalName = "approvalComment", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String approvalComment;

    @ElDtoField(logicalName = "산출물 유형", physicalName = "outputType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String outputType;

    @ElDtoField(logicalName = "삭제 여부", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isDeleted;

    @ElVoField(physicalName = "id")
    public String getId(){
        String ret = this.id;
        return ret;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
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

    @ElVoField(physicalName = "name")
    public String getName(){
        String ret = this.name;
        return ret;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
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

    @ElVoField(physicalName = "approvalStatus")
    public String getApprovalStatus(){
        String ret = this.approvalStatus;
        return ret;
    }

    @ElVoField(physicalName = "approvalStatus")
    public void setApprovalStatus(String approvalStatus){
        this.approvalStatus = approvalStatus;
    }

    @ElVoField(physicalName = "approvalDate")
    public String getApprovalDate(){
        String ret = this.approvalDate;
        return ret;
    }

    @ElVoField(physicalName = "approvalDate")
    public void setApprovalDate(String approvalDate){
        this.approvalDate = approvalDate;
    }

    @ElVoField(physicalName = "approvalComment")
    public String getApprovalComment(){
        String ret = this.approvalComment;
        return ret;
    }

    @ElVoField(physicalName = "approvalComment")
    public void setApprovalComment(String approvalComment){
        this.approvalComment = approvalComment;
    }

    @ElVoField(physicalName = "outputType")
    public String getOutputType(){
        String ret = this.outputType;
        return ret;
    }

    @ElVoField(physicalName = "outputType")
    public void setOutputType(String outputType){
        this.outputType = outputType;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OutVo [");
        sb.append("id").append("=").append(id).append(",");
        sb.append("pjtId").append("=").append(pjtId).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("updatedAt").append("=").append(updatedAt).append(",");
        sb.append("approvalStatus").append("=").append(approvalStatus).append(",");
        sb.append("approvalDate").append("=").append(approvalDate).append(",");
        sb.append("approvalComment").append("=").append(approvalComment).append(",");
        sb.append("outputType").append("=").append(outputType).append(",");
        sb.append("isDeleted").append("=").append(isDeleted);
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
