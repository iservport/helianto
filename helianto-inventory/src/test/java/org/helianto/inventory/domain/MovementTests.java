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

package org.helianto.inventory.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MovementTests {
	
	@Test
	public void constructor() {
		Movement movement = new Movement();
		assertTrue(movement instanceof Serializable);
	}
	
	@Test
	public void movementEquals() {
		Movement movement = new Movement();
		Movement other = new Movement();
		assertFalse(movement.equals(null));
		assertTrue(movement.equals(other));
		
		InventoryTransaction inventoryTransaction = new InventoryTransaction();
		Inventory inventory = new Inventory();
		movement.setInventoryTransaction(inventoryTransaction);
		movement.setInventory(inventory);
		
		assertFalse(movement.equals(null));
		other.setInventoryTransaction(inventoryTransaction);
		assertFalse(movement.equals(null));
		other.setInventory(inventory);
		assertTrue(movement.equals(other));
		assertEquals(movement.hashCode(), other.hashCode());
		movement.setInventoryTransaction(new InventoryTransaction());
		assertFalse(movement.equals(null));
		movement.setInventory(new Inventory());
		assertFalse(movement.equals(null));
		
	}

}
