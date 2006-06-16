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

import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.helianto.core.service.SimpleCoreMgr;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.creation.ResourceCreator;
import org.helianto.process.creation.ResourceType;
import org.helianto.process.dao.ResourceDao;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ResourceDaoImplParamTests extends AbstractIntegrationTest {

    // class (interface) under test
    private ResourceDao resourceDao;
    
//	public void persistResourceParameter(ResourceParameter resourceParameter)
//	public List<ResourceParameter> findResourceParameterByEntity(Entity entity)
    public void testPersistResourceParameter() {
    	ResourceParameter resourceParameter = helpCreateAndPersist(entity);
    	
    	List<ResourceParameter> resourceParameterList = 
    		resourceDao.findResourceParameterByEntity(entity);
    	assertEquals(1, resourceParameterList.size());
    	ResourceParameter retr = resourceParameterList.get(0);
    	assertEquals(retr, resourceParameter);
    }

    public void testPersistAndReadResourceParameterMany() {
        for (int i = 0; i < 50; i++) {
        	ResourceParameter resourceParameter = 
        		resourceCreator.resourceParameterFactory(entity, generateKey(20));
            resourceDao.persistResourceParameter(resourceParameter);
        }
        hibernateTemplate.flush();
    	List<ResourceParameter> resourceParameterList = 
    		resourceDao.findResourceParameterByEntity(entity);
    	assertEquals(50, resourceParameterList.size());
    }

    public void testDupPersistResourceParameter() {
        String generatedCode = generateKey(20);
        ResourceParameter resourceParameter = 
            resourceCreator.resourceParameterFactory(entity, generatedCode);
        resourceDao.persistResourceParameter(resourceParameter);
        hibernateTemplate.flush();

        try {
            ResourceParameter copy = 
                resourceCreator.resourceParameterFactory(entity, generatedCode);
            resourceDao.persistResourceParameter(copy);
            hibernateTemplate.flush();
            fail();
        } catch (DataIntegrityViolationException e) {
            // expected
        }
    }

    public void testFindUniqueResourceParameter() {
        String generatedCode = generateKey(20);
        ResourceParameter resourceParameter = 
            resourceCreator.resourceParameterFactory(entity, generatedCode);
        resourceDao.persistResourceParameter(resourceParameter);
        hibernateTemplate.clear();

        ResourceParameter retr = resourceDao.findResourceParameterByEntityAndCode(entity, generatedCode);
        assertEquals(retr, resourceParameter);
    }

    public void testLoadResourceParameter() {
        String generatedCode = generateKey(20);
        ResourceParameter resourceParameter = 
            resourceCreator.resourceParameterFactory(entity, generatedCode);
        resourceDao.persistResourceParameter(resourceParameter);
        hibernateTemplate.clear();
        
        ResourceParameter retr = 
            resourceDao.loadResourceParameter(resourceParameter.getId());
        assertEquals(retr, resourceParameter);
    }

//	public List<ResourceParameter> findResourceParameterByParent(ResourceParameter parent)
    public void testPersistResourceParameterParent() {
        // create parent
        String generatedCode1 = generateKey(20);
        ResourceParameter parent = 
    		resourceCreator.resourceParameterFactory(entity, generatedCode1);
    	resourceDao.persistResourceParameter(parent);
        hibernateTemplate.flush();
        logger.info("Persist parent Param "+parent);

        // create child
        String generatedCode2 = generateKey(20);
        ResourceParameter resourceParameter = 
    		resourceCreator.resourceParameterFactory(parent, generatedCode2);
    	resourceDao.persistResourceParameter(resourceParameter);
        hibernateTemplate.flush();
        logger.info("Persist child Param "+resourceParameter);
        logger.info("Parent is "+resourceParameter.getParent());

    	List<ResourceParameter> resourceParameterList = 
    		resourceDao.findResourceParameterByParent(parent);
    	assertEquals(1, resourceParameterList.size());
    	ResourceParameter retr = resourceParameterList.get(0);
    	assertEquals(retr, resourceParameter);
    }
    
//    public void persistResourceParameterValue(ResourceParameterValue resourceParameterValue)
//    public List<ResourceParameterValue> findResourceParameterValueByResource(ResourceGroup resourceGroup)
    public void testPersistResourceParameterValue() {
        ResourceParameter resourceParameter = 
            helpCreateAndPersist(entity);
        ResourceGroup resourceGroup = 
            resourceCreator.resourceGroupFactory(entity, "RESOURCE", ResourceType.EQUIPMENT);
        resourceDao.persistResourceGroup(resourceGroup);
        ResourceParameterValue resourceParameterValue = 
            resourceCreator.resourceParameterValueFactory(resourceGroup, resourceParameter);
        resourceDao.persistResourceParameterValue(resourceParameterValue);
        hibernateTemplate.flush();
        
        List<ResourceParameterValue> resourceParameterValueList = 
            resourceDao.findResourceParameterValueByResource(resourceGroup);
        assertEquals(1, resourceParameterValueList.size());
        ResourceParameterValue retr = resourceParameterValueList.get(0);
        assertEquals(retr, resourceParameterValue);
    }

    public void testDuplicateResourceParameterValue() {
        ResourceParameter resourceParameter = 
            helpCreateAndPersist(entity);
        ResourceGroup resourceGroup = 
            resourceCreator.resourceGroupFactory(entity, "RESOURCE", ResourceType.EQUIPMENT);
        resourceDao.persistResourceGroup(resourceGroup);
        ResourceParameterValue resourceParameterValue = 
            resourceCreator.resourceParameterValueFactory(resourceGroup, resourceParameter);
        resourceDao.persistResourceParameterValue(resourceParameterValue);
        hibernateTemplate.flush();
        
        try {
            ResourceParameterValue copy = 
                resourceCreator.resourceParameterValueFactory(resourceGroup, resourceParameter);
            resourceDao.persistResourceParameterValue(copy);
            hibernateTemplate.flush();
            fail();
        } catch (DataIntegrityViolationException e) {
            // expected
        }
    }

    public void testLoadResourceParameterValue() {
        ResourceParameter resourceParameter = 
            helpCreateAndPersist(entity);
        ResourceGroup resourceGroup = 
            resourceCreator.resourceGroupFactory(entity, "RESOURCE", ResourceType.EQUIPMENT);
        resourceDao.persistResourceGroup(resourceGroup);
        ResourceParameterValue resourceParameterValue = 
            resourceCreator.resourceParameterValueFactory(resourceGroup, resourceParameter);
        resourceDao.persistResourceParameterValue(resourceParameterValue);
        hibernateTemplate.clear();
        
        ResourceParameterValue retr = 
            resourceDao.loadResourceParameterValue(resourceParameterValue.getId());
        assertEquals(retr, resourceParameterValue);
    }

    //

    private ResourceParameter helpCreateAndPersist(Entity entity) {
        String generatedCode2 = generateKey(20);
        ResourceParameter resourceParameter = 
            resourceCreator.resourceParameterFactory(entity, generatedCode2);
        resourceDao.persistResourceParameter(resourceParameter);
        hibernateTemplate.flush();
        return resourceParameter;
    }
    
    private ResourceCreator resourceCreator;

    private SimpleCoreMgr simpleCoreMgr;

    private Entity entity;

    {
        setAutowireMode(AbstractDependencyInjectionSpringContextTests.AUTOWIRE_BY_NAME);
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "deploy/dataSource.xml",
                "deploy/sessionFactory.xml", "deploy/support.xml",
                "deploy/transaction.xml", "deploy/core.xml",
                "deploy/process.xml" };
    }

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    public void setResourceCreator(ResourceCreator resourceCreator) {
        this.resourceCreator = resourceCreator;
    }

    public void setSimpleCoreMgr(SimpleCoreMgr simpleCoreMgr) {
        this.simpleCoreMgr = simpleCoreMgr;
    }

    @Override
    public void onSetUpInTransaction() {
        DefaultEntity defaultEntity = simpleCoreMgr
                .installDefaultEntity(generateKey(20));
        entity = defaultEntity.getEntity();
    }


}
