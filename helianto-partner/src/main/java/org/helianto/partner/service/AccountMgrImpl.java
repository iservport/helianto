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

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.partner.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default account service interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("accountMgr")
public class AccountMgrImpl implements AccountMgr {

	public List<Account> findAccounts(Filter accountFilter) {
		List<Account> accountList = (List<Account>) accountDao.find(accountFilter);
    	if (logger.isDebugEnabled() && accountList!=null) {
    		logger.debug("Found account list of size {}", accountList.size());
    	}
		return accountList;
	}

	public Account storeAccount(Account account) {
		return accountDao.merge(account);
	}
	
    public void removeAccount(Account account) {
    	accountDao.remove(account);
    }

	// collabs
	
	private FilterDao<Account> accountDao;

    @Resource(name="accountDao")
    public void setAccountDao(FilterDao<Account> accountDao) {
        this.accountDao = accountDao;
    }

    
    private Logger logger = LoggerFactory.getLogger(AccountMgrImpl.class);

}
