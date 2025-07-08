package com.demo.proworks.users.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.users.service.UsersService;
import com.demo.proworks.users.vo.UsersVo;
import com.demo.proworks.users.dao.UsersDAO;

/**  
 * @subject     : 사용자정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 사용자정보 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/05
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/05			 Inswave	 		최초 생성
 * 
 */
@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService {

    @Resource(name="usersDAO")
    private UsersDAO usersDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 사용자정보 목록을 조회합니다.
     *
     * @process
     * 1. 사용자정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<UsersVo>을(를) 리턴한다.
     * 
     * @param  usersVo 사용자정보 UsersVo
     * @return 사용자정보 목록 List<UsersVo>
     * @throws Exception
     */
	public List<UsersVo> selectListUsers(UsersVo usersVo) throws Exception {
		List<UsersVo> list = usersDAO.selectListUsers(usersVo);	
	
		return list;
	}

    /**
     * 조회한 사용자정보 전체 카운트
     *
     * @process
     * 1. 사용자정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  usersVo 사용자정보 UsersVo
     * @return 사용자정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountUsers(UsersVo usersVo) throws Exception {
		return usersDAO.selectListCountUsers(usersVo);
	}

    /**
     * 사용자정보를 상세 조회한다.
     *
     * @process
     * 1. 사용자정보를 상세 조회한다.
     * 2. 결과 UsersVo을(를) 리턴한다.
     * 
     * @param  usersVo 사용자정보 UsersVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public UsersVo selectUsers(UsersVo usersVo) throws Exception {
		UsersVo resultVO = usersDAO.selectUsers(usersVo);			
        
        return resultVO;
	}

    /**
     * 사용자정보를 등록 처리 한다.
     *
     * @process
     * 1. 사용자정보를 등록 처리 한다.
     * 
     * @param  usersVo 사용자정보 UsersVo
     * @return 번호
     * @throws Exception
     */
	public int insertUsers(UsersVo usersVo) throws Exception {
		return usersDAO.insertUsers(usersVo);	
	}
	
    /**
     * 사용자정보를 갱신 처리 한다.
     *
     * @process
     * 1. 사용자정보를 갱신 처리 한다.
     * 
     * @param  usersVo 사용자정보 UsersVo
     * @return 번호
     * @throws Exception
     */
	public int updateUsers(UsersVo usersVo) throws Exception {				
		return usersDAO.updateUsers(usersVo);	   		
	}

    /**
     * 사용자정보를 삭제 처리 한다.
     *
     * @process
     * 1. 사용자정보를 삭제 처리 한다.
     * 
     * @param  usersVo 사용자정보 UsersVo
     * @return 번호
     * @throws Exception
     */
	public int deleteUsers(UsersVo usersVo) throws Exception {
		return usersDAO.deleteUsers(usersVo);
	}
	
    /**
     * 사용자정보, 프로젝트ID, 그룹ID 매핑한다.
     *
     * @process
     * 1. 사용자정보, 프로젝트ID, 그룹ID 매핑한다.
     * 2. 결과 UsersVo을(를) 리턴한다.
     * 
     * @param  usersVo 사용자정보 UsersVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public UsersVo selectUsersMapping(UsersVo usersVo) throws Exception {
		UsersVo resultVO = usersDAO.selectUsersMapping(usersVo);			
        
        return resultVO;
	}
	
	
	
	
}
