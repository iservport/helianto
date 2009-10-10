/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.partner.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.repository.FilterDao;
import org.helianto.partner.Account;
import org.helianto.partner.AccountFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AccountMgrImplTests {
    
	@Test
    public void findAccounts() {
    	AccountFilter accountFilter = new AccountFilter();
    	List<Account> accountList = new ArrayList<Account>();
    	
    	expect(accountDao.find(accountFilter)).andReturn(accountList);
    	replay(accountDao);
    	
    	assertSame(accountList, accountMgr.findAccounts(accountFilter));
    	verify(accountDao);
    }
    
	@Test
    public void storeAccount() {
    	Account account = new Account();
    	Account managedAccount = new Account();
    	
    	expect(accountDao.merge(account)).andReturn(managedAccount);
    	replay(accountDao);

    	assertSame(managedAccount, accountMgr.storeAccount(account));
    	verify(accountDao);
    }
    
	@Test
    public void removeAccount() {
    	Account account = new Account();
    	
    	accountDao.remove(account);
    	replay(accountDao);

    	accountMgr.removeAccount(account);
    	verify(accountDao);
    }
    
    private AccountMgrImpl accountMgr;
    private FilterDao<Account, AccountFilter> accountDao;

	@SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        accountMgr = new AccountMgrImpl();
        accountDao = EasyMock.createMock(FilterDao.class);
        accountMgr.setAccountDao(accountDao);
    }
    
    @After
    public void tearDown() {
    	EasyMock.reset(accountDao);
    }

}
