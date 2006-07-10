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

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.creation.EntityCreator;
import org.helianto.core.creation.HomeCreatorImpl;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class EntityDaoImplTests extends AbstractIntegrationTest {
    
    private EntityDao entityDao;
    private EntityCreator factory;
    private Home home;
    
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        factory = new EntityCreator();
        home = HomeCreatorImpl.homeFactory("HOME");
    }

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

    public void testEntityLifeCycle() {
        
        Entity entity = new EntityCreator().entityFactory(home, "TEST");
        entityDao.persistEntity(entity);
        
        hibernateTemplate.flush();
        
        Entity en = entityDao.findEntityByHomeAndAlias("HOME", "TEST");
        assertEquals(entity, en);
        
        Entity duplicatedEntity = factory.entityFactory(home, "TEST");
        try {
            entityDao.persistEntity(duplicatedEntity);
            fail();
        } catch (Exception e) {
            //ok
        }

    }
    
    public void testDefaultEntityLifeCycle() {
        
        Entity entity = factory.entityFactory(home, "TEST");
        DefaultEntity defaultEntity = factory.defaultEntityFactory(entity);
        entityDao.persistDefaultEntity(defaultEntity);
        
        hibernateTemplate.flush();
        
        Entity de = entityDao.findDefaultEntity();
        assertEquals(entity, de);
        
        DefaultEntity duplicatedDefaultEntity = factory.defaultEntityFactory(entity);
        try {
            entityDao.persistDefaultEntity(duplicatedDefaultEntity);
            fail();
        } catch (Exception e) {
            //ok
        }

    }

    public void testDefaultEntityPriority() {
        
        Entity entity = factory.entityFactory(home, "TEST");
        DefaultEntity defaultEntity = factory.defaultEntityFactory(entity);
        entityDao.persistDefaultEntity(defaultEntity);
        
        Entity de0 = entityDao.findDefaultEntity(0);
        assertEquals(entity, de0);
        
        hibernateTemplate.flush();
        
        Entity anotherEntity = factory.entityFactory(home, "TEST2");
        DefaultEntity anotherDefaultEntity = factory.defaultEntityFactory(anotherEntity, 1);
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
