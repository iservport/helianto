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

package org.helianto.core;

import org.helianto.core.junit.AbstractDomainTest;

public class PartnerDomainTests extends AbstractDomainTest {
    
    public void testPartnerEquals() {
        Entity entity = new Entity();
        Partner partner = new Partner();
        partner.setEntity(entity);
        partner.setAlias("TEST");
        Partner copy = (Partner) minimalEqualsTest(partner);
        
        copy.setEntity(entity);
        assertFalse(partner.equals(copy));
        copy.setAlias("TEST");
        assertTrue(partner.equals(copy));
    }

}