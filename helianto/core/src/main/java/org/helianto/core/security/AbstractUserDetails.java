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
import org.helianto.core.CredentialState;
import org.helianto.core.User;

import org.acegisecurity.userdetails.UserDetails;

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
    
    protected User user;

    public void setUser(User user) {
        if (user==null) {
            throw new IllegalArgumentException("Cannot set a null user");
        }
        this.user = user;
    }
    
    private AbstractUserDetails() {}

    /**
     * Minimal constructor.
     * @param credential A valid credential.
     */
    public AbstractUserDetails(Credential credential) {
        user = new User();
        user.setCredential(credential);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
    }
    
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    public boolean isCredentialsNonExpired() {
        //TODO implement control over expiration date
        if (user.getCredential().getExpired()==null) {
            return true;
        } 
        return true;
    }

    public boolean isEnabled() {
        char state = user.getCredential().getCredentialState();
        if (state==CredentialState.ACTIVE.getValue() | 
                state==CredentialState.IDLE.getValue()) {
            return true;
        }
        return false;
    }

    public String getPassword() {
        return user.getCredential().getPassword();
     }

    public String getUsername() {
        return user.getCredential().getPrincipal();
    }
    
}
