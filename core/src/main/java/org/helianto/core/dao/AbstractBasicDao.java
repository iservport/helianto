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
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.persistence.Query;

/**
 * Base implementation for <code>BasicDao</code>.
 * 
 * @author Mauricio Fernandes de Castro.
 */
public abstract class AbstractBasicDao<T> implements BasicDao<T> {
	
	public abstract Class<? extends T> getClazz();

	public Collection<T> find(String whereClause) {
        return find(getSelectBuilder(), whereClause);
	}

	/**
	 * Subclasses may override to customize persistent object name.
	 */
	public String getObjectName() {
        return getClazz().getSimpleName();
	}

	/**
	 * Subclasses may override to customize persistent object alias.
	 */
	public String getObjectAlias() {
        return getClazz().getSimpleName().toLowerCase();
	}

	/**
	 * Subclasses may override to customize select clause creation.
	 * 
	 * @param objectName
	 */
	protected StringBuilder getSelectBuilder() {
		StringBuilder selectClause = new StringBuilder("select ");
		return selectClause
			.append(getObjectAlias())
			.append(" from ")
			.append(getObjectName())
			.append(" ")
			.append(getObjectAlias())
			.append(" ");
	}
	
	public Collection<T> find(StringBuilder selectClause, String whereClause) {
        return find(selectClause, whereClause, new Object[0]);
	}

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

	public T findUnique(Object... keys) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique with "+keys.length+" parameter(s)");
        }
		Collection<T> uniqueList = find(getSelectBuilder(), getWhereClauseBuilder(getParams()).toString(), keys);
		try {
			if (uniqueList!=null && uniqueList.size()==0) {
				return null;
			}
			if (uniqueList!=null && uniqueList.size()==1) {
				return uniqueList.iterator().next();
			}
			throw new IllegalArgumentException("Unable to find unique result: found "+uniqueList.size());
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to find unique result", e);
		}
	}

	/**
	 * @return
	 */
	protected StringBuilder getWhereClauseBuilder(String[] fields) {
		StringBuilder whereClauseBuilder = new StringBuilder();
		for (int i = 0;i<fields.length;i++) {
			if (i>0) {
				whereClauseBuilder.append(" AND ");
			}
			whereClauseBuilder.append(getObjectAlias()).append(".");
			whereClauseBuilder.append(fields[i]).append(" = ?");
		}
		whereClauseBuilder.append(" ");
		return whereClauseBuilder;
	}

	/**
	 * Default keys are entity.id and internalNumber
	 */
	protected String[] getParams() {
		return new String[] { "entity", "internalNumber" };
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
//		this.em.   evict   (object);
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
    @PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

    private static final Log logger = LogFactory.getLog(AbstractBasicDao.class);

}
