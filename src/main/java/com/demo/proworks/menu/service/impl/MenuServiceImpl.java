package com.demo.proworks.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.menu.service.MenuService;
import com.demo.proworks.menu.vo.MenuListVo;
import com.demo.proworks.menu.vo.MenuVo;
import com.demo.proworks.menu.dao.MenuDAO;

@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService {

    @Resource(name="menuDAO")
    private MenuDAO menuDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

	public List<MenuVo> selectListMenu(MenuVo menuVo) throws Exception {
		List<MenuVo> list = menuDAO.selectListMenu(menuVo);	
		return list;
	}

	public long selectListCountMenu(MenuVo menuVo) throws Exception {
		return menuDAO.selectListCountMenu(menuVo);
	}

	public MenuVo selectMenu(MenuVo menuVo) throws Exception {
		MenuVo resultVO = menuDAO.selectMenu(menuVo);			
        return resultVO;
	}

	public int insertMenu(MenuVo menuVo) throws Exception {
		return menuDAO.insertMenu(menuVo);	
	}
	
	public int updateMenu(MenuVo menuVo) throws Exception {				
		return menuDAO.updateMenu(menuVo);	   		
	}

	public int deleteMenu(MenuVo menuVo) throws Exception {
		return menuDAO.deleteMenu(menuVo);
	}
	
	public List<MenuVo> selectGroupMenuList(MenuVo menuVo) throws Exception {
		List<MenuVo> list = menuDAO.selectGroupMenuList(menuVo);	
		return list;
	}
	
	public int saveGroupMenu(MenuListVo menuListVo) throws Exception {
		int result = 0;
		
		List<MenuVo> menuVoList = menuListVo.getMenuVoList();
		
		if (menuVoList != null && menuVoList.size() > 0) {
			// 첫 번째 데이터에서 그룹ID 추출
			String grpId = menuVoList.get(0).getGrpId();
			
			// 기존 그룹 메뉴 권한 삭제
			MenuVo deleteVo = new MenuVo();
			deleteVo.setGrpId(grpId);
			menuDAO.deleteGroupMenu(deleteVo);
			
			// 새로운 그룹 메뉴 권한 등록
			for (MenuVo menuVo : menuVoList) {
				result += menuDAO.insertGroupMenu(menuVo);
			}
		}
		
		return result;
	}
}