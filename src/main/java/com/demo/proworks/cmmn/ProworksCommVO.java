package com.demo.proworks.cmmn;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "공통VO")
public class ProworksCommVO extends com.inswave.elfw.core.CommVO {
    private static final long serialVersionUID = 1L;

    public ProworksCommVO(){
    }

    @ElDtoField(logicalName = "페이지Unit", physicalName = "pageUnit", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "10", desc = "", attr = "")
    private int pageUnit = 10;

    @ElDtoField(logicalName = "페이지크기", physicalName = "pageSize", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "10", desc = "", attr = "")
    private int pageSize = 10;

    @ElDtoField(logicalName = "파일참조타입(이슈/산출물/결함)", physicalName = "refType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String refType;

    @ElDtoField(logicalName = "파일참조아이디", physicalName = "refId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String refId;

    @ElVoField(physicalName = "pageUnit")
    public int getPageUnit(){
        return pageUnit;
    }

    @ElVoField(physicalName = "pageUnit")
    public void setPageUnit(int pageUnit){
        this.pageUnit = pageUnit;
    }

    @ElVoField(physicalName = "pageSize")
    public int getPageSize(){
        return pageSize;
    }

    @ElVoField(physicalName = "pageSize")
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProworksCommVO [");
        sb.append("pageUnit").append("=").append(pageUnit).append(",");
        sb.append("pageSize").append("=").append(pageSize).append(",");
        sb.append("refType").append("=").append(refType).append(",");
        sb.append("refId").append("=").append(refId);
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
