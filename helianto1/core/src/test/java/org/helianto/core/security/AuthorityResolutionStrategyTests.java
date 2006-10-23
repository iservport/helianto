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

package org.helianto.core.security;

import java.util.Set;

import junit.framework.TestCase;

import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.test.SecurityTestSupport;

public class AuthorityResolutionStrategyTests extends TestCase {
    
    private UserDetailsAdapter userDetailsAdapter;
    
    private AuthorityResolutionStrategy authorityResolutionStrategy; 
    
    public void testSimpleDefaultAuthorityResolution() {
        UserRole userRole = new UserRole();
        userDetailsAdapter.getUser().getRoles().add(userRole);
        Set<UserRole> roles = authorityResolutionStrategy.resolveUserRoles();
        assertEquals(1, roles.size());
        assertSame(userRole, roles.toArray()[0]);
    }
    
    public void testDefaultAuthorityResolution() {
        UserRole childUserRole = new UserRole(), parentUserRole = new UserRole();
        childUserRole.setServiceExtension("1");
        parentUserRole.setServiceExtension("2");
        User user = userDetailsAdapter.getUser();
        user.setParent(new UserGroup());
        user.getRoles().add(childUserRole);
        user.getParent().getRoles().add(parentUserRole);
        Set<UserRole> roles = authorityResolutionStrategy.resolveUserRoles();
        assertEquals(2, roles.size());
        assertSame(childUserRole, roles.toArray()[0]);
        assertSame(parentUserRole, roles.toArray()[1]);
    }
    
    public void testDuplicateDefaultAuthorityResolution() {
        UserRole childUserRole = new UserRole(), parentUserRole = new UserRole();
        childUserRole.setServiceExtension("1");
        parentUserRole.setServiceExtension("2");
        User user = userDetailsAdapter.getUser();
        user.setParent(new UserGroup());
        user.getRoles().add(childUserRole);
        user.getParent().getRoles().add(parentUserRole);
        user.getParent().getRoles().add(childUserRole); //too
        Set<UserRole> roles = authorityResolutionStrategy.resolveUserRoles();
        assertEquals(2, roles.size());
        assertSame(childUserRole, roles.toArray()[0]);
        assertSame(parentUserRole, roles.toArray()[1]);
    }
    
    //
    
    @Override
    public void setUp() {
        userDetailsAdapter = SecurityTestSupport.createUserDetailsAdapter();
        authorityResolutionStrategy = 
            userDetailsAdapter.new DefaultAuthorityResolutionStrategy();
    }

}
