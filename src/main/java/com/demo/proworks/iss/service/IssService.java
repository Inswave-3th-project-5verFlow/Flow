package com.demo.proworks.iss.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.iss.vo.IssVo;

/**  
 * @subject     : 이슈리스크관리 관련 처리를 담당하는 인터페이스
 * @description : 이슈리스크관리 관련 처리를 담당하는 인터페이스
 * @author      : 우민지
 * @since       : 2025/07/07
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/07			 우민지	 		최초 생성
 * 
 */
public interface IssService {
	
    /**
     * 이슈리스크관리 상세 조회한다.
     *
     * @param  vo 이슈리스크관리
     * @return 이슈리스크관리
     * @throws Exception
     */
    public IssVo selectIss(IssVo vo) throws Exception;
    
    /**
     * 페이징을 처리하여 이슈리스크관리 목록조회를 한다.
     *
     * @param  vo 이슈리스크관리
     * @return 이슈리스크관리 목록
     * @throws Exception
     */
    public List<IssVo> selectListIss(IssVo vo) throws Exception;
    
    /**
     * 이슈리스크관리 목록 조회의 전체 카운트를 조회한다.
     *
     * @param  vo 이슈리스크관리
     * @return 전체 카운트
     * @throws Exception
     */
    public long selectListCountIss(IssVo vo) throws Exception;
    
    /**
     * 이슈리스크관리를 등록한다.
     *
     * @param  vo 이슈리스크관리
     * @throws Exception
     */
    public void insertIss(IssVo vo) throws Exception;
    
    /**
     * 이슈리스크관리를 갱신한다.
     *
     * @param  vo 이슈리스크관리
     * @throws Exception
     */
    public void updateIss(IssVo vo) throws Exception;
    
    /**
     * 이슈리스크관리를 삭제한다.
     *
     * @param  vo 이슈리스크관리
     * @throws Exception
     */
    public void deleteIss(IssVo vo) throws Exception;
    
    /**
     * 이슈와 파일을 함께 등록한다. (트랜잭션)
     * 이슈 등록과 파일 업로드가 모두 성공해야 커밋되며,
     * 하나라도 실패하면 전체가 롤백된다.
     *
     * @param  issVo 이슈리스크 정보
     * @param  files 업로드할 파일 배열 (null 가능)
     * @return 등록된 이슈 정보 (생성된 ID 포함)
     * @throws Exception 등록 실패 시
     */
    public IssVo insertIssWithFiles(IssVo issVo, MultipartFile[] files) throws Exception;
    
    /**
     * 이슈와 파일을 함께 수정한다. (트랜잭션)
     * 이슈 수정과 새 파일 업로드가 모두 성공해야 커밋되며,
     * 하나라도 실패하면 전체가 롤백된다.
     *
     * @param  issVo 이슈리스크 정보 (ID 필수)
     * @param  files 새로 업로드할 파일 배열 (null 가능)
     * @return 수정된 이슈 정보
     * @throws Exception 수정 실패 시
     */
    public IssVo updateIssWithFiles(IssVo issVo, MultipartFile[] files) throws Exception;

	public void insertIssWithStoredFiles(IssVo issVo, List<File> fileList, List<String> orgNameList);
	
}