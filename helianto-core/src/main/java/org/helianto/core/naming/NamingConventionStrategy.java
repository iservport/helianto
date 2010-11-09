package org.helianto.core.naming;

/**
 * A strategy to define naming conventions.
 * 
 * @author mauriciofernandesdecastro
 */
public interface NamingConventionStrategy {
	
	/**
	 * Return the object name.
	 * 
	 * @param object
	 */
	public String getObjectName(Object object);

}
