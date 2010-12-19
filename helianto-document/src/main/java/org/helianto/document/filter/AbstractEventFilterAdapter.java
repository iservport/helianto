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

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.AbstractSequenceFilterAdapter;
import org.helianto.document.Event;

/**
 * Filter base class to be used with event hierarchy.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractEventFilterAdapter<T extends Event> extends AbstractSequenceFilterAdapter<T> {

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
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		
//		appendEqualFilter("internalNumber", getInternalNumber(), mainCriteriaBuilder);
	}
	

}
