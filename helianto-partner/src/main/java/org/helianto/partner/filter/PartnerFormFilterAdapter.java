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

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.partner.form.PartnerForm;

/**
 * Partner filter adapter.
 * 
 * @author Maur�cio Fernandes de Castro
 */
public class PartnerFormFilterAdapter extends AbstractFilterAdapter<PartnerForm> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PartnerFormFilterAdapter(PartnerForm form) {
		super(form);
	}
	
	public boolean isSelection() {
		return getForm().getParent()!=null && getForm().getParent().getId()>0 && getForm().getPartnerType()>0;
	}

	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getForm().getPartnerType()>0) {
			appendEqualFilter("class", getForm().getPartnerType(), mainCriteriaBuilder);
			connect = true;
		}
		return connect;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("privateEntity.id", getForm().getParent().getId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("privateEntity.entityAlias", getForm().getEntityAlias(), mainCriteriaBuilder);
		appendLikeFilter("privateEntity.entityName", getForm().getEntityName(), mainCriteriaBuilder);
		appendEqualFilter("partnerState", getForm().getPartnerState(), mainCriteriaBuilder);
		appendEqualFilter("priority", getForm().getPriority(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "privateEntity.entityAlias";
	}

}
