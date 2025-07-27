package com.demo.proworks.att.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.cmmn.ProworksCommVO;


/**  
 * @subject     : 파일관리 관련 처리를 담당하는 인터페이스
 * @description : 파일관리 관련 처리를 담당하는 인터페이스
 * @author      : 우민지
 * @since       : 2025/07/11
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/11			 우민지	 		최초 생성
 * 
 */
public interface AttService {
	
    /**
     * 멀티파일 업로드 처리
     * 여러 개의 파일을 동시에 업로드한다.
     *
     * @param  files MultipartFile[] 업로드할 파일 배열
     * @param  refType String 참조 타입 (ISSUE_RISK, DEFECT 등)
     * @param  refId String 참조 ID
     * @return 업로드된 파일 목록 List<AttVo>
     * @throws Exception
     */
	public List<AttVo> uploadFiles(MultipartFile[] files, String refType, String refId) throws Exception;
	
    /**
     * 단일 파일 업로드 처리
     * 하나의 파일을 업로드한다.
     *
     * @param  file MultipartFile 업로드할 파일
     * @param  refType String 참조 타입 (ISSUE_RISK, DEFECT 등)
     * @param  refId String 참조 ID
     * @return 업로드된 파일 정보 AttVo
     * @throws Exception
     */
	public AttVo uploadSingleFile(MultipartFile file, String refType, String refId) throws Exception;
	
    /**
     * 파일 목록 조회
     * 참조 타입과 참조 ID로 관련 파일 목록을 조회한다.
     *
     * @param  refType String 참조 타입 (ISSUE_RISK, DEFECT 등)
     * @param  refId String 참조 ID
     * @return 파일 목록 List<AttVo>
     * @throws Exception
     */
	public List<AttVo> getFileList(ProworksCommVO commVo) throws Exception;
		
    /**
     * 파일 다운로드
     * S3에서 파일을 다운로드한다.
     *
     * @param  fileId String 파일 ID
     * @return S3Object S3 파일 객체
     * @throws Exception
     */
	public S3Object downloadFile(String fileId) throws Exception;
	
    /**
     * 파일 삭제 처리
     * S3와 DB에서 파일을 삭제한다. (논리 삭제)
     *
     * @param  fileId String 파일 ID
     * @throws Exception
     */
	public void deleteFile(String fileId) throws Exception;
	
    /**
     * 파일 상세 정보 조회
     * 파일 ID로 파일의 상세 정보를 조회한다.
     *
     * @param  fileId String 파일 ID
     * @return 파일 상세 정보 AttVo
     * @throws Exception
     */
	public AttVo getFileInfo(String fileId) throws Exception;
	
	void deleteFilesByRef(String refType, String refId) throws Exception;
	
}