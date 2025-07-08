package com.demo.proworks.pjt.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "프로젝트 정보")
public class PjtListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "프로젝트 정보List", physicalName = "pjtVoList", type = "com.demo.proworks.pjt.PjtVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.pjt.vo.PjtVo> pjtVoList;

    public java.util.List<com.demo.proworks.pjt.vo.PjtVo> getPjtVoList(){
        return pjtVoList;
    }

    public void setPjtVoList(java.util.List<com.demo.proworks.pjt.vo.PjtVo> pjtVoList){
        this.pjtVoList = pjtVoList;
    }

    @Override
    public String toString() {
        return "PjtListVo [pjtVoList=" + pjtVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
