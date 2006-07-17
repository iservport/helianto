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

package org.helianto.core.junit;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.creation.EntityCreator;
import org.helianto.core.creation.HomeCreatorImpl;
import org.helianto.core.dao.EntityDao;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AbstractEntityTest extends AbstractIntegrationTest {

    public static Entity createAndPersistEntity(EntityDao entityDao) {
        Home home = HomeCreatorImpl.homeFactory(generateKey(20));
        Entity entity = EntityCreator.entityFactory(home, generateKey(20));
        if (entityDao!=null) {
            entityDao.persistEntity(entity);
        }
        return entity;
    }

    public static List<Entity> createAndPersistEntityList(HibernateTemplate hibernateTemplate, int i) {
        List<Entity> entityList = createEntityList(i);
        hibernateTemplate.saveOrUpdateAll(entityList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return entityList;
    }
    
    public static List<Entity> createEntityList(int size) {
        Home home = HomeCreatorImpl.homeFactory(generateKey(20));
        List<Entity> entities = new ArrayList<Entity>();
        for (int i = 0; i<size; i++) {
            Entity e = EntityCreator.entityFactory(home, "ENTITY"+i);
            entities.add(e);
        }
        return entities;
    }

}
