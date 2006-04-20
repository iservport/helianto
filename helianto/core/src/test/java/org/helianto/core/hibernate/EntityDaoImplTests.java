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

import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.creation.EntityCreatorImpl;
import org.helianto.core.creation.HomeCreatorImpl;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.junit.AbstractIntegrationTest;

public class EntityDaoImplTests extends AbstractIntegrationTest {
    
    private EntityDao entityDao;
    private EntityCreatorImpl factory;
    private Home home;
    
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        factory = new EntityCreatorImpl();
        home = new HomeCreatorImpl().homeFactory("HOME");
    }

    public void testEntityLifeCycle() {
        
        Entity entity = new EntityCreatorImpl().entityFactory(home, "TEST");
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

}
