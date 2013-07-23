package org.helianto.core.form;

import org.helianto.core.domain.type.ContextEntity;

/**
 * City form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CityForm 
	extends ContextEntity, SearchForm, PriorityForm {

	/**
	 * City code.
	 */
	String getCityCode();
	
	/**
	 * State id.
	 */
	int getStateId();

}
