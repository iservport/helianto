package org.helianto.security.service;

import java.security.Principal;

import org.helianto.core.domain.Entity;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.PublicUserDetailsExtractor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Resolve <code>PublicUserDetailsExtractor</code> from a Spring Security <code>Authentication</code> object.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("publicUserDetailsExtractor")
public class SpringSecurityPublicUserDetailsExtractor implements PublicUserDetailsExtractor {

	public PublicUserDetails getPublicUserDetails(Principal principal) {
		return (PublicUserDetails) ((Authentication) principal).getPrincipal();
	}

	public Entity getEntity(Principal principal) {
		return getPublicUserDetails(principal).getEntity();
	}

}
