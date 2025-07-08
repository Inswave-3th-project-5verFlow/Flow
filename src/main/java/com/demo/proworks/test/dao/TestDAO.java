package com.demo.proworks.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.test.vo.TestVo;
import com.demo.proworks.test.dao.TestDAO;

/**  
 * @subject     : 테스트관리 관련 처리를 담당하는 DAO
 * @description : 테스트관리 관련 처리를 담당하는 DAO
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Repository("testDAO")
public class TestDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 테스트관리 상세 조회한다.
     *  
     * @param  TestVo 테스트관리
     * @return TestVo 테스트관리
     * @throws ElException
     */
    public TestVo selectTest(TestVo vo) throws ElException {
        return (TestVo) selectByPk("com.demo.proworks.test.selectTest", vo);
    }

    /**
     * 페이징을 처리하여 테스트관리 목록조회를 한다.
     *  
     * @param  TestVo 테스트관리
     * @return List<TestVo> 테스트관리
     * @throws ElException
     */
    public List<TestVo> selectListTest(TestVo vo) throws ElException {      	
        return (List<TestVo>)list("com.demo.proworks.test.selectListTest", vo);
    }

    /**
     * 테스트관리 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  TestVo 테스트관리
     * @return 테스트관리 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountTest(TestVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.test.selectListCountTest", vo);
    }
        
    /**
     * 테스트관리를 등록한다.
     *  
     * @param  TestVo 테스트관리
     * @return 번호
     * @throws ElException
     */
    public int insertTest(TestVo vo) throws ElException {    	
        return insert("com.demo.proworks.test.insertTest", vo);
    }

    /**
     * 테스트관리를 갱신한다.
     *  
     * @param  TestVo 테스트관리
     * @return 번호
     * @throws ElException
     */
    public int updateTest(TestVo vo) throws ElException {
        return update("com.demo.proworks.test.updateTest", vo);
    }

    /**
     * 테스트관리를 삭제한다.
     *  
     * @param  TestVo 테스트관리
     * @return 번호
     * @throws ElException
     */
    public int deleteTest(TestVo vo) throws ElException {
        return delete("com.demo.proworks.test.deleteTest", vo);
    }

}
