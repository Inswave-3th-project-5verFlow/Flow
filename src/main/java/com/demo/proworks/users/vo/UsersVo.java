package com.demo.proworks.users.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "사용자정보")
public class UsersVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UsersVo(){
    }

    @ElDtoField(logicalName = "user_id", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "name", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "position", physicalName = "position", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String position;

    @ElDtoField(logicalName = "email", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElDtoField(logicalName = "phone", physicalName = "phone", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String phone;

    @ElDtoField(logicalName = "account_id", physicalName = "accountId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accountId;

    @ElDtoField(logicalName = "account_pwd", physicalName = "accountPwd", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accountPwd;

    @ElDtoField(logicalName = "image", physicalName = "image", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String image;

    @ElDtoField(logicalName = "is_deleted", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isDeleted;

    @ElDtoField(logicalName = "is_admin", physicalName = "isAdmin", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isAdmin;

    @ElDtoField(logicalName = "is_create", physicalName = "isCreate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isCreate;

    @ElDtoField(logicalName = "search_name", physicalName = "scName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scName;

    @ElDtoField(logicalName = "pjt_id", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtId;

    @ElDtoField(logicalName = "pjt_name", physicalName = "pjtName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtName;

    @ElDtoField(logicalName = "grp_id", physicalName = "grpId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpId;

    @ElDtoField(logicalName = "grp_name", physicalName = "grpName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String grpName;

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

    @ElVoField(physicalName = "position")
    public String getPosition(){
        String ret = this.position;
        return ret;
    }

    @ElVoField(physicalName = "position")
    public void setPosition(String position){
        this.position = position;
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

    @ElVoField(physicalName = "accountId")
    public String getAccountId(){
        String ret = this.accountId;
        return ret;
    }

    @ElVoField(physicalName = "accountId")
    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    @ElVoField(physicalName = "accountPwd")
    public String getAccountPwd(){
        String ret = this.accountPwd;
        return ret;
    }

    @ElVoField(physicalName = "accountPwd")
    public void setAccountPwd(String accountPwd){
        this.accountPwd = accountPwd;
    }

    @ElVoField(physicalName = "image")
    public String getImage(){
        String ret = this.image;
        return ret;
    }

    @ElVoField(physicalName = "image")
    public void setImage(String image){
        this.image = image;
    }

    @ElVoField(physicalName = "isDeleted")
    public String getIsDeleted(){
        String ret = this.isDeleted;
        return ret;
    }

    @ElVoField(physicalName = "isDeleted")
    public void setIsDeleted(String isDeleted){
        this.isDeleted = isDeleted;
    }

    @ElVoField(physicalName = "isAdmin")
    public String getIsAdmin(){
        String ret = this.isAdmin;
        return ret;
    }

    @ElVoField(physicalName = "isAdmin")
    public void setIsAdmin(String isAdmin){
        this.isAdmin = isAdmin;
    }

    @ElVoField(physicalName = "isCreate")
    public String getIsCreate(){
        String ret = this.isCreate;
        return ret;
    }

    @ElVoField(physicalName = "isCreate")
    public void setIsCreate(String isCreate){
        this.isCreate = isCreate;
    }

    @ElVoField(physicalName = "scName")
    public String getScName(){
        String ret = this.scName;
        return ret;
    }

    @ElVoField(physicalName = "scName")
    public void setScName(String scName){
        this.scName = scName;
    }

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        String ret = this.pjtId;
        return ret;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
    }

    @ElVoField(physicalName = "pjtName")
    public String getPjtName(){
        String ret = this.pjtName;
        return ret;
    }

    @ElVoField(physicalName = "pjtName")
    public void setPjtName(String pjtName){
        this.pjtName = pjtName;
    }

    @ElVoField(physicalName = "grpId")
    public String getGrpId(){
        String ret = this.grpId;
        return ret;
    }

    @ElVoField(physicalName = "grpId")
    public void setGrpId(String grpId){
        this.grpId = grpId;
    }

    @ElVoField(physicalName = "grpName")
    public String getGrpName(){
        String ret = this.grpName;
        return ret;
    }

    @ElVoField(physicalName = "grpName")
    public void setGrpName(String grpName){
        this.grpName = grpName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UsersVo [");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("position").append("=").append(position).append(",");
        sb.append("email").append("=").append(email).append(",");
        sb.append("phone").append("=").append(phone).append(",");
        sb.append("accountId").append("=").append(accountId).append(",");
        sb.append("accountPwd").append("=").append(accountPwd).append(",");
        sb.append("image").append("=").append(image).append(",");
        sb.append("isDeleted").append("=").append(isDeleted).append(",");
        sb.append("isAdmin").append("=").append(isAdmin).append(",");
        sb.append("isCreate").append("=").append(isCreate).append(",");
        sb.append("scName").append("=").append(scName).append(",");
        sb.append("pjtId").append("=").append(pjtId).append(",");
        sb.append("pjtName").append("=").append(pjtName).append(",");
        sb.append("grpId").append("=").append(grpId).append(",");
        sb.append("grpName").append("=").append(grpName);
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
