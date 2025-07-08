package com.demo.proworks.pug.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "프로젝트 유저 그룹 매핑 정보")
public class PugVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PugVo(){
    }

    @ElDtoField(logicalName = "pug_id", physicalName = "pugId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pugId;

    @ElDtoField(logicalName = "프로젝트ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtId;

    @ElDtoField(logicalName = "사용자ID", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "그룹ID", physicalName = "grpId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpId;

    @ElDtoField(logicalName = "사용자이름", physicalName = "userName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userName;

    @ElDtoField(logicalName = "그룹명", physicalName = "grpName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpName;

    @ElDtoField(logicalName = "사용자직책", physicalName = "userPosition", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userPosition;

    @ElDtoField(logicalName = "search__사용자이름", physicalName = "scUserName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scUserName;

    @ElDtoField(logicalName = "search_그룹명", physicalName = "scGrpName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scGrpName;

    @ElDtoField(logicalName = "search_사용자ID", physicalName = "scUserId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scUserId;

    @ElDtoField(logicalName = "search_직책", physicalName = "scUserPosition", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scUserPosition;

    @ElDtoField(logicalName = "행상태", physicalName = "rowStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String rowStatus;

    @ElVoField(physicalName = "pugId")
    public String getPugId(){
        String ret = this.pugId;
        return ret;
    }

    @ElVoField(physicalName = "pugId")
    public void setPugId(String pugId){
        this.pugId = pugId;
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

    @ElVoField(physicalName = "userName")
    public String getUserName(){
        String ret = this.userName;
        return ret;
    }

    @ElVoField(physicalName = "userName")
    public void setUserName(String userName){
        this.userName = userName;
    }

    @ElVoField(physicalName = "grpName")
    public String getGrpName(){
        String ret = this.grpName;
        return ret;
    }

    @ElVoField(physicalName = "grpName")
    public void setGrpName(String grpName){
        this.grpName = grpName;
    }

    @ElVoField(physicalName = "userPosition")
    public String getUserPosition(){
        String ret = this.userPosition;
        return ret;
    }

    @ElVoField(physicalName = "userPosition")
    public void setUserPosition(String userPosition){
        this.userPosition = userPosition;
    }

    @ElVoField(physicalName = "scUserName")
    public String getScUserName(){
        String ret = this.scUserName;
        return ret;
    }

    @ElVoField(physicalName = "scUserName")
    public void setScUserName(String scUserName){
        this.scUserName = scUserName;
    }

    @ElVoField(physicalName = "scGrpName")
    public String getScGrpName(){
        String ret = this.scGrpName;
        return ret;
    }

    @ElVoField(physicalName = "scGrpName")
    public void setScGrpName(String scGrpName){
        this.scGrpName = scGrpName;
    }

    @ElVoField(physicalName = "scUserId")
    public String getScUserId(){
        String ret = this.scUserId;
        return ret;
    }

    @ElVoField(physicalName = "scUserId")
    public void setScUserId(String scUserId){
        this.scUserId = scUserId;
    }

    @ElVoField(physicalName = "scUserPosition")
    public String getScUserPosition(){
        String ret = this.scUserPosition;
        return ret;
    }

    @ElVoField(physicalName = "scUserPosition")
    public void setScUserPosition(String scUserPosition){
        this.scUserPosition = scUserPosition;
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
        sb.append("PugVo [");
        sb.append("pugId").append("=").append(pugId).append(",");
        sb.append("pjtId").append("=").append(pjtId).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("grpId").append("=").append(grpId).append(",");
        sb.append("userName").append("=").append(userName).append(",");
        sb.append("grpName").append("=").append(grpName).append(",");
        sb.append("userPosition").append("=").append(userPosition).append(",");
        sb.append("scUserName").append("=").append(scUserName).append(",");
        sb.append("scGrpName").append("=").append(scGrpName).append(",");
        sb.append("scUserId").append("=").append(scUserId).append(",");
        sb.append("scUserPosition").append("=").append(scUserPosition).append(",");
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
