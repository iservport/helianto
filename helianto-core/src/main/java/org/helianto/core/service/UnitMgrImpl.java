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
import org.helianto.core.repository.FilterDao;
import org.springframework.transaction.annotation.Transactional;



/**
 * Default implementation to unit service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("unitMgr")
@Transactional
public class UnitMgrImpl implements UnitMgr {
    
	public List<Unit> findUnits(UnitFilter unitFilter) {
    	List<Unit> unitList = (List<Unit>) unitDao.find(unitFilter);
    	if (logger.isDebugEnabled() && unitList!=null) {
    		logger.debug("Found unit list of size "+unitList.size());
    	}
    	return unitList;
	}

	public Unit storeUnit(Unit unit) {
		Unit managedUnit = unitDao.merge(unit);
    	if (logger.isDebugEnabled()) {
    		logger.debug("Stored unit  "+managedUnit);
    	}
    	return managedUnit;
	}

	public void removeUnit(Unit unit) {
		// TODO Auto-generated method stub
		
	}


    //- collabs

    private FilterDao<Unit, UnitFilter> unitDao;
    
	@Resource(name="unitDao")
    public void setUnitDao(FilterDao<Unit, UnitFilter> unitDao) {
        this.unitDao = unitDao;
    }

    private final Log logger = LogFactory.getLog(UnitMgrImpl.class);


}
