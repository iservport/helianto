package org.helianto.core.filter.base;

import javax.persistence.Transient;

import org.helianto.core.domain.Entity;
import org.helianto.core.number.Sequenceable;

/**
 * Base class implementing <code>Sequenceable</code>.
 * 
 * @author mauriciofernandesdecastro
 */
@SuppressWarnings("serial")
public abstract class AbstractSequence 
	extends AbstractInternal 
	implements Sequenceable {

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	protected AbstractSequence(Entity entity, long internalNumber) {
		super(entity, internalNumber);
	}

	@Transient
	public String getInternalNumberKey() { 
		return "KEY";
	}
	
	@Transient
	public int getStartNumber() {
		return 1;
	}
}
