package org.helianto.partner.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Account</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AccountTests {
    
    /**
     * Test <code>Account</code> static factory method.
     */
	@Test
    public void constructor() {
        Entity entity = new Entity();
        String accountCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Account account = new Account(entity, accountCode);
        
        assertSame(entity, account.getEntity());
        assertEquals(accountCode, account.getAccountCode());
        assertEquals(AccountType.ASSET.getValue(), account.getAccountType());
        
    }
    
    /**
     * Test <code>Account</code> equals() method.
     */
	@Test
    public void accountEquals() {
        Entity entity = new Entity();
        String accountCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Account account = new Account(entity, accountCode);
        Account copy = (Account) DomainTestSupport.minimalEqualsTest(account);
        
        copy.setEntity(null);
        copy.setAccountCode(accountCode);
        assertFalse(account.equals(copy));

        copy.setEntity(entity);
        copy.setAccountCode(null);
        assertFalse(account.equals(copy));

        copy.setEntity(entity);
        copy.setAccountCode(accountCode);

        assertTrue(account.equals(copy));
    }

}
    
    
