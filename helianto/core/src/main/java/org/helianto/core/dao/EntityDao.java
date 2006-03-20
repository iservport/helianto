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

/**
 * Entity data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface EntityDao {
    
    /**
     * Persist <code>Entity</code>.
     */
    public void persistEntity(Entity entity);
    
    /**
     * Remove <code>Entity</code>.
     */
    public void removeEntity(Entity entity);
    
    /**
     * Find <code>Entity</code> by <code>Home</code>
     * name and unique alias.
     */
    public Entity findEntityByHomeAndAlias(String homeName, String alias);

    /**
     * Persist <code>DefaultEntity</code>.
     */
    public void persistDefaultEntity(DefaultEntity defaultEntity);
    
    /**
     * Remove <code>DefaultEntity</code>.
     */
    public void removeDefaultEntity(DefaultEntity defaultEntity);
    
    /**
     * Find <code>Entity</code> assigned as default.
     */
    public Entity findDefaultEntity();
    
    /**
     * Find <code>Entity</code> assigned as default.
     * 
     * <p>More than one default entity is allowed when
     * distinguished by priority.</p>
     */
    public Entity findDefaultEntity(int priority);
    
}
