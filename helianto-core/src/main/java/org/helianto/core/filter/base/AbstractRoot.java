package org.helianto.core.filter.base;

import javax.persistence.Transient;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.type.RootEntity;

/**
 * Base class to implement <code>RootEntity</code>.
 * 
 * @author mauriciofernandesdecastro
 */
@SuppressWarnings("serial")
public abstract class AbstractRoot 
	implements RootEntity 
{
	
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
	
    @Transient
    public int getContextId() {
    	if (getOperator()!=null) {
    		return getOperator().getId();
    	}
    	return 0;
    }
	
}

