package com.demo.proworks.unit.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "단위테스트 케이스 목록")
public class UnitTestListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UnitTestListVo(){
    }

    @ElDtoField(logicalName = "단위테스트 관리", physicalName = "unitTestList", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.unit.vo.UnitTestVo> unitTestList;

    @ElVoField(physicalName = "unitTestList")
    public java.util.List<com.demo.proworks.unit.vo.UnitTestVo> getUnitTestList(){
        return unitTestList;
    }

    @ElVoField(physicalName = "unitTestList")
    public void setUnitTestList(java.util.List<com.demo.proworks.unit.vo.UnitTestVo> unitTestList){
        this.unitTestList = unitTestList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UnitTestListVo [");
        sb.append("unitTestList").append("=").append(unitTestList);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; unitTestList != null && i < unitTestList.size() ; i++ ) {
            com.demo.proworks.unit.vo.UnitTestVo vo = (com.demo.proworks.unit.vo.UnitTestVo)unitTestList.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; unitTestList != null && i < unitTestList.size() ; i++ ) {
            com.demo.proworks.unit.vo.UnitTestVo vo = (com.demo.proworks.unit.vo.UnitTestVo)unitTestList.get(i);
            vo._xStreamDec();	 
        }
    }


}
