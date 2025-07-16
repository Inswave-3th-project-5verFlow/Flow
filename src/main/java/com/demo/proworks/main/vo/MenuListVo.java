package com.demo.proworks.main.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "메뉴 정보")
public class MenuListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public MenuListVo(){
    }

    @ElDtoField(logicalName = "메뉴 정보", physicalName = "menuVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.main.vo.MenuVo> menuVo;

    @ElVoField(physicalName = "menuVo")
    public java.util.List<com.demo.proworks.main.vo.MenuVo> getMenuVo(){
        return menuVo;
    }

    @ElVoField(physicalName = "menuVo")
    public void setMenuVo(java.util.List<com.demo.proworks.main.vo.MenuVo> menuVo){
        this.menuVo = menuVo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MenuListVo [");
        sb.append("menuVo").append("=").append(menuVo);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; menuVo != null && i < menuVo.size() ; i++ ) {
            com.demo.proworks.main.vo.MenuVo vo = (com.demo.proworks.main.vo.MenuVo)menuVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; menuVo != null && i < menuVo.size() ; i++ ) {
            com.demo.proworks.main.vo.MenuVo vo = (com.demo.proworks.main.vo.MenuVo)menuVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
