package com.demo.proworks.pug.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "프로젝트 유저 그룹 매핑 정보")
public class PugListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "프로젝트 유저 그룹 매핑 정보List", physicalName = "pugVoList", type = "com.demo.proworks.pug.PugVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.pug.vo.PugVo> pugVoList;

    public java.util.List<com.demo.proworks.pug.vo.PugVo> getPugVoList(){
        return pugVoList;
    }

    public void setPugVoList(java.util.List<com.demo.proworks.pug.vo.PugVo> pugVoList){
        this.pugVoList = pugVoList;
    }

    @Override
    public String toString() {
        return "PugListVo [pugVoList=" + pugVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
