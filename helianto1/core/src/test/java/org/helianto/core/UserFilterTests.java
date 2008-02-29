package org.helianto.core;

import java.util.Collection;

import junit.framework.TestCase;

import org.helianto.core.test.IdentityTestSupport;

/**
 * @author Maurício Fernandes de Castro
 */
public class UserFilterTests extends TestCase {
	
	public void testUserFilterFactory() {
		User user = new User();
		UserFilter userFilter = UserFilter.userFilterFactory(user);
		assertSame(user, userFilter.getUser());
	}
	
    public void testReset() {
    	UserFilter userFilter = new UserFilter();
    	Collection<Identity> exclusions = IdentityTestSupport.createIdentityList(3);
    	userFilter.setIdentityPrincipal("TEST");
    	userFilter.setExclusions(exclusions);
    	
    	userFilter.reset();
    	
    	assertEquals("", userFilter.getIdentityPrincipal());
    	assertEquals(0, userFilter.getExclusions().size());
    }

}
