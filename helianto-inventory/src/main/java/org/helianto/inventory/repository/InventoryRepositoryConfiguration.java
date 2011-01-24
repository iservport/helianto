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

package org.helianto.inventory.repository;

import org.helianto.core.repository.AbstractRepositoryConfiguration;
import org.helianto.core.repository.FilterDao;
import org.helianto.inventory.Card;
import org.helianto.inventory.CardSet;
import org.helianto.inventory.Inventory;
import org.helianto.inventory.Movement;
import org.helianto.inventory.Picking;
import org.helianto.inventory.ProcessAgreement;
import org.helianto.inventory.ProcessRequirement;
import org.helianto.inventory.Tax;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Inventory repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
public class InventoryRepositoryConfiguration extends AbstractRepositoryConfiguration {

	/**
	 * Card data access.
	 */
	@Bean
	public FilterDao<Card> cardDao() {
		return getFilterDao(Card.class, "cardSet", "cardLabel");
	}

	/**
	 * Card set data access.
	 */
	@Bean
	public FilterDao<CardSet> cardSetDao() {
		return getFilterDao(CardSet.class);
	}

	/**
	 * Inventory data access.
	 */
	@Bean
	public FilterDao<Inventory> inventoryDao() {
		return getFilterDao(Inventory.class);
	}

	/**
	 * Tax data access.
	 */
	@Bean
	public FilterDao<Tax> taxDao() {
		return getFilterDao(Tax.class, "processAgreement", "keyType");
	}

//	Inventory transaction does not require a DAO, as it is always managed by some instance of inventory.
//	/**
//	 * Inventory transaction data access.
//	 */
//	@Bean
//	public FilterDao<InventoryTransaction, ListFilter> inventoryTransactionDao() {
//		return getFilterDao(InventoryTransaction.class);
//	}

	/**
	 * Movement data access.
	 */
	@Bean
	public FilterDao<Movement> movementDao() {
		return getFilterDao(Movement.class, "inventoryTransaction", "inventory");
	}

	/**
	 * Picking data access.
	 */
	@Bean
	public FilterDao<Picking> pickingDao() {
		return getFilterDao(Picking.class);
	}

	/**
	 * Process requirement data access.
	 */
	@Bean
	public FilterDao<ProcessRequirement> processRequirementDao() {
		return getFilterDao(ProcessRequirement.class);
	}

	/**
	 * Process agreement data access.
	 */
	@Bean
	public FilterDao<ProcessAgreement> agreementDao() {
		return getFilterDao(ProcessAgreement.class);
	}

}
