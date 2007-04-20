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

import org.helianto.core.InternalEnumerator;
import org.helianto.core.dao.CommonOrmDao;


import org.helianto.core.Entity;

/**
 * <code>InternalEnumerator</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface InternalEnumeratorDao extends CommonOrmDao {
     
    /**
     * Persist <code>InternalEnumerator</code>.
     */
    public void persistInternalEnumerator(InternalEnumerator internalEnumerator);
    
    /**
     * Merge <code>InternalEnumerator</code>.
     */
    public InternalEnumerator mergeInternalEnumerator(InternalEnumerator internalEnumerator);
    
    /**
     * Remove <code>InternalEnumerator</code>.
     */
    public void removeInternalEnumerator(InternalEnumerator internalEnumerator);
    
    /**
     * Find <code>InternalEnumerator</code> by <code>Entity</code> and typeName.
     */
    public InternalEnumerator findInternalEnumeratorByNaturalId(Entity entity, String typeName);
    
    
    
}
