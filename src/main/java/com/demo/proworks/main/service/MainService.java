package com.demo.proworks.main.service;

import java.util.List;

import com.demo.proworks.main.vo.MenuVo;

/**
 * @subject : 메인 관련 처리를 담당하는 인터페이스
 * @description : 메인 관련 처리를 담당하는 인터페이스
 * @author : 김성민
 * @since : 2025/07/15
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/15 김성민 최초 생성
 * 
 */
public interface MainService {

	/**
	 * 그룹 ID로 메뉴 목록을 조회한다.
	 *
	 * @param menuVo 메뉴 정보 MenuVo
	 * @return List<MenuVo> 메뉴 목록
	 * @throws Exception
	 */
	public List<MenuVo> selectListMenu(MenuVo menuVo) throws Exception;

}
