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
import org.helianto.partner.domain.Account;
import org.helianto.partner.filter.AccountFilterAdapter;
import org.helianto.partner.form.AccountForm;
import org.helianto.partner.repository.AccountRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AccountMgrImplTests {
    
	@Test
    public void findAccounts() {
		AccountForm form = EasyMock.createMock(AccountForm.class);
    	List<Account> accountList = new ArrayList<Account>();
    	
    	expect(accountRepository.find(EasyMock.isA(AccountFilterAdapter.class))).andReturn(accountList);
    	replay(accountRepository);
    	
    	assertSame(accountList, accountMgr.findAccounts(form));
    	verify(accountRepository);
    }
    
	@Test
    public void storeAccount() {
    	Account account = new Account();
    	Account managedAccount = new Account();
    	
    	expect(accountRepository.saveAndFlush(account)).andReturn(managedAccount);
    	replay(accountRepository);

    	assertSame(managedAccount, accountMgr.storeAccount(account));
    	verify(accountRepository);
    }
    
	@Test
    public void removeAccount() {
    	Account account = new Account();
    	
    	accountRepository.delete(account);
    	replay(accountRepository);

    	accountMgr.removeAccount(account);
    	verify(accountRepository);
    }
    
    private AccountMgrImpl accountMgr;
    private AccountRepository accountRepository;

	@Before
    public void setUp() {
        accountMgr = new AccountMgrImpl();
        accountRepository = EasyMock.createMock(AccountRepository.class);
        accountMgr.setAccountRepository(accountRepository);
    }
    
    @After
    public void tearDown() {
    	EasyMock.reset(accountRepository);
    }

}
