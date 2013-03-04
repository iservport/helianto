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


package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;

/**
 * Entity filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 * @see EntityFormFilterAdapter
 */
public class EntityFilterAdapter extends AbstractRootFilterAdapter<Entity> {
	
	private static final long serialVersionUID = 1L;
	private String entityAliasLike;

	/**
	 * Default constructor.
	 * 
	 * @param entity
	 */
	public EntityFilterAdapter(Entity entity) {
		super(entity);
		setOrderByString("alias");
		reset();
	}

	/**
	 * Key constructor.
	 * 
	 * @param operator
	 * @param alias
	 */
	public EntityFilterAdapter(Operator operator, String alias) {
		this(new Entity(operator, alias));
	}

	/**
	 * Entity alias like filter.
	 */
	public String getEntityAliasLike() {
		return entityAliasLike;
	}
	public void setEntityAliasLike(String entityAliasLike) {
		this.entityAliasLike = entityAliasLike;
	}

	/**
	 * Reset.
	 */
	public void reset() {
		setEntityAliasLike("");
	}

	public boolean isSelection() {
		return getForm().getAlias().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("alias", getForm().getAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("alias", getEntityAliasLike(), mainCriteriaBuilder);
	}

}
