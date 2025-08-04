package com.demo.proworks.wbs.service.impl;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javax.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.demo.proworks.wbs.service.WbsService;
import com.demo.proworks.wbs.service.WbsProgressService;
import com.demo.proworks.wbs.service.WbsStatusPropagationService;
import com.demo.proworks.wbs.vo.WbsVo;
import com.demo.proworks.wbs.dao.WbsDAO;
import com.demo.proworks.wbs.constants.WbsConstants;

/**
 * @subject : WBS 관련 처리를 담당하는 ServiceImpl
 * @description : WBS 관련 처리를 담당하는 ServiceImpl
 * @author : 김성민
 * @since : 2025/07/09
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/09 김성민 최초 생성 
 *               2025/07/19 김성민 STG 테이블 관련 코드 제거 
 *               2025/07/21 김성민 진척률 계산 로직 추가
 *               2025/07/21 김성민 상위/하위 업무 상태 연동 로직 추가
 *               2025/07/22 김성민 de_task_id 제거 프로젝트 적용
 * 
 */
@Service("wbsServiceImpl")
public class WbsServiceImpl implements WbsService {

	/** WbsDAO */
	@Resource(name = "wbsDAO")
	private WbsDAO wbsDAO;

	/** MessageSource */
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	/** WbsProgressService */
	@Resource(name = "wbsProgressServiceImpl")
	private WbsProgressService wbsProgressService;
	
	/** WbsStatusPropagationService */
	@Resource(name = "wbsStatusPropagationServiceImpl")
	private WbsStatusPropagationService wbsStatusPropagationService;

	/**
	 * 전체 WBS 계층 구조를 조회한다.
	 *
	 * @process 1. 전체 WBS 계층 구조를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 전체 WBS 계층 구조 목록
	 * @throws Exception
	 */
	public List<WbsVo> selectListWbsAll(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListWbsAll(wbsVo);
	}

	/**
	 * 전체 WBS 카운트를 조회한다.
	 *
	 * @process 1. 전체 WBS 카운트를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 전체 WBS 카운트
	 * @throws Exception
	 */
	public long selectListCountWbsAll(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListCountWbsAll(wbsVo);
	}

	/**
	 * 검색 조건에 따라 WBS 계층 구조를 조회한다.
	 *
	 * @process 1. 검색 조건에 맞는 WBS 계층 구조를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 검색 조건에 맞는 WBS 계층 구조 목록
	 * @throws Exception
	 */
	@Override
	public List<WbsVo> selectListWbsSearch(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListWbsSearch(wbsVo);
	}

	/**
	 * 검색 조건에 따라 WBS 카운트를 조회한다.
	 *
	 * @process 1. 검색 조건에 맞는 WBS 카운트를 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 검색 조건에 맞는 WBS 카운트
	 * @throws Exception
	 */
	public long selectListCountWbsSearch(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListCountWbsSearch(wbsVo);
	}

	/**
	 * WBS를 상세 조회한다.
	 *
	 * @process 1. WBS를 상세 조회한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public WbsVo selectWbs(WbsVo wbsVo) throws Exception {		
		return wbsDAO.selectWbs(wbsVo);
	}

	/**
	 * WBS를 등록 처리 한다.
	 *
	 * @process 1. WBS를 등록한다.
	 *          2. 등록 후 상위 업무 상태를 자동 조정한다.
	 *          3. 상위 업무의 진척률을 재계산한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws Exception
	 */
	public void insertWbs(WbsVo wbsVo) throws Exception {
		wbsDAO.insertWbs(wbsVo);
		
		// 등록 후 상위 업무 상태 자동 조정 및 진척률 재계산
		try {
			// 1. 상위 업무 상태 조정
			wbsStatusPropagationService.propagateOnChildInsert(wbsVo);
			
			// 2. 상위 업무의 진척률 재계산 (새로운 하위 업무가 추가되었으므로)
			if (wbsVo.getPtTaskId() != null && !wbsVo.getPtTaskId().trim().isEmpty()) {
				wbsProgressService.calculateAndUpdateProgress(wbsVo.getPtTaskId(), wbsVo.getPjtId());
			}
		} catch (Exception e) {
		}
	}

