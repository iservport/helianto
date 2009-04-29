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


package org.helianto.process.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.process.Card;
import org.helianto.process.CardSet;


/**
 * Card test support.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CardTestSupport {
	
	static int testKey = 1;

	public static Card createSample() {
		CardSet cardSet = CardSetTestSupport.createCardSet();
		cardSet.setCardRange(Integer.MAX_VALUE);
		return createSample(cardSet);
	}

	public static Card createSample(CardSet cardSet) {
		return Card.cardFactory(cardSet, cardSet.getCardSetLabel()+"00"+(testKey++));
	}

	public static List<Card> createCardList(int cardListSize) {
		CardSet cardSet = CardSetTestSupport.createCardSet();
		return createCardList(cardListSize, cardSet);
	}

	public static List<Card> createCardList(int cardListSize, CardSet cardSet) {
		List<Card> cardList = new ArrayList<Card>();
		for (int i=0; i<cardListSize; i++) {
			cardList.add(CardTestSupport.createSample(cardSet));
		}
		return cardList;
	}

}
