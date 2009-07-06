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


package org.helianto.resource.repository;

import org.helianto.core.dao.AbstractHibernateFilterDao;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.ResourceGroupFilter;
import org.springframework.stereotype.Repository;

/**
 * Resource group data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("resourceGroupDao")
public class DefaultResourceGroupDao extends AbstractHibernateFilterDao<ResourceGroup, ResourceGroupFilter> {

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