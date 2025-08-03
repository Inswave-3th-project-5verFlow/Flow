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
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 전체 WBS 목록
	 * @throws Exception
	 */
	public List<WbsVo> selectListWbsAll(WbsVo wbsVo) throws Exception;
	
	/**
	 * 검색 조건에 따른 WBS 목록을 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 검색 조건에 맞는 WBS 목록
	 * @throws Exception
	 */
	public List<WbsVo> selectListWbsSearch(WbsVo wbsVo) throws Exception;
	
	/**
	 * 전체 WBS 카운트를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 전체 WBS 카운트
	 * @throws Exception
	 */
	public long selectListCountWbsAll(WbsVo wbsVo) throws Exception;
	
	/**
	 * 검색 조건에 따른 WBS 카운트를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 검색 조건에 맞는 WBS 카운트
	 * @throws Exception
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
	 * @throws Exception
	 */
	public void insertWbs(WbsVo wbsVo) throws Exception;
	
	/**
	 * WBS를 갱신 처리 한다.
	 *
	 * @param wbsVo WBS WbsVo
	 * @throws Exception
	 */
	public void updateWbs(WbsVo wbsVo) throws Exception;
	
	/**
	 * WBS를 삭제 처리 한다.
	 *
	 * @param wbsVo WBS WbsVo
	 * @throws Exception
	 */
	public void deleteWbs(WbsVo wbsVo) throws Exception;
	
	/**
	 * 진척률을 계산한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws Exception
	 */
	public void calcProgress(WbsVo wbsVo) throws Exception;
}