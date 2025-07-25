package com.demo.proworks.task.design.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.task.design.service.DesignService;
import com.demo.proworks.task.design.vo.DesignVo;
import com.demo.proworks.task.design.util.DesignWbsConverter;
import com.demo.proworks.wbs.service.WbsProgressService;
import com.demo.proworks.wbs.service.WbsStatusPropagationService;
import com.demo.proworks.wbs.vo.WbsVo;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.util.ControllerContextUtil;
import com.inswave.elfw.util.ElBeanUtils;
import com.inswave.elfw.view.ElMappingJacksonObjectMapper;
import com.demo.proworks.task.design.dao.DesignDAO;

/**
 * @subject : 설계 업무 정보 관련 처리를 담당하는 ServiceImpl
 * @description : 설계 업무 정보 관련 처리를 담당하는 ServiceImpl
 * @author : 백승호
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 백승호 최초 생성
 * 
 */
@Service("designServiceImpl")
public class DesignServiceImpl implements DesignService {

	@Resource(name = "designDAO")
	private DesignDAO designDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Resource(name = "wbsProgressServiceImpl")
	private WbsProgressService wbsProgressService;

	@Resource(name = "wbsStatusPropagationServiceImpl")
	private WbsStatusPropagationService wbsStatusPropagationService;

