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
import org.helianto.core.domain.Province;
import org.helianto.core.form.ProvinceForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProvinceFormFilterAdapterTests  {
    
    public static String OB = "order by alias.provinceCode ";
    public static String C0 = "alias.operator.id = 1 ";
    public static String C1 = "alias.class = 'C' AND ";
    public static String C2 = "AND alias.provinceCode = 'CODE' ";
    public static String C3 = "AND (lower(alias.provinceCode) like 'name_like%' " +
    		                  "OR lower(alias.provinceName) like '%name_like%' ) ";
    public static String C4 = "AND alias.parent.provinceCode = 'XX' ";
    public static String C5 = "AND alias.parent.id = 1 ";
    public static String C6 = "AND alias.operator.operatorName = 'OPERATOR' ";

    @Test
    public void operator() {
        assertEquals(C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
    	Mockito.when(form.getType()).thenReturn('C');
        assertEquals(C1+C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getProvinceCode()).thenReturn("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void search() {
    	Mockito.when(form.getSearchString()).thenReturn("NAME_LIKE");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void stateCode() {
    	Mockito.when(form.getStateCode()).thenReturn("XX");
        assertEquals(C0+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	Province parent = new Province(form.getOperator(), "PARENT");
    	parent.setId(1);
    	Mockito.when(form.getParentProvince()).thenReturn(parent);
        assertEquals(C0+C5+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void operatorName() {
    	Mockito.when(form.getContextName()).thenReturn("OPERATOR");
        assertEquals(C0+C6+OB, filter.createCriteriaAsString());
    }
    
    // collabs
    
    private ProvinceFormFilterAdapter filter;
    private ProvinceForm form;
    
    @Before
    public void setUp() {
    	Operator operator = new Operator("DEFAULT");
    	operator.setId(1);
    	form = Mockito.mock(ProvinceForm.class);
    	filter = new ProvinceFormFilterAdapter(form);
    	Mockito.when(form.getOperator()).thenReturn(operator);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
