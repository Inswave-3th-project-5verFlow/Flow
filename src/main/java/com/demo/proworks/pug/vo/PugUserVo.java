package com.demo.proworks.pug.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "사용자 정보")
public class PugUserVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PugUserVo(){
    }

    @ElDtoField(logicalName = "사용자ID", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "이름", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "이메일", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElDtoField(logicalName = "연락처", physicalName = "phone", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String phone;

    @ElDtoField(logicalName = "직책", physicalName = "position", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String position;

    @ElDtoField(logicalName = "search_이름", physicalName = "scUserName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scUserName;

    @ElDtoField(logicalName = "search_사용자ID", physicalName = "scUserId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scUserId;

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        String ret = this.userId;
        return ret;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        String ret = this.name;
        return ret;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
    }

    @ElVoField(physicalName = "email")
    public String getEmail(){
        String ret = this.email;
        return ret;
    }

    @ElVoField(physicalName = "email")
    public void setEmail(String email){
        this.email = email;
    }

    @ElVoField(physicalName = "phone")
    public String getPhone(){
        String ret = this.phone;
        return ret;
    }

    @ElVoField(physicalName = "phone")
    public void setPhone(String phone){
        this.phone = phone;
    }

    @ElVoField(physicalName = "position")
    public String getPosition(){
        String ret = this.position;
        return ret;
    }

    @ElVoField(physicalName = "position")
    public void setPosition(String position){
        this.position = position;
    }

    @ElVoField(physicalName = "scUserName")
    public String getScUserName(){
        String ret = this.scUserName;
        return ret;
    }

    @ElVoField(physicalName = "scUserName")
    public void setScUserName(String scUserName){
        this.scUserName = scUserName;
    }

    @ElVoField(physicalName = "scUserId")
    public String getScUserId(){
        String ret = this.scUserId;
        return ret;
    }

    @ElVoField(physicalName = "scUserId")
    public void setScUserId(String scUserId){
        this.scUserId = scUserId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PugUserVo [");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("email").append("=").append(email).append(",");
        sb.append("phone").append("=").append(phone).append(",");
        sb.append("position").append("=").append(position).append(",");
        sb.append("scUserName").append("=").append(scUserName).append(",");
        sb.append("scUserId").append("=").append(scUserId);
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
