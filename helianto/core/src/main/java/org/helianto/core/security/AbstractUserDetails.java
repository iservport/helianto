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

import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.CredentialState;
import org.helianto.core.UserLog;

/**
 * A base class to implement {@link org.acegisecurity.userdetails.UserDetails}
 * from a supplied user domain object.
 * 
 * <p>
 * Helianto domain model allows for a two step authorization. Sub-classes
 * must implement the <code>getAuthorities()</code> methods accordingly.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractUserDetails implements UserDetails {
    
    static final Log logger = LogFactory.getLog(AbstractUserDetails.class);
    
    protected UserLog userLog;

    public AbstractUserDetails() {
        throw new IllegalArgumentException("AbstractUserDetails subclasses must take an " +
                "User instance as constructor parameter");
    }

    /**
     * Minimal constructor.
     */
    public AbstractUserDetails(UserLog userLog) {
        if (userLog==null) {
            throw new IllegalArgumentException("Cannot set a null userLog");
        }
        this.userLog = userLog;
    }
    
    /**
     * Static method to retrieve the <code>UserAdapter</code>
     * instance held in the <code>SecurityContext</code>.
     */
    public static PublicUserDetails retrievePublicUserDetailsFromSecurityContext() {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Retriving public user details ...");
        }
        PublicUserDetails pud = (PublicUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Done.");
        }
        return pud;
    }
    
    public boolean isAccountNonExpired() {
        return userLog.getUser().isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        return userLog.getUser().isAccountNonLocked();
    }

    public boolean isCredentialsNonExpired() {
        //TODO implement control over expiration date
        if (userLog.getUser().getCredential().getExpired()==null) {
            return true;
        } 
        return true;
    }

    public boolean isEnabled() {
        char state = userLog.getUser().getCredential().getCredentialState();
        if (state==CredentialState.ACTIVE.getValue() | 
                state==CredentialState.IDLE.getValue()) {
            return true;
        }
        return false;
    }

    public String getPassword() {
        return userLog.getUser().getCredential().getPassword();
     }

    public String getUsername() {
        return userLog.getUser().getCredential().getPrincipal();
    }
    
}
