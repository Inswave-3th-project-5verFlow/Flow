package com.demo.proworks.wbs.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "WBS")
public class WbsListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "WBSList", physicalName = "wbsVoList", type = "com.demo.proworks.wbs.WbsVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.wbs.vo.WbsVo> wbsVoList;

    public java.util.List<com.demo.proworks.wbs.vo.WbsVo> getWbsVoList(){
        return wbsVoList;
    }

    public void setWbsVoList(java.util.List<com.demo.proworks.wbs.vo.WbsVo> wbsVoList){
        this.wbsVoList = wbsVoList;
    }

    @Override
    public String toString() {
        return "WbsListVo [wbsVoList=" + wbsVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
