package com.demo.proworks.def.service;

import java.util.List;

import com.demo.proworks.def.vo.CmtVo;

/**  
 * @subject     : 코멘트 관리 관련 처리를 담당하는 인터페이스
 * @description : 코멘트 관리 관련 처리를 담당하는 인터페이스
 * @author      : 우민지
 * @since       : 2025/07/27
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/27			 우민지	 		최초 생성
 * 
 */
public interface CmtService {
	
    /**
     * 코멘트 관리 페이징 처리하여 목록을 조회한다.
     *
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 코멘트 관리 목록 List<CmtVo>
     * @throws Exception
     */
	public List<CmtVo> selectListCmt(CmtVo cmtVo) throws Exception;
	
    /**
     * 조회한 코멘트 관리 전체 카운트
     * 
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 코멘트 관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountCmt(CmtVo cmtVo) throws Exception;
	
    /**
     * 코멘트 관리를 상세 조회한다.
     *
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public CmtVo selectCmt(CmtVo cmtVo) throws Exception;
		
    /**
     * 코멘트 관리를 등록 처리 한다.
     *
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 번호
     * @throws Exception
     */
	public int insertCmt(CmtVo cmtVo) throws Exception;
	
    /**
     * 코멘트 관리를 갱신 처리 한다.
     *
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 번호
     * @throws Exception
     */
	public int updateCmt(CmtVo cmtVo) throws Exception;
	
    /**
     * 코멘트 관리를 삭제 처리 한다.
     *
     * @param  cmtVo 코멘트 관리 CmtVo
     * @return 번호
     * @throws Exception
     */
	public int deleteCmt(CmtVo cmtVo) throws Exception;
	
}
