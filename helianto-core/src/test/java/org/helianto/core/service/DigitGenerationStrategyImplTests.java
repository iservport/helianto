package org.helianto.core.service;

import static org.junit.Assert.assertEquals;

import org.helianto.core.number.DigitGenerationStrategy;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DigitGenerationStrategyImplTests {
	
	private DigitGenerationStrategy digitGenerationStrategy;
	
	@Test
	public void generate() {
		assertEquals(9, digitGenerationStrategy.generateDigit(12345678));
		assertEquals(9, digitGenerationStrategy.generateDigit(23456789));
		assertEquals(0, digitGenerationStrategy.generateDigit(123456));
		assertEquals(3, digitGenerationStrategy.generateDigit(1234));
		assertEquals(3, digitGenerationStrategy.generateDigit("1234"));
	}
	
	@Test
	public void validate() {
		digitGenerationStrategy.validate("1234");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void error() {
		((DefaultDigitGenerationStrategy) digitGenerationStrategy).setFixedLengthRequired(true);
		((DefaultDigitGenerationStrategy) digitGenerationStrategy).setFixedLength(5);
		digitGenerationStrategy.validate("1234");
	}
	
	@Before
	public void setUp() {
		digitGenerationStrategy = new DefaultDigitGenerationStrategy();
		
	}

}
