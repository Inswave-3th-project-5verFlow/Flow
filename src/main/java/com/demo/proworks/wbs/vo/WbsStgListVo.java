package com.demo.proworks.wbs.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "단계 정보")
public class WbsStgListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public WbsStgListVo(){
    }

    @ElDtoField(logicalName = "단계 정보", physicalName = "wbsStgVoList", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.wbs.vo.WbsStgVo> wbsStgVoList;

    @ElVoField(physicalName = "wbsStgVoList")
    public java.util.List<com.demo.proworks.wbs.vo.WbsStgVo> getWbsStgVoList(){
        return wbsStgVoList;
    }

    @ElVoField(physicalName = "wbsStgVoList")
    public void setWbsStgVoList(java.util.List<com.demo.proworks.wbs.vo.WbsStgVo> wbsStgVoList){
        this.wbsStgVoList = wbsStgVoList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WbsStgListVo [");
        sb.append("wbsStgVoList").append("=").append(wbsStgVoList);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; wbsStgVoList != null && i < wbsStgVoList.size() ; i++ ) {
            com.demo.proworks.wbs.vo.WbsStgVo vo = (com.demo.proworks.wbs.vo.WbsStgVo)wbsStgVoList.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; wbsStgVoList != null && i < wbsStgVoList.size() ; i++ ) {
            com.demo.proworks.wbs.vo.WbsStgVo vo = (com.demo.proworks.wbs.vo.WbsStgVo)wbsStgVoList.get(i);
            vo._xStreamDec();	 
        }
    }


}
