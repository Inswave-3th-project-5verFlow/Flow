package com.demo.proworks.out.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.out.service.OutService;
import com.demo.proworks.out.vo.OutVo;
import com.demo.proworks.out.vo.OutListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 산출물관리 관련 처리를 담당하는 컨트롤러
 * @description : 산출물관리 관련 처리를 담당하는 컨트롤러
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
public class OutController {
	
    /** OutService */
    @Resource(name = "outServiceImpl")
    private OutService outService;
	
    
    /**
     * 산출물관리 목록을 조회합니다.
     *
     * @param  outVo 산출물관리
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="OUT001List")
    @RequestMapping(value="OUT001List")    
    @ElDescription(sub="산출물관리 목록조회",desc="페이징을 처리하여 산출물관리 목록 조회를 한다.")               
    public OutListVo selectListOut(OutVo outVo) throws Exception {    	   	

        List<OutVo> outList = outService.selectListOut(outVo);                  
        long totCnt = outService.selectListCountOut(outVo);
	
		OutListVo retOutList = new OutListVo();
		retOutList.setOutVoList(outList); 
		retOutList.setTotalCount(totCnt);
		retOutList.setPageSize(outVo.getPageSize());
		retOutList.setPageIndex(outVo.getPageIndex());

        return retOutList;            
    }  
        
    /**
     * 산출물관리을 단건 조회 처리 한다.
     *
     * @param  outVo 산출물관리
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "OUT001UpdView")    
    @RequestMapping(value="OUT001UpdView") 
    @ElDescription(sub = "산출물관리 갱신 폼을 위한 조회", desc = "산출물관리 갱신 폼을 위한 조회를 한다.")    
    public OutVo selectOut(OutVo outVo) throws Exception {
    	OutVo selectOutVo = outService.selectOut(outVo);    	    
		
        return selectOutVo;
    } 
 
    /**
     * 산출물관리를 등록 처리 한다.
     *
     * @param  outVo 산출물관리
     * @throws Exception
     */
    @ElService(key="OUT001Ins")    
    @RequestMapping(value="OUT001Ins")
    @ElDescription(sub="산출물관리 등록처리",desc="산출물관리를 등록 처리 한다.")
    public void insertOut(OutVo outVo) throws Exception {    	 
    	outService.insertOut(outVo);   
    }
       
    /**
     * 산출물관리를 갱신 처리 한다.
     *
     * @param  outVo 산출물관리
     * @throws Exception
     */
    @ElService(key="OUT001Upd")    
    @RequestMapping(value="OUT001Upd")    
    @ElValidator(errUrl="/out/outRegister", errContinue=true)
    @ElDescription(sub="산출물관리 갱신처리",desc="산출물관리를 갱신 처리 한다.")    
    public void updateOut(OutVo outVo) throws Exception {  
 
    	outService.updateOut(outVo);                                            
    }

    /**
     * 산출물관리를 삭제 처리한다.
     *
     * @param  outVo 산출물관리    
     * @throws Exception
     */
    @ElService(key = "OUT001Del")    
    @RequestMapping(value="OUT001Del")
    @ElDescription(sub = "산출물관리 삭제처리", desc = "산출물관리를 삭제 처리한다.")    
    public void deleteOut(OutVo outVo) throws Exception {
        outService.deleteOut(outVo);
    }
   
}
