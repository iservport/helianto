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
import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.creation.CredentialState;
import org.springframework.util.Assert;

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
    
    private User user;
    
    private Credential credential;

    /**
     * Minimal constructor.
     */
    public AbstractUserDetails(User user, Credential credential) {
        Assert.notNull(user, "Required to create UserDetails");
        Assert.notNull(credential, "Required to create UserDetails");
        this.user = user;
        this.credential = credential;
    }
    
    /**
     * Static method to retrieve the <code>UserAdapter</code>
     * instance held in the <code>SecurityContext</code>.
     */
    public static PublicUserDetails retrievePublicUserDetailsFromSecurityContext() {
        PublicUserDetails pud = (PublicUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        if (logger.isDebugEnabled()) {
            logger.debug("PublicUserDetails retrieved from security context");
        }
        return pud;
    }
    
    public boolean isAccountNonExpired() {
        // TODO calculate account (User) expiration
        return user.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        // TODO calculate account (User) expiration
        return user.isAccountNonLocked();
    }

    public boolean isCredentialsNonExpired() {
        //TODO implement control over expiration date
        if (credential.getExpired()==null) {
            return true;
        } 
        return true;
    }

    public boolean isEnabled() {
        char state = credential.getCredentialState();
        if (state==CredentialState.ACTIVE.getValue() | 
                state==CredentialState.IDLE.getValue()) {
            return true;
        }
        return false;
    }

    public String getPassword() {
        return credential.getPassword();
     }

    public String getUsername() {
        return user.getIdentity().getPrincipal();
    }
    
    public User getUser() {
        return user;
    }
    
    protected void setUser(User user) {
        this.user = user;
    }
    
    private AbstractUserDetails() {
        throw new IllegalArgumentException("AbstractUserDetails subclasses must take an " +
                "User instance as constructor parameter");
    }

    static final Log logger = LogFactory.getLog(AbstractUserDetails.class);
    
}
