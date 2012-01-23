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

import org.helianto.core.Category;
import org.helianto.core.Country;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.EntityPreference;
import org.helianto.core.Identity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.PersonalAddress;
import org.helianto.core.Province;
import org.helianto.core.PublicAddress;
import org.helianto.core.PublicEntity2;
import org.helianto.core.PublicEntityKey;
import org.helianto.core.PublicEnumerator;
import org.helianto.core.Server;
import org.helianto.core.Service;
import org.helianto.core.Unit;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRequest;
import org.helianto.core.UserRole;
import org.helianto.core.repository.base.AbstractRepositoryConfiguration;
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
	 * Category data access.
	 */
	@Bean
	public FilterDao<Category> categoryDao() {
		return getFilterDao(Category.class, "entity", "categoryGroup", "categoryCode");
	}

	/**
	 * Country data access.
	 */
	@Bean
	public FilterDao<Country> countryDao() {
		return getFilterDao(Country.class, "operator", "countryCode");
	}

	/**
	 * Credential data access.
	 */
	@Bean
	public FilterDao<Credential> credentialDao() {
		return getFilterDao(Credential.class, "identity");
	}

	/**
	 * Entity data access.
	 */
	@Bean
	public FilterDao<Entity> entityDao() {
		return getFilterDao(Entity.class, "operator", "alias");
	}

	/**
	 * Entity preference data access.
	 */
	@Bean
	public FilterDao<EntityPreference> entityPreferenceDao() {
		return getFilterDao(EntityPreference.class, "entity", "keyType");
	}

	/**
	 * Identity data access.
	 */
	@Bean
	public FilterDao<Identity> identityDao() {
		return getFilterDao(Identity.class, "principal");
	}

	/**
	 * Address database data access.
	 */
	@Bean
	public FilterDao<PublicAddress> publicAddressDao() {
		return getFilterDao(PublicAddress.class, "operator", "postalCode");
	}

	/**
	 * Personal address access.
	 */
	@Bean
	public FilterDao<PersonalAddress> personalAddressDao() {
		return getFilterDao(PersonalAddress.class, "identity", "addressType");
	}

	/**
	 * Internal enumerator data access.
	 */
	@Bean
	public FilterDao<InternalEnumerator> internalEnumeratorDao() {
		return getFilterDao(InternalEnumerator.class, "entity", "typeName");
	}

	/**
	 * Public enumerator data access.
	 */
	@Bean
	public FilterDao<PublicEnumerator> publicEnumeratorDao() {
		return getFilterDao(PublicEnumerator.class, "operator", "typeName");
	}

	/**
	 * Key type data access.
	 */
	@Bean
	public FilterDao<KeyType> keyTypeDao() {
		return getFilterDao(KeyType.class, "operator", "keyCode");
	}

	/**
	 * Operator data access.
	 */
	@Bean
	public FilterDao<Operator> operatorDao() {
		return getFilterDao(Operator.class, "operatorName");
	}

	/**
	 * Province data access.
	 */
	@Bean
	public FilterDao<Province> provinceDao() {
		return getFilterDao(Province.class, "operator", "provinceCode");
	}

	/**
	 * Server data access.
	 */
	@Bean
	public FilterDao<Server> serverDao() {
		return getFilterDao(Server.class, "operator", "serverName");
	}

	/**
	 * Service data access.
	 */
	@Bean
	public FilterDao<Service> serviceDao() {
		return getFilterDao(Service.class, "operator", "serviceName");
	}

	/**
	 * Unit data access.
	 */
	@Bean
	public FilterDao<Unit> unitDao() {
		return getFilterDao(Unit.class, "entity", "unitCode");
	}

	/**
	 * User association data access.
	 */
	@Bean
	public FilterDao<UserAssociation> userAssociationDao() {
		return getFilterDao(UserAssociation.class, "parent", "child");
	}

	/**
	 * User group data access.
	 */
	@Bean
	public FilterDao<UserGroup> userGroupDao() {
		return getFilterDao(UserGroup.class, "entity", "userKey");
	}

	/**
	 * User log data access.
	 */
	@Bean
	public FilterDao<UserLog> userLogDao() {
		return getFilterDao(UserLog.class, "user", "lastEvent");
	}

	/**
	 * User role data access.
	 */
	@Bean
	public FilterDao<UserRole> userRoleDao() {
		return getFilterDao(UserRole.class, "userGroup", "service", "serviceExtension");
	}

	/**
	 * User request data access.
	 */
	@Bean
	public FilterDao<UserRequest> userRequestDao() {
		return getFilterDao(UserRequest.class, "userGroup", "internalNumber");
	}
	
	/**
	 * Public entity data access.
	 */
	@Bean
	public FilterDao<PublicEntity2> publicEntityDao() {
		return getFilterDao(PublicEntity2.class, "entity", "entityAlias");
	}

	/**
	 * Public entity key data access.
	 */
	@Bean
	public FilterDao<PublicEntityKey> publicEntityKeyDao() {
		return getFilterDao(PublicEntityKey.class, "publicEntity", "keyType");
	}

}
