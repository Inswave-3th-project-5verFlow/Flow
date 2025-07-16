package com.demo.proworks.main.web;

import org.springframework.stereotype.Controller;

import com.demo.proworks.main.service.MainService;
import com.demo.proworks.main.vo.MenuListVo;
import com.demo.proworks.main.vo.MenuVo;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.annotation.ElService;

@Controller
public class MainController {

	@Resource(name = "mainServiceImpl")
	private MainService mainService;

	/**
	 * 메뉴 목록을 조회합니다.
	 *
	 * @param menuVo 메뉴 정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "MenuList")
	@RequestMapping(value = "MenuList")
	@ElDescription(sub = "메뉴 정보 목록조회", desc = "그룹 ID를 통해 메뉴 정보 목록을 조회한다")
	public MenuListVo selectListMenu(MenuVo menuVo) throws Exception {

		List<MenuVo> list = mainService.selectListMenu(menuVo);
		MenuListVo retPjtList = new MenuListVo();
		retPjtList.setMenuVo(list);
		return retPjtList;
	}

}
