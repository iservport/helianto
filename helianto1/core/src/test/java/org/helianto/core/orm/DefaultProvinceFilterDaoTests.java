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
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultProvinceFilterDaoTests  {
    
    public static String C1 = "province.operator.id = 0 ";
    public static String C2 = "AND province.provinceCode = 'CODE' ";
    public static String C3 = "AND province.provinceName like '%NAME_LIKE%' ";

    @Test
    public void testEmpty() {
        assertEquals(C1, provinceDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setProvinceCode("CODE");
    	filter.setOperator(OperatorTestSupport.createOperator());
        assertEquals(C1+C2, provinceDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilter() {
        filter.setProvinceNameLike("NAME_LIKE");
        assertEquals(C1+C3, provinceDao.createCriteriaAsString(filter, false));
    }
    
    // collabs
    
    private DefaultProvinceDao provinceDao;
    private ProvinceFilter filter;
    
    @Before
    public void setUp() {
    	filter = ProvinceFilter.filterFactory(UserTestSupport.createUser());
    	provinceDao = new DefaultProvinceDao();
    }
    
}
