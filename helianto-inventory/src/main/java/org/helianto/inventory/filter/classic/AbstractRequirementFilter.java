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

package org.helianto.inventory.filter.classic;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.document.filter.classic.AbstractControlFilter;

/**
 * Base requirement filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractRequirementFilter extends AbstractControlFilter {

	private static final long serialVersionUID = 1L;
	private ProcessDocument process;

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		if (process!=null) {
			appendEqualFilter("document.id", getProcess().getId(), mainCriteriaBuilder);
		}
	}

	/**
	 * Process document filter.
	 */
	public ProcessDocument getProcess() {
		return process;
	}
	public void setProcess(ProcessDocument process) {
		this.process = process;
	}

}
