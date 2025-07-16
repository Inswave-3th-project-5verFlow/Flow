package com.demo.proworks.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.main.dao.MainDAO;
import com.demo.proworks.main.service.MainService;
import com.demo.proworks.main.vo.MenuVo;

@Service("mainServiceImpl")
public class MainServiceImpl implements MainService {

	@Resource(name = "mainDAO")
	private MainDAO mainDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Override
	public List<MenuVo> selectListMenu(MenuVo menuVo) throws Exception {
		List<MenuVo> list = mainDAO.selectListMenu(menuVo);
		return list; 

	}

}
