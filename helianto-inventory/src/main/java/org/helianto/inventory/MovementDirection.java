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

import java.math.BigDecimal;

/**
 * Direction of movement.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum MovementDirection {
	
	INPUT('I', +1), OUTPUT('O', -1), NEUTRAL('N', 0);
	
	private MovementDirection(char value, int multiplier) {
		this.value = value;
		this.multiplier = multiplier;
	}
	
	private char value;
	private int multiplier;
	
	public char getValue() {
		return this.value;
	}
	
	public int getMultiplier() {
		return this.multiplier;
	}
	
	public static MovementDirection fromValue(BigDecimal quantity) {
		int signum = quantity.signum();
		if (signum==-1) {
			return OUTPUT;
		}
		if (signum==+1) {
			return INPUT;
		}
		return NEUTRAL;
	}

}
