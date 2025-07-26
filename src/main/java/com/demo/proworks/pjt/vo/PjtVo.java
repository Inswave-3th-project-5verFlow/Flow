package com.demo.proworks.pjt.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "프로젝트 정보")
public class PjtVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PjtVo(){
    }

    @ElDtoField(logicalName = "프로젝트ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtId;

    @ElDtoField(logicalName = "프로젝트명", physicalName = "pjtName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtName;

    @ElDtoField(logicalName = "프로젝트관리자", physicalName = "pjtAsi", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtAsi;

    @ElDtoField(logicalName = "프로젝트상태", physicalName = "pjtStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtStatus;

    @ElDtoField(logicalName = "상세설명", physicalName = "pjtDetail", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtDetail;

    @ElDtoField(logicalName = "등록일자", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일자", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedAt;

    @ElDtoField(logicalName = "시작일", physicalName = "pjtSt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtSt;

    @ElDtoField(logicalName = "완료일", physicalName = "pjtEt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtEt;

    @ElDtoField(logicalName = "로그인유저", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "메뉴그룹아이디", physicalName = "grpId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpId;

    @ElDtoField(logicalName = "진척률", physicalName = "pjtProgress", type = "Integer", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Integer pjtProgress;

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        String ret = this.pjtId;
        return ret;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
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

    @ElVoField(physicalName = "pjtAsi")
    public String getPjtAsi(){
        String ret = this.pjtAsi;
        return ret;
    }

    @ElVoField(physicalName = "pjtAsi")
    public void setPjtAsi(String pjtAsi){
        this.pjtAsi = pjtAsi;
    }

    @ElVoField(physicalName = "pjtStatus")
    public String getPjtStatus(){
        String ret = this.pjtStatus;
        return ret;
    }

    @ElVoField(physicalName = "pjtStatus")
    public void setPjtStatus(String pjtStatus){
        this.pjtStatus = pjtStatus;
    }

    @ElVoField(physicalName = "pjtDetail")
    public String getPjtDetail(){
        String ret = this.pjtDetail;
        return ret;
    }

    @ElVoField(physicalName = "pjtDetail")
    public void setPjtDetail(String pjtDetail){
        this.pjtDetail = pjtDetail;
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

    @ElVoField(physicalName = "pjtSt")
    public String getPjtSt(){
        String ret = this.pjtSt;
        return ret;
    }

    @ElVoField(physicalName = "pjtSt")
    public void setPjtSt(String pjtSt){
        this.pjtSt = pjtSt;
    }

    @ElVoField(physicalName = "pjtEt")
    public String getPjtEt(){
        String ret = this.pjtEt;
        return ret;
    }

    @ElVoField(physicalName = "pjtEt")
    public void setPjtEt(String pjtEt){
        this.pjtEt = pjtEt;
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

    @ElVoField(physicalName = "grpId")
    public String getGrpId(){
        String ret = this.grpId;
        return ret;
    }

    @ElVoField(physicalName = "grpId")
    public void setGrpId(String grpId){
        this.grpId = grpId;
    }

    @ElVoField(physicalName = "pjtProgress")
    public Integer getPjtProgress(){
        Integer ret = this.pjtProgress;
        return ret;
    }

    @ElVoField(physicalName = "pjtProgress")
    public void setPjtProgress(Integer pjtProgress){
        this.pjtProgress = pjtProgress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PjtVo [");
        sb.append("pjtId").append("=").append(pjtId).append(",");
        sb.append("pjtName").append("=").append(pjtName).append(",");
        sb.append("pjtAsi").append("=").append(pjtAsi).append(",");
        sb.append("pjtStatus").append("=").append(pjtStatus).append(",");
        sb.append("pjtDetail").append("=").append(pjtDetail).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("updatedAt").append("=").append(updatedAt).append(",");
        sb.append("pjtSt").append("=").append(pjtSt).append(",");
        sb.append("pjtEt").append("=").append(pjtEt).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("grpId").append("=").append(grpId).append(",");
        sb.append("pjtProgress").append("=").append(pjtProgress);
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
