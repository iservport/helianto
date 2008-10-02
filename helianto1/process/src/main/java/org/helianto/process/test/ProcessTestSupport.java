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
import org.helianto.process.Process;


/**
 * Class to support <code>Process</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Process</code>.
     * @param entity optional Entity 
     * @param docCode optional String 
     */
    public static Process createProcess(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        String docCode;
        try {
            docCode = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            docCode = DomainTestSupport.getNonRepeatableStringValue(testKey, 24);
        }
        Process process = Process.processFactory(entity, docCode, testKey);
        process.setId(testKey++);
        return process;
    }

    /**
     * Test support method to create a <code>Process</code> list.
     *
     * @param processListSize
     */
    public static List<Process> createProcessList(int processListSize) {
        return createProcessList(processListSize, 1);
    }

    /**
     * Test support method to create a <code>Process</code> list.
     *
     * @param documentListSize
     * @param entityListSize
     */
    public static List<Process> createProcessList(int processListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createProcessList(processListSize, entityList);
    }

    /**
     * Test support method to create a <code>Process</code> list.
     *
     * @param processListSize
     * @param entityList
     */
    public static List<Process> createProcessList(int processListSize, List<Entity> entityList) {
        List<Process> processList = new ArrayList<Process>();
        for (Entity entity: entityList) {
            for (int i=0;i<processListSize;i++) {
            	processList.add(createProcess(entity));
            }
        }
        return processList;
    }

}
