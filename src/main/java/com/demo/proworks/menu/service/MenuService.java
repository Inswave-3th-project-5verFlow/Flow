package com.demo.proworks.menu.service;

import java.util.List;

import com.demo.proworks.menu.vo.MenuVo;
import com.demo.proworks.menu.vo.MenuListVo;

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
public interface MenuService {
	
    /**
     * 메뉴-그룹 정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  menuVo 메뉴-그룹 정보 MenuVo
     * @return 메뉴-그룹 정보 목록 List<MenuVo>
     * @throws Exception
     */
	public List<MenuVo> selectListMenu(MenuVo menuVo) throws Exception;
	
    /**
     * 조회한 메뉴-그룹 정보 전체 카운트
     * 
     * @param  menuVo 메뉴-그룹 정보 MenuVo
     * @return 메뉴-그룹 정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountMenu(MenuVo menuVo) throws Exception;
	
    /**
     * 메뉴-그룹 정보를 상세 조회한다.
     *
     * @param  menuVo 메뉴-그룹 정보 MenuVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public MenuVo selectMenu(MenuVo menuVo) throws Exception;
		
    /**
     * 메뉴-그룹 정보를 등록 처리 한다.
     *
     * @param  menuVo 메뉴-그룹 정보 MenuVo
     * @return 번호
     * @throws Exception
     */
	public int insertMenu(MenuVo menuVo) throws Exception;
	
    /**
     * 메뉴-그룹 정보를 갱신 처리 한다.
     *
     * @param  menuVo 메뉴-그룹 정보 MenuVo
     * @return 번호
     * @throws Exception
     */
	public int updateMenu(MenuVo menuVo) throws Exception;
	
    /**
     * 메뉴-그룹 정보를 삭제 처리 한다.
     *
     * @param  menuVo 메뉴-그룹 정보 MenuVo
     * @return 번호
     * @throws Exception
     */
	public int deleteMenu(MenuVo menuVo) throws Exception;
	
    /**
     * 그룹별 메뉴 권한 목록을 조회한다.
     *
     * @param  menuVo 메뉴 정보 (grpId 포함)
     * @return 그룹메뉴 권한 목록 List<MenuVo>
     * @throws Exception
     */
	public List<MenuVo> selectGroupMenuList(MenuVo menuVo) throws Exception;
	
    /**
     * 그룹 메뉴 권한을 저장 처리 한다.
     *
     * @param  menuListVo 그룹메뉴 권한 정보 MenuListVo
     * @return 번호
     * @throws Exception
     */
	public int saveGroupMenu(MenuListVo menuListVo) throws Exception;
	
}
