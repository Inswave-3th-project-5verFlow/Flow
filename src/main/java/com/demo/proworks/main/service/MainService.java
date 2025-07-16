package com.demo.proworks.main.service;

import java.util.List;

import com.demo.proworks.main.vo.MenuVo;

public interface MainService {

	public List<MenuVo> selectListMenu(MenuVo menuVo) throws Exception;

}
