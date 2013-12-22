package org.helianto.core.form;

import org.helianto.core.filter.DateForm;

/**
 * Event interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface EventForm 
	extends EntityIdForm
	, DateForm 
{
	
	/**
	 * Event owner id.
	 */
	int getOwnerId();

    /**
     * Resolution.
     */
    char getResolution();

}
