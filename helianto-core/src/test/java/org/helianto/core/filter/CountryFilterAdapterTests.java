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

import org.helianto.core.form.CountryForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CountryFilterAdapterTests  {
    
    public static String OB = "order by alias.countryCode ";
    public static String C0 = "alias.operator.id = 1 ";
    public static String C2 = "AND alias.countryCode = 'CODE' ";
    public static String C3 = "AND ((lower(alias.countryCode) like '%name_like%' ) "
    		+ "OR (lower(alias.countryName) like '%name_like%' ) ) ";

    @Test
    public void operator() {
        assertEquals(C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getCountryCode()).thenReturn("CODE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void search() {
    	Mockito.when(form.getSearchString()).thenReturn("NAME_LIKE");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString());
    }
    
    // collabs
    
    private CountryFormFilterAdapter filter;
    private CountryForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(CountryForm.class);
    	filter = new CountryFormFilterAdapter(form);
    	Mockito.when(form.getContextId()).thenReturn(1);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
