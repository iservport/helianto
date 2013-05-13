package org.helianto.core.form;

import org.helianto.core.domain.type.RootEntity;

/**
 * Context event form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContextEventForm extends RootEntity {

	/**
	 * Public number.
	 */
	long getPublicNumber();
	
	/**
	 * Resolution.
	 */
	char getResolution();
	
}
