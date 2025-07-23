package com.demo.proworks.menu.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "메뉴-그룹 정보")
public class MenuListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "메뉴-그룹 정보List", physicalName = "menuVoList", type = "com.demo.proworks.menu.MenuVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.menu.vo.MenuVo> menuVoList;

    public java.util.List<com.demo.proworks.menu.vo.MenuVo> getMenuVoList(){
        return menuVoList;
    }

    public void setMenuVoList(java.util.List<com.demo.proworks.menu.vo.MenuVo> menuVoList){
        this.menuVoList = menuVoList;
    }

    @Override
    public String toString() {
        return "MenuListVo [menuVoList=" + menuVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
