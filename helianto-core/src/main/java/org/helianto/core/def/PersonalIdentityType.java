package org.helianto.core.def;

/**
 * Personal identification type.
 * 
 * @author mauriciofernandesdecastro
 */
public enum PersonalIdentityType {
	
	NOT_REQUIRED,
	NATIONAL_ID('R'),
	NATIONAL_TAXATION_ID('T'),
	PASSPORT('P');
	
	private PersonalIdentityType() {
		this('N');
	}
	
	private PersonalIdentityType(char value) {
		this.value = value;
	}
	
	private char value;
	
	/**
	 * Assigned value.
	 */
	public char getValue() {
		return value;
	}

}
