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

import java.util.Date;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.springframework.beans.factory.annotation.Required;

/**
 * Custom implementation for the {@link org.acegisecurity.userdetails.UserDetailsService}
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsServiceImpl extends AbstractUserDetailsServiceTemplate {
    
    private IdentityResolutionStrategy identityResolutionStrategy;
    private UserResolutionStrategy userResolutionStrategy;
    
    @Override
    public Identity loadAndValidateIdentity(String principal) {
        return identityResolutionStrategy.loadAndValidateIdentity(principal);
    }
    
    @Override
    public Credential loadAndValidateCredential(Identity identity) {
        return identityResolutionStrategy.loadAndValidateCredential(identity);
    }
    
    @Override
    public List<User> loadUsers(Identity identity) {
        return userResolutionStrategy.loadUsers(identity);
    }
    
    @Override
    public User selectUser(List<User> userList) {
        User user = userResolutionStrategy.selectUserFromPreviousLogin(userList);
        if (user==null) {
        	user = userResolutionStrategy.selectUserIfAny(userList);
        }
        return user;
    }
    
    @Override
    public User createUser(Identity identity) {
        return userResolutionStrategy.createUser(identity);
    }
    
    @Override
    public void logUser(User user) {
    	securityMgr.writeUserLog(user, new Date());
    }
    
    //- collabs
    
    @Required
    public void setIdentityResolutionStrategy(
            IdentityResolutionStrategy identityResolutionStrategy) {
        this.identityResolutionStrategy = identityResolutionStrategy;
    }

    @Required
    public void setUserResolutionStrategy(
            UserResolutionStrategy userResolutionStrategy) {
        this.userResolutionStrategy = userResolutionStrategy;
    }

}

