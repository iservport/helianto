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

import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.hibernate.EntityDao;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.helianto.core.service.CoreFactoryImpl;

public class EntityDaoImplTests extends AbstractIntegrationTest {
    
    private EntityDao entityDao;
    private CoreFactoryImpl factory;
    
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        factory = new CoreFactoryImpl();
    }

    public void testEntityLifeCycle() {
        
        Home home = factory.homeFactory("HOME");
        Entity entity = factory.entityFactory(home, "TEST");
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

}
