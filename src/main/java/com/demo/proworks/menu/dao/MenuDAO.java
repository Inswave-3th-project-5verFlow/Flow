package com.demo.proworks.menu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.menu.vo.MenuVo;
import com.demo.proworks.menu.dao.MenuDAO;

/**  
 * @subject     : 메뉴-그룹 정보 관련 처리를 담당하는 DAO
 * @description : 메뉴-그룹 정보 관련 처리를 담당하는 DAO
 * @author      : 백승호
 * @since       : 2025/07/23
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/23			 백승호	 		최초 생성
 * 
 */
@Repository("menuDAO")
public class MenuDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 메뉴-그룹 정보 상세 조회한다.
     *  
     * @param  MenuVo 메뉴-그룹 정보
     * @return MenuVo 메뉴-그룹 정보
     * @throws ElException
     */
    public MenuVo selectMenu(MenuVo vo) throws ElException {
        return (MenuVo) selectByPk("com.demo.proworks.menu.selectMenu", vo);
    }

    /**
     * 페이징을 처리하여 메뉴-그룹 정보 목록조회를 한다.
     *  
     * @param  MenuVo 메뉴-그룹 정보
     * @return List<MenuVo> 메뉴-그룹 정보
     * @throws ElException
     */
    public List<MenuVo> selectListMenu(MenuVo vo) throws ElException {      	
        return (List<MenuVo>)list("com.demo.proworks.menu.selectListMenu", vo);
    }

    /**
     * 메뉴-그룹 정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  MenuVo 메뉴-그룹 정보
     * @return 메뉴-그룹 정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountMenu(MenuVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.menu.selectListCountMenu", vo);
    }
        
    /**
     * 메뉴-그룹 정보를 등록한다.
     *  
     * @param  MenuVo 메뉴-그룹 정보
     * @return 번호
     * @throws ElException
     */
    public int insertMenu(MenuVo vo) throws ElException {    	
        return insert("com.demo.proworks.menu.insertMenu", vo);
    }

    /**
     * 메뉴-그룹 정보를 갱신한다.
     *  
     * @param  MenuVo 메뉴-그룹 정보
     * @return 번호
     * @throws ElException
     */
    public int updateMenu(MenuVo vo) throws ElException {
        return update("com.demo.proworks.menu.updateMenu", vo);
    }

    /**
     * 메뉴-그룹 정보를 삭제한다.
     *  
     * @param  MenuVo 메뉴-그룹 정보
     * @return 번호
     * @throws ElException
     */
    public int deleteMenu(MenuVo vo) throws ElException {
        return delete("com.demo.proworks.menu.deleteMenu", vo);
    }

    /**
     * 그룹별 메뉴 권한 목록을 조회한다.
     *  
     * @param  MenuVo 메뉴 정보 (grpId 포함)
     * @return List<MenuVo> 그룹메뉴 권한 목록
     * @throws ElException
     */
    public List<MenuVo> selectGroupMenuList(MenuVo vo) throws ElException {      	
        return (List<MenuVo>)list("com.demo.proworks.menu.selectGroupMenuList", vo);
    }

    /**
     * 그룹 메뉴 권한을 등록한다.
     *  
     * @param  MenuVo 그룹메뉴 권한 정보
     * @return 번호
     * @throws ElException
     */
    public int insertGroupMenu(MenuVo vo) throws ElException {    	
        return insert("com.demo.proworks.menu.insertGroupMenu", vo);
    }

    /**
     * 그룹 메뉴 권한을 삭제한다.
     *  
     * @param  MenuVo 그룹메뉴 권한 정보
     * @return 번호
     * @throws ElException
     */
    public int deleteGroupMenu(MenuVo vo) throws ElException {
        return delete("com.demo.proworks.menu.deleteGroupMenu", vo);
    }

}
