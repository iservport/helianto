package org.helianto.core;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Identity;

import org.helianto.core.User;

/**
 * <code>User</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserTests extends TestCase {
    
    /**
     * Test <code>User</code> static factory method.
     */
    public void testUserFactory() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        
        User user = User.userFactory(entity, identity);
        
        assertSame(entity, user.getEntity());
        assertSame(identity, user.getIdentity());
        
    }
    
    /**
     * Test <code>User</code> equals() method.
     */
    public void testUserEquals() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        identity.setPrincipal("principal");
        
        User user = User.userFactory(entity, identity);
        User copy = (User) DomainTestSupport.minimalEqualsTest(user);
        
        copy.setEntity(null);
        copy.setIdentity(identity);
        assertFalse(user.equals(copy));

        copy.setEntity(entity);
        copy.setIdentity(null);
        assertFalse(user.equals(copy));

        copy.setEntity(entity);
        copy.setIdentity(identity);

        assertTrue(user.equals(copy));
    }

}
    
    
