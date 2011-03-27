package org.helianto.core.security.classic;

import java.security.Principal;

import org.helianto.core.Entity;
import org.helianto.core.security.PublicUserDetails;
import org.springframework.security.core.Authentication;

/**
 * Resolve <code>PublicUserDetailsExtractor</code> from a Spring Security <code>Authentication</code> object.
 * 
 * @author mauriciofernandesdecastro
 */
public class SpringSecurityPublicUserDetailsExtractor implements PublicUserDetailsExtractor {

	public PublicUserDetails getPublicUserDetails(Principal principal) {
		return (PublicUserDetails) ((Authentication) principal).getPrincipal();
	}

	public Entity getEntity(Principal principal) {
		return getPublicUserDetails(principal).getEntity();
	}

}
