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

import org.helianto.core.UserRole;
import org.helianto.core.dao.CommonOrmDao;


import org.helianto.core.UserGroup;
import org.helianto.core.Service;

/**
 * <code>UserRole</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserRoleDao extends CommonOrmDao {
     
    /**
     * Persist <code>UserRole</code>.
     */
    public void persistUserRole(UserRole userRole);
    
    /**
     * Merge <code>UserRole</code>.
     */
    public UserRole mergeUserRole(UserRole userRole);
    
    /**
     * Remove <code>UserRole</code>.
     */
    public void removeUserRole(UserRole userRole);
    
    /**
     * Find <code>UserRole</code> by <code>UserGroup</code> and <code>Service</code> and serviceExtension.
     */
    public UserRole findUserRoleByNaturalId(UserGroup userGroup, Service service, String serviceExtension);
    
    
    
}
