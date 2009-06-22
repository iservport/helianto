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

package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryFilterTests {
	
    @Test
    public void testConstructor() {
		CategoryFilter categoryFilter = new CategoryFilter();
		assertTrue(categoryFilter instanceof Serializable);
		assertTrue(categoryFilter instanceof UserBackedFilter);
	}
	
    @Test
	public void testFactory() {
		User user = new User();
		CategoryGroup categoryGroup = CategoryGroup.NOT_DEFINED;
		CategoryFilter categoryFilter = CategoryFilter.categoryFilterFactory(user);
		assertSame(categoryFilter.getUser(), user);
		assertSame(categoryFilter.getCategoryGroup(), categoryGroup);
	}
	
    @Test
	public void testReset() {
		CategoryFilter categoryFilter = CategoryFilter.categoryFilterFactory(new User());
		categoryFilter.reset();
		assertEquals("", categoryFilter.getCategoryCode());
		assertEquals("", categoryFilter.getCategoryNameLike());
	}

    public static String C1 = "category.entity.id = 0 AND category.categoryGroup = 0 ";
    public static String C2 = "AND category.categoryCode = 'CODE' ";
    public static String C3 = "AND lower(category.categoryNameLike) like '%name_like%' ";

    @Test
    public void testSelect() {
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testCreateCriteriaAsStringCategoryCode() {
        filter.setCategoryCode("CODE");
        assertEquals(C1+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testCreateCriteriaAsStringCategoryNameLike() {
        filter.setCategoryNameLike("NAME_LIKE");
        assertEquals(C1+C3, filter.createCriteriaAsString(false));
    }
    
    private CategoryFilter filter;
    
    @Before
    public void setUp() {
    	filter = CategoryFilter.categoryFilterFactory(UserTestSupport.createUser());
    	filter.setCategoryGroup(CategoryGroup.UNIT);
    }

}
