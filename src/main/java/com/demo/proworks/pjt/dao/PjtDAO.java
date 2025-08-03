package com.demo.proworks.pjt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.proworks.pjt.dao.PjtDAO;
import com.demo.proworks.pjt.vo.PjtVo;
import com.inswave.elfw.exception.ElException;

/**
 * @subject : 프로젝트 정보 관련 처리를 담당하는 DAO
 * @description : 프로젝트 정보 관련 처리를 담당하는 DAO
 * @author : 김성민
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 김성민 최초 생성
 * 
 */
@Repository("pjtDAO")
public class PjtDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 프로젝트 정보 상세 조회한다.
	 * 
	 * @param  pjtVo 프로젝트 정보 PjtVo
	 * @return PjtVo 프로젝트 정보
	 * @throws ElException
	 */
	public PjtVo selectPjt(PjtVo pjtVo) throws ElException {
		return (PjtVo) selectByPk("com.demo.proworks.pjt.selectPjt", pjtVo);
	}

	/**
	 * 페이징을 처리하여 프로젝트 정보 목록조회를 한다.
	 * 
	 * @param  pjtVo 프로젝트 정보 PjtVo
	 * @return List<PjtVo> 프로젝트 정보
	 * @throws ElException
	 */
	public List<PjtVo> selectListPjt(PjtVo pjtVo) throws ElException {
		return (List<PjtVo>) list("com.demo.proworks.pjt.selectListPjt", pjtVo);
	}

	/**
	 * 프로젝트 정보 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param  pjtVo 프로젝트 정보 PjtVo
	 * @return 프로젝트 정보 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountPjt(PjtVo pjtVo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.pjt.selectListCountPjt", pjtVo);
	}

	/**
	 * 프로젝트 정보를 등록한다.
	 * 
	 * @param  pjtVo 프로젝트 정보 PjtVo
	 * @throws ElException
	 */
	public void insertPjt(PjtVo pjtVo) throws ElException {
		insert("com.demo.proworks.pjt.insertPjt", pjtVo);
	}

	/**
	 * 프로젝트 정보를 갱신한다.
	 * 
	 * @param  pjtVo 프로젝트 정보 PjtVo
	 * @throws ElException
	 */
	public void updatePjt(PjtVo pjtVo) throws ElException {
		update("com.demo.proworks.pjt.updatePjt", pjtVo);
	}

	/**
	 * 프로젝트 정보를 삭제한다.
	 * 
	 * @param  pjtVo 프로젝트 정보 PjtVo
	 * @throws ElException
	 */
	public void deletePjt(PjtVo pjtVo) throws ElException {
		delete("com.demo.proworks.pjt.deletePjt", pjtVo);
	}

	/**
	 * 프로젝트 ID로 최상위 업무들의 평균 진척률을 조회한다.
	 * 
	 * @param pjtId 프로젝트 ID
	 * @return Integer 프로젝트 진행률
	 * @throws ElException
	 */
	public Integer selectProjectProgress(String pjtId) throws ElException {
		return (Integer) selectByPk("com.demo.proworks.pjt.selectProjectProgress", pjtId);
	}

	/**
	 * 프로젝트 유저 그룹에 프로젝트를 등록한다.
	 * 
	 * @param  pjtVo 프로젝트 정보 PjtVo
	 * @throws ElException
	 */
	public void insertPug(PjtVo pjtVo) throws ElException {
		insert("com.demo.proworks.pjt.insertPug", pjtVo);
	}

}
