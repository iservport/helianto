package org.helianto.partner.orm;

import java.util.List;

import org.helianto.partner.Account;
import org.helianto.partner.dao.AccountDao;
import org.helianto.partner.test.AccountTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>AccountDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class AccountDaoImplTests extends AbstractPartnerDaoImplConfig {
    
    private AccountDao accountDao;
    
    /*
     * Hook to persist one <code>Account</code>.
     */  
    protected Account writeAccount() {
        Account account = AccountTestSupport.createAccount();
        accountDao.persistAccount(account);
        accountDao.flush();
        accountDao.clear();
        return account;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneAccount() {
        Account account = writeAccount();

        assertEquals(account,  accountDao.findAccountByNaturalId(account.getEntity(), account.getAccountCode()));
    }
    
    /*
     * Hook to persist a <code>Account</code> list.
     */  
    protected List<Account> writeAccountList() {
        int accountListSize = 10;
        int entityListSize = 2;
        List<Account> accountList = AccountTestSupport.createAccountList(accountListSize, entityListSize);
        assertEquals(accountListSize * entityListSize, accountList.size());
        for (Account account: accountList) {
            accountDao.persistAccount(account);
        }
        accountDao.flush();
        accountDao.clear();
        return accountList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListAccount() {
        List<Account> accountList = writeAccountList();

        Account account = accountList.get((int) (Math.random()*accountList.size()));
        assertEquals(account,  accountDao.findAccountByNaturalId(account.getEntity(), account.getAccountCode()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testAccountDuplicate() {
        Account account =  writeAccount();
        Account accountCopy = AccountTestSupport.createAccount(account.getEntity(), account.getAccountCode());

        try {
            accountDao.mergeAccount(accountCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveAccount() {
        List<Account> accountList = writeAccountList();
        Account account = accountList.get((int) (Math.random()*accountList.size()));
        accountDao.removeAccount(account);

        assertNull(accountDao.findAccountByNaturalId(account.getEntity(), account.getAccountCode()));
    }

    //- setters

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    
}

