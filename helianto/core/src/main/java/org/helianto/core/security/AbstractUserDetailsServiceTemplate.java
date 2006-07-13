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

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.service.SecurityMgr;
import org.springframework.dao.DataAccessException;

/**
 * A basic template for the {@link org.acegisecurity.userdetails.UserDetailsService}
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
public abstract class AbstractUserDetailsServiceTemplate implements UserDetailsService {

    protected SecurityMgr securityMgr;
    
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
    public abstract Identity loadAndValidateIdentity(String principal);

    /**
     * Load and validate a <code>Credential</code>
     * 
     * @param identity
     * @return
     */
    public abstract Credential loadAndValidateCredential(Identity identity);
    
    /**
     * Load or create an <code>User</code>
     * 
     * @param identity
     * @return
     */
    public abstract User loadOrCreateUser(Identity identity);
    
    private final Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);

    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }

}
