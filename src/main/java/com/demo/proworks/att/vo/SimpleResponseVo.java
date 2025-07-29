package com.demo.proworks.att.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "간단한 응답")
public class SimpleResponseVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SimpleResponseVo(){
    }

    @ElDtoField(logicalName = "성공여부", physicalName = "success", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String success;

    @ElDtoField(logicalName = "메시지", physicalName = "message", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String message;

    @ElDtoField(logicalName = "데이터", physicalName = "data", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String data;

    @ElDtoField(logicalName = "업로드방식", physicalName = "uploadMethod", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String uploadMethod;

    @ElDtoField(logicalName = "파일크기MB", physicalName = "fileSizeMB", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fileSizeMB;

    @ElVoField(physicalName = "success")
    public String getSuccess(){
        String ret = this.success;
        return ret;
    }

    @ElVoField(physicalName = "success")
    public void setSuccess(String success){
        this.success = success;
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

    @ElVoField(physicalName = "data")
    public String getData(){
        String ret = this.data;
        return ret;
    }

    @ElVoField(physicalName = "data")
    public void setData(String data){
        this.data = data;
    }

    @ElVoField(physicalName = "uploadMethod")
    public String getUploadMethod(){
        String ret = this.uploadMethod;
        return ret;
    }

    @ElVoField(physicalName = "uploadMethod")
    public void setUploadMethod(String uploadMethod){
        this.uploadMethod = uploadMethod;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleResponseVo [");
        sb.append("success").append("=").append(success).append(",");
        sb.append("message").append("=").append(message).append(",");
        sb.append("data").append("=").append(data).append(",");
        sb.append("uploadMethod").append("=").append(uploadMethod).append(",");
        sb.append("fileSizeMB").append("=").append(fileSizeMB);
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
