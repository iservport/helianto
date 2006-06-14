package org.helianto.process.creation;

import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.creation.NullAssociationException;
import org.helianto.core.creation.NullEntityException;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.Unit;
import org.helianto.process.creation.ResourceCreator;
import org.helianto.process.creation.ResourceCreatorImpl;
import org.helianto.process.creation.ResourceType;

import junit.framework.TestCase;

public class ResourceCreatorImplTests extends TestCase {

    private ResourceCreator resourceCreator;
    
    @Override
    public void setUp() {
        resourceCreator = new ResourceCreatorImpl();
    }
    
    public void testResourceGroupCreation() {
        Entity entity = new Entity();
        ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(entity, "123", ResourceType.EQUIPMENT);
        assertSame(entity, resourceGroup.getEntity());
        assertEquals("123", resourceGroup.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resourceGroup.getResourceType());
    }
    
    public void testResourceGroupChildCreation() {
        Entity entity = new Entity();
        ResourceGroup parent = resourceCreator.resourceGroupFactory(entity, "123", ResourceType.EQUIPMENT);
        ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(parent, "456");
        assertSame(entity, resourceGroup.getEntity());
        assertEquals("456", resourceGroup.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resourceGroup.getResourceType());
        assertSame(parent, resourceGroup.getParent());
    }
    
    public void testResourceCreation() {
        Entity entity = new Entity();
        Division division = new Division();
        division.setEntity(entity);
        Resource resource = resourceCreator.resourceFactory(division, "123", ResourceType.EQUIPMENT);
        assertSame(entity, resource.getEntity());
        assertEquals("123", resource.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resource.getResourceType());
        assertSame(division, resource.getOwner());
    }

    public void testResourceChildCreation() {
        Entity entity = new Entity();
        Division division = new Division();
        division.setEntity(entity);
        ResourceGroup parent = resourceCreator.resourceGroupFactory(entity, "123", ResourceType.EQUIPMENT);
        Resource resource = resourceCreator.resourceFactory(parent, "456", division);
        assertSame(entity, resource.getEntity());
        assertEquals("456", resource.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resource.getResourceType());
        assertSame(parent, resource.getParent());
        assertSame(division, resource.getOwner());
    }
    
    public void testResourceParamCreation() {
        Entity entity = new Entity();
        Unit unit = new Unit();
    	ResourceParameter resourceParameter = resourceCreator.resourceParameterFactory(entity, "123", unit);
    	assertSame(entity, resourceParameter.getEntity());
    	assertEquals("123", resourceParameter.getParameterCode());
    	assertSame(unit, resourceParameter.getUnit());
    }
    
    public void testResourceParamCreationNoUnit() {
        Entity entity = new Entity();
    	ResourceParameter resourceParameter = resourceCreator.resourceParameterFactory(entity, "123");
    	assertNull(resourceParameter.getUnit());
    }
    
    public void testResourceParamCreationParent() {
        Entity entity = new Entity();
        Unit unit = new Unit();
		ResourceParameter parent = new ResourceParameter();
		parent.setEntity(entity);
		parent.setUnit(unit);
    	ResourceParameter resourceParameter = resourceCreator.resourceParameterFactory(parent, "123");
    	assertSame(entity, resourceParameter.getEntity());
    	assertEquals("123", resourceParameter.getParameterCode());
    	assertSame(unit, resourceParameter.getUnit());
    	assertSame(parent, resourceParameter.getParent());
    }
    
    public void testResourceParamCreationException() {
    	try {
    		resourceCreator.resourceParameterFactory(null, "", null);
    		fail();
    	} catch (NullEntityException e) {
    		// expected
    	}
    }
    
    public void testResourceParamValueCreation() {
    	ResourceGroup resourceGroup = new ResourceGroup();
    	ResourceParameter resourceParameter = new ResourceParameter();
    	ResourceParameterValue resourceParameterValue = resourceCreator.resourceParameterValueFactory(resourceGroup, resourceParameter, true);
    	assertSame(resourceGroup, resourceParameterValue.getResource());
    	assertSame(resourceParameter, resourceParameterValue.getParameter());
    	assertTrue(resourceParameterValue.isSuppressed());
    }
    
    public void testResourceParamValueCreationNotSuppressed() {
    	ResourceParameterValue resourceParameterValue = resourceCreator.resourceParameterValueFactory(new ResourceGroup(), new ResourceParameter());
    	assertFalse(resourceParameterValue.isSuppressed());
    }
    
    public void testResourceParamValueCreationException() {
    	try {
    		resourceCreator.resourceParameterValueFactory(null, new ResourceParameter());
    		fail();
    	} catch (NullAssociationException e) {
    		// expected
    	}
    	try {
    		resourceCreator.resourceParameterValueFactory(new ResourceGroup(), null);
    		fail();
    	} catch (NullAssociationException e) {
    		// expected
    	}
    }
    
}
