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

package org.helianto.core.orm;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.core.dao.UnitDao;
import org.helianto.core.hibernate.GenericDaoImpl;


/**
 * Default implementation of <code>Unit</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UnitDaoImpl extends GenericDaoImpl implements UnitDao {
     
    public void persistUnit(Unit unit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+unit);
        }
        persist(unit);
    }
    
    public Unit mergeUnit(Unit unit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+unit);
        }
        return (Unit) merge(unit);
    }
    
    public void removeUnit(Unit unit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+unit);
        }
        remove(unit);
    }
    
    public Unit findUnitByNaturalId(Entity entity, String unitCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique unit with entity='"+entity+"' and unitCode='"+unitCode+"' ");
        }
        return (Unit) findUnique(Unit.getUnitNaturalIdQueryString(), entity, unitCode);
    }
    
	@SuppressWarnings("unchecked")
	public List<Unit> findUnits(String criteria) {
        if (criteria!=null && !criteria.equals("")) {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding unit list with criteria='"+criteria+"' ");
            }
            return (ArrayList<Unit>) find(Unit.getUnitQueryStringBuilder().append("where ").append(criteria));
        }
        else {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding full entity list");
            }
            return (ArrayList<Unit>) find(Unit.getUnitQueryStringBuilder());
        }
	}
    
    
}
