package org.helianto.classic.number;

/**
 * Implementing classes should allow verification digit generation.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Verifiable {
	
	/**
	 * Verification digit getter.
	 */
	public int getVerificationDigit();
	
	/**
	 * Verification digit setter.
	 */
	public void setVerificationDigit(int digit);

}
