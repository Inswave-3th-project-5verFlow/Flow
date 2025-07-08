package com.demo.proworks.task.design.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.task.design.vo.DesignVo;
import com.demo.proworks.task.design.dao.DesignDAO;

/**
 * @subject : 설계 업무 정보 관련 처리를 담당하는 DAO
 * @description : 설계 업무 정보 관련 처리를 담당하는 DAO
 * @author : 백승호
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 백승호 최초 생성
 * 
 */
@Repository("designDAO")
public class DesignDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 설계 업무 정보 상세 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return DesignVo 설계 업무 정보
	 * @throws ElException
	 */
	public DesignVo selectDesign(DesignVo vo) throws ElException {
		return (DesignVo) selectByPk("com.demo.proworks.task.design.selectDesign", vo);
	}

	/**
	 * 페이징을 처리하여 설계 업무 정보 목록조회를 한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectListDesign(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectListDesign", vo);
	}

	/**
	 * 설계 업무 정보 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 설계 업무 정보 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountDesign(DesignVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.task.design.selectListCountDesign", vo);
	}

	/**
	 * 설계 업무 정보를 등록한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int insertDesign(DesignVo vo) throws ElException {
		return insert("com.demo.proworks.task.design.insertDesign", vo);
	}

	/**
	 * 설계 업무 정보를 갱신한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int updateDesign(DesignVo vo) throws ElException {
		return update("com.demo.proworks.task.design.updateDesign", vo);
	}

	/**
	 * 설계 업무 정보를 삭제한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int deleteDesign(DesignVo vo) throws ElException {
		return delete("com.demo.proworks.task.design.deleteDesign", vo);
	}

	/**
	 * 트리 구조에 맞게 업무 목록을 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectTreeListDesign(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectTreeListDesign", vo);
	}

}
