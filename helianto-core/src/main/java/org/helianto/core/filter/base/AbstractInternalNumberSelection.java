package org.helianto.core.filter.base;


/**
 * Base class to use in FilterAdapters to limit the fields returned by a select clause.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractInternalNumberSelection 
	extends AbstractIdSelection
{

	private long internalNumber;

	/**
	 * The internal number constructor.
	 * 
	 * @param id
	 * @param internalNumber
	 */
	protected AbstractInternalNumberSelection(final int id, long internalNumber) {
		super(id);
		this.internalNumber = internalNumber;
	}
	
	public long getInternalNumber() {
		return internalNumber;
	}

}
