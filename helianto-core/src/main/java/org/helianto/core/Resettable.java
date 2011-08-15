package org.helianto.core;

/**
 * Resettable interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Resettable {
	
	/**
	 * Reset fields in a domain object to null or empty values.
	 * 
	 * <p>
	 * Should not be used to reset unique key fields. To be called when
	 * empty or null values are required to replace default ones.
	 * </p>
	 */
	public void reset();

}
