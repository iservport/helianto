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

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.ejb.HibernateEntityManager;

/**
 * Base implementation using Jpa for <code>BasicDao</code>.
 * 
 * @author Mauricio Fernandes de Castro.
 */
public abstract class AbstractJpaBasicDao<T> extends AbstractBasicDao<T> {
	
	@SuppressWarnings("unchecked")
	protected Collection<T> find(StringBuilder selectClause, String whereClause, Object... values) {
        if (!whereClause.equals("")) {
        	selectClause.append("where ").append(whereClause);
        }
        Query result = this.em.createQuery(selectClause.toString());
        /*
         * Remember that ordinal parameters are 1-based!
         */
        int i = 1;
        for (Object value: values) {
            result.setParameter(i++, value);
        }
        Collection<T> resultList = result.getResultList();
        if (logger.isDebugEnabled()) {
            logger.debug("Found "+resultList.size()+" item(s)");
        }
        return resultList;
	}

	public T merge(T object) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+object);
        }
		return (T) this.em.merge(object);
	}

	public void persist(T managedObject) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+managedObject);
        }
        this.em.persist(managedObject);
	}

	public void remove(T object) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+object);
        }
		this.em.remove(object);
	}

	public void evict(T object) {
        if (logger.isDebugEnabled()) {
            logger.debug("Evicting "+object);
        }
        if (em instanceof HibernateEntityManager) {
            if (logger.isDebugEnabled()) {
                logger.debug("Using hibernate entity manager");
            }
            ((HibernateEntityManager) (this.em)).getSession().evict(object); 
        }
        else {
        	logger.warn("Evict contract planned to jpa 2.0 not yet available");
        }
	}
	
	public void flush() {
        if (logger.isDebugEnabled()) {
            logger.debug("Flushing session");
        }
		this.em.flush();
	}
	
	public void clear() {
        if (logger.isDebugEnabled()) {
            logger.debug("Clearing session");
        }
		this.em.clear();
	}
	
	// collabs
    
    private EntityManager em;
    
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
//    @PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

    private static final Log logger = LogFactory.getLog(AbstractJpaBasicDao.class);

}
