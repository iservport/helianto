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

package org.helianto.message.filter.classic;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.filter.classic.AbstractRecordFilter;

/**
 * Base class to follow up filters.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public abstract class AbstractFollowUpFilter extends AbstractRecordFilter {

	private static final long serialVersionUID = 1L;
    private char notificationOption;

    /** 
     * Default constructor.
     */
    protected AbstractFollowUpFilter() {
    	super();
    	setNotificationOption(' ');
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

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("notificationOption", getNotificationOption(), mainCriteriaBuilder);
	}

}
