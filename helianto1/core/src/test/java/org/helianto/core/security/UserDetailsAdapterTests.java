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
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.hibernate.AuthenticationDaoImplTests;
import org.helianto.core.test.AuthorizationTestSupport;
import org.helianto.core.test.UserRoleTestSupport;

public class UserDetailsAdapterTests extends TestCase {
    
    public void testUserDetailsAdapterError() {
        Identity id1 = AuthenticationDaoImplTests.createAndPersistIdentity(null);
        Identity id2 = AuthenticationDaoImplTests.createAndPersistIdentity(null);
        User user = AuthorizationTestSupport.createUser();
        user.setIdentity(id1);
        Credential credential = AuthenticationDaoImplTests.createAndPersistCredential(null);
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
        User user = AuthorizationTestSupport.createUser();
        Credential credential = AuthenticationDaoImplTests.createAndPersistCredential(null);
        user.setIdentity(credential.getIdentity());
        UserDetails userDetails = new UserDetailsAdapter(user, credential);
        assertSame(user, ((PublicUserDetails) userDetails).getUser());
        assertSame(credential, ((SecureUserDetails) userDetails).getCredential());
        assertEquals(userDetails.getPassword(), credential.getPassword());
        assertEquals(userDetails.getUsername(), user.getIdentity().getPrincipal());
        assertEquals(userDetails.isAccountNonExpired(), user.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonExpired());
        if (credential.getExpired()==null) {
            assertTrue(userDetails.isCredentialsNonExpired());
        } else {
            assertFalse(userDetails.isCredentialsNonExpired());
        }
    }
    
    public void testUserDetailsLock() {
        // TODO
//        assertEquals(userDetails.isAccountNonLocked(), user.isAccountNonLocked());
//        assertTrue(userDetails.isAccountNonLocked());
    }
    
    public void testUserDetailsEnabled() {
        // TODO
//        if (credential.getCredentialState()==ActivityState.ACTIVE.getValue() || 
//                credential.getCredentialState()==ActivityState.IDLE.getValue()) {
//            assertTrue(userDetails.isEnabled());
//        } else {
//            assertFalse(userDetails.isEnabled());
//        }
    }
    
    public void testConvertUserRoleToString() {
        User user = AuthorizationTestSupport.createUser();
        Credential credential = AuthenticationDaoImplTests.createAndPersistCredential(null);
        user.setIdentity(credential.getIdentity());
        UserRole userRole = UserRoleTestSupport.createUserRole();
        String conv = "ROLE_"+userRole.getService().getServiceName()+"_"+userRole.getServiceExtension();
        assertEquals(conv, new UserDetailsAdapter(user, credential).convertUserRoleToString(userRole));
    }
    
    //
    
    public void testGrantedAuthorities() {
        int i = 4, g = 4, s = 3;
        List<UserRole> userRoleList = UserRoleTestSupport.createUserRoleList(i, g, s);
        UserGroup userGroup = userRoleList.get((int) Math.random()*userRoleList.size()).getUserGroup();
        System.out.println(userGroup);
        User user = AuthorizationTestSupport.createUser();
        AuthorizationCreator.createUserAssociation(userGroup, user);
        Credential credential = AuthenticationDaoImplTests.createAndPersistCredential(null);
        user.setIdentity(credential.getIdentity());
        UserDetailsAdapter userDetails = new UserDetailsAdapter(user, credential);
        GrantedAuthority[] authorities = userDetails.getAuthorities();
        assertEquals(i*s, authorities.length);
        List<GrantedAuthority> list = Arrays.asList(authorities);
        for (UserRole ur: user.getRoles()) {
            String roleName = userDetails.convertUserRoleToString(ur);
            assertTrue(list.contains(new GrantedAuthorityImpl(roleName)));
        }
    }
    
    public void testPublicUserDetailsSwitcher() {
        int e = 3, d = 4;
        List<User> userList = AuthorizationTestSupport.createUserList(e, d);
        assertEquals(e*d, userList.size());
        User user = userList.get((int) Math.random()*userList.size());
        assertEquals(e, user.getIdentity().getUsers().size());
        Credential credential = AuthenticationDaoImplTests.createAndPersistCredential(null);
        credential.setIdentity(user.getIdentity());
        PublicUserDetailsSwitcher userDetails = new UserDetailsAdapter(user, credential);
        assertEquals(e, userDetails.getUsers().size());
        for (UserGroup u: userDetails.getUsers()) {
            assertTrue(user.getIdentity().getUsers().contains(u));
        }
        // take another user
        UserGroup newUser = null;
        for (UserGroup u: userDetails.getUsers()) {
            if (!u.equals(user)) {
                newUser = u;
                break;
            }
        }
        userDetails.setCurrentUser((User) newUser);
        assertSame(newUser, userDetails.getUser());
    }
    
}
