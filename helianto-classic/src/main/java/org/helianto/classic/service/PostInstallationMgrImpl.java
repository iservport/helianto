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

package org.helianto.classic.service;

import org.helianto.classic.IdentityMgr;
import org.helianto.classic.PostInstallationMgr;
import org.helianto.classic.service.internal.ProvinceResourceParserStrategy;
import org.helianto.core.domain.*;
import org.helianto.core.repository.*;
import org.helianto.user.UserMgr;
import org.helianto.user.repository.UserGroupRepository;
import org.helianto.user.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Default implementation for <code>PostInstallation</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
//@org.springframework.stereotype.Service("postInstallationMgr")
public class PostInstallationMgrImpl implements PostInstallationMgr {

	@Transactional
	public Operator installOperator(String defaultOperatorName, boolean reinstall) {
		
		if (defaultOperatorName==null) {
			defaultOperatorName = "DEFAULT";
		}
		
		logger.debug("Check operator {} installation with 'reinstall={}'", defaultOperatorName, reinstall);
		Operator defaultOperator = null;
		if (!reinstall) {
			defaultOperator = opertatorRepository.findByOperatorName(defaultOperatorName);
		}
		if (defaultOperator==null) {
			logger.debug("Will install operator {} ...", defaultOperatorName); 
			defaultOperator = opertatorRepository.save(new Operator(defaultOperatorName, Locale.getDefault()));
		}
		logger.debug("Default operator AVAILABLE as {}.", defaultOperator);
		
		Service adminService = installService(defaultOperator, "ADMIN");
		defaultOperator.getServiceMap().put("ADMIN", adminService);
		
		Service userService = installService(defaultOperator, "USER");
		defaultOperator.getServiceMap().put("USER", userService);
		opertatorRepository.flush();
		return defaultOperator;
	}

	@Transactional
	public void installProvinces(Operator defaultOperator, Resource rs) {
		
		List<Province> provinceList = provinceResourceParserStrategy.parseProvinces(defaultOperator, rs);
		installProvinces(defaultOperator, provinceList);
	}
	
	@Transactional
	public void installProvinces(Operator defaultOperator, List<Province> provinceList) {
		
		logger.debug("Will install {} province(s) ...", provinceList.size());
		Collections.sort(provinceList);
		// due to problems with postgres, the installation is now in two phases
		// first only  provinces
		for (Province p: provinceList) {
			if (p.getClass().equals(Province.class)) {
				Province province = provinceRepository.findByOperatorAndProvinceCode(defaultOperator, p.getProvinceCode());
		    	if (province==null) {
		    		logger.debug("New province {}", p.getProvinceCode());
		    		p.setOperator(defaultOperator);
		    		if (p.getParent()!=null) {
		    			throw new IllegalArgumentException("Progrmming error: instances of Province class must not have a parent");
		    		}
			        provinceRepository.saveAndFlush(p);
		    	}
		    	else {
			    	logger.debug("Province AVAILABLE as {}.", province);	    		
		    	}
			}
		}
		provinceRepository.flush();
    	// then other descendants
		for (Province d: provinceList) {
			if (!d.getClass().equals(Province.class)) {
				Province province = provinceRepository.findByOperatorAndProvinceCode(defaultOperator, d.getProvinceCode());
		    	if (province==null) {
		    		logger.debug("New province {}", d.getProvinceCode());
		    		d.setOperator(defaultOperator);
		    		if (d.getParent()!=null) {
		    			Province parent = provinceRepository.findByOperatorAndProvinceCode(defaultOperator, d.getParent().getProvinceCode());
		    			if (parent==null) {
		    				logger.debug("New parent {}", d.getParent().getProvinceCode());
		    				d.setParent(parent);
		    			}
		    			else {
		    				logger.debug("Current parent {}", d.getParent().getProvinceCode());
		    			}
		    		}
			        provinceRepository.saveAndFlush(d);
		    	}
		    	else {
			    	logger.debug("Province AVAILABLE as {}.", province);	    		
		    	}
			}
		}
		provinceRepository.flush();
	}
	
	@Transactional
	public KeyType installKey(Operator defaultOperator, String keyCode) {
		
		logger.debug("Check key code {} installation ...", keyCode);
		KeyType keyType = keyTypeRepository.findByOperatorAndKeyCode(defaultOperator, keyCode);
		if (keyType==null) {
			logger.debug("Will install key code {} ...", keyCode); 
			keyType = keyTypeRepository.saveAndFlush(new KeyType(defaultOperator, keyCode));
		}
		logger.debug("KeyType  AVAILABLE as {}.", keyType);
		return keyType;
	}

