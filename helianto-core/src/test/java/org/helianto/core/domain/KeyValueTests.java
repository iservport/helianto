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

package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.internal.AbstractKeyValue;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class KeyValueTests {
	
	@SuppressWarnings("serial")
	@Test
	public void contructor() {
		AbstractKeyValue keyValue = new AbstractKeyValue() {
			@Override protected Object getKeyOwner() {
				return null;
			}
		};
		assertTrue(keyValue instanceof Serializable);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void equalsKeyValue() {
		AbstractKeyValue keyValue = new AbstractKeyValue() {
			@Override protected Object getKeyOwner() {
				return "1";
			}
		};
		AbstractKeyValue other = new AbstractKeyValue() {
			@Override protected Object getKeyOwner() {
				return String.valueOf(getId());
			}
		};
		assertFalse(keyValue.equals(null));
		assertFalse(keyValue.equals(other));
		
		Operator operator = OperatorTestSupport.createOperator();
		KeyType keyType = new KeyType().setKeyCode("code");
		keyType.setOperator(operator);
		
		keyValue.setKeyType(keyType);
		assertFalse(keyValue.equals(other));
		other.setKeyType(keyType);
		assertFalse(keyValue.equals(other));
		other.setId(1);// the stub above conveys this to keyOwner
		assertTrue(keyValue.equals(other));
		assertEquals(keyValue.hashCode(), other.hashCode());
		
	}

}
