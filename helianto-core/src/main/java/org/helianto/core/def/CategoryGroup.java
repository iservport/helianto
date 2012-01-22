package org.helianto.core.def;

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
	STOCK('S'),
	/**
	 * Product.
	 */
	PRODUCT('P'),
	/**
	 * Service.
	 */
	SERVICE('E');
	
	private CategoryGroup(char value) {
		this.value = value;
	}
	
	private char value;
	
	public char getValue() {
		return value;
	}

}
