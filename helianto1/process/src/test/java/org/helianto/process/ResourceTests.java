package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;


/**
 * <code>Resource</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceTests extends TestCase {
    
    /**
     * Test <code>Resource</code> static factory method.
     */
    public void testResourceFactoryClass() {
        Entity entity = new Entity();

		Resource resource = Resource.resourceFactory(entity, "CODE");

        assertTrue(resource instanceof Resource);
		assertSame(entity, resource.getEntity());
        assertEquals("CODE", resource.getResourceCode());
        
    }
    
    /**
     * Test <code>Resource</code> static factory method.
     */
    public void testResourceFactoryParent() {
        Entity entity = new Entity();
    	Resource parent = new Resource();
    	parent.setEntity(entity);

		Resource resource = Resource.resourceFactory(parent, "CODE");

        assertTrue(resource instanceof Resource);
		assertSame(entity, resource.getEntity());
        assertEquals("CODE", resource.getResourceCode());
        assertSame(parent, resource.getParent());
        
    }
    
    /**
     * Test <code>Resource</code> equals() method.
     */
    public void testResourceEquals() {
        Entity entity = new Entity();
        
		Resource resource = Resource.resourceFactory(entity, "CODE");
        Resource copy = (Resource) DomainTestSupport.minimalEqualsTest(resource);
        
        copy.setEntity(null);
        copy.setResourceCode("CODE");
        assertFalse(resource.equals(copy));

        copy.setEntity(entity);
        copy.setResourceCode("");
        assertFalse(resource.equals(copy));

        copy.setEntity(entity);
        copy.setResourceCode("CODE");

        assertTrue(resource.equals(copy));
    }
    
}
