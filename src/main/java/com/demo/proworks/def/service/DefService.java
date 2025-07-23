package com.demo.proworks.def.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.def.vo.DefVo;
import com.demo.proworks.unit.vo.UnitTestVo;

/**  
 * @subject     : 테스트결함관리 관련 처리를 담당하는 인터페이스 (Enhanced)
 * @description : 테스트결함관리 관련 처리를 담당하는 인터페이스 (자동 생성 기능 추가)
 * @author      : 우민지
 * @since       : 2025/07/23
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/23			 우민지	 		최초 생성
 * 2025/07/23			 시스템			자동 생성 기능 추가
 * 
 */
public interface DefService {
	
    /**
     * 테스트결함관리 페이징 처리하여 목록을 조회한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 테스트결함관리 목록 List<DefVo>
     * @throws Exception
     */
	public List<DefVo> selectListDef(DefVo defVo) throws Exception;
	
    /**
     * 조회한 테스트결함관리 전체 카운트
     * 
     * @param  defVo 테스트결함관리 DefVo
     * @return 테스트결함관리 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountDef(DefVo defVo) throws Exception;
	
    /**
     * 테스트결함관리를 상세 조회한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public DefVo selectDef(DefVo defVo) throws Exception;
		
    /**
     * 테스트결함관리를 등록 처리 한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int insertDef(DefVo defVo) throws Exception;
	
    /**
     * 테스트결함관리를 갱신 처리 한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int updateDef(DefVo defVo) throws Exception;
	
    /**
     * 테스트결함관리를 삭제 처리 한다.
     *
     * @param  defVo 테스트결함관리 DefVo
     * @return 번호
     * @throws Exception
     */
	public int deleteDef(DefVo defVo) throws Exception;
	
	// ========== 자동 생성 기능 관련 메서드들 ==========
	
    /**
     * 단위테스트 실패 시 자동으로 결함을 생성한다.
     *
     * @param  unitTestVo 실패한 단위테스트 정보
     * @return 생성된 결함 ID
     * @throws Exception
     */
    public String createDefectFromFailedTest(UnitTestVo unitTestVo) throws Exception;
    
    /**
     * 결함 상태가 '완료'로 변경될 때 관련 단위테스트를 '성공'으로 업데이트한다.
     *
     * @param  defectId 완료 처리된 결함 ID
     * @throws Exception
     */
    public void updateTestStatusOnDefectComplete(String defectId) throws Exception;
    
    /**
     * 결함 상태를 변경하고 이력을 기록한다.
     *
     * @param  defVo 결함 정보 (id, status 포함)
     * @throws Exception
     */
    public void updateDefectStatus(DefVo defVo) throws Exception;
    
    /**
     * 결함 통계 정보를 조회한다.
     *
     * @param  defVo 검색 조건
     * @return 통계 정보 Map
     * @throws Exception
     */
    public Map<String, Object> selectDefectStatistics(DefVo defVo) throws Exception;
    
    /**
     * 수정 기한이 임박한 결함 목록을 조회한다.
     *
     * @param  defVo 검색 조건
     * @return 임박한 결함 목록
     * @throws Exception
     */
    public List<DefVo> selectUpcomingDefects(DefVo defVo) throws Exception;
    
    /**
     * 결함과 파일을 함께 등록한다.
     *
     * @param  defVo 결함 정보
     * @param  files 첨부 파일 배열
     * @return 등록된 결함 정보
     * @throws Exception
     */
    public DefVo insertDefWithFiles(DefVo defVo, MultipartFile[] files) throws Exception;
    
    /**
     * 결함과 파일을 함께 수정한다.
     *
     * @param  defVo 결함 정보 (id 필수)
     * @param  files 새로 업로드할 파일 배열 (null 가능)
     * @return 수정된 결함 정보
     * @throws Exception
     */
    public DefVo updateDefWithFiles(DefVo defVo, MultipartFile[] files) throws Exception;
    
    /**
     * 결함 첨부파일 목록 조회
     *
     * @param  defVo 조회 조건 (id 필수)
     * @return 첨부파일 목록
     * @throws Exception
     */
    public List<AttVo> selectDefFileList(DefVo defVo) throws Exception;
    
    /**
     * 결함 첨부파일 삭제
     *
     * @param  fileId 삭제할 파일 ID
     * @return 삭제 결과
     * @throws Exception
     */
    public int deleteDefFile(String fileId) throws Exception;
    
    /**
     * 테스트 결과 업데이트 (단위테스트 상태 변경용)
     *
     * @param  unitTestVo 업데이트할 단위테스트 정보
     * @return 업데이트 결과
     * @throws Exception
     */
    public int updateTestResult(UnitTestVo unitTestVo) throws Exception;
}