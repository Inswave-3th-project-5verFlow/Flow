package com.demo.proworks.menu.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "메뉴-그룹 정보")
public class MenuGroupsListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "메뉴-그룹 정보List", physicalName = "menuGroupsVoList", type = "com.demo.proworks.menu.MenuGroupsVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.menu.vo.MenuGroupsVo> menuGroupsVoList;

    public java.util.List<com.demo.proworks.menu.vo.MenuGroupsVo> getMenuGroupsVoList(){
        return menuGroupsVoList;
    }

    public void setMenuGroupsVoList(java.util.List<com.demo.proworks.menu.vo.MenuGroupsVo> menuGroupsVoList){
        this.menuGroupsVoList = menuGroupsVoList;
    }

    @Override
    public String toString() {
        return "MenuGroupsListVo [menuGroupsVoList=" + menuGroupsVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
