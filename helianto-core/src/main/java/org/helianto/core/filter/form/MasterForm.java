package org.helianto.core.filter.form;

/**
 * Master form interface (to be used in master-detail abstractions).
 * 
 * @author mauriciofernandesdecastro
 */
public interface MasterForm<M> {
	
	/**
	 * Master constraint filters.
	 */
	public M getMaster();
	
	/**
	 * Master as a R/W property.
	 * 
	 * @param master
	 */
	public void setMaster(M master);

}
