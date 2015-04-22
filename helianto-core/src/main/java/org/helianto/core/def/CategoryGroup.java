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
	 * Maintenance.
	 */
	MAINTENANCE('M'),
	/**
	 * Stock.
	 */
	STOCK('S'),
	/**
	 * Process.
	 */
	PROCESS('O'),
	/**
	 * Product.
	 */
	PRODUCT('P'),
	/**
	 * Order.
	 */
	ORDER('Y'),
	/**
	 * Service.
	 */
	SERVICE('E'),
	/**
	 * Planning.
	 */
	REQUEST('Q'),
	/**
	 * Project.
	 */
	PROJECT('J'),
	/**
	 * Planning.
	 */
	PLANNING('R'),
	/**
	 * User.
	 */
	USER('Z'),
	/**
	 * Competence.
	 */
	COMPETENCE('C');
	
	private CategoryGroup(char value) {
		this.value = value;
	}
	
	private char value;
	
	public char getValue() {
		return value;
	}

}
