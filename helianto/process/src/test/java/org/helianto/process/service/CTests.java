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

package org.helianto.process.service;

import java.util.Date;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Supervisor;
import org.helianto.process.Resource;
import org.helianto.process.ResourceType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class CTests extends TestCase {
    
    ApplicationContext context;
    
    private String resourceGroupCode = generateKey(6);
    
    private String resourceName = generateKey(128);
    
    private String resourceCode = generateKey(5)+"1";

    public String generateKey() {
        return String.valueOf(new Date().getTime());
    }

    public String generateKey(int size) {
        String localKey = generateKey();
        while (localKey.length()!=size) {
            if (localKey.length() > size) {
                localKey = localKey.substring(localKey.length()-size, localKey.length());
            } else if (localKey.length() < size) {
                localKey = localKey.concat(localKey);
            }
        }
        return localKey;
    }

    public void setUp() {
        String[] files = new String[] { "/deploy/applicationContext-core1-test.xml", 
                "/deploy/applicationContext-process1-test.xml" };
        context = new ClassPathXmlApplicationContext(files);
    }
    
    public void testSanity() {
        ProcessMgr processMgr = (ProcessMgr) context.getBean("processMgr");
        Supervisor supervisor = new Supervisor();
        supervisor.setSupervisorName(generateKey(20));
        String entityTest = generateKey(20);
//        Entity entity = processMgr.entityFactory(supervisor, entityTest);
//        processMgr.persistEntity(entity);
//
//        Resource resourceGroup = processMgr.resourceGroupFactory(entity);
//        assertNotNull("1.1", resourceGroup);
//        assertSame("1.2", entity, resourceGroup.getEntity());
//        assertNull("1.3", resourceGroup.getParent());
//        assertEquals("1.4", resourceGroup.getResourceType(), ResourceType.GROUP.getValue());
//        
//        //key
//        resourceGroup.setResourceCode(resourceGroupCode+"extra");
//        assertEquals("1.4", resourceGroupCode+"extra", resourceGroup.getResourceCode());
//        resourceGroup.setResourceName(resourceName+"extra");
//        assertEquals("1.5", resourceName+"extra", resourceGroup.getResourceName());
//
//        Resource resource = processMgr.resourceFactory(resourceGroup);
//        assertNotNull("2.1", resource);
//        assertSame("2.2", entity, resource.getEntity());
//        assertSame("2.3", resourceGroup, resource.getParent());
//        assertEquals("2.4", resource.getResourceType(), ResourceType.NORMAL.getValue());
//        
//        //key
//        resource.setResourceCode(resourceCode);
//        assertEquals("2.5", resourceCode, resource.getResourceCode());
//        resource.setResourceName(resourceName+"extra");
//        assertEquals("2.6", resourceName+"extra", resource.getResourceName());
//        
//        processMgr.persistResource(resource);
//        List list = (List) processMgr.findResources(entity); 
//        assertEquals("4.1", 2, list.size());
    }

}
