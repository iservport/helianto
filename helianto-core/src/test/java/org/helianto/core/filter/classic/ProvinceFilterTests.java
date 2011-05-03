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

package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.User;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProvinceFilterTests  {
    
    @Test
	public void factory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getProvinceCode());
		assertEquals("", filter.getProvinceNameLike());
	}
	
    @Test
	public void reset() {
		filter.reset();
		assertEquals("", filter.getProvinceCode());
		assertEquals("", filter.getProvinceNameLike());
	}

    public static String C1 = "province.operator.id = 0 AND province.class=Province ";
    public static String C2 = "AND province.provinceCode = 'CODE' ";
    public static String C3 = "AND lower(province.provinceName) like '%name_like%' ";

    @Test
    public void empty() {
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setProvinceCode("CODE");
    	filter.setOperator(OperatorTestSupport.createOperator());
        assertEquals(C1+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filter() {
        filter.setProvinceNameLike("NAME_LIKE");
        assertEquals(C1+C3, filter.createCriteriaAsString(false));
    }
    
    // collabs
    
    private ProvinceFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = ProvinceFilter.filterFactory(user);
    }
    
}
