package com.demo.proworks.pjt.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "프로젝트 기본 정보")
public class PjtVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PjtVo(){
    }

    @ElDtoField(logicalName = "프로젝트ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtId;

    @ElDtoField(logicalName = "프로젝트명", physicalName = "pjtName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtName;

    @ElDtoField(logicalName = "관리자ID", physicalName = "pjtUserId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtUserId;

    @ElDtoField(logicalName = "시작예정일", physicalName = "pjtSt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtSt;

    @ElDtoField(logicalName = "완료예정일", physicalName = "pjtEd", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtEd;

    @ElDtoField(logicalName = "프로젝트상태", physicalName = "pjtStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtStatus;

    @ElDtoField(logicalName = "상세설명", physicalName = "pjtDetail", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtDetail;

    @ElDtoField(logicalName = "등록일자", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일자", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedAt;

    @ElDtoField(logicalName = "실제시작일", physicalName = "pjtRst", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtRst;

    @ElDtoField(logicalName = "실제완료일", physicalName = "pjtRed", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtRed;

    @ElDtoField(logicalName = "search_프로젝트ID", physicalName = "scPjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scPjtId;

    @ElDtoField(logicalName = "search_프로젝트명", physicalName = "scPjtName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scPjtName;

    @ElDtoField(logicalName = "search_관리자ID", physicalName = "scPjtUserId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scPjtUserId;

    @ElDtoField(logicalName = "search_프로젝트상태", physicalName = "scPjtStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scPjtStatus;

    @ElDtoField(logicalName = "rowStatus", physicalName = "rowStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String rowStatus;

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

    @ElVoField(physicalName = "pjtUserId")
    public String getPjtUserId(){
        String ret = this.pjtUserId;
        return ret;
    }

    @ElVoField(physicalName = "pjtUserId")
    public void setPjtUserId(String pjtUserId){
        this.pjtUserId = pjtUserId;
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

    @ElVoField(physicalName = "pjtEd")
    public String getPjtEd(){
        String ret = this.pjtEd;
        return ret;
    }

    @ElVoField(physicalName = "pjtEd")
    public void setPjtEd(String pjtEd){
        this.pjtEd = pjtEd;
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

    @ElVoField(physicalName = "pjtRst")
    public String getPjtRst(){
        String ret = this.pjtRst;
        return ret;
    }

    @ElVoField(physicalName = "pjtRst")
    public void setPjtRst(String pjtRst){
        this.pjtRst = pjtRst;
    }

    @ElVoField(physicalName = "pjtRed")
    public String getPjtRed(){
        String ret = this.pjtRed;
        return ret;
    }

    @ElVoField(physicalName = "pjtRed")
    public void setPjtRed(String pjtRed){
        this.pjtRed = pjtRed;
    }

    @ElVoField(physicalName = "scPjtId")
    public String getScPjtId(){
        String ret = this.scPjtId;
        return ret;
    }

    @ElVoField(physicalName = "scPjtId")
    public void setScPjtId(String scPjtId){
        this.scPjtId = scPjtId;
    }

    @ElVoField(physicalName = "scPjtName")
    public String getScPjtName(){
        String ret = this.scPjtName;
        return ret;
    }

    @ElVoField(physicalName = "scPjtName")
    public void setScPjtName(String scPjtName){
        this.scPjtName = scPjtName;
    }

    @ElVoField(physicalName = "scPjtUserId")
    public String getScPjtUserId(){
        String ret = this.scPjtUserId;
        return ret;
    }

    @ElVoField(physicalName = "scPjtUserId")
    public void setScPjtUserId(String scPjtUserId){
        this.scPjtUserId = scPjtUserId;
    }

    @ElVoField(physicalName = "scPjtStatus")
    public String getScPjtStatus(){
        String ret = this.scPjtStatus;
        return ret;
    }

    @ElVoField(physicalName = "scPjtStatus")
    public void setScPjtStatus(String scPjtStatus){
        this.scPjtStatus = scPjtStatus;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PjtVo [");
        sb.append("pjtId").append("=").append(pjtId).append(",");
        sb.append("pjtName").append("=").append(pjtName).append(",");
        sb.append("pjtUserId").append("=").append(pjtUserId).append(",");
        sb.append("pjtSt").append("=").append(pjtSt).append(",");
        sb.append("pjtEd").append("=").append(pjtEd).append(",");
        sb.append("pjtStatus").append("=").append(pjtStatus).append(",");
        sb.append("pjtDetail").append("=").append(pjtDetail).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("updatedAt").append("=").append(updatedAt).append(",");
        sb.append("pjtRst").append("=").append(pjtRst).append(",");
        sb.append("pjtRed").append("=").append(pjtRed).append(",");
        sb.append("scPjtId").append("=").append(scPjtId).append(",");
        sb.append("scPjtName").append("=").append(scPjtName).append(",");
        sb.append("scPjtUserId").append("=").append(scPjtUserId).append(",");
        sb.append("scPjtStatus").append("=").append(scPjtStatus).append(",");
        sb.append("rowStatus").append("=").append(rowStatus);
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
