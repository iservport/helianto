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

import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.partner.Account;
import org.helianto.partner.AccountFilter;
import org.springframework.stereotype.Repository;

/**
 * Account data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("accountDao")
public class DefaultAccountDao extends AbstractFilterDao<Account, AccountFilter> {

	@Override
	protected void doSelect(AccountFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("accountCode", filter.getAccountCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(AccountFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("accountNameLike", filter.getAccountNameLike(), mainCriteriaBuilder);
		appendEqualFilter("accountType", filter.getAccountType(), mainCriteriaBuilder);
		appendOrderBy("accountCode", mainCriteriaBuilder);
	}

	@Override
	public Class<? extends Account> getClazz() {
		return Account.class;
	}

	@Override
	protected String[] getParams() {
		return new String[] { "entity", "accountCode" };
	}

}
