package com.demo.proworks.task.design.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "설계 업무 정보")
public class DesignListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "설계 업무 정보List", physicalName = "designVoList", type = "com.demo.proworks.task.design.DesignVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.task.design.vo.DesignVo> designVoList;

    public java.util.List<com.demo.proworks.task.design.vo.DesignVo> getDesignVoList(){
        return designVoList;
    }

    public void setDesignVoList(java.util.List<com.demo.proworks.task.design.vo.DesignVo> designVoList){
        this.designVoList = designVoList;
    }

    @Override
    public String toString() {
        return "DesignListVo [designVoList=" + designVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
