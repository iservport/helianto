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

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.Part;


/**
 * Class to support <code>Part</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Part</code>.
     * @param entity optional Entity 
     * @param partCode optional String 
     */
    public static Part createPart(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        String partCode;
        try {
            partCode = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            partCode = DomainTestSupport.getNonRepeatableStringValue(testKey++, 24);
        }
        Part part = Part.partFactory(entity, partCode);
        return part;
    }

    /**
     * Test support method to create a <code>Part</code> list.
     *
     * @param partListSize
     */
    public static List<Part> createPartList(int partListSize) {
        return createPartList(partListSize, 1);
    }

    /**
     * Test support method to create a <code>Part</code> list.
     *
     * @param partListSize
     * @param entityListSize
     */
    public static List<Part> createPartList(int partListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createPartList(partListSize, entityList);
    }

    /**
     * Test support method to create a <code>Part</code> list.
     *
     * @param documentListSize
     * @param entityList
     */
    public static List<Part> createPartList(int partListSize, List<Entity> entityList) {
        List<Part> partList = new ArrayList<Part>();
        for (Entity entity: entityList) {
            for (int i=0;i<partListSize;i++) {
                partList.add(createPart(entity));
            }
        }
        return partList;
    }

}
