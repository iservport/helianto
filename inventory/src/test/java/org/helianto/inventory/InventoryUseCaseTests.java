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

import java.math.BigDecimal;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.UnitTestSupport;
import org.helianto.inventory.Inventory;
import org.helianto.inventory.InventoryTransaction;
import org.helianto.process.Process;
import org.helianto.process.ProcessDocument;
import org.helianto.process.test.ProcessDocumentTestSupport;


/**
 * Check if the domain is appropriate to support some use cases.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InventoryUseCaseTests {
	
	/**
	 * New item in stock, 100 kg, then 15 kg are transformed by process.
	 */
	public void uc1() {
		Entity entity = EntityTestSupport.createEntity();
		ProcessDocument item = ProcessDocumentTestSupport.createProcessDocument(entity, "ITEM");
		item.setUnit(UnitTestSupport.createUnit(entity, "kg"));
		Inventory rawMaterial = Inventory.inventoryFactory(Inventory.class, item);
		rawMaterial.setRequirementAmount(new BigDecimal(100));
		
		Process fooProcess = Process.processFactory(entity, "fp", 1);
		fooProcess.setUnit(item.getUnit());
		Inventory wip = Inventory.inventoryFactory(Inventory.class, fooProcess);
		
		InventoryTransaction mov = InventoryTransaction
			.inventoryTransactionFactory(InventoryTransaction.class, rawMaterial, wip, new BigDecimal(15));
		
	}

}
