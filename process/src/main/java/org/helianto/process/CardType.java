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


package org.helianto.process;

/**
 * Defines card types.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum CardType {
	
	/**
	 * Station start up.
	 */
	STATION('S', true),
	/**
	 * Authority.
	 */
	AUTHORITY('A', true),
	/**
	 * Data card.
	 */
	DATA('D', false),
	/**
	 * Exception card.
	 */
	EXCEPTION('N', false),
	/**
	 * Dump records to a file.
	 */
	DUMP('P', true),
	/**
	 * Test card.
	 */
	TEST('E', true),
	/**
	 * Station termination.
	 */
	TERMINATION('T', true);
	
	private CardType(char prefix, boolean control) {
		this.prefix = prefix;
	}
	
	private char prefix;
	private boolean control;
	
	/**
	 * True if control card.
	 */
	public boolean isControl() {
		return this.control;
	}
	
	/**
	 * Card prefix.
	 */
	public char getPrefix() {
		return this.prefix;
	}
	
	/**
	 * Find the card type.
	 * 
	 * @param prefix
	 */
	public static CardType getCardType(char prefix) {
		for (CardType cardType: CardType.values()) {
			if (prefix==cardType.getPrefix()) {
				return cardType;
			}
		}
		return null;
	}

}
