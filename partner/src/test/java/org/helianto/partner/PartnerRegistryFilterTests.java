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

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class PartnerRegistryFilterTests extends TestCase {
	
	public void testConstructor() {
		PartnerRegistryFilter partnerRegistryFilter = new PartnerRegistryFilter();
		assertTrue(partnerRegistryFilter instanceof Serializable);
		assertTrue(partnerRegistryFilter instanceof AbstractUserBackedCriteriaFilter);
	}
	
	public void testFactory() {
		User user = new User();
		PartnerRegistryFilter partnerRegistryFilter = PartnerRegistryFilter.partnerRegistryFilterFactory(user);
		assertSame(partnerRegistryFilter.getUser(), user);
	}
	
	public void testReset() {
		PartnerRegistryFilter partnerRegistryFilter = PartnerRegistryFilter.partnerRegistryFilterFactory(new User());
		partnerRegistryFilter.setPartnerNameLike(null);
		partnerRegistryFilter.reset();
		assertEquals("", partnerRegistryFilter.getPartnerNameLike());
	}

	public void testPartnerNameLike() {
		String partnerNameLike = "NAME_LIKE";
		PartnerRegistryFilter partnerRegistryFilter = PartnerRegistryFilter.partnerRegistryFilterFactory(new User());
		partnerRegistryFilter.setPartnerNameLike(partnerNameLike);
		assertSame(partnerRegistryFilter.getPartnerNameLike(), partnerNameLike);
	}
	
}