	@Transactional
	public Service installService(Operator defaultOperator, String serviceName) {
		
		serviceRepository.flush();
		Assert.notNull(defaultOperator);
		
		logger.debug("Check service {} installation ...", serviceName);
		Service service = serviceRepository.findByOperatorAndServiceName(defaultOperator, serviceName);
		if (service==null) {
			logger.debug("Will install service {} ...", serviceName);
			service = serviceRepository.saveAndFlush(new Service(defaultOperator, serviceName));
		}
		logger.debug("Sevice AVAILABLE as {}.", service);
		return service;
	}
	
	@Transactional
	public Entity installEntity(Entity entity, boolean reinstall) {
		String contextName = entity.getContextName();
		String alias = entity.getAlias();

		logger.debug("Check entity {} installation with 'reinstall={}'", alias, reinstall);
		if (!reinstall) {
			entity = entityRepository.findByContextNameAndAlias(contextName, alias);
		}

		if (entity==null) {
			logger.debug("Will install entity {} ...", alias);
			entity = entityRepository.saveAndFlush(new Entity(contextName, alias));
		} 
		else {
			logger.debug("Entity AVAILABLE as {}.", entity);
			return entity;
		}
		entityRepository.saveAndFlush(entity);
		
		return entity;
	}
	
	@Transactional
	public Entity installEntity(Entity entity, Identity manager) {
		String contextName = entity.getContextName();
		String alias = entity.getAlias();
		
		logger.debug("Check if is there already a current entity {} installation'", alias);
		Entity current = entityRepository.findByContextNameAndAlias(contextName, alias);

		if (current==null) {
			logger.debug("Will install entity {} ...", alias);
			entity = entityRepository.saveAndFlush(new Entity(contextName, alias));
		} 
		else {
			logger.debug("Entity AVAILABLE as {}.", entity);
			return current;
		}
		entityRepository.saveAndFlush(entity);
		
//		// TODO remove
//		if (manager!=null) {
//			Credential credential = identityMgr.installCredential(manager);
//			return installManager(entity, credential);
//		}
		return entity;
	}
	
//	/**
//	 * @deprecated
//	 *
//	 * @param entity
//	 * @param credential
//	 * @return
//	 */
//	@Transactional
//	public Entity installManager(Entity entity, Credential credential) {
//		Operator operator = entity.getOperator();
//
//		UserGroup adminGroup = userGroupRepository.save(new UserGroup(entity, "ADMIN"));
//
//		Service adminService = operator.getServiceMap().get("ADMIN");
//		if (adminService==null) {
//			throw new IllegalArgumentException("Unable to load required service 'ADMIN' from operator {} "+operator);
//		}
//
//		// the user installing the entity is assigned administrative roles.
//		UserRole adminRole = new UserRole(adminGroup, adminService, "READ, WRITE");
//		adminGroup.getRoles().add(adminRole);
//		userRoleRepository.save(adminRole);
//
//		if (credential!=null) {
//			UserAssociation adminAssociation = userMgr.installUser(adminGroup, credential, true);
//			adminGroup.getChildAssociations().add(adminAssociation);
//			logger.debug("Association to ADMIN group AVAILABLE as {}.", adminAssociation);
//		}
//		userRoleRepository.flush();
//
//		UserGroup userGroup = userGroupRepository.save(new UserGroup(entity, "USER"));
//
//		Service userService = operator.getServiceMap().get("USER");
//		if (userService==null) {
//			throw new IllegalArgumentException("Unable to load required service 'USER' from operator {} "+operator);
//		}
//
//		UserRole userRole = new UserRole(userGroup, userService, "READ, WRITE");
//		userGroup.getRoles().add(userRole);
//		userRoleRepository.save(userRole);
//
//		if (credential!=null) {
//			UserAssociation userAssociation = userMgr.installUser(userGroup, credential, true);
//			userGroup.getChildAssociations().add(userAssociation);
//			logger.debug("Association to USER group AVAILABLE as {}.", userAssociation);
//		}
//		userRoleRepository.flush();
//
//		entity.setInstallDate(new Date());
//		entityRepository.flush();
//		logger.debug("Entity {} installation finished at {}.", entity.getAlias(), entity.getInstallDate());
//
//		return entity;
//	}
	
//	public Entity installEntityWORK_IN_PROGRESS(Operator defaultOperator, String entityAlias, String managerPrincipal, boolean reinstall) {
//		
//		contextRepository.saveOrUpdate(defaultOperator);
//		
//		logger.debug("Check entity {} installation with 'reinstall={}'", entityAlias, reinstall);
//		Entity defaultEntity = null;
//		if (!reinstall) {
//			defaultEntity = entityRepository.findUnique(defaultOperator, entityAlias);
//		}
//		
//		if (defaultEntity==null) {
//			logger.debug("Will install entity {} ...", entityAlias);
//			defaultEntity = new Entity(defaultOperator, entityAlias);
//			entityRepository.saveOrUpdate(defaultEntity);
//		} 
//		logger.debug("Entity AVAILABLE as {}.", defaultEntity);
//		
//		Credential credential = userMgr.installIdentity(managerPrincipal);
//		
//		//
//		UserGroup adminGroup = installUserGroup(defaultEntity, "ADMIN", reinstall);
//		
//		Service adminService = defaultOperator.getServiceMap().get("ADMIN");
//		if (adminService==null) {
//			throw new IllegalArgumentException("Unable to load required service 'ADMIN' from operator {} "+defaultOperator);
//		}
//		
//		UserRole adminRole = installUserRole(adminGroup, adminService, "MANAGER");
//		adminGroup.getRoles().add(adminRole);
//		
//		UserAssociation adminAssociation = userMgr.installUser(adminGroup, credential, true);
//		logger.debug("Association to ADMIN group AVAILABLE as {}.", adminAssociation);
//		
//		//
//		UserGroup userGroup = installUserGroup(defaultEntity, "USER", reinstall);
//		
//		Service userService = defaultOperator.getServiceMap().get("USER");
//		if (userService==null) {
//			throw new IllegalArgumentException("Unable to load required service 'USER' from operator {} "+defaultOperator);
//		}
//		
//		UserRole userRole = installUserRole(userGroup, userService, "MANAGER");
//		userGroup.getRoles().add(userRole);
//		
//		UserAssociation userAssociation = userMgr.installUser(userGroup, credential, true);
//		logger.debug("Association to USER group AVAILABLE as {}.", userAssociation);
//
//		return defaultEntity;
//	}
//	
	// collabs
	
