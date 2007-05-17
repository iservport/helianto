package org.helianto.core.orm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.UserGroupDao;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.UserGroupTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>UserGroupDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupDaoImplTests extends AbstractIntegrationTest {
    
    private UserGroupDao userGroupDao;
    
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
     * Hook to persist one <code>UserGroup</code>.
     */  
    protected UserGroup writeUserGroup() {
        UserGroup userGroup = UserGroupTestSupport.createUserGroup();
        userGroupDao.persistUserGroup(userGroup);
        userGroupDao.flush();
        userGroupDao.clear();
        return userGroup;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneUserGroup() {
        UserGroup userGroup = writeUserGroup();

        assertEquals(userGroup,  userGroupDao.findUserGroupByNaturalId(userGroup.getEntity(), userGroup.getIdentity()));
    }
    
    /*
     * Hook to persist a <code>UserGroup</code> list.
     */  
    protected List<UserGroup> writeUserGroupList() {
        int entityListSize = 2;
        int identityListSize = 3;
        List<UserGroup> userGroupList = UserGroupTestSupport.createUserGroupList(entityListSize, identityListSize);
        assertEquals(entityListSize * identityListSize, userGroupList.size());
        for (UserGroup userGroup: userGroupList) {
            userGroupDao.persistUserGroup(userGroup);
        }
        userGroupDao.flush();
        userGroupDao.clear();
        return userGroupList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListUserGroup() {
        List<UserGroup> userGroupList = writeUserGroupList();

        UserGroup userGroup = userGroupList.get((int) (Math.random()*userGroupList.size()));
        assertEquals(userGroup,  userGroupDao.findUserGroupByNaturalId(userGroup.getEntity(), userGroup.getIdentity()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testUserGroupDuplicate() {
        UserGroup userGroup =  writeUserGroup();
        UserGroup userGroupCopy = UserGroupTestSupport.createUserGroup(userGroup.getEntity(), userGroup.getIdentity());

        try {
            userGroupDao.mergeUserGroup(userGroupCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveUserGroup() {
        List<UserGroup> userGroupList = writeUserGroupList();
        UserGroup userGroup = userGroupList.get((int) (Math.random()*userGroupList.size()));
        userGroupDao.removeUserGroup(userGroup);

        assertNull(userGroupDao.findUserGroupByNaturalId(userGroup.getEntity(), userGroup.getIdentity()));
    }
    
    //- additional
    
    public void testPersistUserGroupAndParents() {
        // if userGroup is persisted parents should also be
        UserGroup parent1 = UserGroupTestSupport.createUserGroup();
        UserGroup parent2 = UserGroupTestSupport.createUserGroup();
        UserGroup user = UserGroupTestSupport.createUserGroup();
        UserAssociation.userAssociationFactory(parent1, user);
        UserAssociation.userAssociationFactory(parent2, user);
        userGroupDao.persistUserGroup(user);
        userGroupDao.flush();
        userGroupDao.clear();
        
        UserGroup found = userGroupDao.findUserGroupByNaturalId(user.getEntity(), user.getIdentity());
        assertEquals(2, found.getParentAssociations().size());
        Set<UserGroup> userSet = new HashSet<UserGroup>();
        for (UserAssociation a: found.getParentAssociations()) {
            assertEquals(user, a.getChild());
            userSet.add(a.getParent());
        }
        assertTrue(userSet.contains(parent1));
        assertTrue(userSet.contains(parent2));
        
    }
    


    //- setters

    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }
    
}

