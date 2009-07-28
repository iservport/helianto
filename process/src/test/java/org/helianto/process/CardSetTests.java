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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class CardSetTests {

	@Test
    public void cardSetEquals() {
		CardSet copy, cardSet = new CardSet();
		Entity entity = new Entity();
        cardSet.setKey(entity, Long.MAX_VALUE);
        copy = (CardSet) DomainTestSupport.minimalEqualsTest(cardSet);

        copy.setKey(entity, 0);
        assertFalse(cardSet.equals(copy));

        copy.setKey(null, Long.MAX_VALUE);
        assertFalse(cardSet.equals(copy));

        copy.setKey(entity, Long.MAX_VALUE);
        assertTrue(cardSet.equals(copy));
    }
}
