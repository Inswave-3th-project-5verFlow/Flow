package com.demo.proworks.out.service;

import java.util.List;

import com.demo.proworks.out.vo.OutVo;

/**  
 * @subject     : 산출물관리 관련 처리를 담당하는 인터페이스
 * @description : 산출물관리 관련 처리를 담당하는 인터페이스
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
public interface OutService {
	
    /**
     * 산출물관리 페이징 처리하여 목록을 조회한다.
     *
     * @param  outVo 산출물관리 OutVo
     * @return 산출물관리 목록 List<OutVo>
     * @throws Exception
     */
	public List<OutVo> selectListOut(OutVo outVo) throws Exception;
	
    /**
     * 조회한 산출물관리 전체 카운트
     * 
     * @param  outVo 산출물관리 OutVo
     * @return 산출물관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountOut(OutVo outVo) throws Exception;
	
    /**
     * 산출물관리를 상세 조회한다.
     *
     * @param  outVo 산출물관리 OutVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public OutVo selectOut(OutVo outVo) throws Exception;
		
    /**
     * 산출물관리를 등록 처리 한다.
     *
     * @param  outVo 산출물관리 OutVo
     * @return 번호
     * @throws Exception
     */
	public int insertOut(OutVo outVo) throws Exception;
	
    /**
     * 산출물관리를 갱신 처리 한다.
     *
     * @param  outVo 산출물관리 OutVo
     * @return 번호
     * @throws Exception
     */
	public int updateOut(OutVo outVo) throws Exception;
	
    /**
     * 산출물관리를 삭제 처리 한다.
     *
     * @param  outVo 산출물관리 OutVo
     * @return 번호
     * @throws Exception
     */
	public int deleteOut(OutVo outVo) throws Exception;
	
}
