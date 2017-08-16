package org.helianto.classic.def;

/**
 * Define public entity visibility.
 * 
 * @author mauriciofernandesdecastro
 */
public enum PublicEntityVisibility {

	/**
	 * Public entity is visible to all.
	 */
	ALL('A'),
	/**
	 * Visible only when the public entity has the same alias than a 
	 * registered partner.
	 */
	REGISTERED('R');
	
	private char value;

	private PublicEntityVisibility(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}
	
}
