package org.helianto.core.form;

import java.io.Serializable;

/**
 * Association form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AssociationForm extends Serializable {

	/**
	 * Parent id.
	 */
	int getParentId();
	
	/**
	 * Child id.
	 */
	int getChildId();
	
}
