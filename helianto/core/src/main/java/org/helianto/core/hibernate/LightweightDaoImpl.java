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
import org.helianto.core.dao.LightweightDao;
import org.hibernate.HibernateException;
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
            logger.debug("\n        Loading "+clazz.toString()
                    +" with id "+key.toString());
        }
        return this.getHibernateTemplate().load(clazz, key);
    }

    public void merge(Object object) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Merging "+object.toString());
        }
        this.getHibernateTemplate().merge(object);
    }

    public void remove(Object object) {
        if (object instanceof String) {
            remove(find(object.toString(), null));
        } else if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (logger.isDebugEnabled()) {
                logger.debug("\n        Deleting collection of size "+collection.size());
            }
            this.getHibernateTemplate().deleteAll(collection);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("\n        Deleting "+object.toString());
            }
            this.getHibernateTemplate().delete(object);
        }
    }

    public void refresh(Object object) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("\n        Refreshing "+object);
        }
        this.getHibernateTemplate().refresh(object);
    }

    public void persist(Object object) throws DataAccessException {
        this.getHibernateTemplate().persist(object);
    }

    public Collection find(String query, Object values) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n        Finding object with query "+query);
        }
        
        Session session = getSession(true);
        try {
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
            return result.list();
        }
        catch (HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }
    }

    public Object findUnique(String query, Object values) throws DataAccessException {
    	Collection list = find(query, values);
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Query result size is ");
        }
        if (list.size()==1) {
            Object object = list.toArray()[0];
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Unique object "+object+" found");
            }
            return object;
        } else if(list.size()==0) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Unique object not found");
            }
            return null;
        }
        throw new DataIntegrityViolationException("Unique object expected");
    }

}
