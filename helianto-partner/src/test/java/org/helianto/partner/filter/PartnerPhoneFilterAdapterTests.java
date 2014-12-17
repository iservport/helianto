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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerPhoneFilterAdapterTests {
	
    public static String OB = "order by alias.sequence ";
    public static String C1 = "alias.privateEntity.id = 10 ";
    public static String C2 = "alias.sequence = 20 ";
    public static String C3 = "alias.phoneType = 'M' ";

    @Test
    public void empty() {
    	Mockito.when(form.getSequence()).thenReturn(-1);
        assertEquals(OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getPrivateEntityId()).thenReturn(10);
    	Mockito.when(form.getSequence()).thenReturn(20);
        assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	Mockito.when(form.getPrivateEntityId()).thenReturn(10);
    	Mockito.when(form.getSequence()).thenReturn(-1);
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void phoneType() {
    	Mockito.when(form.getPhoneType()).thenReturn('M');
    	Mockito.when(form.getSequence()).thenReturn(-1);
        assertEquals(C3+OB, filter.createCriteriaAsString());
    }
    
    private PartnerPhoneFormFilterAdapter filter;
    private CompositeTestPartnerForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(CompositeTestPartnerForm.class);
    	filter = new PartnerPhoneFormFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
