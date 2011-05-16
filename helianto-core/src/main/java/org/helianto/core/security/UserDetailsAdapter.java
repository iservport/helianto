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
import java.util.List;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

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
 */
public class UserDetailsAdapter implements
        Serializable, UserDetails, PublicUserDetails {

	private static final long serialVersionUID = 1L;
    private User user;
    private Credential credential;
    private List<GrantedAuthority> authorities;

    /**
     * Default constructor
     */
    public UserDetailsAdapter() {}
    
    /**
     * Credential constructor.
     * 
     * @param user
     * @param credential
     */
    public UserDetailsAdapter(User user, Credential credential, Collection<UserRole> roles) {
        this();
        this.user = user;
        logger.info("Secured user: {}", user);
        this.credential = credential;
        grantAuthorities(roles);
    }
    
    /**
     * Iterates through user roles to grant authorities.
     * 
     * @param roles
     */
    protected void grantAuthorities(Collection<UserRole> roles) {
        authorities = new ArrayList<GrantedAuthority>();
        for (UserRole r : roles) {
            String[] roleNames = getUserRolesAsString(r);
            for (String roleName: roleNames) {
                authorities.add(new GrantedAuthorityImpl(roleName));
                logger.info("Granted authority: {}.", roleName);
            }
        }
    }
    
    /**
     * Converts user roles to authorities.
     * 
     * @param userRole
     */
    protected String[] getUserRolesAsString(UserRole userRole) {
        String[] extensions = userRole.getServiceExtension().split(",");
        String[] roleNames = new String[extensions.length+1];
        int i = 0;
        roleNames[i++] = formatRole(userRole.getService().getServiceName(), null);
        for (String extension: extensions) {
            roleNames[i++] = formatRole(userRole.getService().getServiceName(), extension);
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
    	if (credential!=null) {
    		return credential.getPassword();
    	}
        return "";
     }

    public String getUsername() {
        return user.getIdentity().getPrincipal();
    }
    
    public User getUser() {
        return user;
    }
    
    public Entity getEntity() {
    	return getUser().getEntity();
    }
    
    public Operator getOperator() {
    	return getEntity().getOperator();
    }
    
    public List<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    static final Logger logger = LoggerFactory.getLogger(UserDetailsAdapter.class);
    
}
