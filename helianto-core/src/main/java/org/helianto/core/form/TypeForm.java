package org.helianto.core.form;

/**
 * Type form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface TypeForm {
	
    /**
     * Type constraint to polimorphic filters.
     */
	public char getType();
	
	/**
	 * Type is a R/W property.
	 * 
	 * @param type
	 */
	public void setType(char type);

}
