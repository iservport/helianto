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


import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserRole;

public class ServiceCreatorTests extends TestCase {
    
    public void testServiceFactory() {
        Service service = ServiceCreator.serviceFactory("SERVICE_NAME");
        assertEquals("SERVICE_NAME", service.getServiceName());
    }
    
    public void testUserRoleFactory() {
        Service service = ServiceCreator.serviceFactory("SERVICE_NAME");
        Identity identity = UserCreator.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        Entity entity = new Entity();
        User user = UserCreator.userFactory(entity, identity);
        UserRole userRole = ServiceCreator.userRoleFactory(user, service, "EXT");
        assertSame(user, userRole.getUser());
        assertSame(service, userRole.getService());
        assertEquals("EXT", userRole.getServiceExtension());
        assertTrue(user.getRoles().contains(userRole));
    }

    public void testUserRoleFactoryError() {
        try {
            ServiceCreator.userRoleFactory(null, new Service(), ""); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
        try {
            ServiceCreator.userRoleFactory(new User(), null, ""); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
        try {
            ServiceCreator.userRoleFactory(new User(), new Service(), null);
        } catch (Exception e) { fail(); }
    }
    
}
