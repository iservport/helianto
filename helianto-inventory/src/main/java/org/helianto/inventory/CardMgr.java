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

import org.helianto.core.domain.Entity;
import org.helianto.inventory.domain.Card;
import org.helianto.inventory.domain.CardSet;


/**
 * Business logic to manage cards.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface CardMgr {

	/**
	 * Store card set.
	 * 
	 * @param cardSet
	 */
	CardSet storeCardSet(CardSet cardSet);
	
	/**
	 * Find or create an unique card.
	 * 
	 * @param entity
	 * @param cardLabel
	 * @param createIfNecessary
	 */
	Card findCard(Entity entity, String cardLabel, boolean createIfNecessary) 
		throws InvalidCardException;
	
	/**
	 * Store card set.
	 * 
	 * @param card
	 */
	Card storeCard(Card card);
	
}
