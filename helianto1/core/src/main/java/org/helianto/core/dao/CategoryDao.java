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

package org.helianto.core.dao;

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;

import org.helianto.core.dao.CommonOrmDao;

import java.util.List;


import org.helianto.core.Entity;

/**
 * <code>Category</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface CategoryDao extends CommonOrmDao {
     
    /**
     * Persist <code>Category</code>.
     */
    public void persistCategory(Category category);
    
    /**
     * Merge <code>Category</code>.
     */
    public Category mergeCategory(Category category);
    
    /**
     * Remove <code>Category</code>.
     */
    public void removeCategory(Category category);
    
    /**
     * Find <code>Category</code> by <code>Entity</code> and categoryCode.
     */
    public Category findCategoryByNaturalId(Entity entity, CategoryGroup categoryGroup, String categoryCode);
    
    /**
     * Find <code>Category</code> by criteria.
     */
    public List<Category> findCategories(String criteria);
    
    
}
