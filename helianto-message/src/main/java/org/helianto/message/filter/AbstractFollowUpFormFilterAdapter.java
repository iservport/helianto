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

package org.helianto.message.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractControlFilterAdapter;
import org.helianto.core.filter.form.ControlForm;
import org.helianto.message.ControlSource;
import org.helianto.message.form.FollowUpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to follow up filter adapters.
 *
 * @param <F>
 * @param <C>
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFollowUpFormFilterAdapter<F extends FollowUpForm<C>, C extends ControlSource> extends AbstractControlFilterAdapter<F> {

	private static final long serialVersionUID = 1L;

    /** 
     * Default constructor.
     * 
     * @param form
     */
    protected AbstractFollowUpFormFilterAdapter(F form) {
    	super(form);
    }
        
	/**
	 * True if there is a segment for control criterion.
	 */
	protected boolean hasControlCriterion() {
		if (ControlForm.class.isAssignableFrom(getForm().getClass())) {
			return ((ControlForm<C>) getForm()).getControlId()>0;
		}
		throw new UnsupportedOperationException("Must be descendant of ControlForm to use this filter.");
	}
	
	@Override
	public final void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (hasControlCriterion()) {
			preProcessControlFilter(mainCriteriaBuilder, getForm());
		}
	}
	
	protected void preProcessControlFilter(OrmCriteriaBuilder mainCriteriaBuilder, ControlForm<C> controlForm) {
        logger.debug("Control is: '{}'", controlForm);
		appendEqualFilter(new StringBuilder(controlForm.getControlName()).append(".id").toString(), controlForm.getControlId(), mainCriteriaBuilder);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("notificationOption", getForm().getNotificationOption(), mainCriteriaBuilder);
		appendEqualFilter("decision", getForm().getDecision(), mainCriteriaBuilder);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractFollowUpFormFilterAdapter.class);

}
