package org.helianto.core.number;

public interface DigitGenerationStrategy {
	
	/**
	 * Perform number validation prior to verification digit calculation.
	 * 
	 * @param numberAsString
	 */
	public void validate(String numberAsString);

	/**
	 * Verification digit generation.
	 * 
	 * @param number
	 */
	public int generateDigit(long number);

	/**
	 * Verification digit generation.
	 * 
	 * @param numberAsString
	 */
	public int generateDigit(String numberAsString);

}
