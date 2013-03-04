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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class CardSetTests {

	@Test
    public void cardSetEquals() {
		CardSet cardSet = new CardSet();
		assertFalse(cardSet.equals(null));
		
		CardSet other = new CardSet();
		assertTrue(cardSet.equals(other));
		
		Entity entity = new Entity(new Operator("DEFAULT"), "ALIAS");
        cardSet.setEntity(entity);
        assertFalse(cardSet.equals(other));
        cardSet.setInternalNumber(Long.MAX_VALUE);
        assertFalse(cardSet.equals(other));
        other.setEntity(entity);
        assertFalse(cardSet.equals(other));
        other.setInternalNumber(Long.MAX_VALUE);
        assertTrue(cardSet.equals(other));
        assertEquals(cardSet.hashCode(), other.hashCode());
        cardSet.setEntity(new Entity(new Operator("DEFAULT"), "OTHER"));
        assertFalse(cardSet.equals(other));
        cardSet.setInternalNumber(Long.MIN_VALUE);
        assertFalse(cardSet.equals(other));
    }
}
