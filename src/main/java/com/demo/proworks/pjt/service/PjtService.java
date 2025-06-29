package com.demo.proworks.pjt.service;

import java.util.List;

import com.demo.proworks.pjt.vo.PjtVo;

/**  
 * @subject     : 프로젝트 기본 정보 관련 처리를 담당하는 인터페이스
 * @description : 프로젝트 기본 정보 관련 처리를 담당하는 인터페이스
 * @author      : 김성민
 * @since       : 2025/06/26
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/26			 김성민	 		최초 생성
 * 
 */
public interface PjtService {
	
    /**
     * 프로젝트 기본 정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 프로젝트 기본 정보 목록 List<PjtVo>
     * @throws Exception
     */
	public List<PjtVo> selectListPjt(PjtVo pjtVo) throws Exception;
	
    /**
     * 조회한 프로젝트 기본 정보 전체 카운트
     * 
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 프로젝트 기본 정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountPjt(PjtVo pjtVo) throws Exception;
	
    /**
     * 프로젝트 기본 정보를 상세 조회한다.
     *
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public PjtVo selectPjt(PjtVo pjtVo) throws Exception;
		
    /**
     * 프로젝트 기본 정보를 등록 처리 한다.
     *
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 번호
     * @throws Exception
     */
	public int insertPjt(PjtVo pjtVo) throws Exception;
	
    /**
     * 프로젝트 기본 정보를 갱신 처리 한다.
     *
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 번호
     * @throws Exception
     */
	public int updatePjt(PjtVo pjtVo) throws Exception;
	
    /**
     * 프로젝트 기본 정보를 삭제 처리 한다.
     *
     * @param  pjtVo 프로젝트 기본 정보 PjtVo
     * @return 번호
     * @throws Exception
     */
	public int deletePjt(PjtVo pjtVo) throws Exception;
	
}
