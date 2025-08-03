package com.demo.proworks.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.main.dao.MainDAO;
import com.demo.proworks.main.service.MainService;
import com.demo.proworks.main.vo.MenuVo;

/**
 * @subject : 메인 관련 처리를 담당하는 ServiceImpl
 * @description : 메인 관련 처리를 담당하는 ServiceImpl
 * @author : 김성민
 * @since : 2025/07/15
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/15 김성민 최초 생성
 * 
 */
@Service("mainServiceImpl")
public class MainServiceImpl implements MainService {

	/** MainDAO */
	@Resource(name = "mainDAO")
	private MainDAO mainDAO;

	/** MessageSource */
	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * 그룹 ID로 메뉴 목록을 조회한다.
	 */
	@Override
	public List<MenuVo> selectListMenu(MenuVo menuVo) throws Exception {
		List<MenuVo> list = mainDAO.selectListMenu(menuVo);
		return list; 
	}

}
