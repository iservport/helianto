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

package org.helianto.process.test;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.Characteristic;


/**
 * Class to support <code>Characteristic</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CharacteristicTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Characteristic</code>.
     * 
     */
    public static Characteristic createCharacteristic() {
        return CharacteristicTestSupport.createCharacteristic(EntityTestSupport.createEntity());
    }

    /**
     * Test support method to create a <code>Characteristic</code>.
     * 
     * @param entity
     */
    public static Characteristic createCharacteristic(Entity entity) {
        return CharacteristicTestSupport.createCharacteristic(entity, DomainTestSupport.getNonRepeatableStringValue(testKey++, 24));
    }

    /**
     * Test support method to create a <code>Characteristic</code>.
     * 
     * @param entity
     * @param docCode
     */
    public static Characteristic createCharacteristic(Entity entity, String docCode) {
    	Characteristic document = new Characteristic();
        document.setEntity(entity);
        document.setDocCode(docCode);
        return document;
    }

}
