package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.Account;

/**
 * Class to support <code>AccountDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AccountTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Account</code>.
     * @param entity optional Entity 
     * @param accountCode optional String 
     */
    public static Account createAccount(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        String accountCode;
        try {
            accountCode = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            accountCode = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
        }
        Account account = Account.accountFactory(entity, accountCode);
        return account;
    }

    /**
     * Test support method to create a <code>Account</code> list.
     *
     * @param accountListSize
     */
    public static List<Account> createAccountList(int accountListSize) {
        return createAccountList(accountListSize, 1);
    }

    /**
     * Test support method to create a <code>Account</code> list.
     *
     * @param accountListSize
     * @param entityListSize
     */
    public static List<Account> createAccountList(int accountListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createAccountList(accountListSize, entityList);
    }

    /**
     * Test support method to create a <code>Account</code> list.
     *
     * @param accountListSize
     * @param entityList
     */
    public static List<Account> createAccountList(int accountListSize, List<Entity> entityList) {
        List<Account> accountList = new ArrayList<Account>();
        for (Entity entity: entityList) {
	        for (int i=0;i<accountListSize;i++) {
    	        accountList.add(createAccount(entity));
        	}
        }
        return accountList;
    }

}
