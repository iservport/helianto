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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.creation.OperatorCreator;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.type.OperationMode;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class EntityTestSupport extends AbstractHibernateIntegrationTest {

    private static int testKey = 1;

    /**
     * Create test <code>Entity</code>.
     */
    public static Entity createEntity(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = OperatorTestSupport.createOperator();
        }
        Entity entity = OperatorCreator.entityFactory(operator, generateKey(20, testKey++));
        logger.info("+++ "+entity);
        return entity;
    }

    public static Entity createAndPersistEntity(EntityDao entityDao) {
        Entity entity = createEntity();
        if (entityDao!=null) {
            entityDao.persistEntity(entity);
        }
        return entity;
    }

    public static List<Entity> createEntityList(int size) {
        Operator operator = OperatorCreator.operatorFactory(generateKey(20, testKey++), OperationMode.LOCAL, null);
        List<Entity> entities = new ArrayList<Entity>();
        for (int i = 0; i<size; i++) {
            entities.add(createEntity(operator));
        }
        return entities;
    }

    public static List<Entity> createAndPersistEntityList(HibernateTemplate hibernateTemplate, int i) {
        List<Entity> entityList = createEntityList(i);
        hibernateTemplate.saveOrUpdateAll(entityList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return entityList;
    }
    
}
