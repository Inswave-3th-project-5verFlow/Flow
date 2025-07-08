package com.demo.proworks.defect.service;

import java.util.List;

import com.demo.proworks.defect.vo.DefVo;

/**  
 * @subject     : 테스트결함관리 관련 처리를 담당하는 인터페이스
 * @description : 테스트결함관리 관련 처리를 담당하는 인터페이스
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
public interface DefService {
	
    /**
     * 테스트결함관리 페이징 처리하여 목록을 조회한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 테스트결함관리 목록 List<DefVo>
     * @throws Exception
     */
	public List<DefVo> selectListDef(DefVo defVo) throws Exception;
	
    /**
     * 조회한 테스트결함관리 전체 카운트
     * 
     * @param  defVo 테스트결함관리 DefVo
     * @return 테스트결함관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountDef(DefVo defVo) throws Exception;
	
    /**
     * 테스트결함관리를 상세 조회한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public DefVo selectDef(DefVo defVo) throws Exception;
		
    /**
     * 테스트결함관리를 등록 처리 한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int insertDef(DefVo defVo) throws Exception;
	
    /**
     * 테스트결함관리를 갱신 처리 한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int updateDef(DefVo defVo) throws Exception;
	
    /**
     * 테스트결함관리를 삭제 처리 한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int deleteDef(DefVo defVo) throws Exception;
	
}
