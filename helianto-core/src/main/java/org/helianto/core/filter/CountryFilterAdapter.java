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

import org.helianto.core.Country;
import org.helianto.core.criteria.CriteriaBuilder;

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
		getFilter().setCountryCode("");
		getFilter().setCountryName("");
	}

	/**
	 * Selection criterion.
	 */
	public boolean isSelection() {
		return getFilter().getCountryCode().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("countryCode", getFilter().getCountryCode(), mainCriteriaBuilder);
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("countryName", getFilter().getCountryName(), mainCriteriaBuilder);
	}

}
