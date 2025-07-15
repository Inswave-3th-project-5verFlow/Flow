package com.demo.proworks.groups.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "그룹정보")
public class GroupsVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public GroupsVo(){
    }

    @ElDtoField(logicalName = "grp_id", physicalName = "grpId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpId;

    @ElDtoField(logicalName = "grp_name", physicalName = "grpName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpName;

    @ElDtoField(logicalName = "description", physicalName = "description", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String description;

    @ElDtoField(logicalName = "rowStatus", physicalName = "rowStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String rowStatus;

    @ElVoField(physicalName = "grpId")
    public String getGrpId(){
        String ret = this.grpId;
        return ret;
    }

    @ElVoField(physicalName = "grpId")
    public void setGrpId(String grpId){
        this.grpId = grpId;
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

    @ElVoField(physicalName = "description")
    public String getDescription(){
        String ret = this.description;
        return ret;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
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
        sb.append("GroupsVo [");
        sb.append("grpId").append("=").append(grpId).append(",");
        sb.append("grpName").append("=").append(grpName).append(",");
        sb.append("description").append("=").append(description).append(",");
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
