package org.helianto.core.filter.form;

import java.util.List;

/**
 * Parent list form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ParentListForm<P> {
	
	/**
	 * Parent list.
	 */
	public List<P> getParentList();
	
	/**
	 * Parent list as a R/W property.
	 * 
	 * @param parentList
	 */
	public void setParentList(List<P>  parentList);

}
