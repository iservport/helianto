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

package org.helianto.core.def;

/**
 * <code>AddressType</code> enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum AddressType {
	
	/**
	 * Personal address.
	 */
	PERSONAL('P'),
	/**
	 * Collection address.
	 */
	COLLECTION('C'),
	/**
	 * Delivery address.
	 */
	DELIVERY('D'),
	/**
	 * Restricted address.
	 */
	RESTRICTED('R'),
	/**
	 * Main address.
	 */
	MAIN('M');
	
	private char value;
	
	private AddressType(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}

}
