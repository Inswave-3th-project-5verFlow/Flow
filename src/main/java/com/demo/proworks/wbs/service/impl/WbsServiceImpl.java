package com.demo.proworks.wbs.service.impl;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javax.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.demo.proworks.wbs.service.WbsService;
import com.demo.proworks.wbs.vo.WbsVo;
import com.demo.proworks.wbs.dao.WbsDAO;

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
 * 
 */
@Service("wbsServiceImpl")
public class WbsServiceImpl implements WbsService {

	@Resource(name = "wbsDAO")
	private WbsDAO wbsDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * 전체 WBS 계층 구조를 조회한다.
	 */
	public List<WbsVo> selectListWbsAll(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListWbsAll(wbsVo);
	}

	/**
	 * 전체 WBS 카운트를 조회한다.
	 */
	public long selectListCountWbsAll(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListCountWbsAll(wbsVo);
	}

	/**
	 * 검색 조건에 따라 WBS 계층 구조를 조회한다.
	 */
	@Override
	public List<WbsVo> selectListWbsSearch(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListWbsSearch(wbsVo);
	}

	/**
	 * 검색 조건에 따라 WBS 카운트를 조회한다.
	 */
	public long selectListCountWbsSearch(WbsVo wbsVo) throws Exception {
		return wbsDAO.selectListCountWbsSearch(wbsVo);
	}

	/**
	 * WBS를 상세 조회한다.
	 */
	public WbsVo selectWbs(WbsVo wbsVo) throws Exception {
		WbsVo resultVO = wbsDAO.selectWbs(wbsVo);
		return resultVO;
	}

	/**
	 * WBS를 등록 처리 한다. (상위/하위 업무 상태 연동 포함)
	 */
	public int insertWbs(WbsVo wbsVo) throws Exception {
		int result = wbsDAO.insertWbs(wbsVo);
		
		// 등록 후 상위 업무 상태 자동 조정 및 진척률 재계산
		try {
			handleParentStatusOnChildInsert(wbsVo);
			
			// 하위업무 추가 시 상위업무의 진척률 재계산
			if (wbsVo.getPtTaskId() != null && !wbsVo.getPtTaskId().trim().isEmpty()) {
				WbsVo parentParam = new WbsVo();
				parentParam.setTaskId(wbsVo.getPtTaskId());
				parentParam.setPjtId(wbsVo.getPjtId());
				calcProgress(parentParam);
				System.out.println("하위업무 추가로 인한 상위업무 " + wbsVo.getPtTaskId() + " 진척률 재계산 완료");
			}
			
			// 개발업무 추가 시 연관 설계업무의 진척률 재계산
			if (wbsVo.getDeTaskId() != null && !wbsVo.getDeTaskId().trim().isEmpty()) {
				WbsVo designParam = new WbsVo();
				designParam.setTaskId(wbsVo.getDeTaskId());
				designParam.setPjtId(wbsVo.getPjtId());
				calcProgress(designParam);
				System.out.println("개발업무 추가로 인한 설계업무 " + wbsVo.getDeTaskId() + " 진척률 재계산 완료");
			}
			
		} catch (Exception e) {
			System.err.println("상위 업무 상태 조정 또는 진척률 계산 실패: " + e.getMessage());
		}
		
		return result;
	}

	/**
	 * WBS를 갱신 처리 한다. (상위/하위 업무 상태 연동 포함)
	 */
	public int updateWbs(WbsVo wbsVo) throws Exception {
		// 기존 상태 조회
		WbsVo existingTask = wbsDAO.selectWbs(wbsVo);
		String oldStatus = existingTask.getTaskStatus();
		String newStatus = wbsVo.getTaskStatus();
		
		// 상태에 따른 진척률 자동 조정
		adjustRateByStatus(wbsVo);
		
		int result = wbsDAO.updateWbs(wbsVo);
		
		// 상태 변경에 따른 하위 업무 처리
		try {
			// 하위로 전파
			handleChildStatusOnParentUpdate(wbsVo, oldStatus, newStatus);
			
			// 상위로 전파
			if (!oldStatus.equals(newStatus) && "완료".equals(newStatus)) {
				considerParentStatusUpdate(wbsVo);
			}
			
			// 진척률 계산
			calcProgress(wbsVo);
		} catch (Exception e) {
			System.err.println("하위 업무 상태 조정 또는 진척률 계산 실패: " + e.getMessage());
		}
		
		return result;
	}

