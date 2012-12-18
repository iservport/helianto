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
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.base.AbstractControlFilterAdapter;
import org.helianto.document.base.AbstractRepeatable;
import org.helianto.message.AbstractFollowUp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to follow up filter adapters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFollowUpFilterAdapter<T extends AbstractFollowUp> extends AbstractControlFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
	private AbstractRepeatable parent;

    /** 
     * Default constructor.
     * 
     * @param target
     */
    protected AbstractFollowUpFilterAdapter(T target) {
    	super(target);
    }
    
	protected void appendEntityFilter(Entity entity, OrmCriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment(new StringBuilder(getControlFieldName()).append(".entity.id").toString(), "=").append(entity.getId());
    }
    
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getParent()!=null) {
	        logger.debug("Parent control is: '{}'", getParent());
			appendEqualFilter(new StringBuilder(getControlFieldName()).append(".id").toString(), getParent().getId(), mainCriteriaBuilder);
			connect = true;
		}
		return connect;
	}
	
	/**
	 * Subclasses may change control field name, required to pre-process parent filter.
	 */
	protected String getControlFieldName() {
		return "control";
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("notificationOption", getForm().getNotificationOption(), mainCriteriaBuilder);
	}
	
	/**
	 * Parent filter.
	 */
	public AbstractRepeatable getParent() {
		return parent;
	}
	public void setParent(AbstractRepeatable parent) {
		this.parent = parent;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractFollowUpFilterAdapter.class);

}
