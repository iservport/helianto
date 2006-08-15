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

import java.util.Set;

import org.helianto.core.User;
import org.helianto.core.UserGroup;

/**
 * Implementations
 * of this interface should provide code to
 * switch from one <code>User</code> to another having the
 * same <code>Credential</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PublicUserDetailsSwitcher extends PublicUserDetails {

    /**
     * Get the connected <code>UserGroup</code> set.
     */
    public Set<UserGroup> getUsers();
    
    /**
     * Set the <code>UserLog</code>.
     * 
     * <p>As one of the <code>User</code> primary responsibilities
     * is to carry a set of <code>Role</code>s specific to
     * an <code>Entity</code>, a new <code>User</code> different than
     * the current one will change the <code>GrantedAuthority</code>
     * array.</p>
     */
    public void setCurrentUser(User user);
    
}
