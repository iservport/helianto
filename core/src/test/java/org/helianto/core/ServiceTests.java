package org.helianto.core;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;

/**
 * <code>Service</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServiceTests extends TestCase {
    
    /**
     * Test <code>Service</code> static factory method.
     */
    public void testServiceFactory() {
        Operator operator = new Operator();
        String serviceName = DomainTestSupport.STRING_TEST_VALUE;
        
        Service service = Service.serviceFactory(operator, serviceName);
        
        assertSame(operator, service.getOperator());
        assertEquals(serviceName, service.getServiceName());
        
    }
    
    /**
     * Test <code>Service</code> equals() method.
     */
    public void testServiceEquals() {
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
    
    
