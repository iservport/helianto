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

import org.helianto.core.Entity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;

/**
 * Entity filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EntityFilterAdapter extends AbstractRootFilterAdapter<Entity> {
	
	private static final long serialVersionUID = 1L;
	private String entityAliasLike;

	/**
	 * Default constructor.
	 * 
	 * @param operator
	 */
	public EntityFilterAdapter(Entity entity) {
		super(entity);
		setOrderByString("alias");
		reset();
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
		getFilter().setAlias("");
		setEntityAliasLike("");
	}

	public boolean isSelection() {
		return getFilter().getAlias().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("alias", getFilter().getAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("alias", getEntityAliasLike(), mainCriteriaBuilder);
	}

}
