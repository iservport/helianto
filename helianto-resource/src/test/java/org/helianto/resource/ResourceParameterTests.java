package org.helianto.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.resource.domain.ResourceParameter;
import org.junit.Test;


/**
 * <code>ResourceParameter</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterTests {
    
    /**
     * Test <code>ResourceParameter</code> static factory method.
     */
	@Test
    public void resourceParameterFactoryClass() {
        Entity entity = new Entity();

		ResourceParameter resourceParameter = ResourceParameter.resourceParameterFactory(entity, "CODE");

        assertTrue(resourceParameter instanceof ResourceParameter);
		assertSame(entity, resourceParameter.getEntity());
        assertEquals("CODE", resourceParameter.getParameterCode());
        
    }
    
    /**
     * Test <code>ResourceParameter</code> static factory method.
     */
	@Test
    public void resourceParameterFactoryParent() {
        Entity entity = new Entity();
    	ResourceParameter parent = new ResourceParameter();
    	parent.setEntity(entity);

		ResourceParameter resourceParameter = ResourceParameter.resourceParameterFactory(parent, "CODE");

        assertTrue(resourceParameter instanceof ResourceParameter);
		assertSame(entity, resourceParameter.getEntity());
        assertEquals("CODE", resourceParameter.getParameterCode());
        assertSame(parent, resourceParameter.getParent());
        
    }
    
    /**
     * Test <code>ResourceParameter</code> equals() method.
     */
	@Test
    public void resourceParameterEquals() {
        Entity entity = new Entity();
        
		ResourceParameter resourceParameter = ResourceParameter.resourceParameterFactory(entity, "CODE");
        ResourceParameter copy = (ResourceParameter) DomainTestSupport.minimalEqualsTest(resourceParameter);
        
        copy.setEntity(null);
        copy.setParameterCode("CODE");
        assertFalse(resourceParameter.equals(copy));

        copy.setEntity(entity);
        copy.setParameterCode("");
        assertFalse(resourceParameter.equals(copy));

        copy.setEntity(entity);
        copy.setParameterCode("CODE");

        assertTrue(resourceParameter.equals(copy));
    }
    
}
