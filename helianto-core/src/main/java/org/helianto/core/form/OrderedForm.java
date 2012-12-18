package org.helianto.core.form;

/**
 * Ordered form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface OrderedForm {
	
    /**
     * Order indicator.
     */
	public char getOrderBy();
	
	/**
	 * Order indicator is a R/W property.
	 * 
	 * @param orderBy
	 */
	public void setOrderBy(char orderBy);

}
