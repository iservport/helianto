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

import org.helianto.core.filter.form.CompositeIdentityForm;
import org.helianto.core.filter.form.IdentityForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFilterAdapterTests {

    public static String C1 = "";
    public static String C2 = "lower(alias.principal) = 'principal' ";
    public static String C3 = "lower(alias.personalData.firstName) like '%first%' ";

    @Test
    public void empty() {
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((CompositeIdentityForm) form).setPrincipal("PRINCIPAL");
        assertEquals(C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filter() {
    	((CompositeIdentityForm) form).setFirstName("FIRST");
        assertEquals(C1+C3, filter.createCriteriaAsString());
    }
    
    private IdentityFormFilterAdapter filter;
    private IdentityForm form;
    
    @Before
    public void setUp() {
    	form = new CompositeIdentityForm("");
    	filter = new IdentityFormFilterAdapter(form);
    }
}
