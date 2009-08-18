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

package org.helianto.core;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Unit filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class UnitFilter extends AbstractUserBackedCriteriaFilter {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static UnitFilter unitFilterFactory(User user) {
		UnitFilter unitFilter = new UnitFilter();
		unitFilter.setUser(user);
		return unitFilter;
	}
	
	/**
	 * Factory method.
	 * 
	 * @param category
	 */
	public static UnitFilter unitFilterFactory(Category category) {
		UnitFilter unitFilter = new UnitFilter();
		unitFilter.setEntity(category.getEntity());
		unitFilter.setCategory(category);
		return unitFilter;
	}
	
	private static final long serialVersionUID = 1L;
	private CategoryGroup categoryGroup;
	private Category category;
	private String unitCode;
	private String unitNameLike;
	
	/**
	 * Default constructor.
	 */
	public UnitFilter() {
		setUnitCode("");
		setUnitNameLike("");
	}
	
	public void reset() {
		setUnitCode("");
		setUnitNameLike("");
	}

	public boolean isSelection() {
		return getUnitCode().length()>0;
	}

	public String getObjectAlias() {
		return "unit";
	}

	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
    	if (getCategoryGroup()!=null) {
        	appendEqualFilter("category.categoryGroup", getCategoryGroup().getValue(), mainCriteriaBuilder);
    	}
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
    	if (getCategory()!=null) {
        	appendEqualFilter("category.id", getCategory().getId(), mainCriteriaBuilder);
    	}
    	appendLikeFilter("unitNameLike", getUnitNameLike(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
    	appendEqualFilter("unitCode", getUnitCode(), mainCriteriaBuilder);
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

	/**
	 * Category.
	 */
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Unit code.
	 */
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * Unit name like.
	 */
	public String getUnitNameLike() {
		return unitNameLike;
	}
	public void setUnitNameLike(String unitNameLike) {
		this.unitNameLike = unitNameLike;
	}

}
