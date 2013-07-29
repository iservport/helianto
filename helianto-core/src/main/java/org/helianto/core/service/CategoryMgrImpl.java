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

import org.helianto.core.CategoryMgr;
import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.CategoryFormFilterAdapter;
import org.helianto.core.filter.Filter;
import org.helianto.core.form.CategoryForm;
import org.helianto.core.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation to category interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("categoryMgr")
public class CategoryMgrImpl implements CategoryMgr {
	
	@Transactional(readOnly=true)
	public long countCategory(final Entity entity, final char categoryGroup, final String searchString) {
		@SuppressWarnings("serial")
		CategoryForm form = new CategoryForm() {
			public Entity getEntity() { return entity; }
			public char getCategoryGroup() { return categoryGroup; }
			public String getCategoryCode() { return searchString; }
			public String getCategoryName() { return "";}
		};
		return categoryRepository.count(new CategoryFormFilterAdapter(form));
	}
    
	@Transactional(readOnly=true)
	public List<Category> findCategories(CategoryForm form) {
		CategoryFormFilterAdapter filter = new CategoryFormFilterAdapter(form);
    	List<Category> categoryList = (List<Category>) categoryRepository.find(filter);
    	logger.debug("Found category list of size {}", categoryList.size());
    	return categoryList;
	}

	@Transactional(readOnly=true)
	public List<Category> findCategories(Filter categoryFilter) {
    	List<Category> categoryList = (List<Category>) categoryRepository.find(categoryFilter);
    	logger.debug("Found category list of size {}", categoryList.size());
    	return categoryList;
	}

	@Transactional
	public Category storeCategory(Category category) {
		categoryRepository.saveAndFlush(category);
    	return category;
	}

	public void removeCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional
	public Category installCategory(Entity entity, CategoryGroup categoryGroup, String categoryCode, String categoryName) {
    	Category category = categoryRepository.findByEntityAndCategoryGroupAndCategoryCode(entity, categoryGroup.getValue(), categoryCode);
    	if (category!=null) {
        	logger.debug("Found category {}", category);
    		return category;
    	}
    	category = categoryRepository.saveAndFlush(new Category(entity, categoryGroup, categoryCode, categoryName));
    	logger.debug("Category {} installed.", category);
    	return category;
	}


    //- collabs
    private CategoryRepository categoryRepository;
    
    @Resource
    public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

    private final Logger logger = LoggerFactory.getLogger(CategoryMgrImpl.class);

}
