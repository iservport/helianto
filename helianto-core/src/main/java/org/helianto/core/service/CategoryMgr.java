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

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.Filter;
import org.helianto.core.form.CategoryForm;

/**
 * Service interface to categories.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface CategoryMgr {

	/**
	 * List categories.
	 * 
	 * @param categoryFilter
	 * @deprecated
	 */
	public List<Category> findCategories(Filter categoryFilter);

	/**
	 * List categories.
	 * 
	 * @param form
	 */
	public List<Category> findCategories(CategoryForm form);

	/**
	 * Store category.
	 * 
	 * @param category
	 */
	public Category storeCategory(Category category);

	/**
	 * Remove category.
	 * 
	 * @param category
	 */
	public void removeCategory(Category category);

	/**
	 * Install category.
	 * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
	 */
	public Category installCategory(Entity entity, CategoryGroup categoryGroup, String categoryCode, String categoryName);

}
