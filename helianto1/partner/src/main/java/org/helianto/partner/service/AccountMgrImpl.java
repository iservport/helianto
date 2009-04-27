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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.dao.FilterDao;
import org.helianto.partner.Account;
import org.helianto.partner.AccountFilter;

/**
 * Default account service interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AccountMgrImpl implements AccountMgr {

	public List<Account> findAccounts(AccountFilter accountFilter) {
		List<Account> accountList = (List<Account>) accountDao.find(accountFilter);
    	if (logger.isDebugEnabled() && accountList!=null) {
    		logger.debug("Found account list of size "+accountList.size());
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
	
	private FilterDao<Account, AccountFilter> accountDao;

    @Resource(name="accountDao")
    public void setAccountDao(FilterDao<Account, AccountFilter> accountDao) {
        this.accountDao = accountDao;
    }

    
    private Log logger = LogFactory.getLog(AccountMgrImpl.class);

}
