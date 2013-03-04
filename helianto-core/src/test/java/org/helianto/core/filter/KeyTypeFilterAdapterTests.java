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

import org.helianto.core.domain.Operator;
import org.helianto.core.form.CompositeEntityForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class KeyTypeFilterAdapterTests  {
    
    public static String C0 = "alias.operator.id = 1 ";
    public static String C1 = "AND alias.keyCode = 'CODE' ";
    public static String C2 = "AND lower(alias.keyName) like '%name_like%' ";

    @Test
    public void empty() {
        assertEquals(C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	form.setKeyCode("CODE");
        assertEquals(C0+C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void filter() {
    	form.setKeyName("NAME_LIKE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    // collabs
    
    private KeyTypeFormFilterAdapter filter;
    private CompositeEntityForm form;
    
    @Before
    public void setUp() {
    	Operator operator = new Operator("DEFAULT");
    	operator.setId(1);
    	form = new CompositeEntityForm(operator);
    	filter = new KeyTypeFormFilterAdapter(form);
    }
    
}
