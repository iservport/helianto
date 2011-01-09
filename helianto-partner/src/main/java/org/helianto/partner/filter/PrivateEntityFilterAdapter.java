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

package org.helianto.partner.filter;

import org.helianto.core.Entity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.AbstractTrunkFilterAdapter;
import org.helianto.partner.PrivateEntity;

/**
 * Partner registry filter adapter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PrivateEntityFilterAdapter extends AbstractTrunkFilterAdapter<PrivateEntity> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param privateEntity
	 */
	public PrivateEntityFilterAdapter(PrivateEntity privateEntity) {
		super(privateEntity);
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param partnerAlias
	 */
	public PrivateEntityFilterAdapter(Entity entity, String partnerAlias) {
		this(new PrivateEntity(entity, partnerAlias));
	}

	/**
	 * Reset method.
	 */
	public void reset() { }

	public boolean isSelection() {
		return getFilter().getEntityAlias().length()>0;
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("entityName", getFilter().getEntityName(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("partnerAlias",getFilter(). getEntityAlias(), mainCriteriaBuilder);
	}

}
