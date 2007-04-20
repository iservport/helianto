package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.UserRole;
import org.helianto.core.dao.UserRoleDao;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.UserRoleTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>UserRoleDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class UserRoleDaoImplTests extends AbstractIntegrationTest {

    private UserRoleDao userRoleDao;
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml",
                "deploy/sessionFactory.xml",
                "deploy/transaction.xml",
                "deploy/support.xml",
                "deploy/core.xml",
                "deploy/org.helianto.core.xml"
                };
    }
    
    /*
     * Hook to persist one <code>UserRole</code>.
     */  
    protected UserRole writeUserRole() {
        UserRole userRole = UserRoleTestSupport.createUserRole();
        userRoleDao.persistUserRole(userRole);
        userRoleDao.flush();
        userRoleDao.clear();
        return userRole;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneUserRole() {
        UserRole userRole = writeUserRole();

        assertEquals(userRole,  userRoleDao.findUserRoleByNaturalId(userRole.getUserGroup(), userRole.getService(), userRole.getServiceExtension()));
    }
    
    /*
     * Hook to persist a <code>UserRole</code> list.
     */  
    protected List<UserRole> writeUserRoleList() {
        int userRoleListSize = 10;
        int userGroupListSize = 2;
        int serviceListSize = 3;
        List<UserRole> userRoleList = UserRoleTestSupport.createUserRoleList(userRoleListSize, userGroupListSize, serviceListSize);
        assertEquals(userRoleListSize * userGroupListSize * serviceListSize, userRoleList.size());
        for (UserRole userRole: userRoleList) {
            userRoleDao.persistUserRole(userRole);
        }
        userRoleDao.flush();
        userRoleDao.clear();
        return userRoleList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListUserRole() {
        List<UserRole> userRoleList = writeUserRoleList();

        UserRole userRole = userRoleList.get((int) (Math.random()*userRoleList.size()));
        assertEquals(userRole,  userRoleDao.findUserRoleByNaturalId(userRole.getUserGroup(), userRole.getService(), userRole.getServiceExtension()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testUserRoleDuplicate() {
        UserRole userRole =  writeUserRole();
        UserRole userRoleCopy = UserRoleTestSupport.createUserRole(userRole.getUserGroup(), userRole.getService(), userRole.getServiceExtension());

        try {
            userRoleDao.mergeUserRole(userRoleCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveUserRole() {
        List<UserRole> userRoleList = writeUserRoleList();
        UserRole userRole = userRoleList.get((int) (Math.random()*userRoleList.size()));
        userRoleDao.removeUserRole(userRole);

        assertNull(userRoleDao.findUserRoleByNaturalId(userRole.getUserGroup(), userRole.getService(), userRole.getServiceExtension()));
    }

    //- setters

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }
    
}

