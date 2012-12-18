package org.helianto.message.form;

import org.helianto.core.form.EventControlForm;

/**
 * Follow up form interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface FollowUpForm 
	extends EventControlForm {

    /**
     * Notification option.
     */
    public char getNotificationOption();
    
    /**
     * Decison getter.
     */
    public char getDecision();
}
