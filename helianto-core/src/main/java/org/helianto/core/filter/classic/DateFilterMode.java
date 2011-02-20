package org.helianto.core.filter.classic;

/**
 * Convenience to operate date range filters.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
public enum DateFilterMode {
	
	/**
	 * From date to future using interval.
	 */
	FROM_DATE_PLUS_INTERVAL('F'),
	/**
	 * From past to date using interval.
	 */
	TO_DATE_MINUS_INTERVAL('T'),
	/**
	 * Ignore interval and require both from and to dates..
	 */
	FROM_DATE_TO_DATE('B'),
	/**
	 * Disable date range filter.
	 */
	DISABLE_DATE_RANGE('D');
	
	private DateFilterMode(char value) {
		this.value = value;
	}
	
	private char value;
	
	/**
	 * Value assigned to the enum.
	 */
	public char getValue() {
		return value;
	}

}
