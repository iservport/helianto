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

import static org.junit.Assert.assertEquals;

import org.helianto.core.ProvinceFilter;
import org.helianto.core.filter.SelectionStrategy;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultProvinceSelectionStrategyTests  {
    
    public static String C1 = "province.operator.id = 0 ";
    public static String C2 = "AND province.provinceCode = 'CODE' ";
    public static String C3 = "AND province.provinceName like '%NAME_LIKE%' ";

    private SelectionStrategy<ProvinceFilter> provinceSelectionStrategy;

    @Test(expected=IllegalArgumentException.class)
    public void testCreateCriteriaAsStringNoUser() {
        filter.setUser(null);
        	provinceSelectionStrategy.createCriteriaAsString(filter, "unit");
    }
    
    @Test
    public void testCreateCriteriaAsStringUser() {
        assertEquals(C1, provinceSelectionStrategy.createCriteriaAsString(filter, "province"));
    }
    
    @Test
    public void testCreateCriteriaAsStringProvinceCode() {
        filter.setProvinceCode("CODE");
        assertEquals(C1+C2, provinceSelectionStrategy.createCriteriaAsString(filter, "province"));
    }
    
    @Test
    public void testCreateCriteriaAsStringProvinceNameLike() {
        filter.setProvinceNameLike("NAME_LIKE");
        assertEquals(C1+C3, provinceSelectionStrategy.createCriteriaAsString(filter, "province"));
    }
    
    @Before
    public void setUp() {
    	provinceSelectionStrategy = 
            new DefaultProvinceSelectionStrategy();
        filter = ProvinceFilter.filterFactory(UserTestSupport.createUser());
        filter.reset();
    }

    // collabs
    
    private ProvinceFilter filter;
    
}
