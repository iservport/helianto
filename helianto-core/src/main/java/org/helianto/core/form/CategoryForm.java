package org.helianto.core.form;

import org.helianto.core.Resettable;
import org.helianto.core.domain.type.TrunkEntity;

/**
 * Category form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CategoryForm extends TrunkEntity, Resettable {
	
	/**
	 * Category code.
	 */
	public String getCategoryCode();
	
	/**
	 * Category name.
	 */
	public String getCategoryName();
	
	/**
	 * Category group.
	 */
	public char getCategoryGroup();

}
