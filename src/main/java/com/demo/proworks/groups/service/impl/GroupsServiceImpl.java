package com.demo.proworks.groups.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.groups.service.GroupsService;
import com.demo.proworks.groups.vo.GroupsVo;
import com.demo.proworks.groups.dao.GroupsDAO;

/**  
 * @subject     : 그룹정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 그룹정보 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/15
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/15			 Inswave	 		최초 생성
 * 
 */
@Service("groupsServiceImpl")
public class GroupsServiceImpl implements GroupsService {

    @Resource(name="groupsDAO")
    private GroupsDAO groupsDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 그룹정보 목록을 조회합니다.
     *
     * @process
     * 1. 그룹정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<GroupsVo>을(를) 리턴한다.
     * 
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 그룹정보 목록 List<GroupsVo>
     * @throws Exception
     */
	public List<GroupsVo> selectListGroups(GroupsVo groupsVo) throws Exception {
		List<GroupsVo> list = groupsDAO.selectListGroups(groupsVo);	
	
		return list;
	}

    /**
     * 조회한 그룹정보 전체 카운트
     *
     * @process
     * 1. 그룹정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 그룹정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountGroups(GroupsVo groupsVo) throws Exception {
		return groupsDAO.selectListCountGroups(groupsVo);
	}

    /**
     * 그룹정보를 상세 조회한다.
     *
     * @process
     * 1. 그룹정보를 상세 조회한다.
     * 2. 결과 GroupsVo을(를) 리턴한다.
     * 
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public GroupsVo selectGroups(GroupsVo groupsVo) throws Exception {
		GroupsVo resultVO = groupsDAO.selectGroups(groupsVo);			
        
        return resultVO;
	}

    /**
     * 그룹정보를 등록 처리 한다.
     *
     * @process
     * 1. 그룹정보를 등록 처리 한다.
     * 
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 번호
     * @throws Exception
     */
	public int insertGroups(GroupsVo groupsVo) throws Exception {
		return groupsDAO.insertGroups(groupsVo);	
	}
	
    /**
     * 그룹정보를 갱신 처리 한다.
     *
     * @process
     * 1. 그룹정보를 갱신 처리 한다.
     * 
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 번호
     * @throws Exception
     */
	public int updateGroups(GroupsVo groupsVo) throws Exception {				
		return groupsDAO.updateGroups(groupsVo);	   		
	}

    /**
     * 그룹정보를 삭제 처리 한다.
     *
     * @process
     * 1. 그룹정보를 삭제 처리 한다.
     * 
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 번호
     * @throws Exception
     */
	public int deleteGroups(GroupsVo groupsVo) throws Exception {
		return groupsDAO.deleteGroups(groupsVo);
	}
	
}
