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

import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.Operator;

/**
 * Entity data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface EntityDao {
    
    /**
     * Persists <code>Entity</code>.
     */
    public void persistEntity(Entity entity);
    
    /**
     * Merges <code>Entity</code>.
     */
    public Entity mergeEntity(Entity entity);
    
    /**
     * Removes <code>Entity</code>.
     */
    public void removeEntity(Entity entity);
    
    /**
     * Finds code>Entity</code> by <code>Operator</code> and alias.
     */
    public Entity findEntityByNaturalId(Operator operator, String alias);
    
    /**
     * Persists <code>DefaultEntity</code>.
     * @deprecated
     */
    public void persistDefaultEntity(DefaultEntity defaultEntity);
    
    /**
     * Removes <code>DefaultEntity</code>.
     * @deprecated
     */
    public void removeDefaultEntity(DefaultEntity defaultEntity);
    
    /**
     * Finds <code>Entity</code> assigned as default.
     * @deprecated
     */
    public Entity findDefaultEntity();
    
    /**
     * Finds <code>Entity</code> assigned as default.
     * 
     * <p>More than one default entity is allowed when
     * distinguished by priority.</p>
     * @deprecated
     */
    public Entity findDefaultEntity(int priority);
    
    /**
     * Finds last <code>InternalEnumerator</code> for the
     * <code>Entity</code> and type.
     * @param entity
     * @param typeName
     * @return
     * @throws NullEntityException
     */public InternalEnumerator findInternalEnumerator(Entity entity, String typeName);
     
     /**
      * Persists <code>InternalEnumerator</code>.
      */
     public void persistInternalEnumerator(InternalEnumerator internalEnumerator);
    
}
