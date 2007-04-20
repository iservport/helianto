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

package org.helianto.core.orm;

import org.helianto.core.InternalEnumerator;
import org.helianto.core.dao.InternalEnumeratorDao;
import org.helianto.core.hibernate.GenericDaoImpl;



import org.helianto.core.Entity;
/**
 * Default implementation of <code>InternalEnumerator</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InternalEnumeratorDaoImpl extends GenericDaoImpl implements InternalEnumeratorDao {
     
    public void persistInternalEnumerator(InternalEnumerator internalEnumerator) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+internalEnumerator);
        }
        persist(internalEnumerator);
    }
    
    public InternalEnumerator mergeInternalEnumerator(InternalEnumerator internalEnumerator) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+internalEnumerator);
        }
        return (InternalEnumerator) merge(internalEnumerator);
    }
    
    public void removeInternalEnumerator(InternalEnumerator internalEnumerator) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+internalEnumerator);
        }
        remove(internalEnumerator);
    }
    
    public InternalEnumerator findInternalEnumeratorByNaturalId(Entity entity, String typeName) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique internalEnumerator with entity='"+entity+"' and typeName='"+typeName+"' ");
        }
        return (InternalEnumerator) findUnique(InternalEnumerator.getInternalEnumeratorNaturalIdQueryString(), entity, typeName);
    }
    
    
	static String INTERNALENUMERATOR_ENTITY_QRY = "select internalEnumerator from InternalEnumerator internalEnumerator "+
	    "where internalEnumerator.entity = ? ";
    
}
