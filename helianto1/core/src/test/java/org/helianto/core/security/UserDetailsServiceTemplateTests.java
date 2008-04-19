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

import org.springframework.security.userdetails.UserDetails;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.UserTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsServiceTemplateTests extends TestCase {

    public void testLoadUserByUsernameSuccess() {
        UserDetails userDetails = new UserDetailsServiceStub().loadUserByUsername("TEST");
        assertSame(selectedUser, ((PublicUserDetails) userDetails).getUser());
        assertSame(loadedCredential, ((SecureUserDetails) userDetails).getCredential());
    }
    
    // domain objects
    
    List<User> loadedUsers;
    User selectedUser, createdUser;
    Credential loadedCredential;
    Identity loadedIdentity;
    boolean create = false;

    @Override
    public void setUp() {
    	List<Entity> entityList = EntityTestSupport.createEntityList(3);
    	List<Identity> identityList = IdentityTestSupport.createIdentityList(1);
        loadedIdentity = identityList.get(0);
        loadedUsers = UserTestSupport.createUserList(entityList, identityList);
        selectedUser = loadedUsers.get(0);
        createdUser = UserTestSupport.createUser();
        loadedCredential = CredentialTestSupport.createCredential(loadedIdentity);
    }
    
    public class UserDetailsServiceStub extends AbstractUserDetailsServiceTemplate {

		@Override
		public Identity loadAndValidateIdentity(String principal) {
			assertEquals("TEST", principal);
			return loadedIdentity;
		}

		@Override
		public Credential loadAndValidateCredential(Identity identity) {
			assertSame(loadedIdentity, identity);
			return loadedCredential;
		}

		@Override
		public List<User> loadUsers(Identity identity) {
			assertSame(loadedIdentity, identity);
			return loadedUsers;
		}

		@Override
		public User selectUser(List<User> users) {
			assertSame(loadedUsers, users);
			return selectedUser;
		}
        
		@Override
		public User createUser(Identity identity) {
			assertSame(loadedIdentity, identity);
			return createdUser;
		}

		@Override
		public void logUser(User user) {
			if (create) {
				assertSame(createdUser, user);
			}
			else {
				assertSame(selectedUser, user);
			}
		}

    }
    
}
