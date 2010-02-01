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

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.repository.AbstractBasicDao;

/**
 * Base implementation using Jpa for <code>BasicDao</code>.
 * 
 * @author Mauricio Fernandes de Castro.
 */
public abstract class AbstractJpaBasicDao<T> extends AbstractBasicDao<T> {
	
	// collabs
    
    protected EntityManager em;
    
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
//    @PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

    private static final Logger logger = LoggerFactory.getLogger(AbstractJpaBasicDao.class);

}
