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

package org.helianto.process.dao;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.MaterialType;
import org.helianto.process.Unit;

public interface MaterialDao {

    public void persistUnit(Unit unit);

    public void persistMaterialType(MaterialType materialType);

    public List<Unit> findUnitByEntity(Entity entity);
    
    public Unit findUnitByEntityAndCode(Entity entity, String unitCode);

    public List<MaterialType> findMaterialTypeByEntity(Entity entity);
    
    public MaterialType findMaterialTypeByEntityAnd(Entity entity, long internalNumber);
    
    public void removeUnit(Unit unit);

    public void removeMaterialType(MaterialType materialType);

}
