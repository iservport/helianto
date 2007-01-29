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

import org.helianto.core.dao.LightweightDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

/**
 * Hibernate implementation for the <code>LightweightDao</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class LightweightDaoImpl extends HibernateDaoSupport implements LightweightDao {

    public Object load(Class clazz, Serializable key) throws DataAccessException {
        Assert.notNull(clazz);
        Assert.notNull(key);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Loading "+clazz.toString()
                    +" with id "+key.toString());
        }
        return this.getHibernateTemplate().load(clazz, key);
    }

    public Object merge(Object object) {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Merging "+object.toString());
        }
        return this.getHibernateTemplate().merge(object);
    }

    public void remove(Object object) {
        Assert.notNull(object);
        if (object instanceof String) {
            remove(find(object.toString()));
        } else if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (logger.isDebugEnabled()) {
                logger.debug("** DAO Deleting collection of size "+collection.size());
            }
            this.getHibernateTemplate().deleteAll(collection);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("** DAO Deleting "+object.toString());
            }
            this.getHibernateTemplate().delete(object);
        }
    }

    public void refresh(Object object) throws DataAccessException {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Refreshing "+object);
        }
        this.getHibernateTemplate().refresh(object);
    }

    public void persist(Object object) throws DataAccessException {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Persisting "+object);
        }
        this.getHibernateTemplate().persist(object);
    }

    protected Query queryAssembler(String query, Object... values) {
        Assert.notNull(query);
        try {
            Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
            Query result = session.createQuery(query);
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
    
    public Collection find(String query, Object... values) throws DataAccessException {
        Assert.notNull(query);
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO finding ["+query+"] with "+values);
        }
        return queryAssembler(query,  values).list();
    }

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
    
    protected void flush() {
        if (logger.isDebugEnabled()) {
            logger.debug("Flushing session.");
        }
        getHibernateTemplate().flush();
    }

}
