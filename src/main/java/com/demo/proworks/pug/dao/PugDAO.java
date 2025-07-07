package com.demo.proworks.pug.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.pug.vo.PugVo;
import com.demo.proworks.pug.dao.PugDAO;

/**
 * @subject : 프로젝트 유저 그룹 매핑 정보 관련 처리를 담당하는 DAO
 * @description : 프로젝트 유저 그룹 매핑 정보 관련 처리를 담당하는 DAO
 * @author : 김성민
 * @since : 2025/07/07
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/07 김성민 최초 생성
 * 
 */
@Repository("pugDAO")
public class PugDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 프로젝트 유저 그룹 매핑 정보 상세 조회한다.
	 * 
	 * @param PugVo 프로젝트 유저 그룹 매핑 정보
	 * @return PugVo 프로젝트 유저 그룹 매핑 정보
	 * @throws ElException
	 */
	public PugVo selectPug(PugVo vo) throws ElException {
		return (PugVo) selectByPk("com.demo.proworks.pug.selectPug", vo);
	}

	/**
	 * 페이징을 처리하여 프로젝트 유저 그룹 매핑 정보 목록조회를 한다.
	 * 
	 * @param PugVo 프로젝트 유저 그룹 매핑 정보
	 * @return List<PugVo> 프로젝트 유저 그룹 매핑 정보
	 * @throws ElException
	 */
	public List<PugVo> selectListPug(PugVo vo) throws ElException {
		return (List<PugVo>) list("com.demo.proworks.pug.selectListPug", vo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param PugVo 프로젝트 유저 그룹 매핑 정보
	 * @return 프로젝트 유저 그룹 매핑 정보 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountPug(PugVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.pug.selectListCountPug", vo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 등록한다.
	 * 
	 * @param PugVo 프로젝트 유저 그룹 매핑 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int insertPug(PugVo vo) throws ElException {
		return insert("com.demo.proworks.pug.insertPug", vo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 갱신한다.
	 * 
	 * @param PugVo 프로젝트 유저 그룹 매핑 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int updatePug(PugVo vo) throws ElException {
		return update("com.demo.proworks.pug.updatePug", vo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 삭제한다.
	 * 
	 * @param PugVo 프로젝트 유저 그룹 매핑 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int deletePug(PugVo vo) throws ElException {
		return delete("com.demo.proworks.pug.deletePug", vo);
	}

	/**
	 * 메뉴 그룹 목록을 조회한다.
	 * 
	 * @param PugVo 프로젝트 유저 그룹 매핑 정보
	 * @return 번호
	 * @throws ElException
	 */
	public List<PugVo> selectListGrp(PugVo vo) throws ElException {
		return (List<PugVo>) list("com.demo.proworks.pug.selectListGrp", vo);
	}

}
