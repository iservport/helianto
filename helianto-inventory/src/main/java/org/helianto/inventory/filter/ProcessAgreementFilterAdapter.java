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

package org.helianto.inventory.filter;

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.base.AbstractSequenceFilterAdapterDecorator;
import org.helianto.inventory.ProcessAgreement;

/**
 * Process agreement filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessAgreementFilterAdapter extends AbstractSequenceFilterAdapterDecorator<ProcessAgreement> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param filter
	 */
	public ProcessAgreementFilterAdapter(ProcessAgreement filter) {
		super(filter);
	}

	public void reset() { }

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		if (getFilter().getPartner()!=null) {
			appendEqualFilter("partner.id", getFilter().getPartner().getId(), mainCriteriaBuilder);
		}
	}

}
