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

import java.io.Serializable;

import junit.framework.TestCase;

import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.UserBackedFilter;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerFilterTests extends TestCase {
	
	public void testConstructor() {
		PartnerFilter partnerFilter = new PartnerFilter();
		assertTrue(partnerFilter instanceof Serializable);
		assertTrue(partnerFilter instanceof AbstractUserBackedCriteriaFilter);
	}
	
	public void testFactory() {
		User user = new User();
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(user);
		assertSame(partnerFilter.getUser(), user);
	}
	
	public void testFactoryCustomer() {
		User user = new User();
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(user, Customer.class);
		assertSame(partnerFilter.getUser(), user);
		assertEquals(Customer.class, partnerFilter.getClazz());
	}
	
	public void testFactorySupplier() {
		User user = new User();
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(user, Supplier.class);
		assertSame(partnerFilter.getUser(), user);
		assertEquals(Supplier.class, partnerFilter.getClazz());
	}
	
	public void testReset() {
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(new User());
		partnerFilter.setPartnerNameLike("TEST");
		partnerFilter.reset();
		assertEquals("", partnerFilter.getPartnerNameLike());
	}

	public void testPartnerNameLike() {
		String partnerNameLike = "NAME_LIKE";
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(new User());
		partnerFilter.setPartnerNameLike(partnerNameLike);
		assertSame(partnerFilter.getPartnerNameLike(), partnerNameLike);
	}
	
	public void testPartnerState() {
		char partnerState = PartnerState.ACTIVE.getValue();
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(new User());
		partnerFilter.setPartnerState(partnerState);
		assertSame(partnerFilter.getPartnerState(), partnerState);
	}
	
	public void testPriority() {
		char priority = '0';
		PartnerFilter partnerFilter = PartnerFilter.partnerFilterFactory(new User());
		partnerFilter.setPriority(priority);
		assertSame(partnerFilter.getPriority(), priority);
	}
	
}
