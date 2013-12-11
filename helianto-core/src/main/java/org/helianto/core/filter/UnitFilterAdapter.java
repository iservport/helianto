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

package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractEntityIdFilterAdapter;
import org.helianto.core.form.UnitForm;

/**
 * Unit filter adapter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class UnitFilterAdapter 
	extends AbstractEntityIdFilterAdapter<UnitForm> 
{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public UnitFilterAdapter(UnitForm form) {
		super(form);
	}
	
	public boolean isSelection() {
		return super.isSelection() && getForm().getUnitCode()!=null && getForm().getUnitCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
    	appendEqualFilter("unitCode", getForm().getUnitCode(), mainCriteriaBuilder);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
    	appendEqualFilter("unitSymbol", getForm().getUnitSymbol(), mainCriteriaBuilder);
		if (getForm().getNature()!=' ' && getForm().getNature()!='_' && getForm().getNature()>0) {
			mainCriteriaBuilder.appendAnd().appendSegment("nature", "like", "lower")
				.appendLike(String.valueOf(getForm().getNature()));
		}
	}

	
	@Override
	public String getOrderByString() {
		return "unitCode";
	}
	
}
