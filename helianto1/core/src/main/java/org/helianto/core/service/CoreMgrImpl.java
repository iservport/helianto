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

import org.helianto.core.Entity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.dao.EntityDao;

/**
 * Default implementation of the 
 * <code>CoreMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * @deprecated use UserMgr or SimplCoreMgr
 */
public class CoreMgrImpl extends AbstractGenericService implements CoreMgr {
    
    private EntityDao entityDao;

    public long findNextInternalNumber(Entity entity, String typeName) {
        InternalEnumerator enumerator = entityDao.findInternalEnumerator(entity, typeName);
        if (enumerator!=null) {
            long lastNumber = enumerator.getLastNumber();
            enumerator.setLastNumber(lastNumber+1);
            entityDao.persistInternalEnumerator(enumerator);
            return lastNumber;
        } else  {
            enumerator = new InternalEnumerator();
            enumerator.setEntity(entity);
            enumerator.setTypeName(typeName);
            enumerator.setLastNumber(2);    
            entityDao.persistInternalEnumerator(enumerator);
            return 1;
        }
    }
    
    // accessors and mutators
    
    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

}
