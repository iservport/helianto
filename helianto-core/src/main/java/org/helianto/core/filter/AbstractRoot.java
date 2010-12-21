package org.helianto.core.filter;

import org.helianto.core.Operator;
import org.helianto.core.RootEntity;

/**
 * Base class to implement <code>RootEntity</code>.
 * 
 * @author mauriciofernandesdecastro
 */
@SuppressWarnings("serial")
public abstract class AbstractRoot implements RootEntity {
	
	private Operator operator;
	
	/**
	 * Operator constructor.
	 * 
	 * @param operator
	 */
	protected AbstractRoot(Operator operator) {
		setOperator(operator);
	}
	
	public Operator getOperator() { 
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
}

