package com.demo.proworks.out.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.proworks.out.vo.OutVo;

/**  
 * @subject     : 산출물관리 관련 처리를 담당하는 인터페이스
 * @description : 산출물관리 관련 처리를 담당하는 인터페이스
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 2025/07/22			 우민지	 		파일 관련 메서드 추가
 * 
 */
public interface OutService {
	
    /**
     * 산출물관리 상세 조회한다.
     *
     * @param  vo 산출물관리
     * @return 산출물관리
     * @throws Exception
     */
    public OutVo selectOut(OutVo vo) throws Exception;
    
    /**
     * 페이징을 처리하여 산출물관리 목록조회를 한다.
     *
     * @param  vo 산출물관리
     * @return 산출물관리 목록
     * @throws Exception
     */
    public List<OutVo> selectListOut(OutVo vo) throws Exception;
    
    /**
     * 산출물관리 목록 조회의 전체 카운트를 조회한다.
     *
     * @param  vo 산출물관리
     * @return 전체 카운트
     * @throws Exception
     */
    public long selectListCountOut(OutVo vo) throws Exception;
    
    /**
     * 산출물관리를 등록한다.
     *
     * @param  vo 산출물관리
     * @throws Exception
     */
    public void insertOut(OutVo vo) throws Exception;
    
    /**
     * 산출물관리를 갱신한다.
     *
     * @param  vo 산출물관리
     * @throws Exception
     */
    public void updateOut(OutVo vo) throws Exception;
    
    /**
     * 산출물관리를 삭제한다.
     *
     * @param  vo 산출물관리
     * @throws Exception
     */
    public void deleteOut(OutVo vo) throws Exception;
    
    /**
     * 산출물과 파일을 함께 등록한다. (트랜잭션)
     * 산출물 등록과 파일 업로드가 모두 성공해야 커밋되며,
     * 하나라도 실패하면 전체가 롤백된다.
     *
     * @param  outVo 산출물 정보
     * @param  files 업로드할 파일 배열 (null 가능)
     * @return 등록된 산출물 정보 (생성된 ID 포함)
     * @throws Exception 등록 실패 시
     */
    public OutVo insertOutWithFiles(OutVo outVo, MultipartFile[] files) throws Exception;
    
    /**
     * 산출물과 파일을 함께 수정한다. (트랜잭션)
     * 산출물 수정과 새 파일 업로드가 모두 성공해야 커밋되며,
     * 하나라도 실패하면 전체가 롤백된다.
     *
     * @param  outVo 산출물 정보 (ID 필수)
     * @param  files 새로 업로드할 파일 배열 (null 가능)
     * @return 수정된 산출물 정보
     * @throws Exception 수정 실패 시
     */
    public OutVo updateOutWithFiles(OutVo outVo, MultipartFile[] files) throws Exception;
	
}