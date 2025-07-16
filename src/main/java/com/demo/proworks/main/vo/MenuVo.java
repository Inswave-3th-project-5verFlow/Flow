package com.demo.proworks.main.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "메뉴 정보")
public class MenuVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public MenuVo(){
    }

    @ElDtoField(logicalName = "그룹ID", physicalName = "grpId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpId;

    @ElDtoField(logicalName = "메뉴ID", physicalName = "menuId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String menuId;

    @ElDtoField(logicalName = "뎁스", physicalName = "depth", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String depth;

    @ElDtoField(logicalName = "부모메뉴ID", physicalName = "parentMenuId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String parentMenuId;

    @ElDtoField(logicalName = "메뉴명", physicalName = "menuName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String menuName;

    @ElDtoField(logicalName = "경로", physicalName = "menuUrl", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String menuUrl;

    @ElVoField(physicalName = "grpId")
    public String getGrpId(){
        String ret = this.grpId;
        return ret;
    }

    @ElVoField(physicalName = "grpId")
    public void setGrpId(String grpId){
        this.grpId = grpId;
    }

    @ElVoField(physicalName = "menuId")
    public String getMenuId(){
        String ret = this.menuId;
        return ret;
    }

    @ElVoField(physicalName = "menuId")
    public void setMenuId(String menuId){
        this.menuId = menuId;
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

    @ElVoField(physicalName = "parentMenuId")
    public String getParentMenuId(){
        String ret = this.parentMenuId;
        return ret;
    }

    @ElVoField(physicalName = "parentMenuId")
    public void setParentMenuId(String parentMenuId){
        this.parentMenuId = parentMenuId;
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

    @ElVoField(physicalName = "menuUrl")
    public String getMenuUrl(){
        String ret = this.menuUrl;
        return ret;
    }

    @ElVoField(physicalName = "menuUrl")
    public void setMenuUrl(String menuUrl){
        this.menuUrl = menuUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MenuVo [");
        sb.append("grpId").append("=").append(grpId).append(",");
        sb.append("menuId").append("=").append(menuId).append(",");
        sb.append("depth").append("=").append(depth).append(",");
        sb.append("parentMenuId").append("=").append(parentMenuId).append(",");
        sb.append("menuName").append("=").append(menuName).append(",");
        sb.append("menuUrl").append("=").append(menuUrl);
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
