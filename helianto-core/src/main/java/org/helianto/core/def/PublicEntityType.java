package org.helianto.core.def;

/**
 * Define public entity types.
 * 
 * @author mauriciofernandesdecastro
 */
public enum PublicEntityType {
	
	/**
	 * Public entity represents government, etc.
	 */
	PUBLIC('P'),
	/**
	 * Public entity is private.
	 */
	PRIVATE('R'),
	/**
	 * Public entity type not informed.
	 */
	NOT_INFORMED('N');
	
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
