package org.helianto.classic.number;

/**
 * Allows for pattern generation.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Sequencer {
	
	/**
	 * Pattern prefix.
	 */
	String getPatternPrefix();
	
	/**
	 * Pattern suffix.
	 */
	String getPatternSuffix();
	
	/**
	 * Number of digits.
	 */
	int getNumberOfDigits();

}
