package com.demo.proworks.groups.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "그룹정보")
public class GroupsListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "그룹정보List", physicalName = "groupsVoList", type = "com.demo.proworks.groups.GroupsVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.groups.vo.GroupsVo> groupsVoList;

    public java.util.List<com.demo.proworks.groups.vo.GroupsVo> getGroupsVoList(){
        return groupsVoList;
    }

    public void setGroupsVoList(java.util.List<com.demo.proworks.groups.vo.GroupsVo> groupsVoList){
        this.groupsVoList = groupsVoList;
    }

    @Override
    public String toString() {
        return "GroupsListVo [groupsVoList=" + groupsVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
