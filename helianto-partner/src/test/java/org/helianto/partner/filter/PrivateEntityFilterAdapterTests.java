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
import static org.junit.Assert.assertSame;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.filter.PrivateEntityFilterAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class PrivateEntityFilterAdapterTests {
	
    @Test
	public void constructor() {
    	Entity entity = EntityTestSupport.createEntity();
    	filter = new PrivateEntityFilterAdapter(entity, "ALIAS");
		assertSame(entity, filter.getFilter().getEntity());
		assertEquals("ALIAS", filter.getFilter().getEntityAlias());
	}
	
    public static String C1 = "alias.entity.id = 0 ";
    public static String C2 = "AND alias.entityAlias = 'ALIAS' ";
    public static String C3 = "AND lower(alias.entityName) like '%name%' ";

    @Test
    public void empty() {
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	sample.setPartnerAlias("ALIAS");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void name() {
        sample.setPartnerName("NAME");
        assertEquals(C1+C3, filter.createCriteriaAsString());
    }
    
    private PrivateEntityFilterAdapter filter;
    private PrivateEntity sample;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity();
    	sample = new PrivateEntity(entity, "");
    	filter = new PrivateEntityFilterAdapter(sample);
    }
	
}
