package org.helianto.resource.form;

import org.helianto.core.form.EntityIdForm;
import org.helianto.core.form.SearchForm;
import org.helianto.core.form.TypeForm;

/**
 * Resource and subclasses form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ResourceGroupForm 
	extends EntityIdForm
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
