package com.demo.proworks.att.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "파일첨부")
public class AttVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public AttVo(){
    }

    @ElDtoField(logicalName = "첨부ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String id;

    @ElDtoField(logicalName = "파일id", physicalName = "fileId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fileId;

    @ElDtoField(logicalName = "참조타입", physicalName = "refType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String refType;

    @ElDtoField(logicalName = "참조id", physicalName = "refId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String refId;

    @ElDtoField(logicalName = "업로드일시", physicalName = "attachedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String attachedAt;

    @ElDtoField(logicalName = "삭제여부", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isDeleted;

    @ElDtoField(logicalName = "원본파일명", physicalName = "originalFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String originalFileName;

    @ElDtoField(logicalName = "저장파일명", physicalName = "storedFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String storedFileName;

    @ElDtoField(logicalName = "파일크기", physicalName = "fileSize", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fileSize;

    @ElDtoField(logicalName = "파일확장자", physicalName = "fileExtension", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fileExtension;

    @ElDtoField(logicalName = "S3버킷", physicalName = "s3Bucket", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String s3Bucket;

    @ElDtoField(logicalName = "S3키", physicalName = "s3Key", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String s3Key;

    @ElDtoField(logicalName = "생성일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElVoField(physicalName = "id")
    public String getId(){
        String ret = this.id;
        return ret;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
    }

    @ElVoField(physicalName = "fileId")
    public String getFileId(){
        String ret = this.fileId;
        return ret;
    }

    @ElVoField(physicalName = "fileId")
    public void setFileId(String fileId){
        this.fileId = fileId;
    }

    @ElVoField(physicalName = "refType")
    public String getRefType(){
        String ret = this.refType;
        return ret;
    }

    @ElVoField(physicalName = "refType")
    public void setRefType(String refType){
        this.refType = refType;
    }

    @ElVoField(physicalName = "refId")
    public String getRefId(){
        String ret = this.refId;
        return ret;
    }

    @ElVoField(physicalName = "refId")
    public void setRefId(String refId){
        this.refId = refId;
    }

    @ElVoField(physicalName = "attachedAt")
    public String getAttachedAt(){
        String ret = this.attachedAt;
        return ret;
    }

    @ElVoField(physicalName = "attachedAt")
    public void setAttachedAt(String attachedAt){
        this.attachedAt = attachedAt;
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

    @ElVoField(physicalName = "originalFileName")
    public String getOriginalFileName(){
        String ret = this.originalFileName;
        return ret;
    }

    @ElVoField(physicalName = "originalFileName")
    public void setOriginalFileName(String originalFileName){
        this.originalFileName = originalFileName;
    }

    @ElVoField(physicalName = "storedFileName")
    public String getStoredFileName(){
        String ret = this.storedFileName;
        return ret;
    }

    @ElVoField(physicalName = "storedFileName")
    public void setStoredFileName(String storedFileName){
        this.storedFileName = storedFileName;
    }

    @ElVoField(physicalName = "fileSize")
    public String getFileSize(){
        String ret = this.fileSize;
        return ret;
    }

    @ElVoField(physicalName = "fileSize")
    public void setFileSize(String fileSize){
        this.fileSize = fileSize;
    }

    @ElVoField(physicalName = "fileExtension")
    public String getFileExtension(){
        String ret = this.fileExtension;
        return ret;
    }

    @ElVoField(physicalName = "fileExtension")
    public void setFileExtension(String fileExtension){
        this.fileExtension = fileExtension;
    }

    @ElVoField(physicalName = "s3Bucket")
    public String getS3Bucket(){
        String ret = this.s3Bucket;
        return ret;
    }

    @ElVoField(physicalName = "s3Bucket")
    public void setS3Bucket(String s3Bucket){
        this.s3Bucket = s3Bucket;
    }

    @ElVoField(physicalName = "s3Key")
    public String getS3Key(){
        String ret = this.s3Key;
        return ret;
    }

    @ElVoField(physicalName = "s3Key")
    public void setS3Key(String s3Key){
        this.s3Key = s3Key;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AttVo [");
        sb.append("id").append("=").append(id).append(",");
        sb.append("fileId").append("=").append(fileId).append(",");
        sb.append("refType").append("=").append(refType).append(",");
        sb.append("refId").append("=").append(refId).append(",");
        sb.append("attachedAt").append("=").append(attachedAt).append(",");
        sb.append("isDeleted").append("=").append(isDeleted).append(",");
        sb.append("originalFileName").append("=").append(originalFileName).append(",");
        sb.append("storedFileName").append("=").append(storedFileName).append(",");
        sb.append("fileSize").append("=").append(fileSize).append(",");
        sb.append("fileExtension").append("=").append(fileExtension).append(",");
        sb.append("s3Bucket").append("=").append(s3Bucket).append(",");
        sb.append("s3Key").append("=").append(s3Key).append(",");
        sb.append("createdAt").append("=").append(createdAt);
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
