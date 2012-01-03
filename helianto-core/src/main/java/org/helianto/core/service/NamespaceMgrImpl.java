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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Service;
import org.helianto.core.UserRole;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.KeyTypeFormFilterAdapter;
import org.helianto.core.filter.ProvinceFormFilterAdapter;
import org.helianto.core.filter.form.KeyTypeForm;
import org.helianto.core.filter.form.ProvinceForm;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>NamespaceMgr</code> default implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("namespaceMgr")
public class NamespaceMgrImpl implements NamespaceMgr {

	public List<Operator> findOperators(Filter operatorFilter) {
		List<Operator> operatorList = (List<Operator>) operatorDao.find(operatorFilter);
		if (operatorList!=null && operatorList.size()>0) {
			logger.debug("Found {} namespace operator(s)", operatorList.size());
		}
    	return operatorList;
	}
	
	public Operator storeOperator(Operator operator) {
		operatorDao.saveOrUpdate(operator);
		return operator;
	}

	public List<Province> findProvinces(ProvinceForm form) {
		Filter filter = new ProvinceFormFilterAdapter(form);
    	List<Province> provinceList = (List<Province>) provinceDao.find(filter);
    	if (provinceList!=null && provinceList.size()>0) {
        	logger.debug("Found province list of size {}", provinceList.size());
    	}
    	return provinceList;
	}

	/**
	 * @deprecated
	 */
	public List<Province> findProvinces(Filter filter) {
    	List<Province> provinceList = (List<Province>) provinceDao.find(filter);
    	if (provinceList!=null && provinceList.size()>0) {
        	logger.debug("Found province list of size {}", provinceList.size());
    	}
    	return provinceList;
	}

	public Province storeProvince(Province province) {
		Province managedProvince =  provinceDao.merge(province);
		return managedProvince;
	}
	
	public List<Entity> findEntities(Filter filter) {
		List<Entity> entityList = (List<Entity>) entityDao.find(filter);
		if (entityList!=null && entityList.size()>0) {
			logger.debug("Found {} entity(ies).", entityList.size());
		}
		return entityList;
	}
	
	public Entity storeEntity(Entity entity) {
		entityDao.saveOrUpdate(entity);
		return entity;
	}

	public List<KeyType> findKeyTypes(KeyTypeForm form) {
		Filter filter = new KeyTypeFormFilterAdapter(form);
		List<KeyType> keyTypeList = (List<KeyType>) keyTypeDao.find(filter);
    	if (keyTypeList!=null && keyTypeList.size()>0) {
        	logger.debug("Found key type list of size {}", keyTypeList.size());
    	}
    	return keyTypeList;
	}

	/**
	 * @deprecated
	 */
	public List<KeyType> findKeyTypes(Filter filter) {
		List<KeyType> keyTypeList = (List<KeyType>) keyTypeDao.find(filter);
    	if (keyTypeList!=null && keyTypeList.size()>0) {
        	logger.debug("Found key type list of size {}", keyTypeList.size());
    	}
    	return keyTypeList;
	}

	public KeyType storeKeyType(KeyType keyType) {
		keyTypeDao.saveOrUpdate(keyType);
		return keyType;
	}

	public List<Service> findServices(Filter filter) {
		List<Service> serviceList = (List<Service>) serviceDao.find(filter);
    	if (serviceList!=null && serviceList.size()>0) {
    		logger.debug("Loaded user list of size {}", serviceList.size());
    	}
    	return serviceList;
	}

	public Service storeService(Service service) {
		serviceDao.saveOrUpdate(service);
		return service;
	}

	public Map<String, String> loadServiceNameMap(Operator operator, UserRole userRole) {
		Operator managedOperator = operatorDao.merge(operator);
		Map<String, String> serviceNameMap = new HashMap<String, String>();
		Collection<Service> services = managedOperator.getServiceMap().values();
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
    	logger.debug("Loaded service map {}", serviceNameMap);
		return serviceNameMap;
	}

	// collabs
	
	private FilterDao<Operator> operatorDao;
	private FilterDao<Province> provinceDao;
	private FilterDao<Entity> entityDao;
	private FilterDao<KeyType> keyTypeDao;
	private FilterDao<Service> serviceDao;
	
	@Resource(name="operatorDao")
	public void setOperatorDao(FilterDao<Operator> operatorDao) {
		this.operatorDao = operatorDao;
	}

    @Resource(name="provinceDao")
    public void setProvinceDao(FilterDao<Province> provinceDao) {
        this.provinceDao = provinceDao;
    }

    @Resource(name="entityDao")
    public void setEntityDao(FilterDao<Entity> entityDao) {
        this.entityDao = entityDao;
    }
    
    @Resource(name="keyTypeDao")
    public void setKeyTypeDao(FilterDao<KeyType> keyTypeDao) {
        this.keyTypeDao = keyTypeDao;
    }
    
    @Resource(name="serviceDao")
    public void setServiceDao(FilterDao<Service> serviceDao) {
        this.serviceDao = serviceDao;
    }
    
    private final Logger logger = LoggerFactory.getLogger(CategoryMgrImpl.class);

}
