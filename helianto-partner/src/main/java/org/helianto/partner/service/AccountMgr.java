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

import org.helianto.core.filter.Filter;
import org.helianto.partner.Account;

/**
 * Account service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface AccountMgr {

    /**
     * Find <code>Account</code>.
     */
	public List<Account> findAccounts(Filter accountFilter);
	
    /**
     * Write <code>Account</code> to the datastore.
     */
    public Account storeAccount(Account account);

    /**
     * Remove <code>Account</code> from the datastore.
     */
    public void removeAccount(Account account);

}
