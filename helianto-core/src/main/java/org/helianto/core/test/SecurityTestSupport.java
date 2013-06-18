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

import java.util.HashSet;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

/**
 * Security related test support.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SecurityTestSupport {
    
    public static Authentication createAuthentication() {
    	return createAuthentication(new User(new Entity(), new Identity("p")));
    }
    
    public static Authentication createAuthentication(User user) {
        IdentitySecurity identitySecurity = new IdentitySecurity(user.getIdentity(), "");
        UserDetailsAdapter secureUser = new UserDetailsAdapter(user, identitySecurity, new HashSet<UserRole>());
    	return new LocalTestingAuthenticationToken(secureUser);
    }
    
    public static UserDetailsAdapter createUserDetailsAdapter() {
        return createUserDetailsAdapter(new User(new Entity(), new Identity("p")));
    }

    public static UserDetailsAdapter createUserDetailsAdapter(User user) {
        Authentication authentication = createAuthentication(user);
        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        return (UserDetailsAdapter) authentication.getPrincipal();
    }    

}
