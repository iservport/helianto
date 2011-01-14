package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.User;
import org.helianto.core.UserState;
import org.helianto.core.filter.classic.UserBackedFilter;
import org.helianto.core.filter.classic.UserFilter;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupFilterTests {

    @Test
    public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getIdentityPrincipal());
		assertEquals("", filter.getIdentityPrincipalLike());
		assertEquals(0, filter.getExclusions().size());
	}
	
    @Test
	public void reset() {
		filter.reset();
		assertEquals("", filter.getIdentityPrincipal());
		assertEquals(0, filter.getExclusions().size());
	}

    public static String C1 = "alias.entity.id = 0 ";
    public static String C2 = "order by alias.userKey ";
    public static String C3 = "AND alias.identity.id = 1 ";
    public static String C4 = "AND alias.class=User ";
    public static String C5 = "AND alias.identity.principal = 'principal' ";
    public static String C6 = "AND alias.userState = 'A' ";
    public static String C7 = "AND lower(alias.identity.principal) like '%principal%' ";
    public static String C8 = "order by alias.lastEvent DESC ";

    @Test
    public void empty() {
        assertEquals(C1+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void selectIndentity() {
    	filter.setIdentity(IdentityTestSupport.createIdentity());
    	filter.getIdentity().setId(1);
        assertEquals(C1+C3, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void selectIndentityPrincipal() {
    	filter.setIdentityPrincipal("principal");
        assertEquals(C1+C5, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void clazz() {
    	filter.setIdentity(IdentityTestSupport.createIdentity());
    	filter.getIdentity().setId(1);
    	filter.setClazz(User.class);
        assertEquals(C1+C4+C3, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterUserState() {
        filter.setUserState(UserState.ACTIVE);
        assertEquals(C1+C6+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterIdentityPrincipal() {
        filter.setIdentityPrincipalLike("PRINCIPAL");
        assertEquals(C1+C7+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterAll() {
        filter.setUserState(UserState.ACTIVE);
        filter.setIdentityPrincipalLike("PRINCIPAL");
        assertEquals(C1+C6+C7+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterOrderByLastEvent() {
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
