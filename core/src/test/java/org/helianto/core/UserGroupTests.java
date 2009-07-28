package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>UserGroup</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupTests {
    
    /**
     * Test <code>UserGroup</code> static factory method.
     */
	@Test
    public void userGroupFactory() {
        Entity entity = new Entity();
        
        UserGroup userGroup = UserGroup.userGroupFactory(entity, "userKey");
        
        assertSame(entity, userGroup.getEntity());
        assertEquals("userKey", userGroup.getUserKey());
        
    }
    
    /**
     * Test <code>UserGroup</code> equals() method.
     */
	@Test
    public void userGroupEquals() {
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
    
    
