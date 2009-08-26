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

/**
 * Valid cards must correspond to card sets.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InvalidCardException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String cardLabel;
	private String messageDetail = "";

	public InvalidCardException(String cardLabel) {
		super("Not a valid card: "+cardLabel);
		this.cardLabel = cardLabel;
	}

	public InvalidCardException(String cardLabel, String messageDetail) {
		super("Not a valid card: "+cardLabel+", "+messageDetail);
		this.cardLabel = cardLabel;
		this.messageDetail = messageDetail;
	}

	public String getCardLabel() {
		return this.cardLabel;
	}

	public String getMessageDetail() {
		return this.messageDetail;
	}

}
