package com.demo.proworks.wbs.service;

import java.util.List;
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
 *               2025/07/19 김성민 STG 테이블 관련 코드 제거
 * 
 */
public interface WbsService {
	
	/**
	 * 전체 WBS 목록을 조회한다.
	 */
	public List<WbsVo> selectListWbsAll(WbsVo wbsVo) throws Exception;
	
	/**
	 * 검색 조건에 따른 WBS 목록을 조회한다.
	 */
	public List<WbsVo> selectListWbsSearch(WbsVo wbsVo) throws Exception;
	
	/**
	 * 전체 WBS 카운트를 조회한다.
	 */
	public long selectListCountWbsAll(WbsVo wbsVo) throws Exception;
	
	/**
	 * 검색 조건에 따른 WBS 카운트를 조회한다.
	 */
	public long selectListCountWbsSearch(WbsVo wbsVo) throws Exception;
	
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
	
	// 진척률 계산
	public void calcProgress(WbsVo wbsVo) throws Exception;
}