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

import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserRole;

public interface ServiceDao extends UserDao {

    /**
     * Persist <code>Service</code>.
     * 
     * @param service
     */
    public void persistService(Service service);

    /**
     * Find <code>Service</code>.
     * 
     * @param serviceName
     */
    public Service findServiceByName(String serviceName);

    /**
     * Remove <code>Service</code>.
     */
    public void removeService(Service service);
    
    /**
     * Persist <code>UserRole</code>.
     * 
     * @param userRole
     */
    public void persistUserRole(UserRole userRole);

    /**
     * Find <code>UserRole</code>.
     * 
     * @param user
     * @param service
     * @param serviceExtension
     */
    public UserRole findUserRole(User user, Service service, String serviceExtension);

    /**
     * Remove <code>UserRole</code>.
     */
    public void removeUserRole(UserRole userRole);
    
}
