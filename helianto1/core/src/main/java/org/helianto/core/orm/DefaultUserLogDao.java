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


package org.helianto.core.orm;

import org.helianto.core.UserLog;
import org.helianto.core.UserLogFilter;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * <code>UserLog</code> data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("userLogDao")
public class DefaultUserLogDao extends AbstractFilterDao<UserLog, UserLogFilter> {

	@Override
	protected void doFilter(UserLogFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendDateRange("lastEvent", filter);
		if (filter.getIdentity()!=null) {
			appendEqualFilter("user.identity.id", filter.getIdentity().getId(), mainCriteriaBuilder);
		}
	}

	@Override
	protected void doSelect(UserLogFilter filter, CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	protected boolean isSelection(UserLogFilter filter) {
		return false;
	}

	@Override
	public Class<? extends UserLog> getClazz() {
		return UserLog.class;
	}

	@Override
	protected String[] getParams() {
		return new String[] { "user", "lastEvent" };
	}

}
