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
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.Cause;


/**
 * Class to support <code>Cause</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CauseTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Cause</code>.
     * @param entity optional Entity 
     */
    public static Cause createCause(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        Cause cause = Cause.causeFactory(entity, testKey++);
        return cause;
    }

    /**
     * Test support method to create a <code>Cause</code> list.
     *
     * @param causeListSize
     */
    public static List<Cause> createCauseList(int causeListSize) {
        return createCauseList(causeListSize, 1);
    }

    /**
     * Test support method to create a <code>Cause</code> list.
     *
     * @param causeListSize
     * @param entityListSize
     */
    public static List<Cause> createCauseList(int causeListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createCauseList(causeListSize, entityList);
    }

    /**
     * Test support method to create a <code>Cause</code> list.
     *
     * @param causeListSize
     * @param entityList
     */
    public static List<Cause> createCauseList(int causeListSize, List<Entity> entityList) {
        List<Cause> causeList = new ArrayList<Cause>();
        for (Entity entity: entityList) {
            for (int i=0;i<causeListSize;i++) {
                causeList.add(createCause(entity));
            }
        }
        return causeList;
    }

}
