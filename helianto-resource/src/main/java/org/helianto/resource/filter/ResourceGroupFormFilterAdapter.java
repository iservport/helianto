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


package org.helianto.resource.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.resource.form.ResourceGroupForm;

/**
 * Resource group form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFormFilterAdapter extends AbstractTrunkFilterAdapter<ResourceGroupForm>{

    private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public ResourceGroupFormFilterAdapter(ResourceGroupForm form) {
		super(form);
		
	}
	
	public boolean isSelection() {
		return super.isSelection() && getForm().getResourceCode()!=null && getForm().getResourceCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("resourceCode", getForm().getResourceCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getResourceGroupId()>0) {
			appendEqualFilter("resourceGroup.id", getForm().getResourceGroupId(), mainCriteriaBuilder);
		}
		appendEqualFilter("resourceType", getForm().getResourceType(), mainCriteriaBuilder);
	}
	
	@Override
	public boolean isSearch() {
		return super.isSearch() && getForm().getSearchMode()=='R';
	}
	
	protected String[] getFieldNames() {
		return new String[] {"resourceCode" , "resourceName" };
	}
	
	@Override
	public String getOrderByString() {
		return "resourceCode";
	}

}
