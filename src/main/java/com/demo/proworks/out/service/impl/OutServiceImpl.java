package com.demo.proworks.out.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.out.service.OutService;
import com.demo.proworks.out.vo.OutVo;
import com.demo.proworks.out.dao.OutDAO;

/**  
 * @subject     : 산출물관리 관련 처리를 담당하는 ServiceImpl
 * @description	: 산출물관리 관련 처리를 담당하는 ServiceImpl
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
@Service("outServiceImpl")
public class OutServiceImpl implements OutService {

    @Resource(name="outDAO")
    private OutDAO outDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 산출물관리 목록을 조회합니다.
     *
     * @process
     * 1. 산출물관리 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<OutVo>을(를) 리턴한다.
     * 
     * @param  outVo 산출물관리 OutVo
     * @return 산출물관리 목록 List<OutVo>
     * @throws Exception
     */
	public List<OutVo> selectListOut(OutVo outVo) throws Exception {
		List<OutVo> list = outDAO.selectListOut(outVo);	
	
		return list;
	}

    /**
     * 조회한 산출물관리 전체 카운트
     *
     * @process
     * 1. 산출물관리 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  outVo 산출물관리 OutVo
     * @return 산출물관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountOut(OutVo outVo) throws Exception {
		return outDAO.selectListCountOut(outVo);
	}

    /**
     * 산출물관리를 상세 조회한다.
     *
     * @process
     * 1. 산출물관리를 상세 조회한다.
     * 2. 결과 OutVo을(를) 리턴한다.
     * 
     * @param  outVo 산출물관리 OutVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public OutVo selectOut(OutVo outVo) throws Exception {
		OutVo resultVO = outDAO.selectOut(outVo);			
        
        return resultVO;
	}

    /**
     * 산출물관리를 등록 처리 한다.
     *
     * @process
     * 1. 산출물관리를 등록 처리 한다.
     * 
     * @param  outVo 산출물관리 OutVo
     * @return 번호
     * @throws Exception
     */
	public int insertOut(OutVo outVo) throws Exception {
		return outDAO.insertOut(outVo);	
	}
	
    /**
     * 산출물관리를 갱신 처리 한다.
     *
     * @process
     * 1. 산출물관리를 갱신 처리 한다.
     * 
     * @param  outVo 산출물관리 OutVo
     * @return 번호
     * @throws Exception
     */
	public int updateOut(OutVo outVo) throws Exception {				
		return outDAO.updateOut(outVo);	   		
	}

    /**
     * 산출물관리를 삭제 처리 한다.
     *
     * @process
     * 1. 산출물관리를 삭제 처리 한다.
     * 
     * @param  outVo 산출물관리 OutVo
     * @return 번호
     * @throws Exception
     */
	public int deleteOut(OutVo outVo) throws Exception {
		return outDAO.deleteOut(outVo);
	}
	
}
