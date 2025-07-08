package com.demo.proworks.test.service;

import java.util.List;

import com.demo.proworks.test.vo.TestVo;

/**  
 * @subject     : 테스트관리 관련 처리를 담당하는 인터페이스
 * @description : 테스트관리 관련 처리를 담당하는 인터페이스
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
public interface TestService {
	
    /**
     * 테스트관리 페이징 처리하여 목록을 조회한다.
     *
     * @param  testVo 테스트관리 TestVo
     * @return 테스트관리 목록 List<TestVo>
     * @throws Exception
     */
	public List<TestVo> selectListTest(TestVo testVo) throws Exception;
	
    /**
     * 조회한 테스트관리 전체 카운트
     * 
     * @param  testVo 테스트관리 TestVo
     * @return 테스트관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountTest(TestVo testVo) throws Exception;
	
    /**
     * 테스트관리를 상세 조회한다.
     *
     * @param  testVo 테스트관리 TestVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public TestVo selectTest(TestVo testVo) throws Exception;
		
    /**
     * 테스트관리를 등록 처리 한다.
     *
     * @param  testVo 테스트관리 TestVo
     * @return 번호
     * @throws Exception
     */
	public int insertTest(TestVo testVo) throws Exception;
	
    /**
     * 테스트관리를 갱신 처리 한다.
     *
     * @param  testVo 테스트관리 TestVo
     * @return 번호
     * @throws Exception
     */
	public int updateTest(TestVo testVo) throws Exception;
	
    /**
     * 테스트관리를 삭제 처리 한다.
     *
     * @param  testVo 테스트관리 TestVo
     * @return 번호
     * @throws Exception
     */
	public int deleteTest(TestVo testVo) throws Exception;
	
}
