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
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.dao.ProvinceDao;
import org.helianto.core.filter.SelectionStrategy;

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

	public List<Province> findProvinces(ProvinceFilter provinceFilter) {
    	String criteria = provinceSelectionStrategy.createCriteriaAsString(provinceFilter, "province");
    	List<Province> provinceList = provinceDao.findProvinces(criteria);
    	if (logger.isDebugEnabled() && provinceList!=null) {
    		logger.debug("Found province list of size "+provinceList.size());
    	}
    	return provinceList;
	}

	public Province prepareProvince(Province province) {
		Province managedProvince = provinceDao.mergeProvince(province);
		managedProvince.getOperator();
		provinceDao.evictProvince(province);
		return managedProvince;
	}

	public Province prepareNewProvince(Entity entity) {
		Entity managedEntity = entityDao.mergeEntity(entity);
		return Province.provinceFactory(managedEntity.getOperator(), "", "");
	}

	public Province storeProvince(Province province) {
		return provinceDao.mergeProvince(province);
	}

	// collabs
	
	private OperatorDao operatorDao;

	private ProvinceDao provinceDao;
	private SelectionStrategy<ProvinceFilter> provinceSelectionStrategy;
	
	private EntityDao entityDao;
	
	@Resource
	public void setOperatorDao(OperatorDao operatorDao) {
		this.operatorDao = operatorDao;
	}

	@Resource
	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}
	@Resource
	public void setEntityDao(EntityDao entityDao) {
		this.entityDao = entityDao;
	}
	@Resource
	public void setProvinceSelectionStrategy(SelectionStrategy<ProvinceFilter> provinceSelectionStrategy) {
		this.provinceSelectionStrategy = provinceSelectionStrategy;
	}

    private final Log logger = LogFactory.getLog(CategoryMgrImpl.class);

}
