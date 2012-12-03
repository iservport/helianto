package org.helianto.core.filter.form;

import org.helianto.core.domain.type.RootEntity;

/**
 * Key type form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface KeyTypeForm extends RootEntity {
	
	/**
	 * Key code.
	 */
	String getKeyCode();
	
	/**
	 * Key name.
	 */
	String getKeyName();

}