	private OperatorRepository opertatorRepository;
	private ProvinceRepository provinceRepository;
	private KeyTypeRepository keyTypeRepository;
	private ServiceRepository serviceRepository;
	private EntityRepository entityRepository;
	private UserGroupRepository userGroupRepository;
//	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private ProvinceResourceParserStrategy provinceResourceParserStrategy;
	private IdentityMgr identityMgr;
	private UserMgr userMgr;

	@javax.annotation.Resource
	public void setOperatorRepository(OperatorRepository contextRepository) {
		this.opertatorRepository = contextRepository;
	}
	
	@javax.annotation.Resource
	public void setProvinceRepository(ProvinceRepository provinceRepository) {
		this.provinceRepository = provinceRepository;
	}
	
	@javax.annotation.Resource
	public void setKeyTypeRepository(KeyTypeRepository keyTypeRepository) {
		this.keyTypeRepository = keyTypeRepository;
	}
	
	@javax.annotation.Resource
	public void setServiceRepository(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@javax.annotation.Resource
	public void setEntityRepository(EntityRepository entityRepository) {
		this.entityRepository = entityRepository;
	}
	
	@javax.annotation.Resource
	public void setUserGroupRepository(UserGroupRepository userGroupRepository) {
		this.userGroupRepository = userGroupRepository;
	}
	
	@javax.annotation.Resource
	public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}
	
	@javax.annotation.Resource(name="provinceResourceParserStrategy")
	public void setProvinceResourceParserStrategy(ProvinceResourceParserStrategy provinceResourceParserStrategy) {
		this.provinceResourceParserStrategy = provinceResourceParserStrategy;
	}
	
	@javax.annotation.Resource(name="userMgr")
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	@javax.annotation.Resource(name="identityMgr")
	public void setIdentityMgr(IdentityMgr identityMgr) {
		this.identityMgr = identityMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PostInstallationMgrImpl.class);

}
