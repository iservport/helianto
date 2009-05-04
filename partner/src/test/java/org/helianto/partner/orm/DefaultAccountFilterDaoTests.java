package org.helianto.partner.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.partner.AccountFilter;
import org.helianto.partner.AccountType;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultAccountFilterDaoTests {

    public static String OB = "order by account.accountCode ";
    public static String C1 = "account.entity.id = 0 ";
    public static String C2 = "AND account.accountCode = 'CODE' ";
    public static String C3 = "AND lower(account.accountNameLike) like '%name%' ";
    public static String C4 = "AND account.accountType = 'A' ";

    @Test
    public void testEmpty() {
        assertEquals(C1+OB, accountDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setAccountCode("CODE");
        assertEquals(C1+C2, accountDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterName() {
        filter.setAccountNameLike("NAME");
        assertEquals(C1+C3+OB, accountDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterType() {
        filter.setAccountType(AccountType.ASSET.getValue());
        assertEquals(C1+C4+OB, accountDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultAccountDao accountDao;
    private AccountFilter filter;
    
    @Before
    public void setUp() {
    	filter = AccountFilter.accountFilterFactory(UserTestSupport.createUser());
    	accountDao = new DefaultAccountDao();
    }
}

