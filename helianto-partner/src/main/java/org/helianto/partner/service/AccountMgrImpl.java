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

import javax.annotation.Resource;

import org.helianto.partner.AccountMgr;
import org.helianto.partner.domain.Account;
import org.helianto.partner.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default account service interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("accountMgr")
public class AccountMgrImpl 
	implements AccountMgr {

	@Transactional
	public Account storeAccount(Account account) {
		return accountRepository.saveAndFlush(account);
	}
	
	@Transactional
    public void removeAccount(Account account) {
    	accountRepository.delete(account);
    }

	// collabs
	
	private AccountRepository accountRepository;

    @Resource
    public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
