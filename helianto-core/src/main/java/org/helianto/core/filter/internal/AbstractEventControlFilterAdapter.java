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
import org.helianto.core.form.EventControlForm;

/**
 * Base class to filters that require a <code>ControlForm</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractEventControlFilterAdapter <T extends EventControlForm> 
	extends AbstractEventFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Form constructor.
	 * 
	 * @param form
	 */
	public AbstractEventControlFilterAdapter(T form) {
		super(form);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("frequency", getForm().getFrequency(), mainCriteriaBuilder);
	}

}
