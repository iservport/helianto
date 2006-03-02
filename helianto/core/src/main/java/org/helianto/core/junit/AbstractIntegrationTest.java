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

import java.util.Date;

import org.helianto.core.Entity;
import org.helianto.core.Supervisor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 * A base class for service layer integration tests.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public abstract class AbstractIntegrationTest extends
    AbstractTransactionalSpringContextTests {

    protected HibernateTemplate hibernateTemplate;
    
    protected String entityTest = generateKey(20);

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
    /**
     * Generate a not repeatable key.
     */
    public String generateKey() {
        return String.valueOf(new Date().getTime());
    }

    /**
     * Generate a not repeatable key of a given size.
     */
    public String generateKey(int size) {
        String localKey = generateKey();
        logger.info("original key "+localKey);
        while (localKey.length()!=size) {
            if (localKey.length() > size) {
                localKey = localKey.substring(localKey.length()-size, localKey.length());
            } else if (localKey.length() < size) {
                localKey = localKey.concat(localKey);
            }
        }
        return localKey;
    }

    /**
     * Generate a new entity by default. Subclasses should
     * override to provide a perssited entity.
     * @return
     */
    protected Entity getTestEntity() {
        Supervisor supervisor = new Supervisor();
        supervisor.setSupervisorName(generateKey(20));
        Entity entity = new Entity();
        entity.setSupervisor(supervisor);
        entity.setAlias(entityTest);
        return entity;
    }
    
}
