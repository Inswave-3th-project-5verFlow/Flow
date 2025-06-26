package com.demo.proworks.task.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.task.service.TaskService;
import com.demo.proworks.task.vo.TaskVo;
import com.demo.proworks.task.vo.TaskListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 업무정보 관련 처리를 담당하는 컨트롤러
 * @description : 업무정보 관련 처리를 담당하는 컨트롤러
 * @author      : 백승호
 * @since       : 2025/06/24
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/24			 백승호	 		최초 생성
 * 
 */
@Controller
public class TaskController {
	
    /** TaskService */
    @Resource(name = "taskServiceImpl")
    private TaskService taskService;
	
    
    /**
     * 업무정보 목록을 조회합니다.
     *
     * @param  taskVo 업무정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="TASK001List")
    @RequestMapping(value="TASK001List")    
    @ElDescription(sub="업무정보 목록조회",desc="페이징을 처리하여 업무정보 목록 조회를 한다.")               
    public TaskListVo selectListTask(TaskVo taskVo) throws Exception {    	   	

        List<TaskVo> taskList = taskService.selectListTask(taskVo);                  
        long totCnt = taskService.selectListCountTask(taskVo);
	
		TaskListVo retTaskList = new TaskListVo();
		retTaskList.setTaskVoList(taskList); 
		retTaskList.setTotalCount(totCnt);
		retTaskList.setPageSize(taskVo.getPageSize());
		retTaskList.setPageIndex(taskVo.getPageIndex());

        return retTaskList;            
    }  
        
    /**
     * 업무정보을 단건 조회 처리 한다.
     *
     * @param  taskVo 업무정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "TASK001UpdView")    
    @RequestMapping(value="TASK001UpdView") 
    @ElDescription(sub = "업무정보 갱신 폼을 위한 조회", desc = "업무정보 갱신 폼을 위한 조회를 한다.")    
    public TaskVo selectTask(TaskVo taskVo) throws Exception {
    	TaskVo selectTaskVo = taskService.selectTask(taskVo);    	    
		
        return selectTaskVo;
    } 
 
    /**
     * 업무정보를 등록 처리 한다.
     *
     * @param  taskVo 업무정보
     * @throws Exception
     */
    @ElService(key="TASK001Ins")    
    @RequestMapping(value="TASK001Ins")
    @ElDescription(sub="업무정보 등록처리",desc="업무정보를 등록 처리 한다.")
    public void insertTask(TaskVo taskVo) throws Exception {    	 
    	taskService.insertTask(taskVo);   
    }
       
    /**
     * 업무정보를 갱신 처리 한다.
     *
     * @param  taskVo 업무정보
     * @throws Exception
     */
    @ElService(key="TASK001Upd")    
    @RequestMapping(value="TASK001Upd")    
    @ElValidator(errUrl="/task/taskRegister", errContinue=true)
    @ElDescription(sub="업무정보 갱신처리",desc="업무정보를 갱신 처리 한다.")    
    public void updateTask(TaskVo taskVo) throws Exception {  
 
    	taskService.updateTask(taskVo);                                            
    }

    /**
     * 업무정보를 삭제 처리한다.
     *
     * @param  taskVo 업무정보    
     * @throws Exception
     */
    @ElService(key = "TASK001Del")    
    @RequestMapping(value="TASK001Del")
    @ElDescription(sub = "업무정보 삭제처리", desc = "업무정보를 삭제 처리한다.")    
    public void deleteTask(TaskVo taskVo) throws Exception {
        taskService.deleteTask(taskVo);
    }
   
}
