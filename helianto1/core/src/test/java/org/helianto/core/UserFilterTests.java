package org.helianto.core;

import java.io.Serializable;
import java.util.Collection;

import junit.framework.TestCase;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.IdentityTestSupport;

/**
 * @author Maurício Fernandes de Castro
 */
public class UserFilterTests extends TestCase {
	
	public void testInheritance() {
		assertTrue(userFilter instanceof Serializable);
		assertTrue(userFilter instanceof UserBackedFilter);
		assertTrue(userFilter instanceof AbstractUserBackedCriteriaFilter);
	}
	
	public void testUserFilterFactory() {
		User user = new User();
		userFilter = UserFilter.userFilterFactory(user);
		assertSame(user, userFilter.getUser());
	}
	
	public void testIdentityConstructor() {
		Identity identity = IdentityTestSupport.createIdentity();
		userFilter = new UserFilter(identity, true);
		assertSame(identity, userFilter.getIdentity());
		assertTrue(userFilter.isOrderByLastEventDesc());
	}
	
	public void testIdentity() {
		Identity identity = IdentityTestSupport.createIdentity();
		userFilter.setIdentity(identity);
		assertSame(identity, userFilter.getIdentity());
	}
	
    public void testReset() {
    	Collection<Identity> exclusions = IdentityTestSupport.createIdentityList(3);
    	userFilter.setIdentityPrincipal("TEST");
    	userFilter.setExclusions(exclusions);
    	
    	userFilter.reset();
    	
    	assertEquals("", userFilter.getIdentityPrincipal());
    	assertEquals(0, userFilter.getExclusions().size());
    }
    
    UserFilter userFilter;
    
    public void setUp() {
    	userFilter = new UserFilter();
    }

}
