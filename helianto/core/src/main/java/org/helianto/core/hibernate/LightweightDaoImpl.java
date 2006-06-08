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

/**
 * Hibernate implementation for the <code>LightweightDao</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class LightweightDaoImpl extends HibernateDaoSupport implements LightweightDao {

    public Object load(Class clazz, Serializable key) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Loading "+clazz.toString()
                    +" with id "+key.toString());
        }
        return this.getHibernateTemplate().load(clazz, key);
    }

    public void merge(Object object) {
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Merging "+object.toString());
        }
        this.getHibernateTemplate().merge(object);
    }

    public void remove(Object object) {
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
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Refreshing "+object);
        }
        this.getHibernateTemplate().refresh(object);
    }

    public void persist(Object object) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO Persisting "+object);
        }
        this.getHibernateTemplate().persist(object);
    }

    protected Query queryAssembler(String query, Object... values) {
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
            throw new RuntimeException("Unable to assemble query");
        }
    }
    
    public Collection find(String query, Object... values) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("** DAO finding ["+query+"] with "+values);
        }
        return queryAssembler(query,  values).list();
    }

    public Object findUnique(String query, Object... values) throws DataAccessException {
        Collection list = find(query, values);
        if (isUnique(list)) {
            return ((List) list).get(0);
        }
        throw new DataIntegrityViolationException("Unique object expected");
    }

    public boolean isUnique(Collection collection) {
        if (collection.size()==1) {
            if (logger.isDebugEnabled()) {
                logger.debug("Unique result found");
            }
            return true;
        } else if(collection.size()==0) {
            if (logger.isDebugEnabled()) {
                logger.debug("Empty result found");
            }
        }
        return false;
        
    }

    @Deprecated
    protected Query queryAssembler(String query, Object values) {
        try {
            Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
            Query result = session.createQuery(query);
            if (values != null) {
                if (values instanceof Object[]) {
                    Object[] valueList = (Object[]) values;
                    for (int i = 0; i<valueList.length; i++) {
                        result.setParameter(i, valueList[i]);
                        if (logger.isDebugEnabled()) {
                            logger.debug("\n        Parameter "+i+"="+valueList[i]);
                        }
                    }
                } else {
                    result.setParameter(0, values);
                    if (logger.isDebugEnabled()) {
                        logger.debug("\n        Single parameter is "+values);
                    }
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("\n        No parameters");
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Unable to assemble query");
        }
    }
    
    @Deprecated
    public Collection find(String query, Object values) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n        Finding object with query "+query);
        }
        if (values instanceof Object[]) {
            return queryAssembler(query,  values).list();
        }
        return this.getHibernateTemplate().find(query, values);
    }
    
    @Deprecated
    public Object findUnique(String query, Object values) throws DataAccessException {
        Collection list = find(query, values);
        if (isUnique(list)) {
            return ((List) list).get(0);
        }
        throw new DataIntegrityViolationException("Unique object expected");
    }

}
