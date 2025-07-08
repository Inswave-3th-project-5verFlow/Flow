package com.demo.proworks.users.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "사용자정보")
public class UsersListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "사용자정보List", physicalName = "usersVoList", type = "com.demo.proworks.users.UsersVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.users.vo.UsersVo> usersVoList;

    public java.util.List<com.demo.proworks.users.vo.UsersVo> getUsersVoList(){
        return usersVoList;
    }

    public void setUsersVoList(java.util.List<com.demo.proworks.users.vo.UsersVo> usersVoList){
        this.usersVoList = usersVoList;
    }

    @Override
    public String toString() {
        return "UsersListVo [usersVoList=" + usersVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
