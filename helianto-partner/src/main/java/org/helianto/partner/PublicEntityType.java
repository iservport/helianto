package org.helianto.partner;

/**
 * Define public entity types.
 * 
 * @author mauriciofernandesdecastro
 */
public enum PublicEntityType {
	
	/**
	 * Public entity represents government, etc.
	 */
	PUBLIC('0'),
	/**
	 * Public entity is private.
	 */
	PRIVATE('1');
	
	private char value;

	/**
	 * Constructor.
	 * 
	 * @param value
	 */
	private PublicEntityType(char value) {
		this.value = value;
	}
	
	/**
	 * Value getter.
	 */
	public char getValue() {
		return value;
	}

}
