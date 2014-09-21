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
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.form.CountryForm;


/**
 * Country form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class CountryFormFilterAdapter 
	extends AbstractFilterAdapter<CountryForm> 
{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public CountryFormFilterAdapter(CountryForm form) {
		super(form);
	}
	
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getForm().getContextId()>0) {
			appendEqualFilter("operator.id", getForm().getContextId(), mainCriteriaBuilder);
			return true;
		}
		return false;
	}
	
	/**
	 * Selection criterion.
	 */
	public boolean isSelection() {
		return getForm().getContextId()>0 && getForm().getCountryCode()!=null && getForm().getCountryCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("countryCode", getForm().getCountryCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "countryCode";
	}
	
	@Override
	protected String[] getFieldNames() {
		return new String[] { "countryCode", "countryName" };
	}
	
}
