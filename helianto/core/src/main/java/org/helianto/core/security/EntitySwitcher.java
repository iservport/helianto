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

package org.helianto.core.security;

import java.util.List;

import org.helianto.core.Entity;

/**
 * Implementations
 * of this interface should provide code to
 * switch from one <code>User</code> to another having the
 * same <code>Credential</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface EntitySwitcher {

    /**
     * Return an <code>Entity</code> from all
     * <code>User</code>s connected by the same
     * <code>Credential</code>.
     */
    public List<Entity> getEntities();
    
    /**
     * Set an <code>Entity</code> if there is an 
     * <code>User</code> that to replace the current
     * secure <code>User</code> associated with the
     * <code>Entity</code> with the same <code>Credential</code> .
     */
    public void setEntity(Entity entity);
    
}
