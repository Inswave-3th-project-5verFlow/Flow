package com.demo.proworks.menu.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "메뉴-그룹 정보")
public class MenuGroupsVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "menu_id", physicalName = "menuId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String menuId;

    @ElDtoField(logicalName = "grp_id", physicalName = "grpId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String grpId;

    @ElVoField(physicalName = "menuId")
    public String getMenuId(){
        return menuId;
    }

    @ElVoField(physicalName = "menuId")
    public void setMenuId(String menuId){
        this.menuId = menuId;
    }

    @ElVoField(physicalName = "grpId")
    public String getGrpId(){
        return grpId;
    }

    @ElVoField(physicalName = "grpId")
    public void setGrpId(String grpId){
        this.grpId = grpId;
    }

    @Override
    public String toString() {
        return "MenuGroupsVo [menuId=" + menuId + ",grpId=" + grpId + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
