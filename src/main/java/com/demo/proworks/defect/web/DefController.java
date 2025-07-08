package com.demo.proworks.defect.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.defect.service.DefService;
import com.demo.proworks.defect.vo.DefVo;
import com.demo.proworks.defect.vo.DefListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 테스트결함관리 관련 처리를 담당하는 컨트롤러
 * @description : 테스트결함관리 관련 처리를 담당하는 컨트롤러
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
public class DefController {
	
    /** DefService */
    @Resource(name = "defServiceImpl")
    private DefService defService;
	
    
    /**
     * 테스트결함관리 목록을 조회합니다.
     *
     * @param  defVo 테스트결함관리
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="DEF001List")
    @RequestMapping(value="DEF001List")    
    @ElDescription(sub="테스트결함관리 목록조회",desc="페이징을 처리하여 테스트결함관리 목록 조회를 한다.")               
    public DefListVo selectListDef(DefVo defVo) throws Exception {    	   	

        List<DefVo> defList = defService.selectListDef(defVo);                  
        long totCnt = defService.selectListCountDef(defVo);
	
		DefListVo retDefList = new DefListVo();
		retDefList.setDefVoList(defList); 
		retDefList.setTotalCount(totCnt);
		retDefList.setPageSize(defVo.getPageSize());
		retDefList.setPageIndex(defVo.getPageIndex());

        return retDefList;            
    }  
        
    /**
     * 테스트결함관리을 단건 조회 처리 한다.
     *
     * @param  defVo 테스트결함관리
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "DEF001UpdView")    
    @RequestMapping(value="DEF001UpdView") 
    @ElDescription(sub = "테스트결함관리 갱신 폼을 위한 조회", desc = "테스트결함관리 갱신 폼을 위한 조회를 한다.")    
    public DefVo selectDef(DefVo defVo) throws Exception {
    	DefVo selectDefVo = defService.selectDef(defVo);    	    
		
        return selectDefVo;
    } 
 
    /**
     * 테스트결함관리를 등록 처리 한다.
     *
     * @param  defVo 테스트결함관리
     * @throws Exception
     */
    @ElService(key="DEF001Ins")    
    @RequestMapping(value="DEF001Ins")
    @ElDescription(sub="테스트결함관리 등록처리",desc="테스트결함관리를 등록 처리 한다.")
    public void insertDef(DefVo defVo) throws Exception {    	 
    	defService.insertDef(defVo);   
    }
       
    /**
     * 테스트결함관리를 갱신 처리 한다.
     *
     * @param  defVo 테스트결함관리
     * @throws Exception
     */
    @ElService(key="DEF001Upd")    
    @RequestMapping(value="DEF001Upd")    
    @ElValidator(errUrl="/def/defRegister", errContinue=true)
    @ElDescription(sub="테스트결함관리 갱신처리",desc="테스트결함관리를 갱신 처리 한다.")    
    public void updateDef(DefVo defVo) throws Exception {  
 
    	defService.updateDef(defVo);                                            
    }

    /**
     * 테스트결함관리를 삭제 처리한다.
     *
     * @param  defVo 테스트결함관리    
     * @throws Exception
     */
    @ElService(key = "DEF001Del")    
    @RequestMapping(value="DEF001Del")
    @ElDescription(sub = "테스트결함관리 삭제처리", desc = "테스트결함관리를 삭제 처리한다.")    
    public void deleteDef(DefVo defVo) throws Exception {
        defService.deleteDef(defVo);
    }
   
}
