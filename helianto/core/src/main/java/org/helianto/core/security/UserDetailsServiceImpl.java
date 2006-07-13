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

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.springframework.dao.DataRetrievalFailureException;

/**
 * Custom implementation for the {@link org.acegisecurity.userdetails.UserDetailsService}
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public class UserDetailsServiceImpl extends AbstractUserDetailsServiceTemplate {
    
    private IdentityResolutionStrategy identityResolutionStrategy;
    
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
        User user = null;
        UserLog userLog = securityMgr.findLastUserLog(identity);
        if (userLog==null) {
            if (logger.isDebugEnabled()) {
                logger.debug("First login");
            }
            user = guessOrCreateUser(identity);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Last login "+userLog.getLastLogin());
            }
            user = userLog.getUser();
        }
        securityMgr.persistUserLog(user, new Date());
        return user;
    }
    
    /**
     * Take the first available matching <code>User</code>.
     * 
     */
    final User guessOrCreateUser(Identity identity) {
        for (User u: identity.getUsers()) {
            if (logger.isDebugEnabled()) {
                logger.debug("First available user from identity "+identity+" selected");
            }
            return u;
        }
        if (securityMgr.isAutoCreateEnabled()) {
            return securityMgr.autoCreateUser(identity);
        }
        throw new UsernameNotFoundException("No User defined for Credential with principal: ");
    }
    
    private final Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);

    public void setIdentityResolutionStrategy(
            IdentityResolutionStrategy identityResolutionStrategy) {
        this.identityResolutionStrategy = identityResolutionStrategy;
    }

}

