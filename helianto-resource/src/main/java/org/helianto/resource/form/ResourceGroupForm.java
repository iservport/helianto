package org.helianto.resource.form;

import org.helianto.core.TrunkEntity;
import org.helianto.core.filter.form.TypeForm;

/**
 * Resource and subclasses form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ResourceGroupForm extends TrunkEntity, TypeForm, ResourceAssociationForm {
	
	/**
	 * Resource code filter.
	 */
	public String getResourceCode();

	/**
	 * Resource name filter.
	 */
	public String getResourceName();
	
	/**
	 * Resource name filter.
	 */
	public char getResourceType();

}
