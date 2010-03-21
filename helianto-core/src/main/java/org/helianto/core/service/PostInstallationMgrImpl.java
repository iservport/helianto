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
import org.helianto.core.UserGroup;
import org.helianto.core.repository.BasicDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation for <code>PostInstallation</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("postInstallationMgr")
@Transactional
public class PostInstallationMgrImpl implements PostInstallationMgr {

	public Operator installOperator(String defaultOperatorName, boolean reinstall) {
		if (defaultOperatorName==null) {
			defaultOperatorName = "DEFAULT";
		}
		Operator defaultOperator = null;
		if (!reinstall) {
			defaultOperator = operatorDao.findUnique(defaultOperatorName);
		}
		if (defaultOperator==null) {
			logger.info("About to install operator with name {}", defaultOperatorName); 
			defaultOperator = Operator.operatorFactory(defaultOperatorName, Locale.getDefault());
			defaultOperator = operatorDao.merge(defaultOperator);
		}
		logger.info("Operator {} is now available", defaultOperator);
		
		if (!defaultOperator.getServiceMap().containsKey("ADMIN")) {
			Service adminService = Service.serviceFactory(defaultOperator, "ADMIN");
			logger.info("Admin service is installed by default as {}", adminService);
			defaultOperator.getServiceMap().put(adminService.getServiceName(), adminService);
		}
		else {
			logger.debug("Admin service alredy installed.");
		}
		
		if (!defaultOperator.getServiceMap().containsKey("USER")) {
			Service userService = Service.serviceFactory(defaultOperator, "USER");
			logger.info("User service is installed by default as {}", userService);
			defaultOperator.getServiceMap().put(userService.getServiceName(), userService);
		}
		else {
			logger.debug("User service alredy installed.");
		}
				
		return defaultOperator;
	}

	public void installProvinces(Operator defaultOperator, Resource rs) {
		List<Province> provinceList = provinceResourceParserStrategy.parseProvinces(defaultOperator, rs);
		Operator managedOperator = operatorDao.merge(defaultOperator);
		for (Province province: provinceList) {
	    	if (provinceDao.findUnique(defaultOperator, province.getProvinceCode())==null) {
	    		if (province instanceof City && province.getParent()!=null) {
	    			Province managedParent = provinceDao.findUnique(defaultOperator, province.getParent().getProvinceCode());
	    			if (managedParent==null) {
	    				managedParent = provinceDao.merge(province.getParent());
	    			}
	    			province.setParent(managedParent);
	    		}
	    		province.setOperator(managedOperator);
		        provinceDao.merge(province);
		        logger.info("Created province {}.", province);
		        provinceDao.flush();
	    	}
	    	else {
	    		logger.info("Found province {}.", province);
	    	}
		}
	}
	
	public KeyType installKey(Operator defaultOperator, String keyCode) {
		KeyType keyType = keyTypeDao.findUnique(defaultOperator, keyCode);
		if (keyType==null) {
			logger.info("About to install key type {}", keyCode);
			Operator managedOperator = operatorDao.merge(defaultOperator);
			keyType = KeyType.keyTypeFactory(managedOperator, keyCode);
			keyType = keyTypeDao.merge(keyType);
		}
		logger.info("KeyType {} is now available", keyType);
		return keyType;
	}

	public Service installService(Operator defaultOperator, String serviceName) {
		Service service = serviceDao.findUnique(defaultOperator, serviceName);
		if (service==null) {
			logger.info("About to install service with name {}", serviceName);
			Operator managedOperator = operatorDao.merge(defaultOperator);
			service = Service.serviceFactory(managedOperator, serviceName);
			service = serviceDao.merge(service);
		}
		logger.info("Sevice {} is now available.", service);
		return service;
	}
	
	public Entity installEntity(Operator defaultOperator, String entityAlias, boolean reinstall) {
		Entity defaultEntity = null;
		if (!reinstall) {
			defaultEntity = entityDao.findUnique(defaultOperator, entityAlias);
		}
		if (defaultEntity==null) {
			logger.info("About to install entity with alias {}", entityAlias); 
			Operator managedOperator = operatorDao.merge(defaultOperator);
			defaultEntity = Entity.entityFactory(managedOperator, entityAlias);
			defaultEntity = entityDao.merge(defaultEntity);
		} 
		logger.info("Entity {} is now available.", defaultEntity);
		
		return defaultEntity;
	}
	
	public UserGroup instalUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall) {
		UserGroup userGroup = null;
		if (!reinstall) {
			userGroup = userGroupDao.findUnique(defaultEntity, userGroupName);
		}
		if (userGroup==null) {
			userGroup = UserGroup.userGroupFactory(defaultEntity, userGroupName);
			logger.info("About to install user group with name {}", userGroupName);
			userGroupDao.persist(userGroup);
		}
		logger.info("UserGroup {} is now available.", userGroup);
		
		return userGroup;
	}
	
	// collabs
	
	private BasicDao<Operator> operatorDao;
	private BasicDao<Province> provinceDao;
	private BasicDao<KeyType> keyTypeDao;
	private BasicDao<Service> serviceDao;
	private BasicDao<Entity> entityDao;
	private BasicDao<UserGroup> userGroupDao;
	private ProvinceResourceParserStrategy provinceResourceParserStrategy;

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
	
	@javax.annotation.Resource(name="provinceResourceParserStrategy")
	public void setProvinceResourceParserStrategy(ProvinceResourceParserStrategy provinceResourceParserStrategy) {
		this.provinceResourceParserStrategy = provinceResourceParserStrategy;
	}
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(PostInstallationMgrImpl.class);

}
