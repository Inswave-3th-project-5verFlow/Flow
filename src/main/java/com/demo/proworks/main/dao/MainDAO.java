package com.demo.proworks.main.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO;
import com.demo.proworks.main.vo.MenuVo;
import com.inswave.elfw.exception.ElException;


@Repository("mainDAO")
public class MainDAO extends ProworksDefaultAbstractDAO {

	/**
	 * 그룹 ID로 메뉴 목록을 조회한다.
	 * 
	 * @param MenuVo 메뉴 정보
	 * @return List<MenuVo> 메뉴 리스트
	 * @throws ElException
	 */
	public List<MenuVo> selectListMenu(MenuVo vo) throws ElException {
		return (List<MenuVo>) list("com.demo.proworks.main.selectListMenu", vo);
	}

}
