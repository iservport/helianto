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

package org.helianto.classic;

import org.helianto.core.domain.enums.CategoryGroup;
import org.helianto.core.domain.Category2;
import org.helianto.core.domain.Entity;

/**
 * Service interface to categories.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface CategoryMgr {

	/**
	 * Count categories.
	 * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
	 */
	long countCategory(Entity entity, char categoryGroup, String searchString);

//	/**
//	 * List categories.
//	 * 
//	 * @param form
//	 */
//	List<Category> findCategories(CategoryForm form);
//
	/**
	 * Store category.
	 * 
	 * @param category
	 */
	Category2 storeCategory(Category2 category);

	/**
	 * Remove category.
	 * 
	 * @param category
	 */
	void removeCategory(Category2 category);

	/**
	 * Install category.
	 * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
	 */
	Category2 installCategory(Entity entity, CategoryGroup categoryGroup, String categoryCode, String categoryName);

}
