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

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.inventory.domain.Inventory;
import org.helianto.inventory.domain.InventoryTransaction;
import org.helianto.inventory.domain.Movement;
import org.helianto.inventory.domain.Shipment;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ShipmentTests {
	
	@Test
	public void constructor() {
		Shipment shipment = new Shipment();
		assertTrue(shipment instanceof Movement);
	}

	@Test
	public void shipmentEquals() {
		Entity entity = EntityTestSupport.createEntity();
		Shipment shipment = new Shipment();
		Shipment other = new Shipment();
		
		assertFalse(shipment.equals(null));
		assertTrue(shipment.equals(other));
		
		InventoryTransaction inventoryTransaction = new InventoryTransaction();
		Inventory inventory = new Inventory(entity, 0);

		shipment.setInventoryTransaction(inventoryTransaction);
		shipment.setInventory(inventory);
		assertFalse(shipment.equals(other));
		
		other.setInventoryTransaction(inventoryTransaction);
		assertFalse(shipment.equals(other));
		other.setInventory(inventory);
		assertTrue(shipment.equals(other));
		assertEquals(shipment.hashCode(), other.hashCode());
		shipment.setInventory(new Inventory());
		assertFalse(shipment.equals(other));
		shipment.setInventoryTransaction(new InventoryTransaction());
		assertFalse(shipment.equals(other));
	}

}
