package org.helianto.core.domain;

/**
 * Interface to password generator call-backs.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentityPasswordGenerator {
	
	/**
	 * Generate password.
	 * 
	 * @param plainPassword
	 */
	String generatePassword(String plainPassword);
	
}
