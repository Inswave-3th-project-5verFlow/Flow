package com.demo.proworks.pjt.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "프로젝트 기본 정보")
public class PjtListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PjtListVo(){
    }

    @ElDtoField(logicalName = "프로젝트 기본 정보List", physicalName = "pjtListVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.pjt.vo.PjtVo> pjtListVo;

    @ElVoField(physicalName = "pjtListVo")
    public java.util.List<com.demo.proworks.pjt.vo.PjtVo> getPjtListVo(){
        return pjtListVo;
    }

    @ElVoField(physicalName = "pjtListVo")
    public void setPjtListVo(java.util.List<com.demo.proworks.pjt.vo.PjtVo> pjtListVo){
        this.pjtListVo = pjtListVo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PjtListVo [");
        sb.append("pjtListVo").append("=").append(pjtListVo);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; pjtListVo != null && i < pjtListVo.size() ; i++ ) {
            com.demo.proworks.pjt.vo.PjtVo vo = (com.demo.proworks.pjt.vo.PjtVo)pjtListVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; pjtListVo != null && i < pjtListVo.size() ; i++ ) {
            com.demo.proworks.pjt.vo.PjtVo vo = (com.demo.proworks.pjt.vo.PjtVo)pjtListVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
