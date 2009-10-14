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


package org.helianto.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.controller.AbstractFilterOnlyFormAction;
import org.helianto.core.Category;
import org.helianto.core.CategoryFilter;
import org.helianto.core.CategoryGroup;
import org.helianto.core.service.CategoryMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to select categories.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("categoryFilterAction")
public class CategoryFilterFormAction extends AbstractFilterOnlyFormAction<CategoryFilter, Category> {

	@Override
	public CategoryFilter doCreateFilter() throws Exception {
		return CategoryFilter.categoryFilterFactory(getAuthorizedUser());
	}

	@Override
	protected boolean doPreProcess(CategoryFilter filter, RequestContext context) throws Exception {
    	String categoryGroup = getFormObjectScope().getScope(context).getRequiredString("categoryGroup");
		filter.setCategoryGroup(CategoryGroup.valueOf(categoryGroup));
        if (logger.isDebugEnabled()) {
            logger.debug("CategoryGroup set in filter is "+filter.getCategoryGroup());
        }
        return true;
	}

	@Override
	protected List<Category> doApplyFilter(CategoryFilter filter) {
		return categoryMgr.findCategories(filter);
	}

	@Override
	public String getTargetAttributeName() {
		return "category";
	}
	
	// collabs
	
	private CategoryMgr categoryMgr;

	@Resource
    public void setCategoryMgr(CategoryMgr categoryMgr) {
    	this.categoryMgr = categoryMgr;
    }
    
}
