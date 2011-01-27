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

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.AbstractTrunkFilterAdapter;
import org.helianto.resource.Resource;
import org.helianto.resource.ResourceGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource group filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupFilterAdapter extends AbstractTrunkFilterAdapter<ResourceGroup>{

    private static final long serialVersionUID = 1L;
	private Class<? extends ResourceGroup> clazz = ResourceGroup.class;
	
	/**
	 * Default constructor.
	 */
	public ResourceGroupFilterAdapter(ResourceGroup resourceGroup) {
		super(resourceGroup);
		getFilter().setResourceType(' ');
	}
	
	/**
	 * Reset.
	 */
	public void reset() { }

	public boolean isSelection() {
		return getFilter().getResourceCode().length()>0;
	}

	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (!getClazz().equals(ResourceGroup.class)) {
	        logger.debug("Resource group class is: '{}'", getClazz());
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("resourceCode", getFilter().getResourceCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("resourceName", getFilter().getResourceName(), mainCriteriaBuilder);
		appendEqualFilter("resourceType", getFilter().getResourceType(), mainCriteriaBuilder);
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

	/**
	 * Resource name like.
	 */
	private static Logger logger = LoggerFactory.getLogger(ResourceGroup.class);
	
}