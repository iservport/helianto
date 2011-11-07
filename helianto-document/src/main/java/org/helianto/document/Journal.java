package org.helianto.document;

import java.io.Serializable;
import java.util.Date;

/**
 * Journal interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface Journal extends Serializable {
	
    /**
     * Actual start date.
     */
	public Date getActualStartDate();
	
    /**
     * Actual end date.
     */
	public Date getActualEndDate();

}
