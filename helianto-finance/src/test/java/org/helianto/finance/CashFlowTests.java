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

import java.util.Date;

import org.helianto.core.domain.Entity;
import org.helianto.document.base.AbstractRecord;
import org.helianto.partner.domain.Partner;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CashFlowTests {
	
	@Test
	public void constructor() {
		CashFlow cashFlow = new CashFlow();
		assertTrue(cashFlow instanceof AbstractRecord);
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
		Partner partner = new Partner(entity);
		CashFlow cashFlow = new CashFlow(partner);
		assertSame(entity, cashFlow.getEntity());
		assertSame(partner, cashFlow.getPartner());
	}
	
	@Test
	public void equals() {
		final Entity entity = new Entity();
		CashFlow cashFlow = new CashFlow();
		CashFlow other = new CashFlow();
		assertTrue(cashFlow.equals(other));
		
		cashFlow.setEntity(entity);
		cashFlow.setInternalNumber(Long.MAX_VALUE);
		assertFalse(cashFlow.equals(other));
		other.setEntity(entity);
		assertFalse(cashFlow.equals(other));
		other.setInternalNumber(Long.MAX_VALUE);
		assertTrue(cashFlow.equals(other));
		assertEquals(cashFlow.hashCode(), other.hashCode());
		cashFlow.setInternalNumber(0);
		assertFalse(cashFlow.equals(other));

	}
	
	@Test
	public void compare() {
		Entity entity = new Entity();
		CashFlow cashFlow = new CashFlow(entity);
		cashFlow.setDueDate(new Date(1));
		CashFlow other = new CashFlow(entity);
		other.setDueDate(new Date(0));
		assertEquals(1, cashFlow.compareTo(other));
		other.setDueDate(new Date(1));
		assertEquals(0, cashFlow.compareTo(other));
		other.setDueDate(new Date(2));
		assertEquals(-1, cashFlow.compareTo(other));
	}

}
