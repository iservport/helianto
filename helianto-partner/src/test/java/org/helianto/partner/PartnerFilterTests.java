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

package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerFilterTests {
	
    @Test
	public void constructor() {
		PartnerFilter partnerFilter = new PartnerFilter();
		assertTrue(partnerFilter instanceof Serializable);
		assertTrue(partnerFilter instanceof AbstractUserBackedCriteriaFilter);

		Entity entity = new Entity();
		partnerFilter = new PartnerFilter(entity);
		assertSame(partnerFilter.getEntity(), entity);

		partnerFilter = new PartnerFilter(entity, Customer.class);
		assertSame(partnerFilter.getEntity(), entity);
		assertEquals(Customer.class, partnerFilter.getClazz());

		partnerFilter = new PartnerFilter(entity, Supplier.class);
		assertSame(partnerFilter.getEntity(), entity);
		assertEquals(Supplier.class, partnerFilter.getClazz());
		
		User user = new User(entity);
		partnerFilter = new PartnerFilter(user);
		assertSame(partnerFilter.getEntity(), entity);
		assertSame(partnerFilter.getUser(), user);

		partnerFilter = new PartnerFilter(user, Customer.class);
		assertSame(partnerFilter.getEntity(), entity);
		assertSame(partnerFilter.getUser(), user);
		assertEquals(Customer.class, partnerFilter.getClazz());

		partnerFilter = new PartnerFilter(user, Supplier.class);
		assertSame(partnerFilter.getEntity(), entity);
		assertSame(partnerFilter.getUser(), user);
		assertEquals(Supplier.class, partnerFilter.getClazz());
	}
	
    @Test
	public void reset() {
		PartnerFilter partnerFilter = new PartnerFilter(new User());
		partnerFilter.setPartnerNameLike("TEST");
		partnerFilter.reset();
		assertEquals("", partnerFilter.getPartnerNameLike());
	}

    public static String OB = "order by partner.privateEntity.entityAlias ";
    public static String C1 = "partner.privateEntity.entity.id = 1 ";
    public static String C2 = "AND partner.class=Partner ";
    public static String C7 = "AND partner.class=Customer ";
    public static String C3 = "AND partner.priority = '0' ";
    public static String C4 = "AND partner.privateEntity.entityAlias = 'ALIAS' ";
    public static String C5 = "AND lower(partner.privateEntity.entityName) like '%name%' ";
    public static String C6 = "AND partner.partnerState = 'A' ";

    @Test
    public void empty() {
        assertEquals(C1+C2+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setPartnerAlias("ALIAS");
        assertEquals(C1+C2+C4, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterClazz() {
        filter.setClazz(Customer.class);
        assertEquals(C1+C7+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterName() {
        filter.setPartnerNameLike("NAME");
        assertEquals(C1+C2+C5+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filterState() {
        filter.setPartnerState(PartnerState.ACTIVE.getValue());
        assertEquals(C1+C2+C6+C3+OB, filter.createCriteriaAsString(false));
    }
    
    private PartnerFilter filter;
    
    @Before
    public void setUp() {
    	filter = new PartnerFilter(UserTestSupport.createUser());
    	filter.getEntity().setId(1);
    }
}
