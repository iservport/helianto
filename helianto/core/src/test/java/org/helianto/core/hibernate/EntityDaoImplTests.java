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

package org.helianto.core.hibernate;

import java.util.List;

import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.creation.EntityCreator;
import org.helianto.core.creation.HomeCreatorImpl;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.junit.AbstractEntityTest;

public class EntityDaoImplTests extends AbstractEntityTest {
    
    private EntityDao entityDao;
    private Home home;
    
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        home = HomeCreatorImpl.homeFactory("HOME");
    }

    public void testEntityLifeCycle() {
        
        Entity entity = EntityCreator.entityFactory(home, "TEST");
        entityDao.persistEntity(entity);
        
        hibernateTemplate.flush();
        
        Entity en = entityDao.findEntityByHomeAndAlias("HOME", "TEST");
        assertEquals(entity, en);
        
        Entity duplicatedEntity = EntityCreator.entityFactory(home, "TEST");
        try {
            entityDao.persistEntity(duplicatedEntity);
            fail();
        } catch (Exception e) {
            //ok
        }

    }
    
    public void testDefaultEntityLifeCycle() {
        
        Entity entity = EntityCreator.entityFactory(home, "TEST");
        DefaultEntity defaultEntity = EntityCreator.defaultEntityFactory(entity);
        entityDao.persistDefaultEntity(defaultEntity);
        
        hibernateTemplate.flush();
        
        Entity de = entityDao.findDefaultEntity();
        assertEquals(entity, de);
        
        DefaultEntity duplicatedDefaultEntity = EntityCreator.defaultEntityFactory(entity);
        try {
            entityDao.persistDefaultEntity(duplicatedDefaultEntity);
            fail();
        } catch (Exception e) {
            //ok
        }

    }

    public void testDefaultEntityPriority() {
        
        Entity entity = EntityCreator.entityFactory(home, "TEST");
        DefaultEntity defaultEntity = EntityCreator.defaultEntityFactory(entity);
        entityDao.persistDefaultEntity(defaultEntity);
        
        Entity de0 = entityDao.findDefaultEntity(0);
        assertEquals(entity, de0);
        
        hibernateTemplate.flush();
        
        Entity anotherEntity = EntityCreator.entityFactory(home, "TEST2");
        DefaultEntity anotherDefaultEntity = EntityCreator.defaultEntityFactory(anotherEntity, 1);
        entityDao.persistDefaultEntity(anotherDefaultEntity);

        Entity de1 = entityDao.findDefaultEntity(1);
        assertEquals(entity, de1);
        
    }
    
    // utility methods
    
    /**
     * Utility method to create entities.
     * @param size
     * @deprecated
     */
    public static List<Entity> createEntities(int size) {
        return createEntityList(size);
    }
    
}
