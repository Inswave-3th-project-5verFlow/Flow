package com.demo.proworks.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.test.service.TestService;
import com.demo.proworks.test.vo.TestVo;
import com.demo.proworks.test.dao.TestDAO;

/**  
 * @subject     : 테스트관리 관련 처리를 담당하는 ServiceImpl
 * @description	: 테스트관리 관련 처리를 담당하는 ServiceImpl
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {

    @Resource(name="testDAO")
    private TestDAO testDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 테스트관리 목록을 조회합니다.
     *
     * @process
     * 1. 테스트관리 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<TestVo>을(를) 리턴한다.
     * 
     * @param  testVo 테스트관리 TestVo
     * @return 테스트관리 목록 List<TestVo>
     * @throws Exception
     */
	public List<TestVo> selectListTest(TestVo testVo) throws Exception {
		List<TestVo> list = testDAO.selectListTest(testVo);	
	
		return list;
	}

    /**
     * 조회한 테스트관리 전체 카운트
     *
     * @process
     * 1. 테스트관리 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  testVo 테스트관리 TestVo
     * @return 테스트관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountTest(TestVo testVo) throws Exception {
		return testDAO.selectListCountTest(testVo);
	}

    /**
     * 테스트관리를 상세 조회한다.
     *
     * @process
     * 1. 테스트관리를 상세 조회한다.
     * 2. 결과 TestVo을(를) 리턴한다.
     * 
     * @param  testVo 테스트관리 TestVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public TestVo selectTest(TestVo testVo) throws Exception {
		TestVo resultVO = testDAO.selectTest(testVo);			
        
        return resultVO;
	}

    /**
     * 테스트관리를 등록 처리 한다.
     *
     * @process
     * 1. 테스트관리를 등록 처리 한다.
     * 
     * @param  testVo 테스트관리 TestVo
     * @return 번호
     * @throws Exception
     */
	public int insertTest(TestVo testVo) throws Exception {
		return testDAO.insertTest(testVo);	
	}
	
    /**
     * 테스트관리를 갱신 처리 한다.
     *
     * @process
     * 1. 테스트관리를 갱신 처리 한다.
     * 
     * @param  testVo 테스트관리 TestVo
     * @return 번호
     * @throws Exception
     */
	public int updateTest(TestVo testVo) throws Exception {				
		return testDAO.updateTest(testVo);	   		
	}

    /**
     * 테스트관리를 삭제 처리 한다.
     *
     * @process
     * 1. 테스트관리를 삭제 처리 한다.
     * 
     * @param  testVo 테스트관리 TestVo
     * @return 번호
     * @throws Exception
     */
	public int deleteTest(TestVo testVo) throws Exception {
		return testDAO.deleteTest(testVo);
	}
	
}
