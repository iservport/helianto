package org.helianto.core.filter;

import org.helianto.core.Operator;
import org.helianto.core.number.Numerable;

/**
 * Base class to implement <code>Numerable</code>.
 * 
 * @author mauriciofernandesdecastro
 */
@SuppressWarnings("serial")
public abstract class AbstractNumber extends AbstractRoot implements Numerable {
	
	private long internalNumber;
	
	/**
	 * Operator constructor.
	 * 
	 * @param operator
	 * @param internalNumber
	 */
	protected AbstractNumber(Operator operator, long internalNumber) {
		super(operator);
		setPublicNumber(internalNumber);
	}
	
	/**
	 * Public internal number.
	 */
	public long getPublicNumber() {
		return this.internalNumber;
	}
	public void setPublicNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

	/**
	 * The public key.
	 */
	public String getPublicNumberKey() {
		return "PUBLICKEY";
	}
}

