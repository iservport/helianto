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

package org.helianto.bootstrap.service;

import java.util.Date;

import org.helianto.bootstrap.CoreInstallationMgr;
import org.helianto.bootstrap.ServiceInstallationMgr;
import org.helianto.bootstrap.UserInstallationMgr;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Service;
import org.helianto.core.repository.EntityRepository;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.repository.OperatorRepository;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.core.repository.ServiceRepository;
import org.helianto.core.service.strategy.ProvinceResourceParserStrategy;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.helianto.user.repository.UserRepository;
import org.helianto.user.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation for <code>ServiceInstallationMgr</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("serviceInstallationMgr")
public class ServiceInstallationMgrImpl 
	implements ServiceInstallationMgr {

	private OperatorRepository operatorRepository;
	private ServiceRepository serviceRepository;
	private EntityRepository entityRepository;
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private CoreInstallationMgr coreInstallationMgr;
	private UserInstallationMgr userInstallationMgr;
	
	public Service installService(Operator defaultOperator, String serviceName) {
		
		defaultOperator = operatorRepository.save(defaultOperator);
		
		logger.debug("Check service {} installation ...", serviceName);
		Service service = serviceRepository.findByOperatorAndServiceName(defaultOperator, serviceName);
		if (service==null) {
			logger.debug("Will install service {} ...", serviceName);
			service = new Service(defaultOperator, serviceName);
			service = serviceRepository.save(service);
		}
		logger.debug("Sevice AVAILABLE as {}.", service);
		serviceRepository.flush();
		return service;
	}
	
	public Entity installEntity(Entity entity, boolean reinstall) {
		Operator operator = entity.getOperator();
		if (entity.getOperator()==null) {
			throw new IllegalArgumentException("An opertor is required.");
		}
		operator = operatorRepository.save(operator);
		String alias = entity.getAlias();
		
		logger.debug("Check entity {} installation with 'reinstall={}'", alias, reinstall);
		if (!reinstall) {
			entity = entityRepository.findByOperatorAndAlias(operator, alias);
		}

		if (entity==null) {
			logger.debug("Will install entity {} ...", alias);
			entity = entityRepository.save(new Entity(operator, alias));
		} 
		else {
			logger.debug("Entity AVAILABLE as {}.", entity);
			return entity;
		}
		entity = entityRepository.save(entity);
		
		return entity;
	}
	
	public Entity installEntity(Entity entity, Identity manager) {
		Operator operator = entity.getOperator();
		if (entity.getOperator()==null) {
			throw new IllegalArgumentException("An opertor is required.");
		}
		operator = operatorRepository.save(operator);
		String alias = entity.getAlias();		
		
		logger.debug("Check if is there already a current entity {} installation'", alias);
		Entity current = entityRepository.findByOperatorAndAlias(operator, alias);

		if (current==null) {
			logger.debug("Will install entity {} ...", alias);
			entityRepository.save(new Entity(operator, alias));
		} 
		else {
			logger.debug("Entity AVAILABLE as {}.", entity);
			return current;
		}
		
		// TODO remove 
		if (manager!=null) {
			Credential credential = coreInstallationMgr.installCredential(manager);
			return installManager(entity, credential);
		}
		return entity;
	}
	
	/**
	 * @deprecated
	 * 
	 * @param entity
	 * @param credential
	 * @return
	 */
	public Entity installManager(Entity entity, Credential credential) {
		Operator operator = entity.getOperator();
		
		UserGroup adminGroup = userRepository.save(new UserGroup(entity, "ADMIN"));
		
		Service adminService = operator.getServiceMap().get("ADMIN");
		if (adminService==null) {
			throw new IllegalArgumentException("Unable to load required service 'ADMIN' from operator {} "+operator);
		}
		
		// the user installing the entity is assigned administrative roles.
		UserRole adminRole = userRoleRepository.save(new UserRole(adminGroup, adminService, "READ, WRITE"));
		adminGroup.getRoles().add(adminRole);
		
		if (credential!=null) {
			UserAssociation adminAssociation = userInstallationMgr.installUser(adminGroup, credential, true);
			adminGroup.getChildAssociations().add(adminAssociation);
			logger.debug("Association to ADMIN group AVAILABLE as {}.", adminAssociation);
		}
		userRoleRepository.flush();
		
		UserGroup userGroup = userRepository.save(new UserGroup(entity, "USER"));
		
		Service userService = operator.getServiceMap().get("USER");
		if (userService==null) {
			throw new IllegalArgumentException("Unable to load required service 'USER' from operator {} "+operator);
		}
		
		UserRole userRole = userRoleRepository.save(new UserRole(userGroup, userService, "READ, WRITE"));
		userGroup.getRoles().add(userRole);
		
		if (credential!=null) {
			UserAssociation userAssociation = userInstallationMgr.installUser(userGroup, credential, true);
			userGroup.getChildAssociations().add(userAssociation);
			logger.debug("Association to USER group AVAILABLE as {}.", userAssociation);
		}
		userRoleRepository.flush();
		
		entity.setInstallDate(new Date());
		logger.debug("Entity {} installation finished at {}.", entity.getAlias(), entity.getInstallDate());

		return entity;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceInstallationMgrImpl.class);

}
