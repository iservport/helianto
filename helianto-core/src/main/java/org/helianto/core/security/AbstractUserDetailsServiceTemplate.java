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

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

    /**
     * Implements {@link org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)}
     * to provide {@link org.acegisecurity.userdetails.UserDetails} as an adapter.
     * 
     * <p>It is a five step process: (1) load an <code>Identity<code>, (2) load a <code>Credential<code>,
     * (3) list a compatible <code>User</code>s, (4) select (or create) a <code>User</code> from the list, 
     * (5) create an adapter instance which both takes the selected <code>User</code> and satisfies 
     * <code>UserDetails</code>.</p>
     */
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.debug("Attempting login with username {}", username);
        // first, search identities having username as principal
        Identity identity = loadAndValidateIdentity(username);
        logger.debug("Step 1 successful: Identity principal is {}", identity.getPrincipal());
        // then see if there is a credential for that identity
        Credential credential = loadAndValidateCredential(identity);
        logger.debug("Step 2 successful: Identity has a valid Credential");
        // and users sharing that identity
        List<UserGroup> userList = listUsers(identity);
        logger.debug("Step 3 successful: User list is loaded with {} item(s).", userList.size());
        // what user will be selected? the last visitor
        User user = null;
        if (userList!=null && !userList.isEmpty()) {
            user = selectUser(userList);
            logger.debug("Step 4 successful: User selected as '{}' for entity '{}'", user.getIdentity().getIdentityName(), user.getEntity().getAlias());
        }
        if (user==null) {
            logger.debug("Step 4 unsuccessful: User not selected");
            throw new UsernameNotFoundException("Not a valid username: "+username);
        }
        // store user
        User managedUser = storeUser(user);
        // create the adapter
        UserDetails userDetailsAdapter = createUserDetails(managedUser, credential);
        logger.debug("Step 5 successful: User details instance is prepared: USER IS SUCCESSFULLY LOADED");
        return userDetailsAdapter;
    }
    
    /**
     * Hook to load and validate an <code>Identity</code>
     * 
     * @param principal
     */
    public abstract Identity loadAndValidateIdentity(String principal);

    /**
     * Hook to load and validate a <code>Credential</code>
     * 
     * @param identity
     */
    public abstract Credential loadAndValidateCredential(Identity identity);
    
    /**
     * Hook to load a user list
     * 
     * @param identity
     */
    public abstract List<UserGroup> listUsers(Identity identity);
    
    /**
     * Hook to store any change to the selected user
     * 
     * @param identity
     */
    public abstract User storeUser(User user);
    
    /**
     * Select an <code>User</code> from the list
     * 
     * @param userList
     */
    protected User selectUser(List<UserGroup> userList) {
        for (UserGroup userGroup: userList) {
        	if (userGroup instanceof User) {
                return (User) userGroup;
        	}
        }
        return null;
    }
    
    /**
     * Create <code>UserDetails</code> instance..
     * 
     * @param user
     * @param credential
     */
    protected abstract UserDetails createUserDetails(User user, Credential credential);
    
     private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

}
