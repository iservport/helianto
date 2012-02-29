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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.def.ActivityState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Models core user information retrieved by
 * {@link org.springframework.security.core.userdetails.UserDetailsService} as an adapter class
 * to {@link org.helianto.core.User}.
 * 
 * <p>
 * A new <code>UserDetailsAdapter</code> may be created from a single
 * {@link org.helianto.core.User} and the correspondent credential to be expected 
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
    , PublicUserDetails
    
{

	private static final long serialVersionUID = 1L;
    private UserGroup user;
    private Credential credential;
    private List<GrantedAuthority> authorities;

    /**
     * Package default constructor
     */
    UserDetailsAdapter() {}
    
    /**
     * User constructor.
     * 
     * @param user
     * @param credential
     */
    public UserDetailsAdapter(User user, Credential credential, Collection<UserRole> roles) {
        this();
        this.user = user;
        logger.info("Secured user: {}", user);
        this.credential = credential;
        grantAuthorities(roles, user);
    }
    
    /**
     * Anonymous constructor.
     * 
     * @param user
     */
    public UserDetailsAdapter(UserGroup userGroup) {
        this();
        this.user = isAnonymousUserValid(userGroup);
        logger.info("Anonymous user for entity: {}", user.getEntity());
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
     * @param user
     */
    protected void grantAuthorities(Collection<UserRole> roles, User user) {
        authorities = new ArrayList<GrantedAuthority>();
        Set<String> roleNames = new HashSet<String>();
        for (UserRole r : roles) {
        	if (r.getActivityState()==ActivityState.ACTIVE.getValue()) {
               roleNames.addAll(getUserRolesAsString(r, user));
        	}
        }
        for (String roleName: roleNames) {
            authorities.add(new GrantedAuthorityImpl(roleName));
            logger.info("Granted authority: {}.", roleName);
        }
    }
    
    /**
     * Converts user roles to authorities, including "ROLE_SELF_ID_x", where
     * x is the authorized user identity primary key.
     * 
     * @param userRole
     * @param user
     */
    protected Set<String> getUserRolesAsString(UserRole userRole, User user) {
        Set<String> roleNames = new HashSet<String>();
        roleNames.add(formatRole("SELF", new StringBuilder("ID_").append(user.getIdentity().getId()).toString()));
        String[] extensions = userRole.getServiceExtension().split(",");
        roleNames.add(formatRole(userRole.getService().getServiceName(), null));
        for (String extension: extensions) {
        	roleNames.add(formatRole(userRole.getService().getServiceName(), extension));
        }
        return roleNames;
    }
    
    /**
     * Internal role formatter.
     * 
     * @param serviceName
     * @param extension
     */
    protected String formatRole(String serviceName, String extension) {
        StringBuilder sb = new StringBuilder("ROLE_").append(serviceName);
        if (extension!=null && extension.length()>0) {
        	sb.append("_").append(extension.trim());
        }
        return sb.toString();
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
    
    private Credential getCredential() {
		return credential;
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
        char state = getCredential().getCredentialState();
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
    	if (getCredential()!=null) {
    		return credential.getPassword();
    	}
        return "";
     }

    public String getUsername() {
    	if (isAnonymous()) { 
    		return "anonymous";
    	}
        return ((User) user).getIdentity().getPrincipal();
    }
    
    public User getUser() {
    	if (isAnonymous()) { 
    		return null;
    	}
        return (User) user;
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
