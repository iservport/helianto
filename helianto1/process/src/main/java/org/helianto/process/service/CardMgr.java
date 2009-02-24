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


package org.helianto.process.service;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.Card;
import org.helianto.process.CardSet;
import org.helianto.process.CardSetFilter;
import org.helianto.process.InvalidCardException;


/**
 * Business logic to manage cards.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface CardMgr {

	/**
	 * Find card set list.
	 */
	public List<CardSet> findCardSets(CardSetFilter cardSetFilter);

	/**
	 * Store card set.
	 */
	public CardSet storeCardSet(CardSet cardSet);

	/**
	 * Find or create an unique card.
	 */
	public Card findCard(Entity entity, String cardLabel, boolean createIfNecessary) 
		throws InvalidCardException;
	
}
