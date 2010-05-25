package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;

/**
 * Class to support <code>UserDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserTestSupport {
	
    /**
     * Test support method to create a <code>User</code>.
     */
	public static User createUser() {
		return createUser(EntityTestSupport.createEntity());
	}

    /**
     * Test support method to create a <code>User</code>.
     * @param entity optional Entity 
     */
	public static User createUser(Entity entity) {
		return createUser(entity, IdentityTestSupport.createIdentity());
	}

    /**
     * Test support method to create a <code>User</code>.
     * @param entity optional Entity 
     * @param identity optional Identity 
     */
	public static User createUser(Entity entity, Identity identity) {
        User user = User.userFactory(entity, identity);
        return user;		
	}

    /**
     * Test support method to create a <code>User</code> list.
     *
     * @param userListSize
     */
    public static List<User> createUserList(int userListSize) {
        return createUserList(1, userListSize);
    }

    /**
     * Test support method to create a <code>User</code> list.
     *
     * @param entityListSize
     * @param identityListSize
     */
    public static List<User> createUserList(int entityListSize, int identityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        List<Identity> identityList = IdentityTestSupport.createIdentityList(identityListSize);
        return createUserList(entityList, identityList);
    }

    /**
     * Test support method to create a <code>User</code> list.
     *
     * @param entityList
     * @param identityList
     */
    public static List<User> createUserList(List<Entity> entityList, List<Identity> identityList) {
        List<User> userList = new ArrayList<User>();
        for (Entity entity: entityList) {
        	for (Identity identity: identityList) {
    	        userList.add(createUser(entity, identity));
        	}
        }
        return userList;
    }

}