	/**
	 * WBS를 삭제 처리 한다. (진척률 계산 포함)
	 */
	public int deleteWbs(WbsVo wbsVo) throws Exception {
		// 삭제 전 관련 업무 정보 저장
		Set<String> calcTargets = new HashSet<>();
		String pjtId = wbsVo.getPjtId();
		
		try {
			WbsVo task = wbsDAO.selectWbs(wbsVo);
			if (task != null) {
				// 상위 업무가 있으면 계산 대상에 추가
				if (task.getPtTaskId() != null && !task.getPtTaskId().trim().isEmpty()) {
					calcTargets.add(task.getPtTaskId());
				}
				// 연관 설계 업무가 있으면 계산 대상에 추가
				if (task.getDeTaskId() != null && !task.getDeTaskId().trim().isEmpty()) {
					calcTargets.add(task.getDeTaskId());
				}
			}
		} catch (Exception e) {
			// 조회 실패해도 삭제는 진행
		}
		
		// 업무 삭제
		int result = wbsDAO.deleteWbs(wbsVo);
		
		// 관련 업무들의 진척률 재계산
		for (String taskId : calcTargets) {
			try {
				WbsVo calcVo = new WbsVo();
				calcVo.setTaskId(taskId);
				calcVo.setPjtId(pjtId);
				calcProgress(calcVo);
			} catch (Exception e) {
				System.err.println("삭제 후 진척률 계산 실패: " + taskId);
			}
		}
		
		return result;
	}

	/**
	 * 하위 업무 추가 시 상위 업무 상태 자동 조정
	 */
	private void handleParentStatusOnChildInsert(WbsVo childVo) throws Exception {
		String parentTaskId = childVo.getPtTaskId();
		
		if (parentTaskId != null && !parentTaskId.trim().isEmpty()) {
			WbsVo parentParam = new WbsVo();
			parentParam.setTaskId(parentTaskId);
			parentParam.setPjtId(childVo.getPjtId());
			
			WbsVo parentTask = wbsDAO.selectWbs(parentParam);
			
			if (parentTask != null) {
				String parentStatus = parentTask.getTaskStatus();
				
				// 케이스 1: 상위 업무가 대기 상태이고 하위 업무가 추가됨 -> 진행중으로 변경
				if ("대기".equals(parentStatus)) {
					parentTask.setTaskStatus("진행중");
					// 진척률은 calcProgress에서 재계산될 예정이므로 임시값 설정
					parentTask.setTaskRate("0");
					wbsDAO.updateWbs(parentTask);
					System.out.println("상위 업무 " + parentTaskId + " 상태를 대기 → 진행중으로 변경");
				}
				// 케이스 2: 상위 업무가 완료 상태이고 하위 업무가 추가됨 -> 진행중으로 변경
				else if ("완료".equals(parentStatus)) {
					parentTask.setTaskStatus("진행중");
					// 진척률은 calcProgress에서 재계산될 예정이므로 임시값 설정
					parentTask.setTaskRate("0");
					wbsDAO.updateWbs(parentTask);
					System.out.println("상위 업무 " + parentTaskId + " 상태를 완료 → 진행중으로 변경");
				}
			}
		}
		
		// 설계업무에 개발업무가 추가되는 경우도 처리
		String designTaskId = childVo.getDeTaskId();
		if (designTaskId != null && !designTaskId.trim().isEmpty()) {
			WbsVo designParam = new WbsVo();
			designParam.setTaskId(designTaskId);
			designParam.setPjtId(childVo.getPjtId());
			
			WbsVo designTask = wbsDAO.selectWbs(designParam);
			
			if (designTask != null) {
				String designStatus = designTask.getTaskStatus();
				
				if ("대기".equals(designStatus) || "완료".equals(designStatus)) {
					designTask.setTaskStatus("진행중");
					// 진척률은 calcProgress에서 재계산될 예정이므로 임시값 설정
					designTask.setTaskRate("0");
					wbsDAO.updateWbs(designTask);
					System.out.println("설계 업무 " + designTaskId + " 상태를 " + designStatus + " → 진행중으로 변경");
				}
			}
		}
	}

