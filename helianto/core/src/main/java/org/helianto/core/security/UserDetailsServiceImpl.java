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

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.service.SecurityMgr;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.Assert;

/**
 * Custom implementation for the {@link org.acegisecurity.userdetails.UserDetailsService}
 * interface.
 * 
 * <p>
 * The method {@link #loadUserByUsername(String)} returns a 
 * <code>UserLog</code> wrapped in an <code>UserAdpater</code>
 * instance.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private SecurityMgr securityMgr;
    
    /**
     * Implements {@link org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)}
     * to provide {@link org.acegisecurity.userdetails.UserDetails} as an adapter.
     * 
     * <p>It is a four step process: (1) load an <code>Identity<code>, (2) load a <code>Credential<code>,
     * (3) find a compatible <code>User</code> and (4) create an adapter instance which both takes the 
     * <code>User</code> and satisfies <code>UserDetails</code>.</p>
     */
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Attempt login with username "+username);
        }
        Identity identity = loadAndValidateIdentity(username);
        Credential credential = loadAndValidateCredential(identity);
        User user = loadOrCreateUser(identity);
        return new UserDetailsAdapter(user, credential);
    }
    
    /**
     * Load and validate an <code>Identity</code>
     * 
     * @param principal
     * @return
     */
    public Identity loadAndValidateIdentity(String principal) {
        Identity identity = null;
        try {
            identity = securityMgr.findIdentityByPrincipal(principal);
            Assert.notNull(identity, "Null Identity");
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username "+principal, e);
        }
        return identity;
    }
    
    /**
     * Load and validate a <code>Credential</code>
     * 
     * @param identity
     * @return
     */
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
    
    /**
     * Load or create an <code>User</code>
     * 
     * @param identity
     * @return
     */
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

	public void setSecurityMgr(SecurityMgr securityMgr) {
		this.securityMgr = securityMgr;
	}
    
}

