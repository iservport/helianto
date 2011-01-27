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
import static org.junit.Assert.assertSame;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProvinceFilterAdapterTests  {
    
    @Test
    public void constructor() {
		assertSame(target, filter.getFilter());
		
		Operator operator = new Operator("OTHER");
		filter = new ProvinceFilterAdapter(operator, "CODE");
		assertSame(operator, filter.getFilter().getOperator());
		assertEquals("CODE", filter.getFilter().getProvinceCode());
	}
	
    public static String C0 = "alias.operator.id = 1 ";
    public static String C1 = "AND alias.class=Province ";
    public static String C2 = "AND alias.provinceCode = 'CODE' ";
    public static String C3 = "AND lower(alias.provinceName) like '%name_like%' ";

    @Test
    public void empty() {
        assertEquals(C0+C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	target.setProvinceCode("CODE");
        assertEquals(C0+C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filter() {
    	target.setProvinceName("NAME_LIKE");
        assertEquals(C0+C1+C3, filter.createCriteriaAsString());
    }
    
    // collabs
    
    private ProvinceFilterAdapter filter;
    private Province target;
    
    @Before
    public void setUp() {
    	Operator operator = new Operator("DEFAULT");
    	operator.setId(1);
    	target = new Province(operator, "");
    	filter = new ProvinceFilterAdapter(target);
    }
    
}
