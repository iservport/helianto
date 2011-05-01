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


package org.helianto.partner.filter.classic;

import org.helianto.core.User;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;

/**
 * Account filter.
 * @author Mauricio Fernandes de Castro
 */
public class AccountFilter extends AbstractUserBackedCriteriaFilter {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static AccountFilter accountFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(AccountFilter.class, user);
	}

	private static final long serialVersionUID = 1L;
    private String accountCode;
    private String accountNameLike;
    private char accountType;
    
    /**
     * Default constructor.
     */
    public AccountFilter() {
    	super();
    	setAccountCode("");
    	setAccountNameLike("");
    	setAccountType(' ');
    }

	/**
	 * Reset
	 */
	public void reset() {
    	setAccountNameLike("");
    	setAccountType(' ');
	}
	
	public boolean isSelection() {
		return getAccountCode().length()>0;
	}

	public String getObjectAlias() {
		return "account";
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("accountCode", getAccountCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("accountNameLike", getAccountNameLike(), mainCriteriaBuilder);
		appendEqualFilter("accountType", getAccountType(), mainCriteriaBuilder);
		appendOrderBy("accountCode", mainCriteriaBuilder);
	}

	/**
     * Account code filter.
     */
    public String getAccountCode() {
        return this.accountCode;
    }
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * Account name like filter.
     */
    public String getAccountNameLike() {
        return this.accountNameLike;
    }
    public void setAccountNameLike(String accountNameLike) {
        this.accountNameLike = accountNameLike;
    }

    /**
     * Account type filter.
     */
    public char getAccountType() {
        return this.accountType;
    }
    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }

}
