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

package org.helianto.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.AuthorizationDao;
import org.helianto.core.dao.InternalEnumeratorDao;

/**
 * Core base class.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AbstractCoreMgr {

    protected AuthenticationDao authenticationDao;
    
    protected AuthorizationDao authorizationDao;
    protected InternalEnumeratorDao internalEnumeratorDao;
    
    public long findNextInternalNumber(Entity entity, String typeName) {
        InternalEnumerator enumerator = internalEnumeratorDao.findInternalEnumeratorByNaturalId(entity, typeName);
        if (enumerator!=null) {
            long lastNumber = enumerator.getLastNumber();
            enumerator.setLastNumber(lastNumber+1);
            internalEnumeratorDao.persistInternalEnumerator(enumerator);
            if (logger.isDebugEnabled()) {
                logger.debug("Incremented existing InternalEnumerator: "+enumerator);
            }
            return lastNumber;
        } else  {
            enumerator = new InternalEnumerator();
            enumerator.setEntity(entity);
            enumerator.setTypeName(typeName);
            enumerator.setLastNumber(2);    
            internalEnumeratorDao.persistInternalEnumerator(enumerator);
            if (logger.isDebugEnabled()) {
                logger.debug("Created InternalEnumerator: "+enumerator);
            }
            return 1;
        }
    }
    
    //
    
    public void init() {
        if (authenticationDao==null) throw new IllegalArgumentException("AuthenticationDao property required");
        if (authorizationDao==null) throw new IllegalArgumentException("AuthorizationDao property required");
    }
    
    //~ collaborators

    public void setAuthenticationDao(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public void setInternalEnumeratorDao(InternalEnumeratorDao internalEnumeratorDao) {
        this.internalEnumeratorDao = internalEnumeratorDao;
    }

    protected final Log logger = LogFactory.getLog(SecurityMgrImpl.class);

}
