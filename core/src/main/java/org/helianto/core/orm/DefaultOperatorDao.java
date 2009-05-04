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
import org.helianto.core.Operator;
import org.helianto.core.OperatorFilter;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * Operator data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("operatorDao")
public class DefaultOperatorDao extends AbstractFilterDao<Operator, OperatorFilter> {

	/**
	 * Do not raise exception when entity is null. 
	 */
	@Override
	protected boolean requireEntity() {
		return false;
	}

	/**
	 * Overriden because default implementation does not allow other 
	 * entities to be selected.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	protected void doFilter(OperatorFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("operatorName", filter.getOperatorNameLike(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(OperatorFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("operatorName", filter.getOperatorName(), mainCriteriaBuilder);
	}

	@Override
	public Class<? extends Operator> getClazz() {
		return Operator.class;
	}

	/**
	 * Default key is operatorName.
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "operatorName" };
	}

}
