package org.helianto.core.filter.base;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractInternalEntity;
import org.helianto.core.number.Internal;

/**
 * Base class implementing <code>Sequenceable</code>.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 * @see AbstractInternalEntity
 */
@SuppressWarnings("serial")
public abstract class AbstractInternal 
	extends AbstractTrunk 
	implements Internal {

	private long internalNumber;
	
	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	protected AbstractInternal(Entity entity, long internalNumber) {
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
	
}
