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


package org.helianto.core;

import org.helianto.core.filter.AbstractDateRangeFilter;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * <code>UserLog</code> filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserLogFilter extends AbstractDateRangeFilter {
	
	/**
	 * Método fábrica.
	 * 
	 * @param user
	 */
	public static UserLogFilter userLogFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(UserLogFilter.class, user);
	}

	private static final long serialVersionUID = 1L;
	private Identity identity;
	
	/**
	 * Default constructor.
	 */
	public UserLogFilter() {
		super();
	}

	/**
	 * Reset.
	 */
	public void reset() {
	}

	@Override
	public String getObjectAlias() {
		return "userlog";
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendDateRange("lastEvent", this);
		if (getIdentity()!=null) {
			appendEqualFilter("user.identity.id", getIdentity().getId(), mainCriteriaBuilder);
		}
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	public boolean isSelection() {
		return false;
	}

	/**
	 * Identity filter.
	 */
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

}
