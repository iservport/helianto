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
import org.helianto.core.filter.base.AbstractEntityIdFilterAdapter;
import org.helianto.core.form.CategoryForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Category form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryFormFilterAdapter extends AbstractEntityIdFilterAdapter<CategoryForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 */
	public CategoryFormFilterAdapter(CategoryForm category) {
		super(category);
	}
	
	public boolean isSelection() {
		return super.isSelection() && (getForm().getCategoryCode()!=null && getForm().getCategoryCode().length()>0);
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
        logger.debug("CategoryGroup is: '{}'", getForm().getCategoryGroup());
    	appendEqualFilter("categoryGroup", getForm().getCategoryGroup(), (OrmCriteriaBuilder) mainCriteriaBuilder);
        logger.debug("CategoryCode is: '{}'",getForm().getCategoryCode());
    	appendEqualFilter("categoryCode", getForm().getCategoryCode(), (OrmCriteriaBuilder) mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
		appendLikeFilter("categoryName", getForm().getCategoryName(), (OrmCriteriaBuilder) mainCriteriaBuilder);
	}

    private static Logger logger = LoggerFactory.getLogger(CategoryFormFilterAdapter.class);

}
