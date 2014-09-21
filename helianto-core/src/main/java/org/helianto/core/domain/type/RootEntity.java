package org.helianto.core.domain.type;

import java.io.Serializable;

import org.helianto.core.domain.Operator;

/**
 * To be implemented by any entity directly related to an <code>Operator</code>.
 * 
 * @author mauriciofernandesdecastro
 */
public interface RootEntity extends Serializable {
	
	/**
	 * The owning operator.
	 * @deprecated
	 */
	public Operator getOperator();

	/**
	 * The owning context id.
	 */
	public int getContextId();

}
