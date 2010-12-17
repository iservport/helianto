package org.helianto.core;

import java.io.Serializable;

/**
 * To be implemented by any entity directly related to an <code>Operator</code>.
 * 
 * @author mauriciofernandesdecastro
 */
public interface RootEntity extends Serializable {
	
	/**
	 * The owning operator.
	 */
	public Operator getOperator();

}
