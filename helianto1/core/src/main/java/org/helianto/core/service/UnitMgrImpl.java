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
import org.helianto.core.Unit;
import org.helianto.core.UnitFilter;
import org.helianto.core.dao.UnitDao;
import org.helianto.core.filter.SelectionStrategy;


/**
 * Default implementation to unit interface.
 * 
 * @author Maurício Fernandes de Castro
 */
@SuppressWarnings("restriction")
public class UnitMgrImpl implements UnitMgr {
    
    private UnitDao unitDao;
    private SelectionStrategy<UnitFilter> unitSelectionStrategy;
    
	public List<Unit> findUnits(UnitFilter unitFilter) {
    	String criteria = unitSelectionStrategy.createCriteriaAsString(unitFilter, "unit");
    	List<Unit> unitList = unitDao.findUnits(criteria);
    	if (logger.isDebugEnabled() && unitList!=null) {
    		logger.debug("Found unit list of size "+unitList.size());
    	}
    	return unitList;
	}

	public Unit storeUnit(Unit unit) {
		Unit managedUnit = unitDao.mergeUnit(unit);
    	if (logger.isDebugEnabled()) {
    		logger.debug("Stored unit  "+managedUnit);
    	}
    	return managedUnit;
	}

	public void removeUnit(Unit unit) {
		// TODO Auto-generated method stub
		
	}


    //- collabs
    @Resource
    public void setUnitDao(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

    @Resource
	public void setUnitSelectionStrategy(SelectionStrategy<UnitFilter> unitSelectionStrategy) {
		this.unitSelectionStrategy = unitSelectionStrategy;
	}

    private final Log logger = LogFactory.getLog(UnitMgrImpl.class);


}
