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
import org.helianto.resource.domain.Resource;
import org.helianto.resource.domain.ResourceGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource group filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 * @see ResourceGroupFormFilterAdapter
 */
public class ResourceGroupFilterAdapter extends AbstractTrunkFilterAdapter<ResourceGroup>{

    private static final long serialVersionUID = 1L;
	private Class<? extends ResourceGroup> clazz;
	private ResourceGroup parent;
	private ResourceGroup child;
	
	/**
	 * Default constructor.
	 */
	public ResourceGroupFilterAdapter(ResourceGroup resourceGroup) {
		super(resourceGroup);
		getForm().setResourceType(' ');
	}
	
	/**
	 * Reset.
	 */
	public void reset() { }

	public boolean isSelection() {
		return getForm().getResourceCode().length()>0;
	}

	@Override
	public String createSelectAsString() {
		SelectFromBuilder builder = new SelectFromBuilder(ResourceGroup.class, getObjectAlias());
		builder.createSelectFrom();
		if (getParent()!=null) {
	        logger.debug("Parent resource group is: '{}'", getParent());
	        builder.appendInnerJoin("parentAssociations", "parentAssociation");
		}
		if (getChild()!=null) {
	        logger.debug("Child resource group is: '{}'", getParent());
	        builder.appendInnerJoin("childAssociations", "childAssociation");
		}
		return builder.getAsString();
	}
	
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
	        logger.debug("Resource group class is: '{}'", getClazz());
			mainCriteriaBuilder.appendAnd().append(getClazz());
			connect = true;
		}
		return connect;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("resourceCode", getForm().getResourceCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getParent()!=null) {
			mainCriteriaBuilder.appendAnd().append("parentAssociation.parent.id =").append(getParent().getId());
			mainCriteriaBuilder.addSegmentCount(1);
		}
		if (getChild()!=null) {
			mainCriteriaBuilder.appendAnd().append("childAssociation.child.id =").append(getChild().getId());
			mainCriteriaBuilder.addSegmentCount(1);
		}
		appendLikeFilter("resourceName", getForm().getResourceName(), mainCriteriaBuilder);
		appendEqualFilter("resourceType", getForm().getResourceType(), mainCriteriaBuilder);
		appendOrderBy("resourceCode", mainCriteriaBuilder);
	}

	/**
	 * Subclass
	 */
	public Class<? extends ResourceGroup> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends ResourceGroup> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Parent filter.
	 */
	public ResourceGroup getParent() {
		return parent;
	}
	public void setParent(ResourceGroup parent) {
		this.parent = parent;
	}
	
	/**
	 * Child filter.
	 */
	public ResourceGroup getChild() {
		return child;
	}
	public void setChild(ResourceGroup child) {
		this.child = child;
	}

	/**
	 * Discriminator
	 */
	public char getDiscriminator() {
		if (clazz.equals(ResourceGroup.class)) {
			return 'G'; 
		}
		if (clazz.equals(Resource.class)) {
			return 'R'; 
		}
		return ' ';
	}
	public void setDiscriminator(char discriminator) {
		if (discriminator=='G') {
			clazz = ResourceGroup.class; 
		}
		if (discriminator=='R') {
			clazz = Resource.class; 
		}
	}

	private static Logger logger = LoggerFactory.getLogger(ResourceGroupFilterAdapter.class);
	
}
