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

import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class KeyTypeFilterAdapterTests  {
    
    @Test
    public void constructor() {
		assertSame(target, filter.getForm());
		
		Operator operator = new Operator("OTHER");
		filter = new KeyTypeFilterAdapter(operator, "CODE");
		assertSame(operator, filter.getForm().getOperator());
		assertEquals("CODE", filter.getForm().getKeyCode());
	}
	
    public static String C0 = "alias.operator.id = 1 ";
    public static String C1 = "AND alias.keyCode = 'CODE' ";
    public static String C2 = "AND lower(alias.keyName) like '%name_like%' ";

    @Test
    public void empty() {
        assertEquals(C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	target.setKeyCode("CODE");
        assertEquals(C0+C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void filter() {
    	target.setKeyName("NAME_LIKE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    // collabs
    
    private KeyTypeFilterAdapter filter;
    private KeyType target;
    
    @Before
    public void setUp() {
    	Operator operator = new Operator("DEFAULT");
    	operator.setId(1);
    	target = new KeyType(operator, "");
    	filter = new KeyTypeFilterAdapter(target);
    }
    
}
