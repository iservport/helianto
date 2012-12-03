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


package org.helianto.core.filter.classic;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Operator;

/**
 * Entity filter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated see EntityFilterAdapter
 */
public class EntityFilter extends AbstractOperatorBackedCriteriaFilter {
	
	private static final long serialVersionUID = 1L;
	private String entityAlias;
	private String entityAliasLike;

	/**
	 * Default constructor.
	 * 
	 * @param operator
	 */
	public EntityFilter(Operator operator) {
		super(operator);
		setOrderByString("alias");
		reset();
	}

	/**
	 * Entity alias filter.
	 */
	public String getEntityAlias() {
		return entityAlias;
	}
	public void setEntityAlias(String entityAlias) {
		this.entityAlias = entityAlias;
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
		setEntityAlias("");
		setEntityAliasLike("");
	}

	public boolean isSelection() {
		return getEntityAlias().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("alias", getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("alias", getEntityAliasLike(), mainCriteriaBuilder);
	}

}
