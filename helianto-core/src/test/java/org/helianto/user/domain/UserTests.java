package org.helianto.user.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.def.UserType;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.junit.Test;

/**
 * <code>User</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserTests {
    
	@Test
	public void contructor() {
		User user = new User();
		assertTrue(user instanceof UserGroup);
		assertEquals('U', user.getDiscriminator());
    	assertFalse(user.isAccountNonExpired());
    	assertEquals(UserType.INTERNAL.getValue(), user.getUserType());
    	assertEquals('0', user.getPrivacyLevel());
	}
    
	@Test
	public void entityContructor() {
		Entity entity = new Entity();
		User user = new User(entity, new Identity("PRINCIPAL"));
		assertSame(entity, user.getEntity());
	}
    
    /**
     * Test <code>User</code> static factory method.
     */
	@Test
    public void userFactory() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        
        User user = new User(entity, identity);
        
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
        
        User user = new User(entity, identity);
        User copy = (User) DomainTestSupport.minimalEqualsTest(user);
        
        copy.setEntity(null);
        copy.setIdentity(identity);
        assertFalse(user.equals(copy));

        copy.setEntity(entity);
        copy.setIdentity(new Identity("TEST"));
        assertFalse(user.equals(copy));

        copy.setEntity(entity);
        copy.setIdentity(identity);

        assertTrue(user.equals(copy));
    }

}
    
    
