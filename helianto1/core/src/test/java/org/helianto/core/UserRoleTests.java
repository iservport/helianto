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

package org.helianto.core;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;

public class UserRoleTests extends TestCase {

    public void testUserRole() {
        UserRole userRole = new UserRole();
        userRole.setId(Long.MAX_VALUE);
        userRole.setId(Long.MIN_VALUE);
        
        userRole.setUserGroup(new UserGroup());
        userRole.setUserGroup(new User());
        
        userRole.setService(new Service());
        
        userRole.setServiceExtension("");
    }

    public void testUserRoleEquals() {
        UserRole copy, userRole = new UserRole();
        userRole.setUserGroup(new UserGroup());
        userRole.setService(new Service());
        copy = (UserRole) DomainTestSupport.minimalEqualsTest(userRole);

        copy.setUserGroup(userRole.getUserGroup());
        assertFalse(userRole.equals(copy));

        copy.setUserGroup(null);
        copy.setService(userRole.getService());
        assertFalse(userRole.equals(copy));

        copy.setUserGroup(userRole.getUserGroup());
        copy.setService(userRole.getService());
        assertTrue(userRole.equals(copy));
    }

}
