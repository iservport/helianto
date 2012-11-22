package org.helianto.core.form;

import java.util.Date;

/**
 * Event control form interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface EventControlForm 
	extends EventForm {

    /**
     * Date to be controlled.
     */
    public Date getNextCheckDate();
    
    /**
     * Frequency.
     */
    public int getFrequency();
    
}
