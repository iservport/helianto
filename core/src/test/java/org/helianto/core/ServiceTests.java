package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Service</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServiceTests {
    
    /**
     * Test <code>Service</code> static factory method.
     */
	@Test
    public void serviceFactory() {
        Operator operator = new Operator();
        String serviceName = DomainTestSupport.STRING_TEST_VALUE;
        
        Service service = Service.serviceFactory(operator, serviceName);
        
        assertSame(operator, service.getOperator());
        assertEquals(serviceName, service.getServiceName());
        
    }
    
    /**
     * Test <code>Service</code> equals() method.
     */
	@Test
    public void serviceEquals() {
        Operator operator = new Operator();
        String serviceName = DomainTestSupport.STRING_TEST_VALUE;
        
        Service service = Service.serviceFactory(operator, serviceName);
        Service copy = (Service) DomainTestSupport.minimalEqualsTest(service);
        
        copy.setOperator(null);
        copy.setServiceName(serviceName);
        assertFalse(service.equals(copy));

        copy.setOperator(operator);
        copy.setServiceName(null);
        assertFalse(service.equals(copy));

        copy.setOperator(operator);
        copy.setServiceName(serviceName);

        assertTrue(service.equals(copy));
    }

}
    
    
