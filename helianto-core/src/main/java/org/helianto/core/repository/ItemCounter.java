package org.helianto.core.repository;

import java.io.Serializable;

/**
 * Generic class to count categories.
 * 
 * @author mauriciofernandesdecastro
 */
public class ItemCounter<B> 
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private B baseClass;
	private long itemCount;
	
	/**
	 * Constructor.
	 */
	public ItemCounter() {
		super();
	}
	
	/**
	 * Full constructor.
	 * 
	 * @param baseClass
	 * @param itemCount
	 */
	public ItemCounter(B baseClass, long itemCount) {
		this();
		setBaseClass(baseClass);
		setItemCount(itemCount);
	}
	
	/**
	 * Base class.
	 */
	public B getBaseClass() {
		return baseClass;
	}
	public void setBaseClass(B baseClass) {
		this.baseClass = baseClass;
	}
	
	/**
	 * Count.
	 */
	public long getItemCount() {
		return itemCount;
	}
	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}
	
	/**
	 * True if item count is non zero positive.
	 */
	public boolean isCountPositive() {
		return getItemCount()>0;
	}

}
