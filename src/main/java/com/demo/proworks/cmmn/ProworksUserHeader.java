package com.demo.proworks.cmmn;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.inswave.elfw.log.AppLog;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "Y", delimeterYn = "", logicalName = "헤더VO")
public class ProworksUserHeader extends com.inswave.elfw.core.UserHeader {
    private static final long serialVersionUID = 1L;

    private int _offset;

    public ProworksUserHeader(){
        this._offset = 0;
    }

    public ProworksUserHeader(int iOffset){
        this._offset = iOffset;
    }

    @ElDtoField(logicalName = "전문길이", physicalName = "fldLen", type = "int", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 10, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int fldLen;

    @ElDtoField(logicalName = "서비스ID", physicalName = "svcId", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 20, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String svcId;

    @ElDtoField(logicalName = "전문입력ID", physicalName = "inInfId", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 30, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String inInfId;

    @ElDtoField(logicalName = "전문출력ID", physicalName = "outInfId", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 30, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String outInfId;

    @ElDtoField(logicalName = "성공실패여부", physicalName = "sucYn", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 1, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String sucYn;

    @ElDtoField(logicalName = "에러코드", physicalName = "errorCode", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 30, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String errorCode;

    @ElDtoField(logicalName = "에러메시지", physicalName = "errMag", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 100, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String errMag;

    @ElDtoField(logicalName = "테스트", physicalName = "location", type = "String", typeKind = "", fldYn = "Yes", delimeterYn = "Yes", cryptoGbn = "", cryptoKind = "", length = 10, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String location;

    @ElDtoField(logicalName = "계정ID", physicalName = "accountId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accountId;

    @ElDtoField(logicalName = "계정비밀번호", physicalName = "accountPwd", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accountPwd;

    @ElDtoField(logicalName = "유저ID", physicalName = "usrId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String usrId;

    @ElDtoField(logicalName = "유저이름", physicalName = "userName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userName;

    @ElDtoField(logicalName = "관리자여부", physicalName = "isAdmin", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isAdmin;

    @ElDtoField(logicalName = "생성권한", physicalName = "isCreate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isCreate;

    @ElVoField(physicalName = "fldLen")
    public int getFldLen(){
        return fldLen;
    }

    @ElVoField(physicalName = "fldLen")
    public void setFldLen(int fldLen){
        this.fldLen = fldLen;
    }

    @ElVoField(physicalName = "svcId")
    public String getSvcId(){
        String ret = this.svcId;
        return ret;
    }

    @ElVoField(physicalName = "svcId")
    public void setSvcId(String svcId){
        this.svcId = svcId;
    }

    @ElVoField(physicalName = "inInfId")
    public String getInInfId(){
        String ret = this.inInfId;
        return ret;
    }

    @ElVoField(physicalName = "inInfId")
    public void setInInfId(String inInfId){
        this.inInfId = inInfId;
    }

    @ElVoField(physicalName = "outInfId")
    public String getOutInfId(){
        String ret = this.outInfId;
        return ret;
    }

    @ElVoField(physicalName = "outInfId")
    public void setOutInfId(String outInfId){
        this.outInfId = outInfId;
    }

    @ElVoField(physicalName = "sucYn")
    public String getSucYn(){
        String ret = this.sucYn;
        return ret;
    }

    @ElVoField(physicalName = "sucYn")
    public void setSucYn(String sucYn){
        this.sucYn = sucYn;
    }

    @ElVoField(physicalName = "errorCode")
    public String getErrorCode(){
        String ret = this.errorCode;
        return ret;
    }

    @ElVoField(physicalName = "errorCode")
    public void setErrorCode(String errorCode){
        this.errorCode = errorCode;
    }

    @ElVoField(physicalName = "errMag")
    public String getErrMag(){
        String ret = this.errMag;
        return ret;
    }

    @ElVoField(physicalName = "errMag")
    public void setErrMag(String errMag){
        this.errMag = errMag;
    }

    @ElVoField(physicalName = "location")
    public String getLocation(){
        String ret = this.location;
        return ret;
    }

    @ElVoField(physicalName = "location")
    public void setLocation(String location){
        this.location = location;
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

    @ElVoField(physicalName = "usrId")
    public String getUsrId(){
        String ret = this.usrId;
        return ret;
    }

    @ElVoField(physicalName = "usrId")
    public void setUsrId(String usrId){
        this.usrId = usrId;
    }

    @ElVoField(physicalName = "userName")
    public String getUserName(){
        String ret = this.userName;
        return ret;
    }

    @ElVoField(physicalName = "userName")
    public void setUserName(String userName){
        this.userName = userName;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProworksUserHeader [");
        sb.append("fldLen").append("=").append(fldLen).append(",");
        sb.append("svcId").append("=").append(svcId).append(",");
        sb.append("inInfId").append("=").append(inInfId).append(",");
        sb.append("outInfId").append("=").append(outInfId).append(",");
        sb.append("sucYn").append("=").append(sucYn).append(",");
        sb.append("errorCode").append("=").append(errorCode).append(",");
        sb.append("errMag").append("=").append(errMag).append(",");
        sb.append("location").append("=").append(location).append(",");
        sb.append("accountId").append("=").append(accountId).append(",");
        sb.append("accountPwd").append("=").append(accountPwd).append(",");
        sb.append("usrId").append("=").append(usrId).append(",");
        sb.append("userName").append("=").append(userName).append(",");
        sb.append("isAdmin").append("=").append(isAdmin).append(",");
        sb.append("isCreate").append("=").append(isCreate);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return true;
    }

    public byte[] marshalFld() throws IOException{
        return marshalFld( com.inswave.elfw.ElConfig.getFldEncode() ); 
    }

	public byte[] marshalFld(String encode) throws IOException{
    	ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(bout);
            out.write( com.inswave.elfw.util.TypeConversionUtil.intToBytes(this.fldLen , 10) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.svcId , 20, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.inInfId , 30, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.outInfId , 30, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.sucYn , 1, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.errorCode , 30, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.errMag , 100, encode ) );
            out.write( com.inswave.elfw.util.TypeConversionUtil.strToSpBytes(this.location , 10, encode ) );
        } catch (IOException e) {
                AppLog.error("marshalFld Error:["+ toString()+"]", e);
                throw e;
        } finally {
            try	{
                if (out != null) out.close();
           } catch (IOException ie) {
                AppLog.error("marshalFld out close Error", ie);
           }
            try	{
                if (bout != null) bout.close();
           } catch (IOException ie) {
                AppLog.error("marshalFld bout close Error", ie);
           }
        }
        return bout.toByteArray();
    }

    public void unMarshalFld( byte[] bytes ) throws ElException{
        unMarshalFld( bytes, com.inswave.elfw.ElConfig.getFldEncode() ); 
    }

    public void unMarshalFld( byte[] bytes , String encode) throws ElException{
        try{ 
             this.fldLen = com.inswave.elfw.util.TypeConversionUtil.bytesToInt( bytes, _offset, 10, encode );
             _offset += 10;
            this.svcId = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 20, encode );
             _offset += 20;
            this.inInfId = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 30, encode );
             _offset += 30;
            this.outInfId = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 30, encode );
             _offset += 30;
            this.sucYn = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 1, encode );
             _offset += 1;
            this.errorCode = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 30, encode );
             _offset += 30;
            this.errMag = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 100, encode );
             _offset += 100;
            this.location = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, _offset, 10, encode );
             _offset += 10;
        }catch(ElException e) { 
            String errorLine = com.inswave.elfw.util.TypeConversionUtil.getTrimmedString( bytes, 0, bytes.length, encode );
            AppLog.error("unMarshalFld Error:["+ errorLine+"]", e);
            throw e;
        } 
    }

    public int getOffset(){
        return _offset;
    }

    public int getFixedTotalLength(){
        return 231;
    }

    @Override
    public void _xStreamEnc() {
    }


    @Override
    public void _xStreamDec() {
    }


}
