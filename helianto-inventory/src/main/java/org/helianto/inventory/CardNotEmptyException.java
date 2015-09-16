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

import org.helianto.inventory.domain.Card;



/**
 * Card not empty exception.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CardNotEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Card card;

	public CardNotEmptyException(Card card) {
		super("Empty card required");
		this.card = card;
	}
	
	public Card getCard() {
		return this.card;
	}

}
