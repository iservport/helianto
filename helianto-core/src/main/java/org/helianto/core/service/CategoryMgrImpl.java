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

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.CategoryFormFilterAdapter;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.CategoryForm;
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
    
	public List<Category> findCategories(CategoryForm form) {
		CategoryFormFilterAdapter filter = new CategoryFormFilterAdapter(form);
    	List<Category> categoryList = (List<Category>) categoryDao.find(filter);
    	logger.debug("Found category list of size {}", categoryList.size());
    	return categoryList;
	}

	public List<Category> findCategories(Filter categoryFilter) {
    	List<Category> categoryList = (List<Category>) categoryDao.find(categoryFilter);
    	logger.debug("Found category list of size {}", categoryList.size());
    	return categoryList;
	}

	public Category storeCategory(Category category) {
		categoryDao.saveOrUpdate(category);
    	return category;
	}

	public void removeCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
	
	public Category installCategory(Entity entity, CategoryGroup categoryGroup, String categoryCode, String categoryName) {
    	Category category = categoryDao.findUnique(entity, categoryGroup, categoryCode);
    	if (category!=null) {
        	logger.debug("Found category {}", category);
    		return category;
    	}
    	category = new Category(entity, categoryGroup, categoryCode);
    	category.setCategoryName(categoryName);
    	categoryDao.saveOrUpdate(category);
    	logger.debug("Category {} installed.", category);
    	categoryDao.flush();
    	return category;
	}


    //- collabs
    private FilterDao<Category> categoryDao;
    
    @Resource(name="categoryDao")
    public void setCategoryDao(FilterDao<Category> categoryDao) {
        this.categoryDao = categoryDao;
    }

    private final Logger logger = LoggerFactory.getLogger(CategoryMgrImpl.class);

}
