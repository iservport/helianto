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

import java.util.List;

import junit.framework.TestCase;

import org.acegisecurity.userdetails.UserDetails;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.test.AuthorizationTestSupport;
import org.helianto.core.test.CredentialTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsServiceTemplateTests extends TestCase {

    public void testLoadUserByUsername() {
        UserDetails userDetails = new UserDetailsServiceStub().loadUserByUsername("TEST");
        assertSame(user, ((PublicUserDetails) userDetails).getUser());
        assertSame(credential, ((SecureUserDetails) userDetails).getCredential());
    }
    
    List<User> userList = AuthorizationTestSupport.createUserList(1, 1);
    User user = userList.get(0);
    Credential credential = CredentialTestSupport.createCredential();
    Identity identity = userList.get(0).getIdentity();

    public class UserDetailsServiceStub extends AbstractUserDetailsServiceTemplate {
        
        @Override
        public Identity loadAndValidateIdentity(String principal) {
            return identity;
        }

        @Override
        public Credential loadAndValidateCredential(Identity identity) {
            credential.setIdentity(identity);
            return credential;
        }

        @Override
        public User loadOrCreateUser(Identity identity) {
            return user;
        }
        
    }
    
}
