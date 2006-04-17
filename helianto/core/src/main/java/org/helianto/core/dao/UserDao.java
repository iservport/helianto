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

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.UserLog;

/**
 * User data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface UserDao extends CredentialDao {
    
    /**
     * Persist <code>User</code>.
     */
    public void persistUser(User user);
    
    /**
     * Remove <code>User</code>.
     */
    public void removeUser(User user);
    
    /**
     * Find <code>User</code> by <code>Entity</code>
     * alias and <code>Credential</code> principal.
     */
    public User findUserByEntityAliasAndPrincipal(String alias, String principal);

    /**
     * Find <code>User</code> by <code>Entity</code>.
     */
    public List<User> findUserByEntity(Entity entity);
    
    /**
     * Convenience method to create and persist a new 
     * <code>User</code> automatically.
     */
    public User autoCreateAndPersistUser(String principal);
    
    /**
     * Create and persist a new <code>UserLog</code>.
     * 
     * <p>The <code>UserLog</code> is automatically assigned 
     * the current date.</p>
     */
    public UserLog createAndPersistUserLog(User user);
    
    /**
     * List <code>UserLog</code> by <code>User</code>.
     */
    public List<UserLog> findUserLogByUser(User user);
    
    /**
     * Find the last <code>UserLog</code> for any <code>User</code>
     * having a given principal.
     */
    public UserLog findLastUserLog(String principal);
}
