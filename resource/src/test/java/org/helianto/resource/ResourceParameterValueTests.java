package org.helianto.resource;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.ResourceParameter;
import org.helianto.resource.ResourceParameterValue;


/**
 * <code>ResourceParameterValue</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterValueTests extends TestCase {
    
    /**
     * Test <code>ResourceParameterValue</code> static factory method.
     */
    public void testResourceParameterValueFactoryClass() {
    	ResourceGroup resourceGroup = new ResourceGroup();
    	ResourceParameter resourceParameter = new ResourceParameter();

		ResourceParameterValue resourceParameterValue = ResourceParameterValue.resourceParameterValueFactory(resourceGroup, resourceParameter);

        assertTrue(resourceParameterValue instanceof ResourceParameterValue);
		assertSame(resourceGroup, resourceParameterValue.getResource());
		assertSame(resourceParameter, resourceParameterValue.getParameter());
        
    }
    
    /**
     * Test <code>ResourceParameterValue</code> equals() method.
     */
    public void testResourceParameterValueEquals() {
    	ResourceGroup resourceGroup = new ResourceGroup();
    	ResourceParameter resourceParameter = new ResourceParameter();

		ResourceParameterValue resourceParameterValue = ResourceParameterValue.resourceParameterValueFactory(resourceGroup, resourceParameter);
        ResourceParameterValue copy = (ResourceParameterValue) DomainTestSupport.minimalEqualsTest(resourceParameterValue);
        
        copy.setResource(null);
        copy.setParameter(resourceParameter);
        assertFalse(resourceParameterValue.equals(copy));

        copy.setResource(resourceGroup);
        copy.setParameter(null);
        assertFalse(resourceParameterValue.equals(copy));

        copy.setResource(resourceGroup);
        copy.setParameter(resourceParameter);

        assertTrue(resourceParameterValue.equals(copy));
    }
    
}
