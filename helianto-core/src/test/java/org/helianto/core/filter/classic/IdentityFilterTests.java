package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.User;
import org.helianto.core.filter.classic.IdentityFilter;
import org.helianto.core.filter.classic.UserBackedFilter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFilterTests {

    @Test
    public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getPrincipal());
		assertEquals("", filter.getNameOrAliasSearch());
	}
	
    @Test
	public void reset() {
		filter.reset();
		assertEquals("", filter.getPrincipal());
		assertEquals("", filter.getNameOrAliasSearch());
	}

    public static String C1 = "identity.id in (select user.identity.id from User user where user.entity.id =  0 )  ";
    public static String C2 = "lower(identity.principal) like '%principal%' ";
    public static String C3 = "AND lower(identity.optionalAlias) like '%alias%' ";

    @Test
    public void rmpty() {
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void relect() {
    	filter.setPrincipal("PRINCIPAL");
    	filter.setEntity(null);
        assertEquals(C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void rilter() {
        filter.setNameOrAliasSearch("ALIAS");
        assertEquals(C1+C3, filter.createCriteriaAsString(false));
    }
    
    private IdentityFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = IdentityFilter.identityFilterFactory(user);
    }
}

