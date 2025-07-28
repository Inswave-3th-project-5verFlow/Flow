package com.demo.proworks.def.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.def.service.CmtService;
import com.demo.proworks.def.vo.CmtVo;
import com.demo.proworks.def.vo.CmtListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 코멘트 관리 관련 처리를 담당하는 컨트롤러
 * @description : 코멘트 관리 관련 처리를 담당하는 컨트롤러
 * @author      : 우민지
 * @since       : 2025/07/27
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/27			 우민지	 		최초 생성
 * 
 */
@Controller
public class CmtController {
	
    /** CmtService */
    @Resource(name = "cmtServiceImpl")
    private CmtService cmtService;
	
    
    /**
     * 코멘트 관리 목록을 조회합니다.
     *
     * @param  cmtVo 코멘트 관리
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="CMT001List")
    @RequestMapping(value="CMT001List")    
    @ElDescription(sub="코멘트 관리 목록조회",desc="페이징을 처리하여 코멘트 관리 목록 조회를 한다.")               
    public CmtListVo selectListCmt(CmtVo cmtVo) throws Exception {    	   	

        List<CmtVo> cmtList = cmtService.selectListCmt(cmtVo);                  
        long totCnt = cmtService.selectListCountCmt(cmtVo);
	
		CmtListVo retCmtList = new CmtListVo();
		retCmtList.setCmtVoList(cmtList); 
		retCmtList.setTotalCount(totCnt);
		retCmtList.setPageSize(cmtVo.getPageSize());
		retCmtList.setPageIndex(cmtVo.getPageIndex());

        return retCmtList;            
    }  
        
    /**
     * 코멘트 관리을 단건 조회 처리 한다.
     *
     * @param  cmtVo 코멘트 관리
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "CMT001UpdView")    
    @RequestMapping(value="CMT001UpdView") 
    @ElDescription(sub = "코멘트 관리 갱신 폼을 위한 조회", desc = "코멘트 관리 갱신 폼을 위한 조회를 한다.")    
    public CmtVo selectCmt(CmtVo cmtVo) throws Exception {
    	CmtVo selectCmtVo = cmtService.selectCmt(cmtVo);    	    
		
        return selectCmtVo;
    } 
 
    /**
     * 코멘트 관리를 등록 처리 한다.
     *
     * @param  cmtVo 코멘트 관리
     * @throws Exception
     */
    @ElService(key="CMT001Ins")    
    @RequestMapping(value="CMT001Ins")
    @ElDescription(sub="코멘트 관리 등록처리",desc="코멘트 관리를 등록 처리 한다.")
    public void insertCmt(CmtVo cmtVo) throws Exception {    	 
    	cmtService.insertCmt(cmtVo);   
    }
       
    /**
     * 코멘트 관리를 갱신 처리 한다.
     *
     * @param  cmtVo 코멘트 관리
     * @throws Exception
     */
    @ElService(key="CMT001Upd")    
    @RequestMapping(value="CMT001Upd")    
    @ElValidator(errUrl="/cmt/cmtRegister", errContinue=true)
    @ElDescription(sub="코멘트 관리 갱신처리",desc="코멘트 관리를 갱신 처리 한다.")    
    public void updateCmt(CmtVo cmtVo) throws Exception {  
 
    	cmtService.updateCmt(cmtVo);                                            
    }

    /**
     * 코멘트 관리를 삭제 처리한다.
     *
     * @param  cmtVo 코멘트 관리    
     * @throws Exception
     */
    @ElService(key = "CMT001Del")    
    @RequestMapping(value="CMT001Del")
    @ElDescription(sub = "코멘트 관리 삭제처리", desc = "코멘트 관리를 삭제 처리한다.")    
    public void deleteCmt(CmtVo cmtVo) throws Exception {
        cmtService.deleteCmt(cmtVo);
    }
   
}
