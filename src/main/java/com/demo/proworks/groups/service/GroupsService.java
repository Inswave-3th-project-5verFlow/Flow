package com.demo.proworks.groups.service;

import java.util.List;

import com.demo.proworks.groups.vo.GroupsVo;

/**  
 * @subject     : 그룹정보 관련 처리를 담당하는 인터페이스
 * @description : 그룹정보 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/15
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/15			 Inswave	 		최초 생성
 * 
 */
public interface GroupsService {
	
    /**
     * 그룹정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 그룹정보 목록 List<GroupsVo>
     * @throws Exception
     */
	public List<GroupsVo> selectListGroups(GroupsVo groupsVo) throws Exception;
	
    /**
     * 조회한 그룹정보 전체 카운트
     * 
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 그룹정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountGroups(GroupsVo groupsVo) throws Exception;
	
    /**
     * 그룹정보를 상세 조회한다.
     *
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public GroupsVo selectGroups(GroupsVo groupsVo) throws Exception;
		
    /**
     * 그룹정보를 등록 처리 한다.
     *
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 번호
     * @throws Exception
     */
	public int insertGroups(GroupsVo groupsVo) throws Exception;
	
    /**
     * 그룹정보를 갱신 처리 한다.
     *
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 번호
     * @throws Exception
     */
	public int updateGroups(GroupsVo groupsVo) throws Exception;
	
    /**
     * 그룹정보를 삭제 처리 한다.
     *
     * @param  groupsVo 그룹정보 GroupsVo
     * @return 번호
     * @throws Exception
     */
	public int deleteGroups(GroupsVo groupsVo) throws Exception;
	
}
