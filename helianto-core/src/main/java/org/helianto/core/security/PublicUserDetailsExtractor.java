package org.helianto.core.security;

import java.security.Principal;

import org.helianto.core.Entity;

/**
 * A strategy to resolve <code>PublicUserDetails</code> from some authenticated principal.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicUserDetailsExtractor {
	
	/**
	 * Get <code>PublicUserDetails</code> from principal.
	 * 
	 * @param principal
	 */
	public PublicUserDetails getPublicUserDetails(Principal principal);

	/**
	 * Get <code>Entity</code> from principal.
	 * 
	 * @param principal
	 */
	public Entity getEntity(Principal principal);

}
