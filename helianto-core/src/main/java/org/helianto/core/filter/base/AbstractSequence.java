package org.helianto.core.filter.base;

import org.helianto.core.Entity;
import org.helianto.core.number.Sequenceable;

/**
 * Base class implementing <code>Sequenceable</code>.
 * 
 * @author mauriciofernandesdecastro
 */
@SuppressWarnings("serial")
public abstract class AbstractSequence extends AbstractTrunk implements Sequenceable {

	private long internalNumber;
	
	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	protected AbstractSequence(Entity entity, long internalNumber) {
		super(entity);
		setInternalNumber(internalNumber);
	}

	/**
	 * Internal number.
	 */
	public long getInternalNumber() {
		return this.internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}
	
	/**
	 * The entity key.
	 */
	public String getInternalNumberKey() { 
		return "KEY";
	}
}
