package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.filter.UserBackedFilter;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class ServiceFilterTests {

    @Test
    public void testConstructor() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof UserBackedFilter);
	}
	
    @Test
	public void testFactory() {
		assertSame(filter.getUser(), user);
		assertEquals("", filter.getServiceName());
	}
	
    public static String C1 = "service.operator.id = 1 AND service.serviceName = 'SERVICE' ";
    public static String C2 = "lower(service.serviceName) like '%service%' ";

    @Test
    public void testEmpty() {
        assertEquals("", filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setOperator(OperatorTestSupport.createOperator());
    	filter.getOperator().setId(1);
    	filter.setServiceName("SERVICE");
        assertEquals(C1, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilter() {
        filter.setServiceName("SERVICE");
        assertEquals(C2, filter.createCriteriaAsString(false));
    }
    
    private ServiceFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = ServiceFilter.serviceFilterFactory(user);
    }
}

