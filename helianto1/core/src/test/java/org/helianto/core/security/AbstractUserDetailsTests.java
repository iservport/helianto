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

import junit.framework.TestCase;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.test.SecurityTestSupport;

public class AbstractUserDetailsTests extends TestCase {
    

    public void testUserDetails() {
        UserDetails details = SecurityTestSupport.createUserDetailsAdapter();
        details.getUsername();
    }
    
    // inner stub class
    
    @SuppressWarnings("serial")
    public class UserDetailsStub extends AbstractUserDetails {

        public UserDetailsStub(User user, Credential cred) {
            super(user, cred);
            // TODO Auto-generated constructor stub
        }

        public GrantedAuthority[] getAuthorities() {
            // TODO Auto-generated method stub
            return null;
        }
        
    }

}
