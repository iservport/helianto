package org.helianto.core.form;



/**
 * Unit form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UnitForm 
	extends EntityIdForm
{
	
	/**
	 * Unit code filter.
	 */
	String getUnitCode();

	/**
	 * Unit symbol filter.
	 */
	String getUnitSymbol();

	/**
	 * Unit name filter.
	 */
	String getUnitName();

	/**
	 * Unit nature filter.
	 */
	char getNature();

}
