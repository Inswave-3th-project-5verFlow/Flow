package com.demo.proworks.cmt.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "코멘트 관리")
public class CmtVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "코멘트 고유 ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String id;

    @ElDtoField(logicalName = "이슈 ID (이슈와 연결 시)", physicalName = "issueId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String issueId;

    @ElDtoField(logicalName = "결함 ID (결함과 연결 시)", physicalName = "defectId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String defectId;

    @ElDtoField(logicalName = "상위 코멘트 ID (대댓글용)", physicalName = "parentId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String parentId;

    @ElDtoField(logicalName = "코멘트 내용", physicalName = "content", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String content;

    @ElDtoField(logicalName = "등록일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일시", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String updatedAt;

    @ElDtoField(logicalName = "작성자 ID", physicalName = "userId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String userId;

    @ElDtoField(logicalName = "댓글 깊이 (0: 원댓글, 1: 대댓글)", physicalName = "depth", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String depth;

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

    @ElVoField(physicalName = "issueId")
    public String getIssueId(){
        return issueId;
    }

    @ElVoField(physicalName = "issueId")
    public void setIssueId(String issueId){
        this.issueId = issueId;
    }

    @ElVoField(physicalName = "defectId")
    public String getDefectId(){
        return defectId;
    }

    @ElVoField(physicalName = "defectId")
    public void setDefectId(String defectId){
        this.defectId = defectId;
    }

    @ElVoField(physicalName = "parentId")
    public String getParentId(){
        return parentId;
    }

    @ElVoField(physicalName = "parentId")
    public void setParentId(String parentId){
        this.parentId = parentId;
    }

    @ElVoField(physicalName = "content")
    public String getContent(){
        return content;
    }

    @ElVoField(physicalName = "content")
    public void setContent(String content){
        this.content = content;
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

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        return userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "depth")
    public String getDepth(){
        return depth;
    }

    @ElVoField(physicalName = "depth")
    public void setDepth(String depth){
        this.depth = depth;
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
        return "CmtVo [id=" + id + ",issueId=" + issueId + ",defectId=" + defectId + ",parentId=" + parentId + ",content=" + content + ",createdAt=" + createdAt + ",updatedAt=" + updatedAt + ",userId=" + userId + ",depth=" + depth + ",isDeleted=" + isDeleted + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
