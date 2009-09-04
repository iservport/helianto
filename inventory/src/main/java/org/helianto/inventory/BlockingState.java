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
 * Defines the blocking states applied to transactions.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum BlockingState {
	
	/**
	 * Initial state.
	 */
	OPEN('O', false, true),
	/**
	 * Force totals update both on master and detail sides of the transaction.
	 */
	CALCULATE('C', true, true),
	/**
	 * Immutable on fields that affect calculation.
	 */
	ISSUED('I', false, false),
	/**
	 * Immutable on all fields, ready to account.
	 */
	CLOSED('C', false, false);
	
	private BlockingState(char value, boolean totalizeOnChangeRequired, boolean totalizeOnChangeAllowed) {
		this.value = value;
		this.totalizeOnChangeRequired = totalizeOnChangeRequired;
		this.totalizeOnChangeAllowed = totalizeOnChangeAllowed;
	}
	
	private char value;
	private boolean totalizeOnChangeRequired;
	private boolean totalizeOnChangeAllowed;

	/**
	 * The value assigned to the blocking state.
	 */
	public char getValue() {
		return value;
	}
	/**
	 * True if totals update is required.
	 */
	public boolean isTotalizeOnChangeRequired() {
		return totalizeOnChangeRequired;
	}
	/**
	 * True if fields affecting totals can be changed.
	 */
	public boolean isTotalizeOnChangeAllowed() {
		return totalizeOnChangeAllowed;
	}

}
