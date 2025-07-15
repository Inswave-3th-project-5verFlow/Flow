package com.demo.proworks.wbs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.wbs.vo.WbsStgVo;
import com.demo.proworks.wbs.vo.WbsVo;
import com.demo.proworks.wbs.dao.WbsDAO;

/**
 * @subject : WBS 관련 처리를 담당하는 DAO
 * @description : WBS 관련 처리를 담당하는 DAO
 * @author : 김성민
 * @since : 2025/07/09
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/09 김성민 최초 생성
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
	 * 단계 목록을 조회 한다.
	 * 
	 * @param WbsStgVo
	 * @return List<WbsStgVo>
	 * @throws ElException
	 */
	public List<WbsStgVo> selectListStg(WbsStgVo vo) throws ElException {
		return (List<WbsStgVo>) list("com.demo.proworks.wbs.selectListStg", vo);
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

}
