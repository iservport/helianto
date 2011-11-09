package org.helianto.core.filter;

/**
 * Interface to filters including a parent field.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 * @see ParentForm
 */
public interface ParentFilter {
	
	/**
	 * The parent filter field.
	 * 
	 * @param <P>
	 */
	public <P> P getParent();

	/**
	 * The parent filter id field.
	 */
	public long getParentId();

}
