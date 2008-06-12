package org.helianto.core.orm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.UserGroupDao;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>UserGroupDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupDaoImplTests extends AbstractHibernateIntegrationTest {
    
    private UserGroupDao userGroupDao;
    
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
    
    public void testPersistUserGroupAndChildren() {
        // if userGroup is persisted children should also be
        UserGroup child1 = UserGroupTestSupport.createUserGroup();
        UserGroup child2 = UserGroupTestSupport.createUserGroup();
        UserGroup user = UserGroupTestSupport.createUserGroup();
        UserAssociation.userAssociationFactory(user, child1);
        UserAssociation.userAssociationFactory(child1, child2);
        userGroupDao.persistUserGroup(user);
        userGroupDao.flush();
        userGroupDao.clear();
        
        UserGroup found = userGroupDao.findUserGroupByNaturalId(user.getEntity(), user.getIdentity());
        assertEquals(1, found.getChildAssociations().size());
        Set<UserGroup> userSet = new HashSet<UserGroup>();
        for (UserAssociation a: found.getChildAssociations()) {
            assertEquals(user, a.getParent());
            userSet.add(a.getChild());
        }
        assertTrue(userSet.contains(child1));

        found = userGroupDao.findUserGroupByNaturalId(child1.getEntity(), child1.getIdentity());
        assertEquals(1, found.getChildAssociations().size());
        userSet = new HashSet<UserGroup>();
        for (UserAssociation a: found.getChildAssociations()) {
            assertEquals(child1, a.getParent());
            userSet.add(a.getChild());
        }
        assertTrue(userSet.contains(child2));
        
    }
    
    public void testFindUserByCriteria() {
    	List<Identity> identityList =  IdentityTestSupport.createIdentityList(5);
		List<Entity> entityList = EntityTestSupport.createEntityList(3);
		persistUserList(UserTestSupport.createUserList(entityList, identityList));
    	String criteria = "user.identity.id = "+identityList.get(0).getId();
		List<User> userList = userGroupDao.findUserByCriteria(criteria);
		assertEquals(userList.size(), entityList.size());
    }
    
    /*
     * help to write a list
     */
    private void persistUserGroupList(List<UserGroup> userGroupList) {
    	for (UserGroup userGroup: userGroupList) {
    		userGroupDao.persistUserGroup(userGroup);
    	}
    	userGroupDao.flush();
    	userGroupDao.clear();
    }

    /*
     * help to write a list
     */
    private void persistUserList(List<User> userList) {
    	for (User user: userList) {
    		userGroupDao.persistUserGroup(user);
    	}
    	userGroupDao.flush();
    	userGroupDao.clear();
    }

    //- setters

    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }
    
}

