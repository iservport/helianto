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
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.core.filter.form.CategoryForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Category form filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryFormFilterAdapter extends AbstractTrunkFilterAdapter<CategoryForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 */
	public CategoryFormFilterAdapter(CategoryForm category) {
		super(category);
	}
	
	public void reset() {
		getForm().reset();
	}
	
	public boolean isSelection() {
		return (getForm().getCategoryCode()!=null && getForm().getCategoryCode().length()>0);
	}

	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
        super.preProcessFilter(mainCriteriaBuilder);
        logger.debug("CategoryGroup is: '{}'", getForm().getCategoryGroup());
        if (getForm().getCategoryGroup()!=' ') {
            mainCriteriaBuilder.appendAnd().appendSegment("categoryGroup", "=")
            	.append(getForm().getCategoryGroup());
            return true;
        }
        return false;
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

    private static Logger logger = LoggerFactory.getLogger(CategoryFormFilterAdapter.class);

}
