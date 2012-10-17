package org.helianto.core.form;

import java.util.Date;

/**
 * Control form interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ControlForm {

    /**
     * Date to be controlled.
     */
    public Date getNextCheckDate();
    
    /**
     * Frequency.
     */
    public int getFrequency();
    
}
