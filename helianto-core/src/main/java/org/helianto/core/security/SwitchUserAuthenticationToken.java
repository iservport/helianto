package org.helianto.core.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;


/**
 * <code>Authentication</code> implementation required to replace a previously authenticated one.
 * 
 * @author mauriciofernandesdecastro
 */
public class SwitchUserAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private UserDetailsAdapter userDetails;
	
	/**
	 * Required constructor.
	 * 
	 * @param userDetails
	 * @param authenticated
	 */
	public SwitchUserAuthenticationToken(UserDetailsAdapter userDetails, boolean authenticated) {
		super(userDetails.getAuthorities());
		this.userDetails = userDetails;
		setDetails(userDetails);
		setAuthenticated(authenticated);
	}

	public Object getCredentials() {
		return userDetails.getPassword();
	}

	public Object getPrincipal() {
		return userDetails;
	}

}
