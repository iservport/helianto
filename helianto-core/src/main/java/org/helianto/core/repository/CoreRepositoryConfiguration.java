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

package org.helianto.core.repository;

import org.helianto.core.repository.base.AbstractRepositoryConfiguration;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
public class CoreRepositoryConfiguration extends AbstractRepositoryConfiguration {
	
	/**
	 * Default constructor.
	 */
	public CoreRepositoryConfiguration() { }
	
	/**
	 * User association data access.
	 */
	@Bean
	public FilterDao<UserAssociation> userAssociationDao() {
		return getFilterDao(UserAssociation.class, "parent", "child");
	}

	/**
	 * User role data access.
	 */
	@Bean
	public FilterDao<UserRole> userRoleDao() {
		return getFilterDao(UserRole.class, "userGroup", "service", "serviceExtension");
	}

}
