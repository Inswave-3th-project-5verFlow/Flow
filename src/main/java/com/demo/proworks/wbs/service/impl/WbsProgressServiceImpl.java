package com.demo.proworks.wbs.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.demo.proworks.wbs.constants.WbsConstants;
import com.demo.proworks.wbs.dao.WbsDAO;
import com.demo.proworks.wbs.service.WbsProgressService;
import com.demo.proworks.wbs.vo.WbsVo;

/**
 * WBS 진척률 계산 서비스 구현체
 * 
 * @author 김성민
 * @since 2025/07/24
 */
@Service("wbsProgressServiceImpl")
public class WbsProgressServiceImpl implements WbsProgressService {

	@Resource(name = "wbsDAO")
	private WbsDAO wbsDAO;

	/**
	 * 업무의 진척률을 계산하고 업데이트한다. 모든 하위 업무의 평균 진척률을 계산한다.
	 */
	@Override
	public void calculateAndUpdateProgress(String taskId, String projectId) {
		try {
			WbsVo param = new WbsVo();
			param.setTaskId(taskId);
			param.setPjtId(projectId);

			WbsVo task = wbsDAO.selectWbs(param);
			if (task == null) {
				System.out.println("업무를 찾을 수 없음: " + taskId);
				return;
			}

			int calculatedRate = calculateProgress(taskId, projectId);

			// 계산된 진척률로 업데이트
			param.setTaskRate(String.valueOf(calculatedRate));
			wbsDAO.updateRate(param);

			System.out.println(String.format("업무 %s 진척률 업데이트 완료: %d%%", taskId, calculatedRate));

		} catch (Exception e) {
			System.err.println("진척률 계산 실패: " + taskId + ", 오류: " + e.getMessage());
		}
	}

	/**
	 * 업무의 진척률을 계산한다. 모든 하위 업무의 평균 진척률을 계산하고, 상위 업무도 재귀적으로 업데이트한다.
	 */
	@Override
	public int calculateProgress(String taskId, String projectId) {
		try {
			// pt_task_id로 연결된 모든 하위 업무 조회
			WbsVo childParam = new WbsVo();
			childParam.setPtTaskId(taskId);
			childParam.setPjtId(projectId);
			List<WbsVo> allChildList = wbsDAO.selectAllChildrenByParent(childParam);

			int totalProgress = 0;
			int taskCount = 0;

			// 하위 업무들의 진척률 합산
			if (allChildList != null && !allChildList.isEmpty()) {
				for (WbsVo child : allChildList) {
					totalProgress += getProgressRateByStatus(child.getTaskStatus());
					taskCount++;
					System.out.println(String.format("하위 업무 %s: 상태=%s, 진척률=%d%%", child.getTaskId(),
							child.getTaskStatus(), getProgressRateByStatus(child.getTaskStatus())));
				}
			}

			// 평균 진척률 계산
			int averageRate;
			if (taskCount > 0) {
				averageRate = totalProgress / taskCount;
				System.out.println(
						String.format("업무 %s 진척률 계산: %d/%d = %d%%", taskId, totalProgress, taskCount, averageRate));
			} else {
				// 하위 업무가 없는 경우 현재 업무의 상태 기반 진척률 사용
				WbsVo param = new WbsVo();
				param.setTaskId(taskId);
				param.setPjtId(projectId);
				WbsVo currentTask = wbsDAO.selectWbs(param);
				averageRate = getProgressRateByStatus(currentTask.getTaskStatus());
				System.out.println(String.format("업무 %s 하위업무 없음, 자체 상태 기반 진척률: %d%%", taskId, averageRate));
			}

			// 상위 업무가 있으면 재귀적으로 계산
			WbsVo param = new WbsVo();
			param.setTaskId(taskId);
			param.setPjtId(projectId);
			WbsVo currentTask = wbsDAO.selectWbs(param);

			if (currentTask != null && currentTask.getPtTaskId() != null
					&& !currentTask.getPtTaskId().trim().isEmpty()) {
				calculateAndUpdateProgress(currentTask.getPtTaskId(), projectId);
			}

			return averageRate;

		} catch (Exception e) {
			System.err.println("진척률 계산 실패: " + taskId + ", 오류: " + e.getMessage());
			return WbsConstants.ProgressRate.WAITING_RATE;
		}
	}

	/**
	 * 상태에 따른 기본 진척률을 반환한다.
	 */
	@Override
	public int getProgressRateByStatus(String status) {
		if (status == null) {
			return WbsConstants.ProgressRate.WAITING_RATE;
		}

		if (WbsConstants.TaskStatus.COMPLETED.equals(status)) {
			return WbsConstants.ProgressRate.COMPLETED_RATE;
		} else if (WbsConstants.TaskStatus.IN_PROGRESS.equals(status)) {
			return WbsConstants.ProgressRate.IN_PROGRESS_RATE;
		} else {
			return WbsConstants.ProgressRate.WAITING_RATE;
		}
	}

}