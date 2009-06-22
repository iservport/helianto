package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFilterTests {

    @Test
    public void testConstructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void testFactory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getPrincipal());
		assertEquals("", filter.getNameOrAliasSearch());
	}
	
    @Test
	public void testReset() {
		filter.reset();
		assertEquals("", filter.getPrincipal());
		assertEquals("", filter.getNameOrAliasSearch());
	}

    public static String C1 = "identity.id in (select user.identity.id from User user where user.entity.id =  0 )  ";
    public static String C2 = "lower(identity.principal) like '%principal%' ";
    public static String C3 = "AND lower(identity.optionalAlias) like '%alias%' ";

    @Test
    public void testEmpty() {
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setPrincipal("PRINCIPAL");
    	filter.setEntity(null);
        assertEquals(C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilter() {
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

