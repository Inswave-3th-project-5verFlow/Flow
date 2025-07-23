package com.demo.proworks.menu.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "메뉴-그룹 정보")
public class MenuVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public MenuVo(){
    }

    @ElDtoField(logicalName = "menu_id", physicalName = "menuId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String menuId;

    @ElDtoField(logicalName = "menu_name", physicalName = "menuName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String menuName;

    @ElDtoField(logicalName = "description", physicalName = "description", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String description;

    @ElDtoField(logicalName = "parent_menu_id", physicalName = "parentMenuId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String parentMenuId;

    @ElDtoField(logicalName = "depth", physicalName = "depth", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String depth;

    @ElDtoField(logicalName = "url", physicalName = "url", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String url;

    @ElDtoField(logicalName = "grp_id", physicalName = "grpId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpId;

    @ElVoField(physicalName = "menuId")
    public String getMenuId(){
        String ret = this.menuId;
        return ret;
    }

    @ElVoField(physicalName = "menuId")
    public void setMenuId(String menuId){
        this.menuId = menuId;
    }

    @ElVoField(physicalName = "menuName")
    public String getMenuName(){
        String ret = this.menuName;
        return ret;
    }

    @ElVoField(physicalName = "menuName")
    public void setMenuName(String menuName){
        this.menuName = menuName;
    }

    @ElVoField(physicalName = "description")
    public String getDescription(){
        String ret = this.description;
        return ret;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
    }

    @ElVoField(physicalName = "parentMenuId")
    public String getParentMenuId(){
        String ret = this.parentMenuId;
        return ret;
    }

    @ElVoField(physicalName = "parentMenuId")
    public void setParentMenuId(String parentMenuId){
        this.parentMenuId = parentMenuId;
    }

    @ElVoField(physicalName = "depth")
    public String getDepth(){
        String ret = this.depth;
        return ret;
    }

    @ElVoField(physicalName = "depth")
    public void setDepth(String depth){
        this.depth = depth;
    }

    @ElVoField(physicalName = "url")
    public String getUrl(){
        String ret = this.url;
        return ret;
    }

    @ElVoField(physicalName = "url")
    public void setUrl(String url){
        this.url = url;
    }

    @ElVoField(physicalName = "grpId")
    public String getGrpId(){
        String ret = this.grpId;
        return ret;
    }

    @ElVoField(physicalName = "grpId")
    public void setGrpId(String grpId){
        this.grpId = grpId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MenuVo [");
        sb.append("menuId").append("=").append(menuId).append(",");
        sb.append("menuName").append("=").append(menuName).append(",");
        sb.append("description").append("=").append(description).append(",");
        sb.append("parentMenuId").append("=").append(parentMenuId).append(",");
        sb.append("depth").append("=").append(depth).append(",");
        sb.append("url").append("=").append(url).append(",");
        sb.append("grpId").append("=").append(grpId);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
    }


    @Override
    public void _xStreamDec() {
    }


}
