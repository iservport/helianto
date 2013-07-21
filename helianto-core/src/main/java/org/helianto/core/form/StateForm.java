package org.helianto.core.form;

import org.helianto.core.domain.type.ContextEntity;

/**
 * State form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface StateForm 
	extends ContextEntity, SearchForm, PriorityForm {

	/**
	 * State code.
	 */
	String getStateCode();
	
	/**
	 * Country id.
	 */
	int getCountryId();
	
}
