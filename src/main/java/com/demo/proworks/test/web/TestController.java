package com.demo.proworks.test.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.test.service.TestService;
import com.demo.proworks.test.vo.TestVo;
import com.demo.proworks.test.vo.TestListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 테스트관리 관련 처리를 담당하는 컨트롤러
 * @description : 테스트관리 관련 처리를 담당하는 컨트롤러
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Controller
public class TestController {
	
    /** TestService */
    @Resource(name = "testServiceImpl")
    private TestService testService;
	
    
    /**
     * 테스트관리 목록을 조회합니다.
     *
     * @param  testVo 테스트관리
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="TEST001List")
    @RequestMapping(value="TEST001List")    
    @ElDescription(sub="테스트관리 목록조회",desc="페이징을 처리하여 테스트관리 목록 조회를 한다.")               
    public TestListVo selectListTest(TestVo testVo) throws Exception {    	   	

        List<TestVo> testList = testService.selectListTest(testVo);                  
        long totCnt = testService.selectListCountTest(testVo);
	
		TestListVo retTestList = new TestListVo();
		retTestList.setTestVoList(testList); 
		retTestList.setTotalCount(totCnt);
		retTestList.setPageSize(testVo.getPageSize());
		retTestList.setPageIndex(testVo.getPageIndex());

        return retTestList;            
    }  
        
    /**
     * 테스트관리을 단건 조회 처리 한다.
     *
     * @param  testVo 테스트관리
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "TEST001UpdView")    
    @RequestMapping(value="TEST001UpdView") 
    @ElDescription(sub = "테스트관리 갱신 폼을 위한 조회", desc = "테스트관리 갱신 폼을 위한 조회를 한다.")    
    public TestVo selectTest(TestVo testVo) throws Exception {
    	TestVo selectTestVo = testService.selectTest(testVo);    	    
		
        return selectTestVo;
    } 
 
    /**
     * 테스트관리를 등록 처리 한다.
     *
     * @param  testVo 테스트관리
     * @throws Exception
     */
    @ElService(key="TEST001Ins")    
    @RequestMapping(value="TEST001Ins")
    @ElDescription(sub="테스트관리 등록처리",desc="테스트관리를 등록 처리 한다.")
    public void insertTest(TestVo testVo) throws Exception {    	 
    	testService.insertTest(testVo);   
    }
       
    /**
     * 테스트관리를 갱신 처리 한다.
     *
     * @param  testVo 테스트관리
     * @throws Exception
     */
    @ElService(key="TEST001Upd")    
    @RequestMapping(value="TEST001Upd")    
    @ElValidator(errUrl="/test/testRegister", errContinue=true)
    @ElDescription(sub="테스트관리 갱신처리",desc="테스트관리를 갱신 처리 한다.")    
    public void updateTest(TestVo testVo) throws Exception {  
 
    	testService.updateTest(testVo);                                            
    }

    /**
     * 테스트관리를 삭제 처리한다.
     *
     * @param  testVo 테스트관리    
     * @throws Exception
     */
    @ElService(key = "TEST001Del")    
    @RequestMapping(value="TEST001Del")
    @ElDescription(sub = "테스트관리 삭제처리", desc = "테스트관리를 삭제 처리한다.")    
    public void deleteTest(TestVo testVo) throws Exception {
        testService.deleteTest(testVo);
    }
   
}
