package org.helianto.partner.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.Account;
import org.helianto.partner.AccountType;
import org.helianto.partner.filter.AccountFilterAdapter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class AccountFilterAdapterTests {

    @Test
	public void constructor() {
		assertSame(entity, filter.getEntity());
		assertSame(entity, filter.getForm().getEntity());
		
		filter = new AccountFilterAdapter(entity, "CODE");
		assertSame(entity, filter.getEntity());
		assertSame(entity, filter.getForm().getEntity());
		assertEquals("CODE", filter.getForm().getAccountCode());
	}
	
    public static String OB = "order by alias.accountCode ";
    public static String C1 = "alias.entity.id = 0 ";
    public static String C2 = "AND alias.accountCode = 'CODE' ";
    public static String C3 = "AND lower(alias.accountName) like '%name%' ";
    public static String C4 = "AND alias.accountType = 'A' ";

    @Test
    public void empty() {
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	form.setAccountCode("CODE");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterName() {
    	form.setAccountName("NAME");
        assertEquals(C1+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterType() {
    	form.setAccountType(AccountType.ASSET.getValue());
        assertEquals(C1+C4+OB, filter.createCriteriaAsString());
    }
    
    private Account form;
    private AccountFilterAdapter filter;
    private Entity entity;
    
    @Before
    public void setUp() {
    	entity = EntityTestSupport.createEntity();
    	form = new Account(entity, "");
    	filter = new AccountFilterAdapter(form);
    }
}

