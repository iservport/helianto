package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.user.domain.UserGroup;

/**
 * Class to support <code>UserGroupDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupTestSupport {
	
	static int testKey = 0;

    /**
     * Test support method to create a <code>UserGroup</code>.
     */
    public static UserGroup createUserGroup(int id) {
    	UserGroup userGroup = UserGroupTestSupport.createUserGroup();
    	userGroup.setId(id);
    	return userGroup;
    }

    /**
     * Test support method to create a <code>UserGroup</code>.
     */
    public static UserGroup createUserGroup() {
        return UserGroupTestSupport.createUserGroup(EntityTestSupport.createEntity());
    }

    /**
     * Test support method to create a <code>UserGroup</code>.
     * @param entity 
     */
    public static UserGroup createUserGroup(Entity entity) {
        return UserGroupTestSupport.createUserGroup(entity, DomainTestSupport.getNonRepeatableStringValue(testKey++, 40));
    }

    /**
     * Test support method to create a <code>UserGroup</code>.
     * @param entity 
     * @param userKey
     */
    public static UserGroup createUserGroup(Entity entity, String userKey) {
        UserGroup userGroup = new UserGroup(entity, userKey);
    	return userGroup;
    }

    /**
     * Test support method to create a <code>UserGroup</code> list.
     *
     * @param userGroupListSize
     */
    public static List<UserGroup> createUserGroupList(int userGroupListSize) {
        return createUserGroupList(1, userGroupListSize);
    }

    /**
     * Test support method to create a <code>UserGroup</code> list.
     *
     * @param entityListSize
     * @param userGroupListSize
     */
    public static List<UserGroup> createUserGroupList(int entityListSize, int userGroupListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        return createUserGroupList(entityList, userGroupListSize);
    }

    /**
     * Test support method to create a <code>UserGroup</code> list.
     *
     * @param entityList
     * @param userGroupListSize
     */
    public static List<UserGroup> createUserGroupList(List<Entity> entityList, int userGroupListSize) {
        List<UserGroup> userGroupList = new ArrayList<UserGroup>();
        for (Entity entity: entityList) {
        	for (int i=0;i<userGroupListSize;i++) {
    	        userGroupList.add(createUserGroup(entity));
        	}
        }
        return userGroupList;
    }

}
