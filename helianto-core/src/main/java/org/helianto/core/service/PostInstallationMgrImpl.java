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

package org.helianto.core.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Service;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.repository.BasicDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

/**
 * Default implementation for <code>PostInstallation</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("postInstallationMgr")
public class PostInstallationMgrImpl implements PostInstallationMgr {

	public Operator installOperator(String defaultOperatorName, boolean reinstall) {
		
		if (defaultOperatorName==null) {
			defaultOperatorName = "DEFAULT";
		}
		
		logger.debug("Check operator {} installation with 'reinstall={}'", defaultOperatorName, reinstall);
		Operator defaultOperator = null;
		if (!reinstall) {
			defaultOperator = operatorDao.findUnique(defaultOperatorName);
		}
		if (defaultOperator==null) {
			logger.debug("Will install operator {} ...", defaultOperatorName); 
			defaultOperator = new Operator(defaultOperatorName, Locale.getDefault());
			operatorDao.saveOrUpdate(defaultOperator);
		}
		logger.debug("Default operator AVAILABLE as {}.", defaultOperator);
		
		Service adminService = installService(defaultOperator, "ADMIN");
		defaultOperator.getServiceMap().put("ADMIN", adminService);
		
		Service userService = installService(defaultOperator, "USER");
		defaultOperator.getServiceMap().put("USER", userService);
				
		return defaultOperator;
	}

	public void installProvinces(Operator defaultOperator, Resource rs) {
		
		operatorDao.saveOrUpdate(defaultOperator);
		List<Province> provinceList = provinceResourceParserStrategy.parseProvinces(defaultOperator, rs);
		installProvinces(defaultOperator, provinceList);
	}
	
	public void installProvinces(Operator defaultOperator, List<Province> provinceList) {
		
		logger.debug("Will install {} province(s) ...", provinceList.size());
		Collections.sort(provinceList);
		// due to problems with postgres, the installation is now in two phases
		// first only  provinces
		for (Province p: provinceList) {
			if (p.getClass().equals(Province.class)) {
				Province province = provinceDao.findUnique(defaultOperator, p.getProvinceCode());
		    	if (province==null) {
		    		logger.debug("New province {}", p.getProvinceCode());
		    		p.setOperator(defaultOperator);
		    		if (p.getParent()!=null) {
		    			throw new IllegalArgumentException("Progrmming error: instances of Province class must not have a parent");
		    		}
			        provinceDao.saveOrUpdate(p);
		    	}
		    	else {
			    	logger.debug("Province AVAILABLE as {}.", province);	    		
		    	}
			}
		}
		provinceDao.flush();
    	// then other descendants
		for (Province d: provinceList) {
			if (!d.getClass().equals(Province.class)) {
				Province province = provinceDao.findUnique(defaultOperator, d.getProvinceCode());
		    	if (province==null) {
		    		logger.debug("New province {}", d.getProvinceCode());
		    		d.setOperator(defaultOperator);
		    		if (d.getParent()!=null) {
		    			Province parent = provinceDao.findUnique(defaultOperator, d.getParent().getProvinceCode());
		    			if (parent==null) {
		    				logger.debug("New parent {}", d.getParent().getProvinceCode());
		    				d.setParent(parent);
		    			}
		    			else {
		    				logger.debug("Current parent {}", d.getParent().getProvinceCode());
		    			}
		    		}
			        provinceDao.saveOrUpdate(d);
		    	}
		    	else {
			    	logger.debug("Province AVAILABLE as {}.", province);	    		
		    	}
			}
		}
		
	}
	
	public KeyType installKey(Operator defaultOperator, String keyCode) {
		
		operatorDao.saveOrUpdate(defaultOperator);
		
		logger.debug("Check key code {} installation ...", keyCode);
		KeyType keyType = keyTypeDao.findUnique(defaultOperator, keyCode);
		if (keyType==null) {
			logger.debug("Will install key code {} ...", keyCode); 
			keyType = new KeyType(defaultOperator, keyCode);
			keyTypeDao.saveOrUpdate(keyType);
		}
		logger.debug("KeyType  AVAILABLE as {}.", keyType);
		
		return keyType;
	}

	public Service installService(Operator defaultOperator, String serviceName) {
		
		operatorDao.saveOrUpdate(defaultOperator);
		
		logger.debug("Check service {} installation ...", serviceName);
		Service service = serviceDao.findUnique(defaultOperator, serviceName);
		if (service==null) {
			logger.debug("Will install service {} ...", serviceName);
			service = new Service(defaultOperator, serviceName);
			serviceDao.saveOrUpdate(service);
		}
		logger.debug("Sevice AVAILABLE as {}.", service);
		
		return service;
	}
	
	public Entity installEntity(Operator defaultOperator, String entityAlias, String managerPrincipal, boolean reinstall) {
		operatorDao.saveOrUpdate(defaultOperator);
		
		logger.debug("Check entity {} installation with 'reinstall={}'", entityAlias, reinstall);
		Entity defaultEntity = null;
		if (!reinstall) {
			defaultEntity = entityDao.findUnique(defaultOperator, entityAlias);
		}

		if (defaultEntity==null) {
			logger.debug("Will install entity {} ...", entityAlias);
			defaultEntity = new Entity(defaultOperator, entityAlias);
			entityDao.saveOrUpdate(defaultEntity);
		} 
		else {
			logger.debug("Entity AVAILABLE as {}.", defaultEntity);
			return defaultEntity;
		}
		
		Credential credential = userMgr.installIdentity(managerPrincipal);

		return installEntity(defaultEntity, credential);
	}
	
	public Entity installEntity(Entity entity) {
		if (entity.getOperator()==null) {
			throw new IllegalArgumentException("An opertor is required.");
		}
		logger.debug("Check new entity {} installation", entity);
		if (entity.getManager()==null) {
			throw new IllegalArgumentException("Unable to install entity: a manager identity is required.");
		}
		entityDao.saveOrUpdate(entity);
		Credential credential = userMgr.installCredential(entity.getManager());
		logger.debug("Clearing manager supplied with entity {} to avoid duplicate installation...", entity);
		entity.setManager(null);
		return installEntity(entity, credential);
	}
	
	protected Entity installEntity(Entity entity, Credential credential) {
		Operator operator = entity.getOperator();
		
		UserGroup adminGroup  = new UserGroup(entity, "ADMIN");
		userGroupDao.saveOrUpdate(adminGroup);
		
		Service adminService = operator.getServiceMap().get("ADMIN");
		if (adminService==null) {
			throw new IllegalArgumentException("Unable to load required service 'ADMIN' from operator {} "+operator);
		}
		
		UserRole adminRole = new UserRole(adminGroup, adminService, "MANAGER");
		adminGroup.getRoles().add(adminRole);
		userRoleDao.saveOrUpdate(adminRole);
		
		UserAssociation adminAssociation = userMgr.installUser(adminGroup, credential, true);
		adminGroup.getChildAssociations().add(adminAssociation);
		logger.debug("Association to ADMIN group AVAILABLE as {}.", adminAssociation);
		
		UserGroup userGroup  = new UserGroup(entity, "USER");
		userGroupDao.saveOrUpdate(userGroup);
		
		Service userService = operator.getServiceMap().get("USER");
		if (userService==null) {
			throw new IllegalArgumentException("Unable to load required service 'USER' from operator {} "+operator);
		}
		
		UserRole userRole = new UserRole(userGroup, userService, "ALL");
		userGroup.getRoles().add(userRole);
		userRoleDao.saveOrUpdate(userRole);
		
		UserAssociation userAssociation = userMgr.installUser(userGroup, credential, true);
		userGroup.getChildAssociations().add(userAssociation);
		logger.debug("Association to USER group AVAILABLE as {}.", userAssociation);
		
		entityDao.refresh(entity);
		entity.setInstallDate(new Date());
		entityDao.flush();
		logger.debug("Entity {} installation finished at {}.", userAssociation, entity.getInstallDate());

		return entity;
	}
	
//	public Entity installEntityWORK_IN_PROGRESS(Operator defaultOperator, String entityAlias, String managerPrincipal, boolean reinstall) {
//		
//		operatorDao.saveOrUpdate(defaultOperator);
//		
//		logger.debug("Check entity {} installation with 'reinstall={}'", entityAlias, reinstall);
//		Entity defaultEntity = null;
//		if (!reinstall) {
//			defaultEntity = entityDao.findUnique(defaultOperator, entityAlias);
//		}
//		
//		if (defaultEntity==null) {
//			logger.debug("Will install entity {} ...", entityAlias);
//			defaultEntity = new Entity(defaultOperator, entityAlias);
//			entityDao.saveOrUpdate(defaultEntity);
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
	
	private BasicDao<Operator> operatorDao;
	private BasicDao<Province> provinceDao;
	private BasicDao<KeyType> keyTypeDao;
	private BasicDao<Service> serviceDao;
	private BasicDao<Entity> entityDao;
	private BasicDao<UserGroup> userGroupDao;
	private BasicDao<UserRole> userRoleDao;
	private ProvinceResourceParserStrategy provinceResourceParserStrategy;
	private UserMgr userMgr;

	@javax.annotation.Resource(name="operatorDao")
	public void setOperatorDao(BasicDao<Operator> operatorDao) {
		this.operatorDao = operatorDao;
	}
	
	@javax.annotation.Resource(name="provinceDao")
	public void setProvinceDao(BasicDao<Province> provinceDao) {
		this.provinceDao = provinceDao;
	}
	
	@javax.annotation.Resource(name="keyTypeDao")
	public void setKeyTypeDao(BasicDao<KeyType> keyTypeDao) {
		this.keyTypeDao = keyTypeDao;
	}
	
	@javax.annotation.Resource(name="serviceDao")
	public void setServiceDao(BasicDao<Service> serviceDao) {
		this.serviceDao = serviceDao;
	}

	@javax.annotation.Resource(name="entityDao")
	public void setEntityDao(BasicDao<Entity> entityDao) {
		this.entityDao = entityDao;
	}
	
	@javax.annotation.Resource(name="userGroupDao")
	public void setUserGroupDao(BasicDao<UserGroup> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}
	
	@javax.annotation.Resource(name="userRoleDao")
	public void setUserRoleDao(BasicDao<UserRole> userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
	@javax.annotation.Resource(name="provinceResourceParserStrategy")
	public void setProvinceResourceParserStrategy(ProvinceResourceParserStrategy provinceResourceParserStrategy) {
		this.provinceResourceParserStrategy = provinceResourceParserStrategy;
	}
	
	@javax.annotation.Resource(name="userMgr")
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PostInstallationMgrImpl.class);

}
