package com.demo.proworks.users.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.users.service.UsersService;
import com.demo.proworks.users.vo.UsersListVo;
import com.demo.proworks.users.vo.UsersVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.login.LoginInfo;
import com.inswave.elfw.login.LoginProcessor;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 사용자정보 관련 처리를 담당하는 컨트롤러
 * @description : 사용자정보 관련 처리를 담당하는 컨트롤러
 * @author      : Inswave
 * @since       : 2025/06/24
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/24			 Inswave	 		최초 생성
 * 
 */
@Controller
public class UsersController {
	
    /** UsersService */
    @Resource(name = "usersServiceImpl")
    private UsersService usersService;
	
    @Resource(name = "loginProcess")
	protected LoginProcessor loginProcess;
    
	/**
	 * 로그인을 처리한다.
	 * @param loginVo 로그인 정보 LoginVo
	 * @param request 요청 정보 HttpServletRequest
	 * @throws Exception
	 */
	@ElService(key = "Login")
    @RequestMapping(value = "Login")
    @ElDescription(sub = "로그인", desc = "로그인을 처리한다.")
    public void login(com.demo.proworks.emp.vo.LoginVo loginVo, HttpServletRequest request) throws Exception {
    	String id = loginVo.getId();
    	String pw = loginVo.getPw();
    	LoginInfo info = loginProcess.processLogin(request, id, pw);

    	AppLog.debug("- Login 정보 : " + info.toString());
    }
	
	/**
	 * 로그인 폼 페이지를 로드한다.
	 * @param loginVo 로그인 정보 LoginVo
	 * @param request 요청 정보 HttpServletRequest
	 * @throws Exception
	 */
	@ElService(key = "LoginFrm")    
    @RequestMapping(value = "LoginFrm")   
    @ElDescription(sub = "로그인 폼 페이지 로드", desc = "로그인 폼 페이지를 로드한다.")           
    public void loginFrm(com.demo.proworks.emp.vo.LoginVo loginVo, HttpServletRequest request) throws Exception {    
		String id = loginVo.getId();
		
		loginProcess.processLogout(request, id);
    }   

    
    /**
     * 사용자정보 목록을 조회합니다.
     *
     * @param  usersVo 사용자정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="USERS0001List")
    @RequestMapping(value="USERS0001List")    
    @ElDescription(sub="사용자정보 목록조회",desc="페이징을 처리하여 사용자정보 목록 조회를 한다.")               
    public UsersListVo selectListUsers(UsersVo usersVo) throws Exception {    	   	
        List<UsersVo> usersList = usersService.selectListUsers(usersVo);                  
        long totCnt = usersService.selectListCountUsers(usersVo);
	
		UsersListVo retUsersList = new UsersListVo();
		retUsersList.setUsersVoList(usersList); 
		retUsersList.setTotalCount(totCnt);
		retUsersList.setPageSize(usersVo.getPageSize());
		retUsersList.setPageIndex(usersVo.getPageIndex());

        return retUsersList;            
    }  
        
    /**
     * 사용자정보을 단건 조회 처리 한다.
     *
     * @param  usersVo 사용자정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "USERS0001UpdView")    
    @RequestMapping(value = "USERS0001UpdView") 
    @ElDescription(sub = "사용자정보 갱신 폼을 위한 조회", desc = "사용자정보 갱신 폼을 위한 조회를 한다.")    
    public UsersVo selectUsers(UsersVo usersVo) throws Exception {
    	UsersVo selectUsersVo = usersService.selectUsers(usersVo);    	    
		
        return selectUsersVo;
    } 
 
    /**
     * 사용자정보를 등록 처리 한다.
     *
     * @param  usersVo 사용자정보
     * @throws Exception
     */
    @ElService(key="USERS0001Ins")    
    @RequestMapping(value="USERS0001Ins")
    @ElDescription(sub="사용자정보 등록처리",desc="사용자정보를 등록 처리 한다.")
    public void insertUsers(UsersVo usersVo) throws Exception {    	 
    	usersService.insertUsers(usersVo);   
    }
       
    /**
     * 사용자정보를 갱신 처리 한다.
     *
     * @param  usersVo 사용자정보
     * @throws Exception
     */
    @ElService(key="USERS0001Upd")    
    @RequestMapping(value="USERS0001Upd")    
    @ElValidator(errUrl="/users/usersRegister", errContinue=true)
    @ElDescription(sub="사용자정보 갱신처리",desc="사용자정보를 갱신 처리 한다.")    
    public void updateUsers(UsersVo usersVo) throws Exception {  
 
    	usersService.updateUsers(usersVo);                                            
    }

    /**
     * 사용자정보를 삭제 처리한다.
     *
     * @param  usersVo 사용자정보    
     * @throws Exception
     */
    @ElService(key = "USERS0001Del")    
    @RequestMapping(value="USERS0001Del")
    @ElDescription(sub = "사용자정보 삭제처리", desc = "사용자정보를 삭제 처리한다.")    
    public void deleteUsers(UsersVo usersVo) throws Exception {
        usersService.deleteUsers(usersVo);
    }
    
    /**
     * 사용자정보, 프로젝트ID, 그룹ID 매핑한다.
     *
     * @param  usersVo 사용자정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key = "USERS0001ListMapping")
    @RequestMapping(value = "USERS0001ListMapping")    
    @ElDescription(sub = "사용자정보, 프로젝트ID, 그룹ID 매핑", desc = "사용자정보, 프로젝트ID, 그룹ID 매핑한다.")               
    public UsersListVo selectUsersMapping(UsersVo usersVo) throws Exception {    	   	
        List<UsersVo> usersListMapping = usersService.selectUsersMapping(usersVo);                  

        long totCnt = usersService.selectListCountUsers(usersVo);
	
		UsersListVo retUsersList = new UsersListVo();
		retUsersList.setUsersVoList(usersListMapping); 
		retUsersList.setTotalCount(totCnt);
		retUsersList.setPageSize(usersVo.getPageSize());
		retUsersList.setPageIndex(usersVo.getPageIndex());

        return retUsersList;     
       
    }  

    
   
}
