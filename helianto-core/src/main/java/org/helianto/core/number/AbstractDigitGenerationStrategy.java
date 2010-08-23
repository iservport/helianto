package org.helianto.core.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to <code>DigitGenerationStrategy</code> implementations.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractDigitGenerationStrategy implements DigitGenerationStrategy {

	private boolean fixedLengthRequired = false;
	private int fixedLength;
	
	/**
	 * True if fixed length is required.
	 */
	public boolean isFixedLengthRequired() {
		return fixedLengthRequired;
	}
	public void setFixedLengthRequired(boolean fixedLengthRequired) {
		this.fixedLengthRequired = fixedLengthRequired;
	}
	
	/**
	 * Optional fixed length.
	 */
	public int getFixedLength() {
		return fixedLength;
	}
	public void setFixedLength(int fixedLength) {
		this.fixedLength = fixedLength;
	}
	
	public void validate(String numberAsString) {
		if (fixedLengthRequired && numberAsString.length() != fixedLength) {
			throw new IllegalArgumentException(
					"Number '"+numberAsString+"' length="+ numberAsString.length()+", required "+fixedLength);
		}
	}

	public int generateDigit(long number) {
		return generateDigit(String.valueOf(number));
	}

	public int generateDigit(String numberAsString) {
		logger.debug("Ready to generate verification digit from '{}'.", numberAsString);
		int len = numberAsString.length();
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += Character.getNumericValue(numberAsString.charAt(i)) * weight(i + 1, len);
		}
		int verificationDigit = normalize(sum);
		logger.debug("Verification digit genearted as {}.", verificationDigit);
		return verificationDigit;
	}

	/**
	 * Convenience to generate a weight corresponding to the current generation position.
	 * 
	 * @param position
	 * @param length
	 */
	public abstract int weight(int position, int length);
	
	/**
	 * Convenience to constraint verification digit to appropriate value after sum.
	 * 
	 * @param sum
	 */
	public abstract int normalize(int sum);
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractDigitGenerationStrategy.class);

}
