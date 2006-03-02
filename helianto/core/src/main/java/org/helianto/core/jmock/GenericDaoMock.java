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

package org.helianto.core.jmock;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.dao.GenericDao;
import org.springframework.dao.DataAccessException;

public class GenericDaoMock implements GenericDao {
    
    /**
     * Logger for this class
     */
    protected static final Log logger = LogFactory.getLog(GenericDaoMock.class);
    
    public void save(Object object) {
        if (getId(object)==null) {
            setId(object);
        }
        logger.info("\n         Save invoked");
    }

    public void saveOrUpdate(Object object) {
        if (getId(object)==null) {
            setId(object);
            logger.info("\n         Save invoked");
        } else {
            logger.info("\n         Update invoked");
        }
    }

    public void update(Object object) {
        logger.info("\n         Update invoked");
    }

    public void refresh(Object object) {
        logger.info("\n         Refresh invoked");
    }

    public void evict(Object object) {
        logger.info("\n         Evict invoked");
    }

    public Object load(Class clazz, Serializable key)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    public void merge(Object object) throws DataAccessException {
        // TODO Auto-generated method stub

    }

    public void remove(Object object) throws DataAccessException {
        // TODO Auto-generated method stub

    }

    public Collection find(String query, Object values)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    public Object findUnique(String query, Object values)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    private void setId(Object object) {
        try {
            object.getClass()
                .getMethod("setId", getId(object).getClass())
                .invoke(object, new Object[] {new Date().getTime()});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Object getId(Object object) {
        try {
            return object.getClass()
                .getMethod("getId", (Class[]) null)
                .invoke(object, (Object[]) null); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void flush() {
        // no stub is necessary
        
    }

    public void clear() {
//      no stub is necessary
        
    }

}
