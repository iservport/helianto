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

import org.helianto.core.Category;
import org.helianto.core.UnitFilter;
import org.helianto.core.dao.UnitSelectionStrategy;
import org.helianto.core.test.UserTestSupport;
import org.helianto.core.test.CategoryTestSupport;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUnitSelectionStrategyTests extends TestCase {
    
    public static String C1 = "unit.entity.id = 0 ";
    public static String C2 = "AND unit.category.id = 0 ";
    public static String C3 = "AND unit.unitNameLike like '%NAME_LIKE%' ";

    private UnitSelectionStrategy unitSelectionStrategy;

    public void testCreateCriteriaAsStringNoUser() {
        filter.setUser(null);
        try {
        	unitSelectionStrategy.createCriteriaAsString(filter, "unit");
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
        assertEquals(C1, unitSelectionStrategy.createCriteriaAsString(filter, "unit"));
    }
    
    public void testCreateCriteriaAsStringUnitCode() {
        filter.setUnitCode("CODE");
        assertEquals(C1+C2, unitSelectionStrategy.createCriteriaAsString(filter, "unit"));
    }
    
    public void testCreateCriteriaAsStringCategory() {
    	Category category = CategoryTestSupport.createCategory();
        filter.setCategory(category);
        assertEquals(C1+C2, unitSelectionStrategy.createCriteriaAsString(filter, "unit"));
    }
    
    public void testCreateCriteriaAsStringUnitNameLike() {
        filter.setUnitNameLike("NAME_LIKE");
        assertEquals(C1+C3, unitSelectionStrategy.createCriteriaAsString(filter, "unit"));
    }
    
    private UnitFilter filter;
    
    @Override
    public void setUp() {
    	unitSelectionStrategy = 
            new DefaultUnitSelectionStrategy();
        filter = UnitFilter.unitFilterFactory(UserTestSupport.createUser());
        filter.reset();
    }
}
