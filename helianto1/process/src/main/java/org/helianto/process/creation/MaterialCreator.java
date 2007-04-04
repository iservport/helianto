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

package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.process.MaterialType;

public class MaterialCreator {

    public static Unit unitFactory(Entity entity, String unitCode) {
        Unit unit = new Unit();
        unit.setEntity(entity);
        unit.setUnitCode(unitCode);
        return unit;
    }

    public static MaterialType materialFactory(Unit unit, String materialName) {
        MaterialType MaterialType = new MaterialType();
        MaterialType.setEntity(unit.getEntity());
        MaterialType.setMaterialUnit(unit);
        MaterialType.setMaterialName(materialName);
        return MaterialType;
    }

    public static MaterialType materialFactory(MaterialType parent, Unit unit, String materialName) {
        MaterialType MaterialType = materialFactory(unit, materialName);
        MaterialType.setParent(parent);
        return MaterialType;
    }

}
