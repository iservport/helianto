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

package org.helianto.partner.orm;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.partner.Account;
import org.helianto.partner.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of <code>Account</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("accountDao")
public class AccountDaoImpl extends GenericDaoImpl implements AccountDao {
     
    public void persistAccount(Account account) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+account);
        }
        persist(account);
    }
    
    public Account mergeAccount(Account account) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+account);
        }
        return (Account) merge(account);
    }
    
    public void removeAccount(Account account) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+account);
        }
        remove(account);
    }
    
    public Account findAccountByNaturalId(Entity entity, String accountCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique account with entity='"+entity+"' and accountCode='"+accountCode+"' ");
        }
        return (Account) findUnique(Account.getAccountNaturalIdQueryString(), entity, accountCode);
    }
    
}
