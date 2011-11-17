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

package org.helianto.core.filter.classic;

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;
import org.helianto.core.Entity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.CategoryFormFilterAdapter;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Category filter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 * @see CategoryFormFilterAdapter
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
		getForm().setCategoryCode("");
		getForm().setCategoryName("");
	}
	
	public boolean isSelection() {
		return (getForm().getCategoryCode().length()>0);
	}

	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
        super.preProcessFilter(mainCriteriaBuilder);
        logger.debug("CategoryGroup is: '{}'", getForm().getCategoryGroup());
        mainCriteriaBuilder.appendAnd().appendSegment("categoryGroup", "=")
        .append(getForm().getCategoryGroup());
        return true;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
        logger.debug("CategoryCode is: '{}'",getForm().getCategoryCode());
    	appendEqualFilter("categoryCode", getForm().getCategoryCode(), (OrmCriteriaBuilder) mainCriteriaBuilder);
    	reset();
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("categoryName", getForm().getCategoryName(), (OrmCriteriaBuilder) mainCriteriaBuilder);
	}

    private static Logger logger = LoggerFactory.getLogger(CategoryFilterAdapter.class);

}
