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

package org.helianto.process.junit;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.core.test.AbstractHibernateIntegrationTest;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.creation.MaterialCreator;
import org.helianto.process.dao.MaterialDao;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AbstractMaterialTest extends AbstractHibernateIntegrationTest {

    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/core.xml",
                "deploy/process.xml"
                };
    }

    public static Unit createAndPersistUnit(MaterialDao materialDao) {
        Entity entity = EntityTestSupport.createEntity();
        Unit unit = MaterialCreator.unitFactory(entity, generateKey(20));
        if (materialDao!=null) {
            materialDao.persistUnit(unit);
        }
        return unit;
    }

    public static List<Unit> createAndPersistUnitList(HibernateTemplate hibernateTemplate, int i, int e) {
        List<Unit> unitList = createUnitList(i, e);
        for (Unit u: unitList) {
            hibernateTemplate.merge(u);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return unitList;
    }

    public static List<Unit> createUnitList(int size, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        List<Unit> unitList = new ArrayList<Unit>();
        for (Entity e: entityList) {
            for (int i=0;i<size;i++) {
                unitList.add(MaterialCreator.unitFactory(e, generateKey(20,i)));
            }
        }
        return unitList;
    }

}
