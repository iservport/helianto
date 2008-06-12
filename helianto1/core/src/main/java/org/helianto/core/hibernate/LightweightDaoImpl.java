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

package org.helianto.core.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.dao.LightweightDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.Assert;

/**
 * Hibernate implementation for the <code>LightweightDao</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class LightweightDaoImpl implements LightweightDao {
	
	protected SessionFactory sessionFactory;

    public void persist(Object object) throws DataAccessException {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Persisting "+object);
        }
        this.sessionFactory.getCurrentSession().persist(object);
    }

    public Object merge(Object object) {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Merging "+object.toString());
        }
        return this.sessionFactory.getCurrentSession().merge(object);
    }

    @SuppressWarnings("unchecked")
	public Object load(Class clazz, Serializable key) throws DataAccessException {
        Assert.notNull(clazz);
        Assert.notNull(key);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Loading "+clazz.toString()
                    +" with id "+key.toString());
        }
        return this.sessionFactory.getCurrentSession().load(clazz, key);
    }

    public <T> T find(Class<T> clazz, Serializable key) throws DataAccessException {
        Assert.notNull(clazz);
        Assert.notNull(key);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO FINDING "+clazz.toString()
                    +" with id "+key.toString());
        }
        return clazz.cast(this.sessionFactory.getCurrentSession().load(clazz, key)) ;
    }

    @SuppressWarnings("unchecked")
	public void remove(Object object) {
        Assert.notNull(object);
        if (object instanceof String) {
            remove(find(object.toString()));
        } else if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (logger.isDebugEnabled()) {
                logger.debug("** DAO Deleting collection of size "+collection.size());
            }
            for (Object item: collection) {
            	this.sessionFactory.getCurrentSession().delete(item);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("** DAO Deleting "+object.toString());
            }
            this.sessionFactory.getCurrentSession().delete(object);
        }
    }

    protected Query queryAssembler(String query, Object... values) {
        Assert.notNull(query);
        try {
            Query result = this.sessionFactory.getCurrentSession().createQuery(query);
            int i = 0;
            for (Object value: values) {
                result.setParameter(i++, value);
                if (logger.isDebugEnabled()) {
                    logger.debug("Parameter "+i+"="+value);
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Unable to assemble query", e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public Collection find(String query, Object... values) throws DataAccessException {
        Assert.notNull(query);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO finding ["+query+"] with "+values);
        }
        return queryAssembler(query,  values).list();
    }

    @SuppressWarnings("unchecked")
	public Collection find(StringBuilder query, Object... values) throws DataAccessException {
        return find(query.toString(), values);
    }

    @SuppressWarnings("unchecked")
	public Object findUnique(String query, Object... values) throws DataAccessException {
        Assert.notNull(query);
        Collection list = find(query, values);
        if (isUnique(list)) {
            return ((List) list).get(0);
        } else if (isEmpty(list)) {
            return null;
        }
        throw new DataIntegrityViolationException("Unique object expected");
    }

    @SuppressWarnings("unchecked")
	public boolean isUnique(Collection collection) {
        Assert.notNull(collection);
        if (collection.size()==1) {
            if (logger.isDebugEnabled()) {
                logger.debug("Unique result found");
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
	public boolean isEmpty(Collection collection) {
        Assert.notNull(collection);
        if (collection.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Empty result found");
            }
            return true;
        }
        return false;
    }
    
    public boolean contains(Object object) throws DataAccessException {
        Assert.notNull(object);
        boolean contains = this.sessionFactory.getCurrentSession().contains(object);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Session "+(contains ? "contains ": "does not contain ")+object);
        }
        return contains;
    }

    public void refresh(Object object) throws DataAccessException {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Refreshing "+object);
        }
        this.sessionFactory.getCurrentSession().refresh(object);
    }

    public void flush() {
        if (logger.isDebugEnabled()) {
            logger.debug("Flushing session.");
        }
        this.sessionFactory.getCurrentSession().flush();
    }
    
    public void clear() {
        if (logger.isDebugEnabled()) {
            logger.debug("Clearing session.");
        }
        this.sessionFactory.getCurrentSession().clear();
    }
    
    
    
    @Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    protected static final Log logger = LogFactory.getLog(LightweightDaoImpl.class);

}
