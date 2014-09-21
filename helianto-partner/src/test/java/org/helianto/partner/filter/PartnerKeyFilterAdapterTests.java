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

package org.helianto.partner.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.partner.form.PartnerKeyForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PartnerKeyFilterAdapterTests {
	
    public static String OB = "order by alias.keyType.keyCode ";
    public static String C1 = "alias.partner.id = 10 ";
    public static String C2 = "alias.keyType.id = 30 ";
    public static String C3 = "alias.keyType.keyCode = 'CODE' ";
    public static String C4 = "alias.keyValue = 'VALUE' ";

    @Test
    public void empty() {
        assertEquals(OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getPartnerId()).thenReturn(10);
    	Mockito.when(form.getKeyTypeId()).thenReturn(30);
        assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	Mockito.when(form.getPartnerId()).thenReturn(10);
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void keyCode() {
    	Mockito.when(form.getKeyCode()).thenReturn("CODE");
        assertEquals(C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void keyValue() {
    	Mockito.when(form.getKeyValue()).thenReturn("VALUE");
        assertEquals(C4+OB, filter.createCriteriaAsString());
    }
    
    private PartnerKeyFilterAdapter filter;
    private PartnerKeyForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(PartnerKeyForm.class);
    	filter = new PartnerKeyFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
