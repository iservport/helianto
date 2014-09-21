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
import org.helianto.partner.form.PartnerCategoryForm;

/**
 * Partner category form filter adapter.
 * 
 * @author Maur�cio Fernandes de Castro
 */
public class PartnerCategoryFormFilterAdapter 
	extends AbstractFilterAdapter<PartnerCategoryForm> 
{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PartnerCategoryFormFilterAdapter(PartnerCategoryForm form) {
		super(form);
	}
	
	public boolean isSelection() {
		return getForm().getPartnerId()>0 
				&& getForm().getCategoryId()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("partner.id", getForm().getPartnerId(), mainCriteriaBuilder);
		appendEqualFilter("category.id", getForm().getCategoryId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
		appendEqualFilter("partner.privateEntity.id", getForm().getPrivateEntityId(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "category.categoryCode";
	}

}
