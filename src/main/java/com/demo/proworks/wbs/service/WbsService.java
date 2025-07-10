package com.demo.proworks.wbs.service;

import java.util.List;

import com.demo.proworks.wbs.vo.WbsStgVo;
import com.demo.proworks.wbs.vo.WbsVo;

/**
 * @subject : WBS 관련 처리를 담당하는 인터페이스
 * @description : WBS 관련 처리를 담당하는 인터페이스
 * @author : 김성민
 * @since : 2025/07/09
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/09 김성민 최초 생성
 * 
 */
public interface WbsService {

	/**
	 * WBS 페이징 처리하여 목록을 조회한다.
	 *
	 * @param wbsVo WBS WbsVo
	 * @return WBS 목록 List<WbsVo>
	 * @throws Exception
	 */
	public List<WbsVo> selectListWbs(WbsVo wbsVo) throws Exception;

	/**
	 * 조회한 WBS 전체 카운트
	 * 
	 * @param wbsVo WBS WbsVo
	 * @return WBS 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountWbs(WbsVo wbsVo) throws Exception;

	/**
	 * WBS를 상세 조회한다.
	 *
	 * @param wbsVo WBS WbsVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public WbsVo selectWbs(WbsVo wbsVo) throws Exception;

	/**
	 * WBS를 등록 처리 한다.
	 *
	 * @param wbsVo WBS WbsVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertWbs(WbsVo wbsVo) throws Exception;

	/**
	 * WBS를 갱신 처리 한다.
	 *
	 * @param wbsVo WBS WbsVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateWbs(WbsVo wbsVo) throws Exception;

	/**
	 * WBS를 삭제 처리 한다.
	 *
	 * @param wbsVo WBS WbsVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteWbs(WbsVo wbsVo) throws Exception;

	/**
	 * 단계 목록을 조회 한다.
	 *
	 * @param WbsStgVo
	 * @return List<WbsStgVo>
	 * @throws Exception
	 */
	public List<WbsStgVo> selectListStg(WbsStgVo wbsStgVo) throws Exception;

}
