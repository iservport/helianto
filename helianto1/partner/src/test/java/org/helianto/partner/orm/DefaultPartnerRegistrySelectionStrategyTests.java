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

package org.helianto.partner.orm;

import junit.framework.TestCase;

import org.helianto.core.filter.SelectionStrategy;
import org.helianto.core.test.UserTestSupport;
import org.helianto.partner.PartnerRegistryFilter;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerRegistrySelectionStrategyTests extends TestCase {
    
    public static String C1 = "partnerRegistry.entity.id = 0 ";
    public static String C2 = "AND partnerRegistry.partnerAlias = 'ALIAS' ";
    public static String C3 = "AND partnerRegistry.partnerNameLike like '%NAME_LIKE%' ";

    private SelectionStrategy<PartnerRegistryFilter> partnerRegistrySelectionStrategy;

    public void testCreateCriteriaAsStringNoUser() {
        filter.setUser(null);
        try {
        	partnerRegistrySelectionStrategy.createCriteriaAsString(filter, "partnerRegistry");
            fail("expected exception");
        }
        catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
        catch (Exception e) {
            fail("expected previous exception");
        }
    }
    
    public void testCreateCriteriaAsStringUser() {
        assertEquals(C1, partnerRegistrySelectionStrategy.createCriteriaAsString(filter, "partnerRegistry"));
    }
    
    public void testCreateCriteriaAsStringCategoryCode() {
        filter.setPartnerAlias("ALIAS");
        assertEquals(C1+C2, partnerRegistrySelectionStrategy.createCriteriaAsString(filter, "partnerRegistry"));
    }
    
    public void testCreateCriteriaAsStringCategoryNameLike() {
        filter.setPartnerNameLike("NAME_LIKE");
        assertEquals(C1+C3, partnerRegistrySelectionStrategy.createCriteriaAsString(filter, "partnerRegistry"));
    }
    
    private PartnerRegistryFilter filter;
    
    @Override
    public void setUp() {
    	partnerRegistrySelectionStrategy = 
            new DefaultPartnerRegistrySelectionStrategy();
        filter = PartnerRegistryFilter.partnerRegistryFilterFactory(UserTestSupport.createUser());
        filter.reset();
    }
}
