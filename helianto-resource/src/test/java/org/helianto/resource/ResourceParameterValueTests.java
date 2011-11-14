package org.helianto.resource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.domain.ResourceParameter;
import org.helianto.resource.domain.ResourceParameterValue;
import org.junit.Test;


/**
 * <code>ResourceParameterValue</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterValueTests {
    
    /**
     * Test <code>ResourceParameterValue</code> static factory method.
     */
	@Test
    public void resourceParameterValueFactoryClass() {
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
	@Test
    public void resourceParameterValueEquals() {
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
