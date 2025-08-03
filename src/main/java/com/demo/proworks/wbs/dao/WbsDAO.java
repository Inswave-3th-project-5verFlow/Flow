package com.demo.proworks.wbs.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.inswave.elfw.exception.ElException;
import com.demo.proworks.wbs.vo.WbsVo;

@Repository("wbsDAO")
public class WbsDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * WBS 상세 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return WbsVo WBS
	 * @throws ElException
	 */
	public WbsVo selectWbs(WbsVo wbsVo) throws ElException {
		return (WbsVo) selectByPk("com.demo.proworks.wbs.selectWbs", wbsVo);
	}

	/**
	 * WBS를 등록한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws ElException
	 */
	public void insertWbs(WbsVo wbsVo) throws ElException {
		insert("com.demo.proworks.wbs.insertWbs", wbsVo);
	}

	/**
	 * WBS를 갱신한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws ElException
	 */
	public void updateWbs(WbsVo wbsVo) throws ElException {
		update("com.demo.proworks.wbs.updateWbs", wbsVo);
	}

	/**
	 * WBS를 삭제한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws ElException
	 */
	public void deleteWbs(WbsVo wbsVo) throws ElException {
		delete("com.demo.proworks.wbs.deleteWbs", wbsVo);
	}

	/**
	 * 페이징을 처리하여 전체 WBS 계층 구조를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return List<WbsVo> WBS
	 * @throws ElException
	 */
	public List<WbsVo> selectListWbsAll(WbsVo wbsVo) throws ElException {
		return (List<WbsVo>) list("com.demo.proworks.wbs.selectListWbsAll", wbsVo);
	}

	/**
	 * 전체 WBS 카운트를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return WBS 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountWbsAll(WbsVo wbsVo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.wbs.selectListCountWbsAll", wbsVo);
	}

	/**
	 * 페이징을 처리하여 검색 조건에 따른 WBS 계층 구조를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return List<WbsVo> WBS
	 * @throws ElException
	 */
	public List<WbsVo> selectListWbsSearch(WbsVo wbsVo) throws ElException {
		return (List<WbsVo>) list("com.demo.proworks.wbs.selectListWbsSearch", wbsVo);
	}

	/**
	 * 검색 조건에 따른 WBS 카운트를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return WBS 조회의 카운트
	 * @throws ElException
	 */
	public long selectListCountWbsSearch(WbsVo wbsVo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.wbs.selectListCountWbsSearch", wbsVo);
	}


	/**
	 * 진척률 업데이트
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws ElException
	 */
	public void updateRate(WbsVo wbsVo) throws ElException {
		update("com.demo.proworks.wbs.updateRate", wbsVo);
	}
	
	/**
	 * 모든 하위 업무 조회
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return List<WbsVo> WBS
	 * @throws ElException
	 */
	public List<WbsVo> selectAllChildrenByParent(WbsVo wbsVo) throws ElException {
		return (List<WbsVo>) list("com.demo.proworks.wbs.selectAllChildrenByParent", wbsVo);
	}

}