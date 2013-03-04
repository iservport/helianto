package org.helianto.core.form;

/**
 * Child form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ChildForm<C> {
	
	/**
	 * Child constraint filters.
	 */
	public C getChild();
	
	/**
	 * Child as a R/W property.
	 * 
	 * @param child
	 */
	public void setChild(C child);
	
	/**
	 * The child filter name.
	 */
	public String getChildName();

	/**
	 * The child filter id field.
	 */
	public long getChildId();
}
