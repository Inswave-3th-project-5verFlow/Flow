package com.demo.proworks.dept.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.dept.service.DeptService;
import com.demo.proworks.dept.vo.DeptVo;
import com.demo.proworks.dept.vo.DeptListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 부서정보 관련 처리를 담당하는 컨트롤러
 * @description : 부서정보 관련 처리를 담당하는 컨트롤러
 * @author      : 백승호
 * @since       : 2025/06/10
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/10			 백승호	 		최초 생성
 * 
 */
@Controller
public class DeptController {
	
    /** DeptService */
    @Resource(name = "deptServiceImpl")
    private DeptService deptService;
	
    
    /**
     * 부서정보 목록을 조회합니다.
     *
     * @param  deptVo 부서정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="SVC001List")
    @RequestMapping(value="SVC001List")    
    @ElDescription(sub="부서정보 목록조회",desc="페이징을 처리하여 부서정보 목록 조회를 한다.")               
    public DeptListVo selectListDept(DeptVo deptVo) throws Exception {    	   	

        List<DeptVo> deptList = deptService.selectListDept(deptVo);                  
        long totCnt = deptService.selectListCountDept(deptVo);
	
		DeptListVo retDeptList = new DeptListVo();
		retDeptList.setDeptVoList(deptList); 
		retDeptList.setTotalCount(totCnt);
		retDeptList.setPageSize(deptVo.getPageSize());
		retDeptList.setPageIndex(deptVo.getPageIndex());

        return retDeptList;            
    }  
        
    /**
     * 부서정보을 단건 조회 처리 한다.
     *
     * @param  deptVo 부서정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "SVC001UpdView")    
    @RequestMapping(value="SVC001UpdView") 
    @ElDescription(sub = "부서정보 갱신 폼을 위한 조회", desc = "부서정보 갱신 폼을 위한 조회를 한다.")    
    public DeptVo selectDept(DeptVo deptVo) throws Exception {
    	DeptVo selectDeptVo = deptService.selectDept(deptVo);    	    
		
        return selectDeptVo;
    } 
 
    /**
     * 부서정보를 등록 처리 한다.
     *
     * @param  deptVo 부서정보
     * @throws Exception
     */
    @ElService(key="SVC001Ins")    
    @RequestMapping(value="SVC001Ins")
    @ElDescription(sub="부서정보 등록처리",desc="부서정보를 등록 처리 한다.")
    public void insertDept(DeptVo deptVo) throws Exception {    	 
    	deptService.insertDept(deptVo);   
    }
    
     /**
		Insert 복사본
     * 부서정보를 등록 처리 한다.
     *
     * @param  deptVo 부서정보
     * @throws Exception
     */
    @ElService(key = "SVC001InsTest")    
    @RequestMapping(value = "SVC001InsTest")
    @ElDescription(sub = "부서정보 등록처리", desc = "부서정보를 등록 처리 한다.")
    public void insertDeptTest(DeptListVo deptVoList) throws Exception {    
    	 System.out.println("입력된 dept : " + deptVoList.getDeptVoList().toString());

    	 int cnt = deptVoList.getDeptVoList().size();   
    	 
    	 for(int i=0; i<cnt; i++){
    		 deptService.insertDept(deptVoList.getDeptVoList().get(i));
    	 }
    	 
    	 
    	
    }
       
    /**
     * 부서정보를 갱신 처리 한다.
     *
     * @param  deptVo 부서정보
     * @throws Exception
     */
    @ElService(key="SVC001Upd")    
    @RequestMapping(value="SVC001Upd")    
    @ElValidator(errUrl="/dept/deptRegister", errContinue=true)
    @ElDescription(sub="부서정보 갱신처리",desc="부서정보를 갱신 처리 한다.")    
    public void updateDept(DeptVo deptVo) throws Exception {  
 
    	deptService.updateDept(deptVo);                                            
    }
    
    /**
     * 부서정보를 갱신 처리 한다.
     *
     * @param  deptVo 부서정보
     * @throws Exception
     */
    @ElService(key = "SVC001modiTest")    
    @RequestMapping(value = "SVC001modiTest")    
    @ElDescription(sub = "부서정보 갱신처리", desc = "부서정보를 갱신 처리 한다.")    
    public void updateDeptTest(DeptListVo deptVoList) throws Exception {  
    	 System.out.println("수정된 dept : " + deptVoList.getDeptVoList().toString());
//    	deptService.updateDept(deptVo);   

    	 int cnt = deptVoList.getDeptVoList().size();
    	 
    	 for(int i=0; i<cnt; i++){
    		 deptService.updateDept(deptVoList.getDeptVoList().get(i));
    	 }
    	 
    }

    /**
     * 부서정보를 삭제 처리한다.
     *
     * @param  deptVo 부서정보    
     * @throws Exception
     */
    @ElService(key = "SVC001Del")    
    @RequestMapping(value="SVC001Del")
    @ElDescription(sub = "부서정보 삭제처리", desc = "부서정보를 삭제 처리한다.")    
    public void deleteDept(DeptVo deptVo) throws Exception {
        deptService.deleteDept(deptVo);
    }
   
   
   // SVC0001DelTest
       @ElService(key = "SVC0001DelTest")    
    @RequestMapping(value = "SVC0001DelTest")    
    @ElDescription(sub = "부서정보 갱신처리", desc = "부서정보를 갱신 처리 한다.")    
    public void deleteDeptTest(DeptListVo deptVoList) throws Exception {  
    	 System.out.println("삭제된 dept : " + deptVoList.getDeptVoList().toString());

    	 int cnt = deptVoList.getDeptVoList().size();
    	 
    	 for(int i=0; i<cnt; i++){
    		 deptService.deleteDept(deptVoList.getDeptVoList().get(i));
    	 }
    	 
    }
    
       @ElService(key = "SVC001modifyTest")    
       @RequestMapping(value = "SVC001modifyTest")    
       @ElDescription(sub = "부서정보 갱신처리", desc = "부서정보를 갱신 처리 한다.")    
       public void modifyDeptTest(DeptListVo deptVoList) throws Exception {  
    	   System.out.println("modified dept : " + deptVoList.getDeptVoList().toString());
    	   
    	   int cnt = deptVoList.getDeptVoList().size();
    	   
    	   for(int i=0; i<cnt; i++){
    		   String rowStatus = deptVoList.getDeptVoList().get(i).getRowStatus();
    		   if(rowStatus.equals("C")) deptService.insertDept(deptVoList.getDeptVoList().get(i));
    		   else if(rowStatus.equals("D")) deptService.deleteDept(deptVoList.getDeptVoList().get(i));
    		   else if(rowStatus.equals("U")) deptService.updateDept(deptVoList.getDeptVoList().get(i));
    	   }
    	   
       }
}
