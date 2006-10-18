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

package org.helianto.process.service;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.MaterialType;
import org.helianto.process.Unit;

public interface MaterialMgr {

    /**
     * Unit factory method.
     */
    public Unit createUnit(Entity entity);
    
    /**
     * Persist an <code>Unit</code>.
     */
    public void persistUnit(Unit unit);
    
    /**
     * Find <code>Unit</code> by <code>Entity</code> and code.
     */
    public Unit findUnitByNaturalId(Entity entity, String unitCode);
    
    /**
     * Find <code>Unit</code> list for this <code>Entity</code>
     */
    public List<Unit> findUnitByEntity(Entity entity);
    
    /**
     * Material factory method.
     */
    public MaterialType createMaterialType(Unit unit);
    
}
