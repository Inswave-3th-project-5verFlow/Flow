package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "차트 데이터")
public class ChartVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ChartVo() {
    }

    @ElDtoField(logicalName = "라벨", physicalName = "label", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String label;

    @ElDtoField(logicalName = "값", physicalName = "value", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long value;

    @ElVoField(physicalName = "label")
    public String getLabel() {
        return label;
    }

    @ElVoField(physicalName = "label")
    public void setLabel(String label) {
        this.label = label;
    }

    @ElVoField(physicalName = "value")
    public long getValue() {
        return value;
    }

    @ElVoField(physicalName = "value")
    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChartVo [");
        sb.append("label").append("=").append(label).append(",");
        sb.append("value").append("=").append(value);
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
