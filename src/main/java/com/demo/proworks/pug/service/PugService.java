package com.demo.proworks.pug.service;

import java.util.List;

import com.demo.proworks.pug.vo.PugUserVo;
import com.demo.proworks.pug.vo.PugVo;

/**
 * @subject : 프로젝트 유저 그룹 매핑 정보 관련 처리를 담당하는 인터페이스
 * @description : 프로젝트 유저 그룹 매핑 정보 관련 처리를 담당하는 인터페이스
 * @author : 김성민
 * @since : 2025/07/07
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/07 김성민 최초 생성
 * 
 */
public interface PugService {

	/**
	 * 프로젝트 유저 그룹 매핑 정보 페이징 처리하여 목록을 조회한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 프로젝트 유저 그룹 매핑 정보 목록 List<PugVo>
	 * @throws Exception
	 */
	public List<PugVo> selectListPug(PugVo pugVo) throws Exception;

	/**
	 * 조회한 프로젝트 유저 그룹 매핑 정보 전체 카운트
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 프로젝트 유저 그룹 매핑 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountPug(PugVo pugVo) throws Exception;

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 상세 조회한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public PugVo selectPug(PugVo pugVo) throws Exception;

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 등록 처리 한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertPug(PugVo pugVo) throws Exception;

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 갱신 처리 한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updatePug(PugVo pugVo) throws Exception;

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 삭제 처리 한다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deletePug(PugVo pugVo) throws Exception;

	/**
	 * 메뉴 그룹 목록을 조회힌다.
	 *
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws Exception
	 */
	public List<PugVo> selectListGrp(PugVo pugVo) throws Exception;

	/**
	 * 유저 목록을 페이징 처리하여 조회한다.
	 * 
	 * @param pugUserVo 유저 정보 PugUserVo
	 * @return 유저 목록 List<PugUserVo>
	 * @throws Exception
	 */
	public List<PugUserVo> selectListPugUser(PugUserVo pugUserVo) throws Exception;

	/**
	 * 전체 유저 목록의 카운트를 조회한다.
	 * 
	 * @param pugUserVo 유저 정보 PugUserVo
	 * @return 유저 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountPugUser(PugUserVo pugUserVo) throws Exception;

}
