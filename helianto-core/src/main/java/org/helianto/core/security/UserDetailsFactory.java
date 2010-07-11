package org.helianto.core.security;

import org.helianto.core.Credential;
import org.helianto.core.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <code>UserDetails</code> factory interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserDetailsFactory {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 * @param previousAuthentication
	 */
	public UserDetails createUserDetails(User user, Authentication previousAuthentication);

	/**
	 * Factory method.
	 * 
	 * @param user
	 * @param credential
	 */
	public UserDetails createUserDetails(User user, Credential credential);

}
