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

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Entity;
import org.helianto.core.form.AbstractCategoryForm;
import org.helianto.core.form.CategoryForm;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryFilterAdapterTests {
	
    public static String C1 = "alias.entity.id = 1 ";
    public static String C2 = "AND alias.categoryCode = 'CODE' ";
    public static String C3 = "AND lower(alias.categoryName) like '%name_like%' ";
    public static String C4 = "AND alias.categoryGroup = 'U' ";

    @Test
    public void select() {
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void categoryCode() {
    	((AbstractCategoryForm) form).setCategoryCode("CODE");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void categoryName() {
    	((AbstractCategoryForm) form).setCategoryName("NAME_LIKE");
        assertEquals(C1+C3, filter.createCriteriaAsString());
    }
    
    @Test
    public void categoryGroup() {
    	((AbstractCategoryForm) form).setCategoryGroup(CategoryGroup.UNIT.getValue());
        assertEquals(C1+C4, filter.createCriteriaAsString());
    }
    
    private CategoryFormFilterAdapter filter;
    private CategoryForm form;
    
    @SuppressWarnings("serial")
	@Before
    public void setUp() {
    	form = new AbstractCategoryForm() {
    		@Override
    		public Entity getEntity() {
    			Entity entity = EntityTestSupport.createEntity();
    			entity.setId(1);
    			return entity;
    		}
    	};
    	form.reset();
    	filter = new CategoryFormFilterAdapter(form);
    }

}
