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

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.Operator;
import org.helianto.core.OperatorFilter;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.dao.FilterDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <code>NamespaceMgr</code> default implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("namespaceMgr")
public class NamespaceMgrImpl implements NamespaceMgr {

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
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

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public Operator findOperatorByName(String operatorName) {
		return operatorDao.findUnique(operatorName);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Operator storeOperator(Operator operator) {
		return operatorDao.merge(operator);
	}

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<Province> findProvinces(ProvinceFilter filter) {
    	List<Province> provinceList = (List<Province>) provinceDao.find(filter);
    	if (logger.isDebugEnabled() && provinceList!=null) {
    		logger.debug("Found province list of size "+provinceList.size());
    	}
    	return provinceList;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Province prepareProvince(Province province) {
		Province managedProvince = provinceDao.merge(province);
		managedProvince.getOperator();
		provinceDao.evict(province);
		return managedProvince;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Province prepareNewProvince(Entity entity) {
		Entity managedEntity = entityDao.merge(entity);
		return Province.provinceFactory(managedEntity.getOperator(), "", "");
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Province storeProvince(Province province) {
		return provinceDao.merge(province);
	}
	
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<Entity> findEntities(EntityFilter filter) {
		List<Entity> entityList = (List<Entity>) entityDao.find(filter);
		if (logger.isDebugEnabled()) {
			logger.debug("Found "+entityList.size()+" entity(ies).");
		}
		return entityList;
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public Entity storeEntity(Entity entity) {
		Operator managedOperator = operatorDao.merge(entity.getOperator());
		entity.setOperator(managedOperator);
		return entityDao.merge(entity);
	}

	// collabs
	
	private FilterDao<Operator, OperatorFilter> operatorDao;
	private FilterDao<Province, ProvinceFilter> provinceDao;
	private FilterDao<Entity, EntityFilter> entityDao;
	
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
    
    private final Log logger = LogFactory.getLog(CategoryMgrImpl.class);

}
