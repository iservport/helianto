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
	OPEN('O', false, true, false),
	/**
	 * Force totals update both on master and detail sides of the transaction.
	 */
	CALCULATE('C', true, true, false),
	/**
	 * Immutable on fields that affect calculation.
	 */
	ISSUED('I', false, false, true),
	/**
	 * Immutable on all fields, ready to account.
	 */
	CLOSED('C', false, false, true);
	
	private BlockingState(char value, boolean totalizeOnChangeRequired, boolean totalizeOnChangeAllowed, boolean immutable) {
		this.value = value;
		this.totalizeOnChangeRequired = totalizeOnChangeRequired;
		this.totalizeOnChangeAllowed = totalizeOnChangeAllowed;
		this.immutable = immutable;
	}
	
	private char value;
	private boolean totalizeOnChangeRequired;
	private boolean totalizeOnChangeAllowed;
	private boolean immutable;

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
	/**
	 * True if any field can be changed.
	 */
	public boolean isImmutable() {
		return immutable;
	}
	/**
	 * Return the blocking state given the value.
	 * 
	 * @param value
	 */
	public static BlockingState fromValue(char value) {
		for (BlockingState bs: BlockingState.values()) {
			if (bs.getValue()==value) {
				return bs;
			}
		}
		throw new IllegalArgumentException("Invalid value");
	}

}
