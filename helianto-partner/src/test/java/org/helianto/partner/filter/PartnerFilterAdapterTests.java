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
import org.helianto.partner.PartnerState;
import org.helianto.partner.domain.PrivateEntity2;
import org.helianto.partner.form.CompositePartnerForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maur�cio Fernandes de Castro
 */
public class PartnerFilterAdapterTests {
	
    public static String OB = "order by alias.privateEntity.entityAlias ";
    public static String C0 = "alias.privateEntity.entity.id = 1 ";
    public static String C1 = "alias.class = 'C' ";
    public static String C2 = "alias.privateEntity.id = 10 ";
    public static String C3 = "lower(alias.privateEntity.entityAlias) like '%partner%' ";
    public static String C4 = "lower(alias.privateEntity.entityName) like '%name%' ";
    public static String C5 = "alias.partnerState = 'A' ";
    public static String C6 = "alias.priority = '1' ";
    public static String C7 = "alias.class = 'C' ";
    public static String C8 = "alias.privateEntity.entity.id = 2 ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	form.getParent().setId(10);
        ((CompositePartnerForm) form).setPartnerType('C');
        assertEquals(C2+"AND "+C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	form.getParent().setId(10);
        assertEquals(C2+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void entityAlias() {
    	form.setEntityAlias("PARTNER");
        assertEquals(C0+"AND "+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void name() {
    	form.setEntityName("NAME");
        assertEquals(C0+"AND "+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void partnerState() {
        form.setPartnerState(PartnerState.ACTIVE.getValue());
        assertEquals(C0+"AND "+C5+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void priority() {
        form.setPriority('1');
        assertEquals(C0+"AND "+C6+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void type() {
        form.setPartnerType('C');
        assertEquals(C0+"AND "+C7+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void entity() {
    	Entity entity = EntityTestSupport.createEntity(2);
    	form = new CompositePartnerForm(entity);
    	form.setParent(null);
    	filter = new PartnerFormFilterAdapter(form);
        assertEquals(C8+OB, filter.createCriteriaAsString());
    }
    
    private PartnerFormFilterAdapter filter;
    private CompositePartnerForm form;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity(1);
    	PrivateEntity2 privateEntity = new PrivateEntity2(entity, "");
    	form = new CompositePartnerForm(privateEntity);
    	filter = new PartnerFormFilterAdapter(form);
    }
    
}
