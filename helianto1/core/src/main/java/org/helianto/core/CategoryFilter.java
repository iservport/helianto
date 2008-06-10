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

import java.io.Serializable;

import org.helianto.core.CategoryGroup;
import org.helianto.core.User;
import org.helianto.core.filter.UserBackedFilter;

/**
 * Category filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class CategoryFilter implements	Serializable, UserBackedFilter {

	private static final long serialVersionUID = 1L;
	private User user;
	private CategoryGroup categoryGroup;
	private String categoryCode;
	private String categoryNameLike;
	
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 * @param categoryGroup
	 */
	public static CategoryFilter categoryFilterFactory(User user, CategoryGroup categoryGroup) {
		CategoryFilter categoryFilter = new CategoryFilter();
		categoryFilter.setUser(user);
		if (categoryGroup==null) {
			throw new IllegalArgumentException("Unable to create category filter, category group required");
		}
		categoryFilter.setCategoryGroup(categoryGroup);
		return categoryFilter;
	}
	
	public void reset() {
		setCategoryCode("");
		setCategoryNameLike("");
	}
	
	/**
	 * Category code.
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * Category group.
	 */
	public CategoryGroup getCategoryGroup() {
		return categoryGroup;
	}
	protected void setCategoryGroup(CategoryGroup categoryGroup) {
		this.categoryGroup = categoryGroup;
	}
	
	/**
	 * Category name.
	 */
	public String getCategoryNameLike() {
		return categoryNameLike;
	}
	public void setCategoryNameLike(String categoryNameLike) {
		this.categoryNameLike = categoryNameLike;
	}

	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append("\n[");
        sb.append("\n user").append("='").append(getUser()).append("' ");
        sb.append("\n categoryGroup").append("='").append(getCategoryGroup()).append("' ");
        sb.append("\n categoryCode").append("='").append(getCategoryCode()).append("' ");
        sb.append("\n categoryNameLike").append("='").append(getCategoryNameLike()).append("' ");
        sb.append("]");
      
        return sb.toString();
	}

}
