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

package org.helianto.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Category;
import org.helianto.core.CategoryFilter;
import org.helianto.core.dao.CategoryDao;
import org.helianto.core.filter.SelectionStrategy;

/**
 * Default implementation to category interface.
 * 
 * @author Maurício Fernandes de Castro
 */
public class CategoryMgrImpl implements CategoryMgr {
    
    private CategoryDao categoryDao;
    private SelectionStrategy<CategoryFilter> categorySelectionStrategy;
    
	public List<Category> findCategories(CategoryFilter categoryFilter) {
    	String criteria = categorySelectionStrategy.createCriteriaAsString(categoryFilter, "category");
    	List<Category> categoryList = categoryDao.findCategories(criteria);
    	if (logger.isDebugEnabled() && categoryList!=null) {
    		logger.debug("Found category list of size "+categoryList.size());
    	}
    	return categoryList;
	}

	public Category storeCategory(Category category) {
		Category managedCategory = categoryDao.mergeCategory(category);
    	if (logger.isDebugEnabled()) {
    		logger.debug("Stored category  "+managedCategory);
    	}
    	return managedCategory;
	}

	public void removeCategory(Category category) {
		// TODO Auto-generated method stub
		
	}


    //- collabs
    @Resource
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Resource
	public void setCategorySelectionStrategy(SelectionStrategy<CategoryFilter> categorySelectionStrategy) {
		this.categorySelectionStrategy = categorySelectionStrategy;
	}

    private final Log logger = LogFactory.getLog(CategoryMgrImpl.class);


}
