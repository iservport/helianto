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

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.test.SecurityTestSupport;

public class AuthorityResolutionStrategyTests extends TestCase {
    
    private UserDetailsAdapter userDetailsAdapter;
    
    private AuthorityResolutionStrategy authorityResolutionStrategy; 
    
    public void testSimpleDefaultAuthorityResolution() {
        Set<UserRole> roleSet = authorityResolutionStrategy.resolveUserRoles();
        assertEquals(1, roleSet.size());
        assertSame(roles[0], roleSet.toArray()[0]);
    }
    
    private UserGroup createUserAssociationWithRole(UserGroup user, int i) {
        UserGroup parent = new UserGroup();
        parent.setRoles(new HashSet<UserRole>());
        parent.getRoles().add(roles[i]);
        AuthorizationCreator.createUserAssociation(parent, user);
        return parent;
    }
    
    public void testDefaultAuthorityResolution() {
        User user = userDetailsAdapter.getUser();
        createUserAssociationWithRole(user, 1);
        Set<UserRole> roleSet = authorityResolutionStrategy.resolveUserRoles();
        assertEquals(2, roleSet.size());
        assertTrue(roleSet.contains(roles[0]));
        assertTrue(roleSet.contains(roles[1]));
    }
    
    public void testDuplicateDefaultAuthorityResolution() {
        User user = userDetailsAdapter.getUser();
        UserGroup parent = createUserAssociationWithRole(user, 1);
        createUserAssociationWithRole(parent, 1);
        Set<UserRole> roleSet = authorityResolutionStrategy.resolveUserRoles();
        assertEquals(2, roleSet.size());
        assertTrue(roleSet.contains(roles[0]));
        assertTrue(roleSet.contains(roles[1]));
    }
    
    private UserRole[] roles;
    
    private UserRole[] createUserRoles(int size) {
        UserRole[] roles = new UserRole[size];
        for (int i = 0;i<size;i++) {
            UserRole role = new UserRole();
            Service service = new Service();
            role.setUserGroup(userDetailsAdapter.getUser());
            role.setService(service);
            role.setServiceExtension(""+i);
            roles[i] = role;
        }
        return roles;
    }
    
    //
    
    @Override
    public void setUp() {
        userDetailsAdapter = SecurityTestSupport.createUserDetailsAdapter();
        authorityResolutionStrategy = 
            userDetailsAdapter.new DefaultAuthorityResolutionStrategy();
        roles = createUserRoles(3);
        // start with one role only
        userDetailsAdapter.getUser().getRoles().add(roles[0]);
    }

}
