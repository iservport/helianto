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
import org.helianto.core.EntityFilter;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * Entity data acess.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("entityDao")
public class DefaultEntityDao extends AbstractFilterDao<Entity, EntityFilter> {

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
	protected void preProcessFilter(EntityFilter filter, CriteriaBuilder mainCriteriaBuilder) {
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
	protected boolean isSelection(EntityFilter filter) {
		return (filter.getOperator()!=null && filter.getEntityAlias().length()>0);
	}

	@Override
	protected void doSelect(EntityFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("alias", filter.getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(EntityFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("alias", filter.getEntityAliasLike(), mainCriteriaBuilder);
	}

	/**
	 * Keys are operator.id and alias
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "operator", "alias" };
	}

	@Override
	public Class<? extends Entity> getClazz() {
		return Entity.class;
	}

}
