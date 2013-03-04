package org.helianto.core.form;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.type.RootEntity;

/**
 * Base class to root (aggregated to Operator) forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractRootForm 

	extends AbstractSearchForm
	
	implements RootEntity 

{

	private static final long serialVersionUID = 1L;
	private Operator operator;
	
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

}
