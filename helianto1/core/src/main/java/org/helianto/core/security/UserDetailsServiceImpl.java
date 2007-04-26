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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataRetrievalFailureException;

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
        try {
            //TODO find only active credential
            Credential credential = securityMgr.findCredentialByIdentity(identity);
            if (credential!=null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("User credential loaded");
                }
                return credential;
            } else {
                throw new DataRetrievalFailureException("Bad credential");
            }
        } catch (Exception e) {
            throw new DataRetrievalFailureException("General login failure", e);
        }
    }
    
    @Override
    public User loadOrCreateUser(Identity identity) {
        return userResolutionStrategy.loadOrCreateUser(identity);
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

    private final Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);

}

