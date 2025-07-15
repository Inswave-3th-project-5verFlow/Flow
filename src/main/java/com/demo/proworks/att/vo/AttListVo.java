package com.demo.proworks.att.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "파일관리")
public class AttListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "파일관리List", physicalName = "attVoList", type = "com.demo.proworks.att.AttVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.att.vo.AttVo> attVoList;

    public java.util.List<com.demo.proworks.att.vo.AttVo> getAttVoList(){
        return attVoList;
    }

    public void setAttVoList(java.util.List<com.demo.proworks.att.vo.AttVo> attVoList){
        this.attVoList = attVoList;
    }

    @Override
    public String toString() {
        return "AttListVo [attVoList=" + attVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
