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

import org.helianto.core.IdentityType;
import org.helianto.core.def.Gender;
import org.helianto.core.def.Notification;
import org.helianto.core.filter.form.IdentityForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFilterAdapterTests {

    public static String C1 = "";
    public static String C2 = "lower(alias.principal) = 'principal' ";
    public static String C3 = "lower(alias.personalData.firstName) like '%first%' ";
    public static String C4 = "lower(alias.personalData.lastName) like '%last%' ";
    public static String C5 = "(" +
    		"(lower(alias.principal) like '%one%' " +
    		"OR lower(alias.principal) like '%two%' ) " +
    		"OR (lower(alias.optionalAlias) like '%one%' " +
    		"OR lower(alias.optionalAlias) like '%two%' ) " +
    		"OR (lower(alias.personalData.firstName) like '%one%' " +
    		"OR lower(alias.personalData.firstName) like '%two%' ) " +
    		"OR (lower(alias.personalData.lastName) like '%one%' " +
    		"OR lower(alias.personalData.lastName) like '%two%' ) " +
    		") ";
    public static String C6 = "(" +
    		"(lower(alias.principal) like '%one@two%' ) " +
    		") ";
    public static String C7 = "alias.personalData.gender = 'F' ";
    public static String C8 = "alias.identityType = 'P' ";
    public static String C9 = "alias.notification = 'R' ";

    @Test
    public void empty() {
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getPrincipal()).thenReturn("PRINCIPAL");
        assertEquals(C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void first() {
    	Mockito.when(form.getFirstName()).thenReturn("FIRST");
        assertEquals(C1+C3, filter.createCriteriaAsString());
    }
    
    @Test
    public void last() {
    	Mockito.when(form.getLastName()).thenReturn("Last");
        assertEquals(C1+C4, filter.createCriteriaAsString());
    }
    
    @Test
    public void search() {
    	Mockito.when(form.getSearchString()).thenReturn("One Two");
        assertEquals(C1+C5, filter.createCriteriaAsString());
    	Mockito.when(form.getSearchString()).thenReturn("One@Two");
        assertEquals(C1+C6, filter.createCriteriaAsString());
    }
    
    @Test
    public void gender() {
    	Mockito.when(form.getGender()).thenReturn(Gender.FEMALE.getValue());
        assertEquals(C1+C7, filter.createCriteriaAsString());
    }
    
    @Test
    public void identityType() {
    	Mockito.when(form.getIdentityType()).thenReturn(IdentityType.PERSONAL_EMAIL.getValue());
        assertEquals(C1+C8, filter.createCriteriaAsString());
    }
    
    @Test
    public void notification() {
    	Mockito.when(form.getNotification()).thenReturn(Notification.BY_REQUEST.getValue());
        assertEquals(C1+C9, filter.createCriteriaAsString());
    }
    
    private IdentityFormFilterAdapter filter;
    private IdentityForm form;
    
    @Before
    public void setUp() {
    	form = Mockito.mock(IdentityForm.class);
    	filter = new IdentityFormFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
