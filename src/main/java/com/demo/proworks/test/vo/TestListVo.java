package com.demo.proworks.test.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "테스트관리")
public class TestListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "테스트관리List", physicalName = "testVoList", type = "com.demo.proworks.test.TestVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.test.vo.TestVo> testVoList;

    public java.util.List<com.demo.proworks.test.vo.TestVo> getTestVoList(){
        return testVoList;
    }

    public void setTestVoList(java.util.List<com.demo.proworks.test.vo.TestVo> testVoList){
        this.testVoList = testVoList;
    }

    @Override
    public String toString() {
        return "TestListVo [testVoList=" + testVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
