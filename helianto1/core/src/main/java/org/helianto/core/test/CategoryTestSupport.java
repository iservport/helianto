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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.CategoryGroup;
import org.helianto.core.Entity;
import org.helianto.core.Category;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;


/**
 * Class to support <code>CategoryDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Category</code>.
     * @param entity optional Entity 
     * @param categoryCode optional String 
     */
    public static Category createCategory(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        CategoryGroup categoryGroup;
        try {
        	categoryGroup = (CategoryGroup) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
        	categoryGroup = CategoryGroup.NOT_DEFINED;
        }
        String categoryCode;
        try {
            categoryCode = (String) args[2];
        } catch(ArrayIndexOutOfBoundsException e) {
            categoryCode = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
        }
        Category category = Category.categoryFactory(entity, categoryGroup, categoryCode);
        return category;
    }

    /**
     * Test support method to create a <code>Category</code> list.
     *
     * @param categoryListSize
     */
    public static List<Category> createCategoryList(int categoryListSize) {
        return createCategoryList(categoryListSize, 1);
    }

    /**
     * Test support method to create a <code>Category</code> list.
     *
     * @param categoryListSize
     * @param entityListSize
     */
    public static List<Category> createCategoryList(int categoryListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createCategoryList(categoryListSize, entityList);
    }

    /**
     * Test support method to create a <code>Category</code> list.
     *
     * @param categoryListSize
     * @param entityList
     */
    public static List<Category> createCategoryList(int categoryListSize, List<Entity> entityList) {
        List<Category> categoryList = new ArrayList<Category>();
        for (Entity entity: entityList) {
	        for (int i=0;i<categoryListSize;i++) {
    	        categoryList.add(createCategory(entity));
        	}
        }
        return categoryList;
    }

}
