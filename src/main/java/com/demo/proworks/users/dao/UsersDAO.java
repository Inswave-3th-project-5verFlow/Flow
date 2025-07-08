package com.demo.proworks.users.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.users.vo.UsersVo;
import com.demo.proworks.users.dao.UsersDAO;

/**  
 * @subject     : 사용자정보 관련 처리를 담당하는 DAO
 * @description : 사용자정보 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/05
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/05			 Inswave	 		최초 생성
 * 
 */
@Repository("usersDAO")
public class UsersDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 사용자정보 상세 조회한다.
     *  
     * @param  UsersVo 사용자정보
     * @return UsersVo 사용자정보
     * @throws ElException
     */
    public UsersVo selectUsers(UsersVo vo) throws ElException {
        return (UsersVo) selectByPk("com.demo.proworks.users.selectUsers", vo);
    }

    /**
     * 페이징을 처리하여 사용자정보 목록조회를 한다.
     *  
     * @param  UsersVo 사용자정보
     * @return List<UsersVo> 사용자정보
     * @throws ElException
     */
    public List<UsersVo> selectListUsers(UsersVo vo) throws ElException {      	
        return (List<UsersVo>)list("com.demo.proworks.users.selectListUsers", vo);
    }

    /**
     * 사용자정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  UsersVo 사용자정보
     * @return 사용자정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountUsers(UsersVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.users.selectListCountUsers", vo);
    }
        
    /**
     * 사용자정보를 등록한다.
     *  
     * @param  UsersVo 사용자정보
     * @return 번호
     * @throws ElException
     */
    public int insertUsers(UsersVo vo) throws ElException {    	
        return insert("com.demo.proworks.users.insertUsers", vo);
    }

    /**
     * 사용자정보를 갱신한다.
     *  
     * @param  UsersVo 사용자정보
     * @return 번호
     * @throws ElException
     */
    public int updateUsers(UsersVo vo) throws ElException {
        return update("com.demo.proworks.users.updateUsers", vo);
    }

    /**
     * 사용자정보를 삭제한다.
     *  
     * @param  UsersVo 사용자정보
     * @return 번호
     * @throws ElException
     */
    public int deleteUsers(UsersVo vo) throws ElException {
        return delete("com.demo.proworks.users.deleteUsers", vo);
    }
    
    /**
     * 사용자정보, 프로젝트ID, 그룹ID 매핑한다.
     *  
     * @param  UsersVo 사용자정보
     * @return UsersVo 사용자정보
     * @throws ElException
     */
    public UsersVo selectUsersMapping(UsersVo vo) throws ElException {
        return (UsersVo) selectByPk("com.demo.proworks.users.selectUsersMapping", vo);
    }
    
    
    

}
