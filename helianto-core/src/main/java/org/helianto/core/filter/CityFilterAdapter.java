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
import org.helianto.core.filter.base.AbstractContextFilterAdapter;
import org.helianto.core.form.CityForm;

/**
 * City filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CityFilterAdapter extends AbstractContextFilterAdapter<CityForm> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public CityFilterAdapter(CityForm form) {
		super(form);
	}
	
	/**
	 * Selection criterion.
	 */
	public boolean isSelection(CityForm form) {
		return super.isSelection(form)
				&& form.getCityCode()!=null 
				&& form.getCityCode().length()>0;
	}
	
	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("context.id", getForm().getContextId(), mainCriteriaBuilder);
		appendEqualFilter("cityCode", getForm().getCityCode(), mainCriteriaBuilder);
	}
	
	@Override
	public boolean isSearch() {
		return getForm().getSearchString()!=null && getForm().getSearchString().length()>0;
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
		appendEqualFilter("state.stateCode", getForm().getStateCode(), mainCriteriaBuilder);
		appendEqualFilter("state.id", getForm().getStateId(), mainCriteriaBuilder);
		appendEqualFilter("priority", getForm().getPriority(), mainCriteriaBuilder);
	}

	@Override
	protected void doSearch(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
		OrmCriteriaBuilder searchCriteriaBuilder = new OrmCriteriaBuilder(getObjectAlias());
		searchCriteriaBuilder.appendSegment("cityCode", "like", "lower")
			.appendStartLike(getForm().getSearchString().toLowerCase());
		searchCriteriaBuilder.appendOr().appendSegment("cityName", "like", "lower")
			.appendLike(getForm().getSearchString().toLowerCase());
		mainCriteriaBuilder.appendAnd().append(searchCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "cityCode";
	}
	
}
