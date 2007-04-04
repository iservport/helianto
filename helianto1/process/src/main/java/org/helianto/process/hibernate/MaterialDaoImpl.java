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

package org.helianto.process.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.MaterialType;
import org.helianto.process.dao.MaterialDao;

public class MaterialDaoImpl extends GenericDaoImpl implements MaterialDao {

    public void persistUnit(Unit unit) {
        merge(unit);
    }

    public void persistMaterialType(MaterialType materialType) {
        merge(materialType);
    }

    public List<Unit> findUnitByEntity(Entity entity) {
        return (ArrayList<Unit>) find(UNIT_QRY, entity);
    }
    
    public Unit findUnitByNaturalId(Entity entity, String unitCode) {
        return (Unit) findUnique(UNIT_QRY+UNIT_UNIQUE_FILTER, entity, unitCode);
    }
    
    static String UNIT_QRY = "from Unit unit " +
            "where unit.entity = ? ";
    
    static String UNIT_UNIQUE_FILTER = "and unit.unitCode = ? ";

    public List<MaterialType> findMaterialTypeByEntity(Entity entity) {
        return (ArrayList<MaterialType>) find(MATERIALTYPE_QRY, entity);
    }

    public MaterialType findMaterialTypeByEntityAnd(Entity entity, long internalNumber) {
        return (MaterialType) findUnique(MATERIALTYPE_QRY+MATERIALTYPE_UNIQUE_FILTER, entity, internalNumber);
    }

    static String MATERIALTYPE_QRY = "from MaterialType materialType " +
            "where materialType.entity = ? ";

    static String MATERIALTYPE_UNIQUE_FILTER = "and materialType.internalNumber = ? ";

    public void removeUnit(Unit unit) {
        remove(unit);
    }

    public void removeMaterialType(MaterialType materialType) {
        remove(materialType);
    }

}
