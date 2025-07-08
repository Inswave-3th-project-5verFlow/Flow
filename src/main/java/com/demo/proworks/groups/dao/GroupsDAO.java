package com.demo.proworks.groups.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.groups.vo.GroupsVo;
import com.demo.proworks.groups.dao.GroupsDAO;

/**  
 * @subject     : 그룹정보 관련 처리를 담당하는 DAO
 * @description : 그룹정보 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/05
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/05			 Inswave	 		최초 생성
 * 
 */
@Repository("groupsDAO")
public class GroupsDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 그룹정보 상세 조회한다.
     *  
     * @param  GroupsVo 그룹정보
     * @return GroupsVo 그룹정보
     * @throws ElException
     */
    public GroupsVo selectGroups(GroupsVo vo) throws ElException {
        return (GroupsVo) selectByPk("com.demo.proworks.groups.selectGroups", vo);
    }

    /**
     * 페이징을 처리하여 그룹정보 목록조회를 한다.
     *  
     * @param  GroupsVo 그룹정보
     * @return List<GroupsVo> 그룹정보
     * @throws ElException
     */
    public List<GroupsVo> selectListGroups(GroupsVo vo) throws ElException {      	
        return (List<GroupsVo>)list("com.demo.proworks.groups.selectListGroups", vo);
    }

    /**
     * 그룹정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  GroupsVo 그룹정보
     * @return 그룹정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountGroups(GroupsVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.groups.selectListCountGroups", vo);
    }
        
    /**
     * 그룹정보를 등록한다.
     *  
     * @param  GroupsVo 그룹정보
     * @return 번호
     * @throws ElException
     */
    public int insertGroups(GroupsVo vo) throws ElException {    	
        return insert("com.demo.proworks.groups.insertGroups", vo);
    }

    /**
     * 그룹정보를 갱신한다.
     *  
     * @param  GroupsVo 그룹정보
     * @return 번호
     * @throws ElException
     */
    public int updateGroups(GroupsVo vo) throws ElException {
        return update("com.demo.proworks.groups.updateGroups", vo);
    }

    /**
     * 그룹정보를 삭제한다.
     *  
     * @param  GroupsVo 그룹정보
     * @return 번호
     * @throws ElException
     */
    public int deleteGroups(GroupsVo vo) throws ElException {
        return delete("com.demo.proworks.groups.deleteGroups", vo);
    }

}
