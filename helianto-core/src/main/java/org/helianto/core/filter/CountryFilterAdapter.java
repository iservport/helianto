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
import org.helianto.core.domain.Country;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;

/**
 * Country filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CountryFilterAdapter extends AbstractRootFilterAdapter<Country> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 */
	public CountryFilterAdapter(Country country) {
		super(country);
	}
	
	/**
	 * Filter reset.
	 */
	public void reset() {
		getForm().setCountryCode("");
		getForm().setCountryName("");
	}

	/**
	 * Selection criterion.
	 */
	public boolean isSelection() {
		return getForm().getCountryCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("countryCode", getForm().getCountryCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("countryName", getForm().getCountryName(), mainCriteriaBuilder);
	}

}
