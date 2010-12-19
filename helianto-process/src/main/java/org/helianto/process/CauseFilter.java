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

package org.helianto.process;

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;

/**
 * Cause filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CauseFilter extends AbstractUserBackedCriteriaFilter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
	}

	public String getObjectAlias() {
		return "cause";
	}

	public void reset() {
	}

}
