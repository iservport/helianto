package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.core.test.DomainTestSupport;


/**
 * <code>ResourceParameter</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterTests extends TestCase {
    
    /**
     * Test <code>ResourceParameter</code> static factory method.
     */
    public void testResourceParameterFactoryClass() {
        Entity entity = new Entity();

		ResourceParameter resourceParameter = ResourceParameter.resourceParameterFactory(entity, "CODE");

        assertTrue(resourceParameter instanceof ResourceParameter);
		assertSame(entity, resourceParameter.getEntity());
        assertEquals("CODE", resourceParameter.getParameterCode());
        
    }
    
    /**
     * Test <code>ResourceParameter</code> static factory method.
     */
    public void testResourceParameterFactoryUnit() {
        Entity entity = new Entity();
        Unit unit = new Unit();
        unit.setEntity(entity);

		ResourceParameter resourceParameter = ResourceParameter.resourceParameterFactory(unit, "CODE");

        assertTrue(resourceParameter instanceof ResourceParameter);
		assertSame(entity, resourceParameter.getEntity());
        assertEquals("CODE", resourceParameter.getParameterCode());
        assertSame(unit, resourceParameter.getUnit());
        
    }
    
    /**
     * Test <code>ResourceParameter</code> static factory method.
     */
    public void testResourceParameterFactoryParent() {
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
    public void testResourceParameterEquals() {
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
