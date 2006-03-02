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

import java.util.Iterator;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.helianto.process.Operation;
import org.helianto.process.Process;
import org.helianto.process.Resource;
import org.helianto.process.ResourceType;

public class ProcessMgrTests extends
    AbstractIntegrationTest {

    private ProcessMgr processMgr;
    
    private String resourceGroupCode = generateKey(6);
    
    private String resourceName = generateKey(128);
    
    private String resourceCode = generateKey(5)+"1";

    public void setProcessMgr(ProcessMgr processMgr) {
        this.processMgr = processMgr;
    }
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "deploy/applicationContext-core1-test.xml", 
                "deploy/applicationContext-process1-test.xml" };
    }
    

    /* (non-Javadoc)
     * @see org.helianto.core.junit.AbstractIntegrationTest#getTestEntity()
     */
    @Override
    protected Entity getTestEntity() {
        Entity entity = super.getTestEntity();
        processMgr.persistEntity(entity);
        return entity;
    }

    public void testCreateResource() {
        Entity entity = getTestEntity();
        assertNotNull("0", entity.getId());

        // 1 resourceGroupFactory
        Resource resourceGroup = processMgr.resourceGroupFactory(entity);
        assertNotNull("1.1", resourceGroup);
        assertSame("1.2", entity, resourceGroup.getEntity());
        assertNull("1.3", resourceGroup.getParent());
        assertEquals("1.4", resourceGroup.getResourceType(), ResourceType.GROUP.getValue());
        
        //key
        resourceGroup.setResourceCode(resourceGroupCode+"extra");
        assertEquals("1.4", resourceGroupCode+"extra", resourceGroup.getResourceCode());
        resourceGroup.setResourceName(resourceName+"extra");
        assertEquals("1.5", resourceName+"extra", resourceGroup.getResourceName());

        // 2 resourceFactory
        Resource resource = processMgr.resourceFactory(resourceGroup);
        assertNotNull("2.1", resource);
        assertSame("2.2", entity, resource.getEntity());
        assertSame("2.3", resourceGroup, resource.getParent());
        assertEquals("2.4", resource.getResourceType(), ResourceType.NORMAL.getValue());
        
        //key
        resource.setResourceCode(resourceCode);
        assertEquals("2.5", resourceCode, resource.getResourceCode());
        resource.setResourceName(resourceName+"extra");
        assertEquals("2.6", resourceName+"extra", resource.getResourceName());
        
        // 3 persistResource
        processMgr.persistResource(resource);
        assertNotNull("3.1", resourceGroup.getId());
        assertNotNull("3.2", resource.getId());
        
        // clear the session cache !!!
        hibernateTemplate.clear();
        
        // 4 loadResource
        Resource loadedResourceGroup = processMgr.loadResource(resourceGroup.getId());
        logger.info("resourceGroup is "+resourceGroup);
        logger.info("loadedResourceGroup is "+loadedResourceGroup);
        Resource loadedResource = processMgr.loadResource(resource.getId());
        assertEquals("4.1", resourceGroupCode, loadedResourceGroup.getResourceCode());
        assertEquals("4.2", resourceCode, loadedResource.getResourceCode());
        assertEquals("4.3", resourceName, loadedResourceGroup.getResourceName());
        assertEquals("4.4", resourceName, loadedResource.getResourceName());
        
        // clear the session cache !!!
        hibernateTemplate.clear();
        
        // 5 findResources
        List list = (List) processMgr.findResources(entity); 
        assertEquals("5.1", 2, list.size());
        for (Iterator it = list.iterator(); it.hasNext();) {
            assertEquals("5.2", ((Resource) it.next()).getEntity().getId(), entity.getId());
        }
        
        // 6 findResources
        list = (List) processMgr.findResources(entity.getAlias()); 
        assertEquals("6.1", 2, list.size());
        for (Iterator it = list.iterator(); it.hasNext();) {
            assertEquals("6.2", ((Resource) it.next()).getEntity().getId(), entity.getId());
        }
        
        // clear the session cache !!!
        hibernateTemplate.clear();
        
        // 7 findResourceGroups
        list = (List) processMgr.findResourceGroups(entity); 
        assertEquals("7.1", 1, list.size());
        for (Iterator it = list.iterator(); it.hasNext();) {
            Resource r = (Resource) it.next();
            assertEquals("7.2", r.getEntity().getId(), entity.getId());
            assertNull("7.2", r.getParent());
        }
        
        // clear the session cache !!!
        hibernateTemplate.clear();
        
        // 8 findResourcesByGroup
        list = (List) processMgr.findResourcesByGroup(loadedResourceGroup); 
        assertEquals("8.1", 1, list.size());
        for (Iterator it = list.iterator(); it.hasNext();) {
            Resource r = (Resource) it.next();
            assertEquals("8.2", r.getEntity().getId(), entity.getId());
            assertEquals("8.2", r.getParent().getId(), loadedResourceGroup.getId());
        }
        
    }
    
}
