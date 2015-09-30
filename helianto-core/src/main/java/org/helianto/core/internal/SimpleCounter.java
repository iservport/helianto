package org.helianto.core.internal;

import java.io.Serializable;
import java.util.Date;

import org.helianto.core.repository.ItemCounter;

/**
 * Class to count serializable based aggregators.
 * 
 * @author mauriciofernandesdecastro
 */
public class SimpleCounter 
	extends ItemCounter<Serializable> 
	implements Comparable<SimpleCounter>
{

	private static final long serialVersionUID = 1L;
	
	private Date firstCheckDate;
	
	/**
	 * Constructor.
	 * 
	 * @param aggregator
	 * @param itemCount
	 */
	public SimpleCounter(Serializable aggregator, long itemCount) {
		super(aggregator, itemCount); 
	}
	
	/**
	 * Constructor.
	 * 
	 * @param baseClass
	 * @param itemCount
	 */
	public SimpleCounter(Serializable baseClass, long itemCount, Date firstCheckDate) {
		super(baseClass, itemCount); 
		this.firstCheckDate = firstCheckDate;
	}
	
	/**
	 * Avoide NPE when aggregator is null.
	 */
	@Override
	public Serializable getBaseClass() {
		if (super.getBaseClass()==null) {
			return new Integer(0);
		}
		return super.getBaseClass();
	}
	
	/**
	 * First check date.
	 */
	public Date getFirstCheckDate() {
		return firstCheckDate;
	}
	
	@Override
	public int compareTo(SimpleCounter o) {
		return this.firstCheckDate.compareTo(o.firstCheckDate);
	}

}
