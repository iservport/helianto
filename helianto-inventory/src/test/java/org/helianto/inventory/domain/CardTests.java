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


package org.helianto.inventory.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class CardTests {

	@Test
    public void carTestEquals() {
		Card card = new Card();
		assertFalse(card.equals(null));
		
		Card other = new Card();
		assertTrue(card.equals(other));
		
		Entity entity = new Entity("DEFAULT", "ALIAS");
		CardSet cardSet = new CardSet(entity, 0);
        card.setCardSet(cardSet);
        assertFalse(card.equals(other));
        card.setCardLabel("LABEL");
        assertFalse(card.equals(other));
        other.setCardSet(cardSet);
        assertFalse(card.equals(other));
        other.setCardLabel("LABEL");
        assertTrue(card.equals(other));
        assertEquals(card.hashCode(), other.hashCode());
        card.setCardSet(new CardSet(entity, 1));
        assertFalse(card.equals(other));
        card.setCardLabel("OTHER");
        assertFalse(card.equals(other));
    }
}
