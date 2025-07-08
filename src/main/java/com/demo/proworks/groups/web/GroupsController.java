package com.demo.proworks.groups.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.groups.service.GroupsService;
import com.demo.proworks.groups.vo.GroupsVo;
import com.demo.proworks.groups.vo.GroupsListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 그룹정보 관련 처리를 담당하는 컨트롤러
 * @description : 그룹정보 관련 처리를 담당하는 컨트롤러
 * @author      : Inswave
 * @since       : 2025/07/05
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/05			 Inswave	 		최초 생성
 * 
 */
@Controller
public class GroupsController {
	
    /** GroupsService */
    @Resource(name = "groupsServiceImpl")
    private GroupsService groupsService;
	
    
    /**
     * 그룹정보 목록을 조회합니다.
     *
     * @param  groupsVo 그룹정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="GROUPS0001List")
    @RequestMapping(value="GROUPS0001List")    
    @ElDescription(sub="그룹정보 목록조회",desc="페이징을 처리하여 그룹정보 목록 조회를 한다.")               
    public GroupsListVo selectListGroups(GroupsVo groupsVo) throws Exception {    	   	

        List<GroupsVo> groupsList = groupsService.selectListGroups(groupsVo);                  
        long totCnt = groupsService.selectListCountGroups(groupsVo);
	
		GroupsListVo retGroupsList = new GroupsListVo();
		retGroupsList.setGroupsVoList(groupsList); 
		retGroupsList.setTotalCount(totCnt);
		retGroupsList.setPageSize(groupsVo.getPageSize());
		retGroupsList.setPageIndex(groupsVo.getPageIndex());

        return retGroupsList;            
    }  
        
    /**
     * 그룹정보을 단건 조회 처리 한다.
     *
     * @param  groupsVo 그룹정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "GROUPS0001UpdView")    
    @RequestMapping(value="GROUPS0001UpdView") 
    @ElDescription(sub = "그룹정보 갱신 폼을 위한 조회", desc = "그룹정보 갱신 폼을 위한 조회를 한다.")    
    public GroupsVo selectGroups(GroupsVo groupsVo) throws Exception {
    	GroupsVo selectGroupsVo = groupsService.selectGroups(groupsVo);    	    
		
        return selectGroupsVo;
    } 
 
    /**
     * 그룹정보를 등록 처리 한다.
     *
     * @param  groupsVo 그룹정보
     * @throws Exception
     */
    @ElService(key="GROUPS0001Ins")    
    @RequestMapping(value="GROUPS0001Ins")
    @ElDescription(sub="그룹정보 등록처리",desc="그룹정보를 등록 처리 한다.")
    public void insertGroups(GroupsVo groupsVo) throws Exception {    	 
    	groupsService.insertGroups(groupsVo);   
    }
       
    /**
     * 그룹정보를 갱신 처리 한다.
     *
     * @param  groupsVo 그룹정보
     * @throws Exception
     */
    @ElService(key="GROUPS0001Upd")    
    @RequestMapping(value="GROUPS0001Upd")    
    @ElValidator(errUrl="/groups/groupsRegister", errContinue=true)
    @ElDescription(sub="그룹정보 갱신처리",desc="그룹정보를 갱신 처리 한다.")    
    public void updateGroups(GroupsVo groupsVo) throws Exception {  
 
    	groupsService.updateGroups(groupsVo);                                            
    }

    /**
     * 그룹정보를 삭제 처리한다.
     *
     * @param  groupsVo 그룹정보    
     * @throws Exception
     */
    @ElService(key = "GROUPS0001Del")    
    @RequestMapping(value="GROUPS0001Del")
    @ElDescription(sub = "그룹정보 삭제처리", desc = "그룹정보를 삭제 처리한다.")    
    public void deleteGroups(GroupsVo groupsVo) throws Exception {
        groupsService.deleteGroups(groupsVo);
    }
    
    
    	/**
     * 그룹정보를 통합 저장 처리한다.
     *
     * @param  groupsVo 그룹정보    
     * @throws Exception
     */
    @ElService(key = "GROUPS0001Save")    
    @RequestMapping(value = "GROUPS0001Save")
    @ElDescription(sub = "그룹정보 저장 처리", desc = "그룹정보를 저장 처리한다.")    
    public void saveGroups(GroupsListVo groupsVoList) throws Exception {
            System.out.println("modified task : " + groupsVoList.getGroupsVoList().toString());
    	   
    	   int cnt = groupsVoList.getGroupsVoList().size();
    	   
    	   for(int i=0; i<cnt; i++){
    		   String rowStatus = groupsVoList.getGroupsVoList().get(i).getRowStatus();
    		   if(rowStatus.equals("C")) groupsService.insertGroups(groupsVoList.getGroupsVoList().get(i));
    		   else if(rowStatus.equals("D")) groupsService.deleteGroups(groupsVoList.getGroupsVoList().get(i));
    		   else if(rowStatus.equals("U")) groupsService.updateGroups(groupsVoList.getGroupsVoList().get(i));
    	   }
    }
    
    
   
}
