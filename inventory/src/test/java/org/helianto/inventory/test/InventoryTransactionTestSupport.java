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
import org.helianto.inventory.InventoryTransaction;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InventoryTransactionTestSupport {
	
	private static long testKey = 0;
	
	/**
	 * Create a sample.
	 */
	public static InventoryTransaction create() {
		return InventoryTransactionTestSupport.create(EntityTestSupport.createEntity());
	}

	/**
	 * Create a sample.
	 * 
	 * @param entity
	 */
	public static InventoryTransaction create(Entity entity) {
		InventoryTransaction inventoryTransaction =  new InventoryTransaction();
		inventoryTransaction.setEntity(entity);
		inventoryTransaction.setInternalNumber(testKey++);
		return inventoryTransaction;
	}

}
