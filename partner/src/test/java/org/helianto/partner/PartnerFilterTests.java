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
	public void testConstructor() {
		PartnerFilter partnerFilter = new PartnerFilter();
		assertTrue(partnerFilter instanceof Serializable);
		assertTrue(partnerFilter instanceof AbstractUserBackedCriteriaFilter);
	}
	
    @Test
	public void testFactory() {
		User user = new User();
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(user);
		assertSame(partnerFilter.getUser(), user);
	}
	
    @Test
	public void testFactoryCustomer() {
		User user = new User();
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(user, Customer.class);
		assertSame(partnerFilter.getUser(), user);
		assertEquals(Customer.class, partnerFilter.getClazz());
	}
	
    @Test
	public void testFactorySupplier() {
		User user = new User();
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(user, Supplier.class);
		assertSame(partnerFilter.getUser(), user);
		assertEquals(Supplier.class, partnerFilter.getClazz());
	}
	
    @Test
	public void testReset() {
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(new User());
		partnerFilter.setPartnerNameLike("TEST");
		partnerFilter.reset();
		assertEquals("", partnerFilter.getPartnerNameLike());
	}

    public static String OB = "order by partner.partnerRegistry.partnerAlias ";
    public static String C1 = "partner.partnerRegistry.entity.id = 1 ";
    public static String C2 = "AND partner.class=Partner ";
    public static String C7 = "AND partner.class=Customer ";
    public static String C3 = "AND partner.priority = '0' ";
    public static String C4 = "AND partner.partnerRegistry.partnerAlias = 'ALIAS' ";
    public static String C5 = "AND lower(partner.partnerRegistry.partnerName) like '%name%' ";
    public static String C6 = "AND partner.partnerState = 'A' ";

    @Test
    public void testEmpty() {
        assertEquals(C1+C2+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setPartnerAlias("ALIAS");
        assertEquals(C1+C2+C4, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterClazz() {
        filter.setClazz(Customer.class);
        assertEquals(C1+C7+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterName() {
        filter.setPartnerNameLike("NAME");
        assertEquals(C1+C2+C5+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterState() {
        filter.setPartnerState(PartnerState.ACTIVE.getValue());
        assertEquals(C1+C2+C6+C3+OB, filter.createCriteriaAsString(false));
    }
    
    private PartnerFilter filter;
    
    @Before
    public void setUp() {
    	filter = PartnerFilter.partnerFilterFactory(UserTestSupport.createUser());
    	filter.getEntity().setId(1);
    }
}
