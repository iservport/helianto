package org.helianto.core.form;

import java.util.Date;

import org.helianto.core.Identity;
import org.helianto.core.TrunkEntity;


/**
 * Event interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface EventForm 
	extends TrunkEntity {
	
    /**
     * Date issued.
     */
	public Date getIssueDate();
	
	/**
	 * Event owner.
	 */
	public Identity getOwner();

    /**
     * Resolution.
     */
    char getResolution();

}
