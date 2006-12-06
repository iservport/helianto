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

import org.helianto.core.Entity;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.test.EntityTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

public class EntityDaoImplTests extends EntityTestSupport {

    // class under test
    // AuthorizationDao is a supperclass - 
    // use it to avoid autowire by type conflict 
    private AuthorizationDao entityDao;
    
    public void testPersistEntity() {
        //write
        Entity entity = createAndPersistEntity(entityDao);
        hibernateTemplate.flush();
        //read
        assertEquals(entity,  entityDao.findEntityByNaturalId(entity.getOperator(), entity.getAlias()));
    }
    
    private List<Entity> writeEntityList() {
        int i = 10;
        List<Entity> entityList = createAndPersistEntityList(hibernateTemplate, i);
        assertEquals(i, entityList.size());
        return entityList;
    }
    
    public void testFindEntity() {
        // write
        List<Entity> entityList = writeEntityList();
        // read
        Entity entity = entityList.get((int) Math.random()*entityList.size());
        assertEquals(entity,  entityDao.findEntityByNaturalId(entity.getOperator(), entity.getAlias()));
        // TODO add some more finders
    }

    public void testEntityErrors() {
        try {
             entityDao.persistEntity(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             entityDao.removeEntity(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        // TODO add some more methods
    }

    public void testEntityDuplicate() {
        // write
        Entity entity = createAndPersistEntity( entityDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(entity); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveEntity() {
        // write
        List<Entity> entityList = writeEntityList();
        // remove
        Entity entity = entityList.get((int) Math.random()*entityList.size());
        entityDao.removeEntity(entity);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Entity> all = (ArrayList<Entity>) hibernateTemplate.find("from Entity");
        assertFalse(all.contains(entity));
    }

    //- collabs

    public void setEntityDao(AuthorizationDao entityDao) {
        this.entityDao = entityDao;
    }

}
