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

import org.helianto.core.Server;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Operator;
import org.helianto.user.domain.User;

/**
 * Default base service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ServerMgr {
	
	/**
	 * Find the nth active server or return null.
	 * 
	 * @param operator
	 * @param n
	 */
	public Server findActiveServer(Operator operator, int n);

    /**
     * A manager is an <code>User</code> associated to both, an <code>ADMIN</code>
     * group and an <code>USER</code> group.
     * 
     * @param managerIdentity
     */
    public User storeManager(Identity managerIdentity);
    
    /**
     * A manager is an <code>User</code> associated to both, an <code>ADMIN</code>
     * group and an <code>USER</code> group.
     * 
     * @param entity
     * @param managerIdentity
     */
    public User storeManager(Entity entity, Identity managerIdentity);
    
}