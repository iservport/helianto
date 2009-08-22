package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;

/**
 * Class to support <code>UserAssociationDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserAssociationTestSupport {

    /**
     * Test support method to create a <code>UserAssociation</code>.
     * @param parent optional UserGroup 
     * @param child optional UserGroup 
     */
    public static UserAssociation createUserAssociation(Object... args) {
    	Entity entity = EntityTestSupport.createEntity();
        UserGroup parent;
        try {
            parent = (UserGroup) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            parent = UserGroupTestSupport.createUserGroup(entity);
        }
        UserGroup child;
        try {
            child = (UserGroup) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            child = UserGroupTestSupport.createUserGroup(parent.getEntity());
        }
        UserAssociation userAssociation = UserAssociation.userAssociationFactory(parent, child);
        return userAssociation;
    }

    /**
     * Test support method to create a <code>UserAssociation</code> list.
     *
     * @param userAssociationListSize
     */
    public static List<UserAssociation> createUserAssociationList(int userAssociationListSize) {
        return createUserAssociationList(userAssociationListSize, 1);
    }

    /**
     * Test support method to create a <code>UserAssociation</code> list.
     *
     * @param parentListSize
     * @param childListSize
     */
    public static List<UserAssociation> createUserAssociationList(int parentListSize, int childListSize) {
        List<UserGroup> parentList = UserGroupTestSupport.createUserGroupList(parentListSize);
        List<UserGroup> childList = UserGroupTestSupport.createUserGroupList(childListSize);
        return createUserAssociationList(parentList, childList);
    }

    /**
     * Test support method to create a <code>UserAssociation</code> list.
     *
     * @param parentList
     * @param childList
     */
    public static List<UserAssociation> createUserAssociationList( List<UserGroup> parentList, List<UserGroup> childList) {
        List<UserAssociation> userAssociationList = new ArrayList<UserAssociation>();
        for (UserGroup parent: parentList) {
        	for (UserGroup child: childList) {
    	        userAssociationList.add(createUserAssociation(parent, child));
        	}
        }
        return userAssociationList;
    }

}
