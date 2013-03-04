package org.helianto.document;

import java.util.Date;

import org.helianto.core.domain.Identity;
import org.helianto.core.domain.type.TrunkEntity;

/**
 * Event interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface Event extends TrunkEntity {
	
    /**
     * Date issued.
     */
	public Date getIssueDate();
	
	/**
	 * Event owner.
	 */
	public Identity getOwner();

}
