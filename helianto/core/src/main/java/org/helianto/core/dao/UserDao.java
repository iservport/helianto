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
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;

/**
 * User data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
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
     * and <code>Identity</code>.
     */
    public User findUserByEntityAndIdentity(Entity requiredEntity, Identity requiredIdentity);

    /**
     * Find <code>User</code> by <code>Entity</code>.
     */
    public List<User> findUserByEntity(Entity requiredEntity);
    
    /**
     * Persist <code>UserLog</code>.
     * 
     * @param userLog
     */
    public void persistUserLog(UserLog userLog);
    
    /**
     * List <code>UserLog</code> by <code>User</code>.
     */
    public List<UserLog> findUserLogByUser(User requiredUser);
    
    /**
     * Find the last <code>UserLog</code> for an <code>Identity</code>.
     */
    public UserLog findLastUserLog(Identity requiredIdentity);
    
    /**
     * Remove <code>UserLog</code>.
     */
    public void removeUserLog(UserLog userLog);
    
}