	/**
	 * 모든 업무 정보 목록을 조회합니다.
	 *
	 * @process 1. 설계 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTasks(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectTasks(designVo);

		return list;
	}

	/**
	 * 설계 업무 정보 목록을 조회합니다.
	 *
	 * @process 1. 설계 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectListDesign(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectListDesign(designVo);

		return list;
	}

	/**
	 * 개발 업무 정보 목록을 조회합니다.
	 *
	 * @process 1. 설계 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectListDevelop(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectListDevelop(designVo);

		return list;
	}

	/**
	 * 조회한 설계 업무 정보 전체 카운트
	 *
	 * @process 1. 설계 업무 정보 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountDesign(DesignVo designVo) throws Exception {
		return designDAO.selectListCountDesign(designVo);
	}

	/**
	 * 조회한 설계 업무 정보 전체 카운트
	 *
	 * @process 1. 설계 업무 정보 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountDevelop(DesignVo designVo) throws Exception {
		return designDAO.selectListCountDevelop(designVo);
	}

	/**
	 * 설계 업무 정보를 상세 조회한다.
	 *
	 * @process 1. 설계 업무 정보를 상세 조회한다. 2. 결과 DesignVo을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public DesignVo selectDesign(DesignVo designVo) throws Exception {
		DesignVo resultVO = designDAO.selectDesign(designVo);

		return resultVO;
	}

	/**
	 * 설계 업무 정보를 등록 처리 한다.
	 *
	 * @process 1. 설계 업무 정보를 등록 처리 한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertDesign(DesignVo designVo) throws Exception {
		// 1. 기존 Design 로직으로 업무 등록 (중복 삽입 방지)
		int result = designDAO.insertDesign(designVo);

		// 2. WBS 로직 추가: 업무 삽입 후 상태 전파 및 진척률 계산
		if (result > 0 && designVo.getTaskId() != null) {
			try {
				WbsVo wbsVo = DesignWbsConverter.toWbsVo(designVo);

				// 하위 업무 추가 시 상위 업무 상태 자동 조정
				wbsStatusPropagationService.propagateOnChildInsert(wbsVo);

				// 진척률 계산 및 업데이트
				wbsProgressService.calculateAndUpdateProgress(wbsVo.getTaskId(), wbsVo.getPjtId());

			} catch (Exception e) {
				System.err.println("insertDesign 후 WBS 로직 처리 오류: " + e.getMessage());
				// WBS 로직 실패해도 기본 삽입은 성공으로 처리
			}
		}

		return result;
	}

	/**
	 * 설계 업무 정보를 갱신 처리 한다.
	 *
	 * @process 1. 설계 업무 정보를 갱신 처리 한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateDesign(DesignVo designVo) throws Exception {
		// 0. 기존 데이터 조회 (상태 변경 감지용)
		DesignVo currentData = designDAO.selectDesign(designVo);
		if (currentData == null) {
			throw new RuntimeException("존재하지 않는 업무입니다. taskId: " + designVo.getTaskId());
		}

		// 1. 기존 Design 로직으로 업무 수정 (상위업무 변경 등 기존 로직 유지)
		boolean isParentChanged = isParentTaskChanged(currentData, designVo);
		int result;

		if (isParentChanged) {
			System.out.println("상위업무 변경 감지 : taskName=" + designVo.getTaskName() + ", taskId=" + designVo.getTaskId()
					+ ", 이전상위=" + currentData.getPtTaskId() + ", 새상위=" + designVo.getPtTaskId());

			// 1) 자신의 업무 정보 + depth 업데이트
			result = designDAO.updateDesign(designVo);

			// 2) 자신의 하위 업무들의 depth 연쇄 업데이트
			int result2 = updateChildTasksDepth(designVo);

			System.out.println("기본업데이트=" + result + ", 하위업데이트=" + result2);

			// 3) 상위업무 변경 시 WBS 로직 처리
			if (result > 0) {
				try {
					WbsVo wbsVo = DesignWbsConverter.toWbsVo(designVo);

					// 새로운 상위 업무에 하위 업무가 추가되었음을 알림
					if (designVo.getPtTaskId() != null && !designVo.getPtTaskId().equals("0")) {
						wbsStatusPropagationService.propagateOnChildInsert(wbsVo);
					}

					// 이전 상위 업무에서 하위 업무가 제거되었으므로 진척률 재계산
					if (currentData.getPtTaskId() != null && !currentData.getPtTaskId().equals("0")) {
						wbsProgressService.calculateAndUpdateProgress(currentData.getPtTaskId(),
								currentData.getPjtId());
					}

					// 현재 업무의 진척률도 재계산
					wbsProgressService.calculateAndUpdateProgress(wbsVo.getTaskId(), wbsVo.getPjtId());

				} catch (Exception e) {
					System.err.println("상위업무 변경 시 WBS 로직 처리 오류: " + e.getMessage());
					// WBS 로직 실패해도 기본 수정은 성공으로 처리
				}
			}

		} else {
			result = designDAO.updateDesign(designVo);
		}

		AppLog.debug("result : " + result);

		// 2. WBS 로직 추가: 업무 수정 후 상태 전파 및 진척률 계산 (상태 변경의 경우)
		if (result > 0) {
			try {
				WbsVo wbsVo = DesignWbsConverter.toWbsVo(designVo);
				AppLog.debug("wbsVo : " + wbsVo);
				String oldStatus = currentData.getTaskStatus() != null ? currentData.getTaskStatus() : "대기";
				String newStatus = designVo.getTaskStatus() != null ? designVo.getTaskStatus() : "대기";
				AppLog.debug("oldStatus : " + oldStatus);
				AppLog.debug("newStatus : " + newStatus);

				// 상태 변경이 있는 경우 상태 전파 처리
				if (!oldStatus.equals(newStatus)) {
					// 상위 업무에 영향을 주는 상태 전파 (하위 업무 상태 변경)
					wbsStatusPropagationService.propagateOnChildUpdate(wbsVo);

					// 하위 업무에 영향을 주는 상태 전파 (상위 업무 상태 변경)
					wbsStatusPropagationService.propagateOnParentUpdate(wbsVo, oldStatus, newStatus);

					// 상태 변경 시에만 진척률 재계산 (상위업무 변경이 아닌 경우)
					if (!isParentChanged) {
						wbsProgressService.calculateAndUpdateProgress(wbsVo.getTaskId(), wbsVo.getPjtId());
					}
				}

			} catch (Exception e) {
				System.err.println("updateDesign 후 WBS 로직 처리 오류: " + e.getMessage());
				// WBS 로직 실패해도 기본 수정은 성공으로 처리
			}
		}

		return result;
	}

	/**
	 * 하위 업무들의 depth를 연쇄적으로 업데이트합니다. 기존 XML의 selectChildTasks 쿼리를 활용합니다.
	 */
	private int updateChildTasksDepth(DesignVo designVo) throws Exception {
		try {
			// 기존 XML의 selectChildTasks 쿼리로 하위 업무들의 새로운 depth 계산
			List<DesignVo> childTasks = designDAO.selectChildTasks(designVo);

			int updatedCount = 0;

			// 각 하위 업무의 depth 업데이트
			for (DesignVo childTask : childTasks) {
				DesignVo updateChild = new DesignVo();
				updateChild.setTaskId(childTask.getTaskId());
				updateChild.setTaskDepth(childTask.getTaskDepth()); // 새로 계산된 depth
				updateChild.setPjtId(designVo.getPjtId());

				int result = designDAO.updateDesign(updateChild);
				updatedCount += result;
			}

			System.out.println("하위 업무 depth 업데이트 완료: " + updatedCount + "건");
			return updatedCount;

		} catch (Exception e) {
			System.err.println("하위 업무 depth 업데이트 중 오류: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * 상위업무 변경 여부를 체크합니다.
	 */
	private boolean isParentTaskChanged(DesignVo current, DesignVo updated) {
		String currentPtTaskId = (current.getPtTaskId() != null) ? current.getPtTaskId() : "0";
		String updatedPtTaskId = (updated.getPtTaskId() != null) ? updated.getPtTaskId() : "0";

		boolean changed = !currentPtTaskId.equals(updatedPtTaskId);

		if (changed) {
			System.out.println("상위업무 변경 감지: " + currentPtTaskId + " → " + updatedPtTaskId);
		}

		return changed;
	}

	/**
	 * 설계 업무 정보를 삭제 처리 한다.
	 *
	 * @process 1. 설계 업무 정보를 삭제 처리 한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteDesign(DesignVo designVo) throws Exception {
		// 0. 삭제 전 현재 업무 정보 조회 (WBS 로직용)
		DesignVo currentData = designDAO.selectDesign(designVo);

		// 1. 기존 Design 로직으로 업무 삭제 (중복 삭제 방지)
		int result = designDAO.deleteDesign(designVo);

		// 2. WBS 로직 추가: 업무 삭제 후 상태 전파 및 진척률 계산
		if (result > 0 && currentData != null) {
			try {
				WbsVo wbsVo = DesignWbsConverter.toWbsVo(currentData);

				// 업무 삭제 시 관련 업무들의 상태 조정
				wbsStatusPropagationService.propagateOnTaskDelete(wbsVo);

				// 부모 업무의 진척률 재계산 (삭제된 업무가 있었다면)
				if (currentData.getPtTaskId() != null) {
					wbsProgressService.calculateAndUpdateProgress(currentData.getPtTaskId(), currentData.getPjtId());
				}

			} catch (Exception e) {
				System.err.println("deleteDesign 후 WBS 로직 처리 오류: " + e.getMessage());
				// WBS 로직 실패해도 기본 삭제는 성공으로 처리
			}
		}

		return result;
	}

	/**
	 * 트리 구조에 맞게 설계 업무 목록을 조회한다.
	 *
	 * @process 1. 설계 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTreeListDesign(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectTreeListDesign(designVo);

		return list;
	}

	/**
	 * 트리 구조에 맞게 개발 업무 목록을 조회한다.
	 *
	 * @process 1. 개발 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTreeListDevelop(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectTreeListDevelop(designVo);

		return list;
	}

	/**
	 * 트리 구조에 맞게 모든 업무 목록을 조회한다.
	 *
	 * @process 1. 개발 업무 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectTreeList(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectTreeList(designVo);

		return list;
	}

	/**
	 * 모든 단계의 업무를 정렬된 형태로 조회한다.
	 *
	 * @process 1. 모든 단계의 업무를 정렬된 형태로 조회한다. 2. 결과 List<DesignVo>을(를) 리턴한다.
	 * 
	 * @param designVo 설계 업무 정보 DesignVo
	 * @return 설계 업무 정보 목록 List<DesignVo>
	 * @throws Exception
	 */
	public List<DesignVo> selectListTask(DesignVo designVo) throws Exception {
		List<DesignVo> list = designDAO.selectListTask(designVo);

		return list;
	}

}
