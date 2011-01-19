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

import java.beans.PropertyEditor;

import javax.annotation.Resource;

import org.helianto.controller.AbstractEditTargetFormAction;
import org.helianto.core.Category;
import org.helianto.core.filter.classic.CategoryFilter;
import org.helianto.core.service.CategoryMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store categories.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("categoryAction")
public class CategoryFormAction extends AbstractEditTargetFormAction<Category> {

	@Override
	protected Category doCreateTarget(RequestContext context) throws Exception {
		CategoryFilter filter = (CategoryFilter) getFormObjectScope().getScope(context).getRequired("filter");
		return new Category(getAuthorizedEntity(), filter.getCategoryGroup(), "");
	}

	@Override
	protected Category doStoreTarget(Category detachedTarget) throws Exception {
		return categoryMgr.storeCategory(detachedTarget);
	}

	@Override
	protected Category doPrepareTarget(RequestContext context, Category target) throws Exception {
		return target;
	}

	@Resource(name="categoryPropertyEditor")
	@Override
	public void setTargetPropertyEditor(PropertyEditor targetPropertyEditor) {
		internalTargetPropertyEditorSetter(targetPropertyEditor, Category.class);
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
