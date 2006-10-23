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
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserRole;

/**
 * A template interface to coordinate System Configuration.
 *  
 * @author Mauricio Fernandes de Castro
 */
public interface SystemConfigurationTemplate {
    
    /**
     * Create an <code>Entity</code> with name "DEFAULT" associated 
     * to a system <code>Operator</code> also named "DEFAULT".
     */
    public Entity createDefaultEntity();

    /**
     * Create the manager <code>User</code> with privileges from
     * the manager <code>UserRole</code>.
     */
    public User createManager(UserRole managerRole, Identity managerIdentity);

}
