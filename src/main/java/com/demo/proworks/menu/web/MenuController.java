package com.demo.proworks.menu.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.menu.service.MenuService;
import com.demo.proworks.menu.vo.MenuVo;
import com.demo.proworks.menu.vo.MenuListVo;

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
public class MenuController {
	
    /** MenuService */
    @Resource(name = "menuServiceImpl")
    private MenuService menuService;
	
    
    /**
     * 메뉴-그룹 정보 목록을 조회합니다.
     *
     * @param  menuVo 메뉴-그룹 정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="MENUList")
    @RequestMapping(value="MENUList")    
    @ElDescription(sub="메뉴-그룹 정보 목록조회",desc="페이징을 처리하여 메뉴-그룹 정보 목록 조회를 한다.")               
    public MenuListVo selectListMenu(MenuVo menuVo) throws Exception {    	   	

        List<MenuVo> menuList = menuService.selectListMenu(menuVo);                  
        long totCnt = menuService.selectListCountMenu(menuVo);
	
		MenuListVo retMenuList = new MenuListVo();
		retMenuList.setMenuVoList(menuList); 
		retMenuList.setTotalCount(totCnt);
		retMenuList.setPageSize(menuVo.getPageSize());
		retMenuList.setPageIndex(menuVo.getPageIndex());

        return retMenuList;            
    }  
        
    /**
     * 메뉴-그룹 정보을 단건 조회 처리 한다.
     *
     * @param  menuVo 메뉴-그룹 정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "MENUUpdView")    
    @RequestMapping(value="MENUUpdView") 
    @ElDescription(sub = "메뉴-그룹 정보 갱신 폼을 위한 조회", desc = "메뉴-그룹 정보 갱신 폼을 위한 조회를 한다.")    
    public MenuVo selectMenu(MenuVo menuVo) throws Exception {
    	MenuVo selectMenuVo = menuService.selectMenu(menuVo);    	    
		
        return selectMenuVo;
    } 
 
    /**
     * 메뉴-그룹 정보를 등록 처리 한다.
     *
     * @param  menuVo 메뉴-그룹 정보
     * @throws Exception
     */
    @ElService(key="MENUIns")    
    @RequestMapping(value="MENUIns")
    @ElDescription(sub="메뉴-그룹 정보 등록처리",desc="메뉴-그룹 정보를 등록 처리 한다.")
    public void insertMenu(MenuVo menuVo) throws Exception {    	 
    	menuService.insertMenu(menuVo);   
    }
       
    /**
     * 메뉴-그룹 정보를 갱신 처리 한다.
     *
     * @param  menuVo 메뉴-그룹 정보
     * @throws Exception
     */
    @ElService(key="MENUUpd")    
    @RequestMapping(value="MENUUpd")    
    @ElValidator(errUrl="/menu/menuRegister", errContinue=true)
    @ElDescription(sub="메뉴-그룹 정보 갱신처리",desc="메뉴-그룹 정보를 갱신 처리 한다.")    
    public void updateMenu(MenuVo menuVo) throws Exception {  
 
    	menuService.updateMenu(menuVo);                                            
    }

    /**
     * 메뉴-그룹 정보를 삭제 처리한다.
     *
     * @param  menuVo 메뉴-그룹 정보    
     * @throws Exception
     */
    @ElService(key = "MENUDel")    
    @RequestMapping(value="MENUDel")
    @ElDescription(sub = "메뉴-그룹 정보 삭제처리", desc = "메뉴-그룹 정보를 삭제 처리한다.")    
    public void deleteMenu(MenuVo menuVo) throws Exception {
        menuService.deleteMenu(menuVo);
    }
    
    /**
     * 그룹별 메뉴 권한 목록을 조회합니다.
     *
     * @param  menuVo 메뉴 정보 (grpId 포함)
     * @return 그룹메뉴 권한 조회 결과
     * @throws Exception
     */
    @ElService(key="GroupMenuList")
    @RequestMapping(value="GroupMenuList")    
    @ElDescription(sub="그룹 메뉴 권한 목록조회",desc="특정 그룹에 할당된 메뉴 권한 목록을 조회한다.")               
    public MenuListVo selectGroupMenuList(MenuVo menuVo) throws Exception {    	   	

        List<MenuVo> groupMenuList = menuService.selectGroupMenuList(menuVo);                  
	
		MenuListVo retGroupMenuList = new MenuListVo();
		retGroupMenuList.setMenuVoList(groupMenuList); 
		retGroupMenuList.setTotalCount(groupMenuList.size());

        return retGroupMenuList;            
    }
    
    /**
     * 그룹 메뉴 권한을 저장 처리한다.
     *
     * @param  menuListVo 그룹메뉴 권한 정보    
     * @throws Exception
     */
    @ElService(key = "GroupMenuSave")    
    @RequestMapping(value="GroupMenuSave")
    @ElDescription(sub = "그룹 메뉴 권한 저장처리", desc = "그룹 메뉴 권한을 저장 처리한다.")    
    public void saveGroupMenu(MenuListVo menuListVo) throws Exception {
        menuService.saveGroupMenu(menuListVo);
    }
   
}
