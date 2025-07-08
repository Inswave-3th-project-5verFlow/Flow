package com.demo.proworks.iss.service;

import java.util.List;

import com.demo.proworks.iss.vo.IssVo;

/**  
 * @subject     : 이슈리스크관리 관련 처리를 담당하는 인터페이스
 * @description : 이슈리스크관리 관련 처리를 담당하는 인터페이스
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
public interface IssService {
	
    /**
     * 이슈리스크관리 페이징 처리하여 목록을 조회한다.
     *
     * @param  issVo 이슈리스크관리 IssVo
     * @return 이슈리스크관리 목록 List<IssVo>
     * @throws Exception
     */
	public List<IssVo> selectListIss(IssVo issVo) throws Exception;
	
    /**
     * 조회한 이슈리스크관리 전체 카운트
     * 
     * @param  issVo 이슈리스크관리 IssVo
     * @return 이슈리스크관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountIss(IssVo issVo) throws Exception;
	
    /**
     * 이슈리스크관리를 상세 조회한다.
     *
     * @param  issVo 이슈리스크관리 IssVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public IssVo selectIss(IssVo issVo) throws Exception;
		
    /**
     * 이슈리스크관리를 등록 처리 한다.
     *
     * @param  issVo 이슈리스크관리 IssVo
     * @return 번호
     * @throws Exception
     */
	public int insertIss(IssVo issVo) throws Exception;
	
    /**
     * 이슈리스크관리를 갱신 처리 한다.
     *
     * @param  issVo 이슈리스크관리 IssVo
     * @return 번호
     * @throws Exception
     */
	public int updateIss(IssVo issVo) throws Exception;
	
    /**
     * 이슈리스크관리를 삭제 처리 한다.
     *
     * @param  issVo 이슈리스크관리 IssVo
     * @return 번호
     * @throws Exception
     */
	public int deleteIss(IssVo issVo) throws Exception;
	
}
