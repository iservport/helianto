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


package org.helianto.core.orm;

import org.helianto.core.Category;
import org.helianto.core.Entity;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.test.EntityTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultEntityDaoIntegrationTests extends AbstractHibernateIntegrationTest {
	
	public DefaultEntityDaoIntegrationTests() {
		setAutowireMode(AUTOWIRE_BY_NAME);
	}
	
	public void testFindUnique() {
		Entity entity = EntityTestSupport.createEntity();
		entityDao.persist(entity);
		assertEquals(entity, entityDao.findUnique(entity.getOperator(), entity.getAlias()));
	}

    //- collabs

    private BasicDao<Entity> entityDao;
    
    public void setEntityDao(BasicDao<Entity> entityDao) {
        this.entityDao = entityDao;
    }
    
}
