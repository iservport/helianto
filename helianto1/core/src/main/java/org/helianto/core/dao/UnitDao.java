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

package org.helianto.core.dao;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Unit;


/**
 * <code>Unit</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UnitDao extends CommonOrmDao {
     
    /**
     * Persist <code>Unit</code>.
     */
    public void persistUnit(Unit unit);
    
    /**
     * Merge <code>Unit</code>.
     */
    public Unit mergeUnit(Unit unit);
    
    /**
     * Remove <code>Unit</code>.
     */
    public void removeUnit(Unit unit);
    
    /**
     * Find <code>Unit</code> by <code>Entity</code> and unitCode.
     */
    public Unit findUnitByNaturalId(Entity entity, String unitCode);
    
    /**
     * Find <code>Unit</code> by string criteria.
     */
    public List<Unit> findUnits(String criteria);
    
    
    
}
