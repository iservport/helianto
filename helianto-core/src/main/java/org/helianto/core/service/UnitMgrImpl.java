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
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Unit;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UnitFilterAdapter;
import org.helianto.core.form.UnitForm;
import org.helianto.core.repository.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation to unit service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("unitMgr")
public class UnitMgrImpl 
	implements UnitMgr 
{
	
	@Transactional(readOnly=true)
	public Unit findUnit(Entity entity, String unitCode) {
    	return unitRepository.findByEntityAndUnitCode(entity, unitCode);
	}

	@Transactional(readOnly=true)
	public List<Unit> findUnits(UnitForm form) {
		Filter unitFilter = new UnitFilterAdapter(form);;
    	List<Unit> unitList = (List<Unit>) unitRepository.find(unitFilter);
    	logger.debug("Found unit list of size {}", unitList.size());
    	return unitList;
	}

	@Transactional
	public Unit storeUnit(Unit unit) {
		unitRepository.saveAndFlush(unit);
    	logger.debug("Stored unit  {}", unit);
    	return unit;
	}

	@Transactional
	public void removeUnit(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public Unit installUnit(Entity entity, String unitCode, String unitSymbol, String unitName) {
		Unit unit = unitRepository.findByEntityAndUnitCode(entity, unitCode);
    	if (unit!=null) {
        	logger.debug("Found existing unit  {}", unit);
    		return unit;
    	}
    	unit = unitRepository.saveAndFlush(new Unit(entity, unitCode, unitSymbol, unitName));
    	logger.debug("Installed unit  {}", unit);
		return unit;
	}

    //- collabs

    private UnitRepository unitRepository;
    
	@Resource
    public void setUnitRepository(UnitRepository unitRepository) {
		this.unitRepository = unitRepository;
	}

    private final Logger logger = LoggerFactory.getLogger(UnitMgrImpl.class);


}
