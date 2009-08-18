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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Category filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class CategoryFilter extends AbstractUserBackedCriteriaFilter {

	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static CategoryFilter categoryFilterFactory(User user) {
		CategoryFilter categoryFilter = new CategoryFilter();
		categoryFilter.setUser(user);
		return categoryFilter;
	}
	
	private static final long serialVersionUID = 1L;
	private CategoryGroup categoryGroup;
	private String categoryCode;
	private String categoryNameLike;
	
	/**
	 * Default constructor
	 */
	public CategoryFilter() {
		setCategoryGroup(CategoryGroup.NOT_DEFINED);
		setCategoryCode("");
		setCategoryNameLike("");
	}
	
	public void reset() {
		setCategoryCode("");
		setCategoryNameLike("");
	}
	
	public boolean isSelection() {
		return (getCategoryCode().length()>0);
	}

	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
        // force to filter by group
        if (logger.isDebugEnabled()) {
            logger.debug("CategoryGroup is: '"+getCategoryGroup()+"'");
        }
        mainCriteriaBuilder.appendAnd().appendSegment("categoryGroup", "=")
        .append(getCategoryGroup().getValue());
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
        if (logger.isDebugEnabled()) {
            logger.debug("CategoryCode is: '"+getCategoryCode()+"'");
        }
    	appendEqualFilter("categoryCode", getCategoryCode(), mainCriteriaBuilder);
    	reset();
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("categoryNameLike", getCategoryNameLike(), mainCriteriaBuilder);
	}

	public String getObjectAlias() {
		return "category";
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
	public void setCategoryGroup(CategoryGroup categoryGroup) {
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

    private static Log logger = LogFactory.getLog(CategoryFilter.class);

}
