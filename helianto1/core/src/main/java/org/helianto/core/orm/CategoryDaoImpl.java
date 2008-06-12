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

package org.helianto.core.orm;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;
import org.helianto.core.Entity;
import org.helianto.core.dao.CategoryDao;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.springframework.stereotype.Repository;
/**
 * Default implementation of <code>Category</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("categoryDao")
public class CategoryDaoImpl extends GenericDaoImpl implements CategoryDao {
     
    public void persistCategory(Category category) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+category);
        }
        persist(category);
    }
    
    public Category mergeCategory(Category category) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+category);
        }
        return (Category) merge(category);
    }
    
    public void removeCategory(Category category) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+category);
        }
        remove(category);
    }
    
    public Category findCategoryByNaturalId(Entity entity, CategoryGroup categoryGroup, String categoryCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique category with entity='"+entity+"' and categoryGroup='"+categoryGroup+"' and categoryCode='"+categoryCode+"' ");
        }
        return (Category) findUnique(Category.getCategoryNaturalIdQueryString(), entity, categoryGroup.getValue(), categoryCode);
    }
    
    @SuppressWarnings("unchecked")
	public List<Category> findCategories(String criteria) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding category list with criteria ='"+criteria+"'");
        }
        if (criteria.equals("")) {
            return (ArrayList<Category>) find(Category.getCategoryQueryStringBuilder());
        }
        return (ArrayList<Category>) find(Category.getCategoryQueryStringBuilder().append("where ").append(criteria));
    }
    
}
