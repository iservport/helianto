package org.helianto.partner.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.partner.def.AccountType;
import org.helianto.partner.form.AccountForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * @author Mauricio Fernandes de Castro
 */
public class AccountFilterAdapterTests {

	public static String OB = "order by alias.accountCode ";
    public static String C1 = "alias.entity.id = 1 ";
    public static String C2 = "AND alias.accountCode = 'CODE' ";
    public static String C3 = "AND lower(alias.accountName) like '%name%' ";
    public static String C4 = "AND alias.accountType = 'A' ";

    @Test
    public void empty() {
    	Mockito.when(form.getEntityId()).thenReturn(0);
        assertEquals(OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getAccountCode()).thenReturn("CODE");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterName() {
    	Mockito.when(form.getAccountName()).thenReturn("NAME");
        assertEquals(C1+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterType() {
    	Mockito.when(form.getAccountType()).thenReturn(AccountType.ASSET.getValue());
        assertEquals(C1+C4+OB, filter.createCriteriaAsString());
    }
    
    private AccountForm form;
    private AccountFilterAdapter filter;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(AccountForm.class);
    	filter = new AccountFilterAdapter(form);
    	Mockito.when(form.getEntityId()).thenReturn(1);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
}

