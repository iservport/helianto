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
import org.helianto.core.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Category filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryFilterAdapter extends AbstractTrunkFilterAdapter<Category> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 */
	public CategoryFilterAdapter(Category category) {
		super(category);
	}
	
	/**
	 * Default constructor
     * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
	 */
	public CategoryFilterAdapter(Entity entity, CategoryGroup categoryGroup, String categoryCode) {
		super(new Category(entity, categoryGroup, categoryCode));
	}
	
	public void reset() {
		getFilter().setCategoryCode("");
		getFilter().setCategoryName("");
	}
	
	public boolean isSelection() {
		return (getFilter().getCategoryCode().length()>0);
	}

	@Override
	public void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
        super.preProcessFilter(mainCriteriaBuilder);
        logger.debug("CategoryGroup is: '{}'", getFilter().getCategoryGroup());
        mainCriteriaBuilder.appendAnd().appendSegment("categoryGroup", "=")
        .append(getFilter().getCategoryGroup());
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
        logger.debug("CategoryCode is: '{}'",getFilter().getCategoryCode());
    	appendEqualFilter("categoryCode", getFilter().getCategoryCode(), mainCriteriaBuilder);
    	reset();
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("categoryName", getFilter().getCategoryName(), mainCriteriaBuilder);
	}

    private static Logger logger = LoggerFactory.getLogger(CategoryFilterAdapter.class);

}
