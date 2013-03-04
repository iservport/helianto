package org.helianto.core.domain.type;

/**
 * Indicates instances as nodes of a hierarchical tree.
 * 
 * @author mauriciofernandesdecastro
 */
public interface NavigableEntity {
	
	/**
	 * Parent path.
	 */
	public String getParentPath();

	/**
	 * Node path, typically transient.
	 */
	public String getCurrentPath();

}
