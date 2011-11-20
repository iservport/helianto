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

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.ejb.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jpa implementation for <code>PersistenceStrategy</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JpaPersistenceStrategy implements PersistenceStrategy {
	
	public Collection find(int firstRow, String query, Object... values) {
		return find(firstRow, 10, query, values);
	}
	
	public Collection find(int firstRow, int maxRows, String query, Object... values) {
        Query result = this.em.createQuery(query);
        result.setFirstResult(firstRow);
        result.setMaxResults(maxRows);
		return find(result, values);
	}

	public Collection<Object> find(String query, Object... values) {
        return find(this.em.createQuery(query), values);
	}
	
	/**
	 * Convert values to ordinal parameters (1-based!).
	 * 
	 * @param result
	 * @param values
	 */
	protected Collection<Object> find(Query result, Object... values) {
        int i = 1;
        for (Object value: values) {
            result.setParameter(i++, value);
        }
        Collection<Object> resultList = result.getResultList();
        logger.debug("Found {} item(s)", resultList.size());
        return resultList;
		
	}

	public Object merge(Object object) {
        logger.debug("Merging {}", object);
        return this.em.merge(object);
	}

	public void persist(Object managedObject) {
        logger.debug("Persisting {}", managedObject);
        this.em.persist(managedObject);
	}
	
	public Object load(Object managedObject) {
		try {
			Method idGetter = managedObject.getClass().getMethod("getId");
			Long id = (Long) idGetter.invoke(managedObject);
			return load(managedObject.getClass(), id);
		}
		catch (Exception e) {
			logger.warn("Unable to load {}.", e);
		}
		return null;
	}
	
	public Object load(Class clazz, Serializable id) {
		logger.debug("Object id id {}", id);
		return this.em.getReference(clazz, id);
	}

	public void saveOrUpdate(Object managedObject) {
        throw new IllegalArgumentException("Illegal with JPA, use Hibernate instead...");
	}

	public void remove(Object object) {
        logger.debug("Removing {}", object);
        this.em.remove(object);
	}

	public void evict(Object object) {
		if (this.em instanceof HibernateEntityManager) {
	        logger.debug("Evicting {}", object);
	        ((HibernateEntityManager) em).getSession().evict(object);
		}
		else {
	        throw new IllegalArgumentException("Not supported on JPA 1.0");
		}
	}
	
	public void refresh(Object object) {
		this.em.refresh(object);
	}
	
	
	public void flush() {
        logger.debug("Flushing session");
        this.em.flush();
	}
	
	public void clear() {
        logger.debug("Clearing session");
        this.em.clear();
	}

	// collabs
    
    private EntityManager em;
    
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
    @PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}


    private static final Logger logger = LoggerFactory.getLogger(JpaPersistenceStrategy.class);

}
