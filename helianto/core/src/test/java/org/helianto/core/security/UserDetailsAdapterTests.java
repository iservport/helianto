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

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.hibernate.CredentialDaoImplTests;
import org.helianto.core.hibernate.ServiceDaoImplTests;
import org.helianto.core.hibernate.UserDaoImplTests;
import org.helianto.core.type.CredentialState;

public class UserDetailsAdapterTests extends TestCase {
    
    public void testUserDetailsAdapterError() {
        Identity id1 = CredentialDaoImplTests.createAndPersistIdentity(null);
        Identity id2 = CredentialDaoImplTests.createAndPersistIdentity(null);
        User user = UserDaoImplTests.createAndPersistUser(null);
        user.setIdentity(id1);
        Credential credential = CredentialDaoImplTests.createAndPersistCredential(null);
        credential.setIdentity(id2);
        // credential and user do not share a common identity
        // FIXME
//        try {
//            new UserDetailsAdapter(user, credential); fail();
//        } catch (IllegalArgumentException e) { 
//        } catch (Exception e) { fail(); }
        // but now do
        try {
            user.setIdentity(credential.getIdentity());
            new UserDetailsAdapter(user, credential); 
        } catch (Exception e) { fail(); }
        try {
            new UserDetailsAdapter(null, credential); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
            new UserDetailsAdapter(user, null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testAbstractUserDetailsAndInterfaces() {
        User user = UserDaoImplTests.createAndPersistUser(null);
        Credential credential = CredentialDaoImplTests.createAndPersistCredential(null);
        user.setIdentity(credential.getIdentity());
        UserDetails userDetails = new UserDetailsAdapter(user, credential);
        assertSame(user, ((PublicUserDetails) userDetails).getUser());
        assertSame(credential, ((SecureUserDetails) userDetails).getCredential());
        assertEquals(userDetails.getPassword(), credential.getPassword());
        assertEquals(userDetails.getUsername(), user.getIdentity().getPrincipal());
        assertEquals(userDetails.isAccountNonExpired(), user.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonExpired());
        assertEquals(userDetails.isAccountNonLocked(), user.isAccountNonLocked());
        assertTrue(userDetails.isAccountNonLocked());
        if (credential.getExpired()==null) {
            assertTrue(userDetails.isCredentialsNonExpired());
        } else {
            assertFalse(userDetails.isCredentialsNonExpired());
        }
        if (credential.getCredentialState()==CredentialState.ACTIVE.getValue() || 
                credential.getCredentialState()==CredentialState.IDLE.getValue()) {
            assertTrue(userDetails.isEnabled());
        } else {
            assertFalse(userDetails.isEnabled());
        }
    }
    
    public void testConvertUserRoleToString() {
        User user = UserDaoImplTests.createAndPersistUser(null);
        Credential credential = CredentialDaoImplTests.createAndPersistCredential(null);
        user.setIdentity(credential.getIdentity());
        UserRole userRole = ServiceDaoImplTests.createAndPersistUserRole(null);
        String conv = "ROLE_"+userRole.getService().getServiceName()+"_"+userRole.getServiceExtension();
        assertEquals(conv, new UserDetailsAdapter(user, credential).convertUserRoleToString(userRole));
    }
    
    public void testGrantedAuthorities() {
        int e = 2, i = 3, s = 4, r = 2;
        List<UserRole> userRoleList = ServiceDaoImplTests.createUserRoleList(e, i, s, r);
        User user = userRoleList.get((int) Math.random()*e*i*s*r).getUser();
        Credential credential = CredentialDaoImplTests.createAndPersistCredential(null);
        user.setIdentity(credential.getIdentity());
        UserDetailsAdapter userDetails = new UserDetailsAdapter(user, credential);
        GrantedAuthority[] authorities = userDetails.getAuthorities();
        assertEquals(s*r, authorities.length);
        List<GrantedAuthority> list = Arrays.asList(authorities);
        for (UserRole ur: user.getRoles()) {
            String roleName = userDetails.convertUserRoleToString(ur);
            assertTrue(list.contains(new GrantedAuthorityImpl(roleName)));
        }
    }
    
    public void testPublicUserDetailsSwitcher() {
        int e = 3, i = 4;
        List<User> userList = UserDaoImplTests.createUsers(e, i);
        assertEquals(e*i, userList.size());
        User user = userList.get((int) Math.random()*e*i);
        assertEquals(e, user.getIdentity().getUsers().size());
        Credential credential = CredentialDaoImplTests.createAndPersistCredential(null);
        credential.setIdentity(user.getIdentity());
        PublicUserDetailsSwitcher userDetails = new UserDetailsAdapter(user, credential);
        assertEquals(e, userDetails.getUsers().size());
        for (User u: userDetails.getUsers()) {
            assertTrue(user.getIdentity().getUsers().contains(u));
        }
        // take another user
        User newUser = null;
        for (User u: userDetails.getUsers()) {
            if (!u.equals(user)) {
                newUser = u;
                break;
            }
        }
        userDetails.setCurrentUser(newUser);
        assertSame(newUser, userDetails.getUser());
    }
    
}
