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
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;

/**
 * <code>UserGroup</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserGroupDao extends CommonOrmDao {
     
    /**
     * Persist <code>UserGroup</code>.
     */
    public void persistUserGroup(UserGroup userGroup);
    
    /**
     * Merge <code>UserGroup</code>.
     */
    public UserGroup mergeUserGroup(UserGroup userGroup);
    
    /**
     * Merge <code>UserAssociation</code>.
     */
    public UserAssociation mergeUserAssociation(UserAssociation userAssociation);
    
    /**
     * Remove <code>UserGroup</code>.
     */
    public void removeUserGroup(UserGroup userGroup);
    
    /**
     * Find <code>UserGroup</code> by <code>Entity</code> and <code>Identity</code>.
     */
    public UserGroup findUserGroupByNaturalId(Entity entity, Identity identity);

    //
    /**
     * Find <code>UserGroup</code> by criteria.
     */
    public List<UserGroup> findUserGroupByCriteria(String criteria);
    
    /**
     * Find <code>User</code> by criteria.
     */
    public List<User> findUserByCriteria(String criteria);
    
}
