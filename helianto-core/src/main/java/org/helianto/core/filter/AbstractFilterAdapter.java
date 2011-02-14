package org.helianto.core.filter;


/**
 * Filter adapter.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 */
public abstract class AbstractFilterAdapter<T> extends AbstractFilter {
	
	private static final long serialVersionUID = 1L;
	private T filter; 

	/**
	 * Constructor.
	 * 
	 * @param filter
	 */
	public AbstractFilterAdapter(T filter) {
		this.filter = filter;
		reset();
	}

	/**
	 * The filter.
	 */
	public T getFilter() {
		return filter;
	}
	
	/**
	 * Internal filter setter.
	 * 
	 * @param filter
	 */
	protected void setFilter(T filter) {
		this.filter = filter;
	}

}
