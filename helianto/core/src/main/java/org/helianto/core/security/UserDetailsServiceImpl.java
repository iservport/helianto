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
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserDao;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

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
 * @version $Id$
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);
    
    private UserDao userDao;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Take the first available matching <code>User</code>.
     */
    final User guessUser(String principal) {
        Credential credential = userDao.findCredentialByPrincipal(principal);
        if (credential==null) {
            throw new UsernameNotFoundException("No Credential with principal: "+principal);
        } else {
            for (User u: credential.getUsers()) {
                return u;
            }
            User auto = userDao.autoCreateAndPersistUser(principal);
            if (auto != null) {
                return auto;
            }
        }
        throw new UsernameNotFoundException("No User defined for Credential with principal: "+principal);
    }
    
    /**
     * Implements {@link org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)}
     * to provide {@link org.acegisecurity.userdetails.UserDetails} as an adapter.
     * 
     * <p>If there is a matching <code>Credential</code> to the supplied <code>username</code>,
     * the method finds the last created <code>UserLog</code>, if exists. If not, it guesses an
     * <code>User</code> (from users having the same credential) to create a new <code>UserLog</code>.
     * In both cases, the resulting <code>UserLog</code> is passed as a constructor parameter to a new  
     * <code>UserDetailsAdapter</code>.
     */
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Username is "+username);
        }
        UserLog userLog = null;
        try {
            userLog = userDao.findLastUserLog(username);
            if (userLog==null) {
                userLog = userDao.createAndPersistUserLog(guessUser(username));
            } 
            return new UserDetailsAdapter(userLog);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         thrown from "+e.toString());
            }
            throw new DataRetrievalFailureException("Username: "+username);
        }
    }
    
}

