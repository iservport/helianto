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

package org.helianto.partner.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.partner.form.PrivateSegmentForm;

/**
 * Private segment filter adapter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class PrivateSegmentFilterAdapter 
	extends AbstractTrunkFilterAdapter<PrivateSegmentForm> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PrivateSegmentFilterAdapter(PrivateSegmentForm form) {
		super(form);
	}
	
	public boolean isSelection() {
		return getForm().getSegmentAlias()!=null && getForm().getSegmentAlias().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("segmentAlias", getForm().getSegmentAlias(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
		appendLikeFilter("segmentName", getForm().getSegmentName(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "segmentAlias";
	}

}
