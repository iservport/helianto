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

package org.helianto.core.filter.internal;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.form.EventForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filters that require an <code>EventForm</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractEventFilterAdapter<T extends EventForm> 
	extends AbstractDateFilterAdapter<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public AbstractEventFilterAdapter(T filter) {
		super(filter);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendResolution(mainCriteriaBuilder);
	}

	/**
	 * Resolution appender.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void appendResolution(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getResolution()=='N') {
			mainCriteriaBuilder.appendAnd().appendSegment("resolution", "IN ").append("('P', 'T')");
			logger.debug("Resolution constrained to not started.");
		}
		else {
			appendEqualFilter("resolution", getForm().getResolution(), mainCriteriaBuilder);
		}
	}

	 private static Logger logger = LoggerFactory.getLogger(AbstractEventFilterAdapter.class);
		
}
