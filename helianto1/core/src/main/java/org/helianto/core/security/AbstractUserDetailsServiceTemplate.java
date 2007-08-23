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

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.service.SecurityMgr;
import org.springframework.beans.factory.annotation.Required;
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
 */
public abstract class AbstractUserDetailsServiceTemplate implements UserDetailsService {

    protected SecurityMgr securityMgr;
    
    /**
     * Implements {@link org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)}
     * to provide {@link org.acegisecurity.userdetails.UserDetails} as an adapter.
     * 
     * <p>It is a six step process: (1) load an <code>Identity<code>, (2) load a <code>Credential<code>,
     * (3) list a compatible <code>User</code>s, (4) select (or create) a <code>User</code> from the list, 
     * (5) create an adapter instance which both takes the selected <code>User</code> and satisfies 
     * <code>UserDetails</code>, and, optionally, (6) log the <code>User</code> event.</p>
     */
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Attempting login with username "+username);
        }
        Identity identity = loadAndValidateIdentity(username);
        if (logger.isDebugEnabled()) {
            logger.debug("Step 1 successful: Identity principal is "+identity.getPrincipal());
        }
        Credential credential = loadAndValidateCredential(identity);
        if (logger.isDebugEnabled()) {
            logger.debug("Step 2 successful: Identity has a valid Credential");
        }
        List<User> userList = loadUsers(identity);
        if (logger.isDebugEnabled()) {
            logger.debug("Step 3 successful: User list is loaded");
        }
        User user = null;
        if (!userList.isEmpty()) {
            user = selectUser(userList);
            if (logger.isDebugEnabled()) {
                logger.debug("Step 4 successful: User is selected");
            }
        }
        else {
            user = createUser(identity);
            if (logger.isDebugEnabled()) {
                logger.debug("Step 4 successful: User is created");
            }
        }
        if (user==null) {
            throw new UsernameNotFoundException("Not a valid username: "+username);
        }
        UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(user, credential);
        if (logger.isDebugEnabled()) {
            logger.debug("Step 5 successful: User details instance is prepared");
        }
        logUser(user);
        if (logger.isDebugEnabled()) {
            logger.debug("Step 6 successful: User log created");
        }
        return userDetailsAdapter;
    }
    
    /**
     * Load and validate an <code>Identity</code>
     * 
     * @param principal
     */
    public abstract Identity loadAndValidateIdentity(String principal);

    /**
     * Load and validate a <code>Credential</code>
     * 
     * @param identity
     */
    public abstract Credential loadAndValidateCredential(Identity identity);
    
    /**
     * Load <code>User</code>s sharing the same <code>Identity</code>
     * 
     * @param identity
     */
    public abstract List<User> loadUsers(Identity identity);
    
    /**
     * Select an <code>User</code> from the list
     * 
     * @param userList
     */
    public abstract User selectUser(List<User> userList);
    
    /**
     * Create a new <code>User</code>
     * 
     * @param identity
     */
    public abstract User createUser(Identity identity);
    
    /**
     * Write a log on the <code>User</code> event
     * 
     * @param user
     */
    public abstract void logUser(User user);
    
    //- collabs
    
    @Required
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }

    private final Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);

}
