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
import org.helianto.core.criteria.SelectFromBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.form.ResourceGroupForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource group form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFormFilterAdapter extends AbstractTrunkFilterAdapter<ResourceGroupForm>{

    private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 */
	public ResourceGroupFormFilterAdapter(ResourceGroupForm resourceGroup) {
		super(resourceGroup);
		
	}
	
	/**
	 * Reset.
	 */
	public void reset() { 
		getForm().reset();
	}

	public boolean isSelection() {
		return super.isSelection() && getForm().getResourceCode()!=null && getForm().getResourceCode().length()>0;
	}

	@Override
	public String createSelectAsString() {
		SelectFromBuilder builder = new SelectFromBuilder(ResourceGroup.class, getObjectAlias());
		builder.createSelectFrom();
		if (getForm().getParent()!=null) {
	        logger.debug("Parent resource group is: '{}'", getForm().getParent());
	        builder.appendInnerJoin("parentAssociations", "parentAssociation");
		}
		if (getForm().getChild()!=null) {
	        logger.debug("Child resource group is: '{}'", getForm().getParent());
	        builder.appendInnerJoin("childAssociations", "childAssociation");
		}
		return builder.getAsString();
	}
	
//	@Override
//	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
//		super.preProcessFilter(mainCriteriaBuilder);
//		if (getForm().getClazz()!=null) {
//	        logger.debug("Resource group class is: '{}'", getForm().getClazz());
//			mainCriteriaBuilder.appendAnd().append(getForm().getClazz());
//		}
//	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("resourceCode", getForm().getResourceCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getParent()!=null) {
			mainCriteriaBuilder.appendAnd().append("parentAssociation.parent.id =").append(getForm().getParent().getId());
			mainCriteriaBuilder.addSegmentCount(1);
		}
		if (getForm().getChild()!=null) {
			mainCriteriaBuilder.appendAnd().append("childAssociation.child.id =").append(getForm().getChild().getId());
			mainCriteriaBuilder.addSegmentCount(1);
		}
		appendLikeFilter("resourceName", getForm().getResourceName(), mainCriteriaBuilder);
		appendEqualFilter("resourceType", getForm().getResourceType(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "resourceCode";
	}

	private static Logger logger = LoggerFactory.getLogger(ResourceGroupFormFilterAdapter.class);
	
}
