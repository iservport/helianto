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

import java.util.List;
import java.util.Locale;

import org.helianto.core.City;
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
		
		logger.info("Check operator {} installation with 'reinstall={}'", defaultOperatorName, reinstall);
		Operator defaultOperator = null;
		if (!reinstall) {
			defaultOperator = operatorDao.findUnique(defaultOperatorName);
		}
		if (defaultOperator==null) {
			logger.info("Will install operator {} ...", defaultOperatorName); 
			defaultOperator = Operator.operatorFactory(defaultOperatorName, Locale.getDefault());
			operatorDao.saveOrUpdate(defaultOperator);
		}
		logger.info("Default operator AVAILABLE as {}.", defaultOperator);
		
		Service adminService = installService(defaultOperator, "ADMIN");
		defaultOperator.getServiceMap().put("ADMIN", adminService);
		
		Service userService = installService(defaultOperator, "USER");
		defaultOperator.getServiceMap().put("USER", userService);
				
		return defaultOperator;
	}

	public void installProvinces(Operator defaultOperator, Resource rs) {
		
		operatorDao.saveOrUpdate(defaultOperator);
		List<Province> provinceList = provinceResourceParserStrategy.parseProvinces(defaultOperator, rs);
		
		logger.info("Will install {} province(s) ...", provinceList.size());
		for (Province province: provinceList) {
	    	if (provinceDao.findUnique(defaultOperator, province.getProvinceCode())==null) {
	    		if (province instanceof City && province.getParent()!=null) {
	    			Province managedParent = provinceDao.findUnique(defaultOperator, province.getParent().getProvinceCode());
	    			if (managedParent==null) {
	    				provinceDao.saveOrUpdate(province.getParent());
	    			}
	    			province.setParent(managedParent);
	    		}
	    		province.setOperator(defaultOperator);
		        provinceDao.saveOrUpdate(province);
	    	}
	    	else {
	    		logger.info("Province AVAILABLE as {}.", province);
	    	}
		}
		
	}
	
	public KeyType installKey(Operator defaultOperator, String keyCode) {
		
		operatorDao.saveOrUpdate(defaultOperator);
		
		logger.info("Check key code {} installation ...", keyCode);
		KeyType keyType = keyTypeDao.findUnique(defaultOperator, keyCode);
		if (keyType==null) {
			logger.info("Will install key code {} ...", keyCode); 
			keyType = KeyType.keyTypeFactory(defaultOperator, keyCode);
			keyTypeDao.saveOrUpdate(keyType);
		}
		logger.info("KeyType  AVAILABLE as {}.", keyType);
		
		return keyType;
	}

	public Service installService(Operator defaultOperator, String serviceName) {
		
		operatorDao.saveOrUpdate(defaultOperator);
		
		logger.info("Check service {} installation ...", serviceName);
		Service service = serviceDao.findUnique(defaultOperator, serviceName);
		if (service==null) {
			logger.info("Will install service {} ...", serviceName);
			service = Service.serviceFactory(defaultOperator, serviceName);
			serviceDao.saveOrUpdate(service);
		}
		logger.info("Sevice AVAILABLE as {}.", service);
		
		return service;
	}
	
	public Entity installEntity(Operator defaultOperator, String entityAlias, String managerPrincipal, boolean reinstall) {
		
		operatorDao.saveOrUpdate(defaultOperator);
		
		logger.info("Check entity {} installation with 'reinstall={}'", entityAlias, reinstall);
		Entity defaultEntity = null;
		if (!reinstall) {
			defaultEntity = entityDao.findUnique(defaultOperator, entityAlias);
		}
		
		if (defaultEntity==null) {
			logger.info("Will install entity {} ...", entityAlias);
			defaultEntity = Entity.entityFactory(defaultOperator, entityAlias);
			entityDao.saveOrUpdate(defaultEntity);
		} 
		logger.info("Entity AVAILABLE as {}.", defaultEntity);
		
		//
		UserGroup adminGroup = installUserGroup(defaultEntity, "ADMIN", reinstall);
		
		Service adminService = defaultOperator.getServiceMap().get("ADMIN");
		if (adminService==null) {
			throw new IllegalArgumentException("Unable to load required service 'ADMIN' from operator {} "+defaultOperator);
		}
		
		UserRole adminRole = installUserRole(adminGroup, adminService, "MANAGER");
		adminGroup.getRoles().add(adminRole);
		
		UserAssociation adminAssociation = userMgr.installUser(adminGroup, managerPrincipal);
		logger.info("Association to ADMIN group AVAILABLE as {}.", adminAssociation);
		
		//
		UserGroup userGroup = installUserGroup(defaultEntity, "USER", reinstall);
		
		Service userService = defaultOperator.getServiceMap().get("USER");
		if (userService==null) {
			throw new IllegalArgumentException("Unable to load required service 'USER' from operator {} "+defaultOperator);
		}
		
		UserRole userRole = installUserRole(userGroup, userService, "MANAGER");
		userGroup.getRoles().add(userRole);
		
		UserAssociation userAssociation = userMgr.installUser(userGroup, managerPrincipal);
		logger.info("Association to USER group AVAILABLE as {}.", userAssociation);

		return defaultEntity;
	}
	
	public UserGroup installUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall) {

		entityDao.saveOrUpdate(defaultEntity);
		
		logger.info("Check user (group) {} installation with 'reinstall={}'", userGroupName, reinstall);
		UserGroup userGroup = null;
		if (!reinstall) {
			userGroup = userGroupDao.findUnique(defaultEntity, userGroupName);
		}
		if (userGroup==null) {
			logger.info("Will install user (group) {} ...", userGroupName);
			userGroup = UserGroup.userGroupFactory(defaultEntity, userGroupName);
			userGroupDao.saveOrUpdate(userGroup);
		}
		logger.info("UserGroup AVAILABLE as {}.", userGroup);
		
		return userGroup;
	}
	
	public UserRole installUserRole(UserGroup userGroup, Service service, String extension) {
		
		UserRole userRole = userRoleDao.findUnique(userGroup, service, extension);
		if (userGroup==null) {
			logger.info("Will install required user role USER_ALL for user group USER ...");
			userRole = new UserRole(userGroup, service, "ALL");
			userRoleDao.saveOrUpdate(userRole);
		}
		logger.info("User role AVAILABLE as {}.", userRole);
		
		return userRole;
	}
	
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
