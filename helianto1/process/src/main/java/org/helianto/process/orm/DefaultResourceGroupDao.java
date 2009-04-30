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


package org.helianto.process.orm;

import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceGroupFilter;
import org.springframework.stereotype.Repository;

/**
 * Resource group data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("resourceGroupDao")
public class DefaultResourceGroupDao extends AbstractFilterDao<ResourceGroup, ResourceGroupFilter> {

	@Override
	protected void preProcessFilter(ResourceGroupFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		if (!filter.getClazz().equals(ResourceGroup.class)) {
	        if (logger.isDebugEnabled()) {
	            logger.debug("Resource group class is: '"+filter.getClazz()+"'");
	        }
			mainCriteriaBuilder.appendAnd().append(filter.getClazz());
		}
	}

	@Override
	protected void doSelect(ResourceGroupFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("resourceCode", filter.getResourceCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(ResourceGroupFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("resourceName", filter.getResourceNameLike(), mainCriteriaBuilder);
		appendEqualFilter("resourceType", filter.getResourceType(), mainCriteriaBuilder);
		appendOrderBy("resourceCode", mainCriteriaBuilder);
	}

	@Override
	public Class<? extends ResourceGroup> getClazz() {
		return ResourceGroup.class;
	}

	/**
	 * Default keys are entity.id and resourceCode
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "entity", "resourceCode" };
	}

}
