package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.User;
import org.helianto.core.UserFilter;
import org.helianto.core.UserState;
import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupFilterTests {

    @Test
    public void testConstructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void testFactory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getIdentityPrincipal());
		assertEquals("", filter.getIdentityPrincipalLike());
		assertEquals(0, filter.getExclusions().size());
	}
	
    @Test
	public void testReset() {
		filter.reset();
		assertEquals("", filter.getIdentityPrincipal());
		assertEquals(0, filter.getExclusions().size());
	}

    public static String C1 = "usergroup.entity.id = 0 ";
    public static String C2 = "order by usergroup.identity.principal ";
    public static String C3 = "AND usergroup.identity.id = 1 ";
    public static String C4 = "AND usergroup.class=User ";
    public static String C5 = "AND usergroup.identity.principal = 'principal' ";
    public static String C6 = "AND usergroup.userState = 'A' ";
    public static String C7 = "AND lower(usergroup.identity.principal) like '%principal%' ";
    public static String C8 = "order by usergroup.lastEvent DESC ";

    @Test
    public void testEmpty() {
        assertEquals(C1+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelectIndentity() {
    	filter.setIdentity(IdentityTestSupport.createIdentity());
    	filter.getIdentity().setId(1);
        assertEquals(C1+C3, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelectIndentityPrincipal() {
    	filter.setIdentityPrincipal("principal");
        assertEquals(C1+C5, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testClazz() {
    	filter.setIdentity(IdentityTestSupport.createIdentity());
    	filter.getIdentity().setId(1);
    	filter.setClazz(User.class);
        assertEquals(C1+C4+C3, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterUserState() {
        filter.setUserState(UserState.ACTIVE);
        assertEquals(C1+C6+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterIdentityPrincipal() {
        filter.setIdentityPrincipalLike("PRINCIPAL");
        assertEquals(C1+C7+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterAll() {
        filter.setUserState(UserState.ACTIVE);
        filter.setIdentityPrincipalLike("PRINCIPAL");
        assertEquals(C1+C6+C7+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterOrderByLastEvent() {
        filter.setUserState(UserState.ACTIVE);
        filter.setOrderByLastEventDesc(true);
        assertEquals(C1+C6+C8, filter.createCriteriaAsString(false));
    }
    
    private UserFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = UserFilter.userFilterFactory(user);
    }
}

