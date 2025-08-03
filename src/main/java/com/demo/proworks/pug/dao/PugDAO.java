package com.demo.proworks.pug.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.pug.vo.PugUserVo;
import com.demo.proworks.pug.vo.PugVo;
import com.demo.proworks.pug.dao.PugDAO;

@Repository("pugDAO")
public class PugDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 프로젝트 유저 그룹 매핑 정보 상세 조회한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return PugVo 프로젝트 유저 그룹 매핑 정보
	 * @throws ElException
	 */
	public PugVo selectPug(PugVo pugVo) throws ElException {
		return (PugVo) selectByPk("com.demo.proworks.pug.selectPug", pugVo);
	}

	/**
	 * 페이징을 처리하여 프로젝트 유저 그룹 매핑 정보 목록조회를 한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return List<PugVo> 프로젝트 유저 그룹 매핑 정보
	 * @throws ElException
	 */
	public List<PugVo> selectListPug(PugVo pugVo) throws ElException {
		return (List<PugVo>) list("com.demo.proworks.pug.selectListPug", pugVo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 프로젝트 유저 그룹 매핑 정보 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountPug(PugVo pugVo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.pug.selectListCountPug", pugVo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 등록한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @throws ElException
	 */
	public void insertPug(PugVo pugVo) throws ElException {
		insert("com.demo.proworks.pug.insertPug", pugVo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 갱신한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @throws ElException
	 */
	public void updatePug(PugVo pugVo) throws ElException {
		update("com.demo.proworks.pug.updatePug", pugVo);
	}

	/**
	 * 프로젝트 유저 그룹 매핑 정보를 삭제한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @throws ElException
	 */
	public void deletePug(PugVo pugVo) throws ElException {
		delete("com.demo.proworks.pug.deletePug", pugVo);
	}

	/**
	 * 메뉴 그룹 목록을 조회한다.
	 * 
	 * @param pugVo 프로젝트 유저 그룹 매핑 정보 PugVo
	 * @return 번호
	 * @throws ElException
	 */
	public List<PugVo> selectListGrp(PugVo pugVo) throws ElException {
		return (List<PugVo>) list("com.demo.proworks.pug.selectListGrp", pugVo);
	}

	/**
	 * 유저 목록을 조회한다.
	 * 
	 * @param pugUserVo 사용자 정보 PugUserVo
	 * @return 번호
	 * @throws ElException
	 */
	public List<PugUserVo> selectListPugUser(PugUserVo pugUserVo) throws ElException {
		return (List<PugUserVo>) list("com.demo.proworks.pug.selectListPugUser", pugUserVo);
	}

	/**
	 * 전체 유저 목록의 카운트를 조회한다.
	 * 
	 * @param pugUserVo 사용자 정보 PugUserVo
	 * @return 유저 목록의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountPugUser(PugUserVo pugUserVo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.pug.selectListCountPugUser", pugUserVo);
	}

}
