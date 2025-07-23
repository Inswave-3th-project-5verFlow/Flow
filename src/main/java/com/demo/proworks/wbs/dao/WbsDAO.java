package com.demo.proworks.wbs.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.inswave.elfw.exception.ElException;
import com.demo.proworks.wbs.vo.WbsVo;

/**
 * @subject : WBS 관련 처리를 담당하는 DAO
 * @description : WBS 관련 처리를 담당하는 DAO
 * @author : 김성민
 * @since : 2025/07/09
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/09 김성민 최초 생성 2025/07/19 김성민 STG 테이블 관련 코드 제거
 * 
 */
@Repository("wbsDAO")
public class WbsDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * WBS 상세 조회한다.
	 * 
	 * @param WbsVo WBS
	 * @return WbsVo WBS
	 * @throws ElException
	 */
	public WbsVo selectWbs(WbsVo vo) throws ElException {
		return (WbsVo) selectByPk("com.demo.proworks.wbs.selectWbs", vo);
	}

	/**
	 * WBS를 등록한다.
	 * 
	 * @param WbsVo WBS
	 * @return 번호
	 * @throws ElException
	 */
	public int insertWbs(WbsVo vo) throws ElException {
		return insert("com.demo.proworks.wbs.insertWbs", vo);
	}

	/**
	 * WBS를 갱신한다.
	 * 
	 * @param WbsVo WBS
	 * @return 번호
	 * @throws ElException
	 */
	public int updateWbs(WbsVo vo) throws ElException {
		return update("com.demo.proworks.wbs.updateWbs", vo);
	}

	/**
	 * WBS를 삭제한다.
	 * 
	 * @param WbsVo WBS
	 * @return 번호
	 * @throws ElException
	 */
	public int deleteWbs(WbsVo vo) throws ElException {
		return delete("com.demo.proworks.wbs.deleteWbs", vo);
	}

	/**
	 * 페이징을 처리하여 전체 WBS 계층 구조를 조회한다.
	 * 
	 * @param WbsVo WBS
	 * @return List<WbsVo> WBS
	 * @throws ElException
	 */
	public List<WbsVo> selectListWbsAll(WbsVo vo) throws ElException {
		return (List<WbsVo>) list("com.demo.proworks.wbs.selectListWbsAll", vo);
	}

	/**
	 * 전체 WBS 카운트를 조회한다.
	 * 
	 * @param WbsVo WBS
	 * @return WBS 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountWbsAll(WbsVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.wbs.selectListCountWbsAll", vo);
	}

	/**
	 * 페이징을 처리하여 검색 조건에 따른 WBS 계층 구조를 조회한다.
	 * 
	 * @param WbsVo WBS
	 * @return List<WbsVo> WBS
	 * @throws ElException
	 */
	public List<WbsVo> selectListWbsSearch(WbsVo vo) throws ElException {
		return (List<WbsVo>) list("com.demo.proworks.wbs.selectListWbsSearch", vo);
	}

	/**
	 * 검색 조건에 따른 WBS 카운트를 조회한다.
	 * 
	 * @param WbsVo WBS
	 * @return WBS 조회의 카운트
	 * @throws ElException
	 */
	public long selectListCountWbsSearch(WbsVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.wbs.selectListCountWbsSearch", vo);
	}

// WbsDAO.java에 추가할 메소드들

	/**
	 * 하위 설계업무 조회
	 */
	public List<WbsVo> selectChildDesign(WbsVo vo) throws ElException {
		return (List<WbsVo>) list("com.demo.proworks.wbs.selectChildDesign", vo);
	}

	/**
	 * 연관 개발업무 조회
	 */
	public List<WbsVo> selectDevByDesign(WbsVo vo) throws ElException {
		return (List<WbsVo>) list("com.demo.proworks.wbs.selectDevByDesign", vo);
	}

	/**
	 * 상위 설계업무 조회
	 */
	public WbsVo selectParentDesign(WbsVo vo) throws ElException {
		return (WbsVo) selectByPk("com.demo.proworks.wbs.selectParentDesign", vo);
	}

	/**
	 * 개발업무의 상위 설계업무 조회
	 */
	public WbsVo selectDesignByDev(WbsVo vo) throws ElException {
		return (WbsVo) selectByPk("com.demo.proworks.wbs.selectDesignByDev", vo);
	}

	/**
	 * 진척률 업데이트
	 */
	public int updateRate(WbsVo vo) throws ElException {
		return update("com.demo.proworks.wbs.updateRate", vo);
	}
	
	/**
	 * 모든 하위 업무 조회
	 */
	public List<WbsVo> selectAllChildrenByParent(WbsVo vo) throws ElException {
		return (List<WbsVo>) list("com.demo.proworks.wbs.selectAllChildrenByParent", vo);
	}

}