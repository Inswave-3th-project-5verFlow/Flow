package com.demo.proworks.att.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "데모 결과")
public class DemoResultVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public DemoResultVo(){
    }

    @ElDtoField(logicalName = "성공여부", physicalName = "success", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String success;

    @ElDtoField(logicalName = "데모제목", physicalName = "demoTitle", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String demoTitle;

    @ElDtoField(logicalName = "파일명", physicalName = "fileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fileName;

    @ElDtoField(logicalName = "파일크기MB", physicalName = "fileSizeMB", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fileSizeMB;

    @ElDtoField(logicalName = "기존방식소요시간", physicalName = "traditionalDuration", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String traditionalDuration;

    @ElDtoField(logicalName = "개선방식소요시간", physicalName = "multipartDuration", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String multipartDuration;

    @ElDtoField(logicalName = "기존방식처리량", physicalName = "traditionalThroughput", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String traditionalThroughput;

    @ElDtoField(logicalName = "개선방식처리량", physicalName = "multipartThroughput", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String multipartThroughput;

    @ElDtoField(logicalName = "성능개선율", physicalName = "improvementPercent", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String improvementPercent;

    @ElDtoField(logicalName = "메시지", physicalName = "message", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String message;

    @ElVoField(physicalName = "success")
    public String getSuccess(){
        String ret = this.success;
        return ret;
    }

    @ElVoField(physicalName = "success")
    public void setSuccess(String success){
        this.success = success;
    }

    @ElVoField(physicalName = "demoTitle")
    public String getDemoTitle(){
        String ret = this.demoTitle;
        return ret;
    }

    @ElVoField(physicalName = "demoTitle")
    public void setDemoTitle(String demoTitle){
        this.demoTitle = demoTitle;
    }

    @ElVoField(physicalName = "fileName")
    public String getFileName(){
        String ret = this.fileName;
        return ret;
    }

    @ElVoField(physicalName = "fileName")
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    @ElVoField(physicalName = "fileSizeMB")
    public String getFileSizeMB(){
        String ret = this.fileSizeMB;
        return ret;
    }

    @ElVoField(physicalName = "fileSizeMB")
    public void setFileSizeMB(String fileSizeMB){
        this.fileSizeMB = fileSizeMB;
    }

    @ElVoField(physicalName = "traditionalDuration")
    public String getTraditionalDuration(){
        String ret = this.traditionalDuration;
        return ret;
    }

    @ElVoField(physicalName = "traditionalDuration")
    public void setTraditionalDuration(String traditionalDuration){
        this.traditionalDuration = traditionalDuration;
    }

    @ElVoField(physicalName = "multipartDuration")
    public String getMultipartDuration(){
        String ret = this.multipartDuration;
        return ret;
    }

    @ElVoField(physicalName = "multipartDuration")
    public void setMultipartDuration(String multipartDuration){
        this.multipartDuration = multipartDuration;
    }

    @ElVoField(physicalName = "traditionalThroughput")
    public String getTraditionalThroughput(){
        String ret = this.traditionalThroughput;
        return ret;
    }

    @ElVoField(physicalName = "traditionalThroughput")
    public void setTraditionalThroughput(String traditionalThroughput){
        this.traditionalThroughput = traditionalThroughput;
    }

    @ElVoField(physicalName = "multipartThroughput")
    public String getMultipartThroughput(){
        String ret = this.multipartThroughput;
        return ret;
    }

    @ElVoField(physicalName = "multipartThroughput")
    public void setMultipartThroughput(String multipartThroughput){
        this.multipartThroughput = multipartThroughput;
    }

    @ElVoField(physicalName = "improvementPercent")
    public String getImprovementPercent(){
        String ret = this.improvementPercent;
        return ret;
    }

    @ElVoField(physicalName = "improvementPercent")
    public void setImprovementPercent(String improvementPercent){
        this.improvementPercent = improvementPercent;
    }

    @ElVoField(physicalName = "message")
    public String getMessage(){
        String ret = this.message;
        return ret;
    }

    @ElVoField(physicalName = "message")
    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DemoResultVo [");
        sb.append("success").append("=").append(success).append(",");
        sb.append("demoTitle").append("=").append(demoTitle).append(",");
        sb.append("fileName").append("=").append(fileName).append(",");
        sb.append("fileSizeMB").append("=").append(fileSizeMB).append(",");
        sb.append("traditionalDuration").append("=").append(traditionalDuration).append(",");
        sb.append("multipartDuration").append("=").append(multipartDuration).append(",");
        sb.append("traditionalThroughput").append("=").append(traditionalThroughput).append(",");
        sb.append("multipartThroughput").append("=").append(multipartThroughput).append(",");
        sb.append("improvementPercent").append("=").append(improvementPercent).append(",");
        sb.append("message").append("=").append(message);
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
