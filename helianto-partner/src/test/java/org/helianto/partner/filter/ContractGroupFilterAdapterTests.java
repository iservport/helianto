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

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.form.CompositePartnerForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class ContractGroupFilterAdapterTests {
	
    public static String OB = "order by alias.userKey ";
    public static String C1 = "alias.class = 'C' ";
    public static String C2 = "alias.privateEntity.id = 10 ";

    @Test
    public void empty() {
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	form.getParent().setId(10);
        assertEquals(C1+"AND "+C2+OB, filter.createCriteriaAsString());
    }
    
    private ContactGroupFormFilterAdapter filter;
    private CompositePartnerForm form;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity(1);
    	PrivateEntity privateEntity = new PrivateEntity(entity, "");
    	form = new CompositePartnerForm(privateEntity);
    	filter = new ContactGroupFormFilterAdapter(form);
    }
}
