package org.helianto.core;

import java.util.Set;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.UserGroupTestSupport;

/**
 * <code>UserGroup</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupTests extends TestCase {
    
    /**
     * Test <code>UserGroup</code> static factory method.
     */
    public void testUserGroupFactory() {
        Entity entity = new Entity();
        
        UserGroup userGroup = UserGroup.userGroupFactory(entity, "userKey");
        
        assertSame(entity, userGroup.getEntity());
        assertEquals("userKey", userGroup.getUserKey());
        
    }
    
    /**
     * Test <code>UserGroup</code> equals() method.
     */
    public void testUserGroupEquals() {
        Entity entity = new Entity();
        
        UserGroup userGroup = UserGroup.userGroupFactory(entity, "userKey");
        UserGroup copy = (UserGroup) DomainTestSupport.minimalEqualsTest(userGroup);
        
        copy.setEntity(null);
        copy.setUserKey("userKey");
        assertFalse(userGroup.equals(copy));

        copy.setEntity(entity);
        copy.setUserKey("");
        assertFalse(userGroup.equals(copy));

        copy.setEntity(entity);
        copy.setUserKey("userKey");

        assertTrue(userGroup.equals(copy));
    }

}
    
    
