package org.helianto.core;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Identity;

import org.helianto.core.UserGroup;

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
        Identity identity = new Identity();
        
        UserGroup userGroup = UserGroup.userGroupFactory(entity, identity);
        
        assertSame(entity, userGroup.getEntity());
        assertSame(identity, userGroup.getIdentity());
        
    }
    
    /**
     * Test <code>UserGroup</code> equals() method.
     */
    public void testUserGroupEquals() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        
        UserGroup userGroup = UserGroup.userGroupFactory(entity, identity);
        UserGroup copy = (UserGroup) DomainTestSupport.minimalEqualsTest(userGroup);
        
        copy.setEntity(null);
        copy.setIdentity(identity);
        assertFalse(userGroup.equals(copy));

        copy.setEntity(entity);
        copy.setIdentity(null);
        assertFalse(userGroup.equals(copy));

        copy.setEntity(entity);
        copy.setIdentity(identity);

        assertTrue(userGroup.equals(copy));
    }

}
    
    
