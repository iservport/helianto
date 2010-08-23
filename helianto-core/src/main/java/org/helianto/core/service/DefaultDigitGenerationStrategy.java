package org.helianto.core.service;

import org.helianto.core.number.AbstractDigitGenerationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Default <code>DigitGenerationStrategy</code> implementation.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("digitGenerationStrategy")
public class DefaultDigitGenerationStrategy extends AbstractDigitGenerationStrategy  {
	
	public int weight(int position, int length) {
//		logger.debug("W({})={}", position, ((length - position) % 8) + 2);
		return ((length - position) % 8) + 2;
	}
	
	public int normalize(int sum) {
		int complement = 11;
		logger.debug("Normalize sum {} with complent of {}.", sum, complement);
		int verificationDigit = complement - (sum % complement);
		verificationDigit = verificationDigit > 9 ? 0 : verificationDigit;
		return verificationDigit;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultDigitGenerationStrategy.class);

}
