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

import org.helianto.core.repository.AbstractRepositoryConfiguration;
import org.helianto.core.repository.FilterDao;
import org.helianto.resource.ResourceAssociation;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.ResourceParameter;
import org.helianto.resource.ResourceParameterValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
public class ResourceRepositoryConfiguration extends AbstractRepositoryConfiguration {
	
	/**
	 * Resource association data access.
	 */
	@Bean
	public FilterDao<ResourceAssociation> resourceAssociationDao() {
		return getFilterDao(ResourceAssociation.class, "parent", "child");
	}

	/**
	 * Resource group data access.
	 */
	@Bean
	public FilterDao<ResourceGroup> resourceGroupDao() {
		return getFilterDao(ResourceGroup.class, "entity", "resourceCode");
	}

	/**
	 * Resource parameter data access.
	 */
	@Bean
	public FilterDao<ResourceParameter> resourceParameterDao() {
		return getFilterDao(ResourceParameter.class, "entity", "parameterCode");
	}

	/**
	 * Resource parameter value data access.
	 */
	@Bean
	public FilterDao<ResourceParameterValue> resourceParameterValueDao() {
		return getFilterDao(ResourceParameterValue.class, "resource", "parameter");
	}

}
