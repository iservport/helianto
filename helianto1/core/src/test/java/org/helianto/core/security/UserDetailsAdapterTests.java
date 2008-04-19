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

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.SecurityTestSupport;
import org.helianto.core.test.UserRoleTestSupport;
import org.helianto.core.test.UserTestSupport;

public class UserDetailsAdapterTests extends TestCase {
    
    public void testUserDetailsAdapterError() {
        Identity id1 = IdentityTestSupport.createIdentity();
        Identity id2 = IdentityTestSupport.createIdentity();
        User user = UserTestSupport.createUser();
        user.setIdentity(id1);
        Credential credential = CredentialTestSupport.createCredential();
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
        User user = UserTestSupport.createUser();
        Credential credential = CredentialTestSupport.createCredential();
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
        User user = UserTestSupport.createUser();
        Credential credential = CredentialTestSupport.createCredential();
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
        User user = UserTestSupport.createUser();
        UserAssociation.userAssociationFactory(userGroup, user);
        Credential credential = CredentialTestSupport.createCredential();
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
    	// 3 entities, same identity
        List<User> userList = UserTestSupport.createUserList(3, 1);
        PublicUserDetailsSwitcher userDetails = SecurityTestSupport.createUserDetailsAdapter(userList);
        
        assertSame(userList.get(0), userDetails.getUser());

        User user = userList.get((int) (1 + (Math.random()*userList.size()) - 1));
        userDetails.selectUser(user);
        assertSame(user, userDetails.getUser());
    }
    
}
