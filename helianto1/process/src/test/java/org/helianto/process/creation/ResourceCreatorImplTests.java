package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.core.Partner;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.Unit;
import org.helianto.process.creation.ResourceCreatorImpl;
import org.helianto.process.type.ResourceType;

import junit.framework.TestCase;

public class ResourceCreatorImplTests extends TestCase {

    public void testResourceGroupCreation() {
        Entity entity = new Entity();
        ResourceGroup resourceGroup = ResourceCreatorImpl.resourceGroupFactory(entity, "123", ResourceType.EQUIPMENT);
        assertSame(entity, resourceGroup.getEntity());
        assertEquals("123", resourceGroup.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resourceGroup.getResourceType());
    }
    
    public void testResourceGroupChildCreation() {
        Entity entity = new Entity();
        ResourceGroup parent = ResourceCreatorImpl.resourceGroupFactory(entity, "123", ResourceType.EQUIPMENT);
        ResourceGroup resourceGroup = ResourceCreatorImpl.resourceGroupFactory(parent, "456");
        assertSame(entity, resourceGroup.getEntity());
        assertEquals("456", resourceGroup.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resourceGroup.getResourceType());
        assertSame(parent, resourceGroup.getParent());
    }
    
    public void testResourceCreation() {
        Entity entity = new Entity();
        ResourceGroup parent = new ResourceGroup();
        parent.setEntity(entity);
        parent.setResourceType(ResourceType.EQUIPMENT.getValue());
        Partner partner = new Partner();
        Resource resource = ResourceCreatorImpl.resourceFactory(parent, "123", partner);
        assertSame(entity, resource.getEntity());
        assertEquals("123", resource.getResourceCode());
        assertSame(parent, resource.getParent());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resource.getResourceType());
        assertSame(partner, resource.getOwner());
    }

    public void testResourceParamCreation() {
        Entity entity = new Entity();
        Unit unit = new Unit();
    	ResourceParameter resourceParameter = ResourceCreatorImpl.resourceParameterFactory(entity, "123", unit);
    	assertSame(entity, resourceParameter.getEntity());
    	assertEquals("123", resourceParameter.getParameterCode());
    	assertSame(unit, resourceParameter.getUnit());
    }
    
    public void testResourceParamCreationNoUnit() {
        Entity entity = new Entity();
    	ResourceParameter resourceParameter = ResourceCreatorImpl.resourceParameterFactory(entity, "123", null);
    	assertNull(resourceParameter.getUnit());
    }
    
    public void testResourceParamCreationParent() {
        Entity entity = new Entity();
        Unit unit = new Unit();
		ResourceParameter parent = new ResourceParameter();
		parent.setEntity(entity);
		parent.setUnit(unit);
    	ResourceParameter resourceParameter = ResourceCreatorImpl.resourceParameterFactory(parent, "123");
    	assertSame(entity, resourceParameter.getEntity());
    	assertEquals("123", resourceParameter.getParameterCode());
    	assertSame(unit, resourceParameter.getUnit());
    	assertSame(parent, resourceParameter.getParent());
    }
    
    public void testResourceParamCreationException() {
        Entity entity = null;
        try {
            ResourceCreatorImpl.resourceParameterFactory(entity, "", null);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
        ResourceParameter parent = null;
        try {
            ResourceCreatorImpl.resourceParameterFactory(parent, "");
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
    
    public void testResourceParamValueCreation() {
    	ResourceGroup resourceGroup = new ResourceGroup();
    	ResourceParameter resourceParameter = new ResourceParameter();
    	ResourceParameterValue resourceParameterValue = ResourceCreatorImpl.resourceParameterValueFactory(resourceGroup, resourceParameter);
    	assertSame(resourceGroup, resourceParameterValue.getResource());
    	assertSame(resourceParameter, resourceParameterValue.getParameter());
    	assertFalse(resourceParameterValue.isSuppressed());
    }
    
    public void testResourceParamValueCreationNotSuppressed() {
    	ResourceParameterValue resourceParameterValue = ResourceCreatorImpl.resourceParameterValueFactory(new ResourceGroup(), new ResourceParameter());
    	assertFalse(resourceParameterValue.isSuppressed());
    }
    
    public void testResourceParamValueCreationException() {
    	try {
            ResourceCreatorImpl.resourceParameterValueFactory(null, new ResourceParameter());
    		fail();
    	} catch (IllegalArgumentException e) {
    		// expected
    	}
    	try {
            ResourceCreatorImpl.resourceParameterValueFactory(new ResourceGroup(), null);
    		fail();
    	} catch (IllegalArgumentException e) {
    		// expected
    	}
    }
    
}