	/**
	 * 상위 업무 상태 변경 시 하위 업무 상태 자동 조정
	 */
	private void handleChildStatusOnParentUpdate(WbsVo parentVo, String oldStatus, String newStatus) throws Exception {
		// 상태 변경이 없으면 처리하지 않음
		if (oldStatus != null && oldStatus.equals(newStatus)) {
			return;
		}
		
		String parentTaskId = parentVo.getTaskId();
		String pjtId = parentVo.getPjtId();
		
		// 케이스 3: 상위업무 진행중 → 완료로 변경 시 하위 업무 모두 완료 처리
		if ("진행중".equals(oldStatus) && "완료".equals(newStatus)) {
			updateAllChildrenStatus(parentTaskId, pjtId, "완료");
			System.out.println("상위 업무 " + parentTaskId + " 완료로 인해 모든 하위 업무를 완료 처리");
		}
		
		// 설계업무인 경우 연관 개발업무도 처리
		if ("1".equals(parentVo.getIsDesign()) && "진행중".equals(oldStatus) && "완료".equals(newStatus)) {
			updateRelatedDevTasksStatus(parentTaskId, pjtId, "완료");
			System.out.println("설계 업무 " + parentTaskId + " 완료로 인해 연관 개발 업무를 완료 처리");
		}
	}
	
	/**
	 * 하위 업무 상태 변경이 상위 업무에 미치는 영향 처리
	 * - 하위 업무가 모두 완료인 경우 상위 업무도 완료로 변경
	 * - 재귀적으로 모든 상위 업무에 적용
	 */
	private void considerParentStatusUpdate(WbsVo childVo) throws Exception {
		// 상위 업무가 없으면 처리하지 않음
		String parentTaskId = childVo.getPtTaskId();
		if (parentTaskId == null || parentTaskId.trim().isEmpty()) {
			return;
		}
		
		String pjtId = childVo.getPjtId();
		
		// 상위 업무 정보 가져오기
		WbsVo parentParam = new WbsVo();
		parentParam.setTaskId(parentTaskId);
		parentParam.setPjtId(pjtId);
		WbsVo parentVo = wbsDAO.selectWbs(parentParam);
		
		if (parentVo == null) {
			return;
		}
		
		// 이미 완료 상태면 처리하지 않음
		if ("완료".equals(parentVo.getTaskStatus())) {
			return;
		}
		
		// 해당 상위 업무의 모든 하위 업무 조회
		boolean allChildrenCompleted = true;
		
		// 1. 설계업무 하위 조회
		List<WbsVo> childDesignList = wbsDAO.selectChildDesign(parentParam);
		if (childDesignList != null && !childDesignList.isEmpty()) {
			for (WbsVo design : childDesignList) {
				if (!"완료".equals(design.getTaskStatus())) {
					allChildrenCompleted = false;
					break;
				}
			}
		}
		
		// 2. 일반 하위 업무 조회
		if (allChildrenCompleted) {
			WbsVo allChildParam = new WbsVo();
			allChildParam.setPtTaskId(parentTaskId);
			allChildParam.setPjtId(pjtId);
			List<WbsVo> allChildList = wbsDAO.selectAllChildrenByParent(allChildParam);
			
			if (allChildList != null && !allChildList.isEmpty()) {
				for (WbsVo child : allChildList) {
					if (!"완료".equals(child.getTaskStatus())) {
						allChildrenCompleted = false;
						break;
					}
				}
			}
		}
		
		// 3. 개발업무 이고 연관 설계업무가 있는 경우
		if (allChildrenCompleted && "0".equals(childVo.getIsDesign()) && childVo.getDeTaskId() != null && !childVo.getDeTaskId().trim().isEmpty()) {
			// 다른 개발업무들도 확인
			WbsVo designParam = new WbsVo();
			designParam.setTaskId(childVo.getDeTaskId());
			designParam.setPjtId(pjtId);
			
			List<WbsVo> relatedDevList = wbsDAO.selectDevByDesign(designParam);
			if (relatedDevList != null && !relatedDevList.isEmpty()) {
				for (WbsVo dev : relatedDevList) {
					if (!"완료".equals(dev.getTaskStatus())) {
						allChildrenCompleted = false;
						break;
					}
				}
			}
		}
		
		// 모든 하위 업무가 완료면 상위 업무도 완료로 변경
		if (allChildrenCompleted) {
			System.out.println("모든 하위 업무가 완료되어 상위 업무 " + parentTaskId + " 상태를 완료로 변경");
			parentVo.setTaskStatus("완료");
			adjustRateByStatus(parentVo);
			wbsDAO.updateWbs(parentVo);
			
			// 재귀적으로 상위의 상위 업무도 처리
			considerParentStatusUpdate(parentVo);
		}
	}

