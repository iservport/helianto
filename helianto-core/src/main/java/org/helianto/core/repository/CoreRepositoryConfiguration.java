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

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PersonalAddress;
import org.helianto.core.domain.PrivateSequence;
import org.helianto.core.domain.Province;
import org.helianto.core.domain.PublicAddress;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.domain.PublicEntityKey;
import org.helianto.core.domain.PublicSequence;
import org.helianto.core.domain.Server;
import org.helianto.core.domain.Service;
import org.helianto.core.domain.Unit;
import org.helianto.core.repository.base.AbstractRepositoryConfiguration;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserLog;
import org.helianto.user.domain.UserRequest;
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
	 * Entity data access.
	 */
	@Bean
	public FilterDao<Entity> entityDao() {
		return getFilterDao(Entity.class, "operator", "alias");
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
	public FilterDao<PrivateSequence> internalEnumeratorDao() {
		return getFilterDao(PrivateSequence.class, "entity", "typeName");
	}

	/**
	 * Public enumerator data access.
	 */
	@Bean
	public FilterDao<PublicSequence> publicEnumeratorDao() {
		return getFilterDao(PublicSequence.class, "operator", "typeName");
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
	public FilterDao<PublicEntity> publicEntityDao() {
		return getFilterDao(PublicEntity.class, "entity", "entityAlias", "class");
	}

	/**
	 * Public entity key data access.
	 */
	@Bean
	public FilterDao<PublicEntityKey> publicEntityKeyDao() {
		return getFilterDao(PublicEntityKey.class, "publicEntity", "keyType");
	}

}
