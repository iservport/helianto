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
import org.helianto.core.Operator;
import org.helianto.partner.Customer;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerState;
import org.helianto.partner.PrivateEntity;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerFilterAdapterTests {
	
    public static String OB = "order by alias.privateEntity.entityAlias ";
    public static String C1 = "alias.privateEntity.entity.id = 1 ";
    public static String C2 = "AND alias.class=Partner ";
    public static String C7 = "AND alias.class=Customer ";
    public static String C3 = "AND alias.priority = '0' ";
    public static String C4 = "AND alias.privateEntity.entityAlias = 'PARTNER' ";
    public static String C5 = "AND lower(alias.privateEntity.entityName) like '%name%' ";
    public static String C6 = "AND alias.partnerState = 'A' ";

    @Test
    public void empty() {
        assertEquals(C1+C2+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	partner.getPrivateEntity().setEntityAlias("PARTNER");
        assertEquals(C1+C2+C4, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterClazz() {
        filter.setClazz(Customer.class);
        assertEquals(C1+C7+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterName() {
    	partner.getPrivateEntity().setEntityName("NAME");
        assertEquals(C1+C2+C5+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterState() {
        partner.setPartnerState(PartnerState.ACTIVE.getValue());
        assertEquals(C1+C2+C6+C3+OB, filter.createCriteriaAsString());
    }
    
    private PartnerFilterAdapter filter;
    private Partner partner;
    
    @Before
    public void setUp() {
    	Entity entity = new Entity(new Operator(), "ALIAS");
    	entity.setId(1);
    	PrivateEntity privateEntity = new PrivateEntity(entity, "");
    	partner = new Partner(privateEntity);
    	filter = new PartnerFilterAdapter(partner, Partner.class);
    	// clean
    	partner.setPartnerState(' ');
    }
}
