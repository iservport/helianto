package org.helianto.partner;

/**
 * Define public entity visibility.
 * 
 * @author mauriciofernandesdecastro
 */
public enum PublicEntityVisibility {

	ALL('A'),
	REGISTERED('R');
	
	private char value;

	private PublicEntityVisibility(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}
	
}
