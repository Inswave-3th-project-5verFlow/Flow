package com.demo.proworks.wbs.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "단계 정보")
public class WbsStgVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public WbsStgVo(){
    }

    @ElDtoField(logicalName = "단계ID", physicalName = "stgId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String stgId;

    @ElDtoField(logicalName = "단계이름", physicalName = "stgName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String stgName;

    @ElVoField(physicalName = "stgId")
    public String getStgId(){
        String ret = this.stgId;
        return ret;
    }

    @ElVoField(physicalName = "stgId")
    public void setStgId(String stgId){
        this.stgId = stgId;
    }

    @ElVoField(physicalName = "stgName")
    public String getStgName(){
        String ret = this.stgName;
        return ret;
    }

    @ElVoField(physicalName = "stgName")
    public void setStgName(String stgName){
        this.stgName = stgName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WbsStgVo [");
        sb.append("stgId").append("=").append(stgId).append(",");
        sb.append("stgName").append("=").append(stgName);
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
