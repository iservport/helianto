package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;



import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.Identity;
import org.helianto.core.test.IdentityTestSupport;

import org.helianto.core.UserGroup;

/**
 * Class to support <code>UserGroupDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupTestSupport {

    /**
     * Test support method to create a <code>UserGroup</code>.
     * @param entity optional Entity 
     * @param identity optional Identity 
     */
    public static UserGroup createUserGroup(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        Identity identity;
        try {
            identity = (Identity) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            identity = IdentityTestSupport.createIdentity();
        }
        UserGroup userGroup = UserGroup.userGroupFactory(entity, identity);
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
     * @param identityListSize
     */
    public static List<UserGroup> createUserGroupList(int entityListSize, int identityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        List<Identity> identityList = IdentityTestSupport.createIdentityList(identityListSize);
        return createUserGroupList(entityList, identityList);
    }

    /**
     * Test support method to create a <code>UserGroup</code> list.
     *
     * @param entityList
     * @param identityList
     */
    public static List<UserGroup> createUserGroupList(List<Entity> entityList, List<Identity> identityList) {
        List<UserGroup> userGroupList = new ArrayList<UserGroup>();
        for (Entity entity: entityList) {
        	for (Identity identity: identityList) {
    	        userGroupList.add(createUserGroup(entity, identity));
        	}
        }
        return userGroupList;
    }

}
