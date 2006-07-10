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

package org.helianto.core.creation;

import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.springframework.util.Assert;

public class ServiceCreator {

    /**
     * <code>Service</code> catalog.
     */
    public static Service serviceFactory(String serviceName) {
        Service service = new Service();
        service.setServiceName(serviceName);
        return service;
    }
        
    /**
     * <code>Role</code> to be granted an <code>User</code>.
     * 
     * @param user
     * @param service
     * @param extension
     */
    public static UserRole userRoleFactory(User user, Service service, String extension) {
        Assert.notNull(user);
        Assert.notNull(service);
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setService(service);
        if (extension==null) {
            extension = "";
        }
        userRole.setServiceExtension(extension);
        userRole.getUser().getRoles().add(userRole);
        return userRole;
    }
    
}
