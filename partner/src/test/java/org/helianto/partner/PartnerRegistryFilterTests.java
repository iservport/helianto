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
 * @author Maur�cio Fernandes de Castro
 */
public class PartnerRegistryFilterTests {
	
    @Test
	public void constructor() {
		PartnerRegistryFilter partnerRegistryFilter = new PartnerRegistryFilter();
		assertTrue(partnerRegistryFilter instanceof Serializable);
		assertTrue(partnerRegistryFilter instanceof AbstractUserBackedCriteriaFilter);
	}
	
    @Test
	public void factory() {
		User user = new User();
		PartnerRegistryFilter partnerRegistryFilter = PartnerRegistryFilter.partnerRegistryFilterFactory(user);
		assertSame(partnerRegistryFilter.getUser(), user);
	}
	
    @Test
	public void reset() {
		PartnerRegistryFilter partnerRegistryFilter = PartnerRegistryFilter.partnerRegistryFilterFactory(new User());
		partnerRegistryFilter.setPartnerNameLike(null);
		partnerRegistryFilter.reset();
		assertEquals("", partnerRegistryFilter.getPartnerNameLike());
	}

    public static String C1 = "partnerregistry.entity.id = 0 ";
    public static String C2 = "AND partnerregistry.partnerAlias = 'ALIAS' ";
    public static String C3 = "AND lower(partnerregistry.partnerName) like '%name%' ";

    @Test
    public void empty() {
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void select() {
    	filter.setPartnerAlias("ALIAS");
        assertEquals(C1+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void filter() {
        filter.setPartnerNameLike("NAME");
        assertEquals(C1+C3, filter.createCriteriaAsString(false));
    }
    
    private PartnerRegistryFilter filter;
    
    @Before
    public void setUp() {
    	filter = PartnerRegistryFilter.partnerRegistryFilterFactory(UserTestSupport.createUser());
    }
	
}
