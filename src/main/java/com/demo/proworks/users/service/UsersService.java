package com.demo.proworks.users.service;

import java.util.List;

import com.demo.proworks.users.vo.UsersVo;

/**
 * @subject : 사용자정보 관련 처리를 담당하는 인터페이스
 * @description : 사용자정보 관련 처리를 담당하는 인터페이스
 * @author : Inswave
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 Inswave 최초 생성
 * 
 */
public interface UsersService {

	/**
	 * 사용자정보 페이징 처리하여 목록을 조회한다.
	 *
	 * @param usersVo 사용자정보 UsersVo
	 * @return 사용자정보 목록 List<UsersVo>
	 * @throws Exception
	 */
	public List<UsersVo> selectListUsers(UsersVo usersVo) throws Exception;

	/**
	 * 조회한 사용자정보 전체 카운트
	 * 
	 * @param usersVo 사용자정보 UsersVo
	 * @return 사용자정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountUsers(UsersVo usersVo) throws Exception;

	/**
	 * 사용자정보를 상세 조회한다.
	 *
	 * @param usersVo 사용자정보 UsersVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public UsersVo selectUsers(UsersVo usersVo) throws Exception;

	/**
	 * 사용자정보를 등록 처리 한다.
	 *
	 * @param usersVo 사용자정보 UsersVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertUsers(UsersVo usersVo) throws Exception;

	/**
	 * 사용자정보를 갱신 처리 한다.
	 *
	 * @param usersVo 사용자정보 UsersVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateUsers(UsersVo usersVo) throws Exception;

	/**
	 * 사용자정보를 삭제 처리 한다.
	 *
	 * @param usersVo 사용자정보 UsersVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteUsers(UsersVo usersVo) throws Exception;

	/**
	 * 사용자정보, 프로젝트ID, 그룹ID 매핑한다.
	 *
	 * @param usersVo 사용자정보 UsersVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public List<UsersVo> selectUsersMapping(UsersVo usersVo) throws Exception;

	/**
	 * 비밀번호를 초기화 한다
	 *
	 * @param usersVo 사용자정보 UsersVo
	 * @return 번호
	 * @throws Exception
	 */
	public int resetPwd(UsersVo usersVo) throws Exception;

}
