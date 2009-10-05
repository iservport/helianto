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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsAdapterTests {
    
//	@Test(expected=Exception.class)
//    public void userDetailsAdapterError1() {
//        Identity id1 = IdentityTestSupport.createIdentity();
//        Identity id2 = IdentityTestSupport.createIdentity();
//        User user = UserTestSupport.createUser();
//        user.setIdentity(id1);
//        Credential credential = CredentialTestSupport.createCredential();
//        credential.setIdentity(id2);
//        // credential and user do not share a common identity
//        // FIXME
////        try {
////            new UserDetailsAdapter(user, credential); fail();
////        } catch (IllegalArgumentException e) { 
////        } catch (Exception e) { fail(); }
//        // but now do
//        user.setIdentity(credential.getIdentity());
//        new UserDetailsAdapter(user, credential); 
//    }

	@Test(expected=Exception.class)
    public void userDetailsAdapterError2() {
        Identity id1 = IdentityTestSupport.createIdentity();
        Identity id2 = IdentityTestSupport.createIdentity();
        User user = UserTestSupport.createUser();
        user.setIdentity(id1);
        Credential credential = CredentialTestSupport.createCredential();
        credential.setIdentity(id2);
        new UserDetailsAdapter(user, null);
    }

	@Test
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
        assertFalse(userDetails.isAccountNonExpired());
    }
    

    public void userDetailsLock() {
        // TODO
//        assertEquals(userDetails.isAccountNonLocked(), user.isAccountNonLocked());
//        assertTrue(userDetails.isAccountNonLocked());
    }
    
    public void userDetailsEnabled() {
        // TODO
//        if (credential.getCredentialState()==ActivityState.ACTIVE.getValue() || 
//                credential.getCredentialState()==ActivityState.IDLE.getValue()) {
//            assertTrue(userDetails.isEnabled());
//        } else {
//            assertFalse(userDetails.isEnabled());
//        }
    }
    
}
