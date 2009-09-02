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

package org.helianto.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.TopLevelNumberedEntityTestSupport;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InventoryTransactionTests {
	
	@Test
	public void constructor() {
		InventoryTransaction inventoryTransaction = new InventoryTransaction();
		assertTrue(inventoryTransaction instanceof Serializable);
	}
	
	@Test
	public void factory() {
		Entity entity = EntityTestSupport.createEntity();
		Inventory from = TopLevelNumberedEntityTestSupport.create(Inventory.class, entity);
		Inventory to = TopLevelNumberedEntityTestSupport.create(Inventory.class, entity);
		InventoryTransaction inventoryTransaction = InventoryTransaction.inventoryTransactionFactory(InventoryTransaction.class, from, to, new BigDecimal(100));
		Set<Movement> movements = inventoryTransaction.getMovements();
		assertEquals(2, movements.size());
		for (Movement move: movements) {
			if (move.getDirection().equals(MovementDirection.INPUT)) {
				assertSame(from, move.getInventory());
			}
			else if (move.getDirection().equals(MovementDirection.OUTPUT)) {
				assertSame(to, move.getInventory());
			}
			else {
				fail("Either from or to are expected");
			}
		}
	}

	@Test
	public void inventoryTransactionEquals() {
		// we do not need equals because there is no natural key
	}
}
