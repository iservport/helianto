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

package org.helianto.process.hibernate;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.creation.EntityCreator;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.helianto.process.ResourceGroup;
import org.helianto.process.creation.ResourceCreator;
import org.helianto.process.creation.ResourceType;
import org.helianto.process.dao.ResourceDao;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ResourceDaoImplTests extends AbstractIntegrationTest {
    
    private ResourceDao resourceDao;
    private EntityDao entityDao; 
    private ResourceCreator resourceCreator;
    private EntityCreator entityCreator; // extends HomeCreator
    private Entity entity;
    
    {
        setAutowireMode(AbstractDependencyInjectionSpringContextTests.AUTOWIRE_BY_NAME);
    }
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml", 
                "deploy/sessionFactory.xml",
                "deploy/support.xml",
                "deploy/transaction.xml",
                "deploy/core.xml",
                "deploy/process.xml"
                };
    }
    
    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }
    
    public void setResourceCreator(ResourceCreator resourceCreator) {
        this.resourceCreator = resourceCreator;
    }

    public void setEntityCreator(EntityCreator entityCreator) {
        this.entityCreator = entityCreator;
    }

    @Override
    public void onSetUpInTransaction() {
        Home home = entityCreator.homeFactory(generateKey(5));
        entity = entityCreator.entityFactory(home, generateKey(5));
        entityDao.persistEntity(entity);
    }

    public void testPersistResourceGroup() {
        String generatedCode = generateKey(20);
        ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(entity, generatedCode, ResourceType.EQUIPMENT );
        String generatedName = generateKey(128);
        resourceGroup.setResourceName(generatedName);
        resourceDao.persistResourceGroup(resourceGroup);
        hibernateTemplate.flush();
        List<ResourceGroup> resourceList = resourceDao.findResourceByEntity(entity);
        assertEquals(1, resourceList.size());
        ResourceGroup rg = resourceList.get(0);
        assertEquals(entity, rg.getEntity());
        assertEquals(generatedCode, rg.getResourceCode());
        assertEquals(generatedName, rg.getResourceName());
        assertEquals(ResourceType.EQUIPMENT.getValue(), rg.getResourceType());
        logger.info(
           "Id "+rg.getId()+
           ", Entity "+rg.getEntity()+
           ", Code "+rg.getResourceCode()+
           ", Name "+rg.getResourceName()+
           ", Type "+rg.getResourceType()
        );
    }
    
    public void testPersistAndReadResourceGroupMany() {
        for (int i=0;i<50;i++) {
            ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(entity, generateKey(5), ResourceType.EQUIPMENT );
            resourceDao.persistResourceGroup(resourceGroup);
        }
        hibernateTemplate.flush();
        List<ResourceGroup> resourceList = resourceDao.findResourceByEntity(entity);
        assertEquals(50, resourceList.size());
    }

    public void testDupPersistResourceGroup() {
        String generatedCode = generateKey(20);
        ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(entity, generatedCode, ResourceType.EQUIPMENT );
        resourceDao.persistResourceGroup(resourceGroup);
        hibernateTemplate.flush();

        try {
            ResourceGroup resourceGroup2 = resourceCreator.resourceGroupFactory(entity, generatedCode, ResourceType.EQUIPMENT );
            resourceDao.persistResourceGroup(resourceGroup2);
            hibernateTemplate.flush();
            fail();
        } catch (DataIntegrityViolationException e) {
            //expected
            logger.info("Exception "+e.toString());
        }
        
    }    
    
    public void testPersistResourceGroupParent() {
        // create parent
        String generatedCode1 = generateKey(20);
        ResourceGroup resourceGroupParent = resourceCreator.resourceGroupFactory(entity, generatedCode1, ResourceType.EQUIPMENT );
        resourceDao.persistResourceGroup(resourceGroupParent);
        hibernateTemplate.flush();
        
        //create child
        String generatedCode2 = generateKey(20);
        ResourceGroup resourceGroupChild = resourceCreator.resourceGroupFactory(resourceGroupParent, generatedCode2);
        resourceDao.persistResourceGroup(resourceGroupChild);
        hibernateTemplate.flush();
        
        List<ResourceGroup> resourceList = resourceDao.findResourceByEntity(entity);
        assertEquals(2, resourceList.size());
        ResourceGroup rg = resourceList.get(1);
        assertEquals(entity, rg.getEntity());
        assertEquals(generatedCode2, rg.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), rg.getResourceType());
    }
    
    public void testPersistResourceGroupParentList() {
        
        ResourceGroup resourceGroupParent = resourceCreator.resourceGroupFactory(entity, generateKey(20), ResourceType.EQUIPMENT );
        resourceDao.persistResourceGroup(resourceGroupParent);

        for (int i=0;i<10;i++) {
            ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(resourceGroupParent, generateKey(20));
            resourceDao.persistResourceGroup(resourceGroup);
            resourceGroupParent = resourceGroup;
        }

        resourceGroupParent = resourceCreator.resourceGroupFactory(entity, generateKey(20), ResourceType.EQUIPMENT );
        resourceDao.persistResourceGroup(resourceGroupParent);

        for (int i=0;i<5;i++) {
            ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(resourceGroupParent, generateKey(20));
            resourceDao.persistResourceGroup(resourceGroup);
            resourceGroupParent = resourceGroup;
        }
        hibernateTemplate.flush();
        
        List<ResourceGroup> resourceList = resourceDao.findResourceByEntity(entity);
        assertEquals(17, resourceList.size());

        List<ResourceGroup> resourceChildList = resourceDao.findResourceByParent(resourceList.get(10));
        assertEquals(0, resourceChildList.size());
        assertEquals(resourceList.get(9), resourceList.get(10).getParent());
        setComplete();
    }
    
    
}
