package com.demo.proworks.def.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "코멘트 관리")
public class CmtListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "코멘트 관리List", physicalName = "cmtVoList", type = "com.demo.proworks.def.CmtVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.def.vo.CmtVo> cmtVoList;

    public java.util.List<com.demo.proworks.def.vo.CmtVo> getCmtVoList(){
        return cmtVoList;
    }

    public void setCmtVoList(java.util.List<com.demo.proworks.def.vo.CmtVo> cmtVoList){
        this.cmtVoList = cmtVoList;
    }

    @Override
    public String toString() {
        return "CmtListVo [cmtVoList=" + cmtVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
