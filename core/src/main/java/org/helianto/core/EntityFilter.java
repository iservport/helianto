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

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * Entity filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EntityFilter extends AbstractUserBackedCriteriaFilter {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static EntityFilter entityFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(EntityFilter.class, user);
	}

	private static final long serialVersionUID = 1L;
	private String entityAlias;
	private Operator operator;
	private String entityAliasLike;

	/**
	 * Default constructor.
	 */
	public EntityFilter() {
		this.entityAlias = "";
		this.entityAliasLike = "";
	}

	public void reset() {
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
	 * Operator filter.
	 */
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
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

}