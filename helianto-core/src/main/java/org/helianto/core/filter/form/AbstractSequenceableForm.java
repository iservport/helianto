package org.helianto.core.filter.form;

import org.helianto.core.number.Sequenceable;

/**
 * Base class to sequenceable forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractSequenceableForm extends AbstractTrunkForm implements Sequenceable {

	private static final long serialVersionUID = 1L;
	private long internalNumber;
	
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

}
