package org.helianto.core;

/**
 * Indicates instances as nodes of a hierarchical tree.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Navigable {
	
	/**
	 * Parent path.
	 */
	String getParentPath();

	/**
	 * Node path, typically transient.
	 */
	String getCurrentPath();

}
