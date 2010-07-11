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

import org.helianto.core.Category;
import org.helianto.core.CategoryFilter;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation to category interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("categoryMgr")
public class CategoryMgrImpl implements CategoryMgr {
    
	public List<Category> findCategories(CategoryFilter categoryFilter) {
    	List<Category> categoryList = (List<Category>) categoryDao.find(categoryFilter);
    	logger.debug("Found category list of size {}", categoryList.size());
    	return categoryList;
	}

	public Category storeCategory(Category category) {
		Category managedCategory = categoryDao.merge(category);
    	logger.debug("Stored category  "+managedCategory);
    	return managedCategory;
	}

	public void removeCategory(Category category) {
		// TODO Auto-generated method stub
		
	}


    //- collabs
    private FilterDao<Category, CategoryFilter> categoryDao;
    
    @Resource(name="categoryDao")
    public void setCategoryDao(FilterDao<Category, CategoryFilter> categoryDao) {
        this.categoryDao = categoryDao;
    }

    private final Logger logger = LoggerFactory.getLogger(CategoryMgrImpl.class);


}
