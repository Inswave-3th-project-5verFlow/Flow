package com.demo.proworks.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.menu.service.MenuGroupsService;
import com.demo.proworks.menu.vo.MenuGroupsVo;
import com.demo.proworks.menu.dao.MenuGroupsDAO;

/**  
 * @subject     : 메뉴-그룹 정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 메뉴-그룹 정보 관련 처리를 담당하는 ServiceImpl
 * @author      : 백승호
 * @since       : 2025/07/23
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/23			 백승호	 		최초 생성
 * 
 */
@Service("menuGroupsServiceImpl")
public class MenuGroupsServiceImpl implements MenuGroupsService {

    @Resource(name="menuGroupsDAO")
    private MenuGroupsDAO menuGroupsDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 메뉴-그룹 정보 목록을 조회합니다.
     *
     * @process
     * 1. 메뉴-그룹 정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<MenuGroupsVo>을(를) 리턴한다.
     * 
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 메뉴-그룹 정보 목록 List<MenuGroupsVo>
     * @throws Exception
     */
	public List<MenuGroupsVo> selectListMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {
		List<MenuGroupsVo> list = menuGroupsDAO.selectListMenuGroups(menuGroupsVo);	
	
		return list;
	}

    /**
     * 조회한 메뉴-그룹 정보 전체 카운트
     *
     * @process
     * 1. 메뉴-그룹 정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 메뉴-그룹 정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {
		return menuGroupsDAO.selectListCountMenuGroups(menuGroupsVo);
	}

    /**
     * 메뉴-그룹 정보를 상세 조회한다.
     *
     * @process
     * 1. 메뉴-그룹 정보를 상세 조회한다.
     * 2. 결과 MenuGroupsVo을(를) 리턴한다.
     * 
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public MenuGroupsVo selectMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {
		MenuGroupsVo resultVO = menuGroupsDAO.selectMenuGroups(menuGroupsVo);			
        
        return resultVO;
	}

    /**
     * 메뉴-그룹 정보를 등록 처리 한다.
     *
     * @process
     * 1. 메뉴-그룹 정보를 등록 처리 한다.
     * 
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 번호
     * @throws Exception
     */
	public int insertMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {
		return menuGroupsDAO.insertMenuGroups(menuGroupsVo);	
	}
	
    /**
     * 메뉴-그룹 정보를 갱신 처리 한다.
     *
     * @process
     * 1. 메뉴-그룹 정보를 갱신 처리 한다.
     * 
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 번호
     * @throws Exception
     */
	public int updateMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {				
		return menuGroupsDAO.updateMenuGroups(menuGroupsVo);	   		
	}

    /**
     * 메뉴-그룹 정보를 삭제 처리 한다.
     *
     * @process
     * 1. 메뉴-그룹 정보를 삭제 처리 한다.
     * 
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 번호
     * @throws Exception
     */
	public int deleteMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception {
		return menuGroupsDAO.deleteMenuGroups(menuGroupsVo);
	}
	
}
