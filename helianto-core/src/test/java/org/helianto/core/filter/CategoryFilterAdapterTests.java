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

package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Category;
import org.helianto.core.CategoryGroup;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryFilterAdapterTests {
	
    public static String C1 = "alias.entity.id = 0 AND alias.categoryGroup = 'U' ";
    public static String C2 = "AND alias.categoryCode = 'CODE' ";
    public static String C3 = "AND lower(alias.categoryName) like '%name_like%' ";

    @Test
    public void select() {
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void createCriteriaAsStringCategoryCode() {
    	target.setCategoryCode("CODE");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void createCriteriaAsStringCategoryNameLike() {
    	target.setCategoryName("NAME_LIKE");
        assertEquals(C1+C3, filter.createCriteriaAsString());
    }
    
    private CategoryFilterAdapter filter;
    private Category target;
    
    @Before
    public void setUp() {
    	target = new Category(EntityTestSupport.createEntity(), CategoryGroup.UNIT, "");
    	filter = new CategoryFilterAdapter(target);
    }

}
