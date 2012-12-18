package org.helianto.core.form;

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.type.TrunkEntity;

/**
 * Unit form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UnitForm extends TrunkEntity {
	
	/**
	 * Category id filter.
	 */
	int getCategoryId();
	
	/**
	 * Category group filter.
	 */
	CategoryGroup getCategoryGroup();

	/**
	 * Unit code filter.
	 */
	String getUnitCode();

	/**
	 * Unit name filter.
	 */
	String getUnitName();

}
