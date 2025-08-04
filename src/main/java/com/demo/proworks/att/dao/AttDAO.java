package com.demo.proworks.att.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.cmmn.ProworksCommVO;
import com.demo.proworks.iss.vo.IssVo;
import com.demo.proworks.att.dao.AttDAO;

/**  
 * @subject     : 파일관리 관련 처리를 담당하는 DAO
 * @description : 파일관리 관련 처리를 담당하는 DAO
 * @author      : 우민지
 * @since       : 2025/07/11
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/11			 우민지	 		최초 생성
 * 
 */
@Repository("attDAO")
public class AttDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * file 테이블에 파일 정보를 등록한다.
     *  
     * @param  AttVo 파일 정보
     * @return 등록 결과
     * @throws ElException
     */
    public int insertFile(AttVo vo) throws ElException {
        return insert("com.demo.proworks.att.insertFile", vo);
    }

    /**
     * file_attachments 테이블에 첨부 정보를 등록한다.
     *  
     * @param  AttVo 첨부 정보
     * @return 등록 결과
     * @throws ElException
     */
    public int insertFileAttachment(AttVo vo) throws ElException {
        return insert("com.demo.proworks.att.insertFileAttachment", vo);
    }

    /**
     * 파일첨부 정보를 수정한다.
     *  
     * @param  AttVo 파일첨부
     * @return 수정 결과
     * @throws ElException
     */
    public int updateFileAttachment(AttVo vo) throws ElException {
        return update("com.demo.proworks.att.updateFileAttachment", vo);
    }

    /**
     * 파일첨부 정보를 논리 삭제한다.
     *  
     * @param  String fileId
     * @return int 삭제 결과
     * @throws ElException
     */
    public int deleteFileAttachment(String fileId) throws ElException {
        return update("com.demo.proworks.att.deleteFileAttachment", fileId);
    }

    /**
     * 파일 상세 정보를 조회한다. (JOIN)
     *  
     * @param  String fileId
     * @return 파일 상세 정보
     * @throws ElException
     */
    public AttVo selectFileInfo(String fileId) throws ElException {
        return (AttVo) selectByPk("com.demo.proworks.att.selectFileInfo", fileId);
    }

    /**
     * 파일첨부 목록을 조회한다. (JOIN)
     *  
     * @param  AttVo 파일첨부 검색 조건
     * @return 파일첨부 목록
     * @throws ElException
     */
    public List<AttVo> selectFileList(AttVo vo) throws ElException {      	
        return (List<AttVo>)list("com.demo.proworks.att.selectFileList", vo);
    }

    /**
     * 참조 정보로 파일 목록을 조회한다.
     *  
     * @param  검색 조건 (IssVo vo)
     * @return 파일 목록
     * @throws ElException
     */
    public List<AttVo> selectFileListByRef(ProworksCommVO vo) throws ElException {
        return (List<AttVo>)list("com.demo.proworks.att.selectFileListByRef", vo);
    }

    /**
     * 임시 업로드 파일들의 참조 정보를 업데이트한다.
     *  
     * @param  Map<String, String> 업데이트 조건
     * @return int 업데이트 결과
     * @throws ElException
     */
    public int updateFileReference(Map<String, String> paramMap) throws ElException {
        return update("com.demo.proworks.att.updateFileReference", paramMap);
    }

    /**
     * 임시 업로드 파일들을 삭제한다. (롤백용)
     *  
     * @param  String tempRefId 임시 참조 ID
     * @return 삭제 결과
     * @throws ElException
     */
    public int deleteTempFiles(String tempRefId) throws ElException {
        return delete("com.demo.proworks.att.deleteTempFiles", tempRefId);
    }
    
    /**
     * 파일첨부 정보를 삭제한다.
     *  
     * @param  String fileId
     * @return 삭제 결과
     * @throws ElException
     */
    public int hardDeleteFileAttachment(String fileId) throws ElException {
        return delete("com.demo.proworks.att.hardDeleteFileAttachment", fileId);
    }
    
    /**
     * 파일 정보 완전 삭제
     *  
     * @param  String fileId
     * @return int 삭제 결과
     * @throws ElException
     */
    public int hardDeleteFile(String fileId) throws ElException {
        return delete("com.demo.proworks.att.hardDeleteFile", fileId);
    }
    
    
    /**
     * 특정 참조타입/참조ID의 모든 파일 완전 삭제
     *  
     * @param  Map<String, String> refType, refId
     * @return 삭제 결과
     * @throws ElException
     */
    public int hardDeleteFilesByRef(Map<String, String> paramMap) throws ElException {
        return delete("com.demo.proworks.att.hardDeleteFilesByRef", paramMap);
    }
    
    
}