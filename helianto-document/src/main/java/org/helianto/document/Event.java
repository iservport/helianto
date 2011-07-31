package org.helianto.document;

import java.util.Date;

import org.helianto.core.Identity;
import org.helianto.core.number.Sequenceable;

/**
 * Event interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface Event extends Sequenceable {
	
    /**
     * Date issued.
     */
	public Date getIssueDate();
	
	/**
	 * Event owner.
	 */
	public Identity getOwner();

}
