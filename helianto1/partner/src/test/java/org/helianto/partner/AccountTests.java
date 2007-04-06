package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Entity;

/**
 * <code>Account</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AccountTests extends TestCase {
    
    /**
     * Test <code>Account</code> static factory method.
     */
    public void testAccountFactory() {
        Entity entity = new Entity();
        String accountCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Account account = Account.accountFactory(entity, accountCode);
        
        assertSame(entity, account.getEntity());
        assertEquals(accountCode, account.getAccountCode());
        assertEquals(AccountType.ASSET.getValue(), account.getAccountType());
        
    }
    
    /**
     * Test <code>Account</code> equals() method.
     */
    public void testAccountEquals() {
        Entity entity = new Entity();
        String accountCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Account account = Account.accountFactory(entity, accountCode);
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
    
    
