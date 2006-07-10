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

package org.helianto.core.hibernate;

import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.dao.ServiceDao;
import org.springframework.util.Assert;

public class ServiceDaoImpl extends UserDaoImpl implements ServiceDao {
	
    /*
     * Persist and find Service
     */
    
    public void persistService(Service service) {
        Assert.notNull(service);
        merge(service);
    }

    public Service findServiceByName(String serviceName) {
        return (Service) findUnique(SERVICE_QUERY, serviceName);
    }

    static final String SERVICE_QUERY = "from Service service " +
        "where service.serviceName = ? ";

    public void removeService(Service service) {
        Assert.notNull(service);
        remove(service);
    }

    /*
     * Persist and find UserRole
     */
    
    public void persistUserRole(UserRole userRole) {
        Assert.notNull(userRole);
        merge(userRole);
    }

    public UserRole findUserRole(User user, Service service, String serviceExtension) {
        return (UserRole) findUnique(USER_ROLE_QUERY, user, service, serviceExtension);
    }

    static final String USER_ROLE_QUERY = "from UserRole userRole " +
            "where userRole.user = ? " +
            "and userRole.service = ? " +
            "and userRole.serviceExtension = ? ";

    public void removeUserRole(UserRole userRole) {
        Assert.notNull(userRole);
        remove(userRole);
    }

}
