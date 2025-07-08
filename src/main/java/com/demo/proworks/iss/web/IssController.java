package com.demo.proworks.iss.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.iss.service.IssService;
import com.demo.proworks.iss.vo.IssVo;
import com.demo.proworks.iss.vo.IssListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 이슈리스크관리 관련 처리를 담당하는 컨트롤러
 * @description : 이슈리스크관리 관련 처리를 담당하는 컨트롤러
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
public class IssController {
	
    /** IssService */
    @Resource(name = "issServiceImpl")
    private IssService issService;
	
    
    /**
     * 이슈리스크관리 목록을 조회합니다.
     *
     * @param  issVo 이슈리스크관리
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="ISS001List")
    @RequestMapping(value="ISS001List")    
    @ElDescription(sub="이슈리스크관리 목록조회",desc="페이징을 처리하여 이슈리스크관리 목록 조회를 한다.")               
    public IssListVo selectListIss(IssVo issVo) throws Exception {    	   	

        List<IssVo> issList = issService.selectListIss(issVo);                  
        long totCnt = issService.selectListCountIss(issVo);
	
		IssListVo retIssList = new IssListVo();
		retIssList.setIssVoList(issList); 
		retIssList.setTotalCount(totCnt);
		retIssList.setPageSize(issVo.getPageSize());
		retIssList.setPageIndex(issVo.getPageIndex());

        return retIssList;            
    }  
        
    /**
     * 이슈리스크관리을 단건 조회 처리 한다.
     *
     * @param  issVo 이슈리스크관리
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "ISS001UpdView")    
    @RequestMapping(value="ISS001UpdView") 
    @ElDescription(sub = "이슈리스크관리 갱신 폼을 위한 조회", desc = "이슈리스크관리 갱신 폼을 위한 조회를 한다.")    
    public IssVo selectIss(IssVo issVo) throws Exception {
    	IssVo selectIssVo = issService.selectIss(issVo);    	    
		
        return selectIssVo;
    } 
 
    /**
     * 이슈리스크관리를 등록 처리 한다.
     *
     * @param  issVo 이슈리스크관리
     * @throws Exception
     */
    @ElService(key="ISS001Ins")    
    @RequestMapping(value="ISS001Ins")
    @ElDescription(sub="이슈리스크관리 등록처리",desc="이슈리스크관리를 등록 처리 한다.")
    public void insertIss(IssVo issVo) throws Exception {    	 
    	issService.insertIss(issVo);   
    }
       
    /**
     * 이슈리스크관리를 갱신 처리 한다.
     *
     * @param  issVo 이슈리스크관리
     * @throws Exception
     */
    @ElService(key="ISS001Upd")    
    @RequestMapping(value="ISS001Upd")    
    @ElValidator(errUrl="/iss/issRegister", errContinue=true)
    @ElDescription(sub="이슈리스크관리 갱신처리",desc="이슈리스크관리를 갱신 처리 한다.")    
    public void updateIss(IssVo issVo) throws Exception {  
 
    	issService.updateIss(issVo);                                            
    }

    /**
     * 이슈리스크관리를 삭제 처리한다.
     *
     * @param  issVo 이슈리스크관리    
     * @throws Exception
     */
    @ElService(key = "ISS001Del")    
    @RequestMapping(value="ISS001Del")
    @ElDescription(sub = "이슈리스크관리 삭제처리", desc = "이슈리스크관리를 삭제 처리한다.")    
    public void deleteIss(IssVo issVo) throws Exception {
        issService.deleteIss(issVo);
    }
   
}
