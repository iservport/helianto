package org.helianto.resource.form;

import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.form.SearchForm;
import org.helianto.core.form.TypeForm;

/**
 * Resource and subclasses form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ResourceGroupForm 

	extends TrunkEntity
	, TypeForm
	, SearchForm 
	, ResourceGroupIdForm

{
	
	/**
	 * Resource code filter.
	 */
	String getResourceCode();

	/**
	 * Resource type filter.
	 */
	char getResourceType();

}
