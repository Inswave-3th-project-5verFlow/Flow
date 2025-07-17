package com.demo.proworks.task.design.service;

import java.util.List;

import com.demo.proworks.task.design.vo.DesignVo;

/**
 * @subject : 설계 업무 정보 관련 처리를 담당하는 인터페이스
 * @description : 설계 업무 정보 관련 처리를 담당하는 인터페이스
 * @author : 백승호
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 백승호 최초 생성
 * 
 */
public interface DesignService {

	/**
	 * 모든 업무 정보 목록을 조회합니다.
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTasks(DesignVo designVo) throws Exception;

	/**
	 * 설계 업무 정보 페이징 처리하여 목록을 조회한다.
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectListDesign(DesignVo designVo) throws Exception;

	/**
	 * 개발 업무 정보 페이징 처리하여 목록을 조회한다.
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 개발 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectListDevelop(DesignVo designVo) throws Exception;

	/**
	 * 조회한 설계 업무 정보 전체 카운트
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountDesign(DesignVo designVo) throws Exception;

	/**
	 * 조회한 개발 업무 정보 전체 카운트
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountDevelop(DesignVo designVo) throws Exception;

	/**
	 * 설계 업무 정보를 상세 조회한다.
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public DesignVo selectDesign(DesignVo designVo) throws Exception;

	/**
	 * 설계 업무 정보를 등록 처리 한다.
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertDesign(DesignVo designVo) throws Exception;

	/**
	 * 설계 업무 정보를 갱신 처리 한다.
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateDesign(DesignVo designVo) throws Exception;

	/**
	 * 설계 업무 정보를 삭제 처리 한다.
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteDesign(DesignVo designVo) throws Exception;

	/**
	 * 트리 구조로 설계 업무를 조회한다
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTreeListDesign(DesignVo designVo) throws Exception;

	/**
	 * 트리 구조로 개발 업무를 조회한다
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTreeListDevelop(DesignVo designVo) throws Exception;

	/**
	 * 트리 구조로 모든 업무를 조회한다
	 *
	 * @param designVo  업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTreeList(DesignVo designVo) throws Exception;

	/**
	 * 모든 단계의 업무를 정렬된 형태로 조회한다.
	 *
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectListTask(DesignVo designVo) throws Exception;
}
