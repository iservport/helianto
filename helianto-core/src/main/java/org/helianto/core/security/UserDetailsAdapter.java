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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.domain.Operator;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Models core user information retrieved by
 * {@link org.springframework.security.core.userdetails.UserDetailsService} as an adapter class
 * to {@link org.helianto.user.domain.User}.
 * 
 * <p>
 * A new <code>UserDetailsAdapter</code> may be created from a single
 * {@link org.helianto.user.domain.User} and the correspondent credential to be expected 
 * during authentication. A new <code>UserDetailsAdapter</code> may also be created from
 * a group with no credential specified, where the authentication is then considered 
 * to be anonymous.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserDetailsAdapter
	implements
      Serializable
    , UserDetails
    , PublicUserDetails {

	private static final long serialVersionUID = 1L;
    private UserGroup user;
    private IdentitySecurity identitySecurity;
    private List<GrantedAuthority> authorities;

    /**
     * Package default constructor
     */
    UserDetailsAdapter() {
    	super();
    }
    
    /**
     * User constructor.
     * 
     * @param user
     * @param identitySecurity
     */
    public UserDetailsAdapter(User user, IdentitySecurity identitySecurity) {
        this();
        this.user = user;
        this.identitySecurity = identitySecurity;
        this.user.setLastEvent(new Date());
        logger.info("Secured user: {}", user);
    }
    
    /**
     * Roles constructor.
     * 
     * @param user
     * @param identitySecurity
     * @param roles
     */
    public UserDetailsAdapter(User user, IdentitySecurity identitySecurity, Collection<UserRole> roles) {
        this(user, identitySecurity);
        grantAuthorities(roles);
    }
    
    /**
     * Anonymous constructor.
     * 
     * @param user
     */
    public UserDetailsAdapter(UserGroup userGroup) {
        this();
        this.user = isAnonymousUserValid(userGroup);
        logger.info("Anonymous user: {}", userGroup);
    }
    
    /**
     * Identity security id.
     */
    public long getIdentitySecurityId() {
    	if (identitySecurity!=null) {
    		return identitySecurity.getId();
    	}
		return 0;
	}
    
    /**
     * Not null if anonymous user is valid.
     * 
     * <p>
     * Default implementation accepts only the special group USER.
     * </p>
     * 
     * @param user
     */
    protected UserGroup isAnonymousUserValid(UserGroup userGroup) {
    	if (userGroup!=null && userGroup.getUserKey().toUpperCase().equals("USER")) {
        	return userGroup;
    	}
    	return null;
    }
    
    /**
     * Iterates through user roles to grant authorities.
     * 
     * @param roles
     */
    public void grantAuthorities(Collection<UserRole> roles) {
        long identityId = isAnonymous() ? 0 : ((User) user).getIdentity().getId();
        Set<String> roleNames = UserRole.getRoleNames(roles, identityId);
        authorities = new ArrayList<GrantedAuthority>();
        for (String roleName: roleNames) {
            authorities.add(new GrantedAuthorityImpl(roleName));
            logger.info("Granted authority: {}.", roleName);
        }
    }
    
    /**
     * True if user is not instance of class User.
     */
    protected boolean isAnonymous() {
    	return !(user instanceof User);
    }

    public boolean isAccountNonExpired() {
    	if (isAnonymous()) {
    		return true;
    	}
        // TODO calculate account (User) expiration
        return user.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
    	if (isAnonymous()) {
    		return true;
    	}
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
    	if (isAnonymous()) {
    		return true;
    	}
        // TODO replace auto-enable
        char state = identitySecurity.getCredentialState();
        if (state==ActivityState.ACTIVE.getValue() || 
                state==ActivityState.INITIAL.getValue()) {
            return true;
        }
        return false;
    }

    public String getPassword() {
    	if (isAnonymous()) {
    		return "";
    	}
    	if (identitySecurity!=null) {
    		return identitySecurity.getConsumerSecret();
    	}
        return "";
     }

    public String getUsername() {
    	if (isAnonymous()) { 
    		return "anonymous";
    	}
        return ((User) user).getIdentity().getPrincipal();
    }
    
    /**
     * If the authentication is not anonymous, return the user, otherwise return null.
     */
    public User getUser() {
    	if (isAnonymous()) { 
    		return null;
    	}
        return (User) user;
    }
    
    /**
     * Convenience to retrieve user details from context.
     */
    public static UserDetailsAdapter getUserDetailsFromContext() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication!=null) {
    		return (UserDetailsAdapter) authentication.getPrincipal();
    	}
    	return null;
    }
    
    /**
     * Convenience to replace current user with a managed version.
     * 
     * @param user
     */
    public void updateUser(User user) {
    	if (this.user.getId()==user.getId()) {
    		this.user = user;
    	}
    }
    
    /**
     * If the authentication is anonymous, return it as an UserGroup.
     * 
     * @throws IllegalArgumentException if the authentication is not anonymous.
     */
    public UserGroup getAnonymousUser() throws IllegalArgumentException {
    	if (isAnonymous()) { 
    		return user;
    	}
        throw new IllegalArgumentException("Not allowed: user is not anonymous.");
    }
    
    public Entity getEntity() {
    	if (user==null) { 
    		return null;
    	}
    	return user.getEntity();
    }
    
    public Operator getOperator() {
    	return getEntity().getOperator();
    }
    
    public List<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    static final Logger logger = LoggerFactory.getLogger(UserDetailsAdapter.class);
    
}
