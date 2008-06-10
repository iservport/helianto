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

import junit.framework.TestCase;

import org.helianto.core.CategoryFilter;
import org.helianto.core.CategoryGroup;
import org.helianto.core.dao.CategorySelectionStrategy;
import org.helianto.core.test.UserTestSupport;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCategorySelectionStrategyTests extends TestCase {
    
    public static String C1 = "category.entity.id = 0 AND category.categoryGroup = 0 ";
    public static String C2 = "AND category.categoryCode = 'CODE' ";
    public static String C3 = "AND category.categoryNameLike like '%NAME_LIKE%' ";

    private CategorySelectionStrategy categorySelectionStrategy;

    public void testCreateCriteriaAsStringNoUser() {
        filter.setUser(null);
        try {
        	categorySelectionStrategy.createCriteriaAsString(filter, "identity");
            fail("expected exception");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
        catch (Exception e) {
            fail("expected previous exception");
        }
    }
    
    public void testCreateCriteriaAsStringUser() {
        assertEquals(C1, categorySelectionStrategy.createCriteriaAsString(filter, "category"));
    }
    
    public void testCreateCriteriaAsStringCategoryCode() {
        filter.setCategoryCode("CODE");
        assertEquals(C1+C2, categorySelectionStrategy.createCriteriaAsString(filter, "category"));
    }
    
    public void testCreateCriteriaAsStringCategoryNameLike() {
        filter.setCategoryNameLike("NAME_LIKE");
        assertEquals(C1+C3, categorySelectionStrategy.createCriteriaAsString(filter, "category"));
    }
    
    private CategoryFilter filter;
    
    @Override
    public void setUp() {
    	categorySelectionStrategy = 
            new DefaultCategorySelectionStrategy();
        filter = CategoryFilter.categoryFilterFactory(UserTestSupport.createUser(), CategoryGroup.UNIT);
        filter.reset();
    }
}
