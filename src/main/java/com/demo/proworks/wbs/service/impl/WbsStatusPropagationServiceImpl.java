package com.demo.proworks.wbs.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.demo.proworks.wbs.constants.WbsConstants;
import com.demo.proworks.wbs.dao.WbsDAO;
import com.demo.proworks.wbs.service.WbsStatusPropagationService;
import com.demo.proworks.wbs.service.WbsProgressService;
import com.demo.proworks.wbs.vo.WbsVo;
import com.inswave.elfw.log.AppLog;

/**
 * @subject : WBS 상태 전파 서비스 구현체
 * @description : WBS 상태 전파 서비스 구현체
 * @author : 김성민
 * @since : 2025/07/24
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/24 김성민 최초 생성
 * 
 */
@Service("wbsStatusPropagationServiceImpl")
public class WbsStatusPropagationServiceImpl implements WbsStatusPropagationService {

	/** WbsDAO */
	@Resource(name = "wbsDAO")
	private WbsDAO wbsDAO;

	/** WbsProgressService */
	@Resource(name = "wbsProgressServiceImpl")
	private WbsProgressService wbsProgressService;

	/**
	 * 하위 업무 추가 시 상위 업무 상태를 자동 조정한다.
	 *
	 * @process 1. 상위 업무 정보를 조회한다.
	 *          2. 상위 업무가 대기 상태면 진행중으로 변경한다.
	 *          3. 상위 업무가 완료 상태면 진행중으로 변경한다.
	 * 
	 * @param  childTask 추가된 하위 업무 WbsVo
	 */
	@Override
	public void propagateOnChildInsert(WbsVo childTask) {
		try {
			String parentTaskId = childTask.getPtTaskId();

			if (parentTaskId == null || parentTaskId.trim().isEmpty()) {
				return; // 최상위 업무인 경우 처리하지 않음
			}

			// 상위 업무 정보 조회
			WbsVo parentParam = new WbsVo();
			parentParam.setTaskId(parentTaskId);
			parentParam.setPjtId(childTask.getPjtId());
			WbsVo parentTask = wbsDAO.selectWbs(parentParam);

			if (parentTask == null) {
				AppLog.debug("상위 업무를 찾을 수 없음: " + parentTaskId);
				return;
			}

			String parentStatus = parentTask.getTaskStatus();
			boolean statusChanged = false;

			// 규칙1: 상위 업무가 대기 상태면 진행중으로 변경
			if (WbsConstants.TaskStatus.WAITING.equals(parentStatus)) {
				parentTask.setTaskStatus(WbsConstants.TaskStatus.IN_PROGRESS);
				// 진척률은 calculateAndUpdateProgress에서 계산하도록 함
				statusChanged = true;
				AppLog.debug(String.format("상위 업무 %s: 대기 → 진행중 (하위업무 추가)", parentTaskId));
			}
			// 규칙2: 상위 업무가 완료 상태면 진행중으로 변경
			else if (WbsConstants.TaskStatus.COMPLETED.equals(parentStatus)) {
				parentTask.setTaskStatus(WbsConstants.TaskStatus.IN_PROGRESS);
				// 진척률은 calculateAndUpdateProgress에서 계산하도록 함
				statusChanged = true;
				AppLog.debug(String.format("상위 업무 %s: 완료 → 진행중 (하위업무 추가)", parentTaskId));
			}

			// 상태가 변경된 경우에만 업데이트 (진척률은 별도로 계산됨)
			if (statusChanged) {
				wbsDAO.updateWbs(parentTask);
			}

		} catch (Exception e) {
			AppLog.debug("하위 업무 추가 시 상위 업무 상태 조정 실패: " + e.getMessage());
		}
	}

	/**
	 * 상위 업무 상태 변경 시 하위 업무들의 상태를 자동 조정한다.
	 *
	 * @process 1. 상태 변경 여부를 확인한다.
	 *          2. 상위 업무가 진행중에서 완료로 변경되면 모든 하위 업무를 완료로 변경한다.
	 * 
	 * @param  parentTask 상태가 변경된 상위 업무 WbsVo
	 * @param  oldStatus 변경 전 상태
	 * @param  newStatus 변경 후 상태
	 */
	@Override
	public void propagateOnParentUpdate(WbsVo parentTask, String oldStatus, String newStatus) {
		try {
			// 상태 변경이 없으면 처리하지 않음
			if (oldStatus != null && oldStatus.equals(newStatus)) {
				return;
			}

			String parentTaskId = parentTask.getTaskId();
			String projectId = parentTask.getPjtId();

			// 규칙3: 상위업무 진행중 → 완료로 변경 시 하위 업무 모두 완료 처리
			// (설계업무인 경우 연관 개발업무도 포함하여 처리됨)
			if (WbsConstants.TaskStatus.IN_PROGRESS.equals(oldStatus)
					&& WbsConstants.TaskStatus.COMPLETED.equals(newStatus)) {

				updateAllChildrenStatus(parentTaskId, projectId, WbsConstants.TaskStatus.COMPLETED);
				AppLog.debug(String.format("상위 업무 %s 완료로 인해 모든 하위 업무를 완료 처리", parentTaskId));
			}

		} catch (Exception e) {
			AppLog.debug("상위 업무 변경 시 하위 업무 상태 조정 실패: " + e.getMessage());
		}
	}

