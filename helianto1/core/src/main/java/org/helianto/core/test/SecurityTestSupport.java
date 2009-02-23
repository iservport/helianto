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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.security.UserDetailsAdapter;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.context.SecurityContextImpl;
import org.springframework.security.providers.TestingAuthenticationToken;

/**
 * Security related test support.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SecurityTestSupport {
    
    public static UserDetailsAdapter createUserDetailsAdapter() {
        User user = UserTestSupport.createUser();
        return createUserDetailsAdapter(user);
    }

    public static UserDetailsAdapter createUserDetailsAdapter(User user) {
        List<User> users = new ArrayList<User>();
        users.add(user);
        Credential credential = CredentialTestSupport.createCredential(user.getIdentity());
        UserDetailsAdapter secureUser = new UserDetailsAdapter(user, credential);
//        SecurityContext securityContext = new SecurityContextImpl();
//        Authentication authentication = new TestingAuthenticationToken(secureUser, null, null);
//        securityContext.setAuthentication(authentication);
//        SecurityContextHolder.setContext(securityContext);
        return secureUser;
    }

}
