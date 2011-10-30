package org.helianto.core;

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
