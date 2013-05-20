package org.helianto.core.form;


/**
 * Unit form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UnitForm 
	extends CategoryForm {
	
	/**
	 * Category id filter.
	 */
	int getCategoryId();
	
	/**
	 * Unit code filter.
	 */
	String getUnitCode();

	/**
	 * Unit name filter.
	 */
	String getUnitName();

}
