package org.helianto.core.security;

import java.util.Collection;

import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Default implementation for <code>UserDetails</code> factory interface.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userDetailsFactory")
public class UserDetailsFactoryBean implements UserDetailsFactory {
	
	public UserDetails createUserDetails(User user, Authentication previousAuthentication) {
		if(!previousAuthentication.isAuthenticated()) {
			throw new IllegalArgumentException("Unable to create new details, previous authentication expired");
		}
		UserDetailsAdapter previousUserDetails = (UserDetailsAdapter) previousAuthentication.getPrincipal();
		if(!previousUserDetails.getUser().getIdentity().equals(user.getIdentity())) {
			throw new IllegalArgumentException("Unable to create new details, previous authentication not from the same identity");
		}
		return createUserDetails(user, previousUserDetails.getCredential());
	}

	public UserDetails createUserDetails(User user, Credential credential) {
		Collection<UserRole> roles = user.getRoleList();
        GrantedAuthority[] authorities = new GrantedAuthority[roles.size()];
        int i = 0;
        for (UserRole r : roles) {
            String roleName = convertUserRoleToString(r);
            authorities[i++] = new GrantedAuthorityImpl(roleName);
        }
        UserDetailsAdapter userDetails = new UserDetailsAdapter(user, credential);
        userDetails.setAuthorities(authorities);
		return userDetails;
	}

    /**
     * Convert a role to a string.
     * 
     * @param userRole
     */
    protected String convertUserRoleToString(UserRole userRole) {
        StringBuilder sb = new StringBuilder();
        sb.append("ROLE_").append(userRole.getService().getServiceName())
                .append("_").append(userRole.getServiceExtension());
        return sb.toString();
    }
    
}
