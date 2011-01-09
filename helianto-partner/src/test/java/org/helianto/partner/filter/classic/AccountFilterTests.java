package org.helianto.partner.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.User;
import org.helianto.core.filter.classic.UserBackedFilter;
import org.helianto.core.test.UserTestSupport;
import org.helianto.partner.AccountType;
import org.helianto.partner.filter.classic.AccountFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class AccountFilterTests {

    @Test
    public void constructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getAccountCode());
		assertEquals("", filter.getAccountNameLike());
		assertEquals(' ', filter.getAccountType());
	}
	
    @Test
	public void reset() {
		filter.reset();
		assertEquals("", filter.getAccountNameLike());
		assertEquals(' ', filter.getAccountType());
	}

    public static String OB = "order by account.accountCode ";
    public static String C1 = "account.entity.id = 0 ";
    public static String C2 = "AND account.accountCode = 'CODE' ";
    public static String C3 = "AND lower(account.accountNameLike) like '%name%' ";
    public static String C4 = "AND account.accountType = 'A' ";

    @Test
    public void empty() {
        assertEquals(C1+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setAccountCode("CODE");
        assertEquals(C1+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterName() {
        filter.setAccountNameLike("NAME");
        assertEquals(C1+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterType() {
        filter.setAccountType(AccountType.ASSET.getValue());
        assertEquals(C1+C4+OB, filter.createCriteriaAsString(false));
    }
    
    private AccountFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = AccountFilter.accountFilterFactory(user);
    }
}