	/**
	 * 하위 업무 상태 변경이 상위 업무에 미치는 영향을 처리한다.
	 *
	 * @process 1. 상위 업무 정보를 조회한다.
	 *          2. 모든 하위 업무가 완료되었는지 확인한다.
	 *          3. 모든 하위 업무가 완료되면 상위 업무도 완료로 변경한다.
	 *          4. 재귀적으로 상위의 상위 업무도 처리한다.
	 * 
	 * @param  childTask 상태가 변경된 하위 업무 WbsVo
	 */
	@Override
	public void propagateOnChildUpdate(WbsVo childTask) {
		try {
			String parentTaskId = childTask.getPtTaskId();

			if (parentTaskId == null || parentTaskId.trim().isEmpty()) {
				return; // 최상위 업무인 경우 처리하지 않음
			}

			String projectId = childTask.getPjtId();

			// 상위 업무 정보 가져오기
			WbsVo parentParam = new WbsVo();
			parentParam.setTaskId(parentTaskId);
			parentParam.setPjtId(projectId);
			WbsVo parentTask = wbsDAO.selectWbs(parentParam);

			if (parentTask == null || WbsConstants.TaskStatus.COMPLETED.equals(parentTask.getTaskStatus())) {
				return; // 상위 업무가 없거나 이미 완료 상태면 처리하지 않음
			}

			// 해당 상위 업무의 모든 하위 업무가 완료인지 확인
			boolean allChildrenCompleted = areAllChildrenCompleted(parentTaskId, projectId);

			if (allChildrenCompleted) {
				AppLog.debug(String.format("모든 하위 업무가 완료되어 상위 업무 %s 상태를 완료로 변경", parentTaskId));
				parentTask.setTaskStatus(WbsConstants.TaskStatus.COMPLETED);
				adjustRateByStatus(parentTask);
				wbsDAO.updateWbs(parentTask);

				// 재귀적으로 상위의 상위 업무도 처리
				propagateOnChildUpdate(parentTask);
			}

		} catch (Exception e) {
			AppLog.debug("하위 업무 변경 시 상위 업무 상태 조정 실패: " + e.getMessage());
		}
	}

	/**
	 * 업무 삭제 시 관련 업무들의 상태를 조정한다.
	 *
	 * @process 1. 삭제된 업무의 상위 업무들의 진척률을 재계산한다.
	 * 
	 * @param  deletedTask 삭제된 업무 WbsVo
	 */
	@Override
	public void propagateOnTaskDelete(WbsVo deletedTask) {
		try {
			String parentTaskId = deletedTask.getPtTaskId();
			String projectId = deletedTask.getPjtId();

			// 상위 업무가 있으면 진척률 재계산
			if (parentTaskId != null && !parentTaskId.trim().isEmpty()) {
				wbsProgressService.calculateAndUpdateProgress(parentTaskId, projectId);
				AppLog.debug(String.format("업무 삭제로 인한 상위업무 %s 진척률 재계산 완료", parentTaskId));
			}

		} catch (Exception e) {
			AppLog.debug("업무 삭제 시 상태 조정 실패: " + e.getMessage());
		}
	}

	/**
	 * 모든 하위 업무의 상태를 변경한다.
	 *
	 * @param  parentTaskId 상위 업무 ID
	 * @param  projectId 프로젝트 ID
	 * @param  newStatus 새로운 상태
	 */
	private void updateAllChildrenStatus(String parentTaskId, String projectId, String newStatus) {
		try {
			// pt_task_id로 연결된 모든 하위 업무 조회
			WbsVo childParam = new WbsVo();
			childParam.setPtTaskId(parentTaskId);
			childParam.setPjtId(projectId);
			List<WbsVo> allChildList = wbsDAO.selectAllChildrenByParent(childParam);

			if (allChildList != null) {
				for (WbsVo child : allChildList) {
					// 하위 업무 상태 변경
					updateTaskStatus(child, newStatus);
					// 재귀적으로 하위 업무들 처리
					updateAllChildrenStatus(child.getTaskId(), projectId, newStatus);
				}
			}

		} catch (Exception e) {
			AppLog.debug("하위 업무 상태 변경 실패: " + e.getMessage());
		}
	}

	/**
	 * 모든 하위 업무가 완료 상태인지 확인한다.
	 *
	 * @param  parentTaskId 상위 업무 ID
	 * @param  projectId 프로젝트 ID
	 * @return 모든 하위 업무가 완료이면 true, 아니면 false
	 */
	private boolean areAllChildrenCompleted(String parentTaskId, String projectId) {
		try {
			// pt_task_id로 연결된 모든 하위 업무 조회
			WbsVo childParam = new WbsVo();
			childParam.setPtTaskId(parentTaskId);
			childParam.setPjtId(projectId);
			List<WbsVo> allChildList = wbsDAO.selectAllChildrenByParent(childParam);

			if (allChildList != null && !allChildList.isEmpty()) {
				for (WbsVo child : allChildList) {
					if (!WbsConstants.TaskStatus.COMPLETED.equals(child.getTaskStatus())) {
						return false;
					}
				}
			}

			return true;

		} catch (Exception e) {
			AppLog.debug("하위 업무 완료 상태 확인 실패: " + e.getMessage());
			return false;
		}
	}

	/**
	 * 업무의 상태를 업데이트한다.
	 *
	 * @param  task 업무 WbsVo
	 * @param  newStatus 새로운 상태
	 */
	private void updateTaskStatus(WbsVo task, String newStatus) {
		try {
			task.setTaskStatus(newStatus);
			adjustRateByStatus(task);
			wbsDAO.updateWbs(task);
		} catch (Exception e) {
			AppLog.debug("업무 상태 업데이트 실패: " + task.getTaskId());
		}
	}

	/**
	 * 상태에 따라 진척률을 자동 조정한다.
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