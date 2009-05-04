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


package org.helianto.core.orm;

import org.helianto.core.Category;
import org.helianto.core.CategoryFilter;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * Category data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("categoryDao")
public class DefaultCategoryDao extends AbstractFilterDao<Category, CategoryFilter> {

	@Override
	protected void preProcessFilter(CategoryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        // force to filter by group
        if (logger.isDebugEnabled()) {
            logger.debug("CategoryGroup is: '"+filter.getCategoryGroup()+"'");
        }
        mainCriteriaBuilder.appendAnd().appendSegment("categoryGroup", "=")
        .append(filter.getCategoryGroup().getValue());
	}

	@Override
	protected void doFilter(CategoryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("categoryNameLike", filter.getCategoryNameLike(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(CategoryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        if (logger.isDebugEnabled()) {
            logger.debug("CategoryCode is: '"+filter.getCategoryCode()+"'");
        }
    	appendEqualFilter("categoryCode", filter.getCategoryCode(), mainCriteriaBuilder);
    	filter.reset();
	}

	@Override
	protected boolean isSelection(CategoryFilter filter) {
		return (filter.getCategoryCode().length()>0);
	}

	@Override
	protected String[] getParams() {
		return new String[] { "entity", "categoryGroup" };
	}

	@Override
	public Class<? extends Category> getClazz() {
		return Category.class;
	}

}
