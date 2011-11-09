package org.helianto.core.filter.form;

/**
 * Parent form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ParentForm<P> {
	
	/**
	 * Parent constraint filters.
	 */
	public P getParent();
	
	/**
	 * Parent as a R/W property.
	 * 
	 * @param parent
	 */
	public void setParent(P parent);
	
	/**
	 * The parent filter name.
	 */
	public String getParentName();

	/**
	 * The parent filter id field.
	 */
	public long getParentId();
}
