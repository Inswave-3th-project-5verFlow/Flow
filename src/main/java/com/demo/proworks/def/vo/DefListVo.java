package com.demo.proworks.def.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "테스트결함관리")
public class DefListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "테스트결함관리List", physicalName = "defVoList", type = "com.demo.proworks.def.DefVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.def.vo.DefVo> defVoList;

    public java.util.List<com.demo.proworks.def.vo.DefVo> getDefVoList(){
        return defVoList;
    }

    public void setDefVoList(java.util.List<com.demo.proworks.def.vo.DefVo> defVoList){
        this.defVoList = defVoList;
    }

    @Override
    public String toString() {
        return "DefListVo [defVoList=" + defVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
