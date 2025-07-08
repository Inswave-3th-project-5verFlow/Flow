package com.demo.proworks.pug.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "사용자 정보")
public class PugUserListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PugUserListVo(){
    }

    @ElDtoField(logicalName = "사용자 정보", physicalName = "pugUserVoList", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.pug.vo.PugUserVo> pugUserVoList;

    @ElVoField(physicalName = "pugUserVoList")
    public java.util.List<com.demo.proworks.pug.vo.PugUserVo> getPugUserVoList(){
        return pugUserVoList;
    }

    @ElVoField(physicalName = "pugUserVoList")
    public void setPugUserVoList(java.util.List<com.demo.proworks.pug.vo.PugUserVo> pugUserVoList){
        this.pugUserVoList = pugUserVoList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PugUserListVo [");
        sb.append("pugUserVoList").append("=").append(pugUserVoList);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; pugUserVoList != null && i < pugUserVoList.size() ; i++ ) {
            com.demo.proworks.pug.vo.PugUserVo vo = (com.demo.proworks.pug.vo.PugUserVo)pugUserVoList.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; pugUserVoList != null && i < pugUserVoList.size() ; i++ ) {
            com.demo.proworks.pug.vo.PugUserVo vo = (com.demo.proworks.pug.vo.PugUserVo)pugUserVoList.get(i);
            vo._xStreamDec();	 
        }
    }


}
