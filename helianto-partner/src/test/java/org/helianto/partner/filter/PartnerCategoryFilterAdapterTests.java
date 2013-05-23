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

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.nature.Customer;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerCategoryFilterAdapterTests {
	
    public static String OB = "order by alias.category.categoryCode ";
    public static String C1 = "alias.partner.id = 10 ";
    public static String C2 = "alias.category.id = 20 ";
    public static String C3 = "alias.partner.privateEntity.id = 100 ";

    @Test
    public void empty() {
        assertEquals(OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	form.getPartner().setId(10);
    	form.getCategory().setId(20);
        assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void partner() {
    	form.getPartner().setId(10);
        assertEquals(C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void category() {
    	form.getCategory().setId(20);
        assertEquals(C2+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	parent.setId(100);
    	form.setParent(parent);
        assertEquals(C3+OB, filter.createCriteriaAsString());
    }
    
    private PartnerCategoryFormFilterAdapter filter;
    private CompositeTestPartnerForm form;
    private PrivateEntity parent;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity(1);
    	parent = new PrivateEntity(entity, "PRIVATE_ENTITY");
    	Customer customer = new Customer(parent);
    	Category category = new Category(entity, CategoryGroup.NOT_DEFINED, "CATEGORY");
    	form = new CompositeTestPartnerForm(customer);
    	form.setCategory(category);
    	filter = new PartnerCategoryFormFilterAdapter(form);
    }
}
