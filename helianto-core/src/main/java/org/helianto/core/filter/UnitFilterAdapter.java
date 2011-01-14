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

import org.helianto.core.CategoryGroup;
import org.helianto.core.Unit;
import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Unit filter adapter.
 * 
 * @author Maur�cio Fernandes de Castro
 */
public class UnitFilterAdapter extends AbstractTrunkFilterAdapter<Unit> {
	
	private static final long serialVersionUID = 1L;
	private CategoryGroup categoryGroup;
	
	/**
	 * Default constructor.
	 */
	public UnitFilterAdapter(Unit unit) {
		super(unit);
	}
	
	public void reset() {
		getFilter().setUnitCode("");
		getFilter().setUnitName("");
	}

	public boolean isSelection() {
		return getFilter().getUnitCode().length()>0;
	}

	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
    	if (getCategoryGroup()!=null) {
        	appendEqualFilter("category.categoryGroup", getCategoryGroup().getValue(), mainCriteriaBuilder);
    	}
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
    	if (getFilter().getCategory()!=null) {
        	appendEqualFilter("category.id", getFilter().getCategory().getId(), mainCriteriaBuilder);
    	}
    	appendLikeFilter("unitNameLike", getFilter().getUnitName(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
    	appendEqualFilter("unitCode", getFilter().getUnitCode(), mainCriteriaBuilder);
	}

	/**
	 * Category group.
	 */
	public CategoryGroup getCategoryGroup() {
		return categoryGroup;
	}
	public void setCategoryGroup(CategoryGroup categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

}