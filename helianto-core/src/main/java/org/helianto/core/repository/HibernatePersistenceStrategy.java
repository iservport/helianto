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

import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Query;

/**
 * Hibernate implementation for <code>PersistenceStrategy</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("unchecked")
public class HibernatePersistenceStrategy implements PersistenceStrategy {
	
	/**
	 * Default constructor.
	 */
	public HibernatePersistenceStrategy() {		
	}

	/**
	 * Session factory constructor.
	 */
	public HibernatePersistenceStrategy(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Collection<Object> find(String query, Object... values) {
        Query result = this.sessionFactory.getCurrentSession().createQuery(query);
        int i = 0;
        for (Object value: values) {
            result.setParameter(i++, value);
        }
        Collection<Object> resultList = result.list();
        logger.debug("Found {} item(s)", resultList.size());
        return resultList;
	}

	public Object merge(Object object) {
        logger.debug("Merging {}", object);
		return this.sessionFactory.getCurrentSession().merge(object);
	}

	public void persist(Object managedObject) {
        logger.debug("Persisting {}", managedObject);
        this.sessionFactory.getCurrentSession().persist(managedObject);
	}
	
	public void saveOrUpdate(Object managedObject) {
        logger.debug("Saving (or updating) {}", managedObject);
        this.sessionFactory.getCurrentSession().saveOrUpdate(managedObject);
	}

	public void remove(Object object) {
        logger.debug("Removing {}", object);
		this.sessionFactory.getCurrentSession().delete(object);
	}

	public void evict(Object object) {
        logger.debug("Evicting {}", object);
		this.sessionFactory.getCurrentSession().evict(object);
	}
	
	public void flush() {
        logger.debug("Flushing session");
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void clear() {
        logger.debug("Clearing session");
		this.sessionFactory.getCurrentSession().clear();
	}

	// collabs
    
    private org.hibernate.SessionFactory sessionFactory;
    
    /**
     * Spring will inject a managed Hibernate Session into this field.
     */
    @Resource(name="sessionFactory")
	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    private static final Logger logger = LoggerFactory.getLogger(HibernatePersistenceStrategy.class);

}
