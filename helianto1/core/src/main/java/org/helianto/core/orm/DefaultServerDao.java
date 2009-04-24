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

import org.helianto.core.Entity;
import org.helianto.core.Server;
import org.helianto.core.ServerFilter;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * Server data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("serverDao")
public class DefaultServerDao extends AbstractFilterDao<Server, ServerFilter> {

	/**
	 * Do not raise exception when entity is null. 
	 */
	@Override
	protected boolean requireEntity() {
		return false;
	}

	/**
	 * Restrict entity selection to a given operator, if any. 
	 */
	@Override
	protected void preProcessFilter(ServerFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		if (filter.getOperator()!=null) {
			appendEqualFilter("operator.id", filter.getOperator().getId(), mainCriteriaBuilder);
		}
	}

	/**
	 * Overriden because default implementation does not allow other 
	 * entities to be selected.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	protected void doFilter(ServerFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("serverName", filter.getServerName(), mainCriteriaBuilder);
		appendEqualFilter("serverType", filter.getServerType(), mainCriteriaBuilder);
		appendEqualFilter("priority", filter.getPriority(), mainCriteriaBuilder);
		appendEqualFilter("serverState", filter.getServerState(), mainCriteriaBuilder);
		appendOrderBy("priority", mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(ServerFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("serverName", filter.getServerName(), mainCriteriaBuilder);
	}

	@Override
	public Class<? extends Server> getClazz() {
		return Server.class;
	}

	/**
	 * Keys are operator.id and serverName
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "operator", "serverName" };
	}

}
