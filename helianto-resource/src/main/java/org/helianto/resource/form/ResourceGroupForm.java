package org.helianto.resource.form;

import org.helianto.core.TrunkEntity;
import org.helianto.core.filter.form.SearchForm;
import org.helianto.core.filter.form.TypeForm;

/**
 * Resource and subclasses form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ResourceGroupForm 

	extends TrunkEntity
	, TypeForm
	, SearchForm 
	, ResourceGroupMasterForm

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
