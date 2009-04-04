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
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.dao.OperatorDao;

/**
 * <code>OperatorMgr</code> default implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OperatorMgrImpl implements OperatorMgr {

	public List<Operator> findOperator() {
    	return operatorDao.findOperatorAll();
	}

	public Operator findOperatorByName(String operatorName) {
		return operatorDao.findOperatorByNaturalId(operatorName);
	}

	public Operator storeOperator(Operator operator) {
		return operatorDao.mergeOperator(operator);
	}

	public List<Province> findProvinces(ProvinceFilter filter) {
    	List<Province> provinceList = (List<Province>) provinceDao.find(filter);
    	if (logger.isDebugEnabled() && provinceList!=null) {
    		logger.debug("Found province list of size "+provinceList.size());
    	}
    	return provinceList;
	}

	public Province prepareProvince(Province province) {
		Province managedProvince = provinceDao.merge(province);
		managedProvince.getOperator();
		provinceDao.evict(province);
		return managedProvince;
	}

	public Province prepareNewProvince(Entity entity) {
		Entity managedEntity = entityDao.merge(entity);
		return Province.provinceFactory(managedEntity.getOperator(), "", "");
	}

	public Province storeProvince(Province province) {
		return provinceDao.merge(province);
	}

	// collabs
	
	private OperatorDao operatorDao;
	private FilterDao<Province, ProvinceFilter> provinceDao;
	private FilterDao<Entity, EntityFilter> entityDao;
	
	@Resource
	public void setOperatorDao(OperatorDao operatorDao) {
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
