package org.helianto.core.filter.form;

import org.helianto.core.Entity;
import org.helianto.core.number.Sequenceable;

/**
 * Base class to sequenceable forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractSequenceableForm implements Sequenceable {

	private static final long serialVersionUID = 1L;
	private Entity entity;
	private long internalNumber;
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

}
