package org.helianto.core.form.internal;

import org.helianto.core.number.Internal;

/**
 * Base class to internally numbered forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractInternalForm 
	extends AbstractTrunkForm implements Internal {

	private static final long serialVersionUID = 1L;
	private long internalNumber;
	
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

}
