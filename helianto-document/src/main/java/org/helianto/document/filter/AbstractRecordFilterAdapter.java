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

package org.helianto.document.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilter;
import org.helianto.core.filter.base.AbstractSequenceFilterAdapterDecorator;
import org.helianto.document.Recordable;

/**
 * Base class to filters that require a <code>Record</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractRecordFilterAdapter <T extends Recordable> extends AbstractSequenceFilterAdapterDecorator<T> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public AbstractRecordFilterAdapter(T filter) {
		super(filter);
	}
	
	/**
	 * Decorator constructor.
	 * 
	 * @param filter
	 * @param decoratedFilter
	 */
	public AbstractRecordFilterAdapter(T filter, AbstractFilter decoratedFilter) {
		super(filter, decoratedFilter);
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
		appendEqualFilter("resolution", getForm().getResolution(), mainCriteriaBuilder);
	}

}
