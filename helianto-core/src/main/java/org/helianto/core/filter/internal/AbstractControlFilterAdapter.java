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

import java.util.Date;

import org.helianto.core.Controllable;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilter;
import org.helianto.core.filter.classic.AbstractInternalFilterAdapterDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filters that require a <code>Controllable</code> form.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractControlFilterAdapter <T extends Controllable> 
	extends AbstractInternalFilterAdapterDecorator<T> 
{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Form constructor.
	 * 
	 * @param form
	 */
	public AbstractControlFilterAdapter(T form) {
		super(form);
	}
	
	/**
	 * Decorator constructor.
	 * 
	 * @param form
	 * @param decoratedFilter
	 */
	public AbstractControlFilterAdapter(T form, AbstractFilter decoratedFilter) {
		super(form, decoratedFilter);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendResolution(mainCriteriaBuilder);
		appendEqualFilter("complete", getForm().getComplete(), true, mainCriteriaBuilder);
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

	/**
	 * Field name.
	 */
	public String getDateFieldName() {
		return "nextCheckDate";
	}
	
	/**
	 * Next check date.
	 */
	public Date getToDate() {
		return getForm().getNextCheckDate();
	}
	
	 private static Logger logger = LoggerFactory.getLogger(AbstractControlFilterAdapter.class);
	
}
