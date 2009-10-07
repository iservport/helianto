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

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * Models core user information retieved by
 * {@link org.acegisecurity.userdetails.UserDetailsService} as an adapter class
 * to keep coupling from the Acegi-security package as narrow as possible.
 * 
 * <p>
 * A new <code>UserAdapter</code> must be created from a single
 * {@link org.helianto.core.User}. As a group of <code>User</code>s may be
 * connected by a common <code>Credential</code> it is desirable to switch
 * <code>User</code>s inside the group at runtime as no new authentication is
 * be required (same credential). This is is a design choice made to allow
 * multiple entities to share the same set of credentials and still keep
 * individual sets of authorities.
 * </p>
 * 
 * <p>
 * Service layer code should retrieve <code>UserAdapter</code> through the
 * {@link org.helianto.core.security.PublicUserDetails} or
 * {@link org.helianto.core.security.PublicUserDetailsSwitcher} interfaces to
 * prevent unintended access to the embedded <code>User</code> and
 * <code>Credential</code> instances. A convenient static methods
 * {@link org.helianto.core.security.AbstractUserAdapter#retrievePublicUserDetailsFromSecurityContext}
 * and
 * {@link org.helianto.core.security.AbstractUserAdapter#retrievePublicUserDetailsSwitcherFromSecurityContext}
 * are supplied to perform this task. <code>PublicUserDetailsSwitcher</code>
 * provides a method to switch the current <code>User</code> based on an
 * <code>Entity</code> selection.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 * @see org.acegisecurity.providers.dao.User
 */
public class UserDetailsAdapter implements
        Serializable, UserDetails, PublicUserDetails, SecureUserDetails {

	private static final long serialVersionUID = 1L;
    private User user;
    private Credential credential;
    // TODO private List<GrantedAuthority> authorities;
    private GrantedAuthority[] authorities;

    /**
     * Default constructor
     */
    UserDetailsAdapter() {}
    
    /**
     * User constructor.
     */
    public UserDetailsAdapter(User user, Credential credential) {
    	if (credential==null) {
    		throw new IllegalArgumentException("Invalid credential");
    	}
        this.user = user;
        this.credential = credential;
    }
    
    /**
     * Static method to retrieve the <code>UserAdapter</code>
     * instance held in the <code>SecurityContext</code>.
     */
    public static PublicUserDetails retrievePublicUserDetailsFromSecurityContext() {
    	try {
    		SecurityContext context = SecurityContextHolder.getContext();
    		if (context==null) {
        		logger.warn("Security context not available.");
    			return null;
    		}
    		Authentication authentication = context.getAuthentication();
    		if (authentication==null) {
        		logger.warn("Authentication not available.");
    			return null;
    		}
        	Object userDetails = authentication.getPrincipal();
        	if (userDetails instanceof PublicUserDetails) {
                PublicUserDetails pud = (PublicUserDetails) userDetails;  
                if (logger.isDebugEnabled()) {
                    logger.debug("PublicUserDetails retrieved from security context");
                }
                return pud;
        	}
    	} 
    	catch (Exception e) {
    		logger.warn("Error in security context ", e);
    	}
        if (logger.isDebugEnabled()) {
            logger.debug("PublicUserDetails not found!");
        }
    	return null;
    }
    
    public boolean isAccountNonExpired() {
        // TODO calculate account (User) expiration
        return user.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        if (user.getUserState()==ActivityState.ACTIVE.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isCredentialsNonExpired() {
    	// delegate to the application
        return true;
    }

    public boolean isEnabled() {
        // TODO replace auto-enable
        char state = credential.getCredentialState();
        if (state==ActivityState.ACTIVE.getValue() || 
                state==ActivityState.INITIAL.getValue()) {
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
    
    public Credential getCredential() {
        return credential;
    }
    public void setCredential(Credential credential) {
    	this.credential = credential;
    }


    public User getUser() {
        return user;
    }
    
    public final void setUser(User user) {
        validateUserAndCredentialCompatibility(user);
        this.user = user;
        if (logger.isDebugEnabled()) {
            logger.debug("User selected");
        }
    }
    
	protected final void validateUserAndCredentialCompatibility(UserGroup user) {
        Assert.notNull(user, "Required to UserDetailsAdapter");
        Assert.notNull(credential, "Required to UserDetailsAdapter");
        if(!user.getUserKey().equals(credential.getIdentity().getPrincipal())) {
            throw new IllegalArgumentException("User and Credential must have the same principal");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("User and Credential share the same Identity");
        }
    }

	/**
	 * Authorities
	 */
    public GrantedAuthority[] getAuthorities() {
        return this.authorities;
    }
	public void setAuthorities(GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}
	// TODO security v3
//    public List<GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//	public void setAuthorities(List<GrantedAuthority> authorities) {
//		this.authorities = authorities;
//	}

    public String convertUserRoleToString(UserRole userRole) {
        StringBuilder sb = new StringBuilder();
        sb.append("ROLE_").append(userRole.getService().getServiceName())
                .append("_").append(userRole.getServiceExtension());
        return sb.toString();
    }

    static final Log logger = LogFactory.getLog(UserDetailsAdapter.class);
    
}
