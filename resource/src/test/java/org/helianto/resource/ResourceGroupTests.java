package org.helianto.resource;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.resource.ResourceGroup;


/**
 * <code>ResourceGroup</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupTests extends TestCase {
    
    /**
     * Test <code>ResourceGroup</code> static factory method.
     */
    public void testResourceGroupFactoryClass() {
        Entity entity = new Entity();

		ResourceGroup resourceGroup = ResourceGroup.resourceGroupFactory(entity, "CODE");

        assertTrue(resourceGroup instanceof ResourceGroup);
		assertSame(entity, resourceGroup.getEntity());
        assertEquals("CODE", resourceGroup.getResourceCode());
        
    }
    
    /**
     * Test <code>ResourceGroup</code> equals() method.
     */
    public void testResourceGroupEquals() {
        Entity entity = new Entity();
        
		ResourceGroup resourceGroup = ResourceGroup.resourceGroupFactory(entity, "CODE");
        ResourceGroup copy = (ResourceGroup) DomainTestSupport.minimalEqualsTest(resourceGroup);
        
        copy.setEntity(null);
        copy.setResourceCode("CODE");
        assertFalse(resourceGroup.equals(copy));

        copy.setEntity(entity);
        copy.setResourceCode("");
        assertFalse(resourceGroup.equals(copy));

        copy.setEntity(entity);
        copy.setResourceCode("CODE");

        assertTrue(resourceGroup.equals(copy));
    }
    
}
