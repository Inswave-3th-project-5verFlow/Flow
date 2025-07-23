package com.demo.proworks.menu.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.menu.service.MenuGroupsService;
import com.demo.proworks.menu.vo.MenuGroupsVo;
import com.demo.proworks.menu.vo.MenuGroupsListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 메뉴-그룹 정보 관련 처리를 담당하는 컨트롤러
 * @description : 메뉴-그룹 정보 관련 처리를 담당하는 컨트롤러
 * @author      : 백승호
 * @since       : 2025/07/23
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/23			 백승호	 		최초 생성
 * 
 */
@Controller
public class MenuGroupsController {
	
    /** MenuGroupsService */
    @Resource(name = "menuGroupsServiceImpl")
    private MenuGroupsService menuGroupsService;
	
    
    /**
     * 메뉴-그룹 정보 목록을 조회합니다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="MENUGROUPSList")
    @RequestMapping(value="MENUGROUPSList")    
    @ElDescription(sub="메뉴-그룹 정보 목록조회",desc="페이징을 처리하여 메뉴-그룹 정보 목록 조회를 한다.")               
    public MenuGroupsListVo selectListMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {    	   	

        List<MenuGroupsVo> menuGroupsList = menuGroupsService.selectListMenuGroups(menuGroupsVo);                  
        long totCnt = menuGroupsService.selectListCountMenuGroups(menuGroupsVo);
	
		MenuGroupsListVo retMenuGroupsList = new MenuGroupsListVo();
		retMenuGroupsList.setMenuGroupsVoList(menuGroupsList); 
		retMenuGroupsList.setTotalCount(totCnt);
		retMenuGroupsList.setPageSize(menuGroupsVo.getPageSize());
		retMenuGroupsList.setPageIndex(menuGroupsVo.getPageIndex());

        return retMenuGroupsList;            
    }  
        
    /**
     * 메뉴-그룹 정보을 단건 조회 처리 한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "MENUGROUPSUpdView")    
    @RequestMapping(value="MENUGROUPSUpdView") 
    @ElDescription(sub = "메뉴-그룹 정보 갱신 폼을 위한 조회", desc = "메뉴-그룹 정보 갱신 폼을 위한 조회를 한다.")    
    public MenuGroupsVo selectMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {
    	MenuGroupsVo selectMenuGroupsVo = menuGroupsService.selectMenuGroups(menuGroupsVo);    	    
		
        return selectMenuGroupsVo;
    } 
 
    /**
     * 메뉴-그룹 정보를 등록 처리 한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보
     * @throws Exception
     */
    @ElService(key="MENUGROUPSIns")    
    @RequestMapping(value="MENUGROUPSIns")
    @ElDescription(sub="메뉴-그룹 정보 등록처리",desc="메뉴-그룹 정보를 등록 처리 한다.")
    public void insertMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {    	 
    	menuGroupsService.insertMenuGroups(menuGroupsVo);   
    }
       
    /**
     * 메뉴-그룹 정보를 갱신 처리 한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보
     * @throws Exception
     */
    @ElService(key="MENUGROUPSUpd")    
    @RequestMapping(value="MENUGROUPSUpd")    
    @ElValidator(errUrl="/menuGroups/menuGroupsRegister", errContinue=true)
    @ElDescription(sub="메뉴-그룹 정보 갱신처리",desc="메뉴-그룹 정보를 갱신 처리 한다.")    
    public void updateMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {  
 
    	menuGroupsService.updateMenuGroups(menuGroupsVo);                                            
    }

    /**
     * 메뉴-그룹 정보를 삭제 처리한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보    
     * @throws Exception
     */
    @ElService(key = "MENUGROUPSDel")    
    @RequestMapping(value="MENUGROUPSDel")
    @ElDescription(sub = "메뉴-그룹 정보 삭제처리", desc = "메뉴-그룹 정보를 삭제 처리한다.")    
    public void deleteMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {
        menuGroupsService.deleteMenuGroups(menuGroupsVo);
    }
   
}
