package org.helianto.message.form;

import org.helianto.core.Controllable;
import org.helianto.core.filter.form.ControlForm;
import org.helianto.message.ControlSource;

/**
 * Follow up form interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface FollowUpForm<C extends ControlSource> extends Controllable, ControlForm<C> {

    /**
     * Notification option.
     */
    public char getNotificationOption();
    
    /**
     * Decison getter.
     */
    public char getDecision();
}
