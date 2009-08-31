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

package org.helianto.inventory.test;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.TopLevelNumberedEntityTestSupport;
import org.helianto.inventory.Inventory;
import org.helianto.inventory.InventoryTransaction;
import org.helianto.inventory.Movement;

/**
 * Test support to Movement.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MovementTestSupport {

	/**
	 * Create sample.
	 */
	public static Movement create() {
		return MovementTestSupport.create(EntityTestSupport.createEntity());
	}

	/**
	 * Create sample.
	 * 
	 * @param entity
	 */
	public static Movement create(Entity entity) {
		InventoryTransaction inventoryTransaction = TopLevelNumberedEntityTestSupport.create(InventoryTransaction.class, entity);
		return MovementTestSupport.create(inventoryTransaction);
	}

	/**
	 * Create sample.
	 * 
	 * @param inventoryTransaction
	 */
	public static Movement create(InventoryTransaction inventoryTransaction) {
		Movement movement = new Movement();
		movement.setInventoryTransaction(inventoryTransaction);
		movement.setInventory(TopLevelNumberedEntityTestSupport.create(Inventory.class, inventoryTransaction.getEntity()));
		return movement;
	}

}
