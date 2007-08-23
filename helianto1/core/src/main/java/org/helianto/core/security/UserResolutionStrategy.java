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

import org.helianto.core.Identity;
import org.helianto.core.User;

/**
 * A common interface for <code>User</code> resolution strategies.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserResolutionStrategy {
    
    /**
     * Load all <code>User</code>s sharing the same <code>Identity</code>.
     * 
     * @param identity
     */
    public List<User> loadUsers(Identity identity);

    /**
     * Select a valid <code>User</code> from a previous login.
     * 
     * @param userList
     */
    public User selectUserFromPreviousLogin(List<User> userList);

    /**
     * Select a valid <code>User</code> from the list.
     * 
     * @param userList
     */
    public User selectUserIfAny(List<User> userList);

    /**
     * Create a valid <code>User</code>.
     * 
     * @param identity
     */
    public User createUser(Identity identity);

}
