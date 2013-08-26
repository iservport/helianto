package org.helianto.core.form;

import org.helianto.core.domain.type.TrunkEntity;


/**
 * Unit form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UnitForm 
	extends TrunkEntity 
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
