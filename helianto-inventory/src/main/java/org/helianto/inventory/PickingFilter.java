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

package org.helianto.inventory;

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;

/**
 * Picking filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PickingFilter extends AbstractUserBackedCriteriaFilter {
	
	private static final long serialVersionUID = 1L;
    private long internalNumber;
    
    /**
     * Internal number filter.
     */
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("internalNumber", getInternalNumber(), mainCriteriaBuilder);
	}

	public boolean isSelection() {
		return getInternalNumber()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
	}
	
	public String getObjectAlias() {
		return "picking";
	}
	public void reset() {
	}

}
