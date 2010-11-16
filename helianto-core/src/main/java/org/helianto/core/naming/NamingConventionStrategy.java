package org.helianto.core.naming;

/**
 * A strategy to define naming conventions.
 * 
 * @author mauriciofernandesdecastro
 */
public interface NamingConventionStrategy {
	
	/**
	 * Return the conventional name.
	 * 
	 * @param clazz
	 */
	public String getConventionalName(Class<?> clazz);

}
