package com.demo.proworks.pug.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "프로젝트 유저 그룹 매핑 정보")
public class PugListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PugListVo(){
    }

    @ElDtoField(logicalName = "프로젝트 유저 그룹 매핑 정보List", physicalName = "pugVoList", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.pug.vo.PugVo> pugVoList;

    @ElVoField(physicalName = "pugVoList")
    public java.util.List<com.demo.proworks.pug.vo.PugVo> getPugVoList(){
        return pugVoList;
    }

    @ElVoField(physicalName = "pugVoList")
    public void setPugVoList(java.util.List<com.demo.proworks.pug.vo.PugVo> pugVoList){
        this.pugVoList = pugVoList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PugListVo [");
        sb.append("pugVoList").append("=").append(pugVoList);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; pugVoList != null && i < pugVoList.size() ; i++ ) {
            com.demo.proworks.pug.vo.PugVo vo = (com.demo.proworks.pug.vo.PugVo)pugVoList.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; pugVoList != null && i < pugVoList.size() ; i++ ) {
            com.demo.proworks.pug.vo.PugVo vo = (com.demo.proworks.pug.vo.PugVo)pugVoList.get(i);
            vo._xStreamDec();	 
        }
    }


}
