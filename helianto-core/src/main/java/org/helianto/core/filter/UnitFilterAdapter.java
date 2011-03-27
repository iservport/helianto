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

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;
import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;

/**
 * Unit filter adapter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class UnitFilterAdapter extends AbstractTrunkFilterAdapter<Unit> {
	
	private static final long serialVersionUID = 1L;
	private CategoryGroup categoryGroup;
	
	/**
	 * Default constructor.
	 * 
	 * @param unit
	 */
	public UnitFilterAdapter(Unit unit) {
		super(unit);
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param unitCode
	 */
	public UnitFilterAdapter(Entity entity, String unitCode) {
		this(new Unit(entity, unitCode));
	}
	
	/**
	 * Convenience constructor.
	 * 
	 * @param category
	 * @param unitCode
	 */
	public UnitFilterAdapter(Category category, String unitCode) {
		this(new Unit(category, unitCode));
	}
	
	public void reset() {
		getFilter().setUnitCode("");
		getFilter().setUnitName("");
	}

	public boolean isSelection() {
		return getFilter().getUnitCode().length()>0;
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
    	if (getCategoryGroup()!=null) {
        	appendEqualFilter("category.categoryGroup", getCategoryGroup().getValue(), true, mainCriteriaBuilder);
    	}
    	if (getFilter().getCategory()!=null) {
        	appendEqualFilter("category.id", getFilter().getCategory().getId(), mainCriteriaBuilder);
    	}
    	appendLikeFilter("unitName", getFilter().getUnitName(), mainCriteriaBuilder);
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
