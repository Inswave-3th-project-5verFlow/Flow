package com.demo.proworks.menu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.menu.vo.MenuGroupsVo;
import com.demo.proworks.menu.dao.MenuGroupsDAO;

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
@Repository("menuGroupsDAO")
public class MenuGroupsDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 메뉴-그룹 정보 상세 조회한다.
     *  
     * @param  MenuGroupsVo 메뉴-그룹 정보
     * @return MenuGroupsVo 메뉴-그룹 정보
     * @throws ElException
     */
    public MenuGroupsVo selectMenuGroups(MenuGroupsVo vo) throws ElException {
        return (MenuGroupsVo) selectByPk("com.demo.proworks.menu.selectMenuGroups", vo);
    }

    /**
     * 페이징을 처리하여 메뉴-그룹 정보 목록조회를 한다.
     *  
     * @param  MenuGroupsVo 메뉴-그룹 정보
     * @return List<MenuGroupsVo> 메뉴-그룹 정보
     * @throws ElException
     */
    public List<MenuGroupsVo> selectListMenuGroups(MenuGroupsVo vo) throws ElException {      	
        return (List<MenuGroupsVo>)list("com.demo.proworks.menu.selectListMenuGroups", vo);
    }

    /**
     * 메뉴-그룹 정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  MenuGroupsVo 메뉴-그룹 정보
     * @return 메뉴-그룹 정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountMenuGroups(MenuGroupsVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.menu.selectListCountMenuGroups", vo);
    }
        
    /**
     * 메뉴-그룹 정보를 등록한다.
     *  
     * @param  MenuGroupsVo 메뉴-그룹 정보
     * @return 번호
     * @throws ElException
     */
    public int insertMenuGroups(MenuGroupsVo vo) throws ElException {    	
        return insert("com.demo.proworks.menu.insertMenuGroups", vo);
    }

    /**
     * 메뉴-그룹 정보를 갱신한다.
     *  
     * @param  MenuGroupsVo 메뉴-그룹 정보
     * @return 번호
     * @throws ElException
     */
    public int updateMenuGroups(MenuGroupsVo vo) throws ElException {
        return update("com.demo.proworks.menu.updateMenuGroups", vo);
    }

    /**
     * 메뉴-그룹 정보를 삭제한다.
     *  
     * @param  MenuGroupsVo 메뉴-그룹 정보
     * @return 번호
     * @throws ElException
     */
    public int deleteMenuGroups(MenuGroupsVo vo) throws ElException {
        return delete("com.demo.proworks.menu.deleteMenuGroups", vo);
    }

}
