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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.OperatorFilter;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * <code>NamespaceMgr</code> default implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("namespaceMgr")
@Transactional
public class NamespaceMgrImpl implements NamespaceMgr {

	public List<Operator> findOperator() {
		List<Operator> operatorList = (List<Operator>) operatorDao.find(new OperatorFilter());
		if (operatorList!=null && operatorList.size()>0) {
			if (logger.isDebugEnabled()) {
				logger.debug("Found "+operatorList.size()+" namespace operator(s)");
			}
		}
		else {
			Operator operator = firstTimeInstall();
			operatorList.add(operator);
			if (logger.isDebugEnabled()) {
				logger.debug("New default operator added to the list.");
			}
		}
    	return operatorList;
	}
	
	public Operator firstTimeInstall() {
		if (logger.isDebugEnabled()) {
			logger.debug("Likely a first time install, creating a default operator...");
		}
		Operator operator = Operator.operatorFactory("DEFAULT", null);
		return operatorDao.merge(operator);
	}

	@Transactional(readOnly=true)
	public Operator findOperatorByName(String operatorName) {
		return operatorDao.findUnique(operatorName);
	}

	public Operator storeOperator(Operator operator) {
		return operatorDao.merge(operator);
	}

	@Transactional(readOnly=true)
	public List<Province> findProvinces(ProvinceFilter filter) {
    	List<Province> provinceList = (List<Province>) provinceDao.find(filter);
    	if (logger.isDebugEnabled() && provinceList!=null) {
    		logger.debug("Found province list of size "+provinceList.size());
    	}
    	return provinceList;
	}

	@Transactional(readOnly=true)
	public Province prepareProvince(Province province) {
		Province managedProvince = provinceDao.merge(province);
		managedProvince.getOperator();
		provinceDao.evict(managedProvince);
		return managedProvince;
	}

	@Transactional(readOnly=true)
	public Province prepareNewProvince(Entity entity) {
		Entity managedEntity = entityDao.merge(entity);
		return Province.provinceFactory(managedEntity.getOperator());
	}

	@Transactional(readOnly=false)
	public Province storeProvince(Province province) {
		Province managedProvince =  provinceDao.merge(province);
		return managedProvince;
	}
	
	@Transactional(readOnly=true)
	public List<Entity> findEntities(EntityFilter filter) {
		List<Entity> entityList = (List<Entity>) entityDao.find(filter);
		if (logger.isDebugEnabled()) {
			logger.debug("Found "+entityList.size()+" entity(ies).");
		}
		return entityList;
	}
	
	@Transactional(readOnly=true)
	public Entity prepareEntity(Entity entity) {
		Entity managedEntity = entityDao.merge(entity);
		List<UserGroup> userList = new ArrayList<UserGroup>(managedEntity.getUsers());
		Collections.sort(userList);
		managedEntity.setUserList(userList);
    	if (logger.isDebugEnabled() && userList!=null) {
    		logger.debug("Loaded user list of size "+userList.size());
    	}
		entityDao.evict(managedEntity);
		return managedEntity;
	}
	
	public Entity storeEntity(Entity entity) {
		Operator managedOperator = operatorDao.merge(entity.getOperator());
		entity.setOperator(managedOperator);
		Entity managedEntity =  entityDao.merge(entity);
		return managedEntity;
	}

	@Transactional(readOnly=false)
	public List<KeyType> loadKeyTypes(Operator operator) {
		Operator managedOperator = operatorDao.merge(operator);
		List<KeyType> keyTypeList = new ArrayList<KeyType>(managedOperator.getKeyTypes());
    	if (logger.isDebugEnabled() && keyTypeList!=null) {
    		logger.debug("Loaded user list of size "+keyTypeList.size());
    	}
		return keyTypeList;
	}

	public KeyType storeKeyType(KeyType keyType) {
		Operator managedOperator = operatorDao.merge(keyType.getOperator());
		keyType.setOperator(managedOperator);
		KeyType managedKeyType =  keyTypeDao.merge(keyType);
		return managedKeyType;
	}

	@Transactional(readOnly=false)
	public List<Service> loadServices(Operator operator) {
		Operator managedOperator = operatorDao.merge(operator);
		List<Service> serviceList = new ArrayList<Service>(managedOperator.getServices());
    	if (logger.isDebugEnabled() && serviceList!=null) {
    		logger.debug("Loaded user list of size "+serviceList.size());
    	}
		return serviceList;
	}

	public Service storeService(Service service) {
		Operator managedOperator = operatorDao.merge(service.getOperator());
		service.setOperator(managedOperator);
		Service managedService =  serviceDao.merge(service);
		return managedService;
	}

	public UserRole storeUserRole(UserRole userRole) {
		UserRole managedUserRole =  userRoleDao.merge(userRole);
		return managedUserRole;
	}

	public Map<String, String> loadServiceNameMap(Operator operator, UserRole userRole) {
		Operator managedOperator = operatorDao.merge(operator);
		Map<String, String> serviceNameMap = new HashMap<String, String>();
		Set<Service> services = managedOperator.getServices();
		if (services!=null && services.size()>0) {
			for (Service service: services) {
				if (userRole.getService()==null) {
					// the first one as default, if empty
					userRole.setService(service);
				}
				serviceNameMap.put(String.valueOf(service.getId()), service.getServiceName());
			}
		}
		else {
			throw new IllegalArgumentException("Unable to map from empty services list.");
		}
    	if (logger.isDebugEnabled() && services!=null) {
    		logger.debug("Loaded service map "+serviceNameMap);
    	}
		return serviceNameMap;
	}

	// collabs
	
	private FilterDao<Operator, OperatorFilter> operatorDao;
	private FilterDao<Province, ProvinceFilter> provinceDao;
	private FilterDao<Entity, EntityFilter> entityDao;
	private BasicDao<KeyType> keyTypeDao;
	private BasicDao<Service> serviceDao;
	private BasicDao<UserRole> userRoleDao;
	
	@Resource(name="operatorDao")
	public void setOperatorDao(FilterDao<Operator, OperatorFilter> operatorDao) {
		this.operatorDao = operatorDao;
	}

    @Resource(name="provinceDao")
    public void setProvinceDao(FilterDao<Province, ProvinceFilter> provinceDao) {
        this.provinceDao = provinceDao;
    }

    @Resource(name="entityDao")
    public void setEntityDao(FilterDao<Entity, EntityFilter> entityDao) {
        this.entityDao = entityDao;
    }
    
    @Resource(name="keyTypeDao")
    public void setKeyTypeDao(BasicDao<KeyType> keyTypeDao) {
        this.keyTypeDao = keyTypeDao;
    }
    
    @Resource(name="serviceDao")
    public void setServiceDao(BasicDao<Service> serviceDao) {
        this.serviceDao = serviceDao;
    }
    
    @Resource(name="userRoleDao")
    public void setUserRoleDao(BasicDao<UserRole> userRoleDao) {
        this.userRoleDao = userRoleDao;
    }
    
    private final Log logger = LogFactory.getLog(CategoryMgrImpl.class);

}
