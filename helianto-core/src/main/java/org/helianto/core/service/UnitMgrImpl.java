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

import org.helianto.core.UnitMgr;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Unit;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation to unit service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("unitMgr")
public class UnitMgrImpl implements UnitMgr {
	
	public List<Unit> findUnits(Filter unitFilter) {
    	List<Unit> unitList = (List<Unit>) unitDao.find(unitFilter);
    	logger.debug("Found unit list of size {}", unitList.size());
    	return unitList;
	}

	public Unit storeUnit(Unit unit) {
		unitDao.saveOrUpdate(unit);
    	logger.debug("Stored unit  {}", unit);
    	unitDao.flush();
    	return unit;
	}

	public void removeUnit(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	public Unit installUnit(Category category, String unitCode, String unitName) {
		Unit unit = unitDao.findUnique(category.getEntity(), unitCode);
    	if (unit!=null) {
        	logger.debug("Found existing unit  {}", unit);
    		return unit;
    	}
    	unit = unitDao.merge(new Unit(category, unitCode, unitName));
    	logger.debug("Installed unit  {}", unit);
    	unitDao.flush();
		return unit;
	}

    //- collabs

    private FilterDao<Unit> unitDao;
    
	@Resource(name="unitDao")
    public void setUnitDao(FilterDao<Unit> unitDao) {
        this.unitDao = unitDao;
    }

    private final Logger logger = LoggerFactory.getLogger(UnitMgrImpl.class);


}
