package org.helianto.core;

import org.helianto.core.domain.Identity;

/**
 * Ownership interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Ownership {
	
	/**
	 * Reveals the owner of an entity.
	 */
	public Identity getOwner();

}
