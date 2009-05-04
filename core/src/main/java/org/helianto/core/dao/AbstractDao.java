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
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.QueryEnabled;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Base implementation for <code>LightweightDao</code>.
 * 
 * @author Mauricio Fernandes de Castro.
 * @deprecated
 */
public abstract class AbstractDao<T extends QueryEnabled> implements LightweightDao<T> {

	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Collection<T> find(T object, String criteria) {
        StringBuilder queryBuilder = createSelectClause(object);
        if (!criteria.equals("")) {
        	queryBuilder.append("where ").append(criteria);
        }
        Collection<T> resultList = internalFind(queryBuilder.toString()).list();
        if (logger.isDebugEnabled()) {
            logger.debug("Found "+resultList.size()+" item(s)");
        }
        return resultList;
	}

	@SuppressWarnings("unchecked")
	public T findByNaturalId(T object, Object... uniqueKeys) {
        String query = object.getNaturalIdQueryString(createSelectClause(object));
        List<T> resultList = internalFind(query, uniqueKeys).list();
        if (logger.isDebugEnabled()) {
            logger.debug("Found "+resultList.size()+" item(s)");
        }
        if (resultList.size()!=1) {
            logger.warn("Not unique, returning null");
            return null;
        }
        return (T) internalFind(query, uniqueKeys).list().iterator().next();
	}
	
	protected StringBuilder createSelectClause(T object) {
		StringBuilder selectClause = new StringBuilder("select ");
		selectClause.append(object.getObjectAlias())
		.append(" from ")
		.append(object.getClass().getCanonicalName())
		.append(" ")
		.append(object.getObjectAlias())
		.append(" ");
		return selectClause;
	}

	@SuppressWarnings("unchecked")
	public T merge(T object) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+object);
        }
		return (T) this.sessionFactory.getCurrentSession().merge(object);
	}

	public void persist(T managedObject) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+managedObject);
        }
        this.sessionFactory.getCurrentSession().persist(managedObject);
	}

	public void remove(T object) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+object);
        }
		this.sessionFactory.getCurrentSession().delete(object);
	}

	public void evict(T object) {
        if (logger.isDebugEnabled()) {
            logger.debug("Evicting "+object);
        }
		this.sessionFactory.getCurrentSession().evict(object);
	}

    @Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    protected Query internalFind(String query, Object... values) {
        try {
            Query result = this.sessionFactory.getCurrentSession().createQuery(query);
            int i = 0;
            for (Object value: values) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Parameter "+i+"="+value);
                }
                result.setParameter(i++, value);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Query is "+result.getQueryString());
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Unable to assemble query", e);
        }
    }
    
    private static final Log logger = LogFactory.getLog(AbstractDao.class);

}
