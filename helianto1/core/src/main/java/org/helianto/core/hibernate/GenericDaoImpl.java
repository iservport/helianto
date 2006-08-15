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

import java.util.Iterator;

import org.helianto.core.dao.GenericDao;
import org.springframework.util.Assert;

/**
 * Hibernate implementation for the <code>GenericDao</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class GenericDaoImpl extends LightweightDaoImpl implements GenericDao {

    public void save(Object object) {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("\n        Saving "+object.toString());
        }
        this.getHibernateTemplate().save(object);
    }

    public void saveOrUpdate(Object object) {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("\n        Saving or updating "+object.toString());
        }
        this.getHibernateTemplate().saveOrUpdate(object);
    }

    public void update(Object object) {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("\n        Updating "+object.toString());
        }
        this.getHibernateTemplate().update(object);
    }

    public void evict(Object object) {
        Assert.notNull(object);
        if (logger.isDebugEnabled()) {
            logger.debug("\n        Evicting (removing from session cache) "+object.toString());
        }
        this.getHibernateTemplate().evict(object);
    }
    
    public Iterator iterate(String query, Object values) {
        if (values != null) {
            if (values instanceof Object[]) {
                Object[] valueList = (Object[]) values;
                for (int i = 0; i<valueList.length; i++) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("\n        Parameter "+i+"="+valueList[i]);
                    }
                }
                return this.getHibernateTemplate().iterate(query, valueList);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("\n        Single parameter is "+values);
                }
                return this.getHibernateTemplate().iterate(query, values);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("\n        No parameters");
            }
            return this.getHibernateTemplate().iterate(query);
        }
    }

}
