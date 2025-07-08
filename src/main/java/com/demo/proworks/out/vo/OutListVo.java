package com.demo.proworks.out.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "산출물관리")
public class OutListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "산출물관리List", physicalName = "outVoList", type = "com.demo.proworks.out.OutVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.out.vo.OutVo> outVoList;

    public java.util.List<com.demo.proworks.out.vo.OutVo> getOutVoList(){
        return outVoList;
    }

    public void setOutVoList(java.util.List<com.demo.proworks.out.vo.OutVo> outVoList){
        this.outVoList = outVoList;
    }

    @Override
    public String toString() {
        return "OutListVo [outVoList=" + outVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
