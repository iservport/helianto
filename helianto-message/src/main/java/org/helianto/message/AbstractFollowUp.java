/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.message;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.helianto.document.base.AbstractRecord;

/**
 * Base class to follow up.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractFollowUp extends AbstractRecord implements Comparable<AbstractFollowUp> {

    private static final long serialVersionUID = 1L;
    private String followUpDesc;
    private char notificationOption;

    /** 
     * Default constructor.
     */
    protected AbstractFollowUp() {
    	super();
    	setNotificationOptionAsEnum(NotificationOption.REPORTER);
    }

    /**
     * Description.
     */
    @Column(length=512)
    public String getFollowUpDesc() {
        return this.followUpDesc;
    }
    public void setFollowUpDesc(String followUpDesc) {
        this.followUpDesc = followUpDesc;
    }

    /**
     * Notification option.
     */
    public char getNotificationOption() {
    	return this.notificationOption;
    }
    public void setNotificationOption(char notificationOption) {
    	this.notificationOption = notificationOption;
    }
    public void setNotificationOptionAsEnum(NotificationOption notificationOption) {
    	this.notificationOption = notificationOption.getValue();
    }
    
    /**
     * @see Comparable interface, descending order
     */
    public int compareTo(AbstractFollowUp nextFollowUp) {
        return (int) (-getIssueDate().getTime() + nextFollowUp.getIssueDate().getTime());
    }

}
