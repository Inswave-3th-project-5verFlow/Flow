package com.demo.proworks.task.design.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.task.design.service.DesignService;
import com.demo.proworks.task.design.vo.DesignVo;
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
		return designDAO.insertDesign(designVo);
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
		// 기존 데이터 조회
		DesignVo currentData = designDAO.selectDesign(designVo);
		if (currentData == null) {
			throw new RuntimeException("존재하지 않는 업무입니다. taskId: " + designVo.getTaskId());
		}

		// 상위업무 변경 여부 체크
		boolean isParentChanged = isParentTaskChanged(currentData, designVo);

		if (isParentChanged) {
			System.out.println("상위업무 변경 감지 : taskName=" + designVo.getTaskName() + ", taskId=" + designVo.getTaskId()
					+ ", 이전상위=" + currentData.getPtTaskId() + ", 새상위=" + designVo.getPtTaskId());

			// 1) 자신의 업무 정보 + depth 업데이트
			int result1 = designDAO.updateDesign(designVo);

			// 2) 자신의 하위 업무들의 depth 연쇄 업데이트
			int result2 = updateChildTasksDepth(designVo);

			System.out.println("기본업데이트=" + result1 + ", 하위업데이트=" + result2);
			return result1;
		} else {
			// 일반 업데이트
			return designDAO.updateDesign(designVo);
		}
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
				updateChild.setStgId(designVo.getStgId()); // 상위 업무와 동일한 stgId
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
		return designDAO.deleteDesign(designVo);
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
