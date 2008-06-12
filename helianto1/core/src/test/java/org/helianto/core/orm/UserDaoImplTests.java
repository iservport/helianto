package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.User;
import org.helianto.core.dao.UserDao;
import org.helianto.core.dao.UserGroupDao;
import org.helianto.core.test.UserTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>UserDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class UserDaoImplTests extends AbstractHibernateIntegrationTest {

    private UserDao userDao;
    private UserGroupDao userGroupDao;
    
    /*
     * Hook to persist one <code>User</code>.
     */  
    protected User writeUser() {
        User user = UserTestSupport.createUser();
        userGroupDao.persistUserGroup(user);
        userDao.flush();
        userDao.clear();
        return user;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneUser() {
        User user = writeUser();

        assertEquals(user,  userDao.findUserByNaturalId(user.getEntity(), user.getIdentity()));
    }
    
    /*
     * Hook to persist a <code>User</code> list.
     */  
    protected List<User> writeUserList() {
        int entityListSize = 2;
        int identityListSize = 3;
        List<User> userList = UserTestSupport.createUserList(entityListSize, identityListSize);
        assertEquals(entityListSize * identityListSize, userList.size());
        for (User user: userList) {
        	userGroupDao.persistUserGroup(user);
        }
        userDao.flush();
        userDao.clear();
        return userList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListUser() {
        List<User> userList = writeUserList();

        User user = userList.get((int) (Math.random()*userList.size()));
        assertEquals(user,  userDao.findUserByNaturalId(user.getEntity(), user.getIdentity()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testUserDuplicate() {
        User user =  writeUser();
        User userCopy = UserTestSupport.createUser(user.getEntity(), user.getIdentity());

        try {
        	userGroupDao.mergeUserGroup(userCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveUser() {
        List<User> userList = writeUserList();
        User user = userList.get((int) (Math.random()*userList.size()));
        userGroupDao.removeUserGroup(user);

        assertNull(userDao.findUserByNaturalId(user.getEntity(), user.getIdentity()));
    }

    //- setters

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }
    
}

