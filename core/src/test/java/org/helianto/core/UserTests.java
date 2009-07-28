package org.helianto.core;

import static org.junit.Assert.*;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>User</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserTests {
    
    /**
     * Test <code>User</code> static factory method.
     */
	@Test
    public void userFactory() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        
        User user = User.userFactory(entity, identity);
        
        assertSame(entity, user.getEntity());
        assertSame(identity, user.getIdentity());
        
    }
    
    /**
     * Test <code>User</code> equals() method.
     */
	@Test
    public void userEquals() {
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
    
    