	/**
	 * 모든 하위 업무의 상태를 변경 (개선된 버전)
	 * - 하위 설계업무 처리
	 * - 하위 개발업무 처리
	 * - 연관 개발업무 처리
	 * - 모든 뎁스 업무 처리
	 */
	private void updateAllChildrenStatus(String parentTaskId, String pjtId, String newStatus) throws Exception {
		WbsVo param = new WbsVo();
		param.setTaskId(parentTaskId);
		param.setPjtId(pjtId);
		
		// 1. 직접 하위 설계업무 처리
		List<WbsVo> childDesignList = wbsDAO.selectChildDesign(param);
		if (childDesignList != null) {
			for (WbsVo child : childDesignList) {
				child.setTaskStatus(newStatus);
				adjustRateByStatus(child);
				wbsDAO.updateWbs(child);
				
				// 재귀적으로 하위 업무들 처리
				updateAllChildrenStatus(child.getTaskId(), pjtId, newStatus);
			}
		}
		
		// 2. 연관 개발업무 처리
		List<WbsVo> devList = wbsDAO.selectDevByDesign(param);
		if (devList != null) {
			for (WbsVo dev : devList) {
				dev.setTaskStatus(newStatus);
				adjustRateByStatus(dev);
				wbsDAO.updateWbs(dev);
				
				// 개발업무 하위에 다른 업무가 있을 수 있으므로 재귀 처리
				updateAllChildrenStatus(dev.getTaskId(), pjtId, newStatus);
			}
		}
		
		// 3. 하위 업무(일반) 조회 및 처리 - pt_task_id로 연결된 모든 업무(설계/개발 무관)
		WbsVo childParam = new WbsVo();
		childParam.setPtTaskId(parentTaskId);
		childParam.setPjtId(pjtId);
		List<WbsVo> allChildList = wbsDAO.selectAllChildrenByParent(childParam);
		
		if (allChildList != null) {
			for (WbsVo child : allChildList) {
				// 이미 처리된 설계업무는 제외
				if ("1".equals(child.getIsDesign()) && childDesignList != null) {
					boolean alreadyProcessed = false;
					for (WbsVo designChild : childDesignList) {
						if (designChild.getTaskId().equals(child.getTaskId())) {
							alreadyProcessed = true;
							break;
						}
					}
					if (alreadyProcessed) continue;
				}
				
				// 이미 처리된 개발업무는 제외
				if ("0".equals(child.getIsDesign()) && devList != null) {
					boolean alreadyProcessed = false;
					for (WbsVo devChild : devList) {
						if (devChild.getTaskId().equals(child.getTaskId())) {
							alreadyProcessed = true;
							break;
						}
					}
					if (alreadyProcessed) continue;
				}
				
				// 미처리된 업무 상태 변경
				child.setTaskStatus(newStatus);
				adjustRateByStatus(child);
				wbsDAO.updateWbs(child);
				
				// 재귀적으로 하위 업무들 처리
				updateAllChildrenStatus(child.getTaskId(), pjtId, newStatus);
			}
		}
	}

	/**
	 * 설계업무와 연관된 개발업무들의 상태를 변경 (개선된 버전)
	 * - 직접 연관된 개발업무 처리
	 * - 개발업무의 하위 업무도 재귀적으로 처리
	 */
	private void updateRelatedDevTasksStatus(String designTaskId, String pjtId, String newStatus) throws Exception {
		WbsVo param = new WbsVo();
		param.setTaskId(designTaskId);
		param.setPjtId(pjtId);
		
		List<WbsVo> devList = wbsDAO.selectDevByDesign(param);
		if (devList != null) {
			for (WbsVo dev : devList) {
				dev.setTaskStatus(newStatus);
				adjustRateByStatus(dev);
				wbsDAO.updateWbs(dev);
				
				// 개발업무 하위에 있는 업무들도 재귀적으로 처리
				updateAllChildrenStatus(dev.getTaskId(), pjtId, newStatus);
			}
		}
	}

