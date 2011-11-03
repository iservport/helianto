package org.helianto.core;

/**
 * Category groups.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum CategoryGroup {
	
	/**
	 * Not defined.
	 */
	NOT_DEFINED('N'),
	/**
	 * Document.
	 */
	DOCUMENT('D'),
	/**
	 * Unit.
	 */
	UNIT('U'),
	/**
	 * Instrument.
	 */
	INSTRUMENT('I'),
	/**
	 * Stock.
	 */
	STOCK('S');
	
	private CategoryGroup(char value) {
		this.value = value;
	}
	
	private char value;
	
	public char getValue() {
		return value;
	}

}