	/**
	 * WBS를 갱신 처리 한다.
	 *
	 * @process 1. 기존 상태를 조회하여 상태 변경 여부를 확인한다.
	 *          2. 상태에 따른 진척률을 자동 조정한다.
	 *          3. WBS를 갱신한다.
	 *          4. 상하위 업무 상태 전파 및 진척률 계산을 실행한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws Exception
	 */
	public void updateWbs(WbsVo wbsVo) throws Exception {
		// 기존 상태 조회
		WbsVo existingTask = wbsDAO.selectWbs(wbsVo);
		String oldStatus = existingTask.getTaskStatus();
		String newStatus = wbsVo.getTaskStatus();
		
		// 상태에 따른 진척률 자동 조정
		adjustRateByStatus(wbsVo);
		
		wbsDAO.updateWbs(wbsVo);
		
		// 상태 변경에 따른 상하위 업무 상태 전파 및 진척률 계산
		try {
			// 하위로 전파 (상위 → 하위)
			wbsStatusPropagationService.propagateOnParentUpdate(wbsVo, oldStatus, newStatus);
			
			// 상위로 전파 (하위 → 상위)
			if (!oldStatus.equals(newStatus) && WbsConstants.TaskStatus.COMPLETED.equals(newStatus)) {
				wbsStatusPropagationService.propagateOnChildUpdate(wbsVo);
			}
			
			// 진척률 계산
			wbsProgressService.calculateAndUpdateProgress(wbsVo.getTaskId(), wbsVo.getPjtId());
		} catch (Exception e) {

		}
	}

	/**
	 * WBS를 삭제 처리 한다.
	 *
	 * @process 1. 삭제 전 업무 정보를 저장한다.
	 *          2. 업무를 삭제한다.
	 *          3. 삭제 후 관련 업무들의 상태 조정 및 진척률 재계산을 실행한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws Exception
	 */
	public void deleteWbs(WbsVo wbsVo) throws Exception {
		// 삭제 전 업무 정보 저장
		WbsVo taskToDelete = null;
		try {
			taskToDelete = wbsDAO.selectWbs(wbsVo);
		} catch (Exception e) {
			// 조회 실패해도 삭제는 진행
		}
		
		// 업무 삭제
		wbsDAO.deleteWbs(wbsVo);
		
		// 삭제 후 관련 업무들의 상태 조정 및 진척률 재계산  
		if (taskToDelete != null) {
			try {
				wbsStatusPropagationService.propagateOnTaskDelete(taskToDelete);
			} catch (Exception e) {

			}
		}
	}


	/**
	 * 진척률을 계산한다.
	 *
	 * @process 1. 새로운 진척률 서비스로 위임하여 진척률을 계산한다.
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 * @throws Exception
	 */
	public void calcProgress(WbsVo wbsVo) throws Exception {
		wbsProgressService.calculateAndUpdateProgress(wbsVo.getTaskId(), wbsVo.getPjtId());
	}

	/**
	 * 상태에 따라 진척률을 자동 조정한다.
	 *
	 * @process 1. 업무 상태에 따라 진척률을 자동 설정한다.
	 *          2. 완료: 100%, 진행중: 50%, 대기: 0%
	 * 
	 * @param  wbsVo WBS 정보 WbsVo
	 */
	private void adjustRateByStatus(WbsVo wbsVo) {
		String status = wbsVo.getTaskStatus();

		if (status != null) {
			if (WbsConstants.TaskStatus.COMPLETED.equals(status.trim())) {
				wbsVo.setTaskRate(String.valueOf(WbsConstants.ProgressRate.COMPLETED_RATE));
			} else if (WbsConstants.TaskStatus.IN_PROGRESS.equals(status.trim())) {
				wbsVo.setTaskRate(String.valueOf(WbsConstants.ProgressRate.IN_PROGRESS_RATE));
			} else if (WbsConstants.TaskStatus.WAITING.equals(status.trim())) {
				wbsVo.setTaskRate(String.valueOf(WbsConstants.ProgressRate.WAITING_RATE));
			}
		}
	}
	
}