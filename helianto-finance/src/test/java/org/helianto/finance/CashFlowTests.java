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

package org.helianto.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.document.AbstractDocument;
import org.helianto.partner.Partner;
import org.helianto.partner.test.PartnerTestSupport;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CashFlowTests {
	
	@Test
	public void constructor() {
		CashFlow cashFlow = new CashFlow();
		assertTrue(cashFlow instanceof AbstractDocument);
	}

	@Test
	public void constructorEntity() {
		Entity entity = new Entity();
		CashFlow cashFlow = new CashFlow(entity);
		assertSame(entity, cashFlow.getEntity());
	}

	@Test
	public void constructorPartner() {
		Entity entity = new Entity();
		Partner partner = PartnerTestSupport.createPartner(entity);
		CashFlow cashFlow = new CashFlow(partner);
		assertSame(entity, cashFlow.getEntity());
		assertSame(partner, cashFlow.getPartner());
	}
	
	@Test
	public void equals() {
		final Entity entity = new Entity();
		AbstractDocument abstractDocument = new AbstractDocument() {
			private static final long serialVersionUID = 1L;
			@Override public Entity getEntity() {return entity; }
			@Override public String getDocCode() {return "CODE"; }
		};
		CashFlow cashFlow = new CashFlow() {
			private static final long serialVersionUID = 1L;
			@Override public Entity getEntity() {return entity; }
			@Override public String getDocCode() {return "CODE"; }
		};
		assertFalse(cashFlow.equals(abstractDocument));
	}
	
	@Test
	public void compare() {
		Entity entity = new Entity();
		CashFlow cashFlow = new CashFlow(entity);
		cashFlow.setDocCode("1");
		CashFlow other = new CashFlow(entity);
		other.setDocCode("0");
		assertEquals(1, cashFlow.compareTo(other));
		other.setDocCode("1");
		assertEquals(0, cashFlow.compareTo(other));
		other.setDocCode("2");
		assertEquals(-1, cashFlow.compareTo(other));
	}

}
