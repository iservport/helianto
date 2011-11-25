package org.helianto.core.validation;

import org.helianto.core.Operator;
import org.helianto.core.RootEntity;

/**
 * Base class to root (aggregated to Operator) forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractTrunkForm implements RootEntity {

	private static final long serialVersionUID = 1L;
	private Operator operator;
	
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

}
