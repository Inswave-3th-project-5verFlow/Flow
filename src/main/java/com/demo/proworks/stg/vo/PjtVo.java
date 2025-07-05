package com.demo.proworks.stg.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "프로젝트 정보")
public class PjtVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "프로젝트ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtId;

    @ElDtoField(logicalName = "프로젝트명", physicalName = "pjtName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtName;

    @ElDtoField(logicalName = "프로젝트관리자", physicalName = "pjtAsi", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtAsi;

    @ElDtoField(logicalName = "프로젝트상태", physicalName = "pjtStatus", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtStatus;

    @ElDtoField(logicalName = "상세설명", physicalName = "pjtDetail", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtDetail;

    @ElDtoField(logicalName = "등록일자", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일자", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String updatedAt;

    @ElDtoField(logicalName = "시작일", physicalName = "pjtSt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtSt;

    @ElDtoField(logicalName = "완료일", physicalName = "pjtEt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pjtEt;

    @ElDtoField(logicalName = "search_프로젝트ID", physicalName = "scPjtId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scPjtId;

    @ElDtoField(logicalName = "search_프로젝트명", physicalName = "scPjtName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scPjtName;

    @ElDtoField(logicalName = "search_프로젝트관리자", physicalName = "scPjtAsi", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scPjtAsi;

    @ElDtoField(logicalName = "search_프로젝트상태", physicalName = "scPjtStatus", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scPjtStatus;

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        return pjtId;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
    }

    @ElVoField(physicalName = "pjtName")
    public String getPjtName(){
        return pjtName;
    }

    @ElVoField(physicalName = "pjtName")
    public void setPjtName(String pjtName){
        this.pjtName = pjtName;
    }

    @ElVoField(physicalName = "pjtAsi")
    public String getPjtAsi(){
        return pjtAsi;
    }

    @ElVoField(physicalName = "pjtAsi")
    public void setPjtAsi(String pjtAsi){
        this.pjtAsi = pjtAsi;
    }

    @ElVoField(physicalName = "pjtStatus")
    public String getPjtStatus(){
        return pjtStatus;
    }

    @ElVoField(physicalName = "pjtStatus")
    public void setPjtStatus(String pjtStatus){
        this.pjtStatus = pjtStatus;
    }

    @ElVoField(physicalName = "pjtDetail")
    public String getPjtDetail(){
        return pjtDetail;
    }

    @ElVoField(physicalName = "pjtDetail")
    public void setPjtDetail(String pjtDetail){
        this.pjtDetail = pjtDetail;
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

    @ElVoField(physicalName = "pjtSt")
    public String getPjtSt(){
        return pjtSt;
    }

    @ElVoField(physicalName = "pjtSt")
    public void setPjtSt(String pjtSt){
        this.pjtSt = pjtSt;
    }

    @ElVoField(physicalName = "pjtEt")
    public String getPjtEt(){
        return pjtEt;
    }

    @ElVoField(physicalName = "pjtEt")
    public void setPjtEt(String pjtEt){
        this.pjtEt = pjtEt;
    }

    @ElVoField(physicalName = "scPjtId")
    public String getScPjtId(){
        return scPjtId;
    }

    @ElVoField(physicalName = "scPjtId")
    public void setScPjtId(String scPjtId) {
        this.scPjtId = scPjtId;
    }

    @ElVoField(physicalName = "scPjtName")
    public String getScPjtName(){
        return scPjtName;
    }

    @ElVoField(physicalName = "scPjtName")
    public void setScPjtName(String scPjtName) {
        this.scPjtName = scPjtName;
    }

    @ElVoField(physicalName = "scPjtAsi")
    public String getScPjtAsi(){
        return scPjtAsi;
    }

    @ElVoField(physicalName = "scPjtAsi")
    public void setScPjtAsi(String scPjtAsi) {
        this.scPjtAsi = scPjtAsi;
    }

    @ElVoField(physicalName = "scPjtStatus")
    public String getScPjtStatus(){
        return scPjtStatus;
    }

    @ElVoField(physicalName = "scPjtStatus")
    public void setScPjtStatus(String scPjtStatus) {
        this.scPjtStatus = scPjtStatus;
    }

    @Override
    public String toString() {
        return "PjtVo [pjtId=" + pjtId + ",pjtName=" + pjtName + ",pjtAsi=" + pjtAsi + ",pjtStatus=" + pjtStatus + ",pjtDetail=" + pjtDetail + ",createdAt=" + createdAt + ",updatedAt=" + updatedAt + ",pjtSt=" + pjtSt + ",pjtEt=" + pjtEt + ",scPjtId=" + scPjtId + ",scPjtName=" + scPjtName + ",scPjtAsi=" + scPjtAsi + ",scPjtStatus=" + scPjtStatus + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
