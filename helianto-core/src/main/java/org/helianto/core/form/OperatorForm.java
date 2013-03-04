package org.helianto.core.form;


/**
 * Operator form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface OperatorForm {
	
	/**
	 * Operator name.
	 */
	String getOperatorName();
	
	/**
	 * True if must return one instance.
	 */
	boolean isSelection();
	
}
