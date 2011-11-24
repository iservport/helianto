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
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
import org.helianto.core.filter.form.EntityForm;

/**
 * Entity filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EntityFormFilterAdapter extends AbstractRootFilterAdapter<EntityForm> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public EntityFormFilterAdapter(EntityForm form) {
		super(form);
	}

	/**
	 * Reset.
	 */
	public void reset() {
		getForm().reset();
	}

	public boolean isSelection() {
		return getForm().getOperator()!=null && getForm().getOperator().getId()>0 && getForm().getEntityAlias().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("alias", getForm().getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("alias", getForm().getEntityAliasLike(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "alias";
	}

}
