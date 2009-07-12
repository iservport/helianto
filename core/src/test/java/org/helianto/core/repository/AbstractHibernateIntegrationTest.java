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

package org.helianto.core.repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.helianto.core.test.AbstractIntegrationTest;


/**
 * A base class for service layer integration tests requiring Hibernate.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AbstractHibernateIntegrationTest extends AbstractIntegrationTest {

    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "META-INF/spring/dataSource.xml",
                "META-INF/spring/data.xml",
                "META-INF/spring/support.xml",
                "META-INF/spring/core-context.xml",
                };
    }
    
    protected EntityManager em;
    
    @Resource
    public void setEntityManager(EntityManager em) {
    	this.em = em;
    }
    
}
