package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Collection;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.test.IdentityTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maurício Fernandes de Castro
 */
public class UserFilterTests {
	
	@Test
	public void inheritance() {
		assertTrue(userFilter instanceof Serializable);
		assertTrue(userFilter instanceof UserBackedFilter);
		assertTrue(userFilter instanceof AbstractUserBackedCriteriaFilter);
	}
	
	@Test
	public void userFilterFactory() {
		User user = new User();
		userFilter = UserFilter.userFilterFactory(user);
		assertSame(user, userFilter.getUser());
	}
	
	@Test
	public void identityConstructor() {
		Identity identity = IdentityTestSupport.createIdentity();
		userFilter = new UserFilter(identity, true);
		assertSame(identity, userFilter.getIdentity());
		assertTrue(userFilter.isOrderByLastEventDesc());
	}
	
	@Test
	public void identity() {
		Identity identity = IdentityTestSupport.createIdentity();
		userFilter.setIdentity(identity);
		assertSame(identity, userFilter.getIdentity());
	}
	
	@Test
    public void reset() {
    	Collection<Identity> exclusions = IdentityTestSupport.createIdentityList(3);
    	userFilter.setIdentityPrincipal("TEST");
    	userFilter.setExclusions(exclusions);
    	
    	userFilter.reset();
    	
    	assertEquals("", userFilter.getIdentityPrincipal());
    	assertEquals(0, userFilter.getExclusions().size());
    }
    
    UserFilter userFilter;
    
    @Before
    public void setUp() {
    	userFilter = new UserFilter();
    }

}
