package org.helianto.core.orm;

import java.util.Date;
import java.util.List;

import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserLogDao;
import org.helianto.core.test.UserLogTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>UserLogDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class UserLogDaoImplTests extends AbstractHibernateIntegrationTest {

    private UserLogDao userLogDao;
    
    /*
     * Hook to persist one <code>UserLog</code>.
     */  
    protected UserLog writeUserLog() {
        UserLog userLog = UserLogTestSupport.createUserLog();
        userLogDao.persistUserLog(userLog);
        userLogDao.flush();
        userLogDao.clear();
        return userLog;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneUserLog() {
        UserLog userLog = writeUserLog();

        assertEquals(userLog,  userLogDao.findUserLogByNaturalId(userLog.getUser(), userLog.getLastEvent()));
    }
    
    /*
     * Hook to persist a <code>UserLog</code> list.
     */  
    protected List<UserLog> writeUserLogList() {
        int userLogListSize = 10;
        int userListSize = 2;
        List<UserLog> userLogList = UserLogTestSupport.createUserLogList(userLogListSize, userListSize);
        assertEquals(userLogListSize * userListSize, userLogList.size());
        for (UserLog userLog: userLogList) {
            userLogDao.persistUserLog(userLog);
        }
        userLogDao.flush();
        userLogDao.clear();
        return userLogList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListUserLog() {
        List<UserLog> userLogList = writeUserLogList();

        UserLog userLog = userLogList.get((int) (Math.random()*userLogList.size()));
        assertEquals(userLog,  userLogDao.findUserLogByNaturalId(userLog.getUser(), userLog.getLastEvent()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testUserLogDuplicate() {
        UserLog userLog =  writeUserLog();
        UserLog userLogCopy = UserLogTestSupport.createUserLog(userLog.getUser(), userLog.getLastEvent());

        try {
            userLogDao.mergeUserLog(userLogCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveUserLog() {
        List<UserLog> userLogList = writeUserLogList();
        UserLog userLog = userLogList.get((int) (Math.random()*userLogList.size()));
        userLogDao.removeUserLog(userLog);

        assertNull(userLogDao.findUserLogByNaturalId(userLog.getUser(), userLog.getLastEvent()));
    }
    
    //- additional tests

    public void testFindLastUserLog() {
    	// make 5 logs for each of 2 users
    	List<User> userList = UserTestSupport.createUserList(2);
        List<UserLog> userLogList = UserLogTestSupport.createUserLogList(5, userList);
        // pick one to be the last
        UserLog lastUserLog = userLogList.get((int) (Math.random()*userLogList.size()));
        lastUserLog.setLastEvent(new Date(Long.MAX_VALUE));
        // persist
        for (UserLog userLog: userLogList) {
            userLogDao.persistUserLog(userLog);
        }
        userLogDao.flush();
        userLogDao.clear();
        
        assertEquals(lastUserLog, userLogDao.findLastUserLog(userList));
        
    }
    
    //- setters

    public void setUserLogDao(UserLogDao userLogDao) {
        this.userLogDao = userLogDao;
    }
    
}

