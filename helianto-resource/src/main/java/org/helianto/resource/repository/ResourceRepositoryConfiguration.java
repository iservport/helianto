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

import org.helianto.core.repository.FilterDao;
import org.helianto.core.repository.base.AbstractRepositoryConfiguration;
import org.helianto.resource.domain.Resource;
import org.helianto.resource.domain.ResourceGroup;
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
	 * Resource group data access.
	 */
	@Bean
	public FilterDao<ResourceGroup> resourceGroupDao() {
		return getFilterDao(ResourceGroup.class, "entity", "resourceCode");
	}

	/**
	 * Resource data access.
	 */
	@Bean
	public FilterDao<Resource> resourceDao() {
		return getFilterDao(Resource.class, "entity", "resourceCode");
	}

}
