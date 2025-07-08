package com.demo.proworks.iss.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "이슈리스크관리")
public class IssListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "이슈리스크관리List", physicalName = "issVoList", type = "com.demo.proworks.iss.IssVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.iss.vo.IssVo> issVoList;

    public java.util.List<com.demo.proworks.iss.vo.IssVo> getIssVoList(){
        return issVoList;
    }

    public void setIssVoList(java.util.List<com.demo.proworks.iss.vo.IssVo> issVoList){
        this.issVoList = issVoList;
    }

    @Override
    public String toString() {
        return "IssListVo [issVoList=" + issVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
