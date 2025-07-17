package com.demo.proworks.task.design.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.task.design.vo.DesignVo;
import com.demo.proworks.task.design.dao.DesignDAO;

/**
 * @subject : 설계 업무 정보 관련 처리를 담당하는 DAO
 * @description : 설계 업무 정보 관련 처리를 담당하는 DAO
 * @author : 백승호
 * @since : 2025/07/05
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/05 백승호 최초 생성
 * 
 */
@Repository("designDAO")
public class DesignDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 설계 업무 정보 상세 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return DesignVo 설계 업무 정보
	 * @throws ElException
	 */
	public DesignVo selectDesign(DesignVo vo) throws ElException {
		return (DesignVo) selectByPk("com.demo.proworks.task.design.selectDesign", vo);
	}

	/**
	 * 하위 업무 정보 목록을 새로운 depth와 함께 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectChildTasks(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectChildTasks", vo);
	}

	/**
	 * 페이징을 처리하여 모든 업무 정보 목록조회를 한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectTasks(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectTasks", vo);
	}

	/**
	 * 페이징을 처리하여 설계 업무 정보 목록조회를 한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectListDesign(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectListDesign", vo);
	}

	/**
	 * 페이징을 처리하여 개발 업무 정보 목록조회를 한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectListDevelop(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectListDevelop", vo);
	}

	/**
	 * 설계 업무 정보 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 설계 업무 정보 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountDesign(DesignVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.task.design.selectListCountDesign", vo);
	}

	/**
	 * 개발 업무 정보 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 설계 업무 정보 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountDevelop(DesignVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.task.design.selectListCountDevelop", vo);
	}

	/**
	 * 설계 업무 정보를 등록한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int insertDesign(DesignVo vo) throws ElException {
		return insert("com.demo.proworks.task.design.insertDesign", vo);
	}

	/**
	 * 설계 업무 정보를 갱신한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int updateDesign(DesignVo vo) throws ElException {
		return update("com.demo.proworks.task.design.updateDesign", vo);
	}

	/**
	 * 특정 업무의 모든 하위업무들의 depth를 재계산하여 업데이트한다.
	 * 
	 * @param DesignVo 설계 업무 정보 (taskId 필요)
	 * @return 번호
	 * @throws ElException
	 */
	public int updateChildTasksDepth(DesignVo vo) throws ElException {
		return update("com.demo.proworks.task.design.updateChildTasksDepth", vo);
	}

	/**
	 * 설계 업무 정보를 삭제한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return 번호
	 * @throws ElException
	 */
	public int deleteDesign(DesignVo vo) throws ElException {
		return delete("com.demo.proworks.task.design.deleteDesign", vo);
	}

	/**
	 * 트리 구조에 맞게 설계 업무 목록을 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectTreeListDesign(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectTreeListDesign", vo);
	}

	/**
	 * 트리 구조에 맞게 개발 업무 목록을 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectTreeListDevelop(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectTreeListDevelop", vo);
	}

	/**
	 * 트리 구조에 맞게 모든 업무 목록을 조회한다.
	 * 
	 * @param DesignVo 설계 업무 정보
	 * @return List<DesignVo> 설계 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectTreeList(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectTreeList", vo);
	}

	/**
	 * 모든 단계의 업무를 정렬된 형태로 조회한다.
	 * 
	 * @param DesignVo 업무 정보
	 * @return List<DesignVo> 업무 정보
	 * @throws ElException
	 */
	public List<DesignVo> selectListTask(DesignVo vo) throws ElException {
		return (List<DesignVo>) list("com.demo.proworks.task.design.selectListTask", vo);
	}

}