	/**
	 * 진척률 계산
	 */
	public void calcProgress(WbsVo wbsVo) throws Exception {
		WbsVo task = wbsDAO.selectWbs(wbsVo);
		if (task == null) return;

		if ("1".equals(task.getIsDesign())) {
			calcDesignProgress(wbsVo.getTaskId(), wbsVo.getPjtId());
		} else {
			calcDevProgress(wbsVo.getTaskId(), wbsVo.getPjtId());
		}
	}

	/**
	 * 설계업무 진척률 계산
	 */
	private void calcDesignProgress(String taskId, String pjtId) throws Exception {
		WbsVo param = new WbsVo();
		param.setTaskId(taskId);
		param.setPjtId(pjtId);

		List<WbsVo> childList = wbsDAO.selectChildDesign(param);
		List<WbsVo> devList = wbsDAO.selectDevByDesign(param);

		int total = 0;
		int count = 0;

		// 하위 설계업무
		if (childList != null && !childList.isEmpty()) {
			for (WbsVo child : childList) {
				total += getRate(child.getTaskStatus());
				count++;
				System.out.println("하위 설계업무: " + child.getTaskId() + ", 상태: " + child.getTaskStatus() + ", 진척률: " + getRate(child.getTaskStatus()));
			}
		}

		// 연관 개발업무
		if (devList != null && !devList.isEmpty()) {
			for (WbsVo dev : devList) {
				total += getRate(dev.getTaskStatus());
				count++;
				System.out.println("연관 개발업무: " + dev.getTaskId() + ", 상태: " + dev.getTaskStatus() + ", 진척률: " + getRate(dev.getTaskStatus()));
			}
		}

		int rate;
		if (count > 0) {
			rate = total / count;
			System.out.println("설계업무 " + taskId + " 진척률 계산: " + total + "/" + count + " = " + rate + "%");
		} else {
			// 하위 업무가 없는 경우 현재 업무 상태 기반
			WbsVo task = wbsDAO.selectWbs(param);
			rate = getRate(task.getTaskStatus());
			System.out.println("설계업무 " + taskId + " 하위업무 없음, 자체 상태 기반 진척률: " + rate + "%");
		}

		// 진척률 업데이트
		param.setTaskRate(String.valueOf(rate));
		wbsDAO.updateRate(param);
		System.out.println("설계업무 " + taskId + " 진척률 업데이트 완료: " + rate + "%");

		// 상위업무 재계산 (재귀)
		WbsVo parent = wbsDAO.selectParentDesign(param);
		if (parent != null && parent.getTaskId() != null && !parent.getTaskId().trim().isEmpty()) {
			calcDesignProgress(parent.getTaskId(), pjtId);
		}
	}

	/**
	 * 개발업무 진척률 계산 (연관된 설계업무 재계산)
	 */
	private void calcDevProgress(String taskId, String pjtId) throws Exception {
		WbsVo param = new WbsVo();
		param.setTaskId(taskId);
		param.setPjtId(pjtId);

		WbsVo design = wbsDAO.selectDesignByDev(param);
		if (design != null && design.getTaskId() != null && !design.getTaskId().trim().isEmpty()) {
			calcDesignProgress(design.getTaskId(), pjtId);
		}
	}

	/**
	 * 상태별 진척률 반환
	 */
	private int getRate(String status) {
		if (status == null) return 0;
		
		switch (status.toUpperCase()) {
			case "완료": return 100;
			case "진행중": return 50;
			case "대기":
			default: return 0;
		}
	}

	/**
	 * 상태에 따른 진척률 자동 조정
	 */
	private void adjustRateByStatus(WbsVo wbsVo) {
		String status = wbsVo.getTaskStatus();

		if (status != null) {
			switch (status.trim()) {
			case "완료":
				wbsVo.setTaskRate("100");
				break;
			case "진행중":
				wbsVo.setTaskRate("50");
				break;
			case "대기":
				wbsVo.setTaskRate("0");
				break;
			}
		}
	}
}