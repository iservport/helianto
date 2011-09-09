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

}
