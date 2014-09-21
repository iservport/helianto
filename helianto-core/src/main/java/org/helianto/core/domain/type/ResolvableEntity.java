package org.helianto.core.domain.type;

import org.helianto.core.form.EventForm;

/**
 * Implemented by classes that identify an owner and a resolvable status.
 * 
 * @author mauriciofernandesdecastro
 * @see EventForm
 */
public interface ResolvableEntity {

	/**
	 * Event owner id.
	 */
	int getOwnerId();

    /**
     * Resolution.
     */
    char getResolution();

}
