package org.helianto.resource.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.resource.domain.ResourceGroup;
import org.junit.Test;


/**
 * <code>ResourceGroup</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupTests {
    
    /**
     * Test <code>ResourceGroup</code> static factory method.
     */
	@Test
    public void resourceGroupFactoryClass() {
        Entity entity = new Entity();

		ResourceGroup resourceGroup = new ResourceGroup(entity, "CODE");

        assertTrue(resourceGroup instanceof ResourceGroup);
		assertSame(entity, resourceGroup.getEntity());
        assertEquals("CODE", resourceGroup.getResourceCode());
        
    }
    
    /**
     * Test <code>ResourceGroup</code> equals() method.
     */
	@Test
    public void resourceGroupEquals() {
        Entity entity = new Entity();
        
		ResourceGroup resourceGroup = new ResourceGroup(entity, "CODE");
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
