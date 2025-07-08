package com.demo.proworks.defect.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.defect.service.DefService;
import com.demo.proworks.defect.vo.DefVo;
import com.demo.proworks.defect.dao.DefDAO;

/**  
 * @subject     : 테스트결함관리 관련 처리를 담당하는 ServiceImpl
 * @description	: 테스트결함관리 관련 처리를 담당하는 ServiceImpl
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Service("defServiceImpl")
public class DefServiceImpl implements DefService {

    @Resource(name="defDAO")
    private DefDAO defDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 테스트결함관리 목록을 조회합니다.
     *
     * @process
     * 1. 테스트결함관리 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<DefVo>을(를) 리턴한다.
     * 
     * @param  defVo 테스트결함관리 DefVo
     * @return 테스트결함관리 목록 List<DefVo>
     * @throws Exception
     */
	public List<DefVo> selectListDef(DefVo defVo) throws Exception {
		List<DefVo> list = defDAO.selectListDef(defVo);	
	
		return list;
	}

    /**
     * 조회한 테스트결함관리 전체 카운트
     *
     * @process
     * 1. 테스트결함관리 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  defVo 테스트결함관리 DefVo
     * @return 테스트결함관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountDef(DefVo defVo) throws Exception {
		return defDAO.selectListCountDef(defVo);
	}

    /**
     * 테스트결함관리를 상세 조회한다.
     *
     * @process
     * 1. 테스트결함관리를 상세 조회한다.
     * 2. 결과 DefVo을(를) 리턴한다.
     * 
     * @param  defVo 테스트결함관리 DefVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public DefVo selectDef(DefVo defVo) throws Exception {
		DefVo resultVO = defDAO.selectDef(defVo);			
        
        return resultVO;
	}

    /**
     * 테스트결함관리를 등록 처리 한다.
     *
     * @process
     * 1. 테스트결함관리를 등록 처리 한다.
     * 
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int insertDef(DefVo defVo) throws Exception {
		return defDAO.insertDef(defVo);	
	}
	
    /**
     * 테스트결함관리를 갱신 처리 한다.
     *
     * @process
     * 1. 테스트결함관리를 갱신 처리 한다.
     * 
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int updateDef(DefVo defVo) throws Exception {				
		return defDAO.updateDef(defVo);	   		
	}

    /**
     * 테스트결함관리를 삭제 처리 한다.
     *
     * @process
     * 1. 테스트결함관리를 삭제 처리 한다.
     * 
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int deleteDef(DefVo defVo) throws Exception {
		return defDAO.deleteDef(defVo);
	}
	
}
