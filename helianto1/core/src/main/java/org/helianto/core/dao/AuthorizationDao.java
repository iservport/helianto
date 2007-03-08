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

import java.util.Date;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRole;

public interface AuthorizationDao extends EntityDao {
    
    // user group

    /**
     * Persists <code>UserGroup</code>.
     */
    public void persistUserGroup(UserGroup userGroup);
    
    /**
     * Merges <code>UserGroup</code>.
     */
    public UserGroup mergeUserGroup(UserGroup userGroup);
    
    /**
     * Removes <code>UserGroup</code>.
     */
    public void removeUserGroup(UserGroup userGroup);
    
    /**
     * Finds <code>UserGroup</code> by  entity and identity.
     */
    public UserGroup findUserGroupByNaturalId(Entity entity, Identity identity);
    
    /**
     * Finds <code>UserGroup</code> by  entity.
     */
    public List<UserGroup> findUserGroupByEntity(Entity entity);
    
    // user log
    
    /**
     * Persists <code>UserLog</code>.
     */
    public void persistUserLog(UserLog userLog);
    
    /**
     * Merges <code>UserLog</code>.
     */
    public UserLog mergeUserLog(UserLog userLog);
    
    /**
     * Removes <code>UserLog</code>.
     */
    public void removeUserLog(UserLog userLog);
    
    /**
     * Finds <code>UserLog</code> by  user and lastEvent.
     */
    public UserLog findUserLogByNaturalId(User user, Date lastEvent);
    
    /**
     * Finds the last <code>UserLog</code>.
     */
    public UserLog findLastUserLog(Identity requiredIdentity);

    /**
     * Finds <code>UserLog</code> by user.
     */
    public List<UserLog> findUserLogByUser(User requiredUser);
    
    // user role
    
    /**
     * Persists <code>UserRole</code>.
     */
    public void persistUserRole(UserRole userRole);

    /**
     * Merges <code>UserRole</code>.
     */
    public UserRole mergeUserRole(UserRole userRole);

    /**
     * Removes <code>UserRole</code>.
     */
    public void removeUserRole(UserRole userRole);

    /**
     * Finds <code>UserRole</code> by  user, service and identity.
     */
    public UserRole findUserRoleByNaturalId(UserGroup userGroup, Service service, String serviceExtension);

}
