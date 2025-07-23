package com.demo.proworks.menu.service;

import java.util.List;

import com.demo.proworks.menu.vo.MenuGroupsVo;

/**  
 * @subject     : 메뉴-그룹 정보 관련 처리를 담당하는 인터페이스
 * @description : 메뉴-그룹 정보 관련 처리를 담당하는 인터페이스
 * @author      : 백승호
 * @since       : 2025/07/23
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/23			 백승호	 		최초 생성
 * 
 */
public interface MenuGroupsService {
	
    /**
     * 메뉴-그룹 정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 메뉴-그룹 정보 목록 List<MenuGroupsVo>
     * @throws Exception
     */
	public List<MenuGroupsVo> selectListMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception;
	
    /**
     * 조회한 메뉴-그룹 정보 전체 카운트
     * 
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 메뉴-그룹 정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception;
	
    /**
     * 메뉴-그룹 정보를 상세 조회한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public MenuGroupsVo selectMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception;
		
    /**
     * 메뉴-그룹 정보를 등록 처리 한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 번호
     * @throws Exception
     */
	public int insertMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception;
	
    /**
     * 메뉴-그룹 정보를 갱신 처리 한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 번호
     * @throws Exception
     */
	public int updateMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception;
	
    /**
     * 메뉴-그룹 정보를 삭제 처리 한다.
     *
     * @param  menuGroupsVo 메뉴-그룹 정보 MenuGroupsVo
     * @return 번호
     * @throws Exception
     */
	public int deleteMenuGroups(MenuGroupsVo menuGroupsVo) throws Exception;
	
}
